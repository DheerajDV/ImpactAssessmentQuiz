package com.iocl.ImpactAssessmentQuiz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iocl.ImpactAssessmentQuiz.model.TrnQuizResultDetails;
import com.iocl.ImpactAssessmentQuiz.repository.TrnQuizResultDetailsRepository;

@Service
public class TrnQuizResultDetailsService {

	@Autowired
	TrnQuizResultDetailsRepository trnQuizResultDetailsRepository;

	public List<TrnQuizResultDetails> findAll() {
		return (List<TrnQuizResultDetails>) trnQuizResultDetailsRepository.findAll();
	}

	public List<TrnQuizResultDetails> findDivisionData(List<String> div_code) {
		return (List<TrnQuizResultDetails>) trnQuizResultDetailsRepository.findDivisionData(div_code);
	}

	public List<TrnQuizResultDetails> findDivisionDataCompCode(List<String> div_code, List<String> allowed_comp_code) {
		return (List<TrnQuizResultDetails>) trnQuizResultDetailsRepository.findDivisionDataCompCode(div_code,
				allowed_comp_code);
	}



}
