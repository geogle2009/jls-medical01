package com.medical.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.medical.model.DmBizdept;
import com.medical.model.Icd10;
import com.medical.model.JzBiz;

public class BusinessDTO {

	private String accountbalance;// 个人账户余额 113
	private String accountpayment;// 个人账户支付 003
	private String allmoney;// 现金支付总额999
	private HashMap<String, String> assislist;// 参保类别
	private String bearpayment;// 生育保险支付 701
	private Date begintime;// 入院时间
	private String bigpayment;// 大额保险支付 201
	private String bizId;// 业务id
	private List<JzBiz> bizList;
	private String bizType;
	private BigDecimal check1;
	private BigDecimal check2;
	private BigDecimal check3;
	private BigDecimal check4;
	// check info
	private String checkorgname;// 审核机构
	private String checkresult; // 审核结果
	private String checkstate;// 审核状态 审核 未审核
	private Date currenttime;// 就医时间
	private String days;// 住院天数
	private String empid;
	private Date endtime;// 出院时间
	// bizcheck
	private BigDecimal estimate;
	private String familyno;// 家庭编号
	private String healthpayment;// 保健对象补助支付 997+303
	private String hospital;// 医院名称
	private List<DmBizdept> hospitallist;// 医院列表
	private String hospitalmoney;// 住院费用总额 111
	private String hospitalname;
	private String initline;// 起付线 112
	private String injurypayment;// 工伤保险支付 501
	private String medicaltype;// 参保类型、医保类型

	private String medicaltypename;// 参保类型、医保类型

	private String memberId;

	private String oaccountbalance;// 原个人账户余额 113+003

	private String officialpayment;// 公务员补助 301

	private String officialpayment1;// 公务员补基本医疗部分 301

	private String orgname1;

	private String orgname2;

	private String orgname3;

	private String orgname4;

	private String planpayment;// 统筹基金支付 001
	private String predeposit;// 预交住院押金 114
	private HashMap<String, String> presicks;// 参保类别
	private String recdeposit;// 退补住院押金 114-999
	private String restpayment;// 离休统筹支付 202
	private String selfall;// 自付合计 116
	private String selfmoney;// 自理费用

	private String selfscale;// 自付比例 999+003+301+303-112-116-117
	private List<Icd10> sicklist;// 疾病列表

	private String sickname;// 疾病诊断

	private String sicknameKey;
	private String ssn;// 医保编号
	private String z01; // 救助金 z01
	private String z02; // 实际缴纳金额z02

	public String getAccountbalance() {
		return accountbalance;
	}

	public String getAccountpayment() {
		return accountpayment;
	}

	public String getAllmoney() {
		return allmoney;
	}

	public HashMap<String, String> getAssislist() {
		return assislist;
	}

	public String getBearpayment() {
		return bearpayment;
	}

	public Date getBegintime() {
		return begintime;
	}

	public String getBigpayment() {
		return bigpayment;
	}

	public String getBizId() {
		return bizId;
	}

	public List<JzBiz> getBizList() {
		return bizList;
	}

	public String getBizType() {
		return bizType;
	}

	public BigDecimal getCheck1() {
		return check1;
	}

	public BigDecimal getCheck2() {
		return check2;
	}

	public BigDecimal getCheck3() {
		return check3;
	}

	public BigDecimal getCheck4() {
		return check4;
	}

	public String getCheckorgname() {
		return checkorgname;
	}

	public String getCheckresult() {
		return checkresult;
	}

	public String getCheckstate() {
		return checkstate;
	}

	public Date getCurrenttime() {
		return currenttime;
	}

	public String getDays() {
		return days;
	}

	public String getEmpid() {
		return empid;
	}

	public Date getEndtime() {
		return endtime;
	}

	public BigDecimal getEstimate() {
		return estimate;
	}

	public String getFamilyno() {
		return familyno;
	}

	public String getHealthpayment() {
		return healthpayment;
	}

	public String getHospital() {
		return hospital;
	}

	public List<DmBizdept> getHospitallist() {
		return hospitallist;
	}

	public String getHospitalmoney() {
		return hospitalmoney;
	}

	public String getHospitalname() {
		return hospitalname;
	}

	public String getInitline() {
		return initline;
	}

	public String getInjurypayment() {
		return injurypayment;
	}

	public String getMedicaltype() {
		return medicaltype;
	}

	public String getMedicaltypename() {
		return medicaltypename;
	}

	public String getMemberId() {
		return memberId;
	}

	public String getOaccountbalance() {
		return oaccountbalance;
	}

	public String getOfficialpayment() {
		return officialpayment;
	}

	public String getOfficialpayment1() {
		return officialpayment1;
	}

	public String getOrgname1() {
		return orgname1;
	}

	public String getOrgname2() {
		return orgname2;
	}

	public String getOrgname3() {
		return orgname3;
	}

	public String getOrgname4() {
		return orgname4;
	}

	public String getPlanpayment() {
		return planpayment;
	}

	public String getPredeposit() {
		return predeposit;
	}

