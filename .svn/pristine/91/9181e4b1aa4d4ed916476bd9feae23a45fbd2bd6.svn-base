package com.iocl.ImpactAssessmentQuiz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.iocl.ImpactAssessmentQuiz.model.TrnQuizResponseID;
import com.iocl.ImpactAssessmentQuiz.model.TrnQuizResponseModel;

public interface TrnQuizResponseRepository extends CrudRepository<TrnQuizResponseModel, TrnQuizResponseID> {

	@Query(" from TrnQuizResponseModel where quiz_id=:quiz_id order by question_seq ")
	public List<TrnQuizResponseModel> findQuizQuestions(@Param("quiz_id") Long quiz_id);

	@Query("select distinct trnQuizResponseID.mstQuizQuestionBankModel.question_id,question_seq,question_desc from TrnQuizResponseModel where quiz_id=:quiz_id and question_seq=:seq")
	public Object[][] findQuestionNo(@Param("quiz_id") Long quiz_id, @Param("seq") Long seq);

	@Query("select trnQuizResponseID.option_seq,option_desc,emp_answer_flg from TrnQuizResponseModel where quiz_id=:quiz_id and trnQuizResponseID.mstQuizQuestionBankModel.question_id=:question_id"
			+ " order by trnQuizResponseID.option_seq")
	public String[][] findQuestionOptions(@Param("quiz_id") Long quiz_id, @Param("question_id") Long question_id);

	@Query(value="SELECT DISTINCT\r\n" + 
			"    question_id,\r\n" + 
			"    question_seq,\r\n" + 
			"    response_flg\r\n" + 
			"FROM\r\n" + 
			"    (\r\n" + 
			"        SELECT\r\n" + 
			"            question_id,\r\n" + 
			"            question_seq,\r\n" + 
			"            CASE\r\n" + 
			"                    WHEN emp_response_time IS NULL THEN 'N'\r\n" + 
			"                    ELSE 'Y'\r\n" + 
			"                END\r\n" + 
			"            AS response_flg\r\n" + 
			"        FROM\r\n" + 
			"            trn_quiz_response\r\n" + 
			"        WHERE\r\n" + 
			"            quiz_id = :quiz_id\r\n" + 
			"    )\r\n" + 
			"ORDER BY\r\n" + 
			"    question_seq",nativeQuery=true)
	public String[][] findQuestionResponse(@Param("quiz_id") Long quiz_id);

	@Query("from TrnQuizResponseModel where quiz_id=:quiz_id and trnQuizResponseID.mstQuizQuestionBankModel.question_id=:question_id"
			+ " order by trnQuizResponseID.option_seq")
	public List<TrnQuizResponseModel> findQuestionModel(@Param("quiz_id") Long quiz_id,
			@Param("question_id") Long question_id);

	@Query("SELECT COUNT(DISTINCT trnQuizResponseID.mstQuizQuestionBankModel.question_id) FROM TrnQuizResponseModel WHERE  trnQuizResponseID.trnQuizHeaderModel.quiz_id = :quiz_id"
			+ "  AND   emp_answer_flg = 'Y' AND   correct_ans_flg = emp_answer_flg")
	public Long getEmployeeScore(@Param("quiz_id") Long quiz_id);

	@Query("SELECT COUNT(DISTINCT trnQuizResponseID.mstQuizQuestionBankModel.question_id) FROM TrnQuizResponseModel WHERE trnQuizResponseID.trnQuizHeaderModel.quiz_id = :quiz_id "
			+ " AND   emp_answer_flg = 'Y'")
	public Long getTotalAttemptedQues(@Param("quiz_id") Long quiz_id);

	@Query("SELECT COUNT(DISTINCT trnQuizResponseID.mstQuizQuestionBankModel.question_id) FROM TrnQuizResponseModel WHERE  trnQuizResponseID.trnQuizHeaderModel.quiz_id = :quiz_id"
			+ "  AND   emp_answer_flg = 'Y' AND   correct_ans_flg = emp_answer_flg")
	public Long getTotalCorrectQues(@Param("quiz_id") Long quiz_id);

	@Query("SELECT COUNT(DISTINCT trnQuizResponseID.mstQuizQuestionBankModel.question_id) FROM TrnQuizResponseModel WHERE  trnQuizResponseID.trnQuizHeaderModel.quiz_id = :quiz_id"
			+ "  AND   emp_answer_flg = 'Y' AND   correct_ans_flg != emp_answer_flg")
	public Long getTotalWrong(@Param("quiz_id") Long quiz_id);

	@Query("SELECT COUNT(DISTINCT trnQuizResponseID.mstQuizQuestionBankModel.question_id) FROM TrnQuizResponseModel WHERE  trnQuizResponseID.trnQuizHeaderModel.quiz_id = :quiz_id")
	public Long getTotalQuizQues(@Param("quiz_id") Long quiz_id);

}
