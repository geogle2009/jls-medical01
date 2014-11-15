<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<s:head theme="xhtml" />
<sx:head parseContent="true" />
<link rel="stylesheet" href="../css/table-style.css" type="text/css"></link>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>保存成功</title>
</head>
<body>
<table align="center" width="99%" class="t1" border="0" cellpadding="0"
		cellspacing="0">
	<caption>计算结果</caption>
	<s:property value="result"  escape="false"/>
	<tr><td colspan="4"><!--  <button onclick="history.go(-1)">返回</button>-->
	<button onclick="if(window.opener)window.opener.location.reload();window.close();">关闭</button>
	</td></tr>
</table>
</body>
</html>