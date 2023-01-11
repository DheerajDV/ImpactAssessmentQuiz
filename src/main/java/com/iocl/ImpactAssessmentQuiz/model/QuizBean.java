package com.iocl.ImpactAssessmentQuiz.model;

public class QuizBean {

	private Long quiz_id;

	private Object[][] question_det;

	private String[][] option_det;

	private Long selected_seq;

	private String[][] ques_response;

	private Long quiz_questions;

	private Long quiz_time;
	
	/**
	 * @return the ques_response
	 */
	public String[][] getQues_response() {
		return ques_response;
	}

	/**
	 * @param ques_response the ques_response to set
	 */
	public void setQues_response(String[][] ques_response) {
		this.ques_response = ques_response;
	}

	/**
	 * @return the quiz_id
	 */
	public Long getQuiz_id() {
		return quiz_id;
	}

	/**
	 * @param quiz_id the quiz_id to set
	 */
	public void setQuiz_id(Long quiz_id) {
		this.quiz_id = quiz_id;
	}

	/**
	 * @return the question_det
	 */
	public Object[][] getQuestion_det() {
		return question_det;
	}

	/**
	 * @param question_det the question_det to set
	 */
	public void setQuestion_det(Object[][] question_det) {
		this.question_det = question_det;
	}

	/**
	 * @return the selected_seq
	 */
	public Long getSelected_seq() {
		return selected_seq;
	}

	/**
	 * @param selected_seq the selected_seq to set
	 */
	public void setSelected_seq(Long selected_seq) {
		this.selected_seq = selected_seq;
	}

	/**
	 * @return the option_det
	 */
	public String[][] getOption_det() {
		return option_det;
	}

	/**
	 * @param option_det the option_det to set
	 */
	public void setOption_det(String[][] option_det) {
		this.option_det = option_det;
	}

	/**
	 * @return the quiz_time
	 */
	public Long getQuiz_time() {
		return quiz_time;
	}

	/**
	 * @param quiz_time the quiz_time to set
	 */
	public void setQuiz_time(Long quiz_time) {
		this.quiz_time = quiz_time;
	}

	/**
	 * @return the quiz_questions
	 */
	public Long getQuiz_questions() {
		return quiz_questions;
	}

	/**
	 * @param quiz_questions the quiz_questions to set
	 */
	public void setQuiz_questions(Long quiz_questions) {
		this.quiz_questions = quiz_questions;
	}

}
