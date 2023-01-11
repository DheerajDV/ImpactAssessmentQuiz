package com.iocl.ImpactAssessmentQuiz.controller;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iocl.ImpactAssessmentQuiz.bean.MasterCount;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.iocl.ImpactAssessmentQuiz.bean.UploadErrorBean;
import com.iocl.ImpactAssessmentQuiz.model.EmployeeModel;
import com.iocl.ImpactAssessmentQuiz.model.MstActivityListModel;
import com.iocl.ImpactAssessmentQuiz.model.MstAdminModel;
import com.iocl.ImpactAssessmentQuiz.model.MstDivisionListModel;
import com.iocl.ImpactAssessmentQuiz.model.MstUsageListModel;
import com.iocl.ImpactAssessmentQuiz.model.SessionMaster;
import com.iocl.ImpactAssessmentQuiz.model.TrnEmpWiseUsageID;
import com.iocl.ImpactAssessmentQuiz.model.TrnEmpWiseUsageModel;
import com.iocl.ImpactAssessmentQuiz.service.EmployeeService;
import com.iocl.ImpactAssessmentQuiz.service.MstActivityListService;
import com.iocl.ImpactAssessmentQuiz.service.MstAdminService;
import com.iocl.ImpactAssessmentQuiz.service.MstDivisionListService;
import com.iocl.ImpactAssessmentQuiz.service.MstUsageListService;
import com.iocl.ImpactAssessmentQuiz.service.TrnEmpWiseUsageService;

@Controller
public class AdminDataUpload {

	@Autowired
	ResourceLoader resourceLoader;

	@Autowired
	MstAdminService mstAdminService;

	@Autowired
	EmployeeService employeeService;

	@Autowired
	MstActivityListService mstActivityListService;

	@Autowired
	MstDivisionListService mstDivisionListService;
	
	@Autowired
	MstUsageListService mstUsageListService;

	@Autowired
	TrnEmpWiseUsageService trnEmpWiseUsageService;

	@RequestMapping(value = "/UsageReport", method = RequestMethod.GET)
	public ModelAndView UsageReport(HttpServletRequest request, HttpSession session,
			final RedirectAttributes redirectAttributes) {
		if (!request.isRequestedSessionIdValid()) {
			return new ModelAndView("redirect:/");
		}
		try {
			ModelAndView model = new ModelAndView("empWiseUsageReport");

			return model;
		} catch (Exception e) {
			System.out.println("Exception in SIAQ :- ");
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("errorMsg", "Error occured.");
			return new ModelAndView("redirect:/Home");

		}
	}

	@RequestMapping(value = "/ActivityMaster", method = RequestMethod.GET)
	public ModelAndView ActivityMaster(HttpServletRequest request, HttpSession session,
			final RedirectAttributes redirectAttributes) {
		if (!request.isRequestedSessionIdValid()) {
			return new ModelAndView("redirect:/");
		}
		try {
			ModelAndView model = new ModelAndView("activityMaster");

			return model;
		} catch (Exception e) {
			System.out.println("Exception in SIAQ :- ");
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("errorMsg", "Error occured.");
			return new ModelAndView("redirect:/Home");

		}
	}

	@RequestMapping(value = "/DivisionMaster", method = RequestMethod.GET)
	public ModelAndView DivisionMaster(HttpServletRequest request, HttpSession session,
			final RedirectAttributes redirectAttributes) {
		if (!request.isRequestedSessionIdValid()) {
			return new ModelAndView("redirect:/");
		}
		try {
			ModelAndView model = new ModelAndView("divisionMaster");

			return model;
		} catch (Exception e) {
			System.out.println("Exception in SIAQ :- ");
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("errorMsg", "Error occured.");
			return new ModelAndView("redirect:/Home");

		}
	}
	
	@RequestMapping(value = "/UsageMaster", method = RequestMethod.GET)
	public ModelAndView UsageMaster(HttpServletRequest request, HttpSession session,
			final RedirectAttributes redirectAttributes) {
		if (!request.isRequestedSessionIdValid()) {
			return new ModelAndView("redirect:/");
		}
		try {
			ModelAndView model = new ModelAndView("usageMaster");

			return model;
		} catch (Exception e) {
			System.out.println("Exception in SIAQ :- ");
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("errorMsg", "Error occured.");
			return new ModelAndView("redirect:/Home");

		}
	}

