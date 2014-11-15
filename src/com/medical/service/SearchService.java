package com.medical.service;

import java.util.HashMap;
import java.util.List;

import com.medical.common.Pager;
import com.medical.dto.ChronicBillDTO;
import com.medical.dto.DeptDTO;
import com.medical.dto.FeeDTO;
import com.medical.dto.OrganDTO;
import com.medical.dto.SearchDTO;
import com.medical.dto.Yearmonth;

public interface SearchService {

	public List<SearchDTO> findByTerm(SearchDTO searchDTO);

	public List<SearchDTO> findForBizlist();

	public String getToolsmenu();

	public List<OrganDTO> getOrganList(String organid);

	public List<SearchDTO> queryMembers(String jwhere, int cur_page, String url);
	public List<SearchDTO> queryMemberstat(String jwhere, int cur_page, String url);

	public List<DeptDTO> getHospitals();
	public List<DeptDTO> getYaodians();
	public Pager getPager();

	@SuppressWarnings("rawtypes")
	public List<HashMap> getAll(String sql);

	public String queryMemberCounts(String jwhere);
	public String queryMemberStatCount(String sql);

	public String getStat(String sql, String[] cols);

	/**
	 * 取得结算月份
	 * 
	 * @return
	 */
	public List<Yearmonth> getYearMonth();
	public List<Yearmonth> getYear();

	public List<SearchDTO> queryMembers1(String jwhere, int intValue,
			String string);

	public String queryMemberCounts1(String jwhere);

	public List<SearchDTO> queryMembersStat1(String sql);

	public List<ChronicBillDTO> queryChronicBillss(String sql, int intValue,
			String string);

	public List<FeeDTO> findFeeList(String sql);

	public List<FeeDTO> findFeeList1(String sql);
}