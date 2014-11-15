package com.medical.action;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.medical.dto.EmeCheckDTO;
import com.medical.dto.UserInfoDTO;
import com.medical.service.EmeCheckService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("unchecked")
public class EmeCheckAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private EmeCheckService emeCheckService;
	private EmeCheckDTO emeCheckDTO;
	private String memberId;
	private String emecheckId;
	private String pagetype;
	private List<EmeCheckDTO> emes;
	private String toolsmenu;
	private String cur_page;
	private String term;
	private String operational;
	private String value;

	/**
	 *编辑审批界面
	 */
	@SuppressWarnings("rawtypes")
	public String emecheckapproves() {
		Map session = ActionContext.getContext().getSession();

		UserInfoDTO userInfoDTO = (UserInfoDTO) session.get("user");
		String organizationId = userInfoDTO.getOrganizationId();
		if (8 == organizationId.length()) {
			pagetype = "1";
		} else if (6 == organizationId.length()) {
			pagetype = "2";
		} else {
			pagetype = "你所在的机构，没有审批的权限";
			return "result";
		}
		this.setEmeCheckDTO(this.emeCheckService.getEmeCheckInfo(emecheckId,
				memberId));

		return SUCCESS;
	}

	public String getCur_page() {
		return cur_page;
	}

	public EmeCheckDTO getEmeCheckDTO() {
		return emeCheckDTO;
	}

	public String getEmecheckId() {
		return emecheckId;
	}

	public EmeCheckService getEmeCheckService() {
		return emeCheckService;
	}

	public List<EmeCheckDTO> getEmes() {
		return emes;
	}

	public String getMemberId() {
		return memberId;
	}

	public String getOperational() {
		return operational;
	}

	public String getPagetype() {
		return pagetype;
	}

	public String getTerm() {
		return term;
	}

	public String getToolsmenu() {
		return toolsmenu;
	}

	public String getValue() {
		return value;
	}

	public String interview() {
		this.setEmeCheckDTO(emeCheckService.getMemberInfo(memberId));
		if (null == this.getEmeCheckDTO().getSsn()
				|| "".equals(this.getEmeCheckDTO().getSsn())) {
			pagetype = "此人没有社会保险号";
			return "result";
		} else {
			return SUCCESS;
		}
	}

	@SuppressWarnings("rawtypes")
	public String saveEmecheckapproves() {
		Map session = ActionContext.getContext().getSession();
		UserInfoDTO user = (UserInfoDTO) session.get("user");
		if (pagetype.equals("1")) {
			emeCheckDTO.setAuditorofstreet(user.getUsername());
			this.emeCheckService.saveStreetApproves(emeCheckDTO);
		}
		if (pagetype.equals("2")) {
			emeCheckDTO.setAuditorofareg(user.getUsername());
			this.emeCheckService.saveAregApproves(emeCheckDTO);
		}
		pagetype = "保存成功";
		return SUCCESS;
	}

	public String saveInterview() {
		emeCheckService.saveInterview(emeCheckDTO);
		pagetype = "保存成功";
		return SUCCESS;
	}

	public void setCur_page(String cur_page) {
		this.cur_page = cur_page;
	}

	public void setEmeCheckDTO(EmeCheckDTO emeCheckDTO) {
		this.emeCheckDTO = emeCheckDTO;
	}

	public void setEmecheckId(String emecheckId) {
		this.emecheckId = emecheckId;
	}

	public void setEmeCheckService(EmeCheckService emeCheckService) {
		this.emeCheckService = emeCheckService;
	}

	public void setEmes(List<EmeCheckDTO> emes) {
		this.emes = emes;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public void setOperational(String operational) {
		this.operational = operational;
	}

	public void setPagetype(String pagetype) {
		this.pagetype = pagetype;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public void setToolsmenu(String toolsmenu) {
		this.toolsmenu = toolsmenu;
	}

	public void setValue(String value) {
		this.value = value;
	}

	// 审批查询
	@SuppressWarnings("rawtypes")
	public String showEmeCheckApproves() {
		String var = value;
		String jwhere = "";
		Map map = ActionContext.getContext().getSession();
		UserInfoDTO user = (UserInfoDTO) map.get("user");
		if (null == cur_page || "".equals(cur_page)) {
			if (!"".equals(var)) {
				if ("=".equals(operational)) {
					var = " = '" + var + "'";
				} else if ("like".equals(operational)) {
					var = "like  '%" + var + "%'";
				} else {
					var = "";
				}

				if ("SSN".equals(term)) {
					jwhere = jwhere + " and m.ssn  " + var;
				} else if ("FAMILYNO".equals(term)) {
					jwhere = jwhere + " and m.familyno  " + var;
				} else if ("MEMBERNAME".equals(term)) {
					jwhere = jwhere + " and m.membername  " + var;
				} else if ("PAPERID".equals(term)) {
					jwhere = jwhere + " and m.paperid  " + var;
				} else {
				}
			}

			jwhere = jwhere + " and  m.familyno like '"
					+ user.getOrganizationId() + "%' ";
			map.put("sql", jwhere);
			cur_page = "1";
		} else {
			jwhere = (String) map.get("sql");
		}

		int i = user.getOrganizationId().length();

		if (i == 8 || i == 6) {
			pagetype = "2";
		} else {
			pagetype = "1";
		}

		// 执行顺序
		setEmes(emeCheckService.queryEmeCheckApproves(jwhere, new BigDecimal(
				cur_page).intValue()));
		setToolsmenu(emeCheckService.getPager().getToolsmenu());

		return SUCCESS;
	}

	// 走访调查查询
	@SuppressWarnings("rawtypes")
	public String showEmeCheckViews() {
		String var = value;
		String jwhere = "";
		Map map = ActionContext.getContext().getSession();
		if (null == cur_page || "".equals(cur_page)) {
			if (!"".equals(var)) {
				if ("=".equals(operational)) {
					var = " = '" + var + "'";
				} else if ("like".equals(operational)) {
					var = "like  '%" + var + "%'";
				} else {
					var = "";
				}

				if ("SSN".equals(term)) {
					jwhere = jwhere + " and m.ssn  " + var;
				} else if ("FAMILYNO".equals(term)) {
					jwhere = jwhere + " and m.familyno  " + var;
				} else if ("MEMBERNAME".equals(term)) {
					jwhere = jwhere + " and m.membername  " + var;
				} else if ("PAPERID".equals(term)) {
					jwhere = jwhere + " and m.paperid  " + var;
				} else {
				}
			}

			UserInfoDTO user = (UserInfoDTO) map.get("user");
			jwhere = jwhere + " and  m.familyno like '"
					+ user.getOrganizationId() + "%' ";

			map.put("sql", jwhere);
			cur_page = "1";
		} else {
			jwhere = (String) map.get("sql");
		}
		// 执行顺序
		setEmes(emeCheckService.queryEmeCheckViews(jwhere, new BigDecimal(
				cur_page).intValue()));
		setToolsmenu(emeCheckService.getPager().getToolsmenu());

		return SUCCESS;
	}

	@SuppressWarnings("rawtypes")
	public String showEmeCheckViewsInit() {
		Map session = ActionContext.getContext().getSession();
		UserInfoDTO user = (UserInfoDTO) session.get("user");
		if (10 == user.getOrganizationId().length()) {

			return SUCCESS;
		} else {
			this.pagetype = "您没有操作权限!!!";
			return ERROR;
		}
	}
}
