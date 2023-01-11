package com.iocl.ImpactAssessmentQuiz.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "TRN_EMP_WISE_USAGE")
public class TrnEmpWiseUsageModel {

	@EmbeddedId
	private TrnEmpWiseUsageID trnEmpWiseUsageID;

	@Column(name = "REGISTERATION_STATUS")
	private String registeration_status;

	@Column(name = "START_DATE")
	private LocalDateTime start_date;

	@Column(name = "END_DATE")
	private LocalDateTime end_date;

	@Column(name = "SUCCESS_STATUS")
	private String success_status;

	@Column(name = "SCORE")
	private Double score;

	@Column(name = "MIN_PERCENTAGE")
	private Double min_percentage;

	@Column(name = "COURSE_DURATION")
	private Double course_duration;

	@Column(name = "TIME_ELAPSED")
	private Double time_elapsed;

	@Column(name = "CURRENT_ATTEMPT")
	private Long current_attempt;

	@Column(name = "UPDATED_BY")
	private Long updated_by;

	@Column(name = "UPDATED_ON")
	private LocalDateTime updated_on;

	/**
	 * @return the trnEmpWiseUsageID
	 */
	public TrnEmpWiseUsageID getTrnEmpWiseUsageID() {
		return trnEmpWiseUsageID;
	}

	/**
	 * @param trnEmpWiseUsageID the trnEmpWiseUsageID to set
	 */
	public void setTrnEmpWiseUsageID(TrnEmpWiseUsageID trnEmpWiseUsageID) {
		this.trnEmpWiseUsageID = trnEmpWiseUsageID;
	}

	/**
	 * @return the registeration_status
	 */
	public String getRegisteration_status() {
		return registeration_status;
	}

	/**
	 * @param registeration_status the registeration_status to set
	 */
	public void setRegisteration_status(String registeration_status) {
		this.registeration_status = registeration_status;
	}

	/**
	 * @return the start_date
	 */
	public String getStart_date() {
		return start_date == null ? "" : start_date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss a"));
	}

	/**
	 * @param start_date the start_date to set
	 */
	public void setStart_date(LocalDateTime start_date) {
		this.start_date = start_date;
	}

	/**
	 * @return the end_date
	 */
//	public LocalDateTime getEnd_date() {
//		return end_date;
//	}

	public String getEnd_date() {
		return end_date == null ? "" : end_date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss a"));
	}

	/**
	 * @param end_date the end_date to set
	 */
	public void setEnd_date(LocalDateTime end_date) {
		this.end_date = end_date;
	}

	/**
	 * @return the success_status
	 */
	public String getSuccess_status() {
		return success_status;
	}

	/**
	 * @param success_status the success_status to set
	 */
	public void setSuccess_status(String success_status) {
		this.success_status = success_status;
	}

	/**
	 * @return the score
	 */
	public Double getScore() {
		return score;
	}

	/**
	 * @param score the score to set
	 */
	public void setScore(Double score) {
		this.score = score;
	}

	/**
	 * @return the min_percentage
	 */
	public Double getMin_percentage() {
		return min_percentage;
	}

	/**
	 * @param min_percentage the min_percentage to set
	 */
	public void setMin_percentage(Double min_percentage) {
		this.min_percentage = min_percentage;
	}

	/**
	 * @return the course_duration
	 */
	public Double getCourse_duration() {
		return course_duration;
	}

	/**
	 * @param course_duration the course_duration to set
	 */
	public void setCourse_duration(Double course_duration) {
		this.course_duration = course_duration;
	}

	/**
	 * @return the time_elapsed
	 */
	public Double getTime_elapsed() {
		return time_elapsed;
	}

	/**
	 * @param time_elapsed the time_elapsed to set
	 */
	public void setTime_elapsed(Double time_elapsed) {
		this.time_elapsed = time_elapsed;
	}

	/**
	 * @return the current_attempt
	 */
	public Long getCurrent_attempt() {
		return current_attempt;
	}

	/**
	 * @param current_attempt the current_attempt to set
	 */
	public void setCurrent_attempt(Long current_attempt) {
		this.current_attempt = current_attempt;
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
