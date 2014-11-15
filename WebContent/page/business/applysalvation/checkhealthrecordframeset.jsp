<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.medical.dto.UserInfoDTO"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	UserInfoDTO user = (UserInfoDTO) session.getAttribute("user");
	String onno = user.getOrganizationId();
	if (onno.length() != 4) {
		response.sendRedirect("results.jsp");
	}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">

<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>框架显示</title>
</head>
<frameset cols="180,*" frameborder="no" border="0" framespacing="0">
	<frame src="page/business/applysalvation/checkaspinit.action"
		name="leftFrame" scrolling="auto" noresize="noresize" id="leftFrame"
		title="leftFrame">
	<frame src="page/business/applysalvation/checkdone.jsp"
		name="mainFrame" id="mainFrame" title="mainFrame">
</frameset>
<noframes>
<body>
</body>
</noframes>
</html>