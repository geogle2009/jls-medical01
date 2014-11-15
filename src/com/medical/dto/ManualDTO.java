package com.medical.dto;

import java.io.File;
import java.math.BigDecimal;
import java.util.Date;

public class ManualDTO {
	private Long manualId;
	private String manualno;
	private String invoice;
	private String familyno;
	private String ssn;
	private String membername;
	private String sex;
	private Integer age;
	private String linkmode;
	private String address;
	private String asistype;
	private String hospitalname;
	private String sickname;
	private Date currenttime;
	private Date begintime;
	private Date endtime;
	private BigDecimal accountpayment;
	private Integer days;
	private BigDecimal planpayment;
	private BigDecimal predeposit;
	private BigDecimal recdeposit;
	private BigDecimal officialpayment1;
	private BigDecimal initline;
	private BigDecimal selfscale;
	private BigDecimal officialpayment;
	private BigDecimal selfmoney;
	private BigDecimal selfall;
	private BigDecimal healthpayment;
	private BigDecimal hospitalmoney;
	private BigDecimal oaccountbalance;
	private BigDecimal bigpayment;
	private BigDecimal accountbalance;
	private BigDecimal restpayment;
	private BigDecimal injurypayment;
	private BigDecimal bearpayment;
	private Date opertime;
	private String empId;
	private String checkstate;
	private File myFile;
	private String filename;
	
	private String assistFlag ;  
	private Date assistTime ;
	private String inHospitalname ; 
	private String outFlag ;	
	private String biztype;
	private String myFileFileName;

	public String getMyFileFileName() {
		return myFileFileName;
	}

	public void setMyFileFileName(String myFileFileName) {
		this.myFileFileName = myFileFileName;
	}

	public File getMyFile() {
		return myFile;
	}

	public void setMyFile(File myFile) {
		this.myFile = myFile;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Long getManualId() {
		return manualId;
	}

	public void setManualId(Long manualId) {
		this.manualId = manualId;
	}

	public String getManualno() {
		return manualno;
	}

	public void setManualno(String manualno) {
		this.manualno = manualno;
	}

	public String getInvoice() {
		return invoice;
	}

	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}

	public String getFamilyno() {
		return familyno;
	}

	public void setFamilyno(String familyno) {
		this.familyno = familyno;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getMembername() {
		return membername;
	}

	public void setMembername(String membername) {
		this.membername = membername;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getLinkmode() {
		return linkmode;
	}

	public void setLinkmode(String linkmode) {
		this.linkmode = linkmode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAsistype() {
		return asistype;
	}

	public void setAsistype(String asistype) {
		this.asistype = asistype;
	}

	public String getHospitalname() {
		return hospitalname;
	}

	public void setHospitalname(String hospitalname) {
		this.hospitalname = hospitalname;
	}

	public String getSickname() {
		return sickname;
	}

	public void setSickname(String sickname) {
		this.sickname = sickname;
	}

	public Date getCurrenttime() {
		return currenttime;
	}

	public void setCurrenttime(Date currenttime) {
		this.currenttime = currenttime;
	}

	public Date getBegintime() {
		return begintime;
	}

	public void setBegintime(Date begintime) {
		this.begintime = begintime;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public BigDecimal getAccountpayment() {
		return accountpayment;
	}

	public void setAccountpayment(BigDecimal accountpayment) {
		this.accountpayment = accountpayment;
	}

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

	public BigDecimal getPlanpayment() {
		return planpayment;
	}

	public void setPlanpayment(BigDecimal planpayment) {
		this.planpayment = planpayment;
	}

	public BigDecimal getPredeposit() {
		return predeposit;
	}

	public void setPredeposit(BigDecimal predeposit) {
		this.predeposit = predeposit;
	}

	public BigDecimal getRecdeposit() {
		return recdeposit;
	}

	public void setRecdeposit(BigDecimal recdeposit) {
		this.recdeposit = recdeposit;
	}

	public BigDecimal getOfficialpayment1() {
		return officialpayment1;
	}

	public void setOfficialpayment1(BigDecimal officialpayment1) {
		this.officialpayment1 = officialpayment1;
	}

	public BigDecimal getInitline() {
		return initline;
	}

	public void setInitline(BigDecimal initline) {
		this.initline = initline;
	}

	public BigDecimal getSelfscale() {
		return selfscale;
	}

	public void setSelfscale(BigDecimal selfscale) {
		this.selfscale = selfscale;
	}

	public BigDecimal getOfficialpayment() {
		return officialpayment;
	}

	public void setOfficialpayment(BigDecimal officialpayment) {
		this.officialpayment = officialpayment;
	}

	public BigDecimal getSelfmoney() {
		return selfmoney;
	}

	public void setSelfmoney(BigDecimal selfmoney) {
		this.selfmoney = selfmoney;
	}

	public BigDecimal getSelfall() {
		return selfall;
	}

	public void setSelfall(BigDecimal selfall) {
		this.selfall = selfall;
	}

	public BigDecimal getHealthpayment() {
		return healthpayment;
	}

	public void setHealthpayment(BigDecimal healthpayment) {
		this.healthpayment = healthpayment;
	}

	public BigDecimal getHospitalmoney() {
		return hospitalmoney;
	}

	public void setHospitalmoney(BigDecimal hospitalmoney) {
		this.hospitalmoney = hospitalmoney;
	}

	public BigDecimal getOaccountbalance() {
		return oaccountbalance;
	}

	public void setOaccountbalance(BigDecimal oaccountbalance) {
		this.oaccountbalance = oaccountbalance;
	}

	public BigDecimal getBigpayment() {
		return bigpayment;
	}

	public void setBigpayment(BigDecimal bigpayment) {
		this.bigpayment = bigpayment;
	}

	public BigDecimal getAccountbalance() {
		return accountbalance;
	}

	public void setAccountbalance(BigDecimal accountbalance) {
		this.accountbalance = accountbalance;
	}

	public BigDecimal getRestpayment() {
		return restpayment;
	}

	public void setRestpayment(BigDecimal restpayment) {
		this.restpayment = restpayment;
	}

	public BigDecimal getInjurypayment() {
		return injurypayment;
	}

	public void setInjurypayment(BigDecimal injurypayment) {
		this.injurypayment = injurypayment;
	}

	public BigDecimal getBearpayment() {
		return bearpayment;
	}

	public void setBearpayment(BigDecimal bearpayment) {
		this.bearpayment = bearpayment;
	}

	public Date getOpertime() {
		return opertime;
	}

	public void setOpertime(Date opertime) {
		this.opertime = opertime;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getCheckstate() {
		return checkstate;
	}

	public void setCheckstate(String checkstate) {
		this.checkstate = checkstate;
	}

	public String getAssistFlag() {
		return assistFlag;
	}

	public void setAssistFlag(String assistFlag) {
		this.assistFlag = assistFlag;
	}

	public Date getAssistTime() {
		return assistTime;
	}

	public void setAssistTime(Date assistTime) {
		this.assistTime = assistTime;
	}

	public String getInHospitalname() {
		return inHospitalname;
	}

	public void setInHospitalname(String inHospitalname) {
		this.inHospitalname = inHospitalname;
	}

	public String getOutFlag() {
		return outFlag;
	}

	public void setOutFlag(String outFlag) {
		this.outFlag = outFlag;
	}

	public String getBiztype() {
		return biztype;
	}

	public void setBiztype(String biztype) {
		this.biztype = biztype;
	}

}
