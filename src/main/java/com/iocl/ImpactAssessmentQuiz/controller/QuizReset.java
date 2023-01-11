package com.iocl.ImpactAssessmentQuiz.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.iocl.ImpactAssessmentQuiz.model.EmployeeModel;
import com.iocl.ImpactAssessmentQuiz.model.MstAdminModel;
import com.iocl.ImpactAssessmentQuiz.model.SessionMaster;
import com.iocl.ImpactAssessmentQuiz.model.TrnQuizHeaderModel;
import com.iocl.ImpactAssessmentQuiz.service.EmployeeService;
import com.iocl.ImpactAssessmentQuiz.service.MstAdminService;
import com.iocl.ImpactAssessmentQuiz.service.TrnQuizHeaderService;

@Controller
public class QuizReset {

	@Autowired
	TrnQuizHeaderService trnQuizHeaderService;

	@Autowired
	MstAdminService mstAdminService;

	@Autowired
	EmployeeService employeeService;

	@RequestMapping(value = "/QuizReset", method = RequestMethod.GET)
	public ModelAndView QuizResultHeader(HttpServletRequest request, HttpSession session,
			final RedirectAttributes redirectAttributes) {
		if (!request.isRequestedSessionIdValid()) {
			return new ModelAndView("redirect:/");
		}
		try {
			ModelAndView model = new ModelAndView("quizReset");

			return model;
		} catch (Exception e) {
			System.out.println("Exception in SIAQ :- ");
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("errorMsg", "Error occured.");
			return new ModelAndView("redirect:/Home");

		}

	}

	@RequestMapping(value = "/fecthQuizList", method = RequestMethod.POST)
	@ResponseBody
	public List<TrnQuizHeaderModel> fecthQuizList(
			@RequestParam(value = "employee_code", required = true) String emp_code, HttpServletRequest request,
			HttpSession session, final RedirectAttributes redirectAttributes) {
		List<TrnQuizHeaderModel> quiz_det = null;
		try {

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

			if (mstAdminModel != null && mstAdminModel.getDiv_code().contentEquals("*")) {
				quiz_det = trnQuizHeaderService.findQuizListForReset(Long.valueOf(emp_code));
			} else if (mstAdminModel != null && !mstAdminModel.getDiv_code().contentEquals("*")) {

				if (mstAdminModel.getDiv_code().contentEquals("3") && !comp_code.contentEquals("100")) {
					quiz_det = trnQuizHeaderService.findDivQuizListForResetCompCode(Long.valueOf(emp_code),
							mstAdminModel.getDiv_code(), allowed_comp_code);
				} else {
					quiz_det = trnQuizHeaderService.findDivQuizListForReset(Long.valueOf(emp_code),
							mstAdminModel.getDiv_code());
				}

			}
			return quiz_det;
		} catch (Exception e) {
			System.out.println("Exception in SIAQ :- ");
			e.printStackTrace();
			return quiz_det;

		}

	}

	@RequestMapping(value = "/fecthQuizDetails", method = RequestMethod.POST)
	@ResponseBody
	public Object[][] fecthQuizDetails(@RequestParam(value = "quiz_id", required = true) String quiz_id,
			HttpServletRequest request, HttpSession session, final RedirectAttributes redirectAttributes) {
		Object[][] quiz_det = null;
		try {
			quiz_det = trnQuizHeaderService.findQuizDet(Long.valueOf(quiz_id));

			return quiz_det;
		} catch (Exception e) {
			System.out.println("Exception in SIAQ :- ");
			e.printStackTrace();
			return quiz_det;

		}

	}

	@RequestMapping(value = "/ResetQuiz", method = RequestMethod.POST)
	public ModelAndView ResetQuiz(@RequestParam(value = "quiz_id", required = true) String quiz_id,
			@RequestParam(value = "quiz_retain_flg", required = true) String quiz_retain_flg,
			HttpServletRequest request, HttpSession session, final RedirectAttributes redirectAttributes) {
		try {

			Long new_quiz_id = trnQuizHeaderService.getQuizID();

			TrnQuizHeaderModel quiz_data = trnQuizHeaderService.findQuizDetails(new_quiz_id);
			if (quiz_data != null) {
				redirectAttributes.addFlashAttribute("message", "Quiz reset not allowed.");
				return new ModelAndView("redirect:/QuizReset");
			}

			EmployeeModel emp_det = null;
			if (session != null) {
				SessionMaster sessionMaster = new SessionMaster();
				sessionMaster = (SessionMaster) session.getAttribute("sessionMaster");
				emp_det = sessionMaster.getEmployeeModel();
			}
			try {
				trnQuizHeaderService.callQuizResetProc(Long.valueOf(quiz_id), new_quiz_id, emp_det.getEmp_code(),
						quiz_retain_flg);
			} catch (Exception e) {
				redirectAttributes.addFlashAttribute("message", "Error occured in resetting quiz");
				return new ModelAndView("redirect:/QuizReset");
			}

			redirectAttributes.addFlashAttribute("message",
					"New Quiz created with quiz id : " + String.valueOf(new_quiz_id));
			return new ModelAndView("redirect:/QuizReset");
		} catch (Exception e) {
			System.out.println("Exception in SIAQ :- ");
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("errorMsg", "Error occured.");
			return new ModelAndView("redirect:/QuizReset");

		}

	}

}
