package com.iocl.ImpactAssessmentQuiz.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.iocl.ImpactAssessmentQuiz.model.TrnQuizEventModel;

public interface TrnQuizEventRepository extends CrudRepository<TrnQuizEventModel, Long> {

	@Query(value = "select event_id.NEXTVAL from dual", nativeQuery = true)
	public Long getEventId();

}