	public HashMap<String, String> getPresicks() {
		return presicks;
	}

	public String getRecdeposit() {
		return recdeposit;
	}

	public String getRestpayment() {
		return restpayment;
	}

	public String getSelfall() {
		return selfall;
	}

	public String getSelfmoney() {
		return selfmoney;
	}

	public String getSelfscale() {
		return selfscale;
	}

	public List<Icd10> getSicklist() {
		return sicklist;
	}

	public String getSickname() {
		return sickname;
	}

	public String getSicknameKey() {
		return sicknameKey;
	}

	public String getSsn() {
		return ssn;
	}

	public String getZ01() {
		return z01;
	}

	public String getZ02() {
		return z02;
	}

	public void setAccountbalance(String accountbalance) {
		this.accountbalance = accountbalance;
	}

	public void setAccountpayment(String accountpayment) {
		this.accountpayment = accountpayment;
	}

	public void setAllmoney(String allmoney) {
		this.allmoney = allmoney;
	}

	public void setAssislist(HashMap<String, String> assislist) {
		this.assislist = assislist;
	}

	public void setBearpayment(String bearpayment) {
		this.bearpayment = bearpayment;
	}

	public void setBegintime(Date begintime) {
		this.begintime = begintime;
	}

	public void setBigpayment(String bigpayment) {
		this.bigpayment = bigpayment;
	}

	public void setBizId(String bizId) {
		this.bizId = bizId;
	}

	public void setBizList(List<JzBiz> bizList) {
		this.bizList = bizList;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	public void setCheck1(BigDecimal check1) {
		this.check1 = check1;
	}

	public void setCheck2(BigDecimal check2) {
		this.check2 = check2;
	}

	public void setCheck3(BigDecimal check3) {
		this.check3 = check3;
	}

	public void setCheck4(BigDecimal check4) {
		this.check4 = check4;
	}

	public void setCheckorgname(String checkorgname) {
		this.checkorgname = checkorgname;
	}

	public void setCheckresult(String checkresult) {
		this.checkresult = checkresult;
	}

	public void setCheckstate(String checkstate) {
		this.checkstate = checkstate;
	}

	public void setCurrenttime(Date currenttime) {
		this.currenttime = currenttime;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public void setEmpid(String empid) {
		this.empid = empid;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public void setEstimate(BigDecimal estimate) {
		this.estimate = estimate;
	}

	public void setFamilyno(String familyno) {
		this.familyno = familyno;
	}

	public void setHealthpayment(String healthpayment) {
		this.healthpayment = healthpayment;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	public void setHospitallist(List<DmBizdept> hospitallist) {
		this.hospitallist = hospitallist;
	}

	public void setHospitalmoney(String hospitalmoney) {
		this.hospitalmoney = hospitalmoney;
	}

	public void setHospitalname(String hospitalname) {
		this.hospitalname = hospitalname;
	}

	public void setInitline(String initline) {
		this.initline = initline;
	}

	public void setInjurypayment(String injurypayment) {
		this.injurypayment = injurypayment;
	}

	public void setMedicaltype(String medicaltype) {
		this.medicaltype = medicaltype;
	}

	public void setMedicaltypename(String medicaltypename) {
		this.medicaltypename = medicaltypename;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public void setOaccountbalance(String oaccountbalance) {
		this.oaccountbalance = oaccountbalance;
	}

	public void setOfficialpayment(String officialpayment) {
		this.officialpayment = officialpayment;
	}

	public void setOfficialpayment1(String officialpayment1) {
		this.officialpayment1 = officialpayment1;
	}

	public void setOrgname1(String orgname1) {
		this.orgname1 = orgname1;
	}

	public void setOrgname2(String orgname2) {
		this.orgname2 = orgname2;
	}

	public void setOrgname3(String orgname3) {
		this.orgname3 = orgname3;
	}

	public void setOrgname4(String orgname4) {
		this.orgname4 = orgname4;
	}

	public void setPlanpayment(String planpayment) {
		this.planpayment = planpayment;
	}

	public void setPredeposit(String predeposit) {
		this.predeposit = predeposit;
	}

	public void setPresicks(HashMap<String, String> presicks) {
		this.presicks = presicks;
	}

	public void setRecdeposit(String recdeposit) {
		this.recdeposit = recdeposit;
	}

	public void setRestpayment(String restpayment) {
		this.restpayment = restpayment;
	}

	public void setSelfall(String selfall) {
		this.selfall = selfall;
	}

	public void setSelfmoney(String selfmoney) {
		this.selfmoney = selfmoney;
	}

	public void setSelfscale(String selfscale) {
		this.selfscale = selfscale;
	}

	public void setSicklist(List<Icd10> sicklist) {
		this.sicklist = sicklist;
	}

	public void setSickname(String sickname) {
		this.sickname = sickname;
	}

	public void setSicknameKey(String sicknameKey) {
		this.sicknameKey = sicknameKey;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public void setZ01(String z01) {
		this.z01 = z01;
	}

	public void setZ02(String z02) {
		this.z02 = z02;
	}
}
