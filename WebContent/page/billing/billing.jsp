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
		$("#begin").datepicker({changeYear: true ,changeMonth: true}); 
		$("#end").datepicker({changeYear: true ,changeMonth: true});
	});
</script>
</head>
<%
	String type = request.getParameter("type");
%>
<body>
<s:form action="/page/business/printbill" method="post" theme="simple"
	target="_blank">
&nbsp;&nbsp;开始时间：<input type="text" readonly="readonly" id="begin"
		name="printBillDTO.begin"
		value="<s:date name="printBillDTO.begin" format="yyyy-MM-dd"/>" /> &nbsp;&nbsp;&nbsp;&nbsp;结束时间：<input
		type="text" readonly="readonly" id="end" name="printBillDTO.end"
		value="<s:date name="printBillDTO.end" format="yyyy-MM-dd"/>" />
			 &nbsp;&nbsp;&nbsp;&nbsp;<s:submit value="统计"></s:submit>
</s:form>
</body>
</html>