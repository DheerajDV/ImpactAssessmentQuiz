package com.iocl.ImpactAssessmentQuiz.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.iocl.ImpactAssessmentQuiz.model.MstActivityListModel;

public interface MstActivityListRepository extends CrudRepository<MstActivityListModel, String> {

	@Query("select distinct division from MstActivityListModel where division is not null order by division ")
	public ArrayList<String> getDivisionList();

	@Query("select distinct domain from MstActivityListModel where division = case when :division='All' then division else :division end  and domain is not null order by domain ")
	public ArrayList<String> getDomainList(@Param("division") String division);

	@Query("select distinct sub_domain from MstActivityListModel where division= case when :division='All' then division else :division end"
			+ " and domain= case when :domain='All' then domain else :domain end and sub_domain is not null order by sub_domain ")
	public ArrayList<String> getSubDomainList(@Param("division") String division, @Param("domain") String domain);

	@Query(" from MstActivityListModel where division= case when :division='All' then division else :division end"
			+ " and domain= case when :domain='All' then domain else :domain end and sub_domain= case when :sub_domain='All' then sub_domain else :sub_domain end"
			+ " and activity_code is not null order by sub_domain ")
	public List<MstActivityListModel> getModuleList(@Param("division") String division, @Param("domain") String domain,
			@Param("sub_domain") String sub_domain);
	
	
	
	

	

}
