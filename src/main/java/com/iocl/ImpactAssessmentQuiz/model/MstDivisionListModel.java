package com.iocl.ImpactAssessmentQuiz.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MST_DIVISION_MASTER")

public class MstDivisionListModel {
	

	
	@Id
	@Column(name = "Emp_ID")
	private String emp_id;
    
	@Column(name = "EMP_NAME")
	private String emp_name;
	
	@Column(name = "EMP_DESG")
	private String emp_desg;
	
	@Column(name = "EMP_GENDER")
	private String emp_gender;
	
	@Column(name = "EMP_Grade")
	private String emp_grade;
	
	@Column(name = "EMP_EMAILID")
	private String emp_emailid;
	
	@Column(name = "EMP_Manager")
	private String emp_manager;

	@Column(name = "EMP_DOJ")
	private LocalDateTime emp_doj;
	
	@Column(name = "EMP_DOB")
	private LocalDateTime emp_dob;
	
	@Column(name = "EMP_DIV")
	private String emp_div;
	
	@Column(name = "EMP_COM_CODE")
	private String emp_com_code;
	
	@Column(name = "EMP_COM")
	private String emp_com;
	
	@Column(name = "EMP_LOC_CODE")
	private String emp_loc_code;
	
	@Column(name = "EMP_LOC")
	private String emp_loc;
	
	@Column(name = "EMP_DEP_CODE")
	private String emp_dep_code;
	
	@Column(name = "EMP_DEP")
	private String emp_dep;
	
	@Column(name = "EMP_STATUS")
	private String emp_status;
	
	@Column(name = "EMP_JOB_GRADE")
	private String emp_job_grade;
	
	@Column(name = "EMP_TOTAL_COURSE_COMPLETED")
	private int emp_total_course_completed;
	
	@Column(name = "EMP_TOTAL_COURSE_PASSED")
	private int emp_total_course_passed;
	
	@Column(name = "EMP_TOTAL_COURSE_FAILED")
	private int emp_total_course_failed;
	
	@Column(name = "EMP_COURSES_COMP_OTHER_DIV")
	private int emp_courses_comp_other_div;
	
	@Column(name = "EMP_COURSES_PASS_OTHER_DIV")
	private int	 emp_courses_pass_other_div;
	
	@Column(name = "EMP_COURSES_FAIL_OTHER_DIV")
	private int emp_courses_fail_other_div;
	
	@Column(name = "EMP_LOGGED_ONCE")
	private String emp_logged_once;
	
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

	public String getEmp_desg() {
		return emp_desg;
	}

	public void setEmp_desg(String emp_desg) {
		this.emp_desg = emp_desg;
	}

	public String getEmp_gender() {
		return emp_gender;
	}

	public void setEmp_gender(String emp_gender) {
		this.emp_gender = emp_gender;
	}

	public String getEmp_grade() {
		return emp_grade;
	}

	public void setEmp_grade(String emp_grade) {
		this.emp_grade = emp_grade;
	}

	public String getEmp_emailid() {
		return emp_emailid;
	}

	public void setEmp_emailid(String emp_emailid) {
		this.emp_emailid = emp_emailid;
	}

	public String getEmp_manager() {
		return emp_manager;
	}

	public void setEmp_manager(String emp_manager) {
		this.emp_manager = emp_manager;
	}



	public String getEmp_doj() {
		return emp_doj == null ? ""
				: emp_doj.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

	}

	public void setEmp_doj(LocalDateTime emp_doj) {
		this.emp_doj = emp_doj;
	}

	public String getEmp_dob() {
		return emp_dob == null ? ""
				: emp_dob.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
	}

	public void setEmp_dob(LocalDateTime emp_dob) {
		this.emp_dob = emp_dob;
	}

	public String getEmp_div() {
		return emp_div;
	}

	public void setEmp_div(String emp_div) {
		this.emp_div = emp_div;
	}

	public String getEmp_com_code() {
		return emp_com_code;
	}

	public void setEmp_com_code(String emp_com_code) {
		this.emp_com_code = emp_com_code;
	}

	public String getEmp_com() {
		return emp_com;
	}

	public void setEmp_com(String emp_com) {
		this.emp_com = emp_com;
	}

	public String getEmp_loc_code() {
		return emp_loc_code;
	}

	public void setEmp_loc_code(String emp_loc_code) {
		this.emp_loc_code = emp_loc_code;
	}

	public String getEmp_loc() {
		return emp_loc;
	}

	public void setEmp_loc(String emp_loc) {
		this.emp_loc = emp_loc;
	}

	public String getEmp_dep_code() {
		return emp_dep_code;
	}

	public void setEmp_dep_code(String emp_dep_code) {
		this.emp_dep_code = emp_dep_code;
	}

	public String getEmp_dep() {
		return emp_dep;
	}

	public void setEmp_dep(String emp_dep) {
		this.emp_dep = emp_dep;
	}

	public String getEmp_status() {
		return emp_status;
	}

	public void setEmp_status(String emp_status) {
		this.emp_status = emp_status;
	}

	public String getEmp_job_grade() {
		return emp_job_grade;
	}

	public void setEmp_job_grade(String emp_job_grade) {
		this.emp_job_grade = emp_job_grade;
	}

	public int getEmp_total_course_completed() {
		return emp_total_course_completed;
	}

	public void setEmp_total_course_completed(int emp_total_course_completed) {
		this.emp_total_course_completed = emp_total_course_completed;
	}

	public int getEmp_total_course_passed() {
		return emp_total_course_passed;
	}

	public void setEmp_total_course_passed(int emp_total_course_passed) {
		this.emp_total_course_passed = emp_total_course_passed;
	}

	public int getEmp_total_course_failed() {
		return emp_total_course_failed;
	}

	public void setEmp_total_course_failed(int emp_total_course_failed) {
		this.emp_total_course_failed = emp_total_course_failed;
	}

	public int getEmp_courses_comp_other_div() {
		return emp_courses_comp_other_div;
	}

	public void setEmp_courses_comp_other_div(int emp_courses_comp_other_div) {
		this.emp_courses_comp_other_div = emp_courses_comp_other_div;
	}

	public int getEmp_courses_pass_other_div() {
		return emp_courses_pass_other_div;
	}

	public void setEmp_courses_pass_other_div(int emp_courses_pass_other_div) {
		this.emp_courses_pass_other_div = emp_courses_pass_other_div;
	}

	public int getEmp_courses_fail_other_div() {
		return emp_courses_fail_other_div;
	}

	public void setEmp_courses_fail_other_div(int emp_courses_fail_other_div) {
		this.emp_courses_fail_other_div = emp_courses_fail_other_div;
	}

	public String getEmp_logged_once() {
		return emp_logged_once;
	}

	public void setEmp_logged_once(String emp_logged_once) {
		this.emp_logged_once = emp_logged_once;
	}

	public Long getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(Long updated_by) {
		this.updated_by = updated_by;
	}

	public String getUpdated_on() {
		
		return updated_on == null ? ""
				: updated_on.format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss a"));

	}

	public void setUpdated_on(LocalDateTime updated_on) {
		this.updated_on = updated_on;
	}
	
	
	
	
}
