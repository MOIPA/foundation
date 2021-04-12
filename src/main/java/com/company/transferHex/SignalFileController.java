package com.company.transferHex;

import java.io.*;
import java.util.List;

class SignalFileController {
    private String filePath = "";
    private byte[] bytes = null;

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    // 返回10进制的文件内容
    public void getFileContentDex() throws IOException {
        File file = new File(this.filePath);
        FileInputStream fileInputStream = new FileInputStream(file);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int len = -1;
        byte[] buf = new byte[1024];
        while ((len = fileInputStream.read(buf)) != -1) {//汇总字节流到内存
            byteArrayOutputStream.write(buf, 0, len);
        }
        byteArrayOutputStream.close();
        fileInputStream.close();
        // control bytes
        byte[] bytes = byteArrayOutputStream.toByteArray();
        this.bytes = bytes;
//        return bytes;

    }

    /**
     * 修改地址位置为1
     *
     * @param posi       第几个字节  10进制
     * @param posiOffset 字节内偏移 从右往左
     * @param udpOffset  字节偏移
     */
    public void changePosition(String posi, int posiOffset, int udpOffset) {
        // transfer bytes to binary list
        int changePosi = Integer.parseInt(posi);
        // 修改的第几个字节
        changePosi += udpOffset;
        int byteOffset = 7 - posiOffset;
        byte b = this.bytes[changePosi];
        char[] bChars = Integer.toBinaryString(b).toCharArray();
        // 补0
        if (bChars.length < 8) {
            int suppl = 8 - bChars.length;
            char[] back = bChars.clone();
            bChars = new char[8];
            for (int i = 0; i < suppl; i++) {
                bChars[i] = '0';
            }
            for (int i = suppl; i < 8; i++) {
                bChars[i] = back[i - suppl];
            }
        }
        bChars[byteOffset] = '1';
        if (bChars[0] == '1') {
            this.bytes[changePosi] = -1;
        } else {
            this.bytes[changePosi] = Byte.parseByte(String.valueOf(bChars), 2);
        }
    }

    /**
     * 写回文件
     */
    public void writeFile() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            fileOutputStream.write(this.bytes);
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
