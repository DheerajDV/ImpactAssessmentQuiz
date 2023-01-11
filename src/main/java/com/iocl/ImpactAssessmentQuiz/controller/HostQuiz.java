package com.iocl.ImpactAssessmentQuiz.controller;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.iocl.ImpactAssessmentQuiz.bean.EmployeeUploadBean;
import com.iocl.ImpactAssessmentQuiz.bean.ModuleUploadBean;
import com.iocl.ImpactAssessmentQuiz.model.EmployeeModel;
import com.iocl.ImpactAssessmentQuiz.model.MstActivityListModel;
import com.iocl.ImpactAssessmentQuiz.model.MstAdminModel;
import com.iocl.ImpactAssessmentQuiz.model.SessionMaster;
import com.iocl.ImpactAssessmentQuiz.service.EmployeeService;
import com.iocl.ImpactAssessmentQuiz.service.MstActivityListService;
import com.iocl.ImpactAssessmentQuiz.service.MstAdminService;
import com.iocl.ImpactAssessmentQuiz.service.MstQuizQuestionBankService;
import com.iocl.ImpactAssessmentQuiz.service.TrnQuizEventService;
import com.iocl.ImpactAssessmentQuiz.service.TrnQuizHeaderService;

@Controller
public class HostQuiz {

	@Autowired
	MstActivityListService mstActivityListService;

	@Autowired
	EmployeeService employeeService;

	@Autowired
	MstAdminService mstAdminService;

	@Autowired
	TrnQuizEventService trnQuizEventService;

	@Autowired
	TrnQuizHeaderService trnQuizHeaderService;

	@Autowired
	MstQuizQuestionBankService mstQuizQuestionBankService;

	@RequestMapping(value = "/HostQuiz", method = RequestMethod.GET)
	public ModelAndView hostQuiz(HttpServletRequest request, HttpSession session,
			final RedirectAttributes redirectAttributes) {
		if (!request.isRequestedSessionIdValid()) {
			return new ModelAndView("redirect:/");
		}
		try {

			EmployeeModel emp_det = null;
			if (session != null) {
				SessionMaster sessionMaster = new SessionMaster();
				sessionMaster = (SessionMaster) session.getAttribute("sessionMaster");
				emp_det = sessionMaster.getEmployeeModel();
			}

			ModelAndView model = new ModelAndView("hostQuiz");
			ArrayList<String> division_list = mstActivityListService.getDivisionList();

			MstAdminModel mstAdminModel = mstAdminService.findOne(emp_det.getEmp_code());
			ArrayList<ArrayList<String>> admin_division_list = null;
			List<String> div_code = new ArrayList<String>();
			div_code.add(mstAdminModel.getDiv_code());
			if (mstAdminModel.getDiv_code().contentEquals("1")) {
				div_code.add("5");
				div_code.add("9");
			}
			if (mstAdminModel.getDiv_code().contentEquals("*")) {
				admin_division_list = employeeService.getAllDivision();
			} else {
				admin_division_list = employeeService.getAdminDivision(div_code);
			}

			model.addObject("division_list", division_list);
			model.addObject("admin_division_list", admin_division_list);
			return model;
		} catch (Exception e) {
			System.out.println("Exception in SIAQ :- ");
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("errorMsg", "Error occured.");
			return new ModelAndView("redirect:/Home");

		}

	}

	@ResponseBody
	@RequestMapping(value = "/fecthDomainList", method = RequestMethod.POST)
	public ArrayList<String> fecthDomainList(@RequestParam("division") String division, HttpServletRequest request,
			HttpSession session, final RedirectAttributes redirectAttributes) {
		ArrayList<String> domain_list = new ArrayList<String>();
		try {
			domain_list = mstActivityListService.getDomainList(division);

			return domain_list;
		} catch (Exception e) {
			System.out.println("Exception in SIAQ :- ");
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("message", "Error occured.");
			return domain_list;

		}

	}

