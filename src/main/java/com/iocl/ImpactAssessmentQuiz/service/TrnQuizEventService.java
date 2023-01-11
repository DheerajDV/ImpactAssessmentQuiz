package com.iocl.ImpactAssessmentQuiz.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iocl.ImpactAssessmentQuiz.model.EmployeeModel;
import com.iocl.ImpactAssessmentQuiz.model.TrnAutoMailModel;
import com.iocl.ImpactAssessmentQuiz.model.TrnQuizActivityMappingID;
import com.iocl.ImpactAssessmentQuiz.model.TrnQuizActivityMappingModel;
import com.iocl.ImpactAssessmentQuiz.model.TrnQuizEventModel;
import com.iocl.ImpactAssessmentQuiz.model.TrnQuizHeaderModel;
import com.iocl.ImpactAssessmentQuiz.repository.TrnQuizEventRepository;

@Service
public class TrnQuizEventService {

	@Autowired
	TrnQuizEventRepository trnQuizEventRepository;

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
	TrnQuizActivityMappingService trnQuizActivityMappingService;

	@Autowired
	TrnAutoMailService trnAutoMailService;

	public Long getEventId() {
		return trnQuizEventRepository.getEventId();
	}

	@Transactional
	public boolean createEvent(String event_name, String startDate, String endDate, String no_of_questions,
			String quiz_duration, String remarks, String employeeArray, String moduleArrays, EmployeeModel emp_det) {

		try {
			String[] employee_array = employeeArray.split(",");
			String[] module_array = moduleArrays.split(",");

			String[] start_date_arr = startDate.split("T");
			String[] end_date_arr = endDate.split("T");

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			LocalDateTime startdateTime = LocalDateTime.parse(start_date_arr[0] + " " + start_date_arr[1], formatter);
			LocalDateTime enddateTime = LocalDateTime.parse(end_date_arr[0] + " " + end_date_arr[1], formatter);

			TrnQuizEventModel trnQuizEventModel = new TrnQuizEventModel();

			trnQuizEventModel.setEvent_name(event_name);
			trnQuizEventModel.setEvent_start_time(startdateTime);
			trnQuizEventModel.setEvent_end_time(enddateTime);
			trnQuizEventModel.setQuiz_duration(Long.valueOf(quiz_duration));
			trnQuizEventModel.setTotal_questions(Long.valueOf(no_of_questions));
			trnQuizEventModel.setResult_announce_flag("N");
			trnQuizEventModel.setRemarks(remarks);
			trnQuizEventModel.setEvent_status("A");
			trnQuizEventModel.setAnnounced_by(emp_det.getEmp_code());
			trnQuizEventModel.setAnnounced_on(LocalDateTime.now());

			Long event_id = trnQuizEventService.getEventId();
			trnQuizEventModel.setEvent_id(event_id);

			List<TrnQuizHeaderModel> trnQuizHeaderModel = new ArrayList<TrnQuizHeaderModel>();
			List<TrnQuizActivityMappingModel> trnQuizActivityMappingModel = new ArrayList<TrnQuizActivityMappingModel>();
			List<TrnAutoMailModel> trnAutoMailModel = new ArrayList<TrnAutoMailModel>();

			for (int i = 0; i < employee_array.length; i++) {
				EmployeeModel employee_details = employeeService.findEmpDetails(Long.valueOf(employee_array[i]));

				TrnQuizHeaderModel temp = new TrnQuizHeaderModel();

				temp.setEmployeeModel(employee_details);
				temp.setQuiz_start_time(startdateTime);
				temp.setQuiz_end_time(enddateTime);
				temp.setQuiz_duration(Long.valueOf(quiz_duration));
				temp.setQuiz_taken("N");
				temp.setNo_of_attempts((long) 0);
				temp.setQuiz_status("A");
				temp.setAnnounced_by(emp_det.getEmp_code());
				temp.setAnnounced_on(LocalDateTime.now());
				temp.setTotal_questions(Long.valueOf(no_of_questions));
				temp.setAuto_submit_flg("N");
				temp.setUpdated_on(LocalDateTime.now());
				temp.setResult_announce_flg("N");
				temp.setTrnQuizEventModel(trnQuizEventModel);

				Long quiz_id = trnQuizHeaderService.getQuizID();
				temp.setQuiz_id(quiz_id);

				trnQuizHeaderModel.add(temp);

				TrnAutoMailModel trnAutoMailModel_temp = new TrnAutoMailModel();

				trnAutoMailModel_temp.setMessage_source("SIAQ");
				trnAutoMailModel_temp.setTo_mail_id(employee_details.getEmail_id());
				trnAutoMailModel_temp.setMail_subject("Quiz Nomination - Swadhyaya Impact Assessment Quiz");
				trnAutoMailModel_temp.setMail_body("Dear Sir/Ma'am,<br>" + remarks
						+ "<br><br>Regards<br>Swadhyaya Impact Assessment Team<br><br>Please note that it is a system generated mail. Do no reply.");

				trnAutoMailModel_temp.setStatus_flag("P");
				trnAutoMailModel_temp.setMail_register_date_time(LocalDateTime.now());
				trnAutoMailModel_temp.setTo_recipient_type("External");
				trnAutoMailModel_temp.setTo_recipient_code(String.valueOf(employee_details.getEmp_code()));

				Long message_id = trnAutoMailService.getMessageID();
				trnAutoMailModel_temp.setMessage_id(message_id);

				trnAutoMailModel.add(trnAutoMailModel_temp);

				for (int j = 0; j < module_array.length; j++) {
					TrnQuizActivityMappingModel temp_1 = new TrnQuizActivityMappingModel();
					TrnQuizActivityMappingID trnQuizActivityMappingID = new TrnQuizActivityMappingID();
					trnQuizActivityMappingID
							.setMstActivityListModel(mstActivityListService.findActivityCode(module_array[j]));
					trnQuizActivityMappingID.setTrnQuizHeaderModel(temp);

					temp_1.setTrnQuizActivityMappingID(trnQuizActivityMappingID);
					temp_1.setUpdated_by(emp_det.getEmp_code());
					temp_1.setUpdated_on(LocalDateTime.now());

					trnQuizActivityMappingModel.add(temp_1);
				}

			}
			trnQuizEventRepository.save(trnQuizEventModel);
			trnQuizHeaderService.saveorupdateAll(trnQuizHeaderModel);
			trnAutoMailService.saveorupdateAll(trnAutoMailModel);
			trnQuizActivityMappingService.saveorupdateAll(trnQuizActivityMappingModel);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

}
