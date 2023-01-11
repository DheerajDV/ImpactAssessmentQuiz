package com.iocl.ImpactAssessmentQuiz.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.iocl.ImpactAssessmentQuiz.model.EmployeeModel;

public interface EmployeeRepository extends CrudRepository<EmployeeModel, Long> {

	@Query("select emp_name,design_short_desc,loc_name from EmployeeModel where emp_code=:emp_cd")
	public Object[][] getEmpDet(@Param("emp_cd") Long emp_cd);

	@Query("select distinct current_divn_cd,current_divn from EmployeeModel where current_divn_cd is not null order by current_divn")
	public ArrayList<ArrayList<String>> getAllDivision();

	@Query("select distinct current_divn_cd,current_divn from EmployeeModel where current_divn_cd is not null and current_divn_cd in (:div_code) order by current_divn")
	public ArrayList<ArrayList<String>> getAdminDivision(@Param("div_code") List<String> div_code);

	@Query("select distinct curr_comp_code,curr_comp from EmployeeModel where curr_comp_code is not null and "
			+ " current_divn_cd = case when :division='All' then current_divn_cd else :division end and emp_status_code='3'order by curr_comp_code")
	public ArrayList<ArrayList<String>> getCompanyCodeList(@Param("division") String division);

	@Query("select distinct pa_code,pa from EmployeeModel where pa_code is not null and current_divn_cd = case when :division='All' then current_divn_cd else :division end"
			+ " and curr_comp_code= case when :company_code='All' then curr_comp_code else :company_code end and emp_status_code='3'"
			+ "  order by pa_code")
	public ArrayList<ArrayList<String>> getLocationList(@Param("division") String division,
			@Param("company_code") String company_code);

	@Query("select distinct psa_code,psa from EmployeeModel where psa_code is not null and current_divn_cd = case when :division='All' then current_divn_cd else :division end"
			+ " and emp_status_code='3' and curr_comp_code=  case when :company_code='All' then curr_comp_code else :company_code end "
			+ " and pa_code=  case when :loc_code='All' then pa_code else :loc_code end   order by psa_code")
	public ArrayList<ArrayList<String>> getPsaCodeList(@Param("division") String division,
			@Param("company_code") String company_code, @Param("loc_code") String loc_code);

	@Query("select distinct emp_code,emp_name from EmployeeModel where emp_code is not null and current_divn_cd = case when :division='All' then current_divn_cd else :division end"
			+ " and emp_status_code='3' and curr_comp_code= case when :company_code='All' then curr_comp_code else :company_code end and "
			+ " pa_code=  case when :loc_code='All' then pa_code else :loc_code end and psa_code=  case when :psa_code='All' then psa_code else :psa_code end order by emp_code")
	public ArrayList<ArrayList<String>> getEmpCodeList(@Param("division") String division,
			@Param("company_code") String company_code, @Param("loc_code") String loc_code,
			@Param("psa_code") String psa_code);

	@Query(" from EmployeeModel where emp_code is not null and current_divn_cd = case when :division='All' then current_divn_cd else :division end"
			+ " and emp_status_code='3' and curr_comp_code= case when :company_code='All' then curr_comp_code else :company_code end and "
			+ " pa_code=  case when :loc_code='All' then pa_code else :loc_code end and psa_code=  case when :psa_code='All' then psa_code else :psa_code end order by emp_code")
	public List<EmployeeModel> getEmployeeList(@Param("division") String division,
			@Param("company_code") String company_code, @Param("loc_code") String loc_code,
			@Param("psa_code") String psa_code);

	@Query(value = "select so_code from MST_RO_SO_MAPPING where REGION_CODE =:currcompc", nativeQuery = true)
	public List<String> findSOMapping(@Param("currcompc") String currcompc);

	@Query("select distinct curr_comp_code,curr_comp from EmployeeModel where curr_comp_code is not null and "
			+ " current_divn_cd = case when :division='All' then current_divn_cd else :division end and emp_status_code='3' and curr_comp_code in (:allowed_comp_code) order by curr_comp_code")
	public ArrayList<ArrayList<String>> getCompanyCodeList(@Param("division") String division,
			@Param("allowed_comp_code") List<String> allowed_comp_code);

	@Query("select distinct pa_code,pa from EmployeeModel where pa_code is not null and current_divn_cd = case when :division='All' then current_divn_cd else :division end"
			+ " and curr_comp_code= case when :company_code='All' then curr_comp_code else :company_code end and emp_status_code='3'  and curr_comp_code in (:allowed_comp_code) "
			+ "  order by pa_code")
	public ArrayList<ArrayList<String>> getLocationListLocation(@Param("division") String division,
			@Param("company_code") String company_code, @Param("allowed_comp_code") List<String> allowed_comp_code);

	@Query("select distinct psa_code,psa from EmployeeModel where psa_code is not null and current_divn_cd = case when :division='All' then current_divn_cd else :division end"
			+ " and emp_status_code='3' and curr_comp_code=  case when :company_code='All' then curr_comp_code else :company_code end "
			+ " and pa_code=  case when :loc_code='All' then pa_code else :loc_code end and curr_comp_code in (:allowed_comp_code)   order by psa_code")
	public ArrayList<ArrayList<String>> getPsaCodeListLocation(@Param("division") String division,
			@Param("company_code") String company_code, @Param("loc_code") String loc_code,
			@Param("allowed_comp_code") List<String> allowed_comp_code);

	@Query("select distinct emp_code,emp_name from EmployeeModel where emp_code is not null and current_divn_cd = case when :division='All' then current_divn_cd else :division end"
			+ " and emp_status_code='3' and curr_comp_code= case when :company_code='All' then curr_comp_code else :company_code end and "
			+ " pa_code=  case when :loc_code='All' then pa_code else :loc_code end and psa_code=  case when :psa_code='All' then psa_code else :psa_code end "
			+ " and curr_comp_code in (:allowed_comp_code) order by emp_code")
	public ArrayList<ArrayList<String>> getEmpCodeListLocation(@Param("division") String division,
			@Param("company_code") String company_code, @Param("loc_code") String loc_code,
			@Param("psa_code") String psa_code, @Param("allowed_comp_code") List<String> allowed_comp_code);

	@Query(" from EmployeeModel where emp_code is not null and current_divn_cd = case when :division='All' then current_divn_cd else :division end"
			+ " and emp_status_code='3' and curr_comp_code= case when :company_code='All' then curr_comp_code else :company_code end and "
			+ " pa_code=  case when :loc_code='All' then pa_code else :loc_code end and psa_code=  case when :psa_code='All' then psa_code else :psa_code end"
			+ " and curr_comp_code in (:allowed_comp_code) order by emp_code")
	public List<EmployeeModel> getEmployeeListLocation(@Param("division") String division,
			@Param("company_code") String company_code, @Param("loc_code") String loc_code,
			@Param("psa_code") String psa_code, @Param("allowed_comp_code") List<String> allowed_comp_code);

}
