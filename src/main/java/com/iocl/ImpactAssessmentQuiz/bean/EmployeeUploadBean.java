package com.iocl.ImpactAssessmentQuiz.bean;

import java.util.ArrayList;
import java.util.List;

import com.iocl.ImpactAssessmentQuiz.model.EmployeeModel;

public class EmployeeUploadBean {

	private String error_msg;
	private ArrayList<ArrayList<String>> error_records;
	private List<EmployeeModel> employeeModel;

	/**
	 * @return the error_msg
	 */
	public String getError_msg() {
		return error_msg;
	}

	/**
	 * @param error_msg the error_msg to set
	 */
	public void setError_msg(String error_msg) {
		this.error_msg = error_msg;
	}

	/**
	 * @return the error_records
	 */
	public ArrayList<ArrayList<String>> getError_records() {
		return error_records;
	}

	/**
	 * @param error_records the error_records to set
	 */
	public void setError_records(ArrayList<ArrayList<String>> error_records) {
		this.error_records = error_records;
	}

	/**
	 * @return the employeeModel
	 */
	public List<EmployeeModel> getEmployeeModel() {
		return employeeModel;
	}

	/**
	 * @param employeeModel the employeeModel to set
	 */
	public void setEmployeeModel(List<EmployeeModel> employeeModel) {
		this.employeeModel = employeeModel;
	}

}
