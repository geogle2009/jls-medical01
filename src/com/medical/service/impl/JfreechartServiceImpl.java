package com.medical.service.impl;

import java.awt.Font;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import com.medical.dao.ExecutSQLDAO;
import com.medical.service.JfreechartService;

public class JfreechartServiceImpl implements JfreechartService {
	private ExecutSQLDAO executSQLDAO;
	private JFreeChart chart;

	@SuppressWarnings("unchecked")
	public JFreeChart createBar3D(Document doc, String title, String x, String y) {

		StandardChartTheme standardChartTheme = new StandardChartTheme("name");
		standardChartTheme.setLargeFont(new Font("宋体", Font.BOLD, 12));// 可以改变轴向的字体
		standardChartTheme.setRegularFont(new Font("宋体", Font.BOLD, 12));// 可以改变图例的字体
		standardChartTheme.setExtraLargeFont(new Font("宋体", Font.BOLD, 12));// 可以改变图标的标题字体
		ChartFactory.setChartTheme(standardChartTheme);// 设置主题

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		Element root = doc.getRootElement();
		Iterator colit = root.elementIterator("col");
		while (colit.hasNext()) {
			Element ele = (Element) colit.next();
			List cols = ele.elements();
			Element v1 = (Element) cols.get(0);
			Element v2 = (Element) cols.get(1);
			Element v3 = (Element) cols.get(2);
			dataset.addValue(new BigDecimal(v1.getText()).doubleValue(), v2
					.getText(), v3.getText());
		}
		chart = ChartFactory.createBarChart3D(title, // 图表标题
				"", // 目录轴的显示标签
				"", // 数值轴的显示标签
				dataset, // 数据集
				PlotOrientation.VERTICAL, // 图表方向：水平、垂直
				true, // 是否显示图例(对于简单的柱状图必须是false)
				true, // 是否生成工具
				false // 是否生成URL链接
				);

		CategoryPlot plot = (CategoryPlot) chart.getPlot();

		CategoryAxis categoryAxis = plot.getDomainAxis(); // 取得横轴
		categoryAxis.setLabelFont(new Font("宋体", Font.ITALIC, 22));
		categoryAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);

		NumberAxis numberAxis = (NumberAxis) plot.getRangeAxis();// 取得纵轴
		numberAxis.setLabelFont(new Font("宋体", Font.ITALIC, 22));

		return chart;
	}

	@SuppressWarnings("unchecked")
	public JFreeChart createPie3D(Document doc, String title) {

		DefaultPieDataset dataset = new DefaultPieDataset();
		Element root = doc.getRootElement();
		Iterator colit = root.elementIterator("col");
		while (colit.hasNext()) {
			Element ele = (Element) colit.next();
			List cols = ele.elements();
			Element orgname = (Element) cols.get(0);
			Element value = (Element) cols.get(1);
			dataset.setValue(orgname.getText(), new Double(value.getText()));
		}
		chart = ChartFactory.createPieChart3D(title, // 图表标题
				dataset, // 数据
				true, // 是否显示图例
				true, // 是否显示工具提示
				false // 是否生成URL
				);
		// 重新设置图标标题，改变字体
		chart.setTitle(new TextTitle(title, new Font("宋体", Font.ITALIC, 14)));
		// 取得统计图标的第一个图例
		LegendTitle legend = chart.getLegend(0);
		// 修改图例的字体
		legend.setItemFont(new Font("宋体", Font.BOLD, 12));
		// 获得饼图的Plot对象

		PiePlot plot = (PiePlot) chart.getPlot();
		// 设置饼图各部分的标签字体
		plot.setLabelFont(new Font("宋体", Font.BOLD, 12));
		// 设定背景透明度（0-1.0之间）
		plot.setBackgroundAlpha(0.9f);
		// 设定前景透明度（0-1.0之间）
		plot.setForegroundAlpha(0.50f);
		// 显示百分比 "{0}: ({2})"
		plot.setLabelGenerator(new StandardPieSectionLabelGenerator(
				"{0}: ({1}, {2})"));

		return chart;
	}

	public ExecutSQLDAO getExecutSQLDAO() {
		return executSQLDAO;
	}

	public void setExecutSQLDAO(ExecutSQLDAO executSQLDAO) {
		this.executSQLDAO = executSQLDAO;
	}
}
