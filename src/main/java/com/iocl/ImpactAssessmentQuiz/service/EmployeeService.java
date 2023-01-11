package com.iocl.ImpactAssessmentQuiz.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iocl.ImpactAssessmentQuiz.model.EmployeeModel;
import com.iocl.ImpactAssessmentQuiz.repository.EmployeeRepository;

@Service
public class EmployeeService {
	@Autowired
	EmployeeRepository employeeRepository;

	public EmployeeModel findEmpDetails(Long empid) {

		return employeeRepository.findById(empid).orElse(null);

	}

	public Object[][] getEmpDet(Long emp_cd) {
		return (Object[][]) employeeRepository.getEmpDet(emp_cd);
	}

	public ArrayList<ArrayList<String>> getAllDivision() {
		return employeeRepository.getAllDivision();
	}

	public ArrayList<ArrayList<String>> getAdminDivision(List<String> div_code) {
		return employeeRepository.getAdminDivision(div_code);
	}

	public ArrayList<ArrayList<String>> getCompanyCodeList(String division) {
		return employeeRepository.getCompanyCodeList(division);
	}

	public ArrayList<ArrayList<String>> getLocationList(String division, String company_code) {
		return employeeRepository.getLocationList(division, company_code);
	}

	public ArrayList<ArrayList<String>> getPsaCodeList(String division, String company_code, String loc_code) {
		return employeeRepository.getPsaCodeList(division, company_code, loc_code);
	}

	public ArrayList<ArrayList<String>> getEmpCodeList(String division, String company_code, String loc_code,
			String psa_code) {
		return employeeRepository.getEmpCodeList(division, company_code, loc_code, psa_code);
	}

	public List<EmployeeModel> getEmployeeList(String division, String company_code, String loc_code, String psa_code) {
		return employeeRepository.getEmployeeList(division, company_code, loc_code, psa_code);
	}

	public List<String> findSOMapping(String currcompc) {
		return employeeRepository.findSOMapping(currcompc);
	}

	public ArrayList<ArrayList<String>> getCompanyCodeListLocation(String division, List<String> allowed_comp_code) {
		return employeeRepository.getCompanyCodeList(division, allowed_comp_code);
	}

	public ArrayList<ArrayList<String>> getLocationListLocation(String division, String company_code,
			List<String> allowed_comp_code) {
		return employeeRepository.getLocationListLocation(division, company_code, allowed_comp_code);
	}

	public ArrayList<ArrayList<String>> getPsaCodeListLocation(String division, String company_code, String loc_code,
			List<String> allowed_comp_code) {
		return employeeRepository.getPsaCodeListLocation(division, company_code, loc_code, allowed_comp_code);
	}

	public ArrayList<ArrayList<String>> getEmpCodeListLocation(String division, String company_code, String loc_code,
			String psa_code, List<String> allowed_comp_code) {
		return employeeRepository.getEmpCodeListLocation(division, company_code, loc_code, psa_code, allowed_comp_code);
	}

	public List<EmployeeModel> getEmployeeListLocation(String division, String company_code, String loc_code,
			String psa_code, List<String> allowed_comp_code) {
		return employeeRepository.getEmployeeListLocation(division, company_code, loc_code, psa_code,
				allowed_comp_code);
	}

}
