package com.iocl.ImpactAssessmentQuiz.repository;

import org.springframework.data.repository.CrudRepository;

import com.iocl.ImpactAssessmentQuiz.model.MstQuizQuestionOptionsID;
import com.iocl.ImpactAssessmentQuiz.model.MstQuizQuestionOptionsModel;

public interface MstQuizQuestionOptionsRepository
		extends CrudRepository<MstQuizQuestionOptionsModel, MstQuizQuestionOptionsID> {

}
