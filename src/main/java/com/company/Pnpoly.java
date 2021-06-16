package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pnpoly {
    public static void main(String[] args) {
        System.out.println(new Solution().pnpoly(new Point(0,0)));
    }

    private static class Solution {
        boolean pnpoly(Point posi) {
            List<Point> points = Arrays.asList(
                    new Point(114.093971, 22.503322),
                    new Point(114.309995, 22.580363),
                    new Point(114.438633, 22.620668),
                    new Point(114.678947, 22.778572),
                    new Point(115.443872, 22.777506),
                    new Point(115.766975, 22.870775),
                    new Point(116.51494, 23.024129),
                    new Point(116.763304, 23.301632),
                    new Point(116.92543, 23.560532),
                    new Point(117.568185, 23.934176),
                    new Point(118.090209, 24.322536),
                    new Point(118.202892, 24.511007),
                    new Point(118.41906, 24.634043),
                    new Point(118.731239, 24.808397),
                    new Point(118.986502, 24.996128),
                    new Point(118.866919, 25.090408),
                    new Point(118.976153, 25.188801),
                    new Point(119.222217, 25.23326),
                    new Point(119.222217, 25.35557),
                    new Point(119.157827, 25.441216),
                    new Point(119.328002, 25.563313),
                    new Point(119.483229, 25.504368),
                    new Point(119.762638, 25.427642),
                    new Point(119.812081, 25.546102),
                    new Point(119.836227, 25.566964),
                    new Point(119.593038, 25.824302),
                    new Point(119.660878, 26.289871),
                    new Point(119.64708, 26.583871),
                    new Point(119.624083, 26.738846),
                    new Point(120.05182, 26.885359),
                    new Point(120.176002, 26.94927),
                    new Point(120.297884, 27.116089),
                    new Point(120.813008, 27.864675),
                    new Point(121.587995, 28.345935),
                    new Point(121.919146, 29.599752),
                    new Point(121.735173, 31.075855),
                    new Point(121.762769, 31.966302),
                    new Point(119.619484, 34.577739),
                    new Point(119.251538, 34.714583),
                    new Point(119.527497, 35.455609),
                    new Point(120.428965, 36.085346),
                    new Point(122.43427, 37.329642),
                    new Point(120.907295, 37.769091),
                    new Point(119.343524, 37.079462),
                    new Point(118.570838, 38.031504),
                    new Point(117.540589, 38.712237),
                    new Point(117.908535, 39.229272),
                    new Point(121.716776, 40.855317),
                    new Point(122.452668, 40.420843),
                    new Point(121.128062, 38.841852),
                    new Point(121.679981, 38.899383),
                    new Point(124.310795, 39.983523),
                    new Point(137.078519, 48.418561),
                    new Point(123.206957, 54.024225),
                    new Point(119.969032, 52.793091),
                    new Point(119.969032, 52.793091),
                    new Point(91.085276, 45.626415),
                    new Point(86.780309, 49.412824),
                    new Point(73.718228, 39.88438),
                    new Point(89.024779, 27.569957),
                    new Point(98.333811, 27.701042),
                    new Point(99.36406, 22.442005),
                    new Point(106.207854, 22.85186),
                    new Point(109.666546, 21.446486),
                    new Point(109.905711, 20.322603),
                    new Point(110.282856, 20.261847),
                    new Point(110.604808, 21.06705),
                    new Point(111.322303, 21.506759),
                    new Point(113.267817, 21.949418),
                    new Point(113.693255, 22.624672),
                    new Point(113.991061, 22.458039));
            boolean isIn = false;
            for (int i = 0; i < points.size(); i++) {
                int j = i + 1;
                // 起始端点和终结端点
                if (i == points.size() - 1) j = 0;
                Point pointA = points.get(i);
                Point pointB = points.get(j);
                // 是否在端点
                if ((pointA.x == posi.x && pointA.y == posi.y) || (pointB.x == posi.x && pointB.y == posi.y))
                    return true;
                //当y的大小介于y1和y2之间时，根据“相似三角形原理”，如果相交点为（xP,yP），那么(y1-yP)/(y1-y2)=(x1-xP)/(x1-x2)，
                // 即xP=x1-(y1-yP)*(x1-x2)/(y1-y2)，这样一来，当x的值小于等于xP时，点就在这条边的左侧，那么从这个点发射的x轴正方向的射线必定与这条边相交。
                if ((posi.y < pointA.y && posi.y >= pointB.y) || (posi.y < pointB.y && posi.y >= pointA.y)) {
                    // 是否在线上
                    if ((pointA.y - pointB.y) / (pointA.x - pointB.x) == (posi.y - pointB.y) / (posi.x - pointB.x))
                        return true;
                    else {
                        double shouldX = pointA.x - (pointA.y - posi.y) * (pointA.x - pointB.x) / (pointA.y - pointB.y);
                        if (posi.x < shouldX) isIn = !isIn;
                    }
                }
            }
            return isIn;
        }


    }

    private static class Point {
        public double x;
        public double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }
}
