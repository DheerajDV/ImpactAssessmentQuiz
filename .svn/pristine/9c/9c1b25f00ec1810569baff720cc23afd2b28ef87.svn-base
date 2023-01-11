package com.iocl.ImpactAssessmentQuiz.controller;

import java.text.ParseException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.iocl.ImpactAssessmentQuiz.model.EmployeeModel;
import com.iocl.ImpactAssessmentQuiz.model.MstQuizInstrunctions;
import com.iocl.ImpactAssessmentQuiz.model.QuizBean;
import com.iocl.ImpactAssessmentQuiz.model.SessionMaster;
import com.iocl.ImpactAssessmentQuiz.model.TrnQuizHeaderModel;
import com.iocl.ImpactAssessmentQuiz.model.TrnQuizResponseModel;
import com.iocl.ImpactAssessmentQuiz.service.MstQuizInstructionsService;
import com.iocl.ImpactAssessmentQuiz.service.MstQuizQuestionBankService;
import com.iocl.ImpactAssessmentQuiz.service.TrnQuizHeaderService;
import com.iocl.ImpactAssessmentQuiz.service.TrnQuizResponseService;

@Controller
public class TakeQuiz {

	@Autowired
	TrnQuizHeaderService trnQuizHeaderService;

	@Autowired
	MstQuizInstructionsService mstQuizInstructionsService;

	@Autowired
	MstQuizQuestionBankService mstQuizQuestionBankService;

	@Autowired
	TrnQuizResponseService trnQuizResponseService;

	@RequestMapping(value = "/TakeQuiz", method = RequestMethod.GET)
	public ModelAndView takeQuiz(HttpServletRequest request, HttpSession session,
			final RedirectAttributes redirectAttributes) {

		EmployeeModel emp_det = null;
		if (session != null) {
			SessionMaster sessionMaster = new SessionMaster();
			sessionMaster = (SessionMaster) session.getAttribute("sessionMaster");
			emp_det = sessionMaster.getEmployeeModel();
		}
		if (emp_det == null) {
			return new ModelAndView("redirect:/");
		}
		ModelAndView model = new ModelAndView("take_quiz");
		try {

			List<TrnQuizHeaderModel> available_quiz = trnQuizHeaderService.findActiveQuiz(emp_det.getEmp_code());

			model.addObject("available_quiz", available_quiz);
			model.addObject("sysdate", LocalDateTime.now());

			return model;
		} catch (Exception e) {
			System.out.println("Exception in SIAQ :- ");
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("message", "Error occured.");
			return new ModelAndView("redirect:/Home");

		}
	}

	@RequestMapping(value = "/NotAllowed", method = RequestMethod.GET)
	public ModelAndView NotAllowed(HttpServletRequest request, HttpSession session,
			final RedirectAttributes redirectAttributes) {

		EmployeeModel emp_det = null;
		if (session != null) {
			SessionMaster sessionMaster = new SessionMaster();
			sessionMaster = (SessionMaster) session.getAttribute("sessionMaster");
			emp_det = sessionMaster.getEmployeeModel();
		}
		if (emp_det == null) {
			return new ModelAndView("redirect:/");
		}

		ModelAndView model = new ModelAndView("notAllowed");
		return model;
	}

	@RequestMapping(value = "/SessionTimeOut", method = RequestMethod.GET)
	public ModelAndView SessionTimeOut(HttpServletRequest request, HttpSession session,
			final RedirectAttributes redirectAttributes) {

		EmployeeModel emp_det = null;
		if (session != null) {
			SessionMaster sessionMaster = new SessionMaster();
			sessionMaster = (SessionMaster) session.getAttribute("sessionMaster");
			emp_det = sessionMaster.getEmployeeModel();
		}
		if (emp_det == null) {
			return new ModelAndView("redirect:/");
		}

		ModelAndView model = new ModelAndView("session_timeout");
		return model;
	}

