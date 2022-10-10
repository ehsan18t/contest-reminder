package dev.pages.ahsan.creminder.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Utils {
    public static HashMap<String, String> readHashMapFromFile(String path, String del) {
        HashMap<String, String> map = new HashMap<>();
        try {
            InputStream is = Utils.class.getClassLoader().getResourceAsStream(path);
            assert is != null;
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith(del)) {
                    String value = br.readLine();
                    map.put(line.substring(1), value);
                }
            }
        } catch (Exception e) {
            System.out.println(" - Exception in reading " + path + "file!");
            e.printStackTrace();
        }
        return map;
    }
}
