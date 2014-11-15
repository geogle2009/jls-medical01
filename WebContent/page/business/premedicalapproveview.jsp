<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="../css/table-style.css" type="text/css"></link>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看</title>
</head>
<body>
<table align="center" width="90%" class="t1" border="0" cellpadding="0"
	cellspacing="0">
	<tr>
		<th colspan="4">医前救助信息查看</th>
	</tr>
	<tr>
		<td width="90">就诊医院</td>
		<td><s:property value="businessDTO.hospitalname" /></td>
		<td width="90">就医时间</td>
		<td><s:date name="businessDTO.currenttime" format="yyyy-MM-dd" /></td>
	</tr>
	<tr>
		<td width="90">疾病诊断</td>
		<td><s:property value="businessDTO.sickname" /></td>
		<td width="90">参保类型</td>
		<td><s:property value="businessDTO.medicaltype" /></td>
	</tr>
	<tr>
		<td width="90">现金支付总额</td>
		<td colspan="3"><s:property value="businessDTO.allmoney" />元</td>
	</tr>
</table>
<br>
<table align="center" width="90%" class="t1" border="0" cellpadding="0"
	cellspacing="0">
	<tr>
		<th colspan="6">录入医疗费用</th>
	</tr>
	<tr>
		<td width="90">入院时间</td>
		<td><s:date name="businessDTO.begintime" format="yyyy-MM-dd" /></td>
		<td width="90">出院时间</td>
		<td><s:date name="businessDTO.endtime" format="yyyy-MM-dd" /></td>
		<td width="120">个人账户支付</td>
		<td><s:property value="businessDTO.accountpayment" />元</td>
	</tr>
	<tr>
		<td>住院天数</td>
		<td><s:property value="businessDTO.days" />天</td>
		<td width="90">&nbsp;</td>
		<td>&nbsp;</td>
		<td width="120">统筹基金支付</td>
		<td><s:property value="businessDTO.planpayment" />元</td>
	</tr>
	<tr>
		<td>预交住院押金</td>
		<td><s:property value="businessDTO.predeposit" />元</td>
		<td width="90">退补住院押金</td>
		<td><s:property value="businessDTO.recdeposit" />元</td>
		<td width="120">公务员补基本医疗</td>
		<td><s:property value="businessDTO.officialpayment1" />元</td>
	</tr>
	<tr>
		<td>起付线</td>
		<td><s:property value="businessDTO.initline" /></td>
		<td width="90">自付比例</td>
		<td><s:property value="businessDTO.selfscale" /></td>
		<td width="120">公务员补助部分</td>
		<td><s:property value="businessDTO.officialpayment" />元</td>
	</tr>
	<tr>
		<td>自理费用</td>
		<td><s:property value="businessDTO.selfmoney"/>元</td>
		<td width="90">自费合计</td>
		<td><s:property value="businessDTO.selfall" />元</td>
		<td width="120">保健对象补助支付</td>
		<td><s:property value="businessDTO.healthpayment" />元</td>
	</tr>
	<tr>
		<td>住院费用总额</td>
		<td><s:property value="businessDTO.hospitalmoney" />元</td>
		<td>原个人账户余额</td>
		<td><s:property value="businessDTO.oaccountbalance" />元</td>
		<td width="120">大额保险支付</td>
		<td><s:property value="businessDTO.bigpayment" />元</td>
	</tr>
	<tr>
		<td>个人账户余额</td>
		<td><s:property value="businessDTO.accountbalance" />元</td>
		<td>&nbsp;</td>
		<td></td>
		<td width="120">离休统筹自支付</td>
		<td><s:property value="businessDTO.restpayment" />元</td>
	</tr>
	<tr>
		<td>医疗救助</td>
		<td><s:property value="businessDTO.z01" />元</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td width="120">工商保险支付</td>
		<td><s:property value="businessDTO.injurypayment" />元</td>
	</tr>
	<tr>
		<td>实际支付</td>
		<td><s:property value="businessDTO.z02" />元</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td width="120">生育保险支付</td>
		<td><s:property value="businessDTO.bearpayment" />元</td>
	</tr>
	<tr>
		<td colspan="6" align="center">
		<button onclick="history.go(-1)">返回</button>
		</td>
	</tr>
</table>
</body>
</html>