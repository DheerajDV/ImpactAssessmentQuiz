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
public class TrnQuizResponseID implements Serializable {

	public void setMstQuizQuestionBankModel(MstQuizQuestionBankModel mstQuizQuestionBankModel) {
		this.mstQuizQuestionBankModel = mstQuizQuestionBankModel;
	}

	private static final long serialVersionUID = 1L;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "QUIZ_ID", insertable = true, updatable = true, nullable = false)
	@Fetch(FetchMode.JOIN)
	private TrnQuizHeaderModel trnQuizHeaderModel;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "QUESTION_ID", insertable = true, updatable = true, nullable = false)
	@Fetch(FetchMode.JOIN)
	private MstQuizQuestionBankModel mstQuizQuestionBankModel;

	@Column(name = "OPTION_SEQ")
	private String option_seq;

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
	 * @return the trnQuizHeaderModel
	 */
	public TrnQuizHeaderModel getTrnQuizHeaderModel() {
		return trnQuizHeaderModel;
	}

	/**
	 * @param trnQuizHeaderModel the trnQuizHeaderModel to set
	 */
	public void setTrnQuizHeaderModel(TrnQuizHeaderModel trnQuizHeaderModel) {
		this.trnQuizHeaderModel = trnQuizHeaderModel;
	}

	/**
	 * @return the mstQuizQuestionBankModel
	 */
	public MstQuizQuestionBankModel getMstQuizQuestionBankModel() {
		return mstQuizQuestionBankModel;
	}

	/**
	 * @param trnQuizHeaderModel
	 * @param mstQuizQuestionBankModel
	 */
	public TrnQuizResponseID(TrnQuizHeaderModel trnQuizHeaderModel, MstQuizQuestionBankModel mstQuizQuestionBankModel) {
		super();
		this.trnQuizHeaderModel = trnQuizHeaderModel;
		this.mstQuizQuestionBankModel = mstQuizQuestionBankModel;
	}

	/**
	 * 
	 */
	public TrnQuizResponseID() {
		super();
		// TODO Auto-generated constructor stub
	}

}
