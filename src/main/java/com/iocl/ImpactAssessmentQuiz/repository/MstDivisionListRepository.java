package com.iocl.ImpactAssessmentQuiz.repository;

import java.util.ArrayList;
import java.util.List;

import com.iocl.ImpactAssessmentQuiz.bean.MasterCount;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.iocl.ImpactAssessmentQuiz.model.MstDivisionListModel;

public interface MstDivisionListRepository extends CrudRepository< MstDivisionListModel, String> {



	

	@Query(" from MstDivisionListModel where emp_manager=:emp_manager")
	public ArrayList<MstDivisionListModel> findbyemp_manager(@Param("emp_manager") String emp_manager);



	@Query(" select count(*) as record_count, TO_CHAR(updated_on,'DD-MM-YYYY')as update_on,updated_by as update_by from MstDivisionListModel group by TO_CHAR(updated_on,'DD-MM-YYYY'),updated_by")
	public List<MasterCount> find_count_groupby();
}
