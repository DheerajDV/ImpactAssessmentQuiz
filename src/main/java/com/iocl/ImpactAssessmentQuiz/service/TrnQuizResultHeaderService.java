package com.iocl.ImpactAssessmentQuiz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iocl.ImpactAssessmentQuiz.model.TrnQuizResultHeader;
import com.iocl.ImpactAssessmentQuiz.repository.TrnQuizResultHeaderRepository;

@Service
public class TrnQuizResultHeaderService {
	@Autowired
	TrnQuizResultHeaderRepository trnQuizResultHeaderRepository;

	public List<TrnQuizResultHeader> findAll() {
		return (List<TrnQuizResultHeader>) trnQuizResultHeaderRepository.findAll();
	}

	public List<TrnQuizResultHeader> findDivisionData(List<String> div_code) {
		return (List<TrnQuizResultHeader>) trnQuizResultHeaderRepository.findDivisionData(div_code);
	}

	public List<TrnQuizResultHeader> findDivisionDataCompCode(List<String> div_code, List<String> allowed_comp_code) {
		return (List<TrnQuizResultHeader>) trnQuizResultHeaderRepository.findDivisionDataCompCode(div_code, allowed_comp_code);
	}

}
