package com.medical.dto;

import java.util.Date;

public class PersonDTO {
	private String memberId;
	private String memberType;
	private String membername;

	private String paperid;

	private String ssn;

	private String familyno;

	private String relmaster;

	private String sex;

	private String masterName;

	private Date birthday;

	private String rprkind;

	private String rprtype;

	private String rpraddress;

	private String linkmode;

	private String address;

	private String assistType;

	private String organizationId;

	private String assisTypeId;

	private Short isybsqdb;

	public String getAssisTypeId() {
		return assisTypeId;
	}

	public void setAssisTypeId(String assisTypeId) {
		this.assisTypeId = assisTypeId;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMembername() {
		return membername;
	}

	public void setMembername(String membername) {
		this.membername = membername;
	}

	public String getPaperid() {
		return paperid;
	}

	public void setPaperid(String paperid) {
		this.paperid = paperid;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getFamilyno() {
		return familyno;
	}

	public void setFamilyno(String familyno) {
		this.familyno = familyno;
	}

	public String getRelmaster() {
		return relmaster;
	}

	public void setRelmaster(String relmaster) {
		this.relmaster = relmaster;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getMasterName() {
		return masterName;
	}

	public void setMasterName(String masterName) {
		this.masterName = masterName;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getRprkind() {
		return rprkind;
	}

	public void setRprkind(String rprkind) {
		this.rprkind = rprkind;
	}

	public String getRprtype() {
		return rprtype;
	}

	public void setRprtype(String rprtype) {
		this.rprtype = rprtype;
	}

	public String getRpraddress() {
		return rpraddress;
	}

	public void setRpraddress(String rpraddress) {
		this.rpraddress = rpraddress;
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

	public String getAssistType() {
		return assistType;
	}

	public void setAssistType(String assistType) {
		this.assistType = assistType;
	}

	public void setIsybsqdb(Short isybsqdb) {
		this.isybsqdb = isybsqdb;
	}

	public Short getIsybsqdb() {
		return isybsqdb;
	}

	public String getMemberType() {
		return memberType;
	}

	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}
	
}
