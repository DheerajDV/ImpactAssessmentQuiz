package com.iocl.ImpactAssessmentQuiz.service;

import java.text.ParseException;
import java.time.YearMonth;
import java.util.*;

import com.iocl.ImpactAssessmentQuiz.bean.MasterCount;
import com.iocl.ImpactAssessmentQuiz.repository.BarChart;
import com.iocl.ImpactAssessmentQuiz.repository.Courses;
import com.iocl.ImpactAssessmentQuiz.repository.CoursesCompleted;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iocl.ImpactAssessmentQuiz.model.MstUsageListModel;
import com.iocl.ImpactAssessmentQuiz.repository.MstUsageListRepository;

@Service
public class MstUsageListService {

	public MstUsageListService(MstUsageListRepository mstUsageListRepository) {
		this.mstUsageListRepository = mstUsageListRepository;
	}

	@Autowired
	private  final MstUsageListRepository mstUsageListRepository;

	public void saveorupdateAll(List<MstUsageListModel> usage_list) {
		mstUsageListRepository.saveAll(usage_list);

	}

	public void save(MstUsageListModel usage_model) {
		mstUsageListRepository.save(usage_model);

	}

	public void deleteAll() {
		mstUsageListRepository.deleteAll();

	}

	public List<MstUsageListModel> findAll() {
		return (List<MstUsageListModel>) mstUsageListRepository.findAll();
	}

	public ArrayList<ArrayList<String>> func_wise_course_list(String emp_id) {
		return mstUsageListRepository.func_wise_course_completion(emp_id);

	}

	public List<MasterCount> find_count_groupby() {
		return (List<MasterCount>) mstUsageListRepository.find_count_groupby();
	}

	public int[] getBarstats(int month, int year, long emp_code) throws ParseException {

		YearMonth  yearmonth=YearMonth.of(year, month);
		int days=yearmonth.lengthOfMonth();

		String months=Integer.toString(month)+"-"+Integer.toString(year);

		List<BarChart> count=mstUsageListRepository.getBarStats(months,appendZeros(String.valueOf(emp_code)));
		int[] array=new int[days];
		for(int i =0;i<days;i++) {
			array[i]=0;
		}

for(int i=0;i<count.size();i++) {
	int date= Integer.parseInt(count.get(i).getE_date().substring(0,2));
	array[date]=count.get(i).getCount();
}




		// TODO Auto-generated method stub
		return array;
}

	public List<Courses> getCoursescompleted(long emp_code) {
		String id=appendZeros(String.valueOf(emp_code));


		List<Courses> coursesList=mstUsageListRepository.getCourses(id);

		return coursesList;
	}
	public String appendZeros(String id)
	{
		while(id.length()<8){
			id="0"+id;
		}return id;


}

	public List getCoursesByStatus(Long emp_code, String status) {
		String id=appendZeros(String.valueOf(emp_code));


		List<Courses> coursesList=mstUsageListRepository.getCoursesByStatus(id,status.toUpperCase());
		return coursesList;
	}
	public List getDivCoursesByStatus(Long emp_code, String status) {
		String id=appendZeros(String.valueOf(emp_code));


		List<Courses> coursesList=mstUsageListRepository.getDivCoursesByStatus(id,status.toUpperCase());
		return coursesList;
	}
	public List<Courses> Completed_Activity_Codes(Long emp_id, String domain) {
		String id=appendZeros(String.valueOf(emp_id));


		return  mstUsageListRepository.completed_activities(id,domain);

	}

	public List<Courses> getDivCompleted(long emp_code) {
		String id=appendZeros(String.valueOf(emp_code));


		List<Courses> coursesList=mstUsageListRepository.getDivCourses(id);

		return coursesList;
	}
}