	@RequestMapping(value = "/EmpWiseUsageDataJSON", method = RequestMethod.GET)
	@ResponseBody
	public List<TrnEmpWiseUsageModel> EmpWiseUsageDataJSON(HttpServletRequest request, HttpSession session,
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

		List<TrnEmpWiseUsageModel> data = null;

		MstAdminModel mstAdminModel = mstAdminService.findOne(emp_det.getEmp_code());
		List<String> div_code = new ArrayList<String>();
		div_code.add(mstAdminModel.getDiv_code());
		if (mstAdminModel.getDiv_code().contentEquals("1")) {
			div_code.add("5");
			div_code.add("9");
		}

		if (mstAdminModel != null && (mstAdminModel.getDiv_code()).contentEquals("*")) {
			data = trnEmpWiseUsageService.findAll();
		} else if (mstAdminModel != null && !(mstAdminModel.getDiv_code()).contentEquals("*")) {
			if (mstAdminModel.getDiv_code().contentEquals("3") && !comp_code.contentEquals("100")) {
				data = trnEmpWiseUsageService.findDivisionDataCompCode(div_code, allowed_comp_code);
			} else {
				data = trnEmpWiseUsageService.findDivisionData(div_code);
			}
		}

		return data;

	}

	@RequestMapping(value = "/ActivityDataJSON", method = RequestMethod.GET)
	@ResponseBody
	public List<MstActivityListModel> ActivityDataJSON(HttpServletRequest request, HttpSession session,
			final RedirectAttributes redirectAttributes) {

		List<MstActivityListModel> data = mstActivityListService.findAll();

		return data;

	}

	@RequestMapping(value = "/DivisionDataJSON", method = RequestMethod.GET)
	@ResponseBody
	public List<MasterCount> DivisionDataJSON(HttpServletRequest request, HttpSession session,
											  final RedirectAttributes redirectAttributes) {

		List<MasterCount> division_data = mstDivisionListService.find_count_groupby();




		return division_data;

	}




	@RequestMapping(value = "/UsageDataJSON", method = RequestMethod.GET)
	@ResponseBody
	public List<MasterCount> UsageDataJSON(HttpServletRequest request, HttpSession session,
											  final RedirectAttributes redirectAttributes) {

		List<MasterCount> division_data = mstUsageListService.find_count_groupby();




		return division_data;

	}











	@RequestMapping(value = "/UsageReportUploadTemplate", method = RequestMethod.GET)
	public ResponseEntity<Resource> UsageReportUploadTemplate(HttpServletRequest request,
			final RedirectAttributes redirectAttributes, HttpSession session, HttpServletResponse response) {

		try {
			Resource resource = resourceLoader.getResource("classpath:Report_Upload_Format.xlsx");

			// Try to determine file's content type
			String contentType = null;
			try {
				contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
			} catch (IOException ex) {

			}

			// Fallback to the default content type if type could not be determined
			if (contentType == null) {
				contentType = "application/octet-stream";
			}

			return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
					.body(resource);

		} catch (Exception e) {
			System.out.println("Exception Occurred");
			e.printStackTrace();
		}
		return null;

	}

	@RequestMapping(value = "/ActivityUploadTemplate", method = RequestMethod.GET)
	public ResponseEntity<Resource> ActivityUploadTemplate(HttpServletRequest request,
			final RedirectAttributes redirectAttributes, HttpSession session, HttpServletResponse response) {

		try {
			Resource resource = resourceLoader.getResource("classpath:Activity_Master_Upload_Format.xlsx");

			// Try to determine file's content type
			String contentType = null;
			try {
				contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
			} catch (IOException ex) {

			}

			// Fallback to the default content type if type could not be determined
			if (contentType == null) {
				contentType = "application/octet-stream";
			}

			return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
					.body(resource);

		} catch (Exception e) {
			System.out.println("Exception Occurred");
			e.printStackTrace();
		}
		return null;

	}

