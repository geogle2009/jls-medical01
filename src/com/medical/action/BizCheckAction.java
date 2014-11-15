package com.medical.action;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.medical.dto.BizCheckDTO;
import com.medical.dto.BusinessDTO;
import com.medical.dto.DeptDTO;
import com.medical.dto.UserInfoDTO;
import com.medical.service.BizCheckService;
import com.medical.service.SearchService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("unchecked")
public class BizCheckAction extends ActionSupport {
	private static final long serialVersionUID = 7872753307947389015L;
	private BizCheckService bizCheckService;
	private SearchService searchService;
	private BizCheckDTO bizCheckDTO = new BizCheckDTO();
	private List<BizCheckDTO> checkedlist;
	private List<BusinessDTO> sallist;
	private List<DeptDTO> hs;
	// url 参数
	private String type;
	private String cur_page;
	private String toolsmenu;
	private String term;
	private String value;
	private String operational;
	private String state;
	private String checked;
	private String detail;
	private String checktime;
	private String operator;
	private String hid;
	private String inh;

	@SuppressWarnings("rawtypes")
	public String initBizcheck() {
		Map map = ActionContext.getContext().getSession();
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);

		String bizId = (bizCheckDTO.getBizId() != null ? bizCheckDTO.getBizId()
				.toString() : request.getParameter("bizid"));
		bizId = null == bizId || "".equals(bizId) ? "0" : bizId;
		String bizcheckId = bizCheckDTO.getBizcheckId() != null ? bizCheckDTO
				.getBizcheckId().toString() : request
				.getParameter("bizcheckid");
		bizcheckId = null == bizcheckId || "".equals(bizcheckId) ? "0"
				: bizcheckId;
		this.bizCheckDTO = bizCheckService.findByBizIdAndBizcheckId(
				new Integer(bizId), new BigDecimal(bizcheckId));
		UserInfoDTO user = (UserInfoDTO) map.get("user");
		int level = user.getOrganizationId().length();
		bizCheckDTO.setLevel(level);
		switch (level) {

		case 4:
			// 低保中心审批信息
			bizCheckDTO.setChecked(bizCheckDTO.getChecked4());
			bizCheckDTO.setChecktime(bizCheckDTO.getChecktime4());
			bizCheckDTO.setDetail(bizCheckDTO.getDetail4());
			bizCheckDTO.setOperator(bizCheckDTO.getOperator4());
			bizCheckDTO.setOpttime(bizCheckDTO.getOpttime4());
			break;
		case 6:
			bizCheckDTO.setChecked(bizCheckDTO.getChecked3());
			bizCheckDTO.setChecktime(bizCheckDTO.getChecktime3());
			bizCheckDTO.setDetail(bizCheckDTO.getDetail3());
			bizCheckDTO.setOperator(bizCheckDTO.getOperator3());
			bizCheckDTO.setOpttime(bizCheckDTO.getOpttime3());
			break;
		case 8:
			bizCheckDTO.setChecked(bizCheckDTO.getChecked2());
			bizCheckDTO.setChecktime(bizCheckDTO.getChecktime2());
			bizCheckDTO.setDetail(bizCheckDTO.getDetail2());
			bizCheckDTO.setOperator(bizCheckDTO.getOperator2());
			bizCheckDTO.setOpttime(bizCheckDTO.getOpttime2());
			break;
		case 10:
			bizCheckDTO.setChecked(bizCheckDTO.getChecked1());
			bizCheckDTO.setChecktime(bizCheckDTO.getChecktime1());
			bizCheckDTO.setDetail(bizCheckDTO.getDetail1());
			bizCheckDTO.setOperator(bizCheckDTO.getOperator1());
			bizCheckDTO.setOpttime(bizCheckDTO.getOpttime1());
			break;
		default:
			bizCheckDTO.setChecked(null);
			bizCheckDTO.setChecktime(null);
			bizCheckDTO.setDetail(null);
			bizCheckDTO.setOperator(null);
			bizCheckDTO.setOpttime(null);
		}
		return SUCCESS;
	}

	@SuppressWarnings("rawtypes")
	public String initQueryPage() {
		System.out.println("init cur_page>>" + cur_page);
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
					jwhere = jwhere + " and biz.ssn  " + var;
				} else if ("FAMILYNO".equals(term)) {
					jwhere = jwhere + " and inf.familyno  " + var;
				} else if ("MEMBERNAME".equals(term)) {
					jwhere = jwhere + " and inf.membername  " + var;
				} else if ("PAPERID".equals(term)) {
					jwhere = jwhere + " and inf.paperid  " + var;
				} else {
				}
			}
			if ("all".equals(state)) {
			} else if ("unchecked".equals(state)) {
				jwhere = jwhere
						+ " and (biz.check_state = -1 or biz.check_state is null)";
			} else if ("agree".equals(state)) {
				jwhere = jwhere + " and biz.check_state = 0";
			} else if ("reject".equals(state)) {
				jwhere = jwhere + " and biz.check_state = 1";
			}
			if ("1".equals(type)) {
				jwhere = jwhere + " and biz.biz_type = 11";
			} else if ("2".equals(type)) {
				jwhere = jwhere + " and biz.biz_type = 12";
			} else {

			}
			UserInfoDTO user = (UserInfoDTO) map.get("user");
			jwhere = jwhere + " and  inf.familyno like '"
					+ user.getOrganizationId() + "%' ";
			cur_page = "1";
			bizCheckDTO.setJwhere(jwhere);
			bizCheckDTO.setCur_page(1);
			map.put("asql", bizCheckDTO);
		} else {
			bizCheckDTO = (BizCheckDTO) map.get("asql");
			bizCheckDTO.setCur_page(new Integer(cur_page));
		}
		// 执行顺序
		setCheckedlist(bizCheckService.findByTerm(bizCheckDTO, type));
		setToolsmenu(bizCheckService.getPager().getToolsmenu());
		return SUCCESS;
	}

	@SuppressWarnings("rawtypes")
	public String salvationQuery() {
		System.out.println("sal cur_page>>" + cur_page);
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
					jwhere = jwhere + " and ssn  " + var;
				} else if ("FAMILYNO".equals(term)) {
					jwhere = jwhere + " and FAMILY_ID  " + var;
				} else if ("MEMBERNAME".equals(term)) {
					jwhere = jwhere + " and BIZ.NAME  " + var;
				} else {
				}
			}
			if ("all".equals(state)) {
			} else if ("unchecked".equals(state)) {
				jwhere = jwhere + " and biz.check_state = -1";
			} else if ("checked".equals(state)) {
				jwhere = jwhere + " and biz.check_state = 1";
			}
			UserInfoDTO user = (UserInfoDTO) map.get("user");
			jwhere = jwhere + " and  FAMILY_ID like '"
					+ user.getOrganizationId() + "%' ";

			if ("2".equals(type)) {
				jwhere = jwhere + " and biz. biz_type =12 ";
			} else if ("2".equals(type)) {
				jwhere = jwhere + " and biz. biz_type =12 ";
			} else if ("1".equals(type)) {
				jwhere = jwhere + " and biz. biz_type =11";
			}

			if (null == hid || "".equals(hid)) {

			} else {
				jwhere = jwhere + " and biz.hospital_id='" + hid + "'";

			}
			if (null == inh || "".equals(inh)) {
			} else if ("1".equals(inh)) {
				jwhere = jwhere
						+ "     and biz.treatment_type = '120' and biz.out_flag = '0'";
			} else if ("2".equals(inh)) {
				jwhere = jwhere
						+ "     and biz.treatment_type = '120' and biz.out_flag = '1'";
			}

			bizCheckDTO.setJwhere(jwhere + " ");
			cur_page = "1";
			bizCheckDTO.setCur_page(1);
			map.put("sql", bizCheckDTO);
		} else {
			bizCheckDTO = (BizCheckDTO) map.get("sql");
			bizCheckDTO.setCur_page(new Integer(cur_page));
		}
		this.setHs(this.searchService.getHospitals());
		// 执行顺序
		setSallist(bizCheckService.findSalvationQuery(bizCheckDTO));
		setToolsmenu(bizCheckService.getPager().getToolsmenu());

		return SUCCESS;
	}

	public String saveBizcheck() {
		bizCheckService.saveBizCheck(bizCheckDTO);
		this.bizCheckDTO = bizCheckService.findByBizIdAndBizcheckId(
				bizCheckDTO.getBizId(), bizCheckDTO.getBizcheckId());
		return SUCCESS;
	}

	public void setBizCheckDTO(BizCheckDTO bizCheckDTO) {
		this.bizCheckDTO = bizCheckDTO;
	}

	public void setBizCheckService(BizCheckService bizCheckService) {
		this.bizCheckService = bizCheckService;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	public void setCheckedlist(List<BizCheckDTO> checkedlist) {
		this.checkedlist = checkedlist;
	}

	public void setChecktime(String checktime) {
		this.checktime = checktime;
	}

	public void setCur_page(String cur_page) {
		this.cur_page = cur_page;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public void setOperational(String operational) {
		this.operational = operational;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public void setSallist(List<BusinessDTO> sallist) {
		this.sallist = sallist;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public void setToolsmenu(String toolsmenu) {
		this.toolsmenu = toolsmenu;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void validate() {
		/*
		 * this.clearErrorsAndMessages(); if (null == username ||
		 * "".equals(username)) { this.addActionError("用户名不能为空"); } if (null ==
		 * password || "".equals(password)) { this.addActionError("密码不能为空"); }
		 */
	}

	public void setHs(List<DeptDTO> hs) {
		this.hs = hs;
	}

	public List<DeptDTO> getHs() {
		return hs;
	}

	public SearchService getSearchService() {
		return searchService;
	}

	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
	}

	public String getHid() {
		return hid;
	}

	public void setHid(String hid) {
		this.hid = hid;
	}

	public BizCheckDTO getBizCheckDTO() {
		return bizCheckDTO;
	}

	public BizCheckService getBizCheckService() {
		return bizCheckService;
	}

	public String getChecked() {
		return checked;
	}

	@SuppressWarnings("rawtypes")
	public List getCheckedlist() {
		return checkedlist;
	}

	public String getChecktime() {
		return checktime;
	}

	public String getCur_page() {
		return cur_page;
	}

	public String getDetail() {
		return detail;
	}

	public String getOperational() {
		return operational;
	}

	public String getOperator() {
		return operator;
	}

	public List<BusinessDTO> getSallist() {
		return sallist;
	}

	public String getState() {
		return state;
	}

	public String getTerm() {
		return term;
	}

	public String getToolsmenu() {
		return toolsmenu;
	}

	public String getType() {
		return type;
	}

	public String getValue() {
		return value;
	}

	public void setInh(String inh) {
		this.inh = inh;
	}

	public String getInh() {
		return inh;
	}

}