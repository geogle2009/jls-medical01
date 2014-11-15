<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<s:head theme="xhtml" />
<sx:head extraLocales="en-us,nl-nl,de-de" />
<link rel="stylesheet" href="../css/table-style.css" type="text/css"></link>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>药店对帐查询</title>
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
		$("#opertime1").datepicker({changeYear: true ,changeMonth: true});
		$("#opertime2").datepicker({changeYear: true ,changeMonth: true});
	});
</script>
</head>
<body>
<s:form action="searchMedicalMembers1" method="post" theme="simple">
	<input name="cur_page" type="hidden" value="" />
	<table align="center" width="90%" class="t1" border="0" cellpadding="0"
	cellspacing="0">
	<tr><td>选择机构：<s:select name="oid" list="orgs" listKey="orgid"
		listValue="orgname"></s:select>&nbsp;&nbsp;
		查询条件：
	<s:select value="term" name="term"
		list="#{'':'全部','FAMILYNO':'家庭编号','MEMBERNAME':'姓名','PAPERID':'身份证号'}"
		label="查询条件：" listKey="key" listValue="value">
	</s:select>
	&nbsp;&nbsp;操作符：
	<s:select value="operational" name="operational"
		list="#{'=':'等于','like':'相似于'}" label="操作符：" listKey="key"
		listValue="value">
	</s:select>&nbsp;&nbsp;
	查询值：
	<s:textfield name="value"></s:textfield></td></tr>
	<tr><td>
	选择药店：<s:select
		name="hid" list="hs" listKey="hid" listValue="hname" headerKey=""
		headerValue="全部"></s:select>
		 救助时间： 
		<input type="text" readonly="readonly" id="opertime1" name="opertime1"
		value="<s:date name="opertime1" format="yyyy-MM-dd"/>" />至
		<input type="text" readonly="readonly" id="opertime2" name="opertime2"
		value="<s:date name="opertime2" format="yyyy-MM-dd"/>" />&nbsp;&nbsp;<s:submit
		value="查询"></s:submit>&nbsp;&nbsp;<button
		onclick="window.open('../downloadExcel.action?type=3');">导出excel</button>
	</td></tr>
	</table>
</s:form>
<table align="center" width="90%" class="t1" border="0" cellpadding="0"
	cellspacing="0">
	<tr>
		<th>家庭编号</th>
		<th>姓名</th>
		<th>身份证号</th>
		<th>购药药店</th>
		<th>救助时间</th>
		<th>救助金额</th>
		<th>操作</th>
	</tr>
	<s:iterator value="members">
		<tr>
			<td><s:property value="familyno" /></td>
			<td><s:property value="membername" /></td>
			<td><s:property value="paperid"></s:property></td>
			<td><s:property value="hospitalName" /></td>
			<td><s:date name="beginDate1" format="yyyy-MM-dd:hh:mm:ss" /></td>
			<td><s:property value="assistpay" /></td>
			<td>
			<a href="viewpayinfo.action?searchDTO.bizId=<s:property value="bizId" />&searchDTO.year=<s:date name="beginDate1" format="yyyy" />" target="_blank">
			查看
			</a>
			</td>
		</tr>
	</s:iterator>
</table>
<div align="center" style="font-size: 12px"><s:property
	value="totalstr" />&nbsp;&nbsp;&nbsp;&nbsp;<s:property
	value="toolsmenu" escape="false" /></div>
</body>
</html>