<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<sj:head jqueryui="true" />
<link rel="stylesheet" href="/medical/page/css/table-style.css"
	type="text/css"></link>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>医疗救助对象查询</title>
<script type="text/javascript"
	src="<%=path%>/struts/js/base/jquery.ui.datepicker.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/struts/i18n/jquery.ui.datepicker-zh-CN.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#opertime11").datepicker( {
			showMonthAfterYear : true,
			changeMonth : false,
			changeYear : true,
			dateFormat : 'yy-mm-dd',
			duration : 'fast',
			showAnim : 'slideDown',
			showButtonPanel : true,
			showOtherMonths : false
		});
		$("#opertime22").datepicker( {
			showMonthAfterYear : true,
			changeMonth : false,
			changeYear : true,
			dateFormat : 'yy-mm-dd',
			duration : 'fast',
			showAnim : 'slideDown',
			showButtonPanel : true,
			showOtherMonths : false
		});
	});
</script>
<body>
<table  width="100%" border="0" cellpadding="0"
		cellspacing="0">
<tr><td>
<s:form action="page/search/searchmembers.action?cur_page="
	method="post" theme="simple">
	<table align="left" width="90%" border="0" cellpadding="0"
		cellspacing="0">
		<tr>
			<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;选择机构：<s:select name="oid" list="orgs" listKey="orgid"
				listValue="orgname"></s:select>&nbsp;&nbsp;&nbsp;&nbsp;选择医院：<s:select
				name="hid" list="hs" listKey="hid" listValue="hname" headerKey=""
				headerValue="全部"></s:select>&nbsp;&nbsp;&nbsp;&nbsp; 救助时间（按时间段）： <input
				type="text" readonly="readonly" id="opertime11" name="opertime1"
				value='<s:date name="opertime1" format="yyyy-MM-dd"/>' />&nbsp;&nbsp;至&nbsp;&nbsp;
			<input type="text" readonly="readonly" id="opertime22"
				name="opertime2"
				value='<s:date name="opertime2" format="yyyy-MM-dd"/>' /></td>
		</tr>
		<tr>
			<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;查询条件： <s:select value="term" name="term"
				list="#{'':'全部','SSN':'社会保险号','FAMILYNO':'家庭编号','MEMBERNAME':'姓名','PAPERID':'身份证号'}"
				label="查询条件：" listKey="key" listValue="value">
			</s:select> &nbsp;&nbsp;操作符： <s:select value="operational" name="operational"
				list="#{'=':'等于','like':'相似于'}" label="操作符：" listKey="key"
				listValue="value"></s:select>&nbsp;&nbsp;查询值： <s:textfield
				name="value"></s:textfield>&nbsp;&nbsp;来源：<s:select value="ds"
				name="ds" list="#{'1':'城市','2':'农村'}" listKey="key"
				listValue="value" headerKey=""
				headerValue="全部"></s:select> &nbsp;&nbsp; <s:submit value="查询"></s:submit>&nbsp;&nbsp;
			<button onclick="window.open('../downloadExcel.action?type=1')">导出excel</button>
			</td>
		</tr>
	</table>
</s:form>
</td></tr>
<tr><td>
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
			<td><s:property value="membername" /></td>
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
</table></td></tr>
</table>
<div align="center" style="font-size: 12px"><s:property
	value="totalstr" />&nbsp;&nbsp;&nbsp;&nbsp;<s:property
	value="toolsmenu" escape="false" /></div>
<script type="text/javascript">
	function openHealthCard(memberId) {
		var url = "../baseinfo/displayCard.action?memberId=" + memberId;
		window.showModalDialog(url,'',"dialogHeight: 650px; dialogWidth: 800px; center: Yes; resizable: Yes; status: No;");
	}
</script>
</body>
</html>