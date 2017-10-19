package com.thinkgem.jeesite.modules.oa.helper;

import com.thinkgem.jeesite.modules.oa.entity.AttendanceDay;

import java.util.List;

/**
 * Created by GQR on 2017/10/19.
 */
public class AttendanceHelper {

    String status_day_1;
    String status_day_2;
    String status_day_3;
    String status_day_4;
    String status_day_5;
    String status_day_6;
    String status_day_7;
    String status_day_8;
    String status_day_9;
    String status_day_10;
    String status_day_11;
    String status_day_12;
    String status_day_13;
    String status_day_14;
    String status_day_15;
    String status_day_16;
    String status_day_17;
    String status_day_18;
    String status_day_19;
    String status_day_20;
    String status_day_21;
    String status_day_22;
    String status_day_23;
    String status_day_24;
    String status_day_25;
    String status_day_26;
    String status_day_27;
    String status_day_28;
    String status_day_29;
    String status_day_30;
    String status_day_31;

    public AttendanceHelper(List<AttendanceDay> attendancedays) {
        this.updateAttendanceHelperStatus(attendancedays);
    }

    void updateAttendanceHelperStatus(List<AttendanceDay> attendancedays){
        this.status_day_1 = attendancedays.get(0).getStatus();
        this.status_day_2 = attendancedays.get(1).getStatus();
        this.status_day_3 = attendancedays.get(2).getStatus();
        this.status_day_4 = attendancedays.get(3).getStatus();
        this.status_day_5 = attendancedays.get(4).getStatus();
        this.status_day_6 = attendancedays.get(5).getStatus();
        this.status_day_7 = attendancedays.get(6).getStatus();
        this.status_day_8 = attendancedays.get(7).getStatus();
        this.status_day_9 = attendancedays.get(8).getStatus();
        this.status_day_10 = attendancedays.get(9).getStatus();
        this.status_day_11 = attendancedays.get(10).getStatus();
        this.status_day_12 = attendancedays.get(11).getStatus();
        this.status_day_13 = attendancedays.get(12).getStatus();
        this.status_day_14 = attendancedays.get(13).getStatus();
        this.status_day_15 = attendancedays.get(14).getStatus();
        this.status_day_16 = attendancedays.get(15).getStatus();
        this.status_day_17 = attendancedays.get(16).getStatus();
        this.status_day_18 = attendancedays.get(17).getStatus();
        this.status_day_19 = attendancedays.get(18).getStatus();
        this.status_day_20 = attendancedays.get(19).getStatus();
        this.status_day_21 = attendancedays.get(20).getStatus();
        this.status_day_22 = attendancedays.get(21).getStatus();
        this.status_day_23 = attendancedays.get(22).getStatus();
        this.status_day_24 = attendancedays.get(23).getStatus();
        this.status_day_25 = attendancedays.get(24).getStatus();
        this.status_day_26 = attendancedays.get(25).getStatus();
        this.status_day_27 = attendancedays.get(26).getStatus();
        this.status_day_28 = attendancedays.get(27).getStatus();
        this.status_day_29 = attendancedays.get(28).getStatus();
        this.status_day_30 = attendancedays.get(29).getStatus();
        this.status_day_31 = attendancedays.get(30).getStatus();
    }
}