	@ResponseBody
	@RequestMapping(value = "/uploadUsageReport", method = RequestMethod.POST)
	public UploadErrorBean uploadUsageReport(@RequestParam("report_csv_file") MultipartFile file,
			HttpServletRequest request, final RedirectAttributes redirectAttributes, HttpSession session,
			HttpServletResponse response) {

		InputStream uploadedStream = null;

		List<TrnEmpWiseUsageModel> employee_usage_report_list = new ArrayList<TrnEmpWiseUsageModel>();

		UploadErrorBean output = new UploadErrorBean();

		ArrayList<ArrayList<String>> error_records = new ArrayList<ArrayList<String>>();

		try {

			String[] x = null;
			uploadedStream = file.getInputStream();

			String doc_name = "";
			String sDataFile = file.getName();
			if (sDataFile != null && sDataFile != "") {
				doc_name += sDataFile.substring(sDataFile.lastIndexOf("\\") + 1, sDataFile.length() - 0) + ";";
				x = doc_name.split(";");
			}

			if (x != null) {
				String filename = x[0];
				if (filename != null && !filename.equals("")) {
					try {

						XSSFWorkbook wb = new XSSFWorkbook(uploadedStream);

						XSSFSheet sheet = wb.getSheetAt(0);
						int rows = sheet.getLastRowNum();

						int noOfColumns = sheet.getRow(0).getPhysicalNumberOfCells();

						if (noOfColumns > 11) {
							output.setError_msg("Uploaded file is not in proper format");

						}

						DataFormatter fmt = new DataFormatter();

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

//			String[] columns = rows[0].split(",");
						for (int r = 1; r < rows + 1; r++) {

							String cellValue = null;
							String[] excelData = new String[11];
							XSSFRow row = sheet.getRow(r);

							if (row != null) {
								Iterator<Cell> cellIterator = row.iterator();
								int ii = 0;

								Date start_date = null;
								Date end_date = null;
								Double score = null;
								Double min_percentage = null;
								Double course_duration = null;
								Double time_elapsed = null;

								ArrayList<String> temp = new ArrayList<String>();

								String error_row = "";

								while (cellIterator.hasNext()) {
									Cell currentCell = cellIterator.next();
									if (currentCell != null) {

										cellValue = fmt.formatCellValue(currentCell);

										if (ii == 0) {
											if (cellValue == null || cellValue.trim().isEmpty()
													|| cellValue.trim().equals("")) {
												continue;
											}
										}

										if (ii == 3) {
											try {
												start_date = currentCell.getDateCellValue();
											} catch (Exception e) {
												error_row = error_row + " Invalid start date ;";
											}
										}
										if (ii == 4) {
											try {
												end_date = currentCell.getDateCellValue();
											} catch (Exception e) {
												error_row = error_row + " Invalid end date ;";

											}
										}
										if (ii == 6) {
											try {
												score = currentCell.getNumericCellValue();
											} catch (Exception e) {
												error_row = error_row + " Invalid score value ;";

											}
										}

										if (ii == 7) {
											try {
												min_percentage = currentCell.getNumericCellValue();
											} catch (Exception e) {
												error_row = error_row + " Invalid minimum percentage value ;";
											}
										}

										if (ii == 8) {
											try {
												course_duration = currentCell.getNumericCellValue();
											} catch (Exception e) {
												error_row = error_row + " Invalid course duration value ;";
											}
										}

										if (ii == 9) {
											try {
												time_elapsed = currentCell.getNumericCellValue();
											} catch (Exception e) {
												error_row = error_row + " Invalid time elapsed value ;";
											}
										}

										if (!cellValue.trim().isEmpty()) {
											excelData[ii] = cellValue;
											if (cellValue != null && cellValue.trim().isEmpty()) {
												excelData[ii] = null;
											}
										}
										ii++;
									}
								}

								String emp_code = excelData[0];
								String activity_code = excelData[1];
								String registeration_status = excelData[2];
								String success_status = excelData[5];
								String curr_attempt = excelData[10];

								boolean invalid_emp = false;

								try {
									Long.valueOf(emp_code);

								} catch (NumberFormatException e) {
									invalid_emp = true;
									error_row = error_row + " Incorrect employee no ;";

								}
								try {
									Long.valueOf(curr_attempt);

								} catch (NumberFormatException e) {
									error_row = error_row + " Invalid current attempt value ;";

								}
								MstActivityListModel mstActivityListModel = mstActivityListService
										.findActivityCode(activity_code.trim());
								EmployeeModel emp_model = null;
								if (invalid_emp == false) {
									emp_model = employeeService.findEmpDetails(Long.valueOf(emp_code));

									if (emp_model == null) {
										error_row = error_row + " Employee code does not exist ;";
									}

									if (mstActivityListModel == null) {
										error_row = error_row + " Activity code is not present in master ;";
									}

									if (mstAdminModel.getDiv_code().contentEquals("1")
											&& !emp_model.getCurrent_divn_cd().contentEquals("1")
											&& !emp_model.getCurrent_divn_cd().contentEquals("5")
											&& !emp_model.getCurrent_divn_cd().contentEquals("9")
											&& !mstAdminModel.getDiv_code().contentEquals("*")) {

										error_row = error_row + " Employee from other divisions cannot be added ;";
									}
									if (!mstAdminModel.getDiv_code().contentEquals("1")
											&& !emp_model.getCurrent_divn_cd()
													.contentEquals(mstAdminModel.getDiv_code())
											&& !mstAdminModel.getDiv_code().contentEquals("*")) {
										error_row = error_row + " Employee from other divisions cannot be added ;";
									}
								}

								if (!error_row.contentEquals("")) {
									temp.add(excelData[0]);
									temp.add(excelData[1]);
									temp.add(error_row);
									error_records.add(temp);
									continue;
								}

								TrnEmpWiseUsageModel tempEmpWiseUsageModel = new TrnEmpWiseUsageModel();
								TrnEmpWiseUsageID trnEmpWiseUsageID = new TrnEmpWiseUsageID(emp_model,
										mstActivityListModel);
								tempEmpWiseUsageModel.setTrnEmpWiseUsageID(trnEmpWiseUsageID);

								tempEmpWiseUsageModel.setRegisteration_status(registeration_status);
//								temp.setStart_date(LocalDateTime.parse(start_date,
//										DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a")));
//								temp.setEnd_date(LocalDateTime.parse(end_date,
//										DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a")));
								tempEmpWiseUsageModel.setStart_date(
										LocalDateTime.ofInstant(start_date.toInstant(), ZoneId.systemDefault()));
								tempEmpWiseUsageModel.setEnd_date(
										LocalDateTime.ofInstant(end_date.toInstant(), ZoneId.systemDefault()));
								tempEmpWiseUsageModel.setSuccess_status(success_status);
								tempEmpWiseUsageModel.setScore(score);
								tempEmpWiseUsageModel.setMin_percentage(min_percentage);
								tempEmpWiseUsageModel.setCourse_duration(course_duration);
								tempEmpWiseUsageModel.setTime_elapsed(time_elapsed);
								tempEmpWiseUsageModel.setCurrent_attempt(Long.valueOf(curr_attempt));
								tempEmpWiseUsageModel.setUpdated_by(emp_det.getEmp_code());
								tempEmpWiseUsageModel.setUpdated_on(LocalDateTime.now());

								employee_usage_report_list.add(tempEmpWiseUsageModel);

							}
						}
						if (wb != null) {
							wb.close();
						}
						if (error_records.size() > 0) {
							output.setError_records(error_records);
						}

						trnEmpWiseUsageService.saveorupdateAll(employee_usage_report_list);

					} catch (Exception ex) {
						System.out.println("Error in reading file:" + ex.getMessage());
						ex.printStackTrace();
						try {
							if (uploadedStream != null)
								uploadedStream.close();
						} catch (IOException ex1) {
							System.out.println("Exception in closing file:" + ex.getMessage());
						}
						output.setError_msg("Error in reading file:" + ex.getMessage());

					}

				}
			}

			return output;

		} catch (Exception e) {
			System.out.println("Exception Occurred");
			e.printStackTrace();
			output.setError_msg(e.getLocalizedMessage());
			return output;
		} finally {
			try {
				if (uploadedStream != null)
					uploadedStream.close();
			} catch (IOException ex) {
				System.out.println("Exception in closing file:" + ex.getMessage());
			}

		}

	}

