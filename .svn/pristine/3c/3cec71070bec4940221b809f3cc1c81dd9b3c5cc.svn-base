package com.iocl.ImpactAssessmentQuiz.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "TRN_QUIZ_ACTIVITY_MAPPING")
public class TrnQuizActivityMappingModel {

	@EmbeddedId
	private TrnQuizActivityMappingID trnQuizActivityMappingID;

	@Column(name = "UPDATED_BY")
	private Long updated_by;

	@Column(name = "UPDATED_ON")
	private LocalDateTime updated_on;

	/**
	 * @return the trnQuizActivityMappingID
	 */
	public TrnQuizActivityMappingID getTrnQuizActivityMappingID() {
		return trnQuizActivityMappingID;
	}

	/**
	 * @param trnQuizActivityMappingID the trnQuizActivityMappingID to set
	 */
	public void setTrnQuizActivityMappingID(TrnQuizActivityMappingID trnQuizActivityMappingID) {
		this.trnQuizActivityMappingID = trnQuizActivityMappingID;
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
