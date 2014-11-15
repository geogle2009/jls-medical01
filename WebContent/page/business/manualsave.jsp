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
		$("#endtime").datepicker(); 
		$("#begintime").datepicker(); 
		$("#currenttime").datepicker();  
	});
	</script>
<title>手工报销审批</title>
</head>
<body>
<s:form enctype="multipart/form-data" action="savemanual" method="post"
	theme="simple" onsubmit="return check()">
	<s:hidden name="manual.ssn"></s:hidden>
	<s:hidden name="manual.familyno"></s:hidden>
	<s:hidden name="manual.linkmode"></s:hidden>
	<s:hidden name="manual.sex"></s:hidden>
	<s:hidden name="manual.address"></s:hidden>
	<s:hidden name="manual.age"></s:hidden>
	<s:hidden name="manual.membername"></s:hidden>
	<table align="center" width="100%" class="t1" border="0" cellpadding="0"
		cellspacing="0">
		<tr>
			<th colspan="4">手工报销审批</th>
		</tr>
		<tr>
			<td width="90">发票编号</td>
			<td><s:textfield name="manual.invoice"></s:textfield></td>
			<td width="90">就医时间</td>
			<td id="cur"><input type="text" readonly="readonly"
				id="currenttime" name="manual.currenttime"
				value="<s:date name="manual.currenttime" format="yyyy-MM-dd"/>" /></td>
		</tr>
		<tr>
			<td width="90"><script type="text/javascript">
				function disp(){
					var ch1= savemanual.savemanual_manual_outFlag.checked;
					var ch2= savemanual.savemanual_manual_inHospitalname;
					if(ch1){
						ch2.style.display="block";
						sss.style.display="block";
					}else{
						ch2.style.display="none";
						sss.style.display="block";
					}
				}
			</script> <s:checkbox name="manual.outFlag" onclick="disp()"></s:checkbox>转院</td>
			<td><s:textfield name="manual.hospitalname"></s:textfield></td>
			<td width="90"><span style="display: none" id="sss">转入医院</span></td>
			<td><s:textfield cssStyle="display:none"
				name="manual.inHospitalname"></s:textfield></td>
		</tr>
		<tr>
			<td width="90">疾病诊断</td>
			<td><s:textfield name="manual.sickname" /></td>
			<td width="90">参保类型</td>
			<td><s:checkbox name="manual.asistype" value="true">
			</s:checkbox>城市低保户</td>
		</tr>
		<tr>
			<td>业务类型</td>
			<td colspan="3"><s:select name="manual.biztype"
				list="#{'0':'住院','1':'门诊'}" listKey="key" listValue="value"></s:select></td>
		</tr>
	</table>
	<br>
	<table align="center" width="100%" class="t1" border="0" cellpadding="0"
		cellspacing="0">
		<tr>
			<th colspan="6">录入医疗费用</th>
		</tr>
		<tr>
			<td width="90">入院时间</td>
			<td id="begin"><input type="text" readonly="readonly"
				id="begintime" name="manual.begintime"
				value="<s:date name="manual.begintime" format="yyyy-MM-dd"/>" /></td>
			<td width="90">出院时间</td>
			<td id="end"><input type="text" readonly="readonly" id="endtime"
				value="<s:date name="manual.endtime" format="yyyy-MM-dd" />"
				name="manual.endtime" /></td>
			<td width="120">个人账户支付</td>
			<td><s:textfield name="manual.accountpayment"
				cssStyle="width:80" value="0" />元</td>
		</tr>
		<tr>
			<td>住院天数</td>
			<td><s:textfield readonly="true" name="manual.days"
				cssStyle="width:80" value="0" onblur="countdays()"
				onfocus="countdays()" />天</td>
			<td width="90">&nbsp;</td>
			<td>&nbsp;</td>
			<td width="120">统筹基金支付</td>
			<td><s:textfield onblur="count()" onchange="count()"
				name="manual.planpayment" cssStyle="width:80" value="0" />元</td>
		</tr>
		<tr>
			<td>预交住院押金</td>
			<td><s:textfield name="manual.predeposit" cssStyle="width:80"
				value="0" />元</td>
			<td width="90">退补住院押金</td>
			<td><s:textfield name="manual.recdeposit" cssStyle="width:80"
				value="0" />元</td>
			<td width="120">公务员补基本医疗</td>
			<td><s:textfield name="manual.officialpayment1"
				cssStyle="width:80" value="0" />元</td>
		</tr>
		<tr>
			<td>起付线</td>
			<td><s:textfield name="manual.initline" cssStyle="width:80"
				value="0" /></td>
			<td width="90">自付比例</td>
			<td><s:textfield name="manual.selfscale" cssStyle="width:80"
				value="0" /></td>
			<td width="120">公务员补助部分</td>
			<td><s:textfield name="manual.officialpayment"
				cssStyle="width:80" value="0" />元</td>
		</tr>
		<tr>
			<td>自理费用</td>
			<td><s:textfield name="manual.selfmoney" cssStyle="width:80"
				value="0" readonly="true" />元</td>
			<td width="90">自费金额</td>
			<td><s:textfield name="manual.selfall" cssStyle="width:80"
				value="0" onblur="count()" onchange="count()" />元</td>
			<td width="120">保健对象补助支付</td>
			<td><s:textfield name="manual.healthpayment" cssStyle="width:80"
				value="0" />元</td>
		</tr>
		<tr>
			<td>住院费用总额</td>
			<td><s:textfield onblur="count()" onchange="count()"
				name="manual.hospitalmoney" cssStyle="width:80" value="0" />元</td>
			<td>原个人账户余额</td>
			<td><s:textfield name="manual.oaccountbalance"
				cssStyle="width:80" value="0" />元</td>
			<td width="120">大额商业保险支付</td>
			<td><s:textfield onblur="count()" onchange="count()"
				name="manual.bigpayment" cssStyle="width:80" value="0" />元</td>
		</tr>
		<tr>
			<td>个人账户余额</td>
			<td><s:textfield name="manual.accountbalance"
				cssStyle="width:80" value="0" />元</td>
			<td>&nbsp;</td>
			<td></td>
			<td width="120">离休统筹自支付</td>
			<td><s:textfield name="manual.restpayment" cssStyle="width:80"
				value="0" />元</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td width="120">工伤保险支付</td>
			<td><s:textfield name="manual.injurypayment" cssStyle="width:80"
				value="0" />元</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td width="120">生育保险支付</td>
			<td><s:textfield name="manual.bearpayment" cssStyle="width:80"
				value="0" />元</td>
		</tr>
		<tr>
			<td colspan="6"><s:file name="manual.myFile" label="选择发表文件" /></td>
		</tr>
		<tr>
			<td colspan="6"><s:submit value="保存"></s:submit>&nbsp;&nbsp;&nbsp;&nbsp;
			<button onclick="window.close()">关闭</button>
			</td>
		</tr>
	</table>
