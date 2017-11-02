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
    
    String location_day_1;
    String location_day_2;
    String location_day_3;
    String location_day_4;
    String location_day_5;
    String location_day_6;
    String location_day_7;
    String location_day_8;
    String location_day_9;
    String location_day_10;
    String location_day_11;
    String location_day_12;
    String location_day_13;
    String location_day_14;
    String location_day_15;
    String location_day_16;
    String location_day_17;
    String location_day_18;
    String location_day_19;
    String location_day_20;
    String location_day_21;
    String location_day_22;
    String location_day_23;
    String location_day_24;
    String location_day_25;
    String location_day_26;
    String location_day_27;
    String location_day_28;
    String location_day_29;
    String location_day_30;
    String location_day_31;
    
    public AttendanceHelper() {
    	
    }

    public void updateAttendanceHelperStatus(List<AttendanceDay> attendancedays){
    	int size = attendancedays.size();
    	if(size==0) {
        	return;
        }
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
        this.location_day_1 = attendancedays.get(0).getLocation();
        this.location_day_2 = attendancedays.get(1).getLocation();
        this.location_day_3 = attendancedays.get(2).getLocation();
        this.location_day_4 = attendancedays.get(3).getLocation();
        this.location_day_5 = attendancedays.get(4).getLocation();
        this.location_day_6 = attendancedays.get(5).getLocation();
        this.location_day_7 = attendancedays.get(6).getLocation();
        this.location_day_8 = attendancedays.get(7).getLocation();
        this.location_day_9 = attendancedays.get(8).getLocation();
        this.location_day_10 = attendancedays.get(9).getLocation();
        this.location_day_11 = attendancedays.get(10).getLocation();
        this.location_day_12 = attendancedays.get(11).getLocation();
        this.location_day_13 = attendancedays.get(12).getLocation();
        this.location_day_14 = attendancedays.get(13).getLocation();
        this.location_day_15 = attendancedays.get(14).getLocation();
        this.location_day_16 = attendancedays.get(15).getLocation();
        this.location_day_17 = attendancedays.get(16).getLocation();
        this.location_day_18 = attendancedays.get(17).getLocation();
        this.location_day_19 = attendancedays.get(18).getLocation();
        this.location_day_20 = attendancedays.get(19).getLocation();
        this.location_day_21 = attendancedays.get(20).getLocation();
        this.location_day_22 = attendancedays.get(21).getLocation();
        this.location_day_23 = attendancedays.get(22).getLocation();
        this.location_day_24 = attendancedays.get(23).getLocation();
        this.location_day_25 = attendancedays.get(24).getLocation();
        this.location_day_26 = attendancedays.get(25).getLocation();
        this.location_day_27 = attendancedays.get(26).getLocation();
        if(size>=28) {
        	this.status_day_28 = attendancedays.get(27).getStatus();
        	this.location_day_28 = attendancedays.get(27).getLocation();
        }
        if(size>=29){
        	this.status_day_29 = attendancedays.get(28).getStatus();
        	this.location_day_29 = attendancedays.get(28).getLocation();
        }
        if(size>=30){
        	this.status_day_30 = attendancedays.get(29).getStatus();
        	this.location_day_30 = attendancedays.get(29).getLocation();
        }
        if(size>=31){
        	this.status_day_31 = attendancedays.get(30).getStatus();
        	this.location_day_31 = attendancedays.get(30).getLocation();
        }
    }

	public String getStatus_day_1() {
		return status_day_1;
	}

	public void setStatus_day_1(String status_day_1) {
		this.status_day_1 = status_day_1;
	}

	public String getStatus_day_2() {
		return status_day_2;
	}

	public void setStatus_day_2(String status_day_2) {
		this.status_day_2 = status_day_2;
	}

	public String getStatus_day_3() {
		return status_day_3;
	}

	public void setStatus_day_3(String status_day_3) {
		this.status_day_3 = status_day_3;
	}

	public String getStatus_day_4() {
		return status_day_4;
	}

	public void setStatus_day_4(String status_day_4) {
		this.status_day_4 = status_day_4;
	}

	public String getStatus_day_5() {
		return status_day_5;
	}

	public void setStatus_day_5(String status_day_5) {
		this.status_day_5 = status_day_5;
	}

	public String getStatus_day_6() {
		return status_day_6;
	}

	public void setStatus_day_6(String status_day_6) {
		this.status_day_6 = status_day_6;
	}

	public String getStatus_day_7() {
		return status_day_7;
	}

	public void setStatus_day_7(String status_day_7) {
		this.status_day_7 = status_day_7;
	}

	public String getStatus_day_8() {
		return status_day_8;
	}

	public void setStatus_day_8(String status_day_8) {
		this.status_day_8 = status_day_8;
	}

	public String getStatus_day_9() {
		return status_day_9;
	}

	public void setStatus_day_9(String status_day_9) {
		this.status_day_9 = status_day_9;
	}

	public String getStatus_day_10() {
		return status_day_10;
	}

	public void setStatus_day_10(String status_day_10) {
		this.status_day_10 = status_day_10;
	}

	public String getStatus_day_11() {
		return status_day_11;
	}

	public void setStatus_day_11(String status_day_11) {
		this.status_day_11 = status_day_11;
	}

	public String getStatus_day_12() {
		return status_day_12;
	}

	public void setStatus_day_12(String status_day_12) {
		this.status_day_12 = status_day_12;
	}

	public String getStatus_day_13() {
		return status_day_13;
	}

	public void setStatus_day_13(String status_day_13) {
		this.status_day_13 = status_day_13;
	}

	public String getStatus_day_14() {
		return status_day_14;
	}

	public void setStatus_day_14(String status_day_14) {
		this.status_day_14 = status_day_14;
	}

	public String getStatus_day_15() {
		return status_day_15;
	}

	public void setStatus_day_15(String status_day_15) {
		this.status_day_15 = status_day_15;
	}

	public String getStatus_day_16() {
		return status_day_16;
	}

	public void setStatus_day_16(String status_day_16) {
		this.status_day_16 = status_day_16;
	}

	public String getStatus_day_17() {
		return status_day_17;
	}

	public void setStatus_day_17(String status_day_17) {
		this.status_day_17 = status_day_17;
	}

	public String getStatus_day_18() {
		return status_day_18;
	}

	public void setStatus_day_18(String status_day_18) {
		this.status_day_18 = status_day_18;
	}

	public String getStatus_day_19() {
		return status_day_19;
	}

	public void setStatus_day_19(String status_day_19) {
		this.status_day_19 = status_day_19;
	}

	public String getStatus_day_20() {
		return status_day_20;
	}

	public void setStatus_day_20(String status_day_20) {
		this.status_day_20 = status_day_20;
	}

	public String getStatus_day_21() {
		return status_day_21;
	}

	public void setStatus_day_21(String status_day_21) {
		this.status_day_21 = status_day_21;
	}

	public String getStatus_day_22() {
		return status_day_22;
	}

	public void setStatus_day_22(String status_day_22) {
		this.status_day_22 = status_day_22;
	}

	public String getStatus_day_23() {
		return status_day_23;
	}

	public void setStatus_day_23(String status_day_23) {
		this.status_day_23 = status_day_23;
	}

	public String getStatus_day_24() {
		return status_day_24;
	}

	public void setStatus_day_24(String status_day_24) {
		this.status_day_24 = status_day_24;
	}

	public String getStatus_day_25() {
		return status_day_25;
	}

	public void setStatus_day_25(String status_day_25) {
		this.status_day_25 = status_day_25;
	}

	public String getStatus_day_26() {
		return status_day_26;
	}

	public void setStatus_day_26(String status_day_26) {
		this.status_day_26 = status_day_26;
	}

	public String getStatus_day_27() {
		return status_day_27;
	}

	public void setStatus_day_27(String status_day_27) {
		this.status_day_27 = status_day_27;
	}

	public String getStatus_day_28() {
		return status_day_28;
	}

	public void setStatus_day_28(String status_day_28) {
		this.status_day_28 = status_day_28;
	}

	public String getStatus_day_29() {
		return status_day_29;
	}

	public void setStatus_day_29(String status_day_29) {
		this.status_day_29 = status_day_29;
	}

	public String getStatus_day_30() {
		return status_day_30;
	}

	public void setStatus_day_30(String status_day_30) {
		this.status_day_30 = status_day_30;
	}

	public String getStatus_day_31() {
		return status_day_31;
	}

	public void setStatus_day_31(String status_day_31) {
		this.status_day_31 = status_day_31;
	}

	public String getLocation_day_1() {
		return location_day_1;
	}

	public void setLocation_day_1(String location_day_1) {
		this.location_day_1 = location_day_1;
	}

	public String getLocation_day_2() {
		return location_day_2;
	}

	public void setLocation_day_2(String location_day_2) {
		this.location_day_2 = location_day_2;
	}

	public String getLocation_day_3() {
		return location_day_3;
	}

	public void setLocation_day_3(String location_day_3) {
		this.location_day_3 = location_day_3;
	}

	public String getLocation_day_4() {
		return location_day_4;
	}

	public void setLocation_day_4(String location_day_4) {
		this.location_day_4 = location_day_4;
	}

	public String getLocation_day_5() {
		return location_day_5;
	}

	public void setLocation_day_5(String location_day_5) {
		this.location_day_5 = location_day_5;
	}

	public String getLocation_day_6() {
		return location_day_6;
	}

	public void setLocation_day_6(String location_day_6) {
		this.location_day_6 = location_day_6;
	}

	public String getLocation_day_7() {
		return location_day_7;
	}

	public void setLocation_day_7(String location_day_7) {
		this.location_day_7 = location_day_7;
	}

	public String getLocation_day_8() {
		return location_day_8;
	}

	public void setLocation_day_8(String location_day_8) {
		this.location_day_8 = location_day_8;
	}

	public String getLocation_day_9() {
		return location_day_9;
	}

	public void setLocation_day_9(String location_day_9) {
		this.location_day_9 = location_day_9;
	}

	public String getLocation_day_10() {
		return location_day_10;
	}

	public void setLocation_day_10(String location_day_10) {
		this.location_day_10 = location_day_10;
	}

	public String getLocation_day_11() {
		return location_day_11;
	}

	public void setLocation_day_11(String location_day_11) {
		this.location_day_11 = location_day_11;
	}

	public String getLocation_day_12() {
		return location_day_12;
	}

	public void setLocation_day_12(String location_day_12) {
		this.location_day_12 = location_day_12;
	}

	public String getLocation_day_13() {
		return location_day_13;
	}

	public void setLocation_day_13(String location_day_13) {
		this.location_day_13 = location_day_13;
	}

	public String getLocation_day_14() {
		return location_day_14;
	}

	public void setLocation_day_14(String location_day_14) {
		this.location_day_14 = location_day_14;
	}

	public String getLocation_day_15() {
		return location_day_15;
	}

	public void setLocation_day_15(String location_day_15) {
		this.location_day_15 = location_day_15;
	}

	public String getLocation_day_16() {
		return location_day_16;
	}

	public void setLocation_day_16(String location_day_16) {
		this.location_day_16 = location_day_16;
	}

	public String getLocation_day_17() {
		return location_day_17;
	}

	public void setLocation_day_17(String location_day_17) {
		this.location_day_17 = location_day_17;
	}

	public String getLocation_day_18() {
		return location_day_18;
	}

	public void setLocation_day_18(String location_day_18) {
		this.location_day_18 = location_day_18;
	}

	public String getLocation_day_19() {
		return location_day_19;
	}

	public void setLocation_day_19(String location_day_19) {
		this.location_day_19 = location_day_19;
	}

	public String getLocation_day_20() {
		return location_day_20;
	}

	public void setLocation_day_20(String location_day_20) {
		this.location_day_20 = location_day_20;
	}

	public String getLocation_day_21() {
		return location_day_21;
	}

	public void setLocation_day_21(String location_day_21) {
		this.location_day_21 = location_day_21;
	}

	public String getLocation_day_22() {
		return location_day_22;
	}

	public void setLocation_day_22(String location_day_22) {
		this.location_day_22 = location_day_22;
	}

	public String getLocation_day_23() {
		return location_day_23;
	}

	public void setLocation_day_23(String location_day_23) {
		this.location_day_23 = location_day_23;
	}

	public String getLocation_day_24() {
		return location_day_24;
	}

	public void setLocation_day_24(String location_day_24) {
		this.location_day_24 = location_day_24;
	}

	public String getLocation_day_25() {
		return location_day_25;
	}

	public void setLocation_day_25(String location_day_25) {
		this.location_day_25 = location_day_25;
	}

	public String getLocation_day_26() {
		return location_day_26;
	}

	public void setLocation_day_26(String location_day_26) {
		this.location_day_26 = location_day_26;
	}

	public String getLocation_day_27() {
		return location_day_27;
	}

	public void setLocation_day_27(String location_day_27) {
		this.location_day_27 = location_day_27;
	}

	public String getLocation_day_28() {
		return location_day_28;
	}

	public void setLocation_day_28(String location_day_28) {
		this.location_day_28 = location_day_28;
	}

	public String getLocation_day_29() {
		return location_day_29;
	}

	public void setLocation_day_29(String location_day_29) {
		this.location_day_29 = location_day_29;
	}

	public String getLocation_day_30() {
		return location_day_30;
	}

	public void setLocation_day_30(String location_day_30) {
		this.location_day_30 = location_day_30;
	}

	public String getLocation_day_31() {
		return location_day_31;
	}

	public void setLocation_day_31(String location_day_31) {
		this.location_day_31 = location_day_31;
	}
}
