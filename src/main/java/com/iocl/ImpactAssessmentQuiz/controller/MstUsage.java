package com.iocl.ImpactAssessmentQuiz.controller;

import com.iocl.ImpactAssessmentQuiz.model.EmployeeModel;
import com.iocl.ImpactAssessmentQuiz.model.MstActivityListModel;
import com.iocl.ImpactAssessmentQuiz.model.SessionMaster;
import com.iocl.ImpactAssessmentQuiz.repository.Courses;
import com.iocl.ImpactAssessmentQuiz.service.MstActivityListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import com.iocl.ImpactAssessmentQuiz.service.MstUsageListService;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.List;

@Controller
public class MstUsage {
	@Autowired
	private final MstUsageListService mstusageservice;
	@Autowired
	MstActivityListService mstActivityListService;

	public MstUsage(MstUsageListService mstusageservice) {
		this.mstusageservice = mstusageservice;
	}

	@RequestMapping(value = "/BarChart/{month}&{year}", method = RequestMethod.GET)
	@ResponseBody
	public int[] getCount(@PathVariable(value="month")int month, @PathVariable(value="year")int year, HttpSession session) throws ParseException {

		EmployeeModel emp_det = null;
		if (session != null) {
			SessionMaster sessionMaster = new SessionMaster();
			sessionMaster = (SessionMaster) session.getAttribute("sessionMaster");

			emp_det = sessionMaster.getEmployeeModel();
		}
		return mstusageservice.getBarstats(month,year,emp_det.getEmp_code());
	}
	@RequestMapping(value = "/Courses", method = RequestMethod.GET)
	@ResponseBody
	public List<Courses> getCourses(HttpSession session) {

		EmployeeModel emp_det = null;
		if (session != null) {
			SessionMaster sessionMaster = new SessionMaster();
			sessionMaster = (SessionMaster) session.getAttribute("sessionMaster");

			emp_det = sessionMaster.getEmployeeModel();
		}

		return mstusageservice.getCoursescompleted(emp_det.getEmp_code());
	}

	@RequestMapping(value = "/CompleteActivityList", method = RequestMethod.GET)
	@ResponseBody
	public List<MstActivityListModel> getCompleteActivityList(HttpSession session) {


		return mstActivityListService.findAll();
	}

	@RequestMapping(value = "/Courses/id={id}", method = RequestMethod.GET)
	@ResponseBody
	public List<Courses> getEmpCourses(HttpSession session, @PathVariable("id") String id) {
		return mstusageservice.getCoursescompleted(Long.parseLong(id));
	}





		@RequestMapping(value = "/Courses/{status}", method = RequestMethod.GET)
		@ResponseBody
		public List getCoursesbystatus(@PathVariable(value="status") String status, HttpSession session) {

			EmployeeModel emp_det = null;
			if (session != null) {
				SessionMaster sessionMaster = new SessionMaster();
				sessionMaster = (SessionMaster) session.getAttribute("sessionMaster");

				emp_det = sessionMaster.getEmployeeModel();
			}

			return mstusageservice.getCoursesByStatus(emp_det.getEmp_code(),status);
	}
	@RequestMapping(value = "/Courses/{status}/{id}", method = RequestMethod.GET)
	@ResponseBody
	public List getEmpCoursesbystatus(@PathVariable(value="status") String status, @PathVariable(value="id") String id, HttpSession session) {

		return mstusageservice.getCoursesByStatus(Long.parseLong(id) ,status);
	}

	@RequestMapping(value = "/Courses/list/{domain}", method = RequestMethod.GET)
	@ResponseBody
	public List<Courses> getEmpCoursesbystatus(@PathVariable(value="domain") String domain, HttpSession session) {
		EmployeeModel emp_det = null;
		if (session != null) {
			SessionMaster sessionMaster = new SessionMaster();
			sessionMaster = (SessionMaster) session.getAttribute("sessionMaster");

			emp_det = sessionMaster.getEmployeeModel();
		}

		assert emp_det != null;
		return mstusageservice.Completed_Activity_Codes(emp_det.getEmp_code() ,domain);
	}



	@RequestMapping(value = "/Divcourses/{status}", method = RequestMethod.GET)
	@ResponseBody
	public List getDivCoursesbystatus(@PathVariable(value="status") String status, HttpSession session) {

		EmployeeModel emp_det = null;
		if (session != null) {
			SessionMaster sessionMaster = new SessionMaster();
			sessionMaster = (SessionMaster) session.getAttribute("sessionMaster");

			emp_det = sessionMaster.getEmployeeModel();
		}
		System.out.println(status);
		return mstusageservice.getDivCoursesByStatus(emp_det.getEmp_code(),status);
	}
	@RequestMapping(value = "/Divcourses", method = RequestMethod.GET)
	@ResponseBody
	public List getDivCompleted( HttpSession session) {

		EmployeeModel emp_det = null;
		if (session != null) {
			SessionMaster sessionMaster = new SessionMaster();
			sessionMaster = (SessionMaster) session.getAttribute("sessionMaster");

			emp_det = sessionMaster.getEmployeeModel();
		}

		return mstusageservice.getDivCompleted(emp_det.getEmp_code());
	}
}
