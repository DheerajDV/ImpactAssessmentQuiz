package com.iocl.ImpactAssessmentQuiz.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iocl.ImpactAssessmentQuiz.model.MstActivityListModel;
import com.iocl.ImpactAssessmentQuiz.repository.MstActivityListRepository;

@Service
public class MstActivityListService {

	@Autowired
	MstActivityListRepository mstActivityListRepository;

	public ArrayList<String> getDivisionList() {
		return mstActivityListRepository.getDivisionList();
	}

	public ArrayList<String> getDomainList(String division) {
		return mstActivityListRepository.getDomainList(division);
	}

	public ArrayList<String> getSubDomainList(String division, String domain) {
		return mstActivityListRepository.getSubDomainList(division, domain);
	}

	public List<MstActivityListModel> getModuleList(String division, String domain, String sub_domain) {
		return mstActivityListRepository.getModuleList(division, domain, sub_domain);
	}

	public MstActivityListModel findActivityCode(String activity_code) {
		return mstActivityListRepository.findById(activity_code).orElse(null);
	}

	public List<MstActivityListModel> findAll() {
		return (List<MstActivityListModel>) mstActivityListRepository.findAll();
	}

	public void saveorupdateAll(List<MstActivityListModel> activity_list) {
		mstActivityListRepository.saveAll(activity_list);

	}
	public void deleteAll() {
		mstActivityListRepository.deleteAll();

	}

	public Long count() {

		return mstActivityListRepository.count();
	}

}
