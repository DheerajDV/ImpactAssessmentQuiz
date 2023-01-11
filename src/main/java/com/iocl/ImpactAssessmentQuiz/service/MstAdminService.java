package com.iocl.ImpactAssessmentQuiz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iocl.ImpactAssessmentQuiz.model.MstAdminModel;
import com.iocl.ImpactAssessmentQuiz.repository.MstAdminRepository;

@Service
public class MstAdminService {

	@Autowired
	MstAdminRepository mstAdminRepository;

	public MstAdminModel findOne(Long emp_code) {
		return mstAdminRepository.findById(emp_code).orElse(null);
	}

	public List<MstAdminModel> findAll() {
		return (List<MstAdminModel>) mstAdminRepository.findAll();
	}

	public List<MstAdminModel> findDivisionData(List<String> div_code) {
		return mstAdminRepository.findDivisionData(div_code);
	}

	public void saveorupdateAll(List<MstAdminModel> admin_list) {
		mstAdminRepository.saveAll(admin_list);

	}

}
