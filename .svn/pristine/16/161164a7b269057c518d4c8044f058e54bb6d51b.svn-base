package com.iocl.ImpactAssessmentQuiz.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Embeddable
public class TrnQuizResultDetailsID implements Serializable {

	private static final long serialVersionUID = 1L;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "QUIZ_ID", insertable = true, updatable = true, nullable = false)
	@Fetch(FetchMode.JOIN)
	private TrnQuizHeaderModel trnQuizHeaderModel;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "QUESTION_ID", insertable = true, updatable = true, nullable = false)
	@Fetch(FetchMode.JOIN)
	private MstQuizQuestionBankModel mstQuizQuestionBankModel;

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
	 * @param mstQuizQuestionBankModel the mstQuizQuestionBankModel to set
	 */
	public void setMstQuizQuestionBankModel(MstQuizQuestionBankModel mstQuizQuestionBankModel) {
		this.mstQuizQuestionBankModel = mstQuizQuestionBankModel;
	}

}
