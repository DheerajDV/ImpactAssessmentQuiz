package com.iocl.ImpactAssessmentQuiz.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.iocl.ImpactAssessmentQuiz.model.TrnAutoMailModel;

public interface TrnAutoMailRepository extends CrudRepository<TrnAutoMailModel, Long> {
	@Query(value = "select message_id.NEXTVAL from dual", nativeQuery = true)
	public Long getMessageID();

}