	@ResponseBody
	@RequestMapping(value = "/uploadActivityMaster", method = RequestMethod.POST)
	public UploadErrorBean uploadActivityMaster(@RequestParam("report_csv_file") MultipartFile file,
			HttpServletRequest request, final RedirectAttributes redirectAttributes, HttpSession session,
			HttpServletResponse response) {

		InputStream uploadedStream = null;

		List<MstActivityListModel> activity_list = new ArrayList<MstActivityListModel>();

		UploadErrorBean output = new UploadErrorBean();

		ArrayList<ArrayList<String>> error_records = new ArrayList<ArrayList<String>>();

		try {

			String[] x = null;
			uploadedStream = file.getInputStream();

			String doc_name = "";
			String sDataFile = file.getName();
			if (sDataFile != null && sDataFile != "") {
				doc_name += sDataFile.substring(sDataFile.lastIndexOf("\\") + 1, sDataFile.length() - 0) + ";";
				x = doc_name.split(";");
			}

			if (x != null) {
				String filename = x[0];
				if (filename != null && !filename.equals("")) {
					try {

						XSSFWorkbook wb = new XSSFWorkbook(uploadedStream);

						XSSFSheet sheet = wb.getSheetAt(0);
						int rows = sheet.getPhysicalNumberOfRows();

						int noOfColumns = sheet.getRow(0).getPhysicalNumberOfCells();

						if (noOfColumns > 9) {
							output.setError_msg("Uploaded file is not in proper format");

						}

						DataFormatter fmt = new DataFormatter();

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
						mstActivityListService.deleteAll();
//			String[] columns = rows[0].split(",");
						for (int r = 1; r < rows; r++) {

							String cellValue = null;
							String[] excelData = new String[11];
							XSSFRow row = sheet.getRow(r);

							if (row != null) {
								Iterator<Cell> cellIterator = row.iterator();
								int ii = 0;

								Date metadata_expiration_date = null;
								Date f_lms_upload_date = null;
								Double estimated_duration = null;
								ArrayList<String> temp = new ArrayList<String>();

								String error_row = "";

								while (cellIterator.hasNext()) {
									Cell currentCell = cellIterator.next();
									if (currentCell != null) {

										cellValue = fmt.formatCellValue(currentCell);

										if (ii == 0) {
											if (cellValue == null || cellValue.trim().isEmpty()
													|| cellValue.trim().equals("")) {
												continue;
											}
										}

										if (ii == 5) {
											try {
												f_lms_upload_date = currentCell.getDateCellValue();
											} catch (Exception e) {
												error_row = error_row + " Invalid lms upload date;";

											}
										}

										if (ii == 6) {
											try {
												estimated_duration = currentCell.getNumericCellValue();
											} catch (Exception e) {
												error_row = error_row + " Invalid estimated duration value ;";

											}
										}

										if (!cellValue.trim().isEmpty()) {
											excelData[ii] = cellValue;
											if (cellValue != null && cellValue.trim().isEmpty()) {
												excelData[ii] = null;
											}
										}
										ii++;
									}
								}

								String division = excelData[0];
								String domain = excelData[1];
								String sub_domain = excelData[2];
								String activity_code = excelData[3];
								String activity_name = excelData[4];
								String active_status = excelData[7];
								String expiry_status = excelData[8];

								if (!error_row.contentEquals("")) {
									temp.add(excelData[3]);
//									temp.add(excelData[1]);
									temp.add(error_row);
									error_records.add(temp);
									continue;
								}

								MstActivityListModel mstActivityListModel = new MstActivityListModel();

								mstActivityListModel.setActivity_code(activity_code);
								mstActivityListModel.setActivity_name(activity_name);
								mstActivityListModel.setDivision(division);
								mstActivityListModel.setDomain(domain);
								mstActivityListModel.setSub_domain(sub_domain);
								mstActivityListModel.setDuration(estimated_duration);
								mstActivityListModel.setExpiry_status(expiry_status);
								mstActivityListModel.setStatus(active_status);
								mstActivityListModel.setUpdated_by(emp_det.getEmp_code());
								mstActivityListModel.setLms_upload_date(
										LocalDateTime.ofInstant(f_lms_upload_date.toInstant(), ZoneId.systemDefault()));
								mstActivityListModel.setUpdated_on(LocalDateTime.now());
//								mstActivityListModel.setMetadata_expiration_date(LocalDateTime
//										.ofInstant(metadata_expiration_date.toInstant(), ZoneId.systemDefault()));

								mstActivityListModel.setMetadata_expiration_date(null);
								activity_list.add(mstActivityListModel);
								mstActivityListService.saveorupdateAll(activity_list);
								System.out.println("count " + r);

							}
						}
						if (wb != null) {
							wb.close();
						}
						if (error_records.size() > 0) {
							output.setError_records(error_records);
						}

					} catch (Exception ex) {
						System.out.println("Error in reading file:" + ex.getMessage());
						ex.printStackTrace();
						try {
							if (uploadedStream != null)
								uploadedStream.close();
						} catch (IOException ex1) {
							System.out.println("Exception in closing file:" + ex.getMessage());
						}
						output.setError_msg("Error in reading file:" + ex.getMessage());

					}

				}
			}

			return output;

		} catch (Exception e) {
			System.out.println("Exception Occurred");
			e.printStackTrace();
			output.setError_msg(e.getLocalizedMessage());
			return output;
		} finally {
			try {
				if (uploadedStream != null)
					uploadedStream.close();
			} catch (IOException ex) {
				System.out.println("Exception in closing file:" + ex.getMessage());
			}

		}

	}

