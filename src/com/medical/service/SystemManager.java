package com.medical.service;

import java.util.List;

import com.medical.common.Pager;
import com.medical.dto.BusinessDTO;
import com.medical.dto.DrugDTO;
import com.medical.dto.FeeDTO;
import com.medical.dto.MedicalDTO;
import com.medical.dto.PaylistDTO;
import com.medical.dto.UserInfoDTO;

public interface SystemManager {
	/**
	 * 
	 * @param userinfo
	 * @return
	 */
	public UserInfoDTO validate(UserInfoDTO userinfo);

	public List<BusinessDTO> findBusinessList(int cur_page, String organno,
			String type);

	public Pager getPager();

	public MedicalDTO findMedical(String bizId);

	public List<DrugDTO> findDruglist(String bizId);

	public List<FeeDTO> findFeelist(String bizId);

	public List<PaylistDTO> findPaylist(String bizId);

	public List<BusinessDTO> findBusinessoneList(int curPage,
			String organizationId, String type);

	public boolean findUserRoleUnuse(String servletPath, String empId,
			String organizationId);
}