package com.rm.http.custom.socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;

public class DatagramRunner {

    public static void main(String[] args) throws IOException {

        InetAddress inetAddress = Inet4Address.getByName("localhost");
        try (DatagramSocket datagramSocket = new DatagramSocket()) {
            byte[] bytes = "Hello from UDP client".getBytes();
            DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length, inetAddress, 7777);
            datagramSocket.send(datagramPacket);
        }
    }
}
