<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sd" uri="/struts-dojo-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<s:head theme="xhtml" />
<sd:head extraLocales="en-us,nl-nl,de-de" />
<link rel="stylesheet" href="../css/table-style.css" type="text/css"></link>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>医疗救助对象统计</title>
</head>
<body>
<s:form action="distributionbyperson" method="post" theme="simple">
选择机构：<s:select name="oid" list="orgs" listKey="orgid"
		listValue="orgname"></s:select>&nbsp;&nbsp;&nbsp;&nbsp;选择医院：<s:select name="hid" list="hs" listKey="hid" listValue="hname" headerKey="" headerValue="全部"></s:select>&nbsp;&nbsp;&nbsp;&nbsp;
救助时间：<sd:datetimepicker name="opertime1" language="zh-en"
		displayFormat="yyyy-MM-dd"></sd:datetimepicker>&nbsp;&nbsp;至&nbsp;&nbsp;<sd:datetimepicker
		language="zh-en" name="opertime2" displayFormat="yyyy-MM-dd"></sd:datetimepicker>
	<br>
查询条件：
	<s:select value="term" name="term"
		list="#{'':'全部','SSN':'社会保险号','FAMILYNO':'家庭编号','MEMBERNAME':'姓名','PAPERID':'身份证号'}"
		label="查询条件：" listKey="key" listValue="value">
	</s:select>
	&nbsp;&nbsp;&nbsp;&nbsp;操作符：
	<s:select value="operational" name="operational"
		list="#{'=':'等于','like':'相似于'}" label="操作符：" listKey="key"
		listValue="value">
	</s:select>&nbsp;&nbsp;&nbsp;&nbsp;
	查询值：
	<s:textfield name="value"></s:textfield>&nbsp;&nbsp;&nbsp;&nbsp; <s:submit
		value="查询"></s:submit>&nbsp;&nbsp;&nbsp;&nbsp;<button onclick="window.open('../downloadExcel.action?type=1')">导出excel</button>
</s:form>
<table align="center" width="90%" class="t1" border="0" cellpadding="0"
	cellspacing="0">
	<tr>
		<th>家庭编号</th>
		<th>姓名</th>
		<th>身份证号</th>
		<th>社会保险号</th>
		<th>性别</th>
		<th>救助次数</th>
		<th>总费用</th>
		<th>医保报销金额</th>
		<th>救助金额</th>
		<th>个人支付金额</th>
	</tr>
	<s:iterator value="members">
		<tr>
			<td><s:property value="familyno" /></td>
			<td>
			<a href="#"
				onclick="openHealthCard(<s:property value='memberId'/>)">
			<s:property value="membername" /></a></td>
			<td><s:property value="paperid"></s:property></td>
			<td><s:property value="ssn" /></td>
			<td><s:property value="url" /></td>
			<td><s:property value="curpage" /></td>
			<td><s:property value="allmoney" /></td>
			<td><s:property value="insurepay" /></td>
			<td><s:property value="assistpay" /></td>
			<td><s:property value="personpay" /></td>
		</tr>
	</s:iterator>
</table>
<div align="center" style="font-size: 12px"><s:property value="totalstr" escape="false"/>&nbsp;&nbsp;&nbsp;&nbsp;<s:property
	value="toolsmenu" escape="false" /></div>
	<script type="text/javascript">
	function openHealthCard(memberId){
		var url="../baseinfo/displayCard.action?memberId="+memberId;
		window.showModalDialog(url,'',"dialogHeight: 650px; dialogWidth: 800px; center: Yes; resizable: Yes; status: No;");
	}
</script>
</body>
</html>