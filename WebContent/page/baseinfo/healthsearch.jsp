<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>救助对象健康档案</title>
<link href="../css/table-style.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
	function openHealthCard(memberId){
		var url="displayCard.action?memberId="+memberId;
		window.showModalDialog(url,'',"dialogHeight: 650px; dialogWidth: 800px; center: Yes; resizable: Yes; status: No;");
	}
</script>
</head>
<body>
<s:form theme="simple" action="healthSearch.action?cur_page=" method="post"
	cssStyle="font-size:12px"> 查询条件：
  <s:select value="healthDTO.term" name="healthDTO.term"
		list="#{'':'全部','SSN':'社会保险号','FAMILYNO':'家庭编号','MEMBERNAME':'姓名','PAPERID':'身份证号'}"
		label="查询条件：" listKey="key" listValue="value">
	</s:select>
  操作符：
  <s:select value="healthDTO.operational" name="healthDTO.operational"
		list="#{'=':'等于','like':'相似于'}" label="操作符：" listKey="key"
		listValue="value">
	</s:select>
  查询值：
  <s:textfield name="healthDTO.value"></s:textfield>
	<s:submit value="查询"></s:submit>
</s:form>
<table align="center" width="99%" class="t1" border="0" cellpadding="0"
	cellspacing="0">
	<tr>
		<th>家庭编号</th>
		<th>户主姓名</th>
		<th>与户主关系</th>
		<th>姓名</th>
		<th>身份证号</th>
		<th>社会保障号</th>
		<th>户口性质</th>
		<th>居住地址</th>
	</tr>
	<s:iterator value="result">
		<tr>
			<td><a href="#"> <s:property value="familyno" /></a></td>
			<td><s:property value="masterName" /></td>
			<td><s:property value="relmaster" /></td>
			<td><a href="#"
				onclick="openHealthCard(<s:property value='memberId'/>)"> <s:property
				value="membername" /> </a></td>
			<td><s:property value="paperid" /></td>
			<td><s:property value="ssn" /></td>
			<td><s:property value="rprkind" /></td>
			<td><s:property value="address" /></td>
		</tr>
	</s:iterator>
</table>
<div align="center" style="font-size: 12px"><s:property
	value="toolsmenu" escape="false" /></div>
</body>
</html>
