package com.iocl.ImpactAssessmentQuiz.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Nationalized;

@Entity
@Table(name = "MST_QUIZ_QUESTION_OPTIONS")
public class MstQuizQuestionOptionsModel {

	@EmbeddedId
	private MstQuizQuestionOptionsID mstQuizQuestionOptionsID;

	@Nationalized
	@Column(name = "OPTION_DESC")
	private String option_desc;

	@Column(name = "ANSWER_FLG")
	private String answer_flg;

	@Column(name = "UPDATED_BY")
	private Long updated_by;

	@Column(name = "UPDATED_ON")
	private LocalDateTime updated_on;

	/**
	 * @return the mstQuizQuestionOptionsID
	 */
	public MstQuizQuestionOptionsID getMstQuizQuestionOptionsID() {
		return mstQuizQuestionOptionsID;
	}

	/**
	 * @param mstQuizQuestionOptionsID the mstQuizQuestionOptionsID to set
	 */
	public void setMstQuizQuestionOptionsID(MstQuizQuestionOptionsID mstQuizQuestionOptionsID) {
		this.mstQuizQuestionOptionsID = mstQuizQuestionOptionsID;
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
	 * @return the answer_flg
	 */
	public String getAnswer_flg() {
		return answer_flg;
	}

	/**
	 * @param answer_flg the answer_flg to set
	 */
	public void setAnswer_flg(String answer_flg) {
		this.answer_flg = answer_flg;
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
