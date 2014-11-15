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
<title>城市低保住院救助信息录入</title>
</head>
<body>
<table align="center" width="99%" class="t1" border="0" cellpadding="0"
	cellspacing="0">
	<tr>
		<th colspan="4">当前人员信息 (住院)</th>
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
		<td>
			<s:date name="personDTO.birthday" format="yyyy-MM-dd" />
		</td>
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
		<th>业务类别</th>
		<th>就诊医院</th>
		<th>登记时间</th>
		<th>入院时间</th>
		<th>出院时间</th>
		<th>个人支付</th>
		<th>统筹支付</th>
		<th>医疗救助</th>
		<th>总费用</th>
		<th>操作</th>
	</tr>
	<s:iterator value="bizs">
		<tr>
			<td><s:property value="bizName" /></td>
			<td><s:property value="hospitalname" /></td>
			<td>
				<s:date name="regDate" format="yyyy-MM-dd" />
			</td>
			<td>
				<s:date name="beginDate" format="yyyy-MM-dd" />
			</td>
			<td>
				<s:date name="endDate" format="yyyy-MM-dd" />
			</td>
			<td>
				<s:property value="personpay" default="0" />元
			</td>
			<td>
				<s:property value="insurancepay" default="0" />元
			</td>
			<td>
				<s:property value="assistpay" default="0" />元
			</td>
			<td>
				<s:property value="allmoney" default="0" />元
			</td>
			<td><s:url id="cancel" action="cancelBiz">
				<s:param name="bizId">
					<s:property value="bizId" />
				</s:param>
				<s:param name="ssn">
					<s:property value="ssn" />
				</s:param>
				<s:param name="type">
					hospitalfortown
				</s:param>
				<s:param name="assistype">
				11-10
				</s:param>
			</s:url>
			<s:url id="view" action="viewHospitalfortown">
				<s:param name="bizId">
					<s:property value="bizId" />
				</s:param>
			</s:url>
			<s:url id="count0" action="countBiz">
				<s:param name="bizId">
					<s:property value="bizId" />
				</s:param>
				<s:param name="ssn">
					<s:property value="ssn" />
				</s:param>
				<s:param name="type">
					hospitalfortown
				</s:param>
				<s:param name="assistype">
				11-10
				</s:param>
				<s:param name="counttype">
				0
				</s:param>
			</s:url>
			<s:url id="count1" action="countBiz">
				<s:param name="bizId">
					<s:property value="bizId" />
				</s:param>
				<s:param name="ssn">
					<s:property value="ssn" />
				</s:param>
				<s:param name="type">
					hospitalfortown
				</s:param>
				<s:param name="assistype">
				11-10
				</s:param>
				<s:param name="counttype">
				1
				</s:param>
			</s:url>
			<s:a href="%{cancel}">作废</s:a>&nbsp;&nbsp;<s:a href="%{view}">查看</s:a>&nbsp;&nbsp;
				<button class="btn"
			onclick="window.open('countBiz.action?assistype=11-10&bizId=<s:property value="bizId" />&type=hospitalfortown&counttype=0&ssn=<s:property value="ssn" />','','height=500, width=700, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no')" title="测算保障金">测算</button>
			<s:if test="%{personDTO.organizationId==2202}"><button class="btn"
			onclick="window.open('countBiz.action?assistype=11-10&bizId=<s:property value="bizId" />&type=hospitalfortown&counttype=1&ssn=<s:property value="ssn" />','','height=500, width=700, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no')" title="计算保障金并保存">计算</button></s:if>
			</td>
		</tr>
	</s:iterator>
	<tr>
		<td colspan="10">
		<button class="btn"
			onclick="window.open('saveHospitalfortowninit.action?assistype=<s:property value="personDTO.assisTypeId" />&familyno=<s:property value="personDTO.familyno" />&ssn=<s:property value="personDTO.ssn" />','','height=500, width=700, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no')">新建业务</button>
		&nbsp;&nbsp;<button class="btn" onclick="window.document.location.reload()">刷新数据</button>
		</td>
	</tr>
</table>
<p align="center" style="color: red"><s:property value="result" /></p>
</body>
</html>