package com.iocl.ImpactAssessmentQuiz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.iocl.ImpactAssessmentQuiz.model.TrnQuizResultHeader;

public interface TrnQuizResultHeaderRepository extends CrudRepository<TrnQuizResultHeader, Long> {

	@Query(" from TrnQuizResultHeader where employeeModel.current_divn_cd in (:div_code)")
	public List<TrnQuizResultHeader> findDivisionData(@Param("div_code") List<String> div_code);

	@Query(" from TrnQuizResultHeader where employeeModel.current_divn_cd in (:div_code) and employeeModel.curr_comp_code in (:allowed_comp_code) ")
	public List<TrnQuizResultHeader> findDivisionDataCompCode(@Param("div_code") List<String> div_code,
			@Param("allowed_comp_code") List<String> allowed_comp_code);

}
