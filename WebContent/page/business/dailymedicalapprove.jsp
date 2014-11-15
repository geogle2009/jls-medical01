<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="../css/table-style.css" type="text/css"></link>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>日常救助审批</title>
</head>
<body>
<s:form action="approveDailyMedical" theme="simple" method="post">
	<table border="1" width="99%" class="t1" cellpadding="0"
		cellspacing="0">
		<s:hidden name="personDTO.familyno" />
		<s:hidden name="personDTO.membername" />
		<s:hidden name="personDTO.ssn" />
		<s:hidden name="personDTO.paperid" />
		<s:hidden name="personDTO.sex" />
		<s:hidden name="personDTO.birthday" />
		<s:hidden name="personDTO.masterName" />
		<s:hidden name="personDTO.relmaster" />
		<s:hidden name="personDTO.linkmode" />
		<s:hidden name="personDTO.address" />
		<s:hidden name="personDTO.assisTypeId" />
		<s:hidden name="personDTO.memberId" />
		<caption>日常救助金审批表</caption>
		<tr>
			<td>家庭编号</td>
			<td><s:property value="personDTO.familyno" /></td>
			<td>救助对象姓名</td>
			<td><s:property value="personDTO.membername" /></td>
			<td>医保编号</td>
			<td><s:property value="personDTO.ssn" /></td>
			<td width="120" rowspan="6">&nbsp;</td>
		</tr>
		<tr>
			<td>身份证号</td>
			<td><s:property value="personDTO.paperid" /></td>
			<td>性别</td>
			<td><s:property value="personDTO.sex" /></td>
			<td>出生日期</td>
			<td><s:date name="personDTO.birthday" format="yyyy-MM-dd" /></td>
		</tr>
		<tr>
			<td>户主姓名</td>
			<td><s:property value="personDTO.masterName" /></td>
			<td>与户主关系</td>
			<td><s:property value="personDTO.relmaster" /></td>
			<td>联系方式</td>
			<td><s:property value="personDTO.linkmode" /></td>
		</tr>
		<tr>
			<td>家庭详细地址</td>
			<td colspan="5"><s:property value="personDTO.address" /></td>
		</tr>
		<tr>
			<td>医院名称</td>
			<td><s:select name="businessDTO.hospital" listKey="hospitalId"
				listValue="name" list="businessDTO.hospitallist">
			</s:select></td>
			<td>救助对象类别</td>
			<td><s:property value="personDTO.assistType" /></td>
			<td>救助金额</td>
			<td><s:textfield name="businessDTO.z01" value="0"/>元</td>
		</tr>
		<tr>
			<td>业务类型</td>
			<td><s:select name="businessDTO.bizType"
				list="#{'11':'门诊','12':'住院'}" label="查询条件：" listKey="key"
				listValue="value">
			</s:select></td>
			<td>规定救助病种</td>
			<td colspan="3"><s:select name="businessDTO.sickname"
				listKey="value" listValue="value" list="businessDTO.presicks"></s:select></td>
		</tr>
		<tr>
			<td colspan="7"><s:submit value="保存"></s:submit></td>
		</tr>
	</table>
</s:form>
<br>
<table border="1" width="99%" class="t1" cellpadding="0" cellspacing="0">
	<caption>救助审批记录</caption>
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
					dailymedical
				</s:param>
				<s:param name="assistype">
				11-10
				</s:param>
			</s:url>
			<s:url id="view" action="viewBizrecord">
				<s:param name="bizId">
					<s:property value="bizId" />
				</s:param>
			</s:url>
			<s:a href="%{cancel}">作废</s:a>&nbsp;&nbsp;<s:a href="%{view}">查看</s:a>
			</td>
		</tr>
	</s:iterator>
</table>
</body>
</html>