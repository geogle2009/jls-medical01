<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<s:head theme="xhtml" />
<sx:head extraLocales="en-us,nl-nl,de-de" />
<link rel="stylesheet" href="../../css/table-style.css" type="text/css"></link>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>药店对帐查询</title>
<link type="text/css" href="../../js/themes/base/jquery.ui.all.css"
	rel="stylesheet" />
<script type="text/javascript" src="../../js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="../../js/ui/jquery.ui.core.js"></script>
<script type="text/javascript" src="../../js/ui/jquery.ui.widget.js"></script>
<script type="text/javascript" src="../../js/ui/jquery.ui.datepicker.js"></script>
<script type="text/javascript"
	src="../../js/ui/i18n/jquery.ui.datepicker-zh-CN.js"></script>
<script type="text/javascript">
	$(function() {
		$("#opertime1").datepicker({changeYear: true ,changeMonth: true});
		$("#opertime2").datepicker({changeYear: true ,changeMonth: true});
	});
</script>
</head>
<body>
<s:form action="querychronicbilldetail" method="post" theme="simple">选择机构：<s:select
		name="oid" list="orgs" listKey="orgid" listValue="orgname"></s:select>&nbsp;
查询条件：<s:select name="term"
		list="#{'':'全部','name':'姓名','familyno':'家庭编号','paperid':'身份证号'}"
		listKey="key" listValue="value">
	</s:select>&nbsp;
	查询值：<s:textfield name="value"></s:textfield>&nbsp;
	查询类型：<s:select name="subject"
		list="#{'':'全部','1':'发放名单'}"
		listKey="key" listValue="value"></s:select>&nbsp;
	 救助时间： 
		<input type="text" readonly="readonly" id="opertime1" name="opertime1"
		value="<s:date name="opertime1" format="yyyy-MM-dd"/>" />至
		<input type="text" readonly="readonly" id="opertime2" name="opertime2"
		value="<s:date name="opertime2" format="yyyy-MM-dd"/>" />&nbsp;<s:submit
		value="查询"></s:submit>&nbsp;<button
		onclick="window.open('../downloadExcel.action?type=3');">导出excel</button>
</s:form>
<table align="center" width="90%" class="t1" border="0" cellpadding="0"
	cellspacing="0">
	<tr>
		<th>家庭编号</th>
		<th>姓名</th>
		<th>身份证号</th>
		<th>存入</th>
		<th>支出</th>
		<th>余额</th>
		<th>备注</th>
		<th>操作时间</th>
	</tr>
	<s:iterator value="cbs">
		<tr>
			<td><s:property value="familyId" /></td>
			<td><s:property value="name" /></td>
			<td><s:property value="paperid" /></td>
			<td><s:property value="income" /></td>
			<td><s:property value="payout" /></td>
			<td><s:property value="balance" /></td>
			<td><s:property value="subject" /></td>
			<td><s:date name="opttime" format="yyyy-MM-dd:hh:mm:ss" /></td>
		</tr>
	</s:iterator>
</table>
<div align="center">
<s:property value="toolsmenu" escape="false"/>
</div>
</body>
</html>