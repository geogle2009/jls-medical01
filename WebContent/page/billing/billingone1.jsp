<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<s:head theme="xhtml" />
<sx:head extraLocales="en-us,nl-nl,de-de" />
<link rel="stylesheet" href="../css/table-style.css" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>对账单</title>
<link type="text/css" href="../js/themes/base/jquery.ui.all.css"
	rel="stylesheet" />
<script type="text/javascript" src="../js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="../js/ui/jquery.ui.core.js"></script>
<script type="text/javascript" src="../js/ui/jquery.ui.widget.js"></script>
<script type="text/javascript" src="../js/ui/jquery.ui.datepicker.js"></script>
<script type="text/javascript"
	src="../js/ui/i18n/jquery.ui.datepicker-zh-CN.js"></script>
<script type="text/javascript">
	$(function() {
		$("#begin").datepicker( {
			changeYear : true,
			changeMonth : true
		});
		$("#end").datepicker( {
			changeYear : true,
			changeMonth : true
		});
	});
</script>
</head>
<%
	String type = request.getParameter("type");
%>
<body>
<s:form action="/page/search/printbillone1" method="post" theme="simple">
&nbsp;&nbsp;开始时间：<input type="text" readonly="readonly" id="begin"
		name="opertime1"
		value="<s:date name="printBillDTO.begin" format="yyyy-MM-dd"/>" /> &nbsp;&nbsp;&nbsp;&nbsp;结束时间：<input
		type="text" readonly="readonly" id="end" name="opertime2"
		value="<s:date name="printBillDTO.end" format="yyyy-MM-dd"/>" />&nbsp;&nbsp;
		<button onclick="window.open('../downloadExcel.action?type=4')">导出excel</button>
		&nbsp;&nbsp;<s:submit value="统计"></s:submit>
</s:form>
<table align="center" width="90%" class="t1" border="0" cellpadding="0"
	cellspacing="0">
	<tr>
		<th>药店</th>
		<th>救助总金额</th>
		<th>总人次</th>
	</tr>
	<s:iterator value="members">
		<tr>
			<td style="text-align:left" ><s:property value="hospitalName" /></td>
			<td style="text-align:right" ><s:property value="assistpay" /></td>
			<td style="text-align:right" ><s:property value="sumnum" /></td>
		</tr>
	</s:iterator>
</table>
</body>
</html>