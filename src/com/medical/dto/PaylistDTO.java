package com.medical.dto;

public class PaylistDTO {
	private String fundName;// 支付名称

	private String realPay;// 支付金额

	public String getFundName() {
		return fundName;
	}

	public String getRealPay() {
		return realPay;
	}

	public void setFundName(String fundName) {
		this.fundName = fundName;
	}

	public void setRealPay(String realPay) {
		this.realPay = realPay;
	}

}
