package dev.pages.ahsan.creminder.main;

import dev.pages.ahsan.creminder.cfoj.CFOJ;
import dev.pages.ahsan.creminder.cfoj.Contest;
import dev.pages.ahsan.creminder.email.Composer;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Arguments Check
        if (args.length < 4) {
            System.out.println(" - ERROR: Missing Configs!");
            System.exit(1);
        }

        // load user Configs
        Config.email = args[0];
        Config.pass = args[1];
        Config.to = args[2];
        Config.zoneID = args[3];
        Config.runType = args[4];

        // fetch api
        ArrayList<Contest> contests = CFOJ.getContestList(false, String.valueOf(Phase.BEFORE));

        // action
        if (contests.size() != 0) {
            Composer comp = new Composer();
            if (Config.runType.equalsIgnoreCase("daily")) {
                comp.compose(contests, Config.runType);
                comp.notifyMe(Config.email, Config.pass, Config.to);
            } else if (Config.runType.equalsIgnoreCase("hourly")) {
                contests = comp.filter(contests, Config.filterDuration);
                if (contests.size() > 0) {
                    comp.compose(contests, Config.runType);
                    comp.notifyMe(Config.email, Config.pass, Config.to);
                } else
                    System.out.println(" - No Contests Found Within 2 Hour!");
            }
        } else
            System.out.println(" - No Contests Found!");
    }
}