package com.iocl.ImpactAssessmentQuiz.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "QUIZ_INSTRUCTIONS")
public class MstQuizInstrunctions {

	@Id
	@Column(name = "ID")
	private Long id;

	@Column(name = "INSTRUCTION")
	private String instruction;

	@Column(name = "DIV_CD")
	private String div_cd;

	@Column(name = "SEQ")
	private Long seq;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the instruction
	 */
	public String getInstruction() {
		return instruction;
	}

	/**
	 * @param instruction the instruction to set
	 */
	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}

	/**
	 * @return the div_cd
	 */
	public String getDiv_cd() {
		return div_cd;
	}

	/**
	 * @param div_cd the div_cd to set
	 */
	public void setDiv_cd(String div_cd) {
		this.div_cd = div_cd;
	}

	/**
	 * @return the seq
	 */
	public Long getSeq() {
		return seq;
	}

	/**
	 * @param seq the seq to set
	 */
	public void setSeq(Long seq) {
		this.seq = seq;
	}

}
