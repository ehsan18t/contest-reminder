package dev.pages.ahsan.creminder.email;

import dev.pages.ahsan.creminder.cfoj.Contest;
import dev.pages.ahsan.creminder.main.Config;
import dev.pages.ahsan.creminder.utils.Utils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Composer {
    private String msg;
    private String subject;
    private HashMap<String, String> styles;
    private HashMap<String, String> encodings;

    // curr = LocalDateTime.now()
    // take contest within (curr - seconds) to curr
    public ArrayList<Contest> filter(ArrayList<Contest> list, long seconds) {
        ArrayList<Contest> newList = new ArrayList<>();

        for (Contest c : list) {
            // Either starting within 2 hour
            // or, started not more than 5 minutes (This condition will automatically be fulfilled [check: CFOJ.java])
            if ((-1L) * c.getRelativeTimeSeconds() < seconds)
                newList.add(c);
        }
        return newList;
    }

    public void compose(ArrayList<Contest> list, String runType) {
        subject = "(" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMM/dd/yyyy")) + ") ";
        loadStyle();
        switch (runType) {
            case "daily":
                subject += "Upcoming Contests";
                break;
            case "hourly":
                subject += "Contests Starting Within 2 Hour";
                break;
        }

        msg = "";
        msg += styles.get("table");
        msg += styles.get("tableHead");
        for (int i = list.size() - 1; i > -1; i--) {
            Contest c = list.get(i);

            //noinspection StringConcatenationInLoop
            msg += styles.get("tr");

            // Name
            //noinspection StringConcatenationInLoop
            msg += getTD(makeLink(c));

            // Start
            if (runType.equalsIgnoreCase("daily"))
                //noinspection StringConcatenationInLoop
                msg += getTD(getDateTime(c));  // add google calender link
            else
                //noinspection StringConcatenationInLoop
                msg += getTD(getDateTime(c.getStartTimeSeconds()));

            // Before
            //noinspection StringConcatenationInLoop
            msg += getTD(beforeLeft(c.getStartTimeSeconds()) + " left");

            //noinspection StringConcatenationInLoop
            msg += styles.get("_tr");
        }
        msg += styles.get("_table");
    }

    public void notifyMe(String email, String pass, String to) {
        MailSender.sendEmail(email, pass, msg, subject, to);
    }


    ///////////////////////////////
    // Internal Private Methods //
    //////////////////////////////
    // 172800 = 2 days
    private String makeLink(Contest c) {
        return styles.get("a").replace("LINK-HERE", Config.oj + "contests/" + c.getId()) + c.getName() + styles.get("_a")
                + (c.getRelativeTimeSeconds() > -172800 ? styles.get("5space")
                + styles.get("aBtn").replace("LINK-HERE", Config.oj + "contestRegistration/" + c.getId())
                + "Register" + styles.get("_a") : "");
    }

    private String getTD(String str) {
        return styles.get("td") + str + styles.get("_td");
    }

    private void loadStyle() {
        styles = Utils.readHashMapFromFile(Config.emailStyles, "#");
    }

    private String getDateTime(LocalDateTime ldt) {
        return ldt.format(DateTimeFormatter.ofPattern("MMM dd, yyyy")) + "<br>" + ldt.format(DateTimeFormatter.ofPattern("E, hh:mm a"));
    }

    private String getDateTime(Contest c) {
        String str = getDateTime(c.getStartTimeSeconds());
        String googleCalLink = genGCalLink(c);
        return styles.get("a").replace("LINK-HERE", googleCalLink) + str + styles.get("_a");
    }

    private String getDateTime(LocalDateTime ldt, long duration) {
        String date = "";
        date += ldt.format(DateTimeFormatter.ofPattern("yyyyMMdd")) + "T" + ldt.format(DateTimeFormatter.ofPattern("HHmmss"));
        LocalDateTime end = ldt.plusSeconds(duration);
        date += "/" + end.format(DateTimeFormatter.ofPattern("yyyyMMdd")) + "T" + end.format(DateTimeFormatter.ofPattern("HHmmss"));
        return date;
    }

    private String genGCalLink(Contest c) {
        loadEncodings();
        String base = "https://calendar.google.com/calendar/r/eventedit?";
        base += "text=" + encode(c.getName());
        base += "&details=" + encode(c.getName() + " " + Config.oj + "contests/" + c.getId());
        base += "&dates=" + encode(getDateTime(c.getStartTimeSeconds(), c.getDurationSeconds()));
        base += "&ctz=" + encode(Config.zoneID);
        return base;
    }

    private String encode(String str) {
        String encoded = "";
        for (int i = 0; i < str.length(); i++) {
            String c = str.substring(i, i + 1);
            String ec = encodings.get(c);
            //noinspection StringConcatenationInLoop
            encoded += Objects.requireNonNullElse(ec, c);
        }
        return encoded;
    }

    private void loadEncodings() {
        encodings = Utils.readHashMapFromFile(Config.urlEncodings, "#");
        // manually adding space into map because most editor will
        // remove whitespace from url-encoding.txt file automatically.
        encodings.put(" ", "%20");
    }

    private String beforeLeft(LocalDateTime localDateTime) {
        String date = "";
        Duration diff = Duration.between(LocalDateTime.now(ZoneId.of(Config.zoneID)), localDateTime);

        if (diff.toDays() != 0)
            date += diff.toDays() + "d ";
        if (diff.toHours() != 0)
            date += (diff.toHours() % 24) + "h ";
        date += (diff.toMinutes() % 60) + "m";

        return date;
    }
}
