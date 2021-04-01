package com.company;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ParserTest {

    @Test
    public void testLieHao() {
        String ruleToken = "[{<0,0,*>=无},{<1,0,*>=x3},{<0,1,*>=x4},{<1,1,*>=异常}]";
        String[][] testSuit = new String[][]{
                {"序列", "0", "0", "3", "4"},
                {"序列", "1", "0", "3", "4"},
                {"序列", "0", "1", "3", "4"},
                {"序列", "1", "1", "3", "4"}
        };
        assertEquals("无", AnalyzeParser.analyzeSingalWithRuler(testSuit[0], ruleToken));
        assertEquals("3", AnalyzeParser.analyzeSingalWithRuler(testSuit[1], ruleToken));
        assertEquals("4", AnalyzeParser.analyzeSingalWithRuler(testSuit[2], ruleToken));
        assertEquals("异常", AnalyzeParser.analyzeSingalWithRuler(testSuit[3], ruleToken));
    }

    @Test
    public void testZhuCongKongChe() {
        String ruleToken = "[{<1,0>=1车主控},{<0,1>=8车主控},{<0,0>=从控车},{<1,1>=异常}]";
        String[][] testSuit = new String[][]{
                {"主从控车", "1", "0"},
                {"主从控车", "0", "1"},
                {"主从控车", "0", "0"},
                {"主从控车", "1", "1"}
        };
        assertEquals("1车主控", AnalyzeParser.analyzeSingalWithRuler(testSuit[0], ruleToken));
        assertEquals("8车主控", AnalyzeParser.analyzeSingalWithRuler(testSuit[1], ruleToken));
        assertEquals("从控车", AnalyzeParser.analyzeSingalWithRuler(testSuit[2], ruleToken));
        assertEquals("异常", AnalyzeParser.analyzeSingalWithRuler(testSuit[3], ruleToken));
    }

    @Test
    public void testGongliBiao() {
        String ruleToken = "[{<1,0,2147483,*>=无效},{<1,0,*,*>=x3+m},{<0,1,*,2147483>=无效},{<0,1,*,*>=x4+m},{<0,0,*,*>=无效}]";
        String[][] testSuit = new String[][]{
                {"公里标", "1", "0", "2147483", "0"},
                {"公里标", "1", "0", "2123", "1"},
                {"公里标", "0", "1", "0", "2147483"},
                {"公里标", "0", "1", "123123", "12312"},
                {"公里标", "0", "0", "1", "1821"}
        };
        assertEquals("无效", AnalyzeParser.analyzeSingalWithRuler(testSuit[0], ruleToken));
        assertEquals("2123m", AnalyzeParser.analyzeSingalWithRuler(testSuit[1], ruleToken));
        assertEquals("无效", AnalyzeParser.analyzeSingalWithRuler(testSuit[2], ruleToken));
        assertEquals("12312m", AnalyzeParser.analyzeSingalWithRuler(testSuit[3], ruleToken));
        assertEquals("无效", AnalyzeParser.analyzeSingalWithRuler(testSuit[4], ruleToken));
    }

    @Test
    public void testLianGuaZhuangTai() {
        String ruleToken = "[{<0,0>=无效},{<1,0>=有效},{<0,1>=有效},{<1,1>=无效}]";
        String[][] testSuit = new String[][]{
                {"公里标", "0", "0"},
                {"公里标", "1", "0"},
                {"公里标", "0", "1"},
                {"公里标", "1", "1"}
        };
        assertEquals("无效", AnalyzeParser.analyzeSingalWithRuler(testSuit[0], ruleToken));
        assertEquals("有效", AnalyzeParser.analyzeSingalWithRuler(testSuit[1], ruleToken));
        assertEquals("有效", AnalyzeParser.analyzeSingalWithRuler(testSuit[2], ruleToken));
        assertEquals("无效", AnalyzeParser.analyzeSingalWithRuler(testSuit[3], ruleToken));
    }

    @Test
    public void testChongLianBianHao() {
        String ruleToken = "[{<0,0,*>=无},{<1,0,*>=1车与+x3+重联},{<0,1,*>=8车与+x3+重联},{<1,1,*>=异常}]";
        String[][] testSuit = new String[][]{
                {"公里标", "0", "0", "7"},
                {"公里标", "1", "0", "8"},
                {"公里标", "0", "1", "9"},
                {"公里标", "1", "1", "6"}
        };
        assertEquals("无", AnalyzeParser.analyzeSingalWithRuler(testSuit[0], ruleToken));
        assertEquals("1车与8重联", AnalyzeParser.analyzeSingalWithRuler(testSuit[1], ruleToken));
        assertEquals("8车与9重联", AnalyzeParser.analyzeSingalWithRuler(testSuit[2], ruleToken));
        assertEquals("异常", AnalyzeParser.analyzeSingalWithRuler(testSuit[3], ruleToken));
    }

    @Test
    public void testYunXingMoShi() {
        String ruleToken = "//[{<1,*,*,*,*,*,*,*,*>=制动级位 EB位},{<0,1,*,*,*,*,*,*,*>=制动级位 1级},{<0,2,*,*,*,*,*,*,*>=制动级位 2级},\n" +
                " {<0,3,*,*,*,*,*,*,*>=制动级位 3级},{<0,4,*,*,*,*,*,*,*>=制动级位 4级},{<0,5,*,*,*,*,*,*,*>=制动级位 5级},\n" +
                " {<0,6,*,*,*,*,*,*,*>=制动级位 6级},{<0,7,*,*,*,*,*,*,*>=制动级位 7级},{<0,0,0,1,0,*,*,*,*>=速度模式 +x6+km/h},\n" +
                " {<0,0,0,0,1,*,*,*,*>=速度模式 +x7+km/h},{<0,0,1,1,0,*,*,*,*>=级位模式 +x8+级},\n" +
                " {<0,0,1,0,1,*,*,*,*>=级位模式 +x8+级},{<0,0,0,0,0,*,*,*,*>=速度模式 +从控车}\n" +
                " ,{<0,0,1,0,0,*,*,*,*>=级位模式 +从控车}]";
        String[][] testSuit = new String[][]{
                {"公里标", "1", "0", "1", "0", "0", "0", "0", "0", "0"},
                {"公里标", "0", "1", "2", "0", "0", "0", "0", "0", "0"},
                {"公里标", "0", "2", "1", "0", "0", "0", "0", "0", "0"},
                {"公里标", "0", "3", "1", "0", "0", "0", "0", "0", "0"},
                {"公里标", "0", "4", "2", "0", "0", "0", "0", "0", "0"},
                {"公里标", "0", "5", "1", "0", "0", "0", "0", "0", "0"},
                {"公里标", "0", "6", "2", "0", "0", "0", "0", "0", "0"},
                {"公里标", "0", "7", "1", "0", "0", "0", "0", "0", "0"},
                {"公里标", "0", "0", "0", "1", "0", "9", "0", "0", "0"},
                {"公里标", "0", "0", "0", "0", "1", "1", "10", "0", "0"},
                {"公里标", "0", "0", "1", "1", "0", "2", "0", "11", "0"},
                {"公里标", "0", "0", "1", "0", "1", "2", "0", "12", "0"},
                {"公里标", "0", "0", "0", "0", "0", "2", "0", "0", "0"},
                {"公里标", "0", "0", "1", "0", "0", "2", "0", "0", "0"}
        };
        assertEquals("制动级位 EB位", AnalyzeParser.analyzeSingalWithRuler(testSuit[0], ruleToken));
        assertEquals("制动级位 1级", AnalyzeParser.analyzeSingalWithRuler(testSuit[1], ruleToken));
        assertEquals("制动级位 2级", AnalyzeParser.analyzeSingalWithRuler(testSuit[2], ruleToken));
        assertEquals("制动级位 3级", AnalyzeParser.analyzeSingalWithRuler(testSuit[3], ruleToken));
        assertEquals("制动级位 4级", AnalyzeParser.analyzeSingalWithRuler(testSuit[4], ruleToken));
        assertEquals("制动级位 5级", AnalyzeParser.analyzeSingalWithRuler(testSuit[5], ruleToken));
        assertEquals("制动级位 6级", AnalyzeParser.analyzeSingalWithRuler(testSuit[6], ruleToken));
        assertEquals("制动级位 7级", AnalyzeParser.analyzeSingalWithRuler(testSuit[7], ruleToken));
        assertEquals("速度模式 9km/h", AnalyzeParser.analyzeSingalWithRuler(testSuit[8], ruleToken));
        assertEquals("速度模式 10km/h", AnalyzeParser.analyzeSingalWithRuler(testSuit[9], ruleToken));
        assertEquals("级位模式 11级", AnalyzeParser.analyzeSingalWithRuler(testSuit[10], ruleToken));
        assertEquals("级位模式 12级", AnalyzeParser.analyzeSingalWithRuler(testSuit[11], ruleToken));
        assertEquals("速度模式 +从控车", AnalyzeParser.analyzeSingalWithRuler(testSuit[12], ruleToken));
        assertEquals("级位模式 +从控车", AnalyzeParser.analyzeSingalWithRuler(testSuit[13], ruleToken));
    }

    @Test
    public void testYunXingFangXiang() {
        String ruleToken = "[{<1>=前向},{<2>=后向},{<3>=零位}]";
        String[][] testSuit = new String[][]{
                {"公里标", "1"},
                {"公里标", "2"},
                {"公里标", "3"},
                {"公里标", "4"}
        };
        assertEquals("前向", AnalyzeParser.analyzeSingalWithRuler(testSuit[0], ruleToken));
        assertEquals("后向", AnalyzeParser.analyzeSingalWithRuler(testSuit[1], ruleToken));
        assertEquals("零位", AnalyzeParser.analyzeSingalWithRuler(testSuit[2], ruleToken));
        assertEquals("异常", AnalyzeParser.analyzeSingalWithRuler(testSuit[3], ruleToken));
    }
    @Test
    public void testATP() {
        String ruleToken = "[{<1,0,0,*>=无ATP制动},{<1,0,1,*>=ATP制动1级},{<1,0,4,*>=ATP制动4级},{<1,0,7,*>=ATP制动7级},{<1,0,8,*>=ATP制动UB},{<1,0,9,*>=隔离},\n" +
                " {<0,1,*,0>=无ATP制动},{<0,1,*,1>=ATP制动1级},{<0,1,*,4>=ATP制动4级},{<0,1,*,7>=ATP制动7级},{<0,1,*,8>=ATP制动UB},{<0,1,*,9>=隔离},\n" +
                " {<0,0,*,*>=从控车},{<*,*,*,*>=异常}]";
        String[][] testSuit = new String[][]{
                {"公里标", "1", "0", "0", "8"},
                {"公里标", "1", "0", "1", "9"},
                {"公里标", "1", "0", "4", "9"},
                {"公里标", "1", "0", "7", "9"},
                {"公里标", "1", "0", "8", "9"},
                {"公里标", "1", "0", "9", "9"},
                {"公里标", "0", "1", "6", "0"},
                {"公里标", "0", "1", "6", "1"},
                {"公里标", "0", "1", "6", "4"},
                {"公里标", "0", "1", "6", "7"},
                {"公里标", "0", "1", "6", "8"},
                {"公里标", "0", "1", "6", "9"},
                {"公里标", "0", "0", "6", "9"},
                {"公里标", "2", "1", "6", "9"}
        };
        assertEquals("无ATP制动", AnalyzeParser.analyzeSingalWithRuler(testSuit[0], ruleToken));
        assertEquals("ATP制动1级", AnalyzeParser.analyzeSingalWithRuler(testSuit[1], ruleToken));
        assertEquals("ATP制动4级", AnalyzeParser.analyzeSingalWithRuler(testSuit[2], ruleToken));
        assertEquals("ATP制动7级", AnalyzeParser.analyzeSingalWithRuler(testSuit[3], ruleToken));
        assertEquals("ATP制动UB", AnalyzeParser.analyzeSingalWithRuler(testSuit[4], ruleToken));
        assertEquals("隔离", AnalyzeParser.analyzeSingalWithRuler(testSuit[5], ruleToken));
        assertEquals("无ATP制动", AnalyzeParser.analyzeSingalWithRuler(testSuit[6], ruleToken));
        assertEquals("ATP制动1级", AnalyzeParser.analyzeSingalWithRuler(testSuit[7], ruleToken));
        assertEquals("ATP制动4级", AnalyzeParser.analyzeSingalWithRuler(testSuit[8], ruleToken));
        assertEquals("ATP制动7级", AnalyzeParser.analyzeSingalWithRuler(testSuit[9], ruleToken));
        assertEquals("ATP制动UB", AnalyzeParser.analyzeSingalWithRuler(testSuit[10], ruleToken));
        assertEquals("隔离", AnalyzeParser.analyzeSingalWithRuler(testSuit[11], ruleToken));
        assertEquals("从控车", AnalyzeParser.analyzeSingalWithRuler(testSuit[12], ruleToken));
        assertEquals("异常", AnalyzeParser.analyzeSingalWithRuler(testSuit[13], ruleToken));
    }

    @Test
    public void testWangLuoZhiDong() {
        String ruleToken = "[{<1>=施加},{<2>=缓解},{<*>=无效}]";
        String[][] testSuit = new String[][]{
                {"公里标", "1"},
                {"公里标", "2"},
                {"公里标", "3"}
        };
        assertEquals("施加", AnalyzeParser.analyzeSingalWithRuler(testSuit[0], ruleToken));
        assertEquals("缓解", AnalyzeParser.analyzeSingalWithRuler(testSuit[1], ruleToken));
        assertEquals("无效", AnalyzeParser.analyzeSingalWithRuler(testSuit[2], ruleToken));
    }

    @Test
    public void test() {
        String s = "xx+m";
        String regString = "\\+?x(\\d)\\+?";
        s = s.replaceAll(regString, "$1");
        System.out.println(s);
    }
}
