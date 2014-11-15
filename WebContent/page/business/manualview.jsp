<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="../css/table-style.css" type="text/css"></link>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>手工审批</title>
</head>
<body>
<table align="center" width="90%" class="t1" border="0" cellpadding="0"
	cellspacing="0">
	<tr>
		<th colspan="4">手工报销审批</th>
	</tr>
	<tr>
		<td width="90">发票编号</td>
		<td><s:property value="manual.invoice" /></td>
		<td width="90">就医时间</td>
		<td><s:date name="manual.currenttime" format="yyyy-MM-dd" /></td>
	</tr>
	<tr>
	<s:if test="manual.outFlag==1">
		<td width="90">转出医院</td>
		<td><s:property value="manual.hospitalname" /></td>
		<td width="90">转入医院</td>
		<td><s:property value="manual.inHospitalname"></s:property></td>
	</s:if>
	<s:else>
		<td width="90">就诊医院</td>
		<td><s:property value="manual.hospitalname" /></td>
		<td width="90">&nbsp;&nbsp;</td>
		<td>&nbsp;&nbsp;</td>
		</s:else>
	</tr>
	<tr>
		<td width="90">疾病诊断</td>
		<td><s:property value="manual.sickname" /></td>
		<td width="90">参保类型</td>
		<td><s:property value="manual.asistype" /></td>
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
		<td id="begin"><s:date name="manual.begintime"
			format="yyyy-MM-dd" /></td>
		<td width="90">出院时间</td>
		<td id="end"><s:date name="manual.endtime" format="yyyy-MM-dd" />
		</td>
		<td width="120">个人账户支付</td>
		<td><s:property value="manual.accountpayment" />元</td>
	</tr>
	<tr>
		<td>住院天数</td>
		<td><s:property value="manual.days" />天</td>
		<td width="90">&nbsp;</td>
		<td>&nbsp;</td>
		<td width="120">统筹基金支付</td>
		<td><s:property value="manual.planpayment" />元</td>
	</tr>
	<tr>
		<td>预交住院押金</td>
		<td><s:property value="manual.predeposit" />元</td>
		<td width="90">退补住院押金</td>
		<td><s:property value="manual.recdeposit" />元</td>
		<td width="120">公务员补基本医疗</td>
		<td><s:property value="manual.officialpayment1" />元</td>
	</tr>
	<tr>
		<td>起付线</td>
		<td><s:property value="manual.initline" /></td>
		<td width="90">自付比例</td>
		<td><s:property value="manual.selfscale" /></td>
		<td width="120">公务员补助部分</td>
		<td><s:property value="manual.officialpayment" />元</td>
	</tr>
	<tr>
		<td>自理费用</td>
		<td><s:property value="manual.selfmoney" />元</td>
		<td width="90">自费金额</td>
		<td><s:property value="manual.selfall" />元</td>
		<td width="120">保健对象补助支付</td>
		<td><s:property value="manual.healthpayment" />元</td>
	</tr>
	<tr>
		<td>住院费用总额</td>
		<td><s:property value="manual.hospitalmoney" />元</td>
		<td>原个人账户余额</td>
		<td><s:property value="manual.oaccountbalance" />元</td>
		<td width="120">大额商业保险支付</td>
		<td><s:property value="manual.bigpayment" />元</td>
	</tr>
	<tr>
		<td>个人账户余额</td>
		<td><s:property value="manual.accountbalance" />元</td>
		<td>&nbsp;</td>
		<td></td>
		<td width="120">离休统筹自支付</td>
		<td><s:property value="manual.restpayment" />元</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td width="120">工伤保险支付</td>
		<td><s:property value="manual.injurypayment" />元</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td width="120">生育保险支付</td>
		<td><s:property value="manual.bearpayment" />元</td>
	</tr>
	<tr>
		<td colspan="5">
		<img src="<%="http://"+ request.getServerName()+":"+ request.getServerPort()+"/medicalupload/"%><s:property value="manual.filename"/>">
		</td>
	</tr>
	<tr>
		<td colspan="6"><s:url action="manualquerymember" id="back">
			<s:param name="personDTO.ssn" value="manual.ssn">
			</s:param>
		</s:url> <s:a href="%{back}">返回</s:a></td>
	</tr>
</table>
</body>
</html>