	@ResponseBody
	@RequestMapping(value = "/fecthSubDomainList", method = RequestMethod.POST)
	public ArrayList<String> fecthSubDomainList(@RequestParam("division") String division,
			@RequestParam("domain") String domain, HttpServletRequest request, HttpSession session,
			final RedirectAttributes redirectAttributes) {
		ArrayList<String> sub_domain_list = new ArrayList<String>();
		try {
			sub_domain_list = mstActivityListService.getSubDomainList(division, domain);

			return sub_domain_list;
		} catch (Exception e) {
			System.out.println("Exception in SIAQ :- ");
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("message", "Error occured.");
			return sub_domain_list;

		}

	}

	@ResponseBody
	@RequestMapping(value = "/fecthModuleList", method = RequestMethod.POST)
	public List<MstActivityListModel> fecthModuleList(@RequestParam("division") String division,
			@RequestParam("domain") String domain, @RequestParam("sub_domain") String sub_domain,
			HttpServletRequest request, HttpSession session, final RedirectAttributes redirectAttributes) {
		List<MstActivityListModel> module_list = new ArrayList<MstActivityListModel>();
		try {
			module_list = mstActivityListService.getModuleList(division, domain, sub_domain);

			return module_list;
		} catch (Exception e) {
			System.out.println("Exception in SIAQ :- ");
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("message", "Error occured.");
			return module_list;

		}

	}

	@ResponseBody
	@RequestMapping(value = "/fecthCompanyCodeList", method = RequestMethod.POST)
	public ArrayList<ArrayList<String>> fecthCompanyCodeList(@RequestParam("division") String division,
			HttpServletRequest request, HttpSession session, final RedirectAttributes redirectAttributes) {
		ArrayList<ArrayList<String>> company_code_list = new ArrayList<ArrayList<String>>();
		try {
			EmployeeModel emp_det = null;
			session = request.getSession(false);
			if (session != null) {
				SessionMaster sessionMaster = new SessionMaster();
				sessionMaster = (SessionMaster) session.getAttribute("sessionMaster");
				long empcode = sessionMaster.getEmployeeModel().getEmp_code();
				emp_det = employeeService.findEmpDetails(empcode);
			}

			String comp_code = "";
			List<String> allowed_comp_code = new ArrayList<String>();
			if (emp_det != null) {
				comp_code = emp_det.getCurr_comp_code();
				allowed_comp_code = employeeService.findSOMapping(comp_code);
				if (allowed_comp_code.isEmpty()) {
					allowed_comp_code.add(comp_code);
				}
				if (comp_code.contentEquals("100")) {
					company_code_list = employeeService.getCompanyCodeList(division);
				} else {
					company_code_list = employeeService.getCompanyCodeListLocation(division, allowed_comp_code);
				}
			}

			return company_code_list;
		} catch (Exception e) {
			System.out.println("Exception in SIAQ :- ");
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("message", "Error occured.");
			return company_code_list;

		}

	}

	@ResponseBody
	@RequestMapping(value = "/fecthLocationCodeList", method = RequestMethod.POST)
	public ArrayList<ArrayList<String>> fecthLocationCodeList(@RequestParam("division") String division,
			@RequestParam("company_code") String company_code, HttpServletRequest request, HttpSession session,
			final RedirectAttributes redirectAttributes) {
		ArrayList<ArrayList<String>> location_list = new ArrayList<ArrayList<String>>();
		try {

			EmployeeModel emp_det = null;
			session = request.getSession(false);
			if (session != null) {
				SessionMaster sessionMaster = new SessionMaster();
				sessionMaster = (SessionMaster) session.getAttribute("sessionMaster");
				long empcode = sessionMaster.getEmployeeModel().getEmp_code();
				emp_det = employeeService.findEmpDetails(empcode);
			}

			String comp_code = "";
			List<String> allowed_comp_code = new ArrayList<String>();
			if (emp_det != null) {
				comp_code = emp_det.getCurr_comp_code();
				allowed_comp_code = employeeService.findSOMapping(comp_code);
				if (allowed_comp_code.isEmpty()) {
					allowed_comp_code.add(comp_code);
				}
				if (comp_code.contentEquals("100")) {
					location_list = employeeService.getLocationList(division, company_code);
				} else {
					location_list = employeeService.getLocationListLocation(division, company_code, allowed_comp_code);
				}
			}

			return location_list;
		} catch (Exception e) {
			System.out.println("Exception in SIAQ :- ");
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("message", "Error occured.");
			return location_list;

		}

	}

