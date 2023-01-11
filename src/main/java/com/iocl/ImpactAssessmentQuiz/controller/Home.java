package com.iocl.ImpactAssessmentQuiz.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.iocl.ImpactAssessmentQuiz.model.EmployeeModel;
import com.iocl.ImpactAssessmentQuiz.model.SessionMaster;

@Controller
public class Home {

	@RequestMapping(value = "/Home", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request, HttpSession session) {
		EmployeeModel emp_det = null;
		String admin_flg = "";
		
		if (session != null) {
			SessionMaster sessionMaster = new SessionMaster();
			sessionMaster = (SessionMaster) session.getAttribute("sessionMaster");
			admin_flg = sessionMaster.getAdmin_flg();
			emp_det = sessionMaster.getEmployeeModel();

		}
		if (emp_det == null) {
			return new ModelAndView("redirect:/");
		}
		if (admin_flg != null && admin_flg.contentEquals("Y")) {
			return new ModelAndView("dashboard_home");
		}

		else {
			return new ModelAndView("dashboard_home");

		}
	}

}
