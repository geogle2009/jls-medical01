<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="../css/table-style.css" type="text/css"></link>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1" width="99%" class="t1" cellpadding="0" cellspacing="0">
	<caption>日常救助审批记录</caption>
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
			<td><s:date name="regDate" format="yyyy-MM-dd" /></td>
			<td><s:date name="beginDate" format="yyyy-MM-dd" /></td>
			<td><s:date name="endDate" format="yyyy-MM-dd" /></td>
			<td><s:property value="personpay" default="0" />元</td>
			<td><s:property value="insurancepay" default="0" />元</td>
			<td><s:property value="assistpay" default="0" />元</td>
			<td><s:property value="allmoney" default="0" />元</td>
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
			</s:url> <s:url id="view" action="dailymedicalapproveview">
				<s:param name="bizId">
					<s:property value="bizId" />
				</s:param>
			</s:url> <s:a href="%{cancel}">作废</s:a>&nbsp;&nbsp;<s:a href="%{view}">查看</s:a>
			</td>
		</tr>
	</s:iterator>
</table>
<s:url id="approveDailyMedicalInit" action="approveDailyMedicalInit">
	<s:param name="ssn">
		<s:property value="personDTO.ssn" />
	</s:param>
</s:url>
<s:a href="%{approveDailyMedicalInit}">返回继续录入>></s:a>
</body>
</html>