	@ResponseBody
	@RequestMapping(value = "/fecthPsaCodeList", method = RequestMethod.POST)
	public ArrayList<ArrayList<String>> fecthPsaCodeList(@RequestParam("division") String division,
			@RequestParam("company_code") String company_code, @RequestParam("loc_code") String loc_code,
			HttpServletRequest request, HttpSession session, final RedirectAttributes redirectAttributes) {
		ArrayList<ArrayList<String>> psa_list = new ArrayList<ArrayList<String>>();
		try {

			EmployeeModel emp_det = null;
			session = request.getSession(false);
			if (session != null) {
				SessionMaster sessionMaster = new SessionMaster();
				sessionMaster = (SessionMaster) session.getAttribute("sessionMaster");
				long empcode = sessionMaster.getEmployeeModel().getEmp_code();
				emp_det = employeeService.findEmpDetails(empcode);
			}

			String comp_code = "";
			List<String> allowed_comp_code = new ArrayList<String>();
			if (emp_det != null) {
				comp_code = emp_det.getCurr_comp_code();
				allowed_comp_code = employeeService.findSOMapping(comp_code);
				if (allowed_comp_code.isEmpty()) {
					allowed_comp_code.add(comp_code);
				}
				if (comp_code.contentEquals("100")) {
					psa_list = employeeService.getPsaCodeList(division, company_code, loc_code);
				} else {
					psa_list = employeeService.getPsaCodeListLocation(division, company_code, loc_code,
							allowed_comp_code);
				}
			}

			return psa_list;
		} catch (Exception e) {
			System.out.println("Exception in SIAQ :- ");
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("message", "Error occured.");
			return psa_list;

		}

	}

	@ResponseBody
	@RequestMapping(value = "/fecthEmpCodeList", method = RequestMethod.POST)
	public ArrayList<ArrayList<String>> fecthEmpCodeList(@RequestParam("division") String division,
			@RequestParam("company_code") String company_code, @RequestParam("loc_code") String loc_code,
			@RequestParam("psa_code") String psa_code, HttpServletRequest request, HttpSession session,
			final RedirectAttributes redirectAttributes) {
		ArrayList<ArrayList<String>> emp_code_list = new ArrayList<ArrayList<String>>();
		try {

			EmployeeModel emp_det = null;
			session = request.getSession(false);
			if (session != null) {
				SessionMaster sessionMaster = new SessionMaster();
				sessionMaster = (SessionMaster) session.getAttribute("sessionMaster");
				long empcode = sessionMaster.getEmployeeModel().getEmp_code();
				emp_det = employeeService.findEmpDetails(empcode);
			}

			String comp_code = "";
			List<String> allowed_comp_code = new ArrayList<String>();
			if (emp_det != null) {
				comp_code = emp_det.getCurr_comp_code();
				allowed_comp_code = employeeService.findSOMapping(comp_code);
				if (allowed_comp_code.isEmpty()) {
					allowed_comp_code.add(comp_code);
				}
				if (comp_code.contentEquals("100")) {
					emp_code_list = employeeService.getEmpCodeList(division, company_code, loc_code, psa_code);
				} else {
					emp_code_list = employeeService.getEmpCodeListLocation(division, company_code, loc_code, psa_code,
							allowed_comp_code);
				}
			}

			return emp_code_list;
		} catch (Exception e) {
			System.out.println("Exception in SIAQ :- ");
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("message", "Error occured.");
			return emp_code_list;

		}

	}

