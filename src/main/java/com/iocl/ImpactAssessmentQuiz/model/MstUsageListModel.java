package com.iocl.ImpactAssessmentQuiz.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "MST_USAGE_REPORT")

public class MstUsageListModel {

	@Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator= "MST_USAGE_REPORT_SEQ")
	@SequenceGenerator(sequenceName = "mst_usage_report_seq", allocationSize = 1, name = "MST_USAGE_REPORT_SEQ")
	@Column(name = "ID")
    private Long id;
	
	@Column(name = "EMP_ID")
	private String emp_id;

	@Column(name = "EMP_NAME")
	private String emp_name;

	@Column(name = "ACTIVE")
	private String active;

	@Column(name = "DESIGNATION")
	private String designation;

	@Column(name = "EMP_MAIL")
	private String emp_mail;

	@Column(name = "DIVISION")
	private String division;

	@Column(name = "COMPANY")
	private String company;

	@Column(name = "LOCATION")
	private String location;

	@Column(name = "DEPARTMENT")
	private String department;

	@Column(name = "GRADE")
	private String grade;
	
	@Column(name = "ACTIVITY_CODE")
	private String activity_code;

	@Column(name = "ACTIVITY_NAME")
	private String activity_name;

	@Column(name = "DOMAIN")
	private String domain;

	@Column(name = "REGISTRATION_STATUS")
	private String registration_status;

	@Column(name = "START_DATE")
	private LocalDateTime start_date;

	@Column(name = "END_DATE")
	private LocalDateTime end_date;

	@Column(name = "SUCCESS_STATUS")
	private String success_status;

	@Column(name = "SCORE")
	private float score;

	@Column(name = "MINIMUM_PERCENTAGE_GRADE")
	private float minimum_percentage_grade;

	@Column(name = "COURSE_DURATION")
	private float course_duration;

	@Column(name = "TIME_ELAPSED")
	private float time_elapsed;

	@Column(name = "CURRENT_ATTEMPT_INDICATOR")
	private int current_attempt_indicator;

	@Column(name = "UPDATED_BY")
	private Long updated_by;

	@Column(name = "UPDATED_ON")
	private LocalDateTime updated_on;

	public String getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}

	public String getEmp_name() {
		return emp_name;
	}

	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getEmp_mail() {
		return emp_mail;
	}

	public void setEmp_mail(String emp_mail) {
		this.emp_mail = emp_mail;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getActivity_code() {
		return activity_code;
	}

	public void setActivity_code(String activity_code) {
		this.activity_code = activity_code;
	}

	public String getActivity_name() {
		return activity_name;
	}

	public void setActivity_name(String activity_name) {
		this.activity_name = activity_name;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getRegistration_status() {
		return registration_status;
	}

	public void setRegistration_status(String registration_status) {
		this.registration_status = registration_status;
	}

	public LocalDateTime getStart_date() {
		return start_date;
	}

	public void setStart_date(LocalDateTime start_date) {
		this.start_date = start_date;
	}

	public LocalDateTime getEnd_date() {
		return end_date;
	}

	public void setEnd_date(LocalDateTime end_date) {
		this.end_date = end_date;
	}

	public String getSuccess_status() {
		return success_status;
	}

	public void setSuccess_status(String success_status) {
		this.success_status = success_status;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public float getMinimum_percentage_grade() {
		return minimum_percentage_grade;
	}

	public void setMinimum_percentage_grade(float minimum_percentage_grade) {
		this.minimum_percentage_grade = minimum_percentage_grade;
	}

	public float getCourse_duration() {
		return course_duration;
	}

	public void setCourse_duration(float course_duration) {
		this.course_duration = course_duration;
	}

	public float getTime_elapsed() {
		return time_elapsed;
	}

	public void setTime_elapsed(float time_elapsed) {
		this.time_elapsed = time_elapsed;
	}

	public int getCurrent_attempt_indicator() {
		return current_attempt_indicator;
	}

	public void setCurrent_attempt_indicator(int current_attempt_indicator) {
		this.current_attempt_indicator = current_attempt_indicator;
	}

	public Long getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(Long updated_by) {
		this.updated_by = updated_by;
	}

	public String getUpdated_on() {
		return updated_on == null ? "" : updated_on.format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss a"));
	}

	public void setUpdated_on(LocalDateTime updated_on) {
		this.updated_on = updated_on;
	}

}
