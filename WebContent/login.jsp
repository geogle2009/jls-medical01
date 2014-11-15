<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>吉林市医疗救助</title>
<style type="text/css">
<!--
body,td,th {
	font-size: 12px;
}
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
#Layer1 {
	position:absolute;
	width:258px;
	height:108px;
	z-index:1;
	left: 517px;
	top: 224px;
}
-->
</style></head>
<body>
<s:form action="login" method="post" theme="simple">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">  
  <tr>
    <td>&nbsp;</td>
    <td height="140">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td width="490" height="298"><table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" background="page/images/loginbg.png"> 
      <tr>
        <td colspan="5" align="center">吉林市医疗救助</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
         <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td align="right" valign="middle">用户名：</td>
        <td align="left" valign="middle"><s:textfield name="userinfo.username" cssStyle="width:120px"> </s:textfield></td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td align="right" valign="middle">密&nbsp;&nbsp;&nbsp;&nbsp;码：</td>
        <td align="left" valign="middle"><s:password name="userinfo.password" cssStyle="width:120px"> </s:password></td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td align="right" valign="middle"></td>
        <td align="left" valign="middle"><s:submit value="登录系统"></s:submit></td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td colspan="5" align="center">&nbsp;</td>
      </tr>
    </table></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td valign="top"><s:actionerror/></td>
    <td>&nbsp;</td>
  </tr>
</table>
<%String token=request.getParameter("token");
%>
<input type="hidden" name="userinfo.idcard" value="<%=token%>"/>
</s:form>
</body>
</html>