	@ResponseBody
	@RequestMapping(value = "/fecthEmployeeList", method = RequestMethod.POST)
	public List<EmployeeModel> fecthEmployeeList(@RequestParam("division") String division,
			@RequestParam("company_code") String company_code, @RequestParam("loc_code") String loc_code,
			@RequestParam("psa_code") String psa_code, HttpServletRequest request, HttpSession session,
			final RedirectAttributes redirectAttributes) {
		List<EmployeeModel> employees_list = new ArrayList<EmployeeModel>();
		try {

			EmployeeModel emp_det = null;
			session = request.getSession(false);
			if (session != null) {
				SessionMaster sessionMaster = new SessionMaster();
				sessionMaster = (SessionMaster) session.getAttribute("sessionMaster");
				long empcode = sessionMaster.getEmployeeModel().getEmp_code();
				emp_det = employeeService.findEmpDetails(empcode);
			}

			String comp_code = "";
			List<String> allowed_comp_code = new ArrayList<String>();
			if (emp_det != null) {
				comp_code = emp_det.getCurr_comp_code();
				allowed_comp_code = employeeService.findSOMapping(comp_code);
				if (allowed_comp_code.isEmpty()) {
					allowed_comp_code.add(comp_code);
				}
				if (comp_code.contentEquals("100")) {
					employees_list = employeeService.getEmployeeList(division, company_code, loc_code, psa_code);

				} else {
					employees_list = employeeService.getEmployeeListLocation(division, company_code, loc_code, psa_code,
							allowed_comp_code);
				}
			}

			return employees_list;
		} catch (Exception e) {
			System.out.println("Exception in SIAQ :- ");
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("message", "Error occured.");
			return employees_list;

		}

	}

	@RequestMapping(value = "/ModuleUploadTemplate", method = RequestMethod.GET)
	public void ModuleUploadTemplate(HttpServletRequest request, final RedirectAttributes redirectAttributes,
			HttpSession session, HttpServletResponse response) {

		response.setContentType("text/csv");
		response.setHeader("Content-Disposition", "attachment; filename=\"ModuleUploadTemplate.csv\"");
		try {
			OutputStream outputStream = response.getOutputStream();
			String row1 = "Activity_Code\n";
			String row2 = "CORP_FIN_10\n";

			outputStream.write(row1.getBytes());
			outputStream.write(row2.getBytes());
			outputStream.flush();
			outputStream.close();

		} catch (Exception e) {
			System.out.println("Exception Occurred");
			e.printStackTrace();
		}

	}

	@RequestMapping(value = "/EmployeeUploadTemplate", method = RequestMethod.GET)
	public void EmployeeUploadTemplate(HttpServletRequest request, final RedirectAttributes redirectAttributes,
			HttpSession session, HttpServletResponse response) {

		response.setContentType("text/csv");
		response.setHeader("Content-Disposition", "attachment; filename=\"EmployeeUploadTemplate.csv\"");
		try {
			OutputStream outputStream = response.getOutputStream();
			String row1 = "Employee_Code\n";
			String row2 = "511192\n";

			outputStream.write(row1.getBytes());
			outputStream.write(row2.getBytes());
			outputStream.flush();
			outputStream.close();

		} catch (Exception e) {
			System.out.println("Exception Occurred");
			e.printStackTrace();
		}

	}

	@ResponseBody
	@RequestMapping(value = "/checkModuleFile", method = RequestMethod.POST)
	public ModuleUploadBean checkModuleFile(@RequestParam("module_csv_file") MultipartFile file,
			HttpServletRequest request, final RedirectAttributes redirectAttributes, HttpSession session,
			HttpServletResponse response) {
		List<MstActivityListModel> activity_list = new ArrayList<MstActivityListModel>();
		ModuleUploadBean output = new ModuleUploadBean();

		ArrayList<ArrayList<String>> error_records = new ArrayList<ArrayList<String>>();

		ArrayList<String> activity_code_list = new ArrayList<String>();
		try {

			byte[] bytes = file.getBytes();
			String completeData = new String(bytes);
			String[] rows = completeData.split("\n");
//			String[] columns = rows[0].split(",");
			for (int i = 1; i < rows.length; i++) {
				String[] columns = rows[i].split(",");
				if (columns.length > 1) {
					output.setError_msg("Uploaded file is not in proper format");
					break;
				}
				String suffix = columns[0].substring(columns[0].length() - 1, columns[0].length());
				String activity_code = suffix.contentEquals("\r") ? columns[0].substring(0, columns[0].length() - 1)
						: columns[0];

				MstActivityListModel existing_entry = mstActivityListService.findActivityCode(activity_code);
				if (existing_entry == null) {
					ArrayList<String> temp = new ArrayList<String>();
					temp.add(activity_code.trim());
					temp.add("Activity Code does not exist");
					error_records.add(temp);
					continue;
				}
				if (activity_code_list.contains(activity_code)) {
					output.setError_msg("Duplicate records found for activity code : " + activity_code);
					break;

				}
				activity_code_list.add(activity_code);
				activity_list.add(existing_entry);

			}
			if (error_records.size() > 0) {
				output.setError_records(error_records);
			}
			output.setMstActivityListModel(activity_list);
			return output;

		} catch (Exception e) {
			System.out.println("Exception Occurred");
			e.printStackTrace();
			output.setError_msg(e.getLocalizedMessage());
			return output;
		}

	}

