package sushine_group.hrm_management_system.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Selim Horri
 */
@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employees")
public final class Employee {
	@Id
	@Column(name = "empno", unique = true, nullable = false, precision = 10)
	private Integer empno;
	
	@Column(name = "ename", length = 100)
	private String ename;
	
	@Column(name = "job", length = 100)
	private String job;
	
	@Column(name = "mgr", precision = 10)
	private Integer mgr;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	@JsonFormat(shape = Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	@Column(name = "hiredate")
	private LocalDateTime hireDate;
	
	@Column(name = "sal", precision = 7, scale = 2)
	private BigDecimal sal;
	
	@Column(name = "comm", precision = 7, scale = 2)
	private BigDecimal  comm;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "deptno", referencedColumnName = "deptno", nullable = true)
	private Department department;
	
	@Override
	public String toString() {
		return "Employee [empno=" + empno + ", ename=" + ename + ", job=" + job + ", mgr=" + mgr + ", hireDate="
				+ hireDate + ", sal=" + sal + ", comm=" + comm + ", deptno=" + department.getDeptno()
				+ "]";
	}
	
	public Integer getEmpno() {
		return empno;
	}

	public void setEmpno(Integer empno) {
		this.empno = empno;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public Integer getMgr() {
		return mgr;
	}

	public void setMgr(Integer mgr) {
		this.mgr = mgr;
	}

	public LocalDateTime getHireDate() {
		return hireDate;
	}

	public void setHireDate(LocalDateTime hireDate) {
		this.hireDate = hireDate;
	}

	public BigDecimal  getSal() {
		return sal;
	}

	public void setSal(BigDecimal  sal) {
		this.sal = sal;
	}

	public BigDecimal  getComm() {
		return comm;
	}

	public void setComm(BigDecimal  comm) {
		this.comm = comm;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(final Department department) {
		this.department = department;
	}
}











