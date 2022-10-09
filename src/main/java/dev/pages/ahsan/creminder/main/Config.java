package dev.pages.ahsan.creminder.main;

public class Config {
    // App Settings
    // 2 Hour 5 Minutes
    // This config will select contest that are going to start within this time
    // for hourly reminder
    public static long filterDuration = 7500;

    // APIs
    public static String contestAPI = "https://codeforces.com/api/contest.list?gym=";
    public static String oj = "https://codeforces.com/";

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
