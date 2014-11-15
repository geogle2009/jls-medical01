package com.medical.action;

import java.util.List;
import java.util.Map;

import com.medical.dto.HealthDTO;
import com.medical.dto.UserInfoDTO;
import com.medical.service.BaseinfoService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BaseinfoAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private BaseinfoService baseinfoService;
	private HealthDTO healthDTO;
	private List<HealthDTO> result;
	private String cur_page;
	private String memberId;
	private String ds;
	private String toolsmenu;

	public String displayCard() {
		healthDTO = baseinfoService.findByMemberId(memberId, ds);
		return SUCCESS;
	}

	public BaseinfoService getBaseinfoService() {
		return baseinfoService;
	}

	public String getCur_page() {
		return cur_page;
	}

	public List<HealthDTO> getResult() {
		return result;
	}

	public String getToolsmenu() {
		return toolsmenu;
	}

	// ÷¥––≤È—Ø
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String healthSearch() {
		Map map = ActionContext.getContext().getSession();

		UserInfoDTO user = (UserInfoDTO) map.get("user");
		String orgno = user.getOrganizationId();
		if (null == cur_page || "".equals(cur_page)) {
			healthDTO.setCurpage(1);
			map.put("healthDTO", healthDTO);
		} else {
			healthDTO = (HealthDTO) map.get("healthDTO");
			healthDTO.setCurpage(new Integer(cur_page).intValue());
		}
		healthDTO.setUrl("healthSearch.action");
		healthDTO.setPageSize(14);
		result = baseinfoService.findForperson(healthDTO, orgno);
		toolsmenu = baseinfoService.getToolsmenu();

		return SUCCESS;
	}

	public void setBaseinfoService(BaseinfoService baseinfoService) {
		this.baseinfoService = baseinfoService;
	}

	public void setCur_page(String cur_page) {
		this.cur_page = cur_page;
	}

	public void setResult(List<HealthDTO> result) {
		this.result = result;
	}

	public void setToolsmenu(String toolsmenu) {
		this.toolsmenu = toolsmenu;
	}

	public void setHealthDTO(HealthDTO healthDTO) {
		this.healthDTO = healthDTO;
	}

	public HealthDTO getHealthDTO() {
		return healthDTO;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getDs() {
		return ds;
	}

	public void setDs(String ds) {
		this.ds = ds;
	}

}