	@ResponseBody
	@RequestMapping(value = "/checkEmployeeFile", method = RequestMethod.POST)
	public EmployeeUploadBean checkEmployeeFile(@RequestParam("employee_csv_file") MultipartFile file,
			HttpServletRequest request, final RedirectAttributes redirectAttributes, HttpSession session,
			HttpServletResponse response) {
		List<EmployeeModel> employee_list = new ArrayList<EmployeeModel>();
		EmployeeUploadBean output = new EmployeeUploadBean();
		ArrayList<ArrayList<String>> error_records = new ArrayList<ArrayList<String>>();

		ArrayList<Long> employee_code_list = new ArrayList<Long>();
		try {

			EmployeeModel emp_det = null;
			if (session != null) {
				SessionMaster sessionMaster = new SessionMaster();
				sessionMaster = (SessionMaster) session.getAttribute("sessionMaster");
				emp_det = sessionMaster.getEmployeeModel();
			}

			MstAdminModel mstAdminModel = mstAdminService.findOne(emp_det.getEmp_code());

			String comp_code = "";
			List<String> allowed_comp_code = new ArrayList<String>();

			comp_code = emp_det.getCurr_comp_code();
			allowed_comp_code = employeeService.findSOMapping(comp_code);
			if (allowed_comp_code.isEmpty()) {
				allowed_comp_code.add(comp_code);
			}

			byte[] bytes = file.getBytes();
			String completeData = new String(bytes);
			String[] rows = completeData.split("\n");
//			String[] columns = rows[0].split(",");
			for (int i = 1; i < rows.length; i++) {
				String[] columns = rows[i].split(",");
				if (columns.length > 1) {
					output.setError_msg("Uploaded file is not in proper format");
					break;
				}
				String suffix = columns[0].substring(columns[0].length() - 1, columns[0].length());
				String emp_code = suffix.contentEquals("\r") ? columns[0].substring(0, columns[0].length() - 1)
						: columns[0];

				try {
					Long.valueOf(emp_code);

				} catch (NumberFormatException e) {
					ArrayList<String> temp = new ArrayList<String>();
					temp.add(emp_code);
					temp.add("Incorrect employee no");
					error_records.add(temp);
					continue;

				}
				EmployeeModel emp_model = employeeService.findEmpDetails(Long.valueOf(emp_code));
				if (emp_model == null) {
					ArrayList<String> temp = new ArrayList<String>();
					temp.add(emp_code);
					temp.add("Employee code does not exist");
					error_records.add(temp);
					continue;
				}

				if (mstAdminModel.getDiv_code().contentEquals("1") && !emp_model.getCurrent_divn_cd().contentEquals("1")
						&& !emp_model.getCurrent_divn_cd().contentEquals("5")
						&& !emp_model.getCurrent_divn_cd().contentEquals("9")
						&& !mstAdminModel.getDiv_code().contentEquals("*")) {
					ArrayList<String> temp = new ArrayList<String>();
					temp.add(emp_code);
					temp.add("Employee from other divisions cannot be added");
					error_records.add(temp);
					continue;
				}
				if (!comp_code.contentEquals("100") && !allowed_comp_code.contains(emp_model.getCurr_comp_code())) {
					ArrayList<String> temp = new ArrayList<String>();
					temp.add(emp_code);
					temp.add("Not authorized to add this Employee");
					error_records.add(temp);
					continue;

				}
				if (!mstAdminModel.getDiv_code().contentEquals("1")
						&& !emp_model.getCurrent_divn_cd().contentEquals(mstAdminModel.getDiv_code())
						&& !mstAdminModel.getDiv_code().contentEquals("*")) {
					ArrayList<String> temp = new ArrayList<String>();
					temp.add(emp_code);
					temp.add("Employee from other divisions cannot be added");
					error_records.add(temp);
					continue;
				}

				if (employee_code_list.contains(Long.valueOf(emp_code))) {
					output.setError_msg("Duplicate records found for employee code - " + emp_code);
					break;

				}

				employee_code_list.add(Long.valueOf(emp_code));
				employee_list.add(emp_model);

			}
			if (error_records.size() > 0) {
				output.setError_records(error_records);
			}
			output.setEmployeeModel(employee_list);
			return output;

		} catch (Exception e) {
			System.out.println("Exception Occurred");
			e.printStackTrace();
			output.setError_msg(e.getLocalizedMessage());
			return output;
		}

	}

