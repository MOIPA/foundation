package com.company.transferHex;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ConfigReader {
    INSTANCE,
    ;

    private static String getConfigPath() {
        // 使用InPutStream流读取properties文件
        String root = System.getProperty("user.dir");
        String FileName = "config.properties";
        return root + File.separator + FileName;
    }
    /**
     * 读取字段对应的位置
     *
     * @return
     * @throws IOException
     */
    public static int[] getExeclPosi() throws IOException {
        Properties properties = new Properties();
        int[] res = new int[5];
       BufferedReader bufferedReader = new BufferedReader(new FileReader(getConfigPath()));
        properties.load(bufferedReader);
        // 获取key对应的value值
        res[0] = Integer.parseInt(properties.getProperty("ifRepeat"));
        res[1] = Integer.parseInt(properties.getProperty("signalName"));
        res[2] = Integer.parseInt(properties.getProperty("udpByte"));
        res[3] = Integer.parseInt(properties.getProperty("bitOffset"));
        res[4] = Integer.parseInt(properties.getProperty("filterPort"));
        return res;
    }

    public static Map<String, Byte> getFilterRule() throws IOException {
        Map<String, Byte> rules = new HashMap<>();
        Properties properties = new Properties();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(getConfigPath()));
        properties.load(bufferedReader);
        String rule = properties.getProperty("filterRule");
        // 正则循环查找
        int matchStart = 0;
        Matcher matcher = Pattern.compile("\\((.*?),(.*?)\\)").matcher(rule);
        while (matcher.find(matchStart)) {
            rules.put(matcher.group(1), Byte.valueOf(matcher.group(2)));
            matchStart = matcher.end();
        }
        return rules;
    }

}
