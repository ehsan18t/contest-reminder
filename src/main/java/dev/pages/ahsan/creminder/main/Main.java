package dev.pages.ahsan.creminder.main;

import dev.pages.ahsan.creminder.cfoj.CFOJ;
import dev.pages.ahsan.creminder.cfoj.Contest;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Arguments Check
        if(args.length < 4) {
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
        for (Contest c : contests)
            System.out.println(c);
    }
}