package com.medical.dto;

import java.util.Date;

public class ChronicStatusDTO {
	private Long chronicstatusId;
	private String familyId;
	private String name;
	private String ssn;
	private Integer entity;
	private String entityname;
	private String state;
	private Date apptime;
	private String flag;
	private String memberId;
	private String memberType;
	
	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberType() {
		return memberType;
	}

	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}

	public Long getChronicstatusId() {
		return chronicstatusId;
	}

	public void setChronicstatusId(Long chronicstatusId) {
		this.chronicstatusId = chronicstatusId;
	}

	public String getFamilyId() {
		return familyId;
	}

	public void setFamilyId(String familyId) {
		this.familyId = familyId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public Integer getEntity() {
		return entity;
	}

	public void setEntity(Integer entity) {
		this.entity = entity;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getApptime() {
		return apptime;
	}

	public void setApptime(Date apptime) {
		this.apptime = apptime;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public void setEntityname(String entityname) {
		this.entityname = entityname;
	}

	public String getEntityname() {
		return entityname;
	}
}
