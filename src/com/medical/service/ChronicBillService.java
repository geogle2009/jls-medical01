package com.medical.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import com.medical.common.Pager;
import com.medical.dto.ChronicBillDTO;
import com.medical.dto.ChronicBillEmployDTO;
import com.medical.dto.ChronicBillStatDTO;
import com.medical.dto.ChronicStatusDTO;
import com.medical.dto.OrganDTO;
import com.medical.model.JzChronicstatusExample;

public interface ChronicBillService {
	/**
	 * 
	 * @return
	 */
	public ChronicBillStatDTO findCountInfo();

	/**
	 * 
	 * @param opt
	 *            0:清零，1:充值，2:清零并充值
	 * @param subject
	 * @param money
	 */
	public void saveBatchBill(int opt, String subject, BigDecimal money);

	/**
	 * 
	 * @param url
	 * @param curpage
	 * @param example
	 * @return
	 */
	public List<ChronicStatusDTO> findChronicList(String url, int curpage,
			JzChronicstatusExample example);

	/**
	 * 
	 * @return
	 */
	public Pager getPager();

	/**
	 * 
	 * @param ssn
	 * @return
	 */
	public List<ChronicBillDTO> findChronicBillBySSN(String memberId,
			String memberType);

	/**
	 * 
	 * @param chronicBillDTO
	 * @param opt
	 */
	public String saveChronicBill(ChronicBillDTO chronicBillDTO,
			ChronicStatusDTO chronicStatusDTO, String opt);

	/**
	 * 
	 * @param ssn
	 * @return
	 */
	public HashMap<String, String> findChronicStatBySsn(String memberId ,String memberType);

	public List<OrganDTO> getOrganList(String orgid);

	public List<ChronicBillDTO> findChronicBills(String string, Integer curPage, String sql);
	
	public ChronicBillEmployDTO findChronicBillEmploy(ChronicBillEmployDTO cbedto);
}
