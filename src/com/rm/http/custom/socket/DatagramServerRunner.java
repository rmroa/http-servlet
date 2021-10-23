package com.rm.http.custom.socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class DatagramServerRunner {

    public static void main(String[] args) throws IOException {

        try (DatagramSocket datagramServer = new DatagramSocket(7777)) {
            byte[] buffer = new byte[512];
            DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
            datagramServer.receive(datagramPacket);
            System.out.println(new String(buffer));
        }
    }
}
