package com.iocl.ImpactAssessmentQuiz.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Embeddable
public class TrnEmpWiseUsageID implements Serializable {

	private static final long serialVersionUID = 1L;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EMPLOYEE_CODE", insertable = true, updatable = true, nullable = false)
	@Fetch(FetchMode.JOIN)
	private EmployeeModel employeeModel;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACTIVITY_CODE", insertable = true, updatable = true, nullable = false)
	@Fetch(FetchMode.JOIN)
	private MstActivityListModel mstActivityListModel;

	/**
	 * @return the employeeModel
	 */
	public EmployeeModel getEmployeeModel() {
		return employeeModel;
	}

	/**
	 * @param employeeModel the employeeModel to set
	 */
	public void setEmployeeModel(EmployeeModel employeeModel) {
		this.employeeModel = employeeModel;
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
	 * @param employeeModel
	 * @param mstActivityListModel
	 */
	public TrnEmpWiseUsageID(EmployeeModel employeeModel, MstActivityListModel mstActivityListModel) {
		super();
		this.employeeModel = employeeModel;
		this.mstActivityListModel = mstActivityListModel;
	}

	/**
	 * 
	 */
	public TrnEmpWiseUsageID() {
		super();
		// TODO Auto-generated constructor stub
	}

}
