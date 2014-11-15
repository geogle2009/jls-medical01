package com.medical.dto;

import java.math.BigDecimal;
import java.util.Date;

public class EmeCheckDTO {

	private String memberId;
	private String familyno;
	private String membername;
	private String paperid;
	private String sex;
	private Date birthday;
	private String masterName;
	private String relmaster;
	private String linkmode;
	private String address;
	private BigDecimal emecheckId;
	private String ssn;
	private String reliefbecause;
	private String interview;
	private Date interviewtime;
	private String comofstreet;
	private Short resultofstreet;
	private Date streetapptime;
	private String auditorofstreet;
	private String comofareg;
	private Short resultofareg;
	private Date aregapptime;
	private String auditorofareg;
	private String aregmaster;
	private BigDecimal salmoney;
	private String rpraddress;
	private String assistType;

	public String getAddress() {
		return address;
	}

	public Date getAregapptime() {
		return aregapptime;
	}

	public String getAregmaster() {
		return aregmaster;
	}

	public String getAssistType() {
		return assistType;
	}

	public String getAuditorofareg() {
		return auditorofareg;
	}

	public String getAuditorofstreet() {
		return auditorofstreet;
	}

	public Date getBirthday() {
		return birthday;
	}

	public String getComofareg() {
		return comofareg;
	}

	public String getComofstreet() {
		return comofstreet;
	}

	public BigDecimal getEmecheckId() {
		return emecheckId;
	}

	public String getFamilyno() {
		return familyno;
	}

	public String getInterview() {
		return interview;
	}

	public Date getInterviewtime() {
		return interviewtime;
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

	public String getPaperid() {
		return paperid;
	}

	public String getReliefbecause() {
		return reliefbecause;
	}

	public String getRelmaster() {
		return relmaster;
	}

	public Short getResultofareg() {
		return resultofareg;
	}

	public Short getResultofstreet() {
		return resultofstreet;
	}

	public String getRpraddress() {
		return rpraddress;
	}

	public BigDecimal getSalmoney() {
		return salmoney;
	}

	public String getSex() {
		return sex;
	}

	public String getSsn() {
		return ssn;
	}

	public Date getStreetapptime() {
		return streetapptime;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setAregapptime(Date aregapptime) {
		this.aregapptime = aregapptime;
	}

	public void setAregmaster(String aregmaster) {
		this.aregmaster = aregmaster;
	}

	public void setAssistType(String assistType) {
		this.assistType = assistType;
	}

	public void setAuditorofareg(String auditorofareg) {
		this.auditorofareg = auditorofareg;
	}

	public void setAuditorofstreet(String auditorofstreet) {
		this.auditorofstreet = auditorofstreet;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public void setComofareg(String comofareg) {
		this.comofareg = comofareg;
	}

	public void setComofstreet(String comofstreet) {
		this.comofstreet = comofstreet;
	}

	public void setEmecheckId(BigDecimal emecheckId) {
		this.emecheckId = emecheckId;
	}

	public void setFamilyno(String familyno) {
		this.familyno = familyno;
	}

	public void setInterview(String interview) {
		this.interview = interview;
	}

	public void setInterviewtime(Date interviewtime) {
		this.interviewtime = interviewtime;
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

	public void setPaperid(String paperid) {
		this.paperid = paperid;
	}

	public void setReliefbecause(String reliefbecause) {
		this.reliefbecause = reliefbecause;
	}

	public void setRelmaster(String relmaster) {
		this.relmaster = relmaster;
	}

	public void setResultofareg(Short resultofareg) {
		this.resultofareg = resultofareg;
	}

	public void setResultofstreet(Short resultofstreet) {
		this.resultofstreet = resultofstreet;
	}

	public void setRpraddress(String rpraddress) {
		this.rpraddress = rpraddress;
	}

	public void setSalmoney(BigDecimal salmoney) {
		this.salmoney = salmoney;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public void setStreetapptime(Date streetapptime) {
		this.streetapptime = streetapptime;
	}
}