	@ResponseBody
	@RequestMapping(value = "/uploadDivisionMaster", method = RequestMethod.POST)
	public UploadErrorBean uploadDivisionMaster(@RequestParam("report_csv_file") MultipartFile file,
			HttpServletRequest request, final RedirectAttributes redirectAttributes, HttpSession session,
			HttpServletResponse response) {

		InputStream uploadedStream = null;
		System.out.println("in uploadDivisionMaster");

		UploadErrorBean output = new UploadErrorBean();

		ArrayList<ArrayList<String>> error_records = new ArrayList<ArrayList<String>>();

		try {

			String[] x = null;
			uploadedStream = file.getInputStream();

			String doc_name = "";
			String sDataFile = file.getName();
			if (sDataFile != null && sDataFile != "") {
				doc_name += sDataFile.substring(sDataFile.lastIndexOf("\\") + 1, sDataFile.length() - 0) + ";";
				x = doc_name.split(";");
			}

			if (x != null) {
				String filename = x[0];
				if (filename != null && !filename.equals("")) {
					try {

						XSSFWorkbook wb = new XSSFWorkbook(uploadedStream);

						XSSFSheet sheet = wb.getSheetAt(0);
						int rows = sheet.getPhysicalNumberOfRows();

						int noOfColumns = sheet.getRow(0).getPhysicalNumberOfCells();

						if (noOfColumns > 25) {
							output.setError_msg("Uploaded file is not in proper format");

						}

						DataFormatter fmt = new DataFormatter();

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
						mstDivisionListService.deleteAll();
//			String[] columns = rows[0].split(",");
						for (int r = 1; r < rows; r++) {

							String cellValue = null;
							String[] excelData = new String[25];
							XSSFRow row = sheet.getRow(r);

							if (row != null) {
								Iterator<Cell> cellIterator = row.iterator();
								int ii = 0;

								Date emp_doj = null;
								Date emp_dob = null;
								int emp_total_course_completed = 0;

								int emp_total_course_passed = 0;

								int emp_total_course_failed = 0;

								int emp_courses_comp_other_div = 0;

								int emp_courses_pass_other_div = 0;

								int emp_courses_fail_other_div = 0;

								ArrayList<String> temp = new ArrayList<String>();

								String error_row = "";

								while (cellIterator.hasNext()) {
									Cell currentCell = cellIterator.next();
									if (currentCell != null) {

										cellValue = fmt.formatCellValue(currentCell);

										if (ii == 7) {
											try {
												emp_doj = currentCell.getDateCellValue();
											} catch (Exception e) {
												error_row = error_row + " Invalid date of joining value;";

											}
										}

										if (ii == 8) {
											try {
												emp_dob = currentCell.getDateCellValue();
											} catch (Exception e) {
												error_row = error_row + " Invalid date of birth value ;";

											}
										}

										if (ii == 18) {
											try {
												emp_total_course_completed = (int) currentCell.getNumericCellValue();
											} catch (Exception e) {
												error_row = error_row + " Invalid total course completed value ;";

											}
										}
										if (ii == 19) {
											try {
												emp_total_course_passed = (int) currentCell.getNumericCellValue();
											} catch (Exception e) {
												error_row = error_row + " Invalid total course passed  value ;";

											}
										}
										if (ii == 20) {
											try {
												emp_total_course_failed = (int) currentCell.getNumericCellValue();
											} catch (Exception e) {
												error_row = error_row + " Invalid total course failed value ;";

											}
										}
										if (ii == 21) {
											try {
												emp_courses_comp_other_div = (int) currentCell.getNumericCellValue();
											} catch (Exception e) {
												error_row = error_row
														+ " Invalid other division course completed value ;";

											}
										}
										if (ii == 22) {
											try {
												emp_courses_pass_other_div = (int) currentCell.getNumericCellValue();
											} catch (Exception e) {
												error_row = error_row + " Invalid other division course passed value ;";

											}
										}
										if (ii == 23) {
											try {
												emp_courses_fail_other_div = (int) currentCell.getNumericCellValue();
											} catch (Exception e) {
												error_row = error_row + " Invalid other division course failed value ;";

											}
										}

										if (!cellValue.trim().isEmpty()) {
											excelData[ii] = cellValue;
											if (cellValue != null && cellValue.trim().isEmpty()) {
												excelData[ii] = null;
											}
										}
										ii++;
									}
								}

								String emp_id = excelData[0];
								String emp_name = excelData[1];
								String emp_desg = excelData[2];
								String emp_gender = excelData[3];
								String emp_grade = excelData[4];
								String emp_emailid = excelData[5];
								String emp_manager = excelData[6];
								String emp_div = excelData[9];
								String emp_com_code = excelData[10];
								String emp_com = excelData[11];
								String emp_loc_code = excelData[12];
								String emp_loc = excelData[13];
								String emp_dep_code = excelData[14];
								String emp_dep = excelData[15];
								String emp_status = excelData[16];
								String emp_job_grade = excelData[17];
								String emp_logged_once = excelData[24];

								if (!error_row.contentEquals("")) {
									temp.add(excelData[0]);
//									temp.add(excelData[1]);
									temp.add(error_row);
									error_records.add(temp);
									continue;
								}

								List<MstDivisionListModel> division_list = new ArrayList<MstDivisionListModel>();

								MstDivisionListModel mstDivisionListModel = new MstDivisionListModel();
								mstDivisionListModel.setEmp_id(emp_id);
								mstDivisionListModel.setEmp_name(emp_name);
								mstDivisionListModel.setEmp_desg(emp_desg);
								mstDivisionListModel.setEmp_gender(emp_gender);
								mstDivisionListModel.setEmp_grade(emp_grade);
								mstDivisionListModel.setEmp_emailid(emp_emailid);
								mstDivisionListModel.setEmp_manager(emp_manager);
								mstDivisionListModel.setEmp_div(emp_div);
								if (emp_doj != null) {
									mstDivisionListModel.setEmp_doj(
											LocalDateTime.ofInstant(emp_doj.toInstant(), ZoneId.systemDefault()));

								} else {
									mstDivisionListModel.setEmp_doj(null);
								}

								if (emp_dob != null) {
									mstDivisionListModel.setEmp_dob(
											LocalDateTime.ofInstant(emp_dob.toInstant(), ZoneId.systemDefault()));

								} else {
									mstDivisionListModel.setEmp_dob(null);
								}

								mstDivisionListModel.setEmp_com_code(emp_com_code);
								mstDivisionListModel.setEmp_com(emp_com);
								mstDivisionListModel.setEmp_loc_code(emp_loc_code);
								mstDivisionListModel.setEmp_loc(emp_loc);
								mstDivisionListModel.setEmp_dep_code(emp_dep_code);
								mstDivisionListModel.setEmp_dep(emp_dep);
								mstDivisionListModel.setEmp_status(emp_status);
								mstDivisionListModel.setEmp_job_grade(emp_job_grade);
								mstDivisionListModel.setEmp_total_course_completed(emp_total_course_completed);
								mstDivisionListModel.setEmp_total_course_passed(emp_total_course_passed);
								mstDivisionListModel.setEmp_total_course_failed(emp_total_course_failed);
								mstDivisionListModel.setEmp_courses_comp_other_div(emp_courses_comp_other_div);
								mstDivisionListModel.setEmp_courses_pass_other_div(emp_courses_pass_other_div);
								mstDivisionListModel.setEmp_courses_fail_other_div(emp_courses_fail_other_div);
								mstDivisionListModel.setEmp_logged_once(emp_logged_once);
								mstDivisionListModel.setUpdated_by(emp_det.getEmp_code());
								mstDivisionListModel.setUpdated_on(LocalDateTime.now());
								division_list.add(mstDivisionListModel);
								System.out.println("BEFORE UPDATE " + r);
								mstDivisionListService.saveorupdateAll(division_list);
								System.out.println("count " + r);

							}
						}

						if (wb != null) {
							wb.close();
						}
						if (error_records.size() > 0) {
							output.setError_records(error_records);
						}

//						mstDivisionListService.saveorupdateAll(division_list);

					} catch (Exception ex) {
						System.out.println("Error in reading file:" + ex.getMessage());
						ex.printStackTrace();
						try {
							if (uploadedStream != null)
								uploadedStream.close();
						} catch (IOException ex1) {
							System.out.println("Exception in closing file:" + ex.getMessage());
						}
						output.setError_msg("Error in reading file:" + ex.getMessage());

					}

				}
			}

			return output;

		} catch (Exception e) {
			System.out.println("Exception Occurred");
			e.printStackTrace();
			output.setError_msg(e.getLocalizedMessage());
			return output;
		} finally {
			try {
				if (uploadedStream != null)
					uploadedStream.close();
			} catch (IOException ex) {
				System.out.println("Exception in closing file:" + ex.getMessage());
			}

		}

	}
	
	
	
