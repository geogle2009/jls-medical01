<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="page/css/table-style.css" type="text/css"></link>
<title>补录查询</title>
</head>
<script type="text/javascript">
	function handle(ssn,id,mid,mtype){
		var url="page/business/chronic/chronichandleinit.action?chronicStatusDTO.ssn="+ssn+"&chronicStatusDTO.chronicstatusId="+id+"&chronicStatusDTO.memberId="+mid+"&chronicStatusDTO.memberType="+mtype;
		var f="dialogWidth=700px;dialogHeight=500px;status=no;help=no;scroll=auto";		
		window.showModalDialog(url,window,f);
	}
</script>
<body>
<s:form action="querychronic" method="post" theme="simple">
查询条件：<s:select name="term" list="#{'':'全部','name':'姓名','ssn':'社会保障号'}"
		listKey="key" listValue="value">
	</s:select>
	查询值：<s:textfield name="value"></s:textfield>
	<s:submit value="查询"></s:submit>
</s:form>
<table align="center" width="90%" class="t1" border="0" cellpadding="0"
	cellspacing="0">
	<tr>
		<th>家庭编号</th>
		<th>姓名</th>
		<th>社会保险号</th>
		<th>慢性病名称</th>
		<th>审批时间</th>
		<th>操作</th>
	</tr>
	<s:iterator value="css">
		<tr>
			<td><s:property value="familyId"></s:property></td>
			<td><s:property value="name"></s:property></td>
			<td><s:property value="ssn"></s:property></td>
			<td><s:property value="entityname"></s:property></td>
			<td><s:date name="apptime" format="yyyy-MM-dd"></s:date></td>
			<td>
			<span style="cursor:hand" onclick="handle('<s:property value="ssn"/>','<s:property value="chronicstatusId"/>','<s:property value="memberId"/>','<s:property value="memberType"/>')">
			充值及清零</span></td>
		</tr>
	</s:iterator>
	<tr>
		<td colspan="6">
		<div align="center"><s:property value="toolsmenu" escape="false" /></div>
		</td>
	</tr>
</table>
</body>
</html>