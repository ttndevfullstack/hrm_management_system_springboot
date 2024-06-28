package com.bai3.hrm_management_system.model;

import java.util.Collections;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * @author Selim Horri
 */
@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "departments")
public final class Department {
	
	@Id
	@Column(name = "deptno", unique = true, nullable = false, precision = 10)
	private Integer deptno;
	
	@Column(name = "dname", length = 100)
	private String dname;
	
	@Column(name = "loc", length = 100)
	private String loc;
	
	@OneToMany(targetEntity = Employee.class, mappedBy = "department", fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<Employee> employees;
	
	@Override
	public String toString() {
		return "Department [deptno=" + deptno + ", dname=" + dname + ", loc=" + loc + "]";
	}
	
	public Integer getDeptno() {
		return deptno;
	}

	public void setDeptno(Integer deptno) {
		this.deptno = deptno;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	public Set<Employee> getEmployees() {
		return Collections.unmodifiableSet(employees);
	}

	public void setEmployees(final Set<Employee> employees) {
		this.employees = employees;
	}
}









