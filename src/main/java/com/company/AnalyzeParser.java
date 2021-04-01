package com.company;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnalyzeParser {
    public static void main(String[] args) {
//        String analyzer = analyzeSingalWithRuler(new String[]{"序列", "0","1","8","8"}, "[{<1,0,0,*>=无ATP制动},{<1,0,1,*>=ATP制动1级}," +
//                "{<1,0,4,*>=ATP制动4级},{<1,0,7,*>=ATP制动7级},{<1,0,8,*>=ATP制动UB},{<1,0,9,*>=隔离}," +
//                "{<0,1,*,0>=无ATP制动},{<0,1,*,1>=ATP制动1级},{<0,1,*,4>=ATP制动4级},{<0,1,*,7>=ATP制动7级}," +
//                "{<0,1,*,8>=ATP制动UB},{<0,1,*,9>=隔离},{<0,0,*,*>=从控车}]");
        String analyzer = analyzeSingalWithRuler(new String[]{"序列", "0", "0", "3", "4"}, "[{<0,0,*>=无},{<1,0,*>=x3},{<0,1,*>=x4},{<1,1,*>=异常(color:red)}]");
        System.out.println(analyzer);
    }

    public static String analyzeSingalWithRuler(String[] valueArray, String ruleToken) {
        // * 匹配次数
        int regUseTimes = 0;
        List<String[]> tmpResult = new LinkedList<>();
        AtomicReference<String> parsedResult = new AtomicReference<>("");
        // 提取规则和对应的值
        Map<String, String> ruleAndVaule = new HashMap<>();
        Matcher matcher = Pattern.compile("\\{<(.*?)>=(.*?)\\}").matcher(ruleToken);
        int matcherStart = 0;
        while (matcher.find(matcherStart)) {
            ruleAndVaule.put(matcher.group(1), matcher.group(2));
            matcherStart = matcher.end();
        }
        // 匹配所有规则
        for (String exactRuler : ruleAndVaule.keySet()) {
            regUseTimes = 0;
            String crrValue = ruleAndVaule.get(exactRuler);
            String[] singleRuler = exactRuler.split(",");
            boolean success = true;
            for (int i = 0; i < singleRuler.length; i++) {
                if (!singleRuler[i].equals("*") && !singleRuler[i].equals(valueArray[i + 1])) {
                    success = false;
                    break;
                }
                if (singleRuler[i].equals("*")) regUseTimes++;
            }
            // 一条规则匹配成功 记录*匹配次数和匹配结果
            if (success) tmpResult.add(new String[]{crrValue, String.valueOf(regUseTimes)});
        }
        // 找出匹配次数最少的结果
        Optional<String[]> first = tmpResult.stream().min(Comparator.comparingInt(x -> Integer.parseInt(x[1])));
        first.ifPresent(strings -> parsedResult.set(strings[0]));
        // 单位处理
        String rsltWithUnit = parsedResult.get();
        String regString = "\\+?x(\\d)\\+?";
        if (rsltWithUnit.equals("")) return "异常";
        Matcher unitMatcher = Pattern.compile(regString).matcher(rsltWithUnit);
        if(unitMatcher.find())
            rsltWithUnit = rsltWithUnit.replaceAll(regString, valueArray[Integer.parseInt(unitMatcher.group(1))]);
        return rsltWithUnit;
    }
}
