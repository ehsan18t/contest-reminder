package dev.pages.ahsan.creminder.email;

import dev.pages.ahsan.creminder.cfoj.Contest;
import dev.pages.ahsan.creminder.main.Config;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class Composer {
    private String msg;
    private String subject;
    private HashMap<String, String> styles;

    public ArrayList<Contest> filter(ArrayList<Contest> list, long seconds) {
        ArrayList<Contest> newList = new ArrayList<>();

        for (Contest c : list) {
            Duration diff = Duration.between(LocalDateTime.now(ZoneId.of(Config.zoneID)), c.getStartTimeSeconds());
            if (diff.toSeconds() < seconds)
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
    private String makeLink(Contest c) {
        return styles.get("a").replace("LINKHERE", Config.oj + "contests/" + c.getId()) + c.getName() + styles.get("_a")
                + (c.getRelativeTimeSeconds() > -259200 ? styles.get("5space")
                + styles.get("aBtn").replace("LINKHERE", Config.oj + "contestRegistration/" + c.getId())
                +"Register" + styles.get("_a") : "");
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
