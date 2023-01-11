package com.iocl.ImpactAssessmentQuiz.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "TRN_QUIZ_RESET_LOG")
public class TrnQuizResetLogModel {

	@Id
	@Column(name = "QUIZ_ID")
	private Long quiz_id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EMPLOYEE_CODE", insertable = true, updatable = true, nullable = false)
	@Fetch(FetchMode.JOIN)
	private EmployeeModel employeeModel;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RESET_BY", insertable = true, updatable = true, nullable = false)
	@Fetch(FetchMode.JOIN)
	private EmployeeModel reset_by;

	@Column(name = "RESET_ON")
	private LocalDateTime reset_on;

	@Column(name = "OLD_QUIZ_ID")
	private Long old_quiz_id;

	/**
	 * @return the quiz_id
	 */
	public Long getQuiz_id() {
		return quiz_id;
	}

	/**
	 * @param quiz_id the quiz_id to set
	 */
	public void setQuiz_id(Long quiz_id) {
		this.quiz_id = quiz_id;
	}

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
	 * @return the reset_by
	 */
	public EmployeeModel getReset_by() {
		return reset_by;
	}

	/**
	 * @param reset_by the reset_by to set
	 */
	public void setReset_by(EmployeeModel reset_by) {
		this.reset_by = reset_by;
	}

	/**
	 * @return the reset_on
	 */
	public LocalDateTime getReset_on() {
		return reset_on;
	}

	/**
	 * @param reset_on the reset_on to set
	 */
	public void setReset_on(LocalDateTime reset_on) {
		this.reset_on = reset_on;
	}

	/**
	 * @return the old_quiz_id
	 */
	public Long getOld_quiz_id() {
		return old_quiz_id;
	}

	/**
	 * @param old_quiz_id the old_quiz_id to set
	 */
	public void setOld_quiz_id(Long old_quiz_id) {
		this.old_quiz_id = old_quiz_id;
	}

}
