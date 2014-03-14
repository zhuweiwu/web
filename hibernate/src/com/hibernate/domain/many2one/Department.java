package com.hibernate.domain.many2one;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Department {
	private int id;
	private String name;
	private Set<Employee> emps;
	/*private List<Employee> emps;*/
	/*private Map<String, Employee> emps;*/
	
	public Set<Employee> getEmps() {
		return emps;
	}
	public void setEmps(Set<Employee> emps) {
		this.emps = emps;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
