<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<s:head theme="xhtml" />
<sx:head extraLocales="en-us,nl-nl,de-de" />
<link rel="stylesheet" href="../css/table-style.css" type="text/css"></link>
<title>信息查询</title>
<style type="text/css">
<!--
.cssoperator {
	width: 66px;
}

.cssval {
	width: 120px;
}

.cssselect {
	width: 186px;
}

.cssdate {
	width: 85px;
}
-->
</style>
<script type="text/javascript" src="../js/Calendar.js"></script>
</head>
<body>
<s:form action="searchExecute?cur_page=" method="post" theme="simple"
	target="result">
	<table align="center" class="t1" border="0" cellpadding="0"
		cellspacing="0" width="99%">
		<tr><th colspan="2">查询条件选项</th></tr>
		<tr>
			<td>姓名:</td>
			<td><select name="searchDTO.op_membername" class="cssoperator">
				<option value="=">等于</option>
				<option value="like">相似于</option>
				<option value="left">左匹配</option>
				<option value="right">右匹配</option>
			</select> <input type="text" name="searchDTO.membername" class="cssval" /></td>
		</tr>
		<tr>
			<td>身份证号:</td>
			<td><select name="searchDTO.op_paperid" class="cssoperator">
				<option value="=">等于</option>
				<option value="like">相似于</option>
			</select> <input type="text" name="searchDTO.paperid" class="cssval" /></td>
		</tr>
		<tr>
			<td>社会保障号:</td>
			<td><select name="searchDTO.op_ssn" class="cssoperator">
				<option value="=">等于</option>
				<option value="like">相似于</option>
			</select> <input type="text" name="searchDTO.ssn" class="cssval" /></td>
		</tr>
		<tr>
			<td>家庭编号:</td>
			<td><select name="searchDTO.op_familyno" class="cssoperator">
				<option value="=">等于</option>
				<option value="like">相似于</option>
				<option value="left">左匹配</option>
			</select> <input type="text" name="searchDTO.familyno" class="cssval" /></td>
		</tr>
		<tr>
			<td>医疗机构:</td>
			<td><input type="hidden" name="searchDTO.op_hospitalId"
				value="=" /> <select name="searchDTO.hospitalId" class="cssselect">
				<option value="">未选择</option>
				<option value="H015">医药学院附属医院</option>
				<option value="H007">市医院</option>
				<option value="H001">中心医院</option>
			</select></td>
		</tr>
		<tr>
			<td>确认诊断名称:</td>
			<td><input type="hidden" name="searchDTO.op_diagnoseName"
				value="=" /> <sx:autocompleter name="DIAGNOSENAME"
				showDownArrow="false" searchType="startword" list="sicknames"
				loadOnTextChange="true"
				href="/medical/page/business/querySickname.action"
				loadMinimumCount="1" autoComplete="false" /></td>
		</tr>
		<tr>
			<td>就诊时间:</td>
			<td><input type="hidden" name="searchDTO.op_beginDate"
				value="between" /> <input type="text" name="searchDTO.beginDate1"
				class="cssdate" readonly="readonly" onFocus="setday(this)" /> 至 <input
				type="text" name="searchDTO.beginDate1" class="cssdate"
				readonly="readonly" onfocus="setday(this)" /></td>
		</tr>
		<tr>
			<td>保障类型:</td>
			<td><input type="hidden" name="searchDTO.op_assistType"
				value="=" /> <select name="searchDTO.assistType" class="cssselect">
				<option value="">未选择</option>
				<option value="11">城市低保</option>
				<option value="10">分类施保</option>
				<option value="01">农村低保</option>
				<option value="00">非低保</option>
			</select></td>
		</tr>
		<tr>
			<td>总费用:</td>
			<td><select name="searchDTO.op_allmoney" class="cssoperator">
				<option value="&gt;">大于</option>
				<option value="&lt;">小于</option>
				<option value="=">等于</option>
			</select> <input type="text" name="searchDTO.allmoney" class="cssval" /></td>
		</tr>
		<tr>
			<td>在院状态:</td>
			<td><input type="hidden" name="searchDTO.op_outFlag" value="=" />
			<select name="searchDTO.outFlag" class="cssselect">
				<option value="">未选择</option>
				<option value="0">未结算</option>
				<option value="1">已出院结算</option>
			</select></td>
		</tr>
		<tr><td colspan="2" align="center"><s:submit value="查询"></s:submit></td></tr>
	</table>
	
</s:form>
</body>
</html>
