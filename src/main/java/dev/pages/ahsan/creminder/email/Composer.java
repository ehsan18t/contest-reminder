package dev.pages.ahsan.creminder.email;

import dev.pages.ahsan.creminder.cfoj.Contest;
import dev.pages.ahsan.creminder.main.Config;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

@SuppressWarnings("ALL")
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
        loadStyle();
        switch (runType) {
            case "daily":
                subject = "Upcoming Contests";
                break;
            case "hourly":
                subject = "Constests Starting Within 2 Hour";
                break;
        }

        msg = "";
        msg += styles.get("tableStart");
        msg += styles.get("tableHead");
        for (int i = list.size() - 1; i > -1; i--) {
            Contest c = list.get(i);

            msg += styles.get("trStart");

            // Name
            msg += styles.get("tdStart");
            msg += c.getName();
            msg += styles.get("tdEnd");

            // Start
            msg += styles.get("tdStart");
            msg += getDateTime(c.getStartTimeSeconds());
            msg += styles.get("tdEnd");

            // Before
            msg += styles.get("tdStart");
            msg += beforeLeft(c.getStartTimeSeconds()) + " left";
            msg += styles.get("tdEnd");

            msg += styles.get("trEnd");
        }
        msg += styles.get("tableEnd");
    }

    public void notifyMe(String email, String pass, String to) {
        MailSender.sendEmail(email, pass, msg, subject, to);
    }


    ///////////////////////////////
    // Internal Private Methods //
    //////////////////////////////
    private void loadStyle() {
        styles = new HashMap<>();
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream(Config.emailStyles);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("#")) {
                    String value = br.readLine();
                    styles.put(line.replace("#", ""), value);
                }
            }
        } catch (Exception e) {
            System.out.println(" - Exception in loading styles!");
            e.printStackTrace();
        }
    }

    private String getDateTime(LocalDateTime ldt) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy (E), hh:mm a");
        return ldt.format(dateTimeFormatter);
    }

    private String beforeLeft(LocalDateTime localDateTime) {
        String date = "";
        Duration diff = Duration.between(LocalDateTime.now(ZoneId.of(Config.zoneID)), localDateTime);

        if (diff.toDays() != 0) {
            date += diff.toDays() + "d ";
            date += (diff.toHours() % 24) + "h ";
            date += (diff.toMinutes() % 60) + "m ";
        } else if (diff.toHours() != 0) {
            date += (diff.toHours() % 24) + "h ";
            date += (diff.toMinutes() % 60) + "m ";
        } else
            date += (diff.toMinutes() % 60) + "m ";

        return date;
    }
}
