package com.medical.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.annotations.JSON;

import com.medical.common.Age;
import com.medical.common.FtpUpload;
import com.medical.dto.BusinessDTO;
import com.medical.dto.DeptDTO;
import com.medical.dto.ManualDTO;
import com.medical.dto.OrganDTO;
import com.medical.dto.PersonDTO;
import com.medical.dto.PrintBillDTO;
import com.medical.dto.UserInfoDTO;
import com.medical.dto.VbizDTO;
import com.medical.model.Icd10;
import com.medical.model.JzBizExample;
import com.medical.model.JzManualExample;
import com.medical.service.BusinessService;
import com.medical.service.SearchService;
import com.medical.service.SystemManager;
import com.medical.system.DictionaryHandle;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("unchecked")
public class BusinessAction extends ActionSupport {
	private static final long serialVersionUID = -5126976226423666862L;
	private static final int BUFFER_SIZE = 16 * 1024;
	private String assistype;
	private String bizId;
	private List<ManualDTO> manualapproves;
	private List<PrintBillDTO> bills;
	private ManualDTO manual;
	private PrintBillDTO printBillDTO;
	private HashMap<String, String> map;
	private List<VbizDTO> bizs;// 业务列表
	private String biztype;
	private BusinessDTO businessDTO = new BusinessDTO();
	private BusinessService businessService;
	private String counttype;
	private String cur_page;
	private String DIAGNOSE_NAME;
	private DictionaryHandle dictionaryHandle;
	private String familyno;
	private String memberId;
	private String oid;
	private String operational;
	private String opertime1;
	private String opertime2;
	private List<OrganDTO> orgs;
	private PersonDTO personDTO = new PersonDTO();
	private List<PersonDTO> personlist;
	private String result;
	private List<String[]> sicknames;
	private String ssn;
	private SystemManager systemManager;
	private String term;
	private String toolsmenu;
	private String type;
	private String value;
	private List<DeptDTO> hs;
	private String hid;
	public String getHid() {
		return hid;
	}

	public void setHid(String hid) {
		this.hid = hid;
	}

