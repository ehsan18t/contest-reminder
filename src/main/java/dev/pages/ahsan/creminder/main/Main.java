package dev.pages.ahsan.creminder.main;

import dev.pages.ahsan.creminder.cfoj.CFOJ;
import dev.pages.ahsan.creminder.cfoj.Contest;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Contest> contests = CFOJ.getContestList(false, String.valueOf(Phase.BEFORE));
        for (Contest c : contests)
            System.out.println(c);
    }
}