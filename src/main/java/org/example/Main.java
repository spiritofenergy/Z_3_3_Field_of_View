package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        String term = getUserInput();
        String url = buildUrl(term);
        String page = downloadWebPage(url);
        printResult(page);
    }

    private static void printResult(String page) {
        System.out.println(page);
        int start = page.indexOf("wrapperType") + 14;
        int end = page.indexOf("\",", start);
        System.out.println(page.substring(start,end));
    }

    private static String buildUrl(String term) {
        String termWithoutSpaces = term.replaceAll(" ", "+");
        String itunesApi = "https://itunes.apple.com/search?term=";
        String limitParam = "&limit=1";
        String url = itunesApi + termWithoutSpaces + limitParam;
        return url;
    }

    static String getUserInput( ){
        System.out.println("What you looking for in itunes");
        Scanner scanner = new Scanner(System.in);
        String info = scanner.nextLine();
        return info;
    }

    private static String downloadWebPage(String url) throws IOException {
        StringBuilder result = new StringBuilder();
        String line;
        URLConnection urlConnection = new URL(url).openConnection();
        try (InputStream is = urlConnection.getInputStream();
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            while ((line = br.readLine()) != null) {
                result.append(line);}
        }
        return result.toString();
    }
}