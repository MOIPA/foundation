package com.company.UDP;

import java.math.BigInteger;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer {
    public static void main(String args[]) {
        try {
            System.out.println("UDP服务端启动成功，等待接收消息...");
            int port = Integer.parseInt("10004");    // 从命令行中获取端口号参数
            // 创建一个socket，侦听这个端口。
            DatagramSocket dsocket = new DatagramSocket(port);
            byte[] buffer = new byte[20];        // 保存接收到的UDP报文的字节数组
            // 创建一个DatagramPacket，将收到的报文写入buffer中。
            // 注意，这里指定了报文的长度，如果收到的UDP报文比2048大，
            // 多余的信息被舍弃
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            // 不断循环，接收数据
            while (true) {
                dsocket.receive(packet); // 等待收到一个数据包
                // 将收到的报文的字节数组封装成字符串。
                String msg = new String(buffer, 0, packet.getLength());
                // 从数据包中取得消息来源的地址
                System.out.println("接收到的16进制字节：");
                System.out.println(bytesToHexString(buffer,packet.getLength()));
                System.out.println("Receive: "
                        + packet.getAddress().getHostAddress() + ": " + msg);
                // 如果收到QUIT指令，则退出循环。
                if (msg.equalsIgnoreCase("QUIT")) {
                    System.out.println("退出UDP服务");
                    break;
                }
                // packet在接受过数据后大小会变为实际接受字符数量，如果下一次接受的时候不重新设置，会越来越小
                packet.setLength(buffer.length); // 重设数据包的长度
            }
            // 关闭socket
            dsocket.close();
        } catch (Exception e) {
            System.err.println(e);
            // System.err.println(usage);
        }
    }
    public static String bytesToHexString(byte[] src,int realLength){
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || realLength <= 0) {
            return null;
        }
        for (int i = 0; i < realLength; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
}
