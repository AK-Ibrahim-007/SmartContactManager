package com.contactmanager.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Distric {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String districName;
	private int areaCode;
	@ManyToOne 
	private Country country;
	@ManyToOne 
	private State state;
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "distric")
	private List<Worker> worker=new ArrayList<>();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDistricName() {
		return districName;
	}
	public void setDistricName(String districName) {
		this.districName = districName;
	}
	public int getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(int areaCode) {
		this.areaCode = areaCode;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	public List<Worker> getWorker() {
		return worker;
	}
	public void setWorker(List<Worker> worker) {
		this.worker = worker;
	}
	@Override
	public String toString() {
		return "Distric [id=" + id + ", districName=" + districName + ", areaCode=" + areaCode + ", country=" + country
				+ ", state=" + state + ", worker=" + worker + ", getId()=" + getId() + ", getDistricName()="
				+ getDistricName() + ", getAreaCode()=" + getAreaCode() + ", getCountry()=" + getCountry()
				+ ", getState()=" + getState() + ", getWorker()=" + getWorker() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
}
