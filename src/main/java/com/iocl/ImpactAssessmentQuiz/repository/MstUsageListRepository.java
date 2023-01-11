package com.iocl.ImpactAssessmentQuiz.repository;

import java.util.ArrayList;

import java.util.List;


import com.iocl.ImpactAssessmentQuiz.bean.MasterCount;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.iocl.ImpactAssessmentQuiz.model.MstUsageListModel;

public interface MstUsageListRepository extends CrudRepository< MstUsageListModel, String> {
	
	
	

	@Query(" select domain, count(*) AS courses_completed  from MstUsageListModel where trim(emp_id) =:emp_id group by domain")
	public ArrayList<ArrayList<String>> func_wise_course_completion(@Param("emp_id") String emp_id);
	@Query(" select activity_code as course_code ,activity_name as course_name  from MstUsageListModel where trim(emp_id) =:emp_id and domain =:domain")
	public List<Courses> completed_activities(@Param("emp_id") String emp_id,@Param("domain") String domain);
	@Query("select count(*) as count , TO_CHAR(end_date,'DD-MM-YYYY') as e_date,emp_id from MstUsageListModel WHERE  TO_CHAR(end_date,'MM-YYYY')=:month GROUP BY TO_CHAR(end_date,'DD-MM-YYYY'), emp_id HAVING emp_id=:id")
	public List<BarChart> getBarStats(@Param("month") String date , @Param("id") String id);
	@Query("select activity_code as course_code, success_status as status ,activity_name as course_name, TO_CHAR(start_date,'DD-MM-YYYY') as begin_date ,TO_CHAR(end_date,'DD-MM-YYYY') as completion_date from MstUsageListModel where emp_id=:emp_id")
	public List<Courses> getCourses(@Param("emp_id") String emp_id);
	@Query("select activity_code as course_code ,activity_name as course_name, TO_CHAR(start_date,'DD-MM-YYYY') as begin_date ,TO_CHAR(end_date,'DD-MM-YYYY') as completion_date from MstUsageListModel where emp_id=:emp_id and UPPER(success_status)=:status")
	public List<Courses> getCoursesByStatus(@Param("emp_id")String id, @Param("status")String status);



	@Query(" select count(*) as record_count, TO_CHAR(updated_on,'DD-MM-YYYY')as update_on,updated_by as update_by from MstUsageListModel group by TO_CHAR(updated_on,'DD-MM-YYYY'),updated_by")
	public List<MasterCount> find_count_groupby();
@Query("SELECT activity_code as course_code, activity_name as course_name,TO_CHAR(start_date,'DD-MM-YYYY') as begin_date,TO_CHAR(end_date,'DD-MM-YYYY') as completion_date FROM MstUsageListModel WHERE activity_code IN (SELECT activity_code FROM MstActivityListModel WHERE division NOT IN (SELECT emp_div FROM MstDivisionListModel WHERE emp_id=:emp_id)) AND emp_id=:emp_id AND UPPER(success_status)=:status")
	List<Courses> getDivCoursesByStatus(@Param("emp_id")String id, @Param("status")String status);
@Query("SELECT activity_code as course_code, activity_name as course_name,TO_CHAR(start_date,'DD-MM-YYYY') as begin_date,TO_CHAR(end_date,'DD-MM-YYYY') as completion_date,success_status as status FROM MstUsageListModel WHERE activity_code IN (SELECT activity_code FROM MstActivityListModel WHERE division NOT IN (SELECT emp_div FROM MstDivisionListModel WHERE emp_id=:emp_id)) AND emp_id=:emp_id")
	List<Courses> getDivCourses(@Param("emp_id")String id);
}

