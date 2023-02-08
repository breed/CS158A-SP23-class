package edu.sjsu.cs158a.udp;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.Random;

public class UDPSender {
    public static void main(String[] arg) throws IOException {
        try (var sock = new DatagramSocket()) {
            System.out.println(sock.getLocalSocketAddress());
            System.out.println(sock.getRemoteSocketAddress());
            var count = 3;
            byte[] bytes = new byte[4*count];
            var bb = ByteBuffer.wrap(bytes);
            for (int i = 0; i < count; i++) {
                var rand = new Random().nextInt();
                bb.putInt(rand);
            }
            var packet = new DatagramPacket(bytes, bytes.length);
            packet.setAddress(InetAddress.getByName("127.0.0.1"));
            packet.setPort(2323);
            sock.send(packet);
        }
    }
}
