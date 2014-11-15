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
<title>住院救助金使用情况</title>
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
		$("#opertime1").datepicker({changeYear: true ,changeMonth: true});
		$("#opertime2").datepicker({changeYear: true ,changeMonth: true});
	});
</script>
</head>
<body onLoad="createChart()">
<s:form action="distributionbysickh" method="post" theme="simple">选择地区：<s:select
		name="oid" list="orgs" listKey="orgid" listValue="orgname"></s:select>&nbsp;&nbsp;&nbsp;&nbsp;
选择月份：<s:select name="mid" list="months" listKey="monthid"
		listValue="yearmonth"></s:select> &nbsp;&nbsp;&nbsp;&nbsp;<s:checkbox
		name="hflag"></s:checkbox>按时间段：
		<input type="text" readonly="readonly" id="opertime1" name="opertime1"
		value="<s:date name="opertime1" format="yyyy-MM-dd"/>" />&nbsp;&nbsp;至&nbsp;&nbsp;
		<input type="text" readonly="readonly" id="opertime2" name="opertime2"
		value="<s:date name="opertime2" format="yyyy-MM-dd"/>" />&nbsp;&nbsp;&nbsp;&nbsp;<s:submit
		value="查询"></s:submit>
</s:form>
<table id="stattable" width="99%" cellspacing="0" cellpadding="0"
	border="0" class="t1">
	<tr>
		<th height="24">病种</th>
		<th height="24">救助人次</th>
		<th height="24">总医疗费用</th>
		<th height="24">救助金支付</th>
		<th height="24">占住院病人数比例</th>
	</tr>
	<tbody id="stattablebody">
		<s:property value="stats" escape="false" />
	</tbody>
</table>
<div id="d001"><img id="img001" alt="住院救助人次分布图" src="" /></div>
</body>
<script type="text/javascript">
	function createChart() {
		var tbody = document.getElementById('stattablebody');
		var l = tbody.rows.length;
		if (l > 0) {
			createChart1();
			document.getElementById('d001').style.display = 'block';
		} else {
			document.getElementById('d001').style.display = 'none';
		}
	}
	function createChart1() {
		var tbody = document.getElementById('stattablebody');
		var l = tbody.rows.length;
		var xml = "";
		var title = "住院救助金使用情况";
		if (l > 0) {
			for ( var i = 0; i < l; i++) {
				xml = xml + "<col>";
				var orgname = document.getElementById("r" + i + "d0").innerText;
				var val = document.getElementById("r" + i + "d3").innerText;
				xml = xml + "<orgname>" + orgname + "</orgname><value>" + val
						+ "</value>";
				xml = xml + "</col>";
			}
			xml = "<root>" + xml + "</root>";
		}
		document.getElementById("img001").src = 'distributionbysickhchart.action?xml='
				+ xml + '&title=' + title;
	}
</script>
</html>