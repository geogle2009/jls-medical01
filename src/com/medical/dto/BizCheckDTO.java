package com.medical.dto;

import java.math.BigDecimal;
import java.util.Date;

public class BizCheckDTO {
	private Date begintime;// 入院时间
	private BigDecimal bizcheckId;// 审核单ID

	private Integer bizId;// 业务ID
	private String familyno; // 家庭编号
	private String hospital;// 医院名称
	private String inDeptName; // 入院科室
	private String membername;// 姓名
	private String paperid; // 身份证号
	private String sickname;// 疾病诊断
	private String ssn; // 社会保险号
	private BigDecimal smsState;// 短信发送标记
	private BigDecimal checkState;// 审核状态
	private BigDecimal estimate;// 预计费用
	private String checkorg;//应审核机构
	// 社区审批
	private BigDecimal checked1;
	private String detail1;
	private Date checktime1;
	private String operator1;
	private Date opttime1;
	// 街道审批
	private BigDecimal checked2;
	private String detail2;
	private Date checktime2;
	private String operator2;
	private Date opttime2;
	// 区县审批
	private BigDecimal checked3;
	private String detail3;
	private Date checktime3;
	private String operator3;
	private Date opttime3;
	// 低保中心审批
	private BigDecimal checked4;
	private String detail4;
	private Date checktime4;
	private String operator4;
	private Date opttime4;

	// 当前审批单
	private int level;// 4：市级、6：区县、8：街道、10：社区
	private BigDecimal checked;
	private String detail;
	private Date checktime;
	private String operator;
	private Date opttime;

	private int cur_page;

	private String jwhere;

	public Date getBegintime() {
		return begintime;
	}

	public BigDecimal getBizcheckId() {
		return bizcheckId;
	}

	public Integer getBizId() {
		return bizId;
	}

	public BigDecimal getChecked() {
		return checked;
	}

	public BigDecimal getChecked1() {
		return checked1;
	}

	public BigDecimal getChecked2() {
		return checked2;
	}

	public BigDecimal getChecked3() {
		return checked3;
	}

	public BigDecimal getChecked4() {
		return checked4;
	}

	public BigDecimal getCheckState() {
		return checkState;
	}

	public Date getChecktime() {
		return checktime;
	}

	public Date getChecktime1() {
		return checktime1;
	}

	public Date getChecktime2() {
		return checktime2;
	}

	public Date getChecktime3() {
		return checktime3;
	}

	public Date getChecktime4() {
		return checktime4;
	}

	public int getCur_page() {
		return cur_page;
	}

	public String getDetail() {
		return detail;
	}

	public String getDetail1() {
		return detail1;
	}

	public String getDetail2() {
		return detail2;
	}

	public String getDetail3() {
		return detail3;
	}

	public String getDetail4() {
		return detail4;
	}

	public String getFamilyno() {
		return familyno;
	}

	public String getHospital() {
		return hospital;
	}

	public String getInDeptName() {
		return inDeptName;
	}

	public String getJwhere() {
		return jwhere;
	}

	public String getMembername() {
		return membername;
	}

	public String getOperator() {
		return operator;
	}

	public String getOperator1() {
		return operator1;
	}

	public String getOperator2() {
		return operator2;
	}

	public String getOperator3() {
		return operator3;
	}

	public String getOperator4() {
		return operator4;
	}

	public Date getOpttime() {
		return opttime;
	}

	public Date getOpttime1() {
		return opttime1;
	}

	public Date getOpttime2() {
		return opttime2;
	}

	public Date getOpttime3() {
		return opttime3;
	}

	public Date getOpttime4() {
		return opttime4;
	}

	public String getPaperid() {
		return paperid;
	}

	public String getSickname() {
		return sickname;
	}

	public BigDecimal getSmsState() {
		return smsState;
	}

	public String getSsn() {
		return ssn;
	}

	public void setBegintime(Date begintime) {
		this.begintime = begintime;
	}

	public void setBizcheckId(BigDecimal bizcheckId) {
		this.bizcheckId = bizcheckId;
	}

	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}

	public void setChecked(BigDecimal checked) {
		this.checked = checked;
	}

	public void setChecked1(BigDecimal checked1) {
		this.checked1 = checked1;
	}

	public void setChecked2(BigDecimal checked2) {
		this.checked2 = checked2;
	}

	public void setChecked3(BigDecimal checked3) {
		this.checked3 = checked3;
	}

	public void setChecked4(BigDecimal checked4) {
		this.checked4 = checked4;
	}

	public void setCheckState(BigDecimal checkState) {
		this.checkState = checkState;
	}

	public void setChecktime(Date checktime) {
		this.checktime = checktime;
	}

	public void setChecktime1(Date checktime1) {
		this.checktime1 = checktime1;
	}

	public void setChecktime2(Date checktime2) {
		this.checktime2 = checktime2;
	}

	public void setChecktime3(Date checktime3) {
		this.checktime3 = checktime3;
	}

	public void setChecktime4(Date checktime4) {
		this.checktime4 = checktime4;
	}

	public void setCur_page(int cur_page) {
		this.cur_page = cur_page;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public void setDetail1(String detail1) {
		this.detail1 = detail1;
	}

	public void setDetail2(String detail2) {
		this.detail2 = detail2;
	}

	public void setDetail3(String detail3) {
		this.detail3 = detail3;
	}

	public void setDetail4(String detail4) {
		this.detail4 = detail4;
	}

	public void setFamilyno(String familyno) {
		this.familyno = familyno;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	public void setInDeptName(String inDeptName) {
		this.inDeptName = inDeptName;
	}

	public void setJwhere(String jwhere) {
		this.jwhere = jwhere;
	}

	public void setMembername(String membername) {
		this.membername = membername;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public void setOperator1(String operator1) {
		this.operator1 = operator1;
	}

	public void setOperator2(String operator2) {
		this.operator2 = operator2;
	}

	public void setOperator3(String operator3) {
		this.operator3 = operator3;
	}

	public void setOperator4(String operator4) {
		this.operator4 = operator4;
	}

	public void setOpttime(Date opttime) {
		this.opttime = opttime;
	}

	public void setOpttime1(Date opttime1) {
		this.opttime1 = opttime1;
	}

	public void setOpttime2(Date opttime2) {
		this.opttime2 = opttime2;
	}

	public void setOpttime3(Date opttime3) {
		this.opttime3 = opttime3;
	}

	public void setOpttime4(Date opttime4) {
		this.opttime4 = opttime4;
	}

	public void setPaperid(String paperid) {
		this.paperid = paperid;
	}

	public void setSickname(String sickname) {
		this.sickname = sickname;
	}

	public void setSmsState(BigDecimal smsState) {
		this.smsState = smsState;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getLevel() {
		return level;
	}

	public void setEstimate(BigDecimal estimate) {
		this.estimate = estimate;
	}

	public BigDecimal getEstimate() {
		return estimate;
	}

	public void setCheckorg(String checkorg) {
		this.checkorg = checkorg;
	}

	public String getCheckorg() {
		return checkorg;
	}
}
