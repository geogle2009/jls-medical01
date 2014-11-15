package com.medical.service;

import java.util.List;

import com.medical.common.Pager;
import com.medical.dto.EmeCheckDTO;

public interface EmeCheckService {
	/**
	 * 查询走访列表
	 * 
	 * @param emeCheckDTO
	 * @return
	 */
	public List<EmeCheckDTO> queryEmeCheckViews(String jwhere, int cur_page);

	/**
	 * 查询审批列表
	 * 
	 * @param emeCheckDTO
	 * @return
	 */
	public List<EmeCheckDTO> queryEmeCheckApproves(String jwhere, int cur_page);

	/**
	 * 
	 * @param emeCheckDTO
	 */
	public void saveInterview(EmeCheckDTO emeCheckDTO);

	/**
	 * 
	 * @param emeCheckDTO
	 */
	public void saveStreetApproves(EmeCheckDTO emeCheckDTO);

	/**
	 * 
	 * @param emeCheckDTO
	 */
	public void saveAregApproves(EmeCheckDTO emeCheckDTO);

	public EmeCheckDTO getMemberInfo(String memberid);

	public EmeCheckDTO getEmeCheckInfo(String emecheckId,String memberId);
	public Pager getPager();
}
