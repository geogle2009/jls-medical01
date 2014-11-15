package com.medical.service;

import java.math.BigDecimal;
import java.util.List;

import com.medical.common.Pager;
import com.medical.dto.BizCheckDTO;
import com.medical.dto.BusinessDTO;

public interface BizCheckService {
	/**
	 * 根据查询条件查询审批单列表
	 * 
	 * @param bizCheckDTO
	 * @return
	 */
	public List<BizCheckDTO> findByTerm(BizCheckDTO bizCheckDTO,String type);

	/**
	 * 根据审批单ID查询审批单
	 * 
	 * @param bizcheckId
	 * @return
	 */
	public BizCheckDTO findByBizIdAndBizcheckId(Integer bizId,
			BigDecimal bizcheckId);

	/**
	 * 保存审批单到数据库
	 * 
	 * @param bizCheckDTO
	 */
	public Boolean saveBizCheck(BizCheckDTO bizCheckDTO);

	/**
	 * 取得分页后 toolsmenu
	 * 
	 * @return
	 */
	public Pager getPager();
	/***
	 * 救助对象查询
	 * @return
	 */
	public List<BusinessDTO> findSalvationQuery(BizCheckDTO bizCheckDTO);
}