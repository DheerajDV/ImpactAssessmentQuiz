package com.iocl.ImpactAssessmentQuiz.repository;

import org.springframework.data.repository.CrudRepository;

import com.iocl.ImpactAssessmentQuiz.model.TrnQuizActivityMappingID;
import com.iocl.ImpactAssessmentQuiz.model.TrnQuizActivityMappingModel;

public interface TrnQuizActivityMappingRepository
		extends CrudRepository<TrnQuizActivityMappingModel, TrnQuizActivityMappingID> {

}
