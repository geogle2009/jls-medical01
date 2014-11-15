<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<style type="text/css">
<!--
body,td,th {
	font-size: 12px;
}
div {height:150px;width:800;overflow-y:scroll;background:#FFFFFF}
-->
</style>
<link href="/medical/page/css/table-style.css" rel="stylesheet" type="text/css">
</head>
<body>
<br>
<table width="815" border="0" align="center" cellpadding="0"
	cellspacing="0" class="t1">
	<tr>
		<th colspan="5">医疗救助信息表</th>
	</tr>
	<tr>
		<td width="100" >姓名</td>
		<td width="220" ><s:property
			value="medicaldto.name" /> &nbsp;</td>
		<td width="100" >社会保障号</td>
		<td width="220" ><s:property
			value="medicaldto.ssn" /> &nbsp;</td>
		<td rowspan="7" ><a
			href="http://10.10.10.2/yljz/photo/<s:property value="medicaldto.photopath"/>"
			target="_blank"> <img width="100" height="120"
			src="http://10.10.10.2/yljz/photo/<s:property value="medicaldto.photopath"/>"></img>
		</a></td>
	</tr>
	<tr>
		<td >医院名称</td>
		<td colspan="3" ><s:property
			value="medicaldto.hname" /> &nbsp;</td>
	</tr>
	<tr>
		<td >入住日期</td>
		<td ><s:date name="medicaldto.beginDate"
			format="yyyy-MM-dd" /> &nbsp;</td>
		<td >出院日期</td>
		<td ><s:date name="medicaldto.endDate"
			format="yyyy-MM-dd" /> &nbsp;</td>
	</tr>
	<tr>
		<td >入院诊断名称</td>
		<td ><s:property
			value="medicaldto.inDiseaseName" /> &nbsp;</td>
		<td >出院诊断名称</td>
		<td ><s:property
			value="medicaldto.finDiseaseName" /> &nbsp;</td>
	</tr>
	<tr>
		<td >确认诊断名称</td>
		<td ><s:property value="medicaldto.diagnoseName" />
		&nbsp;</td>
		<td >住院天数</td>
		<td ><s:property value="medicaldto.inDays" />
		&nbsp;</td>
	</tr>
	<tr>
		<td >住院/门诊</td>
		<td ><s:property value="medicaldto.bizType" />&nbsp;
		</td>
		<td >实际费用</td>
		<td ><s:property value="medicaldto.cnt" />
		&nbsp; 元</td>
	</tr>
	<tr>
		<td >医保统筹</td>
		<td ><s:property value="medicaldto.ybtc" />
		元&nbsp;</td>
		<td >医疗救助金</td>
		<td ><s:property value="medicaldto.sal" />

		&nbsp; 元<br>
		<s:property value="medicaldto.assispaymsg" /></td>
	</tr>
</table>
<table width="800" border="0" align="center" cellpadding="0"
	cellspacing="0">
	<tr>
		<th colspan="3" align="left">药品明细</th>
	</tr>
	<tr>
		<td colspan="3">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="t1">
			<tr>
				<th width="220" >中心药品项目名称</th>
				<th width="50" >单价</th>
				<th width="50" >用量</th>
				<th width="50" >金额</th>
				<th>处方医生姓名</th>
				<th width="80" >费用发生时间</th>
			</tr>
		</table>
		<div>
		<table width="800" border="0" align="center" cellpadding="0"
			cellspacing="0" class="t1">
			<s:iterator value="druglist">
				<tr>
					<td width="220" ><s:property
						value="mediItemName" />&nbsp;<s:property value="itemName" /></td>
					<td width="50" ><s:property value="price" />
					&nbsp;</td>
					<td width="50" ><s:property value="dosage" />
					&nbsp;</td>
					<td width="50" ><s:property value="money" />
					&nbsp;</td>
					<td ><s:property value="doctorName" />
					&nbsp;</td>
					<td width="80" ><s:date name="feeDate"
						format="yyyy-MM-dd" /> &nbsp;</td>
				</tr>
			</s:iterator>
		</table>
		</div>
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td colspan="7" >药品费用合计: （元）</td>
			</tr>
		</table>		</td>
	</tr>
	<tr>
		<td colspan="2" align="left">费用分类</td>
		<td align="left">支付费用</td>
	</tr>
	<tr valign="top">
		<td>
		<table width="100%" border="0" cellpadding="0" cellspacing="0"  class="t1">
			<tr>
				<th >费用名称</th>
				<th >费用金额</th>
			</tr>
			<s:iterator value="feelist">
				<tr>
					<td ><s:property value="stat" /> &nbsp;</td>
					<td ><s:property value="zfy" /> &nbsp;</td>
				</tr>
			</s:iterator>
			<tr>
				<td colspan="2" >分类费用合计:（元）</td>
			</tr>
		</table>		</td>
		<td width="1px">&nbsp;</td>
		<td>
		<table width="100%" border="0" cellpadding="0" cellspacing="0"  class="t1">
			<tr>
				<th >支付名称</th>
				<th >支付金额</th>
			</tr>
			<s:iterator value="paylist">
				<tr>
					<td ><s:property value="fundName" />&nbsp;</td>
					<td ><s:property value="realPay" />&nbsp;</td>
				</tr>
			</s:iterator>
		</table></td>
	</tr>
</table>
<p align="center">
<button onclick="history.go(-1)">返回</button>
</p>
</body>
</html>