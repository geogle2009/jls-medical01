<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>" target="_self">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<META HTTP-EQUIV='pragma' CONTENT='no-cache'>
<META HTTP-EQUIV='Cache-Control' CONTENT='no-cache, must-revalidate'>
<META HTTP-EQUIV='expires' CONTENT='0'>
<link rel="stylesheet" href="page/css/table-style.css" type="text/css"></link>
<title>补录</title>
<script type="text/javascript">
	function checkform() {
		var oNum = document.getElementById('income').value;
		var password = document.getElementById('password').value;
		var flag = isNumber(oNum);
		if (!flag) {
			alert('请输入数字');
		}
		if(password==null ||password==""){
			alert('请输入权限密码');
			flag=false;
		}
		return flag;
	}
	function isNumber(oNum) {
		if (!oNum)
			return false;
		var strP = /^[\+-]?[1-9]?\d*(\.\d{1,2})?$/;
		if (!strP.test(oNum))
			return false;
		try {
			if (parseFloat(oNum) != oNum)
				return false;
		} catch (ex) {
			return false;
		}
		return true;
	}
</script>
</head>
<body>
<s:form action="chronichandle" theme="simple"
	onsubmit="return checkform();">
	<s:hidden name="chronicBillDTO.ssn"></s:hidden>
	<s:hidden name="chronicBillDTO.balance"></s:hidden>
	<s:hidden name="chronicStatusDTO.chronicstatusId"></s:hidden>
	<s:hidden name="chronicBillDTO.memberId"></s:hidden>
	<s:hidden name="chronicBillDTO.memberType"></s:hidden>
	<table align="center" width="90%" class="t1" border="0" cellpadding="0"
		cellspacing="0">
		<tr>
			<th>救助信息</th>
			<td><s:property value="result"/>&nbsp;&nbsp;&nbsp;&nbsp;当前余额：<s:property value="chronicBillDTO.balance" />元</td>
		</tr>
		<tr>
			<th>选择操作：</th>
			<td><s:radio list="#{'0':'清零','1':'充值','2':'清零并充值'}" name="opt"
				listKey="key" listValue="value"></s:radio></td>
		</tr>
		<tr>
			<th>充值金额：</th>
			<td><s:textfield id="income" name="chronicBillDTO.income"
				value="0"></s:textfield>元</td>
		</tr>
		<tr>
			<th>备注：</th>
			<td><s:textfield name="chronicBillDTO.subject"></s:textfield></td>
		</tr>
		<tr>
			<th><font color='red'>*</font>权限密码：</th>
			<td><s:textfield id="password" name="chronicBillEmployDTO.password"></s:textfield></td>
		</tr>
		<tr>
			<td colspan="2"><s:submit value="保存"></s:submit></td>
		</tr>
	</table>
</s:form>
<table align="center" width="90%" class="t1" border="0" cellpadding="0"
	cellspacing="0">
	<tr>
		<th>家庭编号</th>
		<th>姓名</th>
		<th>社会保险号</th>
		<th>科目</th>
		<th>收入（元）</th>
		<th>支出（元）</th>
		<th>余额（元）</th>
		<th>时间</th>
	</tr>
	<s:iterator value="cbs">
		<tr>
			<td><s:property value="familyId" /></td>
			<td><s:property value="name" /></td>
			<td><s:property value="ssn" /></td>
			<td><s:property value="subject" /></td>
			<td><s:property value="income" /></td>
			<td><s:property value="payout" /></td>
			<td><s:property value="balance" /></td>
			<td><s:date name="opttime" format="yyyy-MM-dd" /></td>
		</tr>
	</s:iterator>
</table>
</body>
</html>