	@RequestMapping(value = "/createEvent", method = RequestMethod.POST)
	public ModelAndView createEvent(@RequestParam("event_name") String event_name,
			@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate,
			@RequestParam("no_of_questions") String no_of_questions,
			@RequestParam("quiz_duration") String quiz_duration, @RequestParam("remarks") String remarks,
			@RequestParam("employeeArray") String employeeArray, @RequestParam("moduleArray") String moduleArrays,
			HttpServletRequest request, final RedirectAttributes redirectAttributes, HttpSession session,
			HttpServletResponse response) {

		if (!request.isRequestedSessionIdValid()) {
			return new ModelAndView("redirect:/");
		}
		try {

			if (event_name.contains("cook") || event_name.contains("<") || event_name.contains(">")
					|| event_name.contains(":") || event_name.contains(";")) {
				redirectAttributes.addFlashAttribute("errorMsg",
						"Please make sure your input doesn't contain characters/words like cook < > ; :");
				return new ModelAndView("redirect:/HostQuiz");

			}

			if (remarks.contains("cook") || remarks.contains("<") || remarks.contains(">") || remarks.contains(":")
					|| remarks.contains(";")) {
				redirectAttributes.addFlashAttribute("errorMsg",
						"Please make sure your input doesn't contain characters/words like cook < > ; :");
				return new ModelAndView("redirect:/HostQuiz");

			}

			List<String> module_array = new ArrayList<>();
			for (String t : moduleArrays.split(",")) {
				module_array.add(t);
			}

			Long total_questions_module = mstQuizQuestionBankService.findTotalModuleQuestions(module_array);

			if (total_questions_module.compareTo(Long.valueOf(no_of_questions)) < 0) {

				redirectAttributes.addFlashAttribute("errorMsg",
						"Sufficient Questions not available in selected modules");
				return new ModelAndView("redirect:/HostQuiz");

			}

			EmployeeModel emp_det = null;
			if (session != null) {
				SessionMaster sessionMaster = new SessionMaster();
				sessionMaster = (SessionMaster) session.getAttribute("sessionMaster");
				emp_det = sessionMaster.getEmployeeModel();
			}

			remarks = remarks.replace("\r\n", "<br>");

			boolean output = trnQuizEventService.createEvent(event_name, startDate, endDate, no_of_questions,
					quiz_duration, remarks, employeeArray, moduleArrays, emp_det);

			if (output == true) {
				redirectAttributes.addFlashAttribute("message", "Event Successfully created");
			} else {
				redirectAttributes.addFlashAttribute("errorMsg", "Error occured.");
			}
			return new ModelAndView("redirect:/HostQuiz");
		} catch (Exception e) {
			System.out.println("Exception in SIAQ :- ");
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("errorMsg", "Error occured.");
			return new ModelAndView("redirect:/HostQuiz");

		}

	}

}
