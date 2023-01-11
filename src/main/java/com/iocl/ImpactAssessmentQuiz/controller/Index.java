package com.iocl.ImpactAssessmentQuiz.controller;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.iocl.ImpactAssessmentQuiz.model.SessionMaster;

@Controller
@Scope("session")
@SessionAttributes("sessionMaster")
public class Index {

	@ModelAttribute("sessionMaster")
	public SessionMaster setUpSessionMaster() {
		return new SessionMaster();
	}

	@RequestMapping("")
	public ModelAndView loginView() {
		return new ModelAndView("employeeLoginPage");
	}

}
