<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String a = request.getScheme() + "://" + request.getServerName()
			+ "/file/";
	String b = basePath + "ViewPic.ct";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<META HTTP-EQUIV='pragma' CONTENT='no-cache'>
<META HTTP-EQUIV='Cache-Control' CONTENT='no-cache, must-revalidate'>
<META HTTP-EQUIV='expires' CONTENT='0'>
<title>社会救助卡预览</title>
</head>
<style type="text/css">
<!--
#apDiv1 {
	position: absolute;
	width: 117px;
	height: 144px;
	z-index: 1;
	left: 383px;
	top: 47px;
}

#apDiv2 {
	position: absolute;
	width: 126px;
	height: 44px;
	z-index: 2;
	left: 162px;
	top: 133px;
}
-->
</style>
<body>
	<table width="512" height="324"
		background="page/business/chronic/card.jpg">
		<tr>
			<td></td>
		</tr>
	</table>
	<div id="apDiv2">
		姓名：
		<s:property value="chronicApproveDTO.masterName"></s:property>
	</div>
	<div id="apDiv1">
		<img
			src="<%=basePath%>ViewPic.ct?memberId=<s:property value="chronicApproveDTO.memberId"/>&ds=<s:property value="chronicApproveDTO.memberType"/>"
			width="117px" height="144px" />
	</div>
</body>
</html>