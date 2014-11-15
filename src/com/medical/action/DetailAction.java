package com.medical.action;

import java.util.List;
import java.util.Map;

import com.medical.dto.BusinessDTO;
import com.medical.dto.DrugDTO;
import com.medical.dto.FeeDTO;
import com.medical.dto.MedicalDTO;
import com.medical.dto.PaylistDTO;
import com.medical.dto.UserInfoDTO;
import com.medical.service.SystemManager;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class DetailAction extends ActionSupport {

	private static final long serialVersionUID = -2728703702054638096L;

	private SystemManager systemManager;

	private List<BusinessDTO> list;
	private String toolsmenu;
	private String bizId;

	private MedicalDTO medicaldto;
	private List<DrugDTO> druglist;
	private List<FeeDTO> feelist;

	private List<PaylistDTO> paylist;

	private int cur_page;
	private String type;

	@SuppressWarnings("rawtypes")
	public String detail() {
		Map session = ActionContext.getContext().getSession();
		UserInfoDTO user = (UserInfoDTO) session.get("user");
		list = systemManager.findBusinessList(cur_page, user
				.getOrganizationId(), type);
		toolsmenu = systemManager.getPager().getToolsmenu();
		return SUCCESS;
	}

	@SuppressWarnings("rawtypes")
	public String detailone() {
		Map session = ActionContext.getContext().getSession();
		UserInfoDTO user = (UserInfoDTO) session.get("user");
		list = systemManager.findBusinessoneList(cur_page, user
				.getOrganizationId(), type);
		toolsmenu = systemManager.getPager().getToolsmenu();
		return SUCCESS;
	}

	public String getBizId() {
		return bizId;
	}

	public int getCur_page() {
		return cur_page;
	}

	public List<DrugDTO> getDruglist() {
		return druglist;
	}

	public List<FeeDTO> getFeelist() {
		return feelist;
	}

	public List<BusinessDTO> getList() {
		return list;
	}

	public MedicalDTO getMedicaldto() {
		return medicaldto;
	}

	public List<PaylistDTO> getPaylist() {
		return paylist;
	}

	public SystemManager getSystemManager() {
		return systemManager;
	}

	public String getToolsmenu() {
		return toolsmenu;
	}

	public String getType() {
		return type;
	}

	public void setBizId(String bizId) {
		this.bizId = bizId;
	}

	public void setCur_page(int cur_page) {
		this.cur_page = cur_page;
	}

	public void setDruglist(List<DrugDTO> druglist) {
		this.druglist = druglist;
	}

	public void setFeelist(List<FeeDTO> feelist) {
		this.feelist = feelist;
	}

	public void setList(List<BusinessDTO> list) {
		this.list = list;
	}

	public void setMedicaldto(MedicalDTO medicaldto) {
		this.medicaldto = medicaldto;
	}

	public void setPaylist(List<PaylistDTO> paylist) {
		this.paylist = paylist;
	}

	public void setSystemManager(SystemManager systemManager) {
		this.systemManager = systemManager;
	}

	public void setToolsmenu(String toolsmenu) {
		this.toolsmenu = toolsmenu;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String viewBiz() {

		medicaldto = systemManager.findMedical(bizId);
		druglist = systemManager.findDruglist(bizId);
		feelist = systemManager.findFeelist(bizId);
		paylist = systemManager.findPaylist(bizId);

		int m = 0;
		int n = 0;

		if (null != feelist) {
			m = feelist.size();
		}
		if (null != paylist) {
			n = paylist.size();
		}

		if (m < n) {
			for (int i = 0; i < n - m; i++) {
				FeeDTO fe = new FeeDTO();
				feelist.add(fe);
			}
		} else {
			for (int i = 0; i < m - n; i++) {
				PaylistDTO pay = new PaylistDTO();
				paylist.add(pay);
			}
		}

		return SUCCESS;

	}
}
