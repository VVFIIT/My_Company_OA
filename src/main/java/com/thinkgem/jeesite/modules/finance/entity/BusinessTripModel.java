package com.thinkgem.jeesite.modules.finance.entity;

import java.util.List;

public class BusinessTripModel {
	
	private BusinessTripApplication businessTripApplication;
	private List<BusinessTripReservation> businessTripReservationList;
	private List<BusinessTripAirTicket> BusinessTripAirTicketList;
	public BusinessTripApplication getBusinessTripApplication() {
		return businessTripApplication;
	}
	public void setBusinessTripApplication(BusinessTripApplication businessTripApplication) {
		this.businessTripApplication = businessTripApplication;
	}
	public List<BusinessTripReservation> getBusinessTripReservationList() {
		return businessTripReservationList;
	}
	public void setBusinessTripReservationList(List<BusinessTripReservation> businessTripReservationList) {
		this.businessTripReservationList = businessTripReservationList;
	}
	public List<BusinessTripAirTicket> getBusinessTripAirTicketList() {
		return BusinessTripAirTicketList;
	}
	public void setBusinessTripAirTicketList(List<BusinessTripAirTicket> businessTripAirTicketList) {
		BusinessTripAirTicketList = businessTripAirTicketList;
	}
	
	
}
