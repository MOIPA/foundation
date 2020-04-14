package com.ds.KMP;

/**
 * @author TR
 * @content 模式匹配算法KMP
 */
public class KMP {
    public int match(String s, String p) {
        boolean isMatch;
        int j=0;
        int matchPoint=0;
        for (int i = 0; i < s.length(); i++) {
            isMatch = true;
            matchPoint=i;
            for (; j < p.length(); j++,i++) {
                if (s.charAt(i) != p.charAt(j)) {
                    isMatch = false;
                    i--;
                    if(j==0)i++;
                    j=calcNext(p.substring(0,j));
                    break;
                }
            }
            if (isMatch)return matchPoint;
        }
        return matchPoint;
    }

     private int calcNext(String s) {
        // 计算左右最大相等点 aabbaa = 2 abcda = 1
         int i;
        for (i = 0; i < s.length() / 2; i++)
            if (s.charAt(i) != s.charAt(s.length() - i - 1)) {
                return i;
            }
        return i;
    }
}

class main {
    public static void main(String[] args) {
        KMP match = new KMP();
//        System.out.println(match.match("abcabaaddaabaabcac", "abaabcac"));
        System.out.println(match.match("我在哪里学的java", "哪里学的"));
    }
}
