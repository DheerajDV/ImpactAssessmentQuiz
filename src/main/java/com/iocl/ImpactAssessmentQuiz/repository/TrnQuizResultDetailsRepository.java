package com.iocl.ImpactAssessmentQuiz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.iocl.ImpactAssessmentQuiz.model.TrnQuizResultDetails;
import com.iocl.ImpactAssessmentQuiz.model.TrnQuizResultDetailsID;

public interface TrnQuizResultDetailsRepository extends CrudRepository<TrnQuizResultDetails, TrnQuizResultDetailsID> {

	@Query(" from TrnQuizResultDetails where employeeModel.current_divn_cd in (:div_code)")
	public List<TrnQuizResultDetails> findDivisionData(@Param("div_code") List<String> div_code);

	@Query(" from TrnQuizResultDetails where employeeModel.current_divn_cd in (:div_code) and employeeModel.curr_comp_code in (:allowed_comp_code) ")
	public List<TrnQuizResultDetails> findDivisionDataCompCode(@Param("div_code") List<String> div_code,
			@Param("allowed_comp_code") List<String> allowed_comp_code);

}
