package com.iocl.ImpactAssessmentQuiz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.iocl.ImpactAssessmentQuiz.model.MstAdminModel;

public interface MstAdminRepository extends CrudRepository<MstAdminModel, Long> {

	@Query(" from MstAdminModel where employeeModel.current_divn_cd in (:div_code)")
	public List<MstAdminModel> findDivisionData(@Param("div_code") List<String> div_code);

}
