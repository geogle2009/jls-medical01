<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link rel="stylesheet" href="../css/table-style.css" type="text/css"></link>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>城市低保住院救助信息录入</title>
</head>
<body>
<table align="center" width="99%" class="t1" border="0" cellpadding="0"
	cellspacing="0">
	<tr>
		<th colspan="4">当前人员信息 (手工)</th>
	</tr>
	<tr>
		<td width="90">姓名</td>
		<td><s:property value="personDTO.membername" /></td>
		<td width="90">性别</td>
		<td><s:property value="personDTO.sex" /></td>
	</tr>
	<tr>
		<td>低保编号</td>
		<td><s:property value="personDTO.familyno" /></td>
		<td>医保卡号</td>
		<td><s:property value="personDTO.ssn" /></td>
	</tr>
	<tr>
		<td>保障类型</td>
		<td><s:property value="personDTO.assistType" /></td>
		<td>身份证号码</td>
		<td><s:property value="personDTO.paperid" /></td>
	</tr>
	<tr>
		<td>出生日期</td>
		<td><s:date name="personDTO.birthday" format="yyyy-MM-dd" /></td>
		<td>户主姓名</td>
		<td><s:property value="personDTO.masterName" /></td>
	</tr>
	<tr>
		<td>家庭住址</td>
		<td colspan="3"><s:property value="personDTO.address" /></td>
	</tr>
	<tr>
		<td>联系方式</td>
		<td colspan="3"><s:property value="personDTO.linkmode" /></td>
	</tr>
</table>
<br>
<table align="center" width="99%" class="t1" border="0" cellpadding="0"
	cellspacing="0">
	<caption style="font-size: 12px">业务信息</caption>
	<tr>
		<th>编号</th>
		<th>发表编号</th>
		<th>就医医院</th>
		<th>患病名称</th>
		<th>费用总额</th>
		<th>自理费用</th>
		<th>自费金额</th>
		<th>是否报销</th>
		<th>报销时间</th>
		<th>操作</th>
	</tr>
	<s:iterator value="manualapproves">
		<tr>
			<td><s:property value="manualno" /></td>
			<td><s:property value="invoice" /></td>
			<td><s:property value="hospitalname" /></td>
			<td><s:property value="sickname" /></td>
			<td><s:property value="hospitalmoney" default="0" />元</td>
			<td><s:property value="selfmoney" default="0" />元</td>
			<td><s:property value="selfall" default="0" />元</td>
			<td>
			<s:if test="assistFlag==1">
				已报
			</s:if><s:else>
			未报
			</s:else>
			</td>
			<td><s:date name="assistTime" format="yyyy-MM-dd"/></td>
			<td><s:url action="viewmanual" id="view">
				<s:param name="manual.manualId">
					<s:property value="manualId" />
				</s:param>
				<s:param name="manual.ssn">
					<s:property value="ssn" />
				</s:param>
			</s:url> <s:url action="cancelmanual" id="cancel">
				<s:param name="manual.manualId">
					<s:property value="manualId" />
				</s:param>
				<s:param name="manual.ssn">
					<s:property value="ssn" />
				</s:param>
			</s:url> <s:url action="printmanual1" id="print">
				<s:param name="manual.manualId">
					<s:property value="manualId" />
				</s:param>
				<s:param name="manual.ssn">
					<s:property value="ssn" />
				</s:param>
			</s:url>
			<s:a href="%{view}" cssStyle="cursor:hand">查看</s:a> 、<a style="cursor:hand" href="#" onclick="window.open('modifymanual.action?manual.manualId=<s:property value="manualId"/>','','height=500, width=700, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no')">修改</a>、<s:a
				href="%{cancel}" cssStyle="cursor:hand">作废</s:a>、<s:a
				href="%{print}" cssStyle="cursor:hand" target="_blank">打印</s:a></td>
		</tr>
	</s:iterator>
	<tr>
		<td colspan="10">
		<button class="btn"
			onclick="window.open('savemanualinit.action?memberId=<s:property value="personDTO.memberId"/>&assistype=<s:property value="personDTO.assisTypeId" />&familyno=<s:property value="personDTO.familyno" />&ssn=<s:property value="personDTO.ssn" />','','height=500, width=700, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no')">新建业务</button>
		&nbsp;&nbsp;
		<button class="btn" onclick="window.document.location.reload()">刷新数据</button>
		</td>
	</tr>
</table>
<p align="center" style="color: red"><s:property value="result" /></p>
</body>
</html>