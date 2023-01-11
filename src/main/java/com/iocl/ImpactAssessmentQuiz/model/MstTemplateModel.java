package com.iocl.ImpactAssessmentQuiz.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MST_TEMPLATE")
public class MstTemplateModel {

	@Id
	@Column(name = "TEMPLATE_ID")
	private Long template_id;

	@Column(name = "TEMPLATE_DESC")
	private String template_desc;

	/**
	 * @return the template_id
	 */
	public Long getTemplate_id() {
		return template_id;
	}

	/**
	 * @param template_id the template_id to set
	 */
	public void setTemplate_id(Long template_id) {
		this.template_id = template_id;
	}

	/**
	 * @return the template_desc
	 */
	public String getTemplate_desc() {
		return template_desc;
	}

	/**
	 * @param template_desc the template_desc to set
	 */
	public void setTemplate_desc(String template_desc) {
		this.template_desc = template_desc;
	}

}