	@ResponseBody
	@RequestMapping(value = "/uploadUsageMaster", method = RequestMethod.POST)
	public UploadErrorBean uploadUsageMaster(@RequestParam("report_csv_file") MultipartFile file,
			HttpServletRequest request, final RedirectAttributes redirectAttributes, HttpSession session,
			HttpServletResponse response) {

		InputStream uploadedStream = null;
		System.out.println("in uploadUsageMaster");

		UploadErrorBean output = new UploadErrorBean();

		ArrayList<ArrayList<String>> error_records = new ArrayList<ArrayList<String>>();

		try {

			String[] x = null;
			uploadedStream = file.getInputStream();

			String doc_name = "";
			String sDataFile = file.getName();
			if (sDataFile != null && sDataFile != "") {
				doc_name += sDataFile.substring(sDataFile.lastIndexOf("\\") + 1, sDataFile.length() - 0) + ";";
				x = doc_name.split(";");
			}

			if (x != null) {
				String filename = x[0];
				if (filename != null && !filename.equals("")) {
					try {

						XSSFWorkbook wb = new XSSFWorkbook(uploadedStream);

						XSSFSheet sheet = wb.getSheetAt(0);
						int rows = sheet.getPhysicalNumberOfRows();

						int noOfColumns = sheet.getRow(0).getPhysicalNumberOfCells();

						if (noOfColumns > 22) {
							output.setError_msg("Uploaded file is not in proper format");

						}

						DataFormatter fmt = new DataFormatter();

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
					
//			String[] columns = rows[0].split(",");
						for (int r = 1; r < rows; r++) {

							String cellValue = null;
							String[] excelData = new String[25];
							XSSFRow row = sheet.getRow(r);

							if (row != null) {
								Iterator<Cell> cellIterator = row.iterator();
								int ii = 0;

								Date start_date = null;
								Date end_date = null;
								float score = 0;

								float min_percentage_grade = 0;

								float course_duration = 0;

								float time_elapsed = 0;

								int current_attempt_indicator = 0;

							

								ArrayList<String> temp = new ArrayList<String>();

								String error_row = "";

								while (cellIterator.hasNext()) {
									Cell currentCell = cellIterator.next();
									if (currentCell != null) {

										cellValue = fmt.formatCellValue(currentCell);

										if (ii == 14) {
											try {
												start_date = currentCell.getDateCellValue();
											} catch (Exception e) {
												error_row = error_row + " Invalid start date ;";

											}
										}

										if (ii == 15) {
											try {
												end_date = currentCell.getDateCellValue();
											} catch (Exception e) {
												error_row = error_row + " Invalid end date ;";

											}
										}

										if (ii == 17) {
											try {
												score = (float) currentCell.getNumericCellValue();
											} catch (Exception e) {
												error_row = error_row + " Invalid score value ;";

											}
										}
										if (ii == 18) {
											try {
												min_percentage_grade = (float) currentCell.getNumericCellValue();
											} catch (Exception e) {
												error_row = error_row + " Min_percentage_grade is ;";

											}
										}
										if (ii == 19) {
											try {
												course_duration = (float) currentCell.getNumericCellValue();
											} catch (Exception e) {
												error_row = error_row + " Total course duration ;";

											}
										}
										if (ii == 20) {
											try {
												time_elapsed = (float) currentCell.getNumericCellValue();
											} catch (Exception e) {
												error_row = error_row
														+ " Total time elapsed:  ;";

											}
										}
										if (ii == 21) {
											try {
												current_attempt_indicator = (int) currentCell.getNumericCellValue();
											} catch (Exception e) {
												error_row = error_row + " No of attempts made ;";

											}
										}
										
										if (!cellValue.trim().isEmpty()) {
											excelData[ii] = cellValue;
											if (cellValue != null && cellValue.trim().isEmpty()) {
												excelData[ii] = null;
											}
										}
										ii++;
									}
								}

								String emp_id = excelData[0];
								String emp_name = excelData[1];
								String emp_active = excelData[2];
								String emp_designation = excelData[3];
								String emp_emailid = excelData[4];
								String emp_division = excelData[5];
								String emp_company = excelData[6];
								String emp_location = excelData[7];
								String emp_dep = excelData[8];
								String emp_grade = excelData[9];
								String emp_activity_code = excelData[10];
								String emp_activity_name = excelData[11];
								String emp_domain = excelData[12];
								String emp_registration_status = excelData[13];
								String emp_success_status = excelData[16];
								

								if (!error_row.contentEquals("")) {
									temp.add(excelData[0]);
									temp.add(excelData[1]);
									temp.add(error_row);
									error_records.add(temp);
									continue;
								}

								List<MstUsageListModel> usage_list = new ArrayList<MstUsageListModel>();

								MstUsageListModel mstUsageListModel = new MstUsageListModel();
								mstUsageListModel.setEmp_id(emp_id);
								mstUsageListModel.setEmp_name(emp_name);
								mstUsageListModel.setActive(emp_active);
								mstUsageListModel.setDesignation(emp_designation);
								mstUsageListModel.setEmp_mail(emp_emailid);
								mstUsageListModel.setDivision(emp_division);
								mstUsageListModel.setCompany(emp_company);
								mstUsageListModel.setLocation(emp_location);
								mstUsageListModel.setDepartment(emp_dep);
								mstUsageListModel.setGrade(emp_grade);
								mstUsageListModel.setActivity_code(emp_activity_code);
								mstUsageListModel.setActivity_name(emp_activity_name);
								mstUsageListModel.setDomain(emp_domain);
								mstUsageListModel.setRegistration_status(emp_registration_status);
								mstUsageListModel.setSuccess_status(emp_success_status);
								
								if (start_date != null) {
									mstUsageListModel.setStart_date(
											LocalDateTime.ofInstant(start_date.toInstant(), ZoneId.systemDefault()));

								} else {
									mstUsageListModel.setStart_date(null);
								}

								if (end_date != null) {
									mstUsageListModel.setEnd_date(
											LocalDateTime.ofInstant(end_date.toInstant(), ZoneId.systemDefault()));

								} else {
									mstUsageListModel.setEnd_date(null);
								}
								
								mstUsageListModel.setScore(score);
								mstUsageListModel.setCourse_duration(course_duration);
								mstUsageListModel.setMinimum_percentage_grade(min_percentage_grade)	;	
								mstUsageListModel.setTime_elapsed(time_elapsed);
								mstUsageListModel.setCurrent_attempt_indicator(current_attempt_indicator);
								
								mstUsageListModel.setUpdated_by(emp_det.getEmp_code());
								mstUsageListModel.setUpdated_on(LocalDateTime.now());
								usage_list.add(mstUsageListModel);
								System.out.println("BEFORE UPDATE " + r);
								mstUsageListService.save(mstUsageListModel);
								System.out.println("count " + r);

							}
						}

						if (wb != null) {
							wb.close();
						}
						if (error_records.size() > 0) {
							output.setError_records(error_records);
						}

//						mstDivisionListService.saveorupdateAll(division_list);

					} catch (Exception ex) {
						System.out.println("Error in reading file:" + ex.getMessage());
						ex.printStackTrace();
						try {
							if (uploadedStream != null)
								uploadedStream.close();
						} catch (IOException ex1) {
							System.out.println("Exception in closing file:" + ex.getMessage());
						}
						output.setError_msg("Error in reading file:" + ex.getMessage());

					}

				}
			}

			return output;

		} catch (Exception e) {
			System.out.println("Exception Occurred");
			e.printStackTrace();
			output.setError_msg(e.getLocalizedMessage());
			return output;
		} finally {
			try {
				if (uploadedStream != null)
					uploadedStream.close();
			} catch (IOException ex) {
				System.out.println("Exception in closing file:" + ex.getMessage());
			}

		}

	}

}