	@RequestMapping(value = "/QuizInstructions", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView QuizInstructions(@RequestParam("quiz_id") String quiz_id, HttpServletRequest request,
			HttpSession session, final RedirectAttributes redirectAttributes) {

		EmployeeModel emp_det = null;
		if (session != null) {
			SessionMaster sessionMaster = new SessionMaster();
			sessionMaster = (SessionMaster) session.getAttribute("sessionMaster");
			emp_det = sessionMaster.getEmployeeModel();
		}
		if (emp_det == null) {
			return new ModelAndView("redirect:/");
		}

		ModelAndView model = new ModelAndView("quizInstructions");

		try {

			List<MstQuizInstrunctions> instrctions = mstQuizInstructionsService
					.findDivInstructions(emp_det.getCurrent_divn_cd());
			TrnQuizHeaderModel trnQuizHeaderModel = trnQuizHeaderService.findQuizDetails(Long.valueOf(quiz_id));

			if (trnQuizHeaderModel.getEmployeeModel().getEmp_code().compareTo(emp_det.getEmp_code()) != 0) {
				return new ModelAndView("redirect:/NotAllowed");
			}

			model.addObject("instrctions", instrctions);
			if (trnQuizHeaderModel.getQuiz_taken().contentEquals("Y")) {
				model = new ModelAndView("quizResults");
				model.addObject("trnQuizHeaderModel", trnQuizHeaderModel);

			}

			model.addObject("quiz_id", quiz_id);
			return model;
		} catch (Exception e) {
			System.out.println("Exception in SIAQ :- ");
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("message", "Error occured.");
			return new ModelAndView("redirect:/TakeQuiz");

		}
	}

	@RequestMapping(value = "/StartQuiz", method = RequestMethod.POST)
	public ModelAndView StartQuiz(@RequestParam("quiz_id") String quiz_id, HttpServletRequest request,
			HttpSession session, final RedirectAttributes redirectAttributes) {

		EmployeeModel emp_det = null;
		if (session != null) {
			SessionMaster sessionMaster = new SessionMaster();
			sessionMaster = (SessionMaster) session.getAttribute("sessionMaster");
			emp_det = sessionMaster.getEmployeeModel();
		}
		if (emp_det == null) {
			return new ModelAndView("redirect:/");
		}

		ModelAndView model = new ModelAndView("quizPage");
		try {

			TrnQuizHeaderModel trnQuizHeaderModel = trnQuizHeaderService.findQuizDetails(Long.valueOf(quiz_id));

			if (trnQuizHeaderModel.getQuiz_taken().contentEquals("Y")) {
				model = new ModelAndView("quizResults");
				model.addObject("quiz_id", quiz_id);
				model.addObject("trnQuizHeaderModel", trnQuizHeaderModel);
				return model;
			}
			if (trnQuizHeaderModel.getQuiz_status().contentEquals("I")) {
				model = new ModelAndView("quizResults");
				model.addObject("quiz_id", quiz_id);
				model.addObject("trnQuizHeaderModel", trnQuizHeaderModel);
				return model;
			}

			Long total_questions_available = mstQuizQuestionBankService.findAvailableQuestions(Long.valueOf(quiz_id));

			if (total_questions_available < trnQuizHeaderModel.getTotal_questions()) {
				redirectAttributes.addFlashAttribute("message", "Sufficient Questions not available");
				return new ModelAndView("redirect:/QuizInstructions?quiz_id=" + quiz_id);
			}

			trnQuizHeaderModel.setAttempt_start_time(LocalDateTime.now());
			trnQuizHeaderModel.setQuiz_taken("Y");
			trnQuizHeaderModel.setNo_of_attempts(trnQuizHeaderModel.getNo_of_attempts() + Long.valueOf(1));
			trnQuizHeaderModel.setUpdated_on(LocalDateTime.now());

			trnQuizHeaderService.saveorupdate(trnQuizHeaderModel);

			trnQuizHeaderService.callQuestionSelectionProc(Long.valueOf(quiz_id));

			QuizBean quizBean = new QuizBean();

			Object[][] question_det = trnQuizResponseService.findQuestionNo(Long.valueOf(quiz_id), Long.valueOf(1));
			String[][] option_det = trnQuizResponseService.findQuestionOptions(Long.valueOf(quiz_id),
					(Long) question_det[0][0]);
			String[][] question_response = trnQuizResponseService.findQuestionResponse(Long.valueOf(quiz_id));

			Long selected_seq = Long.valueOf(1);

			quizBean.setQuiz_id(Long.valueOf(quiz_id));
			quizBean.setSelected_seq(selected_seq);
			quizBean.setQuestion_det(question_det);
			quizBean.setOption_det(option_det);
			quizBean.setQuiz_questions(trnQuizHeaderModel.getTotal_questions());
			quizBean.setQuiz_time(trnQuizHeaderModel.getQuiz_duration());
			quizBean.setQues_response(question_response);

			model.addObject("quizBean", quizBean);

			return model;
		} catch (Exception e) {
			System.out.println("Exception in SIAQ :- ");
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("message", "Error occured.");
			return new ModelAndView("redirect:/QuizInstructions?quiz_id=" + quiz_id);

		}
	}

//	@PostMapping("/QuizAction")

	@RequestMapping(value = "/QuizAction", method = RequestMethod.POST)
	public String QuizAction(Model model, @RequestParam("action") String action,
			@RequestParam(value = "quiz_id", required = false) String quiz_id,
			@RequestParam(value = "curr_question_id", required = false) String curr_question_id,
			@RequestParam(value = "curr_question_seq", required = false) String curr_question_seq,
			@RequestParam(value = "curr_option_seq_selected", required = false) String curr_option_seq_selected,
			@RequestParam(value = "move_to_ques_seq", required = false) String move_to_ques_seq,
			HttpServletRequest request, HttpSession session, final RedirectAttributes redirectAttributes) {

		EmployeeModel emp_det = null;
		if (session != null) {
			SessionMaster sessionMaster = new SessionMaster();
			sessionMaster = (SessionMaster) session.getAttribute("sessionMaster");
			emp_det = sessionMaster.getEmployeeModel();
		}
		if (emp_det == null) {
			return ("session_timeout");
		}

		QuizBean quizBean = new QuizBean();
		try {

			TrnQuizHeaderModel trnQuizHeaderModel = trnQuizHeaderService.findQuizDetails(Long.valueOf(quiz_id));

			if (curr_option_seq_selected != null && !curr_option_seq_selected.contentEquals("")
					&& !action.contentEquals("skip")) {
				List<TrnQuizResponseModel> trnQuizResponseModel = trnQuizResponseService
						.findQuestionModel(Long.valueOf(quiz_id), Long.valueOf(curr_question_id));

				for (int i = 0; i < trnQuizResponseModel.size(); i++) {
					if (trnQuizResponseModel.get(i).getTrnQuizResponseID().getOption_seq()
							.contentEquals(curr_option_seq_selected)) {
						trnQuizResponseModel.get(i).setEmp_answer_flg("Y");
						trnQuizResponseModel.get(i).setEmp_response_time(LocalDateTime.now());
						trnQuizResponseService.saveorupdate(trnQuizResponseModel.get(i));
					} else {
						trnQuizResponseModel.get(i).setEmp_answer_flg("N");
						trnQuizResponseModel.get(i).setEmp_response_time(LocalDateTime.now());
						trnQuizResponseService.saveorupdate(trnQuizResponseModel.get(i));

					}

				}
			} else {
				List<TrnQuizResponseModel> trnQuizResponseModel = trnQuizResponseService
						.findQuestionModel(Long.valueOf(quiz_id), Long.valueOf(curr_question_id));
				for (int i = 0; i < trnQuizResponseModel.size(); i++) {
					trnQuizResponseModel.get(i).setUpdated_on(LocalDateTime.now());
					trnQuizResponseService.saveorupdate(trnQuizResponseModel.get(i));
				}
			}

			quizBean = new QuizBean();

			Object[][] question_det = trnQuizResponseService.findQuestionNo(Long.valueOf(quiz_id),
					Long.valueOf(move_to_ques_seq));
			String[][] option_det = trnQuizResponseService.findQuestionOptions(Long.valueOf(quiz_id),
					(Long) question_det[0][0]);
			String[][] question_response = trnQuizResponseService.findQuestionResponse(Long.valueOf(quiz_id));

			Long selected_seq = Long.valueOf(move_to_ques_seq);

			quizBean.setQuiz_id(Long.valueOf(quiz_id));
			quizBean.setSelected_seq(selected_seq);
			quizBean.setQuestion_det(question_det);
			quizBean.setOption_det(option_det);
			quizBean.setQuiz_questions(trnQuizHeaderModel.getTotal_questions());
			quizBean.setQuiz_time(trnQuizHeaderModel.getQuiz_duration());
			quizBean.setQues_response(question_response);

			model.addAttribute("quizBean", quizBean);

//			return quizBean;
			return "quiz::quiz";

		} catch (Exception e) {
			System.out.println("Exception in SIAQ :- ");
			e.printStackTrace();
			return "quiz :: quiz";
//			return "quiz :: quiz(quizBean=" + quizBean + ")";

		}
	}

	@RequestMapping(value = "/FinishQuiz", method = RequestMethod.POST)
	public ModelAndView FinishQuiz(@RequestParam("action") String action,
			@RequestParam(value = "quiz_id", required = false) String quiz_id,
			@RequestParam(value = "curr_question_id", required = false) String curr_question_id,
			@RequestParam(value = "curr_question_seq", required = false) String curr_question_seq,
			@RequestParam(value = "curr_option_seq_selected", required = false) String curr_option_seq_selected,
			@RequestParam(value = "move_to_ques_seq", required = false) String move_to_ques_seq,
			HttpServletRequest request, HttpSession session, final RedirectAttributes redirectAttributes) {
		ModelAndView model = new ModelAndView("quizResults");

		EmployeeModel emp_det = null;
		if (session != null) {
			SessionMaster sessionMaster = new SessionMaster();
			sessionMaster = (SessionMaster) session.getAttribute("sessionMaster");
			emp_det = sessionMaster.getEmployeeModel();
		}
		if (emp_det == null) {
			return new ModelAndView("redirect:/");
		}
		try {

			TrnQuizHeaderModel trnQuizHeaderModel = trnQuizHeaderService.findQuizDetails(Long.valueOf(quiz_id));

			List<TrnQuizResponseModel> trnQuizResponseModel = trnQuizResponseService
					.findQuestionModel(Long.valueOf(quiz_id), Long.valueOf(curr_question_id));
			if (curr_option_seq_selected != null && !curr_option_seq_selected.contentEquals("")) {
				for (int i = 0; i < trnQuizResponseModel.size(); i++) {
					if (trnQuizResponseModel.get(i).getTrnQuizResponseID().getOption_seq()
							.contentEquals(curr_option_seq_selected)) {
						trnQuizResponseModel.get(i).setEmp_answer_flg("Y");
						trnQuizResponseModel.get(i).setEmp_response_time(LocalDateTime.now());
						trnQuizResponseService.saveorupdate(trnQuizResponseModel.get(i));
					} else {
						trnQuizResponseModel.get(i).setEmp_answer_flg("N");
						trnQuizResponseModel.get(i).setEmp_response_time(LocalDateTime.now());
						trnQuizResponseService.saveorupdate(trnQuizResponseModel.get(i));

					}

				}
			}

			Long employee_score = trnQuizResponseService.getEmployeeScore(Long.valueOf(quiz_id));
			Long total_attempted = trnQuizResponseService.getTotalAttemptedQues(Long.valueOf(quiz_id));
			Long total_questions = trnQuizResponseService.getTotalQuizQues(Long.valueOf(quiz_id));
			Long total_skipped = total_questions - total_attempted;
			Long total_correct = trnQuizResponseService.getTotalCorrectQues(Long.valueOf(quiz_id));
			Long total_wrong = trnQuizResponseService.getTotalWrong(Long.valueOf(quiz_id));

			trnQuizHeaderModel.setEmployee_score(employee_score);
			trnQuizHeaderModel.setTotal_attempted(total_attempted);
			trnQuizHeaderModel.setTotal_skipped(total_skipped);
			trnQuizHeaderModel.setTotal_correct(total_correct);
			trnQuizHeaderModel.setTotal_wrong(total_wrong);
			trnQuizHeaderModel.setAttempt_end_time(LocalDateTime.now());
			trnQuizHeaderModel.setUpdated_on(LocalDateTime.now());

			if (action.contentEquals("TimeUp")) {
				trnQuizHeaderModel.setAuto_submit_flg("Y");

			}

			trnQuizHeaderService.saveorupdate(trnQuizHeaderModel);

			model.addObject("quiz_id", quiz_id);
			model.addObject("action", "finish");
			model.addObject("trnQuizHeaderModel", trnQuizHeaderModel);
			return model;

		} catch (Exception e) {
			System.out.println("Exception in SIAQ :- ");
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("message", "Error occured.");
			return new ModelAndView("redirect:/QuizInstructions?quiz_id=" + quiz_id);

		}
	}

//	@RequestMapping(value = "/QuizResult", method = { RequestMethod.GET, RequestMethod.POST })
//	public ModelAndView QuizResultView(@RequestParam(value = "quiz_id", required = false) String quiz_id,
//			HttpServletRequest request, HttpSession session, final RedirectAttributes redirectAttributes) {
//
//		ModelAndView model = new ModelAndView("quizResults");
//		try {
//
//			TrnQuizHeaderModel trnQuizHeaderModel = trnQuizHeaderService.findQuizDetails(Long.valueOf(quiz_id));
//			model = new ModelAndView("quizResults");
//			model.addObject("trnQuizHeaderModel", trnQuizHeaderModel);
//			model.addObject("quiz_id", quiz_id);
//			return model;
//		} catch (Exception e) {
//			System.out.println("Exception in SIAQ :- ");
//			e.printStackTrace();
//			redirectAttributes.addFlashAttribute("message", "Error occured.");
//			return new ModelAndView("redirect:/QuizInstructions?quiz_id=" + quiz_id);
//
//		}
//	}

	@PostMapping("/checkQuizStatus")
	public @ResponseBody String checkQuizStatus(@RequestParam(value = "quiz_id", required = true) String quiz_id,
			HttpSession session) throws ParseException {

		TrnQuizHeaderModel trnQuizHeaderModel = trnQuizHeaderService.findQuizDetails(Long.valueOf(quiz_id));
		return trnQuizHeaderModel.getQuiz_taken();

	}

	@PostMapping("/checkQuizExpiryStatus")
	public @ResponseBody String checkQuizExpiryStatus(@RequestParam(value = "quiz_id", required = true) String quiz_id,
			HttpSession session) throws ParseException {
		TrnQuizHeaderModel trnQuizHeaderModel = trnQuizHeaderService.findQuizDetails(Long.valueOf(quiz_id));
		if (LocalDateTime.now().isAfter(trnQuizHeaderModel.getQuiz_end_time())
				|| LocalDateTime.now().isBefore(trnQuizHeaderModel.getQuiz_start_time())) {
			return "Y";
		}
		if (trnQuizHeaderModel.getQuiz_status().contentEquals("I")) {
			return "Y";
		}
		return "N";

	}

	@PostMapping("/checkRemainingTimeStatus")
	public @ResponseBody String checkRemainingTimeStatus(
			@RequestParam(value = "quiz_id", required = true) String quiz_id, HttpSession session)
			throws ParseException {
		TrnQuizHeaderModel trnQuizHeaderModel = trnQuizHeaderService.findQuizDetails(Long.valueOf(quiz_id));

		LocalDateTime quiz_attempt_start_time = trnQuizHeaderModel.getAttempt_start_time();
		LocalDateTime system_time = LocalDateTime.now();

		Long quiz_time_minutes = trnQuizHeaderModel.getQuiz_duration();

		Duration time_elapsed = Duration.between(quiz_attempt_start_time, system_time);
		Long time_elapsed_minutes = time_elapsed.toMinutes();

		if (time_elapsed_minutes < quiz_time_minutes) {
			return "Y";
		} else {
			return "N";
		}

	}

	@PostMapping("/FetchQuizStat")
	public @ResponseBody String FetchQuizStat(@RequestParam(value = "quiz_id", required = true) String quiz_id,
			HttpSession session) throws ParseException {

		Long total_attempted = trnQuizResponseService.getTotalAttemptedQues(Long.valueOf(quiz_id));
		Long total_questions = trnQuizResponseService.getTotalQuizQues(Long.valueOf(quiz_id));
		Long total_skipped = total_questions - total_attempted;

		return "No. of Questions : " + total_questions + " ; No. of attempted questions : " + total_attempted
				+ " ; No. of un-attempted questions : " + total_skipped + "\r\n"
				+ "Do you wish to Submit and Finish the quiz ? \r\n";

	}

}
