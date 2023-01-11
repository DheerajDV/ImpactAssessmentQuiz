package com.iocl.ImpactAssessmentQuiz.service;

import java.util.ArrayList;
import java.util.List;

import com.iocl.ImpactAssessmentQuiz.bean.MasterCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iocl.ImpactAssessmentQuiz.model.MstDivisionListModel;
import com.iocl.ImpactAssessmentQuiz.repository.MstDivisionListRepository;

@Service
public class MstDivisionListService {
	
	@Autowired
	MstDivisionListRepository mstDivisionListRepository;
	
	public void saveorupdateAll(List<MstDivisionListModel> division_list) {
		mstDivisionListRepository.saveAll(division_list);

	}
	public void deleteAll() {
		mstDivisionListRepository.deleteAll();

	}
	
	public List<MstDivisionListModel> findAll() {
		return (List<MstDivisionListModel>) mstDivisionListRepository.findAll();
	}
	public List<MasterCount> find_count_groupby() {
		return (List<MasterCount>) mstDivisionListRepository.find_count_groupby();
	}

	public MstDivisionListModel userDivisionmasterDetails(String empid) {
		return mstDivisionListRepository.findById(empid).orElse(null);

	}

	public ArrayList<MstDivisionListModel> userDivisionmasterDetailsbyManager(String emp_manager) {
		return mstDivisionListRepository.findbyemp_manager(emp_manager);

	}



}
