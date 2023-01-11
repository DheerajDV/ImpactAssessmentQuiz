package com.iocl.ImpactAssessmentQuiz.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.iocl.ImpactAssessmentQuiz.model.EmployeeModel;
import com.iocl.ImpactAssessmentQuiz.model.MstAdminModel;
import com.iocl.ImpactAssessmentQuiz.model.SessionMaster;
import com.iocl.ImpactAssessmentQuiz.model.TrnQuizResultDetails;
import com.iocl.ImpactAssessmentQuiz.model.TrnQuizResultHeader;
import com.iocl.ImpactAssessmentQuiz.service.EmployeeService;
import com.iocl.ImpactAssessmentQuiz.service.MstAdminService;
import com.iocl.ImpactAssessmentQuiz.service.TrnQuizHeaderService;
import com.iocl.ImpactAssessmentQuiz.service.TrnQuizResultDetailsService;
import com.iocl.ImpactAssessmentQuiz.service.TrnQuizResultHeaderService;

@Controller
public class QuizResult {
	@Autowired
	TrnQuizResultDetailsService trnQuizResultDetailsService;

	@Autowired
	TrnQuizResultHeaderService trnQuizResultHeaderService;

	@Autowired
	TrnQuizHeaderService trnQuizHeaderService;

	@Autowired
	MstAdminService mstAdminService;

	@Autowired
	EmployeeService employeeService;

	@RequestMapping(value = "/QuizResultHeader", method = RequestMethod.GET)
	public ModelAndView QuizResultHeader(HttpServletRequest request, HttpSession session,
			final RedirectAttributes redirectAttributes) {
		if (!request.isRequestedSessionIdValid()) {
			return new ModelAndView("redirect:/");
		}
		try {
			ModelAndView model = new ModelAndView("quizResultHeader");

			return model;
		} catch (Exception e) {
			System.out.println("Exception in SIAQ :- ");
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("errorMsg", "Error occured.");
			return new ModelAndView("redirect:/Home");

		}

	}

	@RequestMapping(value = "/QuizHeaderJson", method = RequestMethod.GET)
	@ResponseBody
	public List<TrnQuizResultHeader> QuizHeaderJson(HttpServletRequest request, HttpSession session,
			final RedirectAttributes redirectAttributes) {

		EmployeeModel emp_det = null;
		if (session != null) {
			SessionMaster sessionMaster = new SessionMaster();
			sessionMaster = (SessionMaster) session.getAttribute("sessionMaster");
			emp_det = sessionMaster.getEmployeeModel();
		}
		String comp_code = "";
		List<String> allowed_comp_code = new ArrayList<String>();

		comp_code = emp_det.getCurr_comp_code();
		allowed_comp_code = employeeService.findSOMapping(comp_code);
		if (allowed_comp_code.isEmpty()) {
			allowed_comp_code.add(comp_code);
		}

		List<TrnQuizResultHeader> data = null;

		MstAdminModel mstAdminModel = mstAdminService.findOne(emp_det.getEmp_code());
		List<String> div_code = new ArrayList<String>();
		div_code.add(mstAdminModel.getDiv_code());
		if (mstAdminModel.getDiv_code().contentEquals("1")) {
			div_code.add("5");
			div_code.add("9");
		}

		if (mstAdminModel != null && (mstAdminModel.getDiv_code()).contentEquals("*")) {
			data = trnQuizResultHeaderService.findAll();
		} else if (mstAdminModel != null && !(mstAdminModel.getDiv_code()).contentEquals("*")) {
			if (mstAdminModel.getDiv_code().contentEquals("3") && !comp_code.contentEquals("100")) {
				data = trnQuizResultHeaderService.findDivisionDataCompCode(div_code, allowed_comp_code);
			} else {
				data = trnQuizResultHeaderService.findDivisionData(div_code);
			}
		}

		return data;

	}

	@RequestMapping(value = "/QuizDetailsJson", method = RequestMethod.GET)
	@ResponseBody
	public List<TrnQuizResultDetails> QuizDetailsJson(HttpServletRequest request, HttpSession session,
			final RedirectAttributes redirectAttributes) {

		EmployeeModel emp_det = null;
		if (session != null) {
			SessionMaster sessionMaster = new SessionMaster();
			sessionMaster = (SessionMaster) session.getAttribute("sessionMaster");
			emp_det = sessionMaster.getEmployeeModel();
		}

		String comp_code = "";
		List<String> allowed_comp_code = new ArrayList<String>();

		comp_code = emp_det.getCurr_comp_code();
		allowed_comp_code = employeeService.findSOMapping(comp_code);
		if (allowed_comp_code.isEmpty()) {
			allowed_comp_code.add(comp_code);
		}

		MstAdminModel mstAdminModel = mstAdminService.findOne(emp_det.getEmp_code());
		List<TrnQuizResultDetails> data = null;
		List<String> div_code = new ArrayList<String>();
		div_code.add(mstAdminModel.getDiv_code());
		if (mstAdminModel.getDiv_code().contentEquals("1")) {
			div_code.add("5");
			div_code.add("9");
		}
		if (mstAdminModel != null && (mstAdminModel.getDiv_code()).contentEquals("*")) {
			data = trnQuizResultDetailsService.findAll();
		} else if (mstAdminModel != null && !(mstAdminModel.getDiv_code()).contentEquals("*")) {

			if (mstAdminModel.getDiv_code().contentEquals("3") && !comp_code.contentEquals("100")) {
				data = trnQuizResultDetailsService.findDivisionDataCompCode(div_code, allowed_comp_code);
			} else {
				data = trnQuizResultDetailsService.findDivisionData(div_code);
			}

		}

		return data;

	}

	@RequestMapping(value = "/QuizResultDetails", method = RequestMethod.GET)
	public ModelAndView QuizResultDetails(HttpServletRequest request, HttpSession session,
			final RedirectAttributes redirectAttributes) {
		if (!request.isRequestedSessionIdValid()) {
			return new ModelAndView("redirect:/");
		}
		try {
			ModelAndView model = new ModelAndView("quizResultDetails");

			return model;
		} catch (Exception e) {
			System.out.println("Exception in SIAQ :- ");
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("errorMsg", "Error occured.");
			return new ModelAndView("redirect:/Home");

		}

	}

	@RequestMapping(value = "/GenerateResult", method = RequestMethod.POST)
	@ResponseBody
	public String GenerateResult(HttpServletRequest request, HttpSession session,
			final RedirectAttributes redirectAttributes) {
		try {
			trnQuizHeaderService.callGenerateResultProc();
			return "Y";
		} catch (Exception e) {
			return "N";

		}

	}
	
}
