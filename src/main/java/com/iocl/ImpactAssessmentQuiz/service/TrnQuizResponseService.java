package com.iocl.ImpactAssessmentQuiz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iocl.ImpactAssessmentQuiz.model.TrnQuizResponseModel;
import com.iocl.ImpactAssessmentQuiz.repository.TrnQuizResponseRepository;

@Service
public class TrnQuizResponseService {

	@Autowired
	TrnQuizResponseRepository trnQuizResponseRepository;

	public List<TrnQuizResponseModel> findQuizQuestionNo(Long quiz_id) {
		return trnQuizResponseRepository.findQuizQuestions(quiz_id);
	}

	public Object[][] findQuestionNo(Long quiz_id, Long seq_no) {
		return trnQuizResponseRepository.findQuestionNo(quiz_id, seq_no);
	}

	public String[][] findQuestionOptions(Long quiz_id, Long question_id) {
		return trnQuizResponseRepository.findQuestionOptions(quiz_id, question_id);

	}

	public String[][] findQuestionResponse(Long quiz_id) {
		return trnQuizResponseRepository.findQuestionResponse(quiz_id);

	}

	public List<TrnQuizResponseModel> findQuestionModel(Long quiz_id, Long question_id) {
		return trnQuizResponseRepository.findQuestionModel(quiz_id, question_id);
	}

	public void saveorupdate(TrnQuizResponseModel trnQuizResponseModel) {
		trnQuizResponseRepository.save(trnQuizResponseModel);

	}

	public Long getEmployeeScore(Long quiz_id) {
		return trnQuizResponseRepository.getEmployeeScore(quiz_id);
	}

	public Long getTotalAttemptedQues(Long quiz_id) {
		return trnQuizResponseRepository.getTotalAttemptedQues(quiz_id);
	}

	public Long getTotalCorrectQues(Long quiz_id) {
		return trnQuizResponseRepository.getTotalCorrectQues(quiz_id);
	}

	public Long getTotalWrong(Long quiz_id) {
		return trnQuizResponseRepository.getTotalWrong(quiz_id);
	}

	public Long getTotalQuizQues(Long quiz_id) {
		return trnQuizResponseRepository.getTotalQuizQues(quiz_id);

	}

}
