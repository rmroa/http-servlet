package com.rm.http.custom.client;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class UrlExample {

    public static void main(String[] args) throws IOException {

        URL url = new URL("file:path\\DatagramRunner.java");
        URLConnection urlConnection = url.openConnection();

        System.out.println(new String(urlConnection.getInputStream().readAllBytes()));
    }

    private static void checkGoogle() throws IOException {
        URL url = new URL("https://google.com");
        URLConnection urlConnection = url.openConnection();
        urlConnection.setDoOutput(true);

        try (OutputStream outputStream = urlConnection.getOutputStream()) {
        }
        System.out.println();
    }
}
