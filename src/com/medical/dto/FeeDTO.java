package com.medical.dto;

import java.math.BigDecimal;
import java.util.Date;

public class FeeDTO {
	private String stat;// 分类

	private String zfy; // 费用

	private String hname;
	private String itemName;
	private BigDecimal price;
	private BigDecimal dosage;
	private BigDecimal money;
	private Date operTime;
	
	private String familyno;
	private String paperid;
	private String membername;
	

	public String getFamilyno() {
		return familyno;
	}

	public void setFamilyno(String familyno) {
		this.familyno = familyno;
	}

	public String getPaperid() {
		return paperid;
	}

	public void setPaperid(String paperid) {
		this.paperid = paperid;
	}

	public String getMembername() {
		return membername;
	}

	public void setMembername(String membername) {
		this.membername = membername;
	}

	public String getStat() {
		return stat;
	}

	public String getZfy() {
		return zfy;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public void setZfy(String zfy) {
		this.zfy = zfy;
	}

	public String getHname() {
		return hname;
	}

	public void setHname(String hname) {
		this.hname = hname;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getDosage() {
		return dosage;
	}

	public void setDosage(BigDecimal dosage) {
		this.dosage = dosage;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public void setOperTime(Date operTime) {
		this.operTime = operTime;
	}

	public Date getOperTime() {
		return operTime;
	}

}
