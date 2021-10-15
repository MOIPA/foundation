package com.company.UDP;

import java.io.File;
import java.io.FileInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
    public static void main(String args[]) {
        try {

            // 域名和端口
            String host = "localhost";
            int port = Integer.parseInt("10002");
            // 下面构造待发送报文的字节数组
            byte[] message;

            // udp 发送文件
            /**
             // 如果第三个参数为 -f，则表示将文件的内容以UDP方式发送
             // 获得待发送的文件对象以及文件的长度
             File f = new File(args[3]);
             int len = (int) f.length();
             // 创建一个足够容纳文件内容的字节数组
             message = new byte[len];
             FileInputStream in = new FileInputStream(f);
             // 将文件内容以字节的方式读到字节数组中
             int bytes_read = 0, n;
             do {
             n = in.read(message, bytes_read, len - bytes_read);
             bytes_read += n;
             } while ((bytes_read < len) && (n != -1));
             */

            // udp 发送消息
//            String msg = "hello";
            byte[] data = {
                    (byte)0xfc,0x00,(byte)0xFF,(byte)0xFb
            };

            message = data;
            // 根据域名获取IP地址
            InetAddress address = InetAddress.getByName(host);
            // 初始化一个UDP包。
            // DatagramPacket的构造方法中必须使用InetAddress，而不能是IP地址或者域名
            DatagramPacket packet = new DatagramPacket(message, message.length,
                    address, port);
            // 创建一个DatagramSocket，以发送UDP包
            DatagramSocket dsocket = new DatagramSocket();
            dsocket.send(packet);
            System.out.println("send: " + new String(message));
            dsocket.close();
            // 注意：如果在构造DatagramPacket时，不提供IP地址和端口号，
            // 则需要调用DatagramSocket的connect方法，否则无法发送UDP包
//            packet = new DatagramPacket(message, message.length);
//            dsocket = new DatagramSocket();
//            dsocket.connect(address, port);
//            dsocket.send(packet);
//            System.out.println("Send: " + new String(message));
//            dsocket.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

}
