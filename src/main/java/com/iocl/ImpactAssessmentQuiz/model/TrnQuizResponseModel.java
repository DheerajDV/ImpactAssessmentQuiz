package com.iocl.ImpactAssessmentQuiz.model;

import java.time.LocalDateTime;

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
@Table(name = "TRN_QUIZ_RESPONSE")
public class TrnQuizResponseModel {

	@EmbeddedId
	private TrnQuizResponseID trnQuizResponseID;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EMPLOYEE_CODE", insertable = true, updatable = true, nullable = false)
	@Fetch(FetchMode.JOIN)
	private EmployeeModel employeeModel;

	@Nationalized
	@Column(name = "QUESTION_DESC")
	private String question_desc;

	@Column(name = "QUESTION_SEQ")
	private Long question_seq;

	@Nationalized
	@Column(name = "OPTION_DESC")
	private String option_desc;

	@Column(name = "CORRECT_ANS_FLG")
	private String correct_ans_flg;

	@Column(name = "EMP_ANSWER_FLG")
	private String emp_answer_flg;

	@Column(name = "EMP_RESPONSE_TIME")
	private LocalDateTime emp_response_time;

	@Column(name = "UPDATED_ON")
	private LocalDateTime updated_on;

	/**
	 * @return the trnQuizResponseID
	 */
	public TrnQuizResponseID getTrnQuizResponseID() {
		return trnQuizResponseID;
	}

	/**
	 * @param trnQuizResponseID the trnQuizResponseID to set
	 */
	public void setTrnQuizResponseID(TrnQuizResponseID trnQuizResponseID) {
		this.trnQuizResponseID = trnQuizResponseID;
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
	 * @return the option_desc
	 */
	public String getOption_desc() {
		return option_desc;
	}

	/**
	 * @param option_desc the option_desc to set
	 */
	public void setOption_desc(String option_desc) {
		this.option_desc = option_desc;
	}

	/**
	 * @return the correct_ans_flg
	 */
	public String getCorrect_ans_flg() {
		return correct_ans_flg;
	}

	/**
	 * @param correct_ans_flg the correct_ans_flg to set
	 */
	public void setCorrect_ans_flg(String correct_ans_flg) {
		this.correct_ans_flg = correct_ans_flg;
	}

	/**
	 * @return the emp_answer_flg
	 */
	public String getEmp_answer_flg() {
		return emp_answer_flg;
	}

	/**
	 * @param emp_answer_flg the emp_answer_flg to set
	 */
	public void setEmp_answer_flg(String emp_answer_flg) {
		this.emp_answer_flg = emp_answer_flg;
	}

	/**
	 * @return the emp_response_time
	 */
	public LocalDateTime getEmp_response_time() {
		return emp_response_time;
	}

	/**
	 * @param emp_response_time the emp_response_time to set
	 */
	public void setEmp_response_time(LocalDateTime emp_response_time) {
		this.emp_response_time = emp_response_time;
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