	private SearchService searchService;
	/**
	 * 日常救助
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String approveDailyMedical() {

		Map session = ActionContext.getContext().getSession();
		UserInfoDTO user = (UserInfoDTO) session.get("user");

		businessDTO.setMemberId(personDTO.getMemberId());
		businessDTO.setSsn(personDTO.getSsn());
		businessDTO.setMedicaltype(personDTO.getAssisTypeId());
		businessDTO.setEmpid(user.getEmpId());
		businessDTO.setFamilyno(personDTO.getFamilyno());
		this.businessService.saveDailyMedical(businessDTO);

		personDTO.setSsn(personDTO.getSsn());
		personDTO.setOrganizationId(user.getOrganizationId());
		setPersonDTO(this.businessService.findMedicalPerson(personDTO));

		businessDTO.setHospitallist(businessService.findHospitalList());

		businessDTO.setPresicks(this.dictionaryHandle.getDsMap("006"));

		this.setBizs(this.businessService.queryDailyMedicalBizBySsn(businessDTO
				.getSsn()));

		return SUCCESS;
	}

	/**
	 * 日常救助
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String approveDailyMedicalInit() {
		Map session = ActionContext.getContext().getSession();
		UserInfoDTO user = (UserInfoDTO) session.get("user");
		if (user.getOrganizationId().length() == 6) {
			personDTO.setSsn(ssn);
			personDTO.setOrganizationId(user.getOrganizationId());
			setPersonDTO(this.businessService.findMedicalPerson(personDTO));

			businessDTO.setHospitallist(businessService.findHospitalList());

			businessDTO.setPresicks(this.dictionaryHandle.getDsMap("006"));

			this.setBizs(this.businessService
					.queryDailyMedicalBizBySsn(personDTO.getSsn()));

			return SUCCESS;
		} else {
			this.result = "没有权限操作！";
			return ERROR;
		}
	}

	/**
	 * 区县医前审批
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String approvePreMedical() {

		Map session = ActionContext.getContext().getSession();
		UserInfoDTO user = (UserInfoDTO) session.get("user");

		businessDTO.setMemberId(personDTO.getMemberId());
		businessDTO.setSsn(personDTO.getSsn());
		businessDTO.setMedicaltype(personDTO.getAssisTypeId());
		businessDTO.setEmpid(user.getEmpId());
		businessDTO.setFamilyno(personDTO.getFamilyno());
		this.businessService.savePreMedical(businessDTO);

		personDTO.setSsn(personDTO.getSsn());
		personDTO.setOrganizationId(user.getOrganizationId());
		setPersonDTO(this.businessService.findMedicalPerson(personDTO));

		businessDTO.setHospitallist(businessService.findHospitalList());

		businessDTO.setPresicks(this.dictionaryHandle.getDsMap("006"));

		this.setBizs(this.businessService.queryPreMedicalBizBySsn(businessDTO
				.getSsn()));

		return SUCCESS;
	}

	/**
	 * 区县医前审批
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String approvePreMedicalInit() {
		Map session = ActionContext.getContext().getSession();
		UserInfoDTO user = (UserInfoDTO) session.get("user");
		if (user.getOrganizationId().length() == 6) {
			personDTO.setSsn(ssn);
			personDTO.setOrganizationId(user.getOrganizationId());
			setPersonDTO(this.businessService.findMedicalPerson(personDTO));

			businessDTO.setHospitallist(businessService.findHospitalList());

			businessDTO.setPresicks(this.dictionaryHandle.getDsMap("006"));

			this.setBizs(this.businessService.queryPreMedicalBizBySsn(personDTO
					.getSsn()));

			return SUCCESS;
		} else {
			this.result = "没有权限操作！";
			return ERROR;
		}
	}

	@SuppressWarnings("rawtypes")
	public String bizrecord() {
		// System.out.println(ssn);

		Map map = ActionContext.getContext().getSession();
		UserInfoDTO userInfoDTO = (UserInfoDTO) map.get("user");

		personDTO.setAssistType(assistype);
		personDTO.setOrganizationId(userInfoDTO.getOrganizationId());
		personDTO.setSsn(ssn);
		personDTO = businessService.findMedicalPerson(personDTO);
		this.setBizs(businessService.findBizRecordBySsn(personDTO));
		return SUCCESS;
	}

	@SuppressWarnings("rawtypes")
	public String bizrecordquery() {
		Map map = ActionContext.getContext().getSession();
		UserInfoDTO userInfoDTO = (UserInfoDTO) map.get("user");
		personDTO.setOrganizationId(userInfoDTO.getOrganizationId());
		setPersonlist(this.businessService.findMedicalFamily(personDTO));
		return SUCCESS;
	}

	/**
	 * 作废业务
	 */
	@SuppressWarnings("rawtypes")
	public String cancelBiz() {
		if (null == bizId || "".equals(bizId)) {
		} else {
			this.businessService.removeBiz(bizId);
			this.result = "作废成功，请重新录入业务信息";
		}
		if ("city".equals(type)) {
			this.result = "作废成功";
			return type;
		} else if ("bizrecord".equals(type)) {
			this.personDTO.setSsn(ssn);
			this.personDTO.setAssistType(assistype);
			this.bizrecord();
			return type;
		} else if ("premedical".equals(type)) {
			Map session = ActionContext.getContext().getSession();
			UserInfoDTO user = (UserInfoDTO) session.get("user");
			personDTO.setSsn(ssn);
			personDTO.setOrganizationId(user.getOrganizationId());
			setPersonDTO(this.businessService.findMedicalPerson(personDTO));
			businessDTO.setHospitallist(businessService.findHospitalList());
			businessDTO.setPresicks(this.dictionaryHandle.getDsMap("006"));
			this.setBizs(this.businessService.queryPreMedicalBizBySsn(personDTO
					.getSsn()));
			return type;
		} else if ("dailymedical".equals(type)) {
			Map session = ActionContext.getContext().getSession();
			UserInfoDTO user = (UserInfoDTO) session.get("user");
			personDTO.setSsn(ssn);
			personDTO.setOrganizationId(user.getOrganizationId());
			setPersonDTO(this.businessService.findMedicalPerson(personDTO));
			businessDTO.setHospitallist(businessService.findHospitalList());
			businessDTO.setPresicks(this.dictionaryHandle.getDsMap("006"));
			this.setBizs(this.businessService.queryPreMedicalBizBySsn(personDTO
					.getSsn()));
			return type;
		} else {
			this.personDTO.setSsn(ssn);
			this.personDTO.setAssistType(assistype);
			this.queryMedicalPerson();
			return type;
		}
	}

	/**
	 * 计算业务
	 */
	public String countBiz() {
		if (null == bizId || "".equals(bizId)) {
		} else {
			businessDTO.setBizId(bizId);

			HashMap<String, String> map = this.businessService
					.findMedicalMoney(businessDTO, counttype);

			if (map.get("1") == null) {

				result = "<tr><td>姓名:" + map.get("姓名").toString()
						+ "</td><td>身份证号:" + map.get("身份证号").toString()
						+ "</td><td>医保卡号:" + map.get("医保卡号").toString()
						+ "</td><td>住院日期:" + map.get("住院日期").toString()
						+ "</td></tr>" + "<tr><td>总费用:"
						+ map.get("总费用").toString() + "</td><td>救助金额:"
						+ map.get("救助金额").toString() + "元</td><td>实收现金:"
						+ map.get("实收现金").toString() + "元</td><td>医保统筹:"
						+ map.get("医保统筹").toString() + "元</td></tr>";

			} else {
				result = "<tr><td>" + map.get("1").toString() + "</td></tr>";
			}

		}

		return SUCCESS;
	}

	public String getAssistype() {
		return assistype;
	}

	public String getBizId() {
		return bizId;
	}

	public List<VbizDTO> getBizs() {
		return bizs;
	}

	public String getBiztype() {
		return biztype;
	}

	public BusinessDTO getBusinessDTO() {
		return businessDTO;
	}

	public BusinessService getBusinessService() {
		return businessService;
	}

	public String getCounttype() {
		return counttype;
	}

	public String getCur_page() {
		return cur_page;
	}

	public String getDIAGNOSE_NAME() {
		return DIAGNOSE_NAME;
	}

