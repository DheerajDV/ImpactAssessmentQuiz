package com.iocl.ImpactAssessmentQuiz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iocl.ImpactAssessmentQuiz.model.TrnQuizActivityMappingModel;
import com.iocl.ImpactAssessmentQuiz.repository.TrnQuizActivityMappingRepository;

@Service
public class TrnQuizActivityMappingService {

	@Autowired
	TrnQuizActivityMappingRepository trnQuizActivityMappingRepository;

	public void saveorupdateAll(List<TrnQuizActivityMappingModel> trnQuizActivityMappingModel) {
		trnQuizActivityMappingRepository.saveAll(trnQuizActivityMappingModel);

	}

}
