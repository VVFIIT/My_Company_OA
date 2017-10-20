package com.thinkgem.jeesite.modules.oa.entity;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Jiang on 2017/10/18.
 */
@Document
public class AttendanceDayStatus {

    //正常出勤
    private int normalDay;

    //出差-短期
    private int travelDayShort;

    //出差-长期
    private int travelDayLong;

    //加班
    private int overtimeDay;

    //请假
    private int leaveDay;

    //其它带薪假
    private int paidLeaveDay;

    //病假
    private int sickLeaveDay;

    //公休日
    private int holiday;

    //法定节假日
    private int statutoryHoliday;

    public int getNormalDay() {
        return normalDay;
    }

    public void setNormalDay(int normalDay) {
        this.normalDay = normalDay;
    }

    public int getTravelDayShort() {
        return travelDayShort;
    }

    public void setTravelDayShort(int travelDayShort) {
        this.travelDayShort = travelDayShort;
    }

    public int getTravelDayLong() {
        return travelDayLong;
    }

    public void setTravelDayLong(int travelDayLong) {
        this.travelDayLong = travelDayLong;
    }

    public int getOvertimeDay() {
        return overtimeDay;
    }

    public void setOvertimeDay(int overtimeDay) {
        this.overtimeDay = overtimeDay;
    }

    public int getLeaveDay() {
        return leaveDay;
    }

    public void setLeaveDay(int leaveDay) {
        this.leaveDay = leaveDay;
    }

    public int getPaidLeaveDay() {
        return paidLeaveDay;
    }

    public void setPaidLeaveDay(int paidLeaveDay) {
        this.paidLeaveDay = paidLeaveDay;
    }

    public int getSickLeaveDay() {
        return sickLeaveDay;
    }

    public void setSickLeaveDay(int sickLeaveDay) {
        this.sickLeaveDay = sickLeaveDay;
    }

    public int getHoliday() {
        return holiday;
    }

    public void setHoliday(int holiday) {
        this.holiday = holiday;
    }

    public int getStatutoryHoliday() {
        return statutoryHoliday;
    }

    public void setStatutoryHoliday(int statutoryHoliday) {
        this.statutoryHoliday = statutoryHoliday;
    }
}
