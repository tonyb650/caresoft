package com.caresoft.clinicapp;
import java.util.ArrayList;
import java.util.Date;

public class AdminUser extends User implements HIPAACompliantUser, HIPAACompliantAdmin {
	private Integer employeeID;
    private String role;
    private ArrayList<String> securityIncidents;
    
    // TO DO: Implement a constructor that takes an ID and a role
    public AdminUser(int id, String role) {
    	this.id = id; // could use: super(id); ??
    	this.role = role;
    	this.securityIncidents = new ArrayList<String>();
    }
    // TO DO: Implement HIPAACompliantUser!
	@Override
    public boolean assignPin(int pin) {
    	if(pin>99999) {
    		this.pin = pin;
    		return true;
    	}
    	return false;
    }
	@Override
    public boolean accessAuthorized(Integer confirmedAuthID) {
    	if(!confirmedAuthID.equals(this.id)) {
    		authIncident();
    		return false;
    	}
    	return true;
    }
    // TO DO: Implement HIPAACompliantAdmin!
	@Override
    public ArrayList<String> reportSecurityIncidents(){
    	return this.securityIncidents;
    }
    
    public void newIncident(String notes) {
        String report = String.format(
            "Datetime Submitted: %s \n,  Reported By ID: %s\n Notes: %s \n", 
            new Date(), this.id, notes
        );
        securityIncidents.add(report);
    }
    public void authIncident() {
        String report = String.format(
            "Datetime Submitted: %s \n,  ID: %s\n Notes: %s \n", 
            new Date(), this.id, "AUTHORIZATION ATTEMPT FAILED FOR THIS USER"
        );
        securityIncidents.add(report);
    }
    
    // TO DO: Setters & Getters
    public void setEmployeeID(Integer id) {
    	this.employeeID=id;
    }
    
    public Integer getEmployeeID() {
    	return this.employeeID;
    }
    
    public void setRole(String role) {
    	this.role = role;
    }
    public String getRole() {
    	return this.role;
    }
    public ArrayList<String> getSecurityIncidents() {
    	return this.securityIncidents;
    }
    public void setSecurityIncidents(ArrayList<String> securityIncidents) {
    	this.securityIncidents = securityIncidents;
    }
}
