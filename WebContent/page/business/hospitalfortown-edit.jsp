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
<link type="text/css" href="../js/themes/base/jquery.ui.all.css"
	rel="stylesheet" />
<script type="text/javascript" src="../js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="../js/ui/jquery.ui.core.js"></script>
<script type="text/javascript" src="../js/ui/jquery.ui.widget.js"></script>
<script type="text/javascript" src="../js/ui/jquery.ui.datepicker.js"></script>
<script type="text/javascript"
	src="../js/ui/i18n/jquery.ui.datepicker-zh-CN.js"></script>
<script type="text/javascript">
	$(function() {
		$("#currenttime").datepicker();
		$("#begintime").datepicker();
		$("#endtime").datepicker();
	});
</script>

</head>
<body>
<s:form action="saveHospitalfortown" method="post" theme="simple">
	<s:hidden name="businessDTO.ssn"></s:hidden>
	<s:hidden name="businessDTO.familyno"></s:hidden>
	<table align="center" width="90%" class="t1" border="0" cellpadding="0"
		cellspacing="0">
		<tr>
			<th colspan="4">就诊信息(住院)</th>
		</tr>
		<tr>
			<td width="90">就诊医院</td>
			<td><s:select name="businessDTO.hospital" listKey="hospitalId"
				listValue="name" list="businessDTO.hospitallist">
			</s:select></td>
			<td width="90">就医时间</td>
			<td><input type="text" readonly="readonly" id="currenttime"
				name="businessDTO.currenttime"
				value="<s:date name="businessDTO.currenttime" format="yyyy-MM-dd"/>" />
			</td>
		</tr>
		<tr>
			<td width="90">疾病诊断</td>
			<td><s:url id="dataUrl" value="querySickname.action" /><sx:autocompleter
				name="businessDTO.sickname" showDownArrow="false"
				searchType="startword" list="sicknames" loadOnTextChange="true"
				href="querySickname.action?type=00" loadMinimumCount="1"
				autoComplete="false" /></td>
			<td width="90">参保类型</td>
			<td><s:property value="businessDTO.medicaltypename" /> <s:hidden
				name="businessDTO.medicaltype"></s:hidden></td>
		</tr>
		<tr>
			<td width="90">现金支付总额</td>
			<td colspan="3"><s:textfield name="businessDTO.allmoney" />元</td>
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
			<td id="begin"><input type="text" readonly="readonly"
				id="begintime" name="businessDTO.begintime"
				value="<s:date name="businessDTO.begintime" format="yyyy-MM-dd"/>" />
			</td>
			<td width="90">出院时间</td>
			<td id="end">
			<input type="text" readonly="readonly"
				id="endtime" name="businessDTO.endtime"
				value="<s:date name="businessDTO.endtime" format="yyyy-MM-dd"/>" />
		 </td>
			<td width="120">个人账户支付</td>
			<td><s:textfield name="businessDTO.accountpayment"
				cssStyle="width:80" value="0" />元</td>
		</tr>
		<tr>
			<td>住院天数</td>
			<td><s:textfield readonly="true" name="businessDTO.days"
				cssStyle="width:80" value="0" onblur="countdays()"
				onfocus="countdays()" />天</td>
			<td width="90">&nbsp;</td>
			<td>&nbsp;</td>
			<td width="120">统筹基金支付</td>
			<td><s:textfield name="businessDTO.planpayment"
				cssStyle="width:80" value="0" />元</td>
		</tr>
		<tr>
			<td>预交住院押金</td>
			<td><s:textfield name="businessDTO.predeposit"
				cssStyle="width:80" value="0" />元</td>
			<td width="90">退补住院押金</td>
			<td><s:textfield name="businessDTO.recdeposit"
				cssStyle="width:80" value="0" />元</td>
			<td width="120">公务员补基本医疗</td>
			<td><s:textfield name="businessDTO.officialpayment1"
				cssStyle="width:80" value="0" />元</td>
		</tr>
		<tr>
			<td>起付线</td>
			<td><s:textfield name="businessDTO.initline" cssStyle="width:80"
				value="0" /></td>
			<td width="90">自付比例</td>
			<td><s:textfield name="businessDTO.selfscale"
				cssStyle="width:80" value="0" /></td>
			<td width="120">公务员补助部分</td>
			<td><s:textfield name="businessDTO.officialpayment"
				cssStyle="width:80" value="0" />元</td>
		</tr>
		<tr>
			<td>自理费用</td>
			<td><s:textfield name="businessDTO.selfmoney"
				cssStyle="width:80" value="0" />元</td>
			<td width="90">自费合计</td>
			<td><s:textfield name="businessDTO.selfall" cssStyle="width:80"
				value="0" />元</td>
			<td width="120">保健对象补助支付</td>
			<td><s:textfield name="businessDTO.healthpayment"
				cssStyle="width:80" value="0" />元</td>
		</tr>
		<tr>
			<td>住院费用总额</td>
			<td><s:textfield name="businessDTO.hospitalmoney"
				cssStyle="width:80" value="0" />元</td>
			<td>原个人账户余额</td>
			<td><s:textfield name="businessDTO.oaccountbalance"
				cssStyle="width:80" value="0" />元</td>
			<td width="120">大额保险支付</td>
			<td><s:textfield name="businessDTO.bigpayment"
				cssStyle="width:80" value="0" />元</td>
		</tr>
		<tr>
			<td>个人账户余额</td>
			<td><s:textfield name="businessDTO.accountbalance"
				cssStyle="width:80" value="0" />元</td>
			<td>&nbsp;</td>
			<td></td>
			<td width="120">离休统筹自支付</td>
			<td><s:textfield name="businessDTO.restpayment"
				cssStyle="width:80" value="0" />元</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td width="120">工商保险支付</td>
			<td><s:textfield name="businessDTO.injurypayment"
				cssStyle="width:80" value="0" />元</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td width="120">生育保险支付</td>
			<td><s:textfield name="businessDTO.bearpayment"
				cssStyle="width:80" value="0" />元</td>
		</tr>
		<tr>
			<td colspan="6"><s:submit value="保存"></s:submit>
			<button onclick=
	window.close();
>关闭</button>
			</td>
		</tr>
	</table>
</s:form>
</body>
<script type="text/javascript">
	function countdays() {
		var begin, end, begin1, end1, begin2, end2;
		begin = document.getElementById('begin').firstChild.firstChild.value;
		if ('' == begin) {
			alert('请填写入院时间!');
			return;
		} else {
			begin1 = begin.substring(0, 10);
		}
		end = document.getElementById('end').firstChild.firstChild.value;
		if ('' == end) {
			alert('请填写出院时间');
			return;
		} else {
			end1 = end.substring(0, 10);
		}
		document.getElementById('saveHospitalfortown_businessDTO_days').value = DateDiff(
				end1, begin1);
	}
	//计算天数差的函数，通用
	function DateDiff(sDate1, sDate2) { //sDate1和sDate2是2002-12-18格式
		var aDate, oDate1, oDate2, iDays;
		aDate = sDate1.split("-");
		oDate1 = new Date(aDate[1] + '-' + aDate[2] + '-' + aDate[0]);//转换为12-18-2002格式
		aDate = sDate2.split("-");
		oDate2 = new Date(aDate[1] + '-' + aDate[2] + '-' + aDate[0]);
		iDays = parseInt(Math.abs(oDate1 - oDate2) / 1000 / 60 / 60 / 24);//把相差的毫秒数转换为天数
		return iDays;
	}
</script>
</html>