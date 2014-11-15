package com.medical.dto;

import java.util.Date;

public class DrugDTO {
	/*
	 * 项目药品类型名称 Medi_Item_Name 中心药品项目名称 Item_Name 剂型 Model 规格 Standard 计量单位 Unit
	 * 单价 Price 用量 Dosage 金额 Money 厂商 Factory 费用发生日期 Fee_Date 处方医生姓名 Doctor_Name
	 * 
	 */

	private String mediItemName;

	private String itemName;

	private String model;

	private String standard;

	private String unit;

	private String price;

	private String dosage;

	private String money;

	private String factory;

	private Date feeDate;

	private String doctorName;
	
	private String feelistId;

	public String getDoctorName() {
		return doctorName;
	}

	public String getDosage() {
		return dosage;
	}

	public String getFactory() {
		return factory;
	}

	public Date getFeeDate() {
		return feeDate;
	}

	public String getFeelistId() {
		return feelistId;
	}

	public String getItemName() {
		return itemName;
	}

	public String getMediItemName() {
		return mediItemName;
	}

	public String getModel() {
		return model;
	}

	public String getMoney() {
		return money;
	}

	public String getPrice() {
		return price;
	}

	public String getStandard() {
		return standard;
	}

	public String getUnit() {
		return unit;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public void setDosage(String dosage) {
		this.dosage = dosage;
	}

	public void setFactory(String factory) {
		this.factory = factory;
	}

	public void setFeeDate(Date feeDate) {
		this.feeDate = feeDate;
	}

	public void setFeelistId(String feelistId) {
		this.feelistId = feelistId;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public void setMediItemName(String mediItemName) {
		this.mediItemName = mediItemName;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}


}
