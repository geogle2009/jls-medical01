<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base target="_self">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../css/table-style.css" type="text/css" />
<title>救助对象信息卡片</title>
</head>
<body>
<table width="100%" border="0">
	<tr>
		<td>
		<table width="100%" class="t2">
			<caption>居民健康档案</caption>
			<tr>
				<th>家庭编号</th>
				<td><s:property value="healthDTO.familyno" /></td>
				<th>户主姓名</th>
				<td><s:property value="healthDTO.masterName" /></td>
			</tr>
			<tr>
				<th>与户主关系</th>
				<td><s:property value="healthDTO.relmaster" /></td>
				<th>姓名</th>
				<td><s:property value="healthDTO.membername" /></td>
			</tr>
			<tr>
				<th>身份证号</th>
				<td><s:property value="healthDTO.paperid" /></td>
				<th>社会保障号</th>
				<td><s:property value="healthDTO.ssn" /></td>
			</tr>
			<tr>
				<th>性别</th>
				<td><s:property value="healthDTO.sex" /></td>
				<th>出生日期</th>
				<td><s:date name="healthDTO.birthday" format="yyyy-MM-dd" /></td>
			</tr>
			<tr>
				<th>户口性质</th>
				<td><s:property value="healthDTO.rprkind" /></td>
				<th>户口类型</th>
				<td><s:property value="healthDTO.rprtype" /></td>
			</tr>
			<tr>
				<th>户口所在地</th>
				<td><s:property value="healthDTO.rpraddress" /></td>
				<th>联系方式</th>
				<td><s:property value="healthDTO.linkmode" /></td>
			</tr>
			<tr>
				<th>居住地址</th>
				<td colspan="3"><s:property value="healthDTO.address" /></td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td>
		<table width="100%" class="t2">
			<caption>健康情况</caption>
			<tr>
				<th>残疾情况</th>
				<td><s:property value="healthDTO.deformity" /></td>
				<th>残疾等级</th>
				<td><s:property value="healthDTO.defgrade" /></td>
			</tr>

			<tr>
				<th>患病情况</th>
				<td><s:property value="healthDTO.health" /></td>
				<th>患病类型</th>
				<td><s:property value="healthDTO.sickentype" /></td>
			</tr>
			<tr>
				<th>患病名称</th>
				<td colspan="3"><s:property value="healthDTO.sickenname" /></td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td>
		<table width="100%" class="t1">
			<caption>就医记录</caption>
			<tr>
				<th>医疗机构</th>
				<th>业务类别</th>
				<th>诊断名称</th>
				<th>就医时间</th>
				<th>总费用</th>
				<th>个人支付</th>
				<th>统筹支付</th>
				<th>医疗救助</th>
				<th width="50">&nbsp;</th>
			</tr>
			<s:iterator value="healthDTO.bizDTOs">
				<tr>
					<td><s:property value="hospitalId" /></td>
					<td><s:property value="bizType" /></td>
					<td><s:property value="diagnoseName" /></td>
					<td><s:date name="opertime" format="yyyy-MM-dd" /></td>
					<td><s:property value="allmoney" /></td>
					<td><s:property value="personpay" /></td>
					<td><s:property value="insurancepay" /></td>
					<td><s:property value="assistpay" /></td>
					<td width="50"><a
						href="viewBiz.action?bizId=<s:property value='bizId'/>"
						target="_self">详细</a></td>
				</tr>
			</s:iterator>
		</table>
		</td>
	</tr>
	<tr>
		<td align="center"><input type="button" name="Submit" value="关闭"
			onClick="javascript: window.close();"></td>
	</tr>
</table>
</body>
</html>
