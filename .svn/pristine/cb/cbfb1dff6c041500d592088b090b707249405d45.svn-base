package com.iocl.ImpactAssessmentQuiz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iocl.ImpactAssessmentQuiz.repository.MstQuizQuestionBankRepository;

@Service
public class MstQuizQuestionBankService {

	@Autowired
	MstQuizQuestionBankRepository mstQuizQuestionBankRepository;

	public Long findAvailableQuestions(Long quiz_id) {
		return mstQuizQuestionBankRepository.findAvailableQuestions(quiz_id);
	}

	public Long findTotalModuleQuestions(List<String> module_list) {
		return mstQuizQuestionBankRepository.findTotalModuleQuestions(module_list);
	}

}
