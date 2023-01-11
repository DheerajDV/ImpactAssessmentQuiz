package com.iocl.ImpactAssessmentQuiz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iocl.ImpactAssessmentQuiz.model.TrnEmpWiseUsageModel;
import com.iocl.ImpactAssessmentQuiz.repository.TrnEmpWiseUsageRepository;

@Service
public class TrnEmpWiseUsageService {

	@Autowired
	TrnEmpWiseUsageRepository trnEmpWiseUsageRepository;

	public List<TrnEmpWiseUsageModel> findAll() {
		return (List<TrnEmpWiseUsageModel>) trnEmpWiseUsageRepository.findAll();
	}

	public List<TrnEmpWiseUsageModel> findDivisionDataCompCode(List<String> div_code, List<String> allowed_comp_code) {
		return trnEmpWiseUsageRepository.findDivisionDataCompCode(div_code, allowed_comp_code);
	}

	public List<TrnEmpWiseUsageModel> findDivisionData(List<String> div_code) {
		return trnEmpWiseUsageRepository.findDivisionData(div_code);
	}

	public void saveorupdateAll(List<TrnEmpWiseUsageModel> employee_usage_report_list) {
		trnEmpWiseUsageRepository.saveAll(employee_usage_report_list);

	}

}