	public DictionaryHandle getDictionaryHandle() {
		return dictionaryHandle;
	}

	public String getFamilyno() {
		return familyno;
	}

	public String getMemberId() {
		return memberId;
	}

	public String getOid() {
		return oid;
	}

	public String getOperational() {
		return operational;
	}

	public String getOpertime1() {
		return opertime1;
	}

	public String getOpertime2() {
		return opertime2;
	}

	public List<OrganDTO> getOrgs() {
		return orgs;
	}

	public PersonDTO getPersonDTO() {
		return personDTO;
	}

	public List<PersonDTO> getPersonlist() {
		return personlist;
	}

	public String getResult() {
		return result;
	}

	@JSON(name = "sicknames")
	public List<String[]> getSicknames() {
		return sicknames;
	}

	public String getSsn() {
		return ssn;
	}

	// 业务处理1

	public SystemManager getSystemManager() {
		return systemManager;
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

	public String hospitalforcountry() {
		return "hospitalforcountry";
	}

	public String hospitalfortown() {
		return "hospitalfortown";
	}

	// 业务处理2
	public String outpatientforcountry() {
		return "outpatientforcountry";
	}

	public String premedicalapproveview() {
		businessDTO = this.businessService.findBiz(bizId);
		return SUCCESS;
	}

	@SuppressWarnings("rawtypes")
	public String queryBizrecord() {
		Map session = ActionContext.getContext().getSession();
		UserInfoDTO userinfo = (UserInfoDTO) session.get("user");
		String orgid = userinfo.getOrganizationId();

		JzBizExample example = new JzBizExample();
		if (null == cur_page || "".equals(cur_page)) {
			com.medical.model.JzBizExample.Criteria criteria = example
					.createCriteria();
			criteria.andGatherFlagEqualTo("5").andStausEqualTo("1")
					.andFamilyIdLike(this.oid + "%");
			if ("SSN".equals(term)) {
				criteria.andSsnLessThan(value + "%");
			} else if ("FAMILYNO".equals(term)) {
				criteria.andFamilyIdLike(value + "%");
			} else if ("MEMBERNAME".equals(term)) {
				criteria.andNameLike(value + "%");
			} else if ("PAPERID".equals(term)) {
				criteria.andIdcardLike(value + "%");
			} else {
			}
			session.put("sql", example);
			cur_page = "1";
		} else {
			example = (JzBizExample) session.get("sql");
		}
		this.setBizs(this.businessService.queryBizRecordBySsn(example,
				new Integer(cur_page)));
		this.setToolsmenu(this.businessService.getPager().getToolsmenu());
		this.setOrgs(this.businessService.getOrganList(orgid));
		return SUCCESS;
	}

	@SuppressWarnings("rawtypes")
	public String queryBizrecordinit() {
		Map session = ActionContext.getContext().getSession();
		UserInfoDTO userinfo = (UserInfoDTO) session.get("user");
		// String orgid = userinfo.getOrganizationId();
		// if (6 == orgid.length()) {
		// this.setOrgs(this.businessService.getOrganList(userinfo
		// .getOrganizationId()));
		// return SUCCESS;
		// } else {
		// this.result = "您所在的机构，没有审批权限！";
		// return "result";
		// }
		this.setOrgs(this.businessService.getOrganList(userinfo
				.getOrganizationId()));
		return SUCCESS;
	}

	@SuppressWarnings("rawtypes")
	public String queryBusiByCity() {

		Map session = ActionContext.getContext().getSession();
		UserInfoDTO userinfo = (UserInfoDTO) session.get("user");
		String orgid = userinfo.getOrganizationId();

		JzBizExample example = new JzBizExample();
		if (null == cur_page || "".equals(cur_page)) {
			com.medical.model.JzBizExample.Criteria criteria = example
					.createCriteria();
			criteria.andGatherFlagEqualTo("0").andStausEqualTo("1")
					.andFamilyIdLike(orgid + "%");

			if ("SSN".equals(term)) {
				criteria.andSsnLessThan(value + "%");
			} else if ("FAMILYNO".equals(term)) {
				criteria.andFamilyIdLike(value + "%");
			} else if ("MEMBERNAME".equals(term)) {
				criteria.andNameLike(value + "%");
			} else if ("PAPERID".equals(term)) {
				criteria.andIdcardLike(value + "%");

			} else {
			}
			session.put("sql", example);
			cur_page = "1";
		} else {
			example = (JzBizExample) session.get("sql");
		}

		this.setBizs(this.businessService.queryBusiByCity(example, new Integer(
				cur_page)));
		this.setToolsmenu(this.businessService.getPager().getToolsmenu());
		this.setOrgs(this.businessService.getOrganList(orgid));
		return SUCCESS;
	}

	@SuppressWarnings("rawtypes")
	public String queryBusiByCityInit() {
		Map session = ActionContext.getContext().getSession();
		UserInfoDTO userinfo = (UserInfoDTO) session.get("user");
		String orgid = userinfo.getOrganizationId();
		if (4 == orgid.length()) {
			this.setOrgs(this.businessService.getOrganList(userinfo
					.getOrganizationId()));
			return SUCCESS;
		} else {
			this.result = "您所在的机构，没有审批权限！";
			return "result";
		}

	}

	/**
	 * 日常救助查询
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String queryDailyMedical() {
		Map session = ActionContext.getContext().getSession();
		UserInfoDTO user = (UserInfoDTO) session.get("user");
		this.setPersonlist(this.businessService.queryDailyMedicalMember(
				this.personDTO.getFamilyno(), user.getOrganizationId()));
		return SUCCESS;
	}

	/**
	 * 区县日常救助查询
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String queryDailyMedicalInit() {
		Map session = ActionContext.getContext().getSession();
		UserInfoDTO user = (UserInfoDTO) session.get("user");
		if (user.getOrganizationId().length() == 6) {
			return SUCCESS;
		} else {
			this.result = "没有操作权限!";
			return ERROR;
		}
	}

	@SuppressWarnings("rawtypes")
	public String queryMedicalFamily() {

		Map map = ActionContext.getContext().getSession();
		UserInfoDTO userInfoDTO = (UserInfoDTO) map.get("user");

		personDTO.setOrganizationId(userInfoDTO.getOrganizationId());

		setPersonlist(this.businessService.findMedicalFamily(personDTO));

		return SUCCESS;
	}

	@SuppressWarnings("rawtypes")
	public String queryMedicalPerson() {

		Map map = ActionContext.getContext().getSession();
		UserInfoDTO userInfoDTO = (UserInfoDTO) map.get("user");

		personDTO.setAssistType(assistype);
		personDTO.setOrganizationId(userInfoDTO.getOrganizationId());
		personDTO.setSsn(ssn);
		personDTO = businessService.findMedicalPerson(personDTO);
		if (null == personDTO) {
			result = "此医保编号在城乡低保数据库不存在，或者不在登录机构内";
			return "noresult";
		} else {
			if ("outpatientfortown".equals(type)) {
				biztype = "11";
				this.setBizs(businessService.findBizBySsn(personDTO, biztype));
			}
			if ("outpatientforcountry".equals(type)) {
				biztype = "11";
			}
			if ("hospitalfortown".equals(type)) {
				biztype = "12";
				this.setBizs(businessService.findBizBySsn(personDTO, biztype));
			}
			if ("hospitalforcountry".equals(type)) {
				biztype = "12";
			}
			return type;
		}

	}

	/**
	 * 区县医前救助查询
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String queryPreMedical() {
		Map session = ActionContext.getContext().getSession();
		UserInfoDTO user = (UserInfoDTO) session.get("user");
		this.setPersonlist(this.businessService.queryPreMedicalMember(
				this.personDTO.getFamilyno(), user.getOrganizationId()));
		return SUCCESS;
	}

	/**
	 * 区县医前救助查询
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String queryPreMedicalInit() {
		Map session = ActionContext.getContext().getSession();
		UserInfoDTO user = (UserInfoDTO) session.get("user");
		if (user.getOrganizationId().length() == 6) {
			return SUCCESS;
		} else {
			this.result = "没有操作权限!";
			return ERROR;
		}
	}

	/**
	 * 表单字段 sick 或 sickname 返回 疾病文本 门诊和住院 疾病名称有所不同
	 * 
	 * @return
	 */
	public String querySickname() {

		String code = businessDTO.getSickname();
		if (null == code || "".equals(code) || "null".equals(code)) {
			code = this.DIAGNOSE_NAME;
		}

		if (null == code || "".equals(code)) {

		} else {
			List<Icd10> list = businessService.findSickListByCode(code, type);
			List<String[]> s = new ArrayList<String[]>();
			for (Icd10 ii : list) {
				s.add(new String[] { ii.getPycode() + "(" + ii.getName() + ")",
						ii.getIcdId().toString() });
			}
			this.setSicknames(s);
		}

		return SUCCESS;
	}

	@SuppressWarnings("rawtypes")
	public String saveBizrecord() {
		Map map = ActionContext.getContext().getSession();
		UserInfoDTO userInfoDTO = (UserInfoDTO) map.get("user");
		businessDTO.setEmpid(userInfoDTO.getEmpId());
		businessService.saveBizrecord(businessDTO);
		return SUCCESS;
	}

	public String saveBizrecordinit() {
		businessDTO.setMemberId(memberId);
		businessDTO.setSsn(ssn);
		businessDTO.setFamilyno(familyno);
		businessDTO.setMedicaltype(assistype);
		businessDTO.setMedicaltypename(businessService.getDictValue("004-"
				+ assistype));
		return SUCCESS;
	}

	// 业务处理2
	@SuppressWarnings("rawtypes")
	public String saveHospitalfortown() {

		Map map = ActionContext.getContext().getSession();
		UserInfoDTO userInfoDTO = (UserInfoDTO) map.get("user");
		businessDTO.setEmpid(userInfoDTO.getEmpId());

		businessService.saveHospitalForTown(businessDTO);

		return SUCCESS;
	}

	public String saveHospitalfortowninit() {

		businessDTO.setHospitallist(businessService.findHospitalList());
		/*
		 * HashMap<String, String> assises = new HashMap<String, String>();
		 * assises.put("11", "城市低保"); assises.put("10", "分类施保");
		 * businessDTO.setAssislist(assises);
		 */
		businessDTO.setSsn(ssn);
		businessDTO.setFamilyno(familyno);
		businessDTO.setMedicaltype(assistype);
		businessDTO.setMedicaltypename(businessService.getDictValue("004-"
				+ assistype));
		return "hospitalfortown-edit";
	}

	// 业务处理1
	@SuppressWarnings("rawtypes")
	public String saveOutpatientfortown() {
		Map map = ActionContext.getContext().getSession();
		UserInfoDTO userInfoDTO = (UserInfoDTO) map.get("user");
		businessDTO.setEmpid(userInfoDTO.getEmpId());
		businessService.saveOutpatientForTown(businessDTO);

		return SUCCESS;
	}

	public String saveOutpatientfortowninit() {

		businessDTO.setHospitallist(businessService.findHospitalList());
		/*
		 * HashMap<String, String> assises = new HashMap<String, String>();
		 * assises.put("10", "分类施保"); assises.put("11", "城市低保");
		 * businessDTO.setAssislist(assises);
		 */
		businessDTO.setSsn(ssn);
		businessDTO.setFamilyno(familyno);
		businessDTO.setMedicaltype(assistype);
		businessDTO.setMedicaltypename(businessService.getDictValue("004-"
				+ assistype));

		return "outpatientfortown-edit";
	}

	public void setAssistype(String assistype) {
		this.assistype = assistype;
	}

	public void setBizId(String bizId) {
		this.bizId = bizId;
	}

	public void setBizs(List<VbizDTO> bizs) {
		this.bizs = bizs;
	}

	public void setBiztype(String biztype) {
		this.biztype = biztype;
	}

	public void setBusinessDTO(BusinessDTO businessDTO) {
		this.businessDTO = businessDTO;
	}

	public void setBusinessService(BusinessService businessService) {
		this.businessService = businessService;
	}

	public void setCounttype(String counttype) {
		this.counttype = counttype;
	}

	public void setCur_page(String cur_page) {
		this.cur_page = cur_page;
	}

	public void setDIAGNOSE_NAME(String diagnose_name) {
		DIAGNOSE_NAME = diagnose_name;
	}

	public void setDictionaryHandle(DictionaryHandle dictionaryHandle) {
		this.dictionaryHandle = dictionaryHandle;
	}

	public void setFamilyno(String familyno) {
		this.familyno = familyno;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public void setOperational(String operational) {
		this.operational = operational;
	}

	public void setOpertime1(String opertime1) {
		this.opertime1 = opertime1;
	}

	public void setOpertime2(String opertime2) {
		this.opertime2 = opertime2;
	}

	public void setOrgs(List<OrganDTO> orgs) {
		this.orgs = orgs;
	}

	public void setPersonDTO(PersonDTO personDTO) {
		this.personDTO = personDTO;
	}

	public void setPersonlist(List<PersonDTO> personlist) {
		this.personlist = personlist;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public void setSicknames(List<String[]> sicknames) {
		this.sicknames = sicknames;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public void setSystemManager(SystemManager systemManager) {
		this.systemManager = systemManager;
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

	public void validateQueryMedicalFamily() {
		if (null == personDTO.getFamilyno()
				|| "".equals(personDTO.getFamilyno())) {
			this.addActionError("家庭编号不能为空");
		}
	}

	public void validateSaveBizrecord() {
		if (null == businessDTO.getBegintime()
				|| "".equals(businessDTO.getBegintime().toString())) {
			this.addActionError("入院日期不能为空");
		}
		if (null == businessDTO.getCurrenttime()
				|| "".equals(businessDTO.getCurrenttime().toString())) {
			this.addActionError("就诊日期不能为空");
		}
		if (null == businessDTO.getEndtime()
				|| "".equals(businessDTO.getEndtime().toString())) {
			this.addActionError("出院日期不能为空");
		}
		if (null == businessDTO.getSickname()
				|| "".equals(businessDTO.getSickname())) {
			this.addActionError("未选择就诊疾病");
		}
		if (null == businessDTO.getAllmoney()) {
			this.addActionError("填写现金支付总额");
		}
		if (null == businessDTO.getZ01()) {
			this.addActionError("填写保障金额");
		}
		if (null == businessDTO.getSickname()
				|| "".equals(businessDTO.getSickname())) {
			this.addActionError("填写疾病诊断名称");
		}
	}

	public void validateSaveHospitalfortown() {
		if (null == businessDTO.getBegintime()
				|| "".equals(businessDTO.getBegintime().toString())) {
			this.addActionError("入院日期不能为空");
		}
		if (null == businessDTO.getCurrenttime()
				|| "".equals(businessDTO.getCurrenttime().toString())) {
			this.addActionError("就诊日期不能为空");
		}
		if (null == businessDTO.getEndtime()
				|| "".equals(businessDTO.getEndtime().toString())) {
			this.addActionError("出院日期不能为空");
		}
		if (null == businessDTO.getSickname()
				|| "".equals(businessDTO.getSickname())) {
			this.addActionError("未选择就诊疾病");
		}
	}

	public void validateSaveOutpatientfortown() {
		if (null == businessDTO.getBegintime()
				|| "".equals(businessDTO.getBegintime().toString())) {
			this.addActionError("入院日期不能为空");
		}
		if (null == businessDTO.getCurrenttime()
				|| "".equals(businessDTO.getCurrenttime().toString())) {
			this.addActionError("就诊日期不能为空");
		}
		if (null == businessDTO.getEndtime()
				|| "".equals(businessDTO.getEndtime().toString())) {
			this.addActionError("出院日期不能为空");
		}
		if (null == businessDTO.getSickname()
				|| "".equals(businessDTO.getSickname())) {
			this.addActionError("未选择就诊疾病");
		}
	}

	public String viewBizrecord() {
		businessDTO = this.businessService.findBiz(bizId);
		return SUCCESS;
	}

	/**
	 * 日常救助
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String viewDailyMedical() {
		Map session = ActionContext.getContext().getSession();
		UserInfoDTO userinfo = (UserInfoDTO) session.get("user");
		String orgid = userinfo.getOrganizationId();
		JzBizExample example = new JzBizExample();
		if (null == cur_page || "".equals(cur_page)) {
			com.medical.model.JzBizExample.Criteria criteria = example
					.createCriteria();
			criteria.andGatherFlagEqualTo("7").andStausEqualTo("1")
					.andFamilyIdLike(this.oid + "%");
			if ("SSN".equals(term)) {
				criteria.andSsnLessThan(value + "%");
			} else if ("FAMILYNO".equals(term)) {
				criteria.andFamilyIdLike(value + "%");
			} else if ("MEMBERNAME".equals(term)) {
				criteria.andNameLike(value + "%");
			} else if ("PAPERID".equals(term)) {
				criteria.andIdcardLike(value + "%");
			} else {
			}
			if (null == this.opertime1 || null == this.opertime2
					|| "".equals(this.opertime1) || "".equals(this.opertime2)) {
			} else {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					Date date1 = sdf.parse(opertime1.substring(0, 10));
					Date date2 = sdf.parse(opertime2.substring(0, 10));
					criteria.andOpertimeBetween(date1, date2);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			session.put("sql", example);
			cur_page = "1";
		} else {
			example = (JzBizExample) session.get("sql");
		}
		this.setBizs(this.businessService.queryDailyMedicalBiz(example,
				new Integer(cur_page)));
		this.setToolsmenu(this.businessService.getPager().getToolsmenu());
		this.setOrgs(this.businessService.getOrganList(orgid));
		return SUCCESS;
	}

	/**
	 * 日常救助
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String viewDailyMedicalInit() {

		Map session = ActionContext.getContext().getSession();
		UserInfoDTO user = (UserInfoDTO) session.get("user");
		if (user.getOrganizationId().length() == 4) {
			this.setOrgs(this.businessService.getOrganList(user
					.getOrganizationId()));
			return SUCCESS;
		} else {
			this.result = "没有权限操作！";
			return ERROR;
		}
	}

	public String viewHospitalfortown() {
		businessDTO = this.businessService.findBiz(bizId);
		return SUCCESS;
	}

	public String viewOutpatientfortown() {
		businessDTO = this.businessService.findBiz(bizId);
		return SUCCESS;
	}

	/**
	 * 市一级查看审批结果
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String viewPreMedical() {
		Map session = ActionContext.getContext().getSession();
		UserInfoDTO userinfo = (UserInfoDTO) session.get("user");
		String orgid = userinfo.getOrganizationId();
		JzBizExample example = new JzBizExample();
		if (null == cur_page || "".equals(cur_page)) {
			com.medical.model.JzBizExample.Criteria criteria = example
					.createCriteria();
			criteria.andGatherFlagEqualTo("6").andStausEqualTo("1")
					.andFamilyIdLike(this.oid + "%");
			if ("SSN".equals(term)) {
				criteria.andSsnLessThan(value + "%");
			} else if ("FAMILYNO".equals(term)) {
				criteria.andFamilyIdLike(value + "%");
			} else if ("MEMBERNAME".equals(term)) {
				criteria.andNameLike(value + "%");
			} else if ("PAPERID".equals(term)) {
				criteria.andIdcardLike(value + "%");
			} else {
			}
			if (null == this.opertime1 || null == this.opertime2
					|| "".equals(this.opertime1) || "".equals(this.opertime2)) {
			} else {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					Date date1 = sdf.parse(opertime1.substring(0, 10));
					Date date2 = sdf.parse(opertime2.substring(0, 10));
					criteria.andOpertimeBetween(date1, date2);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			session.put("sql", example);
			cur_page = "1";
		} else {
			example = (JzBizExample) session.get("sql");
		}
		this.setBizs(this.businessService.queryPreMedicalBiz(example,
				new Integer(cur_page)));
		this.setToolsmenu(this.businessService.getPager().getToolsmenu());
		this.setOrgs(this.businessService.getOrganList(orgid));
		return SUCCESS;
	}

	/**
	 * 市一级查看审批结果
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String viewPreMedicalInit() {

		Map session = ActionContext.getContext().getSession();
		UserInfoDTO user = (UserInfoDTO) session.get("user");
		if (user.getOrganizationId().length() == 4) {
			this.setOrgs(this.businessService.getOrganList(user
					.getOrganizationId()));
			return SUCCESS;
		} else {
			this.result = "没有权限操作！";
			return ERROR;
		}
	}

	@SuppressWarnings("rawtypes")
	public String manualquerymember() {
		Map session = ActionContext.getContext().getSession();
		UserInfoDTO user = (UserInfoDTO) session.get("user");
		personDTO.setOrganizationId(user.getOrganizationId());
		personDTO.setAssistType("10-11");
		personDTO = this.businessService.findMedicalPerson(personDTO);
		if (null == personDTO) {
			this.result = "查询无结果，请重新核对此社会保险号!";
			return INPUT;
		} else {
			this.manualapproves = this.businessService
					.queryManualApproves(personDTO.getSsn());
			return SUCCESS;
		}
	}

	@SuppressWarnings("rawtypes")
	public String savemanualinit() {
		this.manual = new ManualDTO();
		Map session = ActionContext.getContext().getSession();
		UserInfoDTO user = (UserInfoDTO) session.get("user");
		personDTO.setSsn(ssn);
		personDTO.setOrganizationId(user.getOrganizationId());
		personDTO.setAssistType("10-11");
		personDTO = this.businessService.findMedicalPerson(personDTO);
		this.manual.setMembername(personDTO.getMembername());
		this.manual.setSex(personDTO.getSex());
		this.manual.setSsn(personDTO.getSsn());
		this.manual.setAddress(personDTO.getAddress());
		int age = 0;
		try {
			new Age();
			age = Age.getAge(personDTO.getBirthday());
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.manual.setAge(age);
		this.manual.setLinkmode(personDTO.getLinkmode());
		this.manual.setFamilyno(personDTO.getFamilyno());

		return SUCCESS;
	}

	public String manualmodify() {
		this.manual = this.businessService.findManualApprove(manual);
		System.out.println(manual.getOutFlag());
		if ("0".equals(manual.getOutFlag().trim())) {
			manual.setBiztype("0");
			manual.setOutFlag("0");
			System.out.println(manual.getOutFlag());
		} else if ("1".equals(manual.getOutFlag().trim())) {
			manual.setBiztype("0");
			manual.setOutFlag("1");
			System.out.println(manual.getOutFlag());
		} else if ("2".equals(manual.getOutFlag().trim())) {
			manual.setBiztype("1");
			manual.setOutFlag("0");
			System.out.println(manual.getOutFlag());
		} else if ("3".equals(manual.getOutFlag().trim())) {
			manual.setBiztype("1");
			manual.setOutFlag("1");
			System.out.println(manual.getOutFlag());
		}

		return SUCCESS;
	}

	@SuppressWarnings("rawtypes")
	public String savemanual() {
		if ("false".equals(manual.getOutFlag())
				&& "0".equals(manual.getBiztype())) {
			manual.setOutFlag("0");// 非转院住院
		} else if ("true".equals(manual.getOutFlag())
				&& "0".equals(manual.getBiztype())) {
			manual.setOutFlag("1");// 转院住院
		} else if ("false".equals(manual.getOutFlag())
				&& "1".equals(manual.getBiztype())) {
			manual.setOutFlag("2");// 非转院住院
		} else if ("true".equals(manual.getOutFlag())
				&& "1".equals(manual.getBiztype())) {
			manual.setOutFlag("3");// 非转院住院
		}
		Map session = ActionContext.getContext().getSession();
		UserInfoDTO user = (UserInfoDTO) session.get("user");
		this.manual.setEmpId(user.getEmpId());

		if (null == manual.getMyFileFileName()
				|| "".equals(manual.getMyFileFileName())) {

		} else {
			String imageFileName = new Date().getTime()
					+ getExtention(manual.getMyFileFileName());
			this.manual.setFilename(imageFileName);

			String realfilepath = ServletActionContext.getServletContext()
					.getRealPath("/");
			realfilepath = realfilepath.substring(0, realfilepath.length() - 1);
			realfilepath = realfilepath + "upload\\";
			File dir = new File(realfilepath);
			if (dir.exists()) {
				dir.mkdirs();
			}
			File imageFile = new File(realfilepath + imageFileName);
			copy(manual.getMyFile(), imageFile);
			// ftp上传
			FtpUpload ftpup = new FtpUpload();
			ftpup.UploadFile(realfilepath + imageFileName, imageFileName);

		}
		this.businessService.saveManualApprove(this.manual);
		return SUCCESS;
	}

	public String viewmanual() {
		this.manual = this.businessService.findManualApprove(manual);
		return SUCCESS;
	}

	@SuppressWarnings("rawtypes")
	public String cancelmanual() {
		Map session = ActionContext.getContext().getSession();
		UserInfoDTO user = (UserInfoDTO) session.get("user");
		if (user.getOrganizationId().length() == 4) {
			this.businessService.removeManualApprove(manual);
			this.result = "<a href=\"manualquerymember?personDTO.ssn="
					+ manual.getSsn() + "\">返回</a>";
		}
		return SUCCESS;
	}

	public String printmanual() {
		return "";
	}

	public String printmanual1() {
		manual = businessService.findManualApprove(manual);
		manualapproves = new ArrayList<ManualDTO>();
		manualapproves.add(manual);
		return SUCCESS;

	}

	public String printbill() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(printBillDTO.getBegin());
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(printBillDTO.getEnd());
		Calendar cal = Calendar.getInstance();
		String begin = sdf.format(cal1.getTime());
		String end = sdf.format(cal2.getTime());
		String current = sdf.format(cal.getTime());
		map = new HashMap<String, String>();
		map.put("begin", begin);
		map.put("end", end);
		map.put("current", current);
		printBillDTO.setBeginval(begin);
		printBillDTO.setEndval(end);
		bills = businessService.findBillTotalByDept(printBillDTO);
		return SUCCESS;
	}
	@SuppressWarnings("rawtypes")
	public String printbilloneinit() {
		Map session = ActionContext.getContext().getSession();
		UserInfoDTO user = (UserInfoDTO) session.get("user");
		this.setOrgs(this.searchService.getOrganList(user.getOrganizationId()));
		this.setHs(this.searchService.getHospitals());
		return SUCCESS;
	}
	public String printbillone() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(printBillDTO.getBegin());
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(printBillDTO.getEnd());
		Calendar cal = Calendar.getInstance();
		String begin = sdf.format(cal1.getTime());
		String end = sdf.format(cal2.getTime());
		String current = sdf.format(cal.getTime());
		map = new HashMap<String, String>();
		map.put("begin", begin);
		map.put("end", end);
		map.put("current", current);
		printBillDTO.setBeginval(begin);
		printBillDTO.setEndval(end);
		printBillDTO.setHid(hid);
		bills = businessService.findBillTotalByDept(printBillDTO);
		return SUCCESS;
	}

	public void validatePrintbillone() {
		if (printBillDTO.getBegin() != null && printBillDTO.getEnd() != null) {
		} else {
			this.addActionError("选择开始结束时间");
		}
	}

	public void validate() {
		super.validate();
	}

	@SuppressWarnings("rawtypes")
	public String querymanualinit() {
		Map session = ActionContext.getContext().getSession();
		UserInfoDTO userinfo = (UserInfoDTO) session.get("user");
		String orgid = userinfo.getOrganizationId();
		this.setOrgs(this.businessService.getOrganList(orgid));
		return SUCCESS;
	}

	@SuppressWarnings("rawtypes")
	public String querymanual() {
		Map session = ActionContext.getContext().getSession();
		UserInfoDTO userinfo = (UserInfoDTO) session.get("user");
		String orgid = userinfo.getOrganizationId();

		JzManualExample example = new JzManualExample();
		if (null == cur_page || "".equals(cur_page)) {
			JzManualExample.Criteria criteria = example.createCriteria();
			criteria.andCheckstateEqualTo("1").andFamilynoLike(this.oid + "%");
			if ("SSN".equals(term)) {
				criteria.andSsnEqualTo(value);
			} else if ("FAMILYNO".equals(term)) {
				criteria.andFamilynoLike(value + "%");
			} else if ("MEMBERNAME".equals(term)) {
				criteria.andMembernameLike(value + "%");
			} else if ("PAPERID".equals(term)) {
				if (null == value || "".equals(value)) {
				} else {
					criteria.andManualnoEqualTo(value);
				}
			} else {
			}
			session.put("sql", example);
			cur_page = "1";
		} else {
			example = (JzManualExample) session.get("sql");
		}
		this.manualapproves = this.businessService.queryManualApprove(example,
				new Integer(cur_page));
		this.setToolsmenu(this.businessService.getPager().getToolsmenu());
		this.setOrgs(this.businessService.getOrganList(orgid));
		return SUCCESS;
	}

	private static void copy(File src, File dst) {
		try {
			InputStream in = null;
			OutputStream out = null;
			try {
				in = new BufferedInputStream(new FileInputStream(src),
						BUFFER_SIZE);
				out = new BufferedOutputStream(new FileOutputStream(dst),
						BUFFER_SIZE);
				byte[] buffer = new byte[BUFFER_SIZE];
				while (in.read(buffer) > 0) {
					out.write(buffer);
				}
			} finally {
				if (null != in) {
					in.close();
				}
				if (null != out) {
					out.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String getExtention(String fileName) {
		int pos = fileName.lastIndexOf(".");
		return fileName.substring(pos);
	}

	public void setBills(List<PrintBillDTO> bills) {
		this.bills = bills;
	}

	public List<PrintBillDTO> getBills() {
		return bills;
	}

	public void setPrintBillDTO(PrintBillDTO printBillDTO) {
		this.printBillDTO = printBillDTO;
	}

	public PrintBillDTO getPrintBillDTO() {
		return printBillDTO;
	}

	public void setMap(HashMap<String, String> map) {
		this.map = map;
	}

	public HashMap<String, String> getMap() {
		return map;
	}

	public ManualDTO getManual() {
		return manual;
	}

	public void setManual(ManualDTO manual) {
		this.manual = manual;
	}

	public List<ManualDTO> getManualapproves() {
		return manualapproves;
	}

	public void setManualapproves(List<ManualDTO> manualapproves) {
		this.manualapproves = manualapproves;
	}

	public SearchService getSearchService() {
		return searchService;
	}

	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
	}

	public List<DeptDTO> getHs() {
		return hs;
	}

	public void setHs(List<DeptDTO> hs) {
		this.hs = hs;
	}
	
}
