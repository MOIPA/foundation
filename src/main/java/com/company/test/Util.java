package com.company.test;

import java.util.Calendar;
import java.util.Date;

public class Util {
    /**
     *
     * @param currentTime   当前时间
     * @param startTime	开始时间
     * @param endTime   结束时间
     * @author tangrui   判断当前时间在时间区间内
     */
    public static boolean isDateInInterval(Date currentTime, Date startTime, Date endTime) {
        if (currentTime.getTime() == startTime.getTime()
                || currentTime.getTime() == endTime.getTime()) {
            return true;
        }

        Calendar date = Calendar.getInstance();
        date.setTime(currentTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if ((date.after(begin) && date.before(end))||(date.before(begin)&&date.before(end))) {
            return true;
        } else {
            return false;
        }
    }
}
