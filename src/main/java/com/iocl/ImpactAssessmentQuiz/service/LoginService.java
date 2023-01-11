package com.iocl.ImpactAssessmentQuiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iocl.ImpactAssessmentQuiz.model.EmployeeModel;
import com.iocl.ImpactAssessmentQuiz.repository.EmployeeRepository;

@Service
public class LoginService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public EmployeeModel userDetails(String empid) {
		return employeeRepository.findById(Long.parseLong(empid)).orElse(null);

	}

	public EmployeeModel findEmpDetails(Long empid) {
		return employeeRepository.findById(empid).orElse(null);

	}
}