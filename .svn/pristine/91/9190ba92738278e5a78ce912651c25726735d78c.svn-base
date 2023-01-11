package com.iocl.ImpactAssessmentQuiz.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Embeddable
public class TrnQuizActivityMappingID implements Serializable {

	private static final long serialVersionUID = 1L;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "QUIZ_ID", insertable = true, updatable = true, nullable = false)
	@Fetch(FetchMode.JOIN)
	private TrnQuizHeaderModel trnQuizHeaderModel;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACTIVITY_CODE", insertable = true, updatable = true, nullable = false)
	@Fetch(FetchMode.JOIN)
	private MstActivityListModel mstActivityListModel;

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
	 * @param trnQuizHeaderModel
	 * @param mstActivityListModel
	 */
	public TrnQuizActivityMappingID(TrnQuizHeaderModel trnQuizHeaderModel, MstActivityListModel mstActivityListModel) {
		super();
		this.trnQuizHeaderModel = trnQuizHeaderModel;
		this.mstActivityListModel = mstActivityListModel;
	}

	/**
	 * 
	 */
	public TrnQuizActivityMappingID() {
		super();
		// TODO Auto-generated constructor stub
	}

}
