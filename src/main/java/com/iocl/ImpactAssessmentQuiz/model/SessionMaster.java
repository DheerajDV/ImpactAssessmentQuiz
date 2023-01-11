package com.iocl.ImpactAssessmentQuiz.model;

import java.util.ArrayList;
import java.util.Optional;

import com.iocl.ImpactAssessmentQuiz.bean.Function_wise_course;

public class SessionMaster {

	private String CaptchaAnswer;
	private EmployeeModel employeeModel;
	private String host;
	private String admin_flg;
	private MstDivisionListModel mstDivisionListModel;
	private String learning_group_flg;

	private Long mst_activity_list_count;
	
	public ArrayList<MstDivisionListModel> learning_group;
	
	private ArrayList<ArrayList<String>> func_group;
	
	private ArrayList<Function_wise_course> function_wise_course_list;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public EmployeeModel getEmployeeModel() {
		return employeeModel;
	}

	public void setEmployeeModel(EmployeeModel employeeModel) {
		this.employeeModel = employeeModel;
	}

	public String getCaptchaAnswer() {
		return CaptchaAnswer;
	}

	public void setCaptchaAnswer(String captchaAnswer) {
		CaptchaAnswer = captchaAnswer;
	}

	/**
	 * @return the admin_flg
	 */
	public String getAdmin_flg() {
		return admin_flg;
	}

	/**
	 * @param admin_flg the admin_flg to set
	 */
	public void setAdmin_flg(String admin_flg) {
		this.admin_flg = admin_flg;
	}

	public MstDivisionListModel getMstDivisionListModel() {
		return mstDivisionListModel;
	}

	public void setMstDivisionListModel(MstDivisionListModel mstDivisionListModel) {
		this.mstDivisionListModel = mstDivisionListModel;
	}

	public ArrayList<MstDivisionListModel> getLearning_group() {
		return learning_group;
	}

	public void setLearning_group(ArrayList<MstDivisionListModel> learning_group) {
		this.learning_group = learning_group;
	}

	public String getLearning_group_flg() {
		return learning_group_flg;
	}

	public void setLearning_group_flg(String learning_group_flg) {
		this.learning_group_flg = learning_group_flg;
	}

	public ArrayList<ArrayList<String>> getFunc_group() {
		return func_group;
	}

	public void setFunc_group(ArrayList<ArrayList<String>> func_group) {
		this.func_group = func_group;
	}

	public ArrayList<Function_wise_course> getFunction_wise_course_list() {
		return function_wise_course_list;
	}

	public void setFunction_wise_course_list(ArrayList<Function_wise_course> function_wise_course_list) {
		this.function_wise_course_list = function_wise_course_list;
	}

	public Long getMst_activity_list_count() {
		return mst_activity_list_count;
	}

	public void setMst_activity_list_count(Long mst_activity_list_count) {
		this.mst_activity_list_count = mst_activity_list_count;
	}
}
