package com.ds.KMP;

/**
 * @author TR
 * @content 朴素字符串匹配算法
 */
public class NormalMatch {
    public static void main(String[] args) {
        Match match = new Match();
        System.out.println(match.match("abcabaaddaabaabcac","abaabcac"));
    }
}

class Match {
    public int match(String s, String p) {
        boolean ismatch;
        for (int i = 0; i < s.length(); i++) {
            ismatch = true;
            for (int j = 0; j < p.length()&& j<s.length(); j++) {
                if (s.charAt(j+i) != p.charAt(j)) {
                    ismatch = false;
                    break;
                }
            }
            if (ismatch)return i;
        }
        return -1;
    }
}
