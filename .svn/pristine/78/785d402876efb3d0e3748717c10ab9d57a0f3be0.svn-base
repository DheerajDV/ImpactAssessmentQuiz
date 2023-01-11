package com.iocl.ImpactAssessmentQuiz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iocl.ImpactAssessmentQuiz.model.TrnAutoMailModel;
import com.iocl.ImpactAssessmentQuiz.repository.TrnAutoMailRepository;

@Service
public class TrnAutoMailService {

	@Autowired
	TrnAutoMailRepository trnAutoMailRepository;

	public Long getMessageID() {
		return trnAutoMailRepository.getMessageID();

	}

	public void saveorupdateAll(List<TrnAutoMailModel> trnAutoMailModel) {
		trnAutoMailRepository.saveAll(trnAutoMailModel);

	}

}
