package com.iocl.ImpactAssessmentQuiz.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "MST_ADMIN")
public class MstAdminModel {

	@Id
	@Column(name = "EMP_CODE")
	private Long emp_code;

	@Column(name = "DIV_CODE")
	private String div_code;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EMP_CODE", insertable = false, updatable = false, nullable = false)
	private EmployeeModel employeeModel;

	@Column(name = "UPDATED_BY")
	private Long updated_by;

	@Column(name = "UPDATED_ON")
	private LocalDateTime updated_on;

	/**
	 * @return the employeeModel
	 */
	public EmployeeModel getEmployeeModel() {
		return employeeModel;
	}

	/**
	 * @param employeeModel the employeeModel to set
	 */
	public void setEmployeeModel(EmployeeModel employeeModel) {
		this.employeeModel = employeeModel;
	}

	/**
	 * @return the emp_code
	 */
	public Long getEmp_code() {
		return emp_code;
	}

	/**
	 * @param emp_code the emp_code to set
	 */
	public void setEmp_code(Long emp_code) {
		this.emp_code = emp_code;
	}

	/**
	 * @return the div_code
	 */
	public String getDiv_code() {
		return div_code;
	}

	/**
	 * @param div_code the div_code to set
	 */
	public void setDiv_code(String div_code) {
		this.div_code = div_code;
	}

	/**
	 * @return the updated_by
	 */
	public Long getUpdated_by() {
		return updated_by;
	}

	/**
	 * @param updated_by the updated_by to set
	 */
	public void setUpdated_by(Long updated_by) {
		this.updated_by = updated_by;
	}

	/**
	 * @return the updated_on
	 */
	public LocalDateTime getUpdated_on() {
		return updated_on;
	}

	/**
	 * @param updated_on the updated_on to set
	 */
	public void setUpdated_on(LocalDateTime updated_on) {
		this.updated_on = updated_on;
	}

}
