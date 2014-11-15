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
<title>医疗救助对象查询</title>
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
<s:form action="searchMedicalMembers" method="post" theme="simple">
<input name="cur_page" type="hidden" value=""/>
选择机构：<s:select name="oid" list="orgs" listKey="orgid"
		listValue="orgname"></s:select>&nbsp;&nbsp;&nbsp;&nbsp;选择医院：<s:select
		name="hid" list="hs" listKey="hid" listValue="hname" headerKey=""
		headerValue="全部"></s:select>&nbsp;&nbsp;&nbsp;&nbsp;
类型：<s:select value="biztype" name="biztype" list="biztypes" label="类型："
		listKey="key" listValue="value">
	</s:select>
	&nbsp;&nbsp;
	业务范围：<s:select listKey="key" listValue="value" name="scope" list="#{'':'全部','1':'非新农合','2':'新农合'}"></s:select>
	&nbsp;&nbsp;救助时间： 
		<input type="text" readonly="readonly" id="opertime1" name="opertime1"
		value="<s:date name="opertime1" format="yyyy-MM-dd"/>" />至
		<input type="text" readonly="readonly" id="opertime2" name="opertime2"
		value="<s:date name="opertime2" format="yyyy-MM-dd"/>" />&nbsp;&nbsp;<s:submit
		value="查询"></s:submit>&nbsp;&nbsp;<button
		onclick="window.open('../downloadExcel.action?type=1')">导出excel</button>
</s:form>
<table align="center" width="90%" class="t1" border="0" cellpadding="0"
	cellspacing="0">
	<tr>
		<th>家庭编号</th>
		<th>姓名</th>
		<th>身份证号</th>
		<th>社会保险号</th>
		<th>就诊医院</th>
		<th>确诊患病名称</th>
		<th>救助时间</th>
		<th>总费用</th>
		<th>救助金额</th>
		<th>统筹</th>
	</tr>
	<s:iterator value="members">
		<tr>
			<td><s:property value="familyno" /></td>
			<td><a href="#"
				onclick="openHealthCard('<s:property value='ssn'/>','<s:property value='ssn'/>')"> <s:property
				value="membername" /></a></td>
			<td><s:property value="paperid"></s:property></td>
			<td><s:property value="ssn" /></td>
			<td><s:property value="hospitalName" /></td>
			<td><s:property value="diagnoseName" /></td>
			<td><s:date name="beginDate1" format="yyyy-MM-dd" /></td>
			<td><s:property value="allmoney" /></td>
			<td><s:property value="assistpay" /></td>
			<td><s:property value="insurepay" /></td>
		</tr>
	</s:iterator>
</table>
<div align="center" style="font-size: 12px"><s:property
	value="totalstr" />&nbsp;&nbsp;&nbsp;&nbsp;<s:property
	value="toolsmenu" escape="false" /></div>
<script type="text/javascript">
	function openHealthCard(memberId,ds){
		var url="../baseinfo/displayCard.action?memberId="+memberId+"&ds="+ds;
		window.showModalDialog(url,'',"dialogHeight: 650px; dialogWidth: 800px; center: Yes; resizable: Yes; status: No;");
	}
</script>
</body>
</html>