package com.medical.dto;

import java.math.BigDecimal;

public class ChronicBillStatDTO {
	private BigDecimal zrc;// 总救助人次
	private BigDecimal ndzrc;// 本年救助人次
	private BigDecimal zxf;// 总消费金额
	private BigDecimal ndzxf;// 年度总消费金额
	private BigDecimal dqrs;// 享受救助人数
	private BigDecimal zbal; //所有人账户没有清零之前的余额总和
	private BigDecimal znbal;
	private BigDecimal ndzsr;//年度总收入
	private BigDecimal zsr;//总收入

	
	public BigDecimal getZnbal() {
		return znbal;
	}

	public void setZnbal(BigDecimal znbal) {
		this.znbal = znbal;
	}

	public BigDecimal getNdzsr() {
		return ndzsr;
	}

	public void setNdzsr(BigDecimal ndzsr) {
		this.ndzsr = ndzsr;
	}

	public BigDecimal getZsr() {
		return zsr;
	}

	public void setZsr(BigDecimal zsr) {
		this.zsr = zsr;
	}

	public BigDecimal getZrc() {
		return zrc;
	}

	public void setZrc(BigDecimal zrc) {
		this.zrc = zrc;
	}

	public BigDecimal getNdzrc() {
		return ndzrc;
	}

	public void setNdzrc(BigDecimal ndzrc) {
		this.ndzrc = ndzrc;
	}

	public BigDecimal getZxf() {
		return zxf;
	}

	public void setZxf(BigDecimal zxf) {
		this.zxf = zxf;
	}

	public BigDecimal getNdzxf() {
		return ndzxf;
	}

	public void setNdzxf(BigDecimal ndzxf) {
		this.ndzxf = ndzxf;
	}

	public BigDecimal getDqrs() {
		return dqrs;
	}

	public void setDqrs(BigDecimal dqrs) {
		this.dqrs = dqrs;
	}

	public void setZbal(BigDecimal zbal) {
		this.zbal = zbal;
	}

	public BigDecimal getZbal() {
		return zbal;
	}

}
