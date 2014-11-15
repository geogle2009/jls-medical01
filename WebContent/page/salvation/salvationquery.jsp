<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link rel="stylesheet" href="../css/table-style.css" type="text/css"></link>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>救助对象查询</title>
</head>
<body>
<%
		String type = request.getParameter("type");
			if (null == type) {
				type = (String) request.getAttribute("type");
			}
	%>
<s:form theme="simple" action="salvationQuery" method="post"
	cssStyle="font-size:12px">
	选择医院：<s:select
		name="hid" list="hs" listKey="hid" listValue="hname" headerKey=""
		headerValue="全部"></s:select>
	查询条件：
	<s:select value="term" name="term"
		list="#{'':'全部','SSN':'社会保险号','FAMILYNO':'家庭编号','MEMBERNAME':'姓名'}"
		label="查询条件：" listKey="key" listValue="value">
	</s:select>
	操作符：
	<s:select value="operational" name="operational"
		list="#{'=':'等于','like':'相似于'}" label="操作符：" listKey="key"
		listValue="value">
	</s:select>
	查询值：
	<s:textfield name="value"></s:textfield>
	<%
	if("2".equals(type)){%>
	入院情况：<s:select name="inh" list="#{'1':'正在住院','2':'已出院'}" headerKey=""
		headerValue="全部" ></s:select><%  		
	}
	%>
	<s:submit value="查询"></s:submit>&nbsp;&nbsp;
	 <button
		onclick="window.open('../downloadExcel.action?type=5');">导出excel</button>
	<input type="hidden" name="type" value="<%=type%>" />
</s:form>
<table align="center" width="90%" class="t1" border="0" cellpadding="0"
	cellspacing="0">
	<tr>
		<th>家庭编号</th>
		<th>姓名</th>
		<th>社会保险号</th>
		<th>医院名称</th>
		<th>入院科室</th>
		<th>入院时间</th>
		<th>疾病诊断</th>
		<th>操作</th>
	</tr>
	<s:iterator value="sallist">
		<tr>
			<td><s:property value="familyno" /></td>
			<td><s:property value="bearpayment" /></td>
			<td><s:property value="ssn" /></td>
			<td><s:property value="hospital" /></td>
			<td><s:property value="bigpayment" /></td>
			<td><s:date name="begintime" format="yyyy-MM-dd" /></td>
			<td><s:property value="sickname" /></td>
			<td><s:url id="view" action="viewBiz">
				<s:param name="bizId">
					<s:property value="bizId" />
				</s:param>
			</s:url> <s:a href="%{view}">查看</s:a></td>
		</tr>
	</s:iterator>
</table>
<div align="center" style="font-size: 12px"><s:property
	value="toolsmenu" escape="false" /></div>
</body>
</html>