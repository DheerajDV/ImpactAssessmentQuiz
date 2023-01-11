package com.iocl.ImpactAssessmentQuiz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.iocl.ImpactAssessmentQuiz.model.TrnEmpWiseUsageID;
import com.iocl.ImpactAssessmentQuiz.model.TrnEmpWiseUsageModel;

public interface TrnEmpWiseUsageRepository extends CrudRepository<TrnEmpWiseUsageModel, TrnEmpWiseUsageID> {

	@Query(" from TrnEmpWiseUsageModel where trnEmpWiseUsageID.employeeModel.current_divn_cd in (:div_code) and trnEmpWiseUsageID.employeeModel.curr_comp_code in (:allowed_comp_code)")
	public List<TrnEmpWiseUsageModel> findDivisionDataCompCode(@Param("div_code") List<String> div_code,
			@Param("allowed_comp_code") List<String> allowed_comp_code);

	@Query(" from TrnEmpWiseUsageModel where trnEmpWiseUsageID.employeeModel.current_divn_cd in (:div_code)")
	public List<TrnEmpWiseUsageModel> findDivisionData(@Param("div_code") List<String> div_code);

}
