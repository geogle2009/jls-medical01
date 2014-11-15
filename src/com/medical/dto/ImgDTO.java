package com.medical.dto;

import java.math.BigDecimal;

public class ImgDTO {
	private String filename;
	private String efilepath;
	private String sfilepath;

	private Long width;
	private Long height;

	private String xWidth;
	private String xHeight;

	static final int WIDTH = 90;
	static final long HEIGHT = 120;

	@SuppressWarnings("static-access")
	public ImgDTO(Long width, Long height) {
		this.width = width;
		this.height = height;
		BigDecimal x = new BigDecimal(width);
		BigDecimal y = new BigDecimal(height);
		x = new BigDecimal(WIDTH).divide(x, 4, BigDecimal.ROUND_HALF_DOWN);
		y = new BigDecimal(HEIGHT).divide(y, 4, BigDecimal.ROUND_HALF_DOWN);
		if (x.doubleValue() > y.doubleValue()) {
			this.xWidth = new BigDecimal((y.doubleValue() * width)).divide(
					new BigDecimal(1), 0, BigDecimal.ROUND_HALF_DOWN)
					+ "px";
			this.xHeight = this.HEIGHT + "px";
		} else {
			this.xWidth = this.WIDTH + "px";
			this.xHeight = new BigDecimal((x.doubleValue() * height)).divide(
					new BigDecimal(1), 0, BigDecimal.ROUND_HALF_DOWN)
					+ "px";
		}
	}

	public ImgDTO() {
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getEfilepath() {
		return efilepath;
	}

	public void setEfilepath(String efilepath) {
		this.efilepath = efilepath;
	}

	public String getSfilepath() {
		return sfilepath;
	}

	public void setSfilepath(String sfilepath) {
		this.sfilepath = sfilepath;
	}

	public Long getWidth() {
		return width;
	}

	public void setWidth(Long width) {
		this.width = width;
	}

	public Long getHeight() {
		return height;
	}

	public void setHeight(Long height) {
		this.height = height;
	}

	public String getxWidth() {
		return xWidth;
	}

	public void setxWidth(String xWidth) {
	}

	public String getxHeight() {
		return xHeight;
	}

	public void setxHeight(String xHeight) {
		this.xHeight = xHeight;
	}

}
