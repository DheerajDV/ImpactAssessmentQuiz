package com.iocl.ImpactAssessmentQuiz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.iocl.ImpactAssessmentQuiz.model.MstQuizInstrunctions;

public interface MstQuizInstructionsRepository extends CrudRepository<MstQuizInstrunctions, Long> {
	@Query(" from MstQuizInstrunctions order by id")
	public List<MstQuizInstrunctions> getInstructions();

	@Query(" from MstQuizInstrunctions where div_cd=:current_divn_cd order by id")
	public List<MstQuizInstrunctions> getInstructions(@Param("current_divn_cd") String current_divn_cd);

}
