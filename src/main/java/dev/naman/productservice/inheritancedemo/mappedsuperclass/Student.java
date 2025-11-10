package dev.naman.productservice.inheritancedemo.mappedsuperclass;

import jakarta.persistence.Entity;

@Entity(name = "ms_student")
public class Student extends User{
	private double psp;
	private double attendance;

	public double getPsp() {
		return psp;
	}

	public void setPsp(double psp) {
		this.psp = psp;
	}

	public double getAttendance() {
		return attendance;
	}

	public void setAttendance(double attendance) {
		this.attendance = attendance;
	}
}