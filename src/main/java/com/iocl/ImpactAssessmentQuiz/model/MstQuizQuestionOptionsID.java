package com.iocl.ImpactAssessmentQuiz.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Embeddable
public class MstQuizQuestionOptionsID implements Serializable {

	private static final long serialVersionUID = 1L;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "QUESTION_ID", insertable = true, updatable = true, nullable = false)
	@Fetch(FetchMode.JOIN)
	private MstQuizQuestionBankModel mstQuizQuestionBankModel;

	@Column(name = "OPTION_SEQ")
	private String option_seq;

	/**
	 * @return the mstQuizQuestionBankModel
	 */
	public MstQuizQuestionBankModel getMstQuizQuestionBankModel() {
		return mstQuizQuestionBankModel;
	}

	/**
	 * @param mstQuizQuestionBankModel the mstQuizQuestionBankModel to set
	 */
	public void setMstQuizQuestionBankModel(MstQuizQuestionBankModel mstQuizQuestionBankModel) {
		this.mstQuizQuestionBankModel = mstQuizQuestionBankModel;
	}

	/**
	 * @return the option_seq
	 */
	public String getOption_seq() {
		return option_seq;
	}

	/**
	 * @param option_seq the option_seq to set
	 */
	public void setOption_seq(String option_seq) {
		this.option_seq = option_seq;
	}

	/**
	 * @param mstQuizQuestionBankModel
	 * @param option_seq
	 */
	public MstQuizQuestionOptionsID(MstQuizQuestionBankModel mstQuizQuestionBankModel, String option_seq) {
		super();
		this.mstQuizQuestionBankModel = mstQuizQuestionBankModel;
		this.option_seq = option_seq;
	}

	/**
	 * 
	 */
	public MstQuizQuestionOptionsID() {
		super();
		// TODO Auto-generated constructor stub
	}

}
