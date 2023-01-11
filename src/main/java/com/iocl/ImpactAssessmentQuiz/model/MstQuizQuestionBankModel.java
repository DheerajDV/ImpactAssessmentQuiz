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
import org.hibernate.annotations.Nationalized;

@Entity
@Table(name = "MST_QUIZ_QUESTION_BANK")
public class MstQuizQuestionBankModel {

	@Id
	@Column(name = "QUESTION_ID")
	private Long question_id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACTIVITY_CODE", insertable = true, updatable = true, nullable = false)
	@Fetch(FetchMode.JOIN)
	private MstActivityListModel mstActivityListModel;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "QUESTION_TEMPLATE", insertable = true, updatable = true, nullable = false)
	@Fetch(FetchMode.JOIN)
	private MstTemplateModel mstTemplateModel;

	@Nationalized
	@Column(name = "QUESTION_DESC")
	private String question_desc;

	@Column(name = "DIFFICULTY_LEVEL")
	private String difficulty_level;

	@Column(name = "MANDATORY_FLG")
	private String mandatory_flg;

	@Column(name = "UPDATED_BY")
	private Long updated_by;

	@Column(name = "UPDATED_ON")
	private LocalDateTime updated_on;

	/**
	 * @return the question_id
	 */
	public Long getQuestion_id() {
		return question_id;
	}

	/**
	 * @param question_id the question_id to set
	 */
	public void setQuestion_id(Long question_id) {
		this.question_id = question_id;
	}

	/**
	 * @return the mstActivityListModel
	 */
	public MstActivityListModel getMstActivityListModel() {
		return mstActivityListModel;
	}

	/**
	 * @param mstActivityListModel the mstActivityListModel to set
	 */
	public void setMstActivityListModel(MstActivityListModel mstActivityListModel) {
		this.mstActivityListModel = mstActivityListModel;
	}

	/**
	 * @return the mstTemplateModel
	 */
	public MstTemplateModel getMstTemplateModel() {
		return mstTemplateModel;
	}

	/**
	 * @param mstTemplateModel the mstTemplateModel to set
	 */
	public void setMstTemplateModel(MstTemplateModel mstTemplateModel) {
		this.mstTemplateModel = mstTemplateModel;
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
	 * @return the difficulty_level
	 */
	public String getDifficulty_level() {
		return difficulty_level;
	}

	/**
	 * @param difficulty_level the difficulty_level to set
	 */
	public void setDifficulty_level(String difficulty_level) {
		this.difficulty_level = difficulty_level;
	}

	/**
	 * @return the mandatory_flg
	 */
	public String getMandatory_flg() {
		return mandatory_flg;
	}

	/**
	 * @param mandatory_flg the mandatory_flg to set
	 */
	public void setMandatory_flg(String mandatory_flg) {
		this.mandatory_flg = mandatory_flg;
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
