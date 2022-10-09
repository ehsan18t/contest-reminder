package dev.pages.ahsan.creminder.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.ZoneId;

public class Config {
    // APIs
    public static String contestAPI = "https://codeforces.com/api/contest.list?gym=";

    // Mail Server Settings
    public static final String HOST = "smtp.gmail.com";
    public static final String PORT = "465";

    // Mail Settings
    public static final String emailStyles = "css/email.styles";

    // User Config
    public static String email; // will load from gh secret
    public static String pass; // will load from gh secret
    public static String to; // will load from gh secret
    public static String zoneID; // will load from gh secret
    public static String runType; // will load from gh secret
}
