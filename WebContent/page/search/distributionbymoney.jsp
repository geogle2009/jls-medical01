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
<title>救助资金使用情况</title>
</head>
<body onLoad="createChart()">
<s:form action="distributionbymoney" method="post" theme="simple">选择年度：<s:select
		name="mid" list="months" listKey="monthid" listValue="yearmonth"></s:select>&nbsp;&nbsp;&nbsp;&nbsp;
<s:submit value="查询"></s:submit>
</s:form>
<table id="stattable" width="99%" cellspacing="0" cellpadding="0"
	border="0" class="t1">
	<tr>
		<th height="24">月份</th>
		<th height="24">实际使用金额</th>
		<th height="24">累计使用金额</th>
		<th height="24">实际使用占 本年度比例</th>
		<th height="24">年度预算金额</th>
	</tr>
	<tbody id="stattablebody">
		<s:property value="stats" escape="false" />
	</tbody>
</table>
<div id="d001"><img id="img001" alt="各年度救助人数及资金统计表" src="" /></div>
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
		var title = "年度救助资金使用情况";
		if (l > 0) {
			for ( var i = 0; i < l; i++) {
				var v1 = document.getElementById("r" + i + "d1").innerText;
				var v2 = document.getElementById("r" + i + "d0").innerText;
				var v3 = document.getElementById("r" + i + "d2").innerText;
				xml = xml + "<col>";
				xml = xml + "<v1>" + v1 + "</v1><v2>实际使用金额</v2><v3>" + v2
						+ "</v3>";
				xml = xml + "</col>";
				xml = xml + "<col>";
				xml = xml + "<v1>" + v3 + "</v1><v2>累计使用金额</v2><v3>" + v2
						+ "</v3>";
				xml = xml + "</col>";
			}
			xml = "<root>" + xml + "</root>";
		}
		document.getElementById("img001").src = 'distributionbymoneychart.action?xml='
				+ xml + '&title=' + title;
	}
</script>
</html>