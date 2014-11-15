package com.medical.service;

import org.dom4j.Document;
import org.jfree.chart.JFreeChart;

public interface JfreechartService {

	/**
	 * 生成条形图
	 * 
	 * @return
	 */
	public JFreeChart createBar3D(Document doc, String title, String x, String y);

	/**
	 * 生成饼图
	 * 
	 * @return
	 */
	public JFreeChart createPie3D(Document doc, String title);
}
