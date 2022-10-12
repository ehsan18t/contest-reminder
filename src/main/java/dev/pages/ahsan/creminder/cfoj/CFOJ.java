package dev.pages.ahsan.creminder.cfoj;

import com.fasterxml.jackson.databind.JsonNode;
import dev.pages.ahsan.creminder.main.Config;
import dev.pages.ahsan.creminder.main.Phase;
import dev.pages.ahsan.creminder.parser.JSON;

import java.util.ArrayList;


public class CFOJ {
    public static ArrayList<Contest> getContestList(boolean gym, String phase) {
        ArrayList<Contest> list = new ArrayList<>();
        JsonNode node = JSON.request(Config.contestAPI + gym + "");
//        System.out.println(Config.contestAPI + gym + "");
        try {
            if (node.get("status").toString().equals("\"OK\"")) {
                JsonNode result = node.path("result");
                if (phase.equalsIgnoreCase("ALL")) {
                    for (JsonNode c : result) {
                        Contest contest = JSON.getMapper().readValue(c.toString(), Contest.class);
                        list.add(contest);
                    }
                } else {
                    for (JsonNode c : result) {
                        Contest contest = JSON.getMapper().readValue(c.toString(), Contest.class);
                        // Hasn't started yet
                        boolean condition1 = contest.getPhase().equalsIgnoreCase(phase);
                        // Started not more than 5 min
                        boolean condition2 = contest.getPhase().equalsIgnoreCase(String.valueOf(Phase.CODING)) && contest.getRelativeTimeSeconds() < 300;

                        if (condition1 || condition2)
                            list.add(contest);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(" - API Fetch Failed!");
            e.printStackTrace();
        }
        return list;
    }

    public static ArrayList<Contest> getContestList(boolean gym) {
        return getContestList(gym, "ALL");
    }
}
