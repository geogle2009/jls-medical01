package com.medical.dto;

import java.util.Date;
import java.util.List;

public class HealthDTO {
	private String memberId;

	private String membername;

	private String paperid;

	private String ssn;

	private String familyno;

	private String relmaster;

	private String sex;

	private String masterName;

	private Date birthday;

	private String health;

	private String sickentype;

	private String sickenname;

	private String deformity;

	private String defgrade;

	private String rprkind;

	private String rprtype;

	private String rpraddress;

	private String linkmode;

	private String address;
	private String assistType;
	private List<BizDTO> bizDTOs;
	private String term;
	private String operational;
	private String value;
	private int curpage;

	private String url;

	private int pageSize;

	public String getAddress() {
		return address;
	}

	public String getAssistType() {
		return assistType;
	}

	public Date getBirthday() {
		return birthday;
	}

	public List<BizDTO> getBizDTOs() {
		return bizDTOs;
	}

	public int getCurpage() {
		return curpage;
	}
	public String getDefgrade() {
		return defgrade;
	}
	public String getDeformity() {
		return deformity;
	}
	public String getFamilyno() {
		return familyno;
	}
	public String getHealth() {
		return health;
	}
	public String getLinkmode() {
		return linkmode;
	}

	public String getMasterName() {
		return masterName;
	}

	public String getMemberId() {
		return memberId;
	}

	public String getMembername() {
		return membername;
	}

	public String getOperational() {
		return operational;
	}

	public int getPageSize() {
		return pageSize;
	}

	public String getPaperid() {
		return paperid;
	}

	public String getRelmaster() {
		return relmaster;
	}

	public String getRpraddress() {
		return rpraddress;
	}

	public String getRprkind() {
		return rprkind;
	}

	public String getRprtype() {
		return rprtype;
	}

	public String getSex() {
		return sex;
	}

	public String getSickenname() {
		return sickenname;
	}

	public String getSickentype() {
		return sickentype;
	}

	public String getSsn() {
		return ssn;
	}

	public String getTerm() {
		return term;
	}

	public String getUrl() {
		return url;
	}

	public String getValue() {
		return value;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setAssistType(String assistType) {
		this.assistType = assistType;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public void setBizDTOs(List<BizDTO> bizDTOs) {
		this.bizDTOs = bizDTOs;
	}

	public void setCurpage(int curpage) {
		this.curpage = curpage;
	}

	public void setDefgrade(String defgrade) {
		this.defgrade = defgrade;
	}

	public void setDeformity(String deformity) {
		this.deformity = deformity;
	}

	public void setFamilyno(String familyno) {
		this.familyno = familyno;
	}

	public void setHealth(String health) {
		this.health = health;
	}

	public void setLinkmode(String linkmode) {
		this.linkmode = linkmode;
	}

	public void setMasterName(String masterName) {
		this.masterName = masterName;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public void setMembername(String membername) {
		this.membername = membername;
	}

	public void setOperational(String operational) {
		this.operational = operational;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setPaperid(String paperid) {
		this.paperid = paperid;
	}

	public void setRelmaster(String relmaster) {
		this.relmaster = relmaster;
	}

	public void setRpraddress(String rpraddress) {
		this.rpraddress = rpraddress;
	}

	public void setRprkind(String rprkind) {
		this.rprkind = rprkind;
	}

	public void setRprtype(String rprtype) {
		this.rprtype = rprtype;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public void setSickenname(String sickenname) {
		this.sickenname = sickenname;
	}

	public void setSickentype(String sickentype) {
		this.sickentype = sickentype;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
