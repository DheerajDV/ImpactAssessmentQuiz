package com.iocl.ImpactAssessmentQuiz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.iocl.ImpactAssessmentQuiz.model.MstQuizQuestionBankModel;

public interface MstQuizQuestionBankRepository extends CrudRepository<MstQuizQuestionBankModel, Long> {

	@Query("select count(*) from MstQuizQuestionBankModel where mstActivityListModel.activity_code "
			+ " in (select trnQuizActivityMappingID.mstActivityListModel.activity_code from TrnQuizActivityMappingModel "
			+ " where trnQuizActivityMappingID.trnQuizHeaderModel.quiz_id=:quiz_id)")
	public Long findAvailableQuestions(@Param("quiz_id") Long quiz_id);

	@Query("select count(*) from MstQuizQuestionBankModel where mstActivityListModel.activity_code "
			+ " in (:module_list)")
	public Long findTotalModuleQuestions(@Param("module_list") List<String> module_list);

}
