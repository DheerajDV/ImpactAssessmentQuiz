package com.iocl.ImpactAssessmentQuiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iocl.ImpactAssessmentQuiz.repository.MstTemplateRepository;

@Service
public class MstTemplateService {

	@Autowired
	MstTemplateRepository mstTemplateRepository;

}
