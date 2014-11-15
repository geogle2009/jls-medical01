<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link rel="stylesheet" href="../css/table-style.css" type="text/css"></link>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>补录查询页面</title>
</head>
<body>
<s:form theme="simple" action="queryBizrecord" method="post"
	cssStyle="font-size:12px">
	选择地区：<s:select
		name="oid" list="orgs" listKey="orgid" listValue="orgname"></s:select>
	查询条件：
	<s:select value="term" name="term"
		list="#{'':'全部','SSN':'社会保险号','FAMILYNO':'家庭编号','MEMBERNAME':'姓名','PAPERID':'身份证号'}"
		label="查询条件：" listKey="key" listValue="value">
	</s:select>
	操作符：
	<s:select value="operational" name="operational"
		list="#{'=':'等于','like':'相似于'}" label="操作符：" listKey="key"
		listValue="value">
	</s:select>
	查询值：
	<s:textfield name="value"></s:textfield>
	<s:submit value="查询"></s:submit>
</s:form>
<table align="center" width="99%" class="t1" border="0" cellpadding="0"
	cellspacing="0">
	<tr>
		<th>业务类别</th>
		<th>家庭编号</th>
		<th>姓名</th>
		<th>社会保障编号</th>
		<th>身份证号</th>
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
			<td><s:property value="familyId" /></td>
			<td><s:property value="name" /></td>
			<td><s:property value="ssn" /></td>
			<td><s:property value="idcard" /></td>
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
			<td>
			<button class="btn" onclick="view(<s:property value="bizId" />,'<s:property value="bizName" />')">查看</button>
			<button class="btn"
			onclick="window.open('cancelBiz.action?assistype=11-10&bizId=<s:property value="bizId" />&type=bizrecord&counttype=1&ssn=<s:property value="ssn" />','','height=500, width=700, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no')" title="作废">作废</button>
			</td>
		</tr>
	</s:iterator>
</table>
<div align="center" style="font-size: 12px"><s:property
	value="toolsmenu" escape="false" /></div>
<script type="text/javascript">
	function view(bizid,bizname){
		window.location.replace('viewBizrecord.action?bizId='+bizid);
	}
</script>
</body>
</html>