</s:form>
</body>
<script type="text/javascript">
	function countdays() {
		var begin, end, begin1, end1, begin2, end2;
		begin = document.getElementById('begin').firstChild.value;
		if ('' == begin) {
			alert('请填写入院时间!');
			return;
		} else {
			var year=begin.substring(0, 4);
			if(year<2013){
				alert('只能处理2013年以后的结算单!');
				return
			}else{
			begin1 = begin.substring(0, 10);}
			
		}
		end = document.getElementById('end').firstChild.value;
		if ('' == end) {
			alert('请填写出院时间');
			return;
		} else {
			end1 = end.substring(0, 10);
		}
		document.getElementById('savemanual_manual_days').value = DateDiff(
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
	function count() {
		var planpayment = document
				.getElementById('savemanual_manual_planpayment').value;
		var selfall = document.getElementById('savemanual_manual_selfall').value;
		var hospitalmoney = document
				.getElementById('savemanual_manual_hospitalmoney').value;
		var bigpayment = document
				.getElementById('savemanual_manual_bigpayment').value;
		planpayment = parseFloat(planpayment);
		selfall = parseFloat(selfall);
		hospitalmoney = parseFloat(hospitalmoney);
		bigpayment = parseFloat(bigpayment);

		if (isNaN(planpayment)) {
			planpayment = 0;
			alert('请输入数字');
		} else {
		}
		if (isNaN(selfall)) {
			selfall = 0;
			alert('请输入数字');
		} else {
		}
		if (isNaN(hospitalmoney)) {
			hospitalmoney = 0;
			alert('请输入数字');
		} else {
		}
		if (isNaN(bigpayment)) {
			bigpayment = 0;
			alert('请输入数字');
		} else {
		}
		document.getElementById('savemanual_manual_selfmoney').value =Math.round((hospitalmoney -planpayment -bigpayment -selfall)*100)/100;
	}
	function check(){
		var flag=true;
		var invoice =document.getElementById('savemanual_manual_invoice').value;
		var hospitalname =document.getElementById('savemanual_manual_hospitalname').value;
		var sickname =document.getElementById('savemanual_manual_sickname').value;
		var cur = document.getElementById('cur').firstChild.firstChild.value;;
		var begin=document.getElementById('begin').firstChild.firstChild.value;;
		var end=document.getElementById('end').firstChild.firstChild.value;
		if(""==invoice){
			alert("发票编号 不能 为空");
			flag= false;
		}
		if(""==hospitalname){
			alert("就医医院 不能 为空");
			flag= false;
		}
		if(""==sickname){
			alert("疾病诊断 不能为空");
			flag= false;
		}
		if(""==cur){
			alert("就医时间 不能为空");
			flag= false;
		}
		if(""==begin){
			alert("入院时间 不能为空");
			flag= false;
		}
		if(""==end){
			alert("出院时间 不能为空");
			flag= false;
		}
		return flag;
	}
	function uploadin(){
		window.open('jtwain/applet.jsp');
		}
</script>
</html>