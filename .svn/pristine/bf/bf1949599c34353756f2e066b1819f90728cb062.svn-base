package com.iocl.ImpactAssessmentQuiz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iocl.ImpactAssessmentQuiz.model.MstQuizInstrunctions;
import com.iocl.ImpactAssessmentQuiz.repository.MstQuizInstructionsRepository;

@Service
public class MstQuizInstructionsService {

	@Autowired
	MstQuizInstructionsRepository mstQuizInstructionsRepository;

	public List<MstQuizInstrunctions> findAll() {
		return (List<MstQuizInstrunctions>) mstQuizInstructionsRepository.getInstructions();
	}

	public List<MstQuizInstrunctions> findDivInstructions(String current_divn_cd) {
		return (List<MstQuizInstrunctions>) mstQuizInstructionsRepository.getInstructions(current_divn_cd);
	}

}
