package com.medical.dto;

import java.math.BigDecimal;
import java.util.Date;

public class BizDTO {
	private Integer bizId;
	private String hospitalId;
	private String centerId;
	private String bizType;
	private String bizName;
	private String serialNo;
	private String ssn;
	private String familyId;
	private String name;
	private String sex;
	private Date birthday;
	private String idcard;
	private String treatmentType;
	private String treatmentName;
	private Short bizTimes;
	private Date regDate;
	private Date beginDate;
	private String inDeptName;
	private String inAreaName;
	private String inBed;
	private String inDisease;
	private String inDiseaseName;
	private String diagnose;
	private String diagnoseName;
	private Date diagnoseDate;
	private String finDisease;
	private String finDiseaseName;
	private Date endDate;
	private String outDeptName;
	private String complications;
	private Short inDays;
	private String assistType;
	private String staus;
	private String patientId;
	private Date finDate;
	private BigDecimal inmoney;
	private BigDecimal operuid;
	private Date opertime;
	private String assistFlag;
	private String photopath;
	private BigDecimal allmoney;
	private BigDecimal personpay;
	private BigDecimal insurancepay;
	private BigDecimal assistpay;
	private String assistpaymsg;
	private String photopath2;
	private String confirmFlag;
	private BigDecimal inMoney;
	private String personType;
	private Integer feeBatch;
	private Short outFlag;
	private Short alreadyGet;
	private String gatherFlag;
	private BigDecimal smsState;
	private BigDecimal checkState;
	public BigDecimal getAllmoney() {
		return allmoney;
	}
	public Short getAlreadyGet() {
		return alreadyGet;
	}
	public String getAssistFlag() {
		return assistFlag;
	}
	public BigDecimal getAssistpay() {
		return assistpay;
	}
	public String getAssistpaymsg() {
		return assistpaymsg;
	}
	public String getAssistType() {
		return assistType;
	}
	public Date getBeginDate() {
		return beginDate;
	}
	public Date getBirthday() {
		return birthday;
	}
	public Integer getBizId() {
		return bizId;
	}
	public String getBizName() {
		return bizName;
	}
	public Short getBizTimes() {
		return bizTimes;
	}
	public String getBizType() {
		return bizType;
	}
	public String getCenterId() {
		return centerId;
	}
	public BigDecimal getCheckState() {
		return checkState;
	}
	public String getComplications() {
		return complications;
	}
	public String getConfirmFlag() {
		return confirmFlag;
	}
	public String getDiagnose() {
		return diagnose;
	}
	public Date getDiagnoseDate() {
		return diagnoseDate;
	}
	public String getDiagnoseName() {
		return diagnoseName;
	}
	public Date getEndDate() {
		return endDate;
	}
	public String getFamilyId() {
		return familyId;
	}
	public Integer getFeeBatch() {
		return feeBatch;
	}
	public Date getFinDate() {
		return finDate;
	}
	public String getFinDisease() {
		return finDisease;
	}
	public String getFinDiseaseName() {
		return finDiseaseName;
	}
	public String getGatherFlag() {
		return gatherFlag;
	}
	public String getHospitalId() {
		return hospitalId;
	}
	public String getIdcard() {
		return idcard;
	}
	public String getInAreaName() {
		return inAreaName;
	}
	public String getInBed() {
		return inBed;
	}
	public Short getInDays() {
		return inDays;
	}
	public String getInDeptName() {
		return inDeptName;
	}
	public String getInDisease() {
		return inDisease;
	}
	public String getInDiseaseName() {
		return inDiseaseName;
	}
	public BigDecimal getInmoney() {
		return inmoney;
	}
	public BigDecimal getInMoney() {
		return inMoney;
	}
	public BigDecimal getInsurancepay() {
		return insurancepay;
	}
	public String getName() {
		return name;
	}
	public Date getOpertime() {
		return opertime;
	}
	public BigDecimal getOperuid() {
		return operuid;
	}
	public String getOutDeptName() {
		return outDeptName;
	}
	public Short getOutFlag() {
		return outFlag;
	}
	public String getPatientId() {
		return patientId;
	}
	public BigDecimal getPersonpay() {
		return personpay;
	}
	public String getPersonType() {
		return personType;
	}
	public String getPhotopath() {
		return photopath;
	}
	public String getPhotopath2() {
		return photopath2;
	}
	public Date getRegDate() {
		return regDate;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public String getSex() {
		return sex;
	}
	public BigDecimal getSmsState() {
		return smsState;
	}
	public String getSsn() {
		return ssn;
	}
	public String getStaus() {
		return staus;
	}
	public String getTreatmentName() {
		return treatmentName;
	}
	public String getTreatmentType() {
		return treatmentType;
	}
	public void setAllmoney(BigDecimal allmoney) {
		this.allmoney = allmoney;
	}
	public void setAlreadyGet(Short alreadyGet) {
		this.alreadyGet = alreadyGet;
	}
	public void setAssistFlag(String assistFlag) {
		this.assistFlag = assistFlag;
	}
	public void setAssistpay(BigDecimal assistpay) {
		this.assistpay = assistpay;
	}
	public void setAssistpaymsg(String assistpaymsg) {
		this.assistpaymsg = assistpaymsg;
	}
	public void setAssistType(String assistType) {
		this.assistType = assistType;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}
	public void setBizName(String bizName) {
		this.bizName = bizName;
	}
	public void setBizTimes(Short bizTimes) {
		this.bizTimes = bizTimes;
	}
	public void setBizType(String bizType) {
		this.bizType = bizType;
	}
	public void setCenterId(String centerId) {
		this.centerId = centerId;
	}
	public void setCheckState(BigDecimal checkState) {
		this.checkState = checkState;
	}
	public void setComplications(String complications) {
		this.complications = complications;
	}
	public void setConfirmFlag(String confirmFlag) {
		this.confirmFlag = confirmFlag;
	}
	public void setDiagnose(String diagnose) {
		this.diagnose = diagnose;
	}
	public void setDiagnoseDate(Date diagnoseDate) {
		this.diagnoseDate = diagnoseDate;
	}
	public void setDiagnoseName(String diagnoseName) {
		this.diagnoseName = diagnoseName;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public void setFamilyId(String familyId) {
		this.familyId = familyId;
	}
	public void setFeeBatch(Integer feeBatch) {
		this.feeBatch = feeBatch;
	}
	public void setFinDate(Date finDate) {
		this.finDate = finDate;
	}
	public void setFinDisease(String finDisease) {
		this.finDisease = finDisease;
	}
	public void setFinDiseaseName(String finDiseaseName) {
		this.finDiseaseName = finDiseaseName;
	}
	public void setGatherFlag(String gatherFlag) {
		this.gatherFlag = gatherFlag;
	}
	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public void setInAreaName(String inAreaName) {
		this.inAreaName = inAreaName;
	}
	public void setInBed(String inBed) {
		this.inBed = inBed;
	}
	public void setInDays(Short inDays) {
		this.inDays = inDays;
	}
	public void setInDeptName(String inDeptName) {
		this.inDeptName = inDeptName;
	}
	public void setInDisease(String inDisease) {
		this.inDisease = inDisease;
	}
	public void setInDiseaseName(String inDiseaseName) {
		this.inDiseaseName = inDiseaseName;
	}
	public void setInmoney(BigDecimal inmoney) {
		this.inmoney = inmoney;
	}
	public void setInMoney(BigDecimal inMoney) {
		this.inMoney = inMoney;
	}
	public void setInsurancepay(BigDecimal insurancepay) {
		this.insurancepay = insurancepay;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setOpertime(Date opertime) {
		this.opertime = opertime;
	}
	public void setOperuid(BigDecimal operuid) {
		this.operuid = operuid;
	}
	public void setOutDeptName(String outDeptName) {
		this.outDeptName = outDeptName;
	}
	public void setOutFlag(Short outFlag) {
		this.outFlag = outFlag;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public void setPersonpay(BigDecimal personpay) {
		this.personpay = personpay;
	}
	public void setPersonType(String personType) {
		this.personType = personType;
	}
	public void setPhotopath(String photopath) {
		this.photopath = photopath;
	}
	public void setPhotopath2(String photopath2) {
		this.photopath2 = photopath2;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public void setSmsState(BigDecimal smsState) {
		this.smsState = smsState;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public void setStaus(String staus) {
		this.staus = staus;
	}
	public void setTreatmentName(String treatmentName) {
		this.treatmentName = treatmentName;
	}
	public void setTreatmentType(String treatmentType) {
		this.treatmentType = treatmentType;
	}
}
