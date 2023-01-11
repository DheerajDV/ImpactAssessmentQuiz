package com.iocl.ImpactAssessmentQuiz.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MST_ACTIVITY_LIST")
public class MstActivityListModel {

	@Id
	@Column(name = "ACTIVITY_CODE")
	private String activity_code;

	@Column(name = "DIVISION")
	private String division;

	@Column(name = "DOMAIN")
	private String domain;

	@Column(name = "SUB_DOMAIN")
	private String sub_domain;

	@Column(name = "ACTIVITY_NAME")
	private String activity_name;

	@Column(name = "DURATION")
	private Double duration;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "EXPIRY_STATUS")
	private String expiry_status;

	@Column(name = "UPDATED_BY")
	private Long updated_by;

	@Column(name = "UPDATED_ON")
	private LocalDateTime updated_on;

	@Column(name = "Metadata_Expiration_Date")
	private LocalDateTime metadata_expiration_date;

	@Column(name = "lms_upload_date")
	private LocalDateTime lms_upload_date;

	/**
	 * @return the activity_code
	 */
	public String getActivity_code() {
		return activity_code;
	}

	/**
	 * @return the metadata_expiration_date
	 */
	public String getMetadata_expiration_date() {
		return metadata_expiration_date == null ? ""
				: metadata_expiration_date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss a"));

	}

	/**
	 * @param metadata_expiration_date the metadata_expiration_date to set
	 */
	public void setMetadata_expiration_date(LocalDateTime metadata_expiration_date) {
		this.metadata_expiration_date = metadata_expiration_date;
	}

	/**
	 * @param activity_code the activity_code to set
	 */
	public void setActivity_code(String activity_code) {
		this.activity_code = activity_code;
	}

	/**
	 * @return the division
	 */
	public String getDivision() {
		return division;
	}

	/**
	 * @param division the division to set
	 */
	public void setDivision(String division) {
		this.division = division;
	}

	/**
	 * @return the domain
	 */
	public String getDomain() {
		return domain;
	}

	/**
	 * @param domain the domain to set
	 */
	public void setDomain(String domain) {
		this.domain = domain;
	}

	/**
	 * @return the sub_domain
	 */
	public String getSub_domain() {
		return sub_domain;
	}

	/**
	 * @param sub_domain the sub_domain to set
	 */
	public void setSub_domain(String sub_domain) {
		this.sub_domain = sub_domain;
	}

	/**
	 * @return the activity_name
	 */
	public String getActivity_name() {
		return activity_name;
	}

	/**
	 * @param activity_name the activity_name to set
	 */
	public void setActivity_name(String activity_name) {
		this.activity_name = activity_name;
	}

	/**
	 * @return the duration
	 */
	public Double getDuration() {
		return duration;
	}

	/**
	 * @param duration the duration to set
	 */
	public void setDuration(Double duration) {
		this.duration = duration;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the expiry_status
	 */
	public String getExpiry_status() {
		return expiry_status;
	}

	/**
	 * @param expiry_status the expiry_status to set
	 */
	public void setExpiry_status(String expiry_status) {
		this.expiry_status = expiry_status;
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

	public String getLms_upload_date() {

		return lms_upload_date == null ? ""
				: lms_upload_date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss a"));
	}

	public void setLms_upload_date(LocalDateTime f_lms_upload_date) {

		this.lms_upload_date = f_lms_upload_date;
	}

}
