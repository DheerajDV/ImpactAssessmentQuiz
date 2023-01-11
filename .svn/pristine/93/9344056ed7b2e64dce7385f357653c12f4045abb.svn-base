package com.iocl.ImpactAssessmentQuiz.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Nationalized;

@Entity
@Table(name = "QUIIZ_RESULT_DETAILS")
public class TrnQuizResultDetails {

	@EmbeddedId
	private TrnQuizResultDetailsID trnQuizResultDetailsID;

//	@Column(name = "EVENT_ID")
//	private Long event_id;
//
//	@Column(name = "EVENT_NAME")
//	private String event_name;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EMPLOYEE_CODE", insertable = true, updatable = true, nullable = false)
	@Fetch(FetchMode.JOIN)
	private EmployeeModel employeeModel;

	@Nationalized
	@Column(name = "QUESTION_DESC")
	private String question_desc;

	@Column(name = "QUESTION_SEQ")
	private Long question_seq;

	@Column(name = "OPTION_A")
	private String option_a;

	@Column(name = "OPTION_B")
	private String option_b;

	@Column(name = "OPTION_C")
	private String option_c;

	@Column(name = "OPTION_D")
	private String option_d;

	@Column(name = "CORRECT_ANSWER")
	private String correct_answer;

	@Column(name = "EMP_ANSWER")
	private String emp_answer;

	/**
	 * @return the trnQuizResultDetailsID
	 */
	public TrnQuizResultDetailsID getTrnQuizResultDetailsID() {
		return trnQuizResultDetailsID;
	}

	/**
	 * @param trnQuizResultDetailsID the trnQuizResultDetailsID to set
	 */
	public void setTrnQuizResultDetailsID(TrnQuizResultDetailsID trnQuizResultDetailsID) {
		this.trnQuizResultDetailsID = trnQuizResultDetailsID;
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
	 * @return the question_desc
	 */
	public String getQuestion_desc() {
		return question_desc;
	}

	/**
	 * @param question_desc the question_desc to set
	 */
	public void setQuestion_desc(String question_desc) {
		this.question_desc = question_desc;
	}

	/**
	 * @return the question_seq
	 */
	public Long getQuestion_seq() {
		return question_seq;
	}

	/**
	 * @param question_seq the question_seq to set
	 */
	public void setQuestion_seq(Long question_seq) {
		this.question_seq = question_seq;
	}

	/**
	 * @return the option_a
	 */
	public String getOption_a() {
		return option_a;
	}

	/**
	 * @param option_a the option_a to set
	 */
	public void setOption_a(String option_a) {
		this.option_a = option_a;
	}

	/**
	 * @return the option_b
	 */
	public String getOption_b() {
		return option_b;
	}

	/**
	 * @param option_b the option_b to set
	 */
	public void setOption_b(String option_b) {
		this.option_b = option_b;
	}

	/**
	 * @return the option_c
	 */
	public String getOption_c() {
		return option_c;
	}

	/**
	 * @param option_c the option_c to set
	 */
	public void setOption_c(String option_c) {
		this.option_c = option_c;
	}

	/**
	 * @return the option_d
	 */
	public String getOption_d() {
		return option_d;
	}

	/**
	 * @param option_d the option_d to set
	 */
	public void setOption_d(String option_d) {
		this.option_d = option_d;
	}

	/**
	 * @return the correct_answer
	 */
	public String getCorrect_answer() {
		return correct_answer;
	}

	/**
	 * @param correct_answer the correct_answer to set
	 */
	public void setCorrect_answer(String correct_answer) {
		this.correct_answer = correct_answer;
	}

	/**
	 * @return the emp_answer
	 */
	public String getEmp_answer() {
		return emp_answer;
	}

	/**
	 * @param emp_answer the emp_answer to set
	 */
	public void setEmp_answer(String emp_answer) {
		this.emp_answer = emp_answer;
	}


}
