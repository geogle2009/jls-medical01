<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link rel="stylesheet" href="../css/table-style.css" type="text/css"></link>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>家庭信息</title>
</head>
<body>
<form action="success.jsp" method="post" name="inputform">
<table  width="99%" class="t1" border="0" cellpadding="0"
	cellspacing="0">
	<tr>
		<th colspan="7">查询救助对象</th>
	</tr>
	<tr>
		<td width="80">家庭编号</td>
		<td width="120">无</td>
		<td width="80">家庭类型</td>
		<td width="120"><input type="text" name="textfield"></td>
		<td width="80">&nbsp;</td>
		<td width="120">&nbsp;</td>
		<td rowspan="6">&nbsp;</td>
	</tr>
	<tr>
		<td width="80">户主姓名</td>
		<td width="120"><input type="text" name="textfield2"></td>
		<td width="80">证件类别</td>
		<td width="120"><select name="select">
			<option value="1">身份证</option>
			<option value="0">军人证</option>
			<option value="0">其他</option>
		</select></td>
		<td width="80">证件号码</td>
		<td width="120"><input type="text" name="textfield6"></td>
	</tr>
	<tr>
		<td width="80">出生日期</td>
		<td width="120"><input type="text" name="textfield3"></td>
		<td width="80">性别</td>
		<td width="120"><select name="select2">
			<option value="1">男</option>
			<option value="0">女</option>
		</select></td>
		<td width="80">联系方式</td>
		<td width="120"><input type="text" name="textfield7"></td>
	</tr>
	<tr>
		<td width="80">&nbsp;</td>
		<td width="120">&nbsp;</td>
		<td width="80">&nbsp;</td>
		<td width="120">&nbsp;</td>
		<td width="80">&nbsp;</td>
		<td width="120">&nbsp;</td>
	</tr>
	<tr>
		<td width="80">&nbsp;</td>
		<td width="120">&nbsp;</td>
		<td width="80">&nbsp;</td>
		<td width="120">&nbsp;</td>
		<td width="80">&nbsp;</td>
		<td width="120">&nbsp;</td>
	</tr>
	<tr>
		<td width="80">&nbsp;</td>
		<td width="120">&nbsp;</td>
		<td width="80">&nbsp;</td>
		<td width="120">&nbsp;</td>
		<td width="80">&nbsp;</td>
		<td width="120">&nbsp;</td>
	</tr>
	<tr>
		<td width="80">家庭住址</td>
		<td colspan="5"><input type="text" name="textfield8"></td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td colspan="6"><input type="submit" value="保存家庭信息"/>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<button onclick="history.go(-1)">返回</button>
		</td>
		<td>&nbsp;</td>
	</tr>
</table>
</form>
<table  width="99%" class="t1" border="0" cellpadding="0"
	cellspacing="0">
	<caption style="font-size:12px">家庭成员列表</caption>
	<tr>
		<th>姓名</th>
		<th>与户主关系</th>
		<th>证件类型</th>
		<th>证件号码</th>
		<th>操作</th>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>修改\删除</td>
	</tr>
	<tr>
		<td colspan="5"><a href="member.jsp">添加成员</a></td>
	</tr>
</table>
</body>
</html>