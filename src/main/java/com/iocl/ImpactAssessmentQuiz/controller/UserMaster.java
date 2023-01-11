package com.iocl.ImpactAssessmentQuiz.controller;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.iocl.ImpactAssessmentQuiz.model.MstAdminModel;
import com.iocl.ImpactAssessmentQuiz.model.SessionMaster;
import com.iocl.ImpactAssessmentQuiz.service.EmployeeService;
import com.iocl.ImpactAssessmentQuiz.service.MstAdminService;

@Controller
public class UserMaster {

	@Autowired
	EmployeeService employeeService;

	@Autowired
	MstAdminService mstAdminService;

	@Autowired
	ResourceLoader resourceLoader;

	@RequestMapping(value = "/UserMaster", method = RequestMethod.GET)
	public ModelAndView userMaster(HttpServletRequest request, HttpSession session,
			final RedirectAttributes redirectAttributes) {
		if (!request.isRequestedSessionIdValid()) {
			return new ModelAndView("redirect:/");
		}
		try {
			ModelAndView model = new ModelAndView("userMaster");

			return model;
		} catch (Exception e) {
			System.out.println("Exception in SIAQ :- ");
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("errorMsg", "Error occured.");
			return new ModelAndView("redirect:/Home");

		}
	}

	@RequestMapping(value = "/adminDataJSON", method = RequestMethod.GET)
	@ResponseBody
	public List<MstAdminModel> adminDataJSON(HttpServletRequest request, HttpSession session,
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

		List<MstAdminModel> data = null;

		MstAdminModel mstAdminModel = mstAdminService.findOne(emp_det.getEmp_code());
		List<String> div_code = new ArrayList<String>();
		div_code.add(mstAdminModel.getDiv_code());
		if (mstAdminModel.getDiv_code().contentEquals("1")) {
			div_code.add("5");
			div_code.add("9");
		}

		if (mstAdminModel != null && (mstAdminModel.getDiv_code()).contentEquals("*")) {
			data = mstAdminService.findAll();
		} else if (mstAdminModel != null && !(mstAdminModel.getDiv_code()).contentEquals("*")) {
			data = mstAdminService.findDivisionData(div_code);
		}

		return data;

	}

	@RequestMapping(value = "/AdminUploadTemplate", method = RequestMethod.GET)
	public ResponseEntity<Resource> AdminUploadTemplate(HttpServletRequest request,
			final RedirectAttributes redirectAttributes, HttpSession session, HttpServletResponse response) {

		try {
			Resource resource = resourceLoader.getResource("classpath:Admin_Upload_Format.xlsx");

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
	@RequestMapping(value = "/uploadAdminMaster", method = RequestMethod.POST)
	public UploadErrorBean uploadAdminMaster(@RequestParam("report_csv_file") MultipartFile file,
			HttpServletRequest request, final RedirectAttributes redirectAttributes, HttpSession session,
			HttpServletResponse response) {

		InputStream uploadedStream = null;

		List<MstAdminModel> admin_list = new ArrayList<MstAdminModel>();

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

						if (noOfColumns > 1) {
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
						for (int r = 1; r < rows; r++) {

							String cellValue = null;
							String[] excelData = new String[11];
							XSSFRow row = sheet.getRow(r);

							if (row != null) {
								Iterator<Cell> cellIterator = row.iterator();
								int ii = 0;

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

										if (!cellValue.trim().isEmpty()) {
											excelData[ii] = cellValue;
											if (cellValue != null && cellValue.trim().isEmpty()) {
												excelData[ii] = null;
											}
										}
										ii++;
									}
								}

								boolean invalid_emp = false;

								String emp_code = excelData[0];

								try {
									Long.valueOf(emp_code);

								} catch (NumberFormatException e) {
									error_row = error_row + " Incorrect employee no ;";
									invalid_emp = true;
								}

								EmployeeModel emp_model = null;
								if (invalid_emp == false) {
									emp_model = employeeService.findEmpDetails(Long.valueOf(emp_code));

									if (emp_model == null) {
										error_row = error_row + " Employee code does not exist ;";
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
									temp.add(error_row);
									error_records.add(temp);
									continue;
								}

								MstAdminModel admin_model_temp = new MstAdminModel();

								admin_model_temp.setEmp_code(Long.valueOf(emp_code));
								admin_model_temp.setEmployeeModel(emp_model);
								admin_model_temp.setDiv_code(emp_model.getCurrent_divn_cd());
								admin_model_temp.setUpdated_by(emp_det.getEmp_code());
								admin_model_temp.setUpdated_on(LocalDateTime.now());
								admin_list.add(admin_model_temp);

							}
						}
						if (wb != null) {
							wb.close();
						}
						if (error_records.size() > 0) {
							output.setError_records(error_records);
						}

						mstAdminService.saveorupdateAll(admin_list);

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
