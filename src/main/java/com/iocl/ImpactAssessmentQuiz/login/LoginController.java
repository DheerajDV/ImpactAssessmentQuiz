package com.iocl.ImpactAssessmentQuiz.login;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.iocl.ImpactAssessmentQuiz.model.*;
import com.iocl.ImpactAssessmentQuiz.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.iocl.ImpactAssessmentQuiz.bean.Function_wise_course;

@Controller
@Scope("session")
public class LoginController {

//	@Value("${rsa.public}")
//	private String magicalWord;

//	@Autowired
//	AuthenticationManager authenticationManager;

	@Autowired
	EmployeeService employeeService;

	@Autowired
	LoginService loginService;

	@Autowired
	MstAdminService mstAdminService;
	@Autowired
	MstDivisionListService mstDivisionListService;
	@Autowired
	MstActivityListService mstActivityListService;

	@Autowired
	MstUsageListService mstUsageListService;

	@ModelAttribute("sessionMaster")
	public SessionMaster setUpSessionMaster() {
		return new SessionMaster();
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(@RequestParam("uname") String uname, @RequestParam("upass") String upass,
							  @RequestParam("captchaText") String captchaText, HttpServletRequest request,

							  @SessionAttribute("sessionMaster") SessionMaster sessionMaster, HttpSession session,
							  final RedirectAttributes redirectAttributes) {

		System.out.println("Employee iD :- " + uname);

		ArrayList<MstDivisionListModel> learning_group = new ArrayList<MstDivisionListModel>();
		ArrayList<ArrayList<String>> func_group = new ArrayList<ArrayList<String>>();

		try {
			String capAns = sessionMaster.getCaptchaAnswer();
			System.out.println("Captcha :- " + captchaText);
			long authId = 0L;

			try {
				authId = Long.parseLong(uname);

			} catch (Exception ee) {
				authId = 0L;
			}
			ADAuthentication authenticate = new ADAuthentication();
			boolean isValidUser = authenticate.Authenticate(uname, upass);

//			Authentication authentication = null;
//			try {
//				authentication = authenticationManager
//						.authenticate(new UsernamePasswordAuthenticationToken(uname, upass));
//			} catch (Exception e) {
//				e.printStackTrace();
//				redirectAttributes.addFlashAttribute("message", "Invalid Credentials");
//				return new ModelAndView("redirect:/");
//			}

			isValidUser = true;
			String learning_group_flg = "";
//			if (authentication != null && authentication.isAuthenticated()) {
			if (isValidUser == true) {
				System.out.println("Authentication Successful");
				EmployeeModel employee = loginService.findEmpDetails(authId);
				MstDivisionListModel mstDivisionListModel = mstDivisionListService.userDivisionmasterDetails(uname);

				long mstActivityListModel_list_count = mstActivityListService.count();
				learning_group = mstDivisionListService.userDivisionmasterDetailsbyManager(uname);
				func_group = mstUsageListService.func_wise_course_list(uname);

				ArrayList<Function_wise_course> function_wise_course_list = new ArrayList<Function_wise_course>();

				for (int k = 0; k < func_group.size(); k++) {
					System.out.println(func_group.get(k).get(0) + " " + func_group.get(k).get(1));
					Function_wise_course function_wise_course = new Function_wise_course();
					function_wise_course.setDomain(func_group.get(k).get(0));
					function_wise_course.setNo_of_courses(Integer.parseInt(func_group.get(k).get(1)));

					function_wise_course_list.add(function_wise_course);

				}

				if (employee != null) {
					MstAdminModel mstAdminModel = mstAdminService.findOne(employee.getEmp_code());
					if (mstAdminModel != null) {
						sessionMaster.setAdmin_flg("Y");
					} else {
						sessionMaster.setAdmin_flg("N");
					}

					if (capAns.equals(captchaText)) {
						sessionMaster.setMst_activity_list_count(mstActivityListModel_list_count);
						sessionMaster.setEmployeeModel(employee);
						sessionMaster.setMstDivisionListModel(mstDivisionListModel);
						System.out.println(mstDivisionListModel);
						sessionMaster.setLearning_group(learning_group);
						if (sessionMaster.getLearning_group().size() > 1) {
							learning_group_flg = "Y";
						}
						sessionMaster.setLearning_group_flg(learning_group_flg);
						sessionMaster.setFunction_wise_course_list(function_wise_course_list);
						sessionMaster.setHost(request.getServerName());
						session.setAttribute("sessionMaster", sessionMaster);
						session.setMaxInactiveInterval(30 * 60);
						/* return "redirect:/Home"; */
//						return "dashboard_home";
						return new ModelAndView("redirect:/Home");

					} else {
						redirectAttributes.addFlashAttribute("message", "Invalid Captcha");
					}
				} else {
					redirectAttributes.addFlashAttribute("message", "Not Authorised");
				}
			} else {
				redirectAttributes.addFlashAttribute("message", "Invalid Credentials");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/");
	}

}
