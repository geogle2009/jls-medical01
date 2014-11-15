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
<title>符合慢性病救助人员列表</title>
</head>
<script type="text/javascript">
	function handle(id, type, mid, mtype) {
		var url = "page/business/approvechronicmemberinit?chronicApproveDTO.chronicapproveId="
				+ id
				+ "&chronicApproveDTO.ssn="
				+ type
				+ "&chronicApproveDTO.memberId="
				+ mid
				+ "&chronicApproveDTO.memberType=" + mtype;
		var f = "dialogWidth=700px;dialogHeight=500px;status=yes;help=no;scroll=auto";
		window.showModalDialog(url, window, f);
		//window.location.reload();
	}
</script>
<body>
<br />
<table align="center" width="90%" class="t1" border="0" cellpadding="0"
	cellspacing="0">
	<tr>
		<th>家庭编号</th>
		<th>姓名</th>
		<th>身份证号</th>
		<th>社会保险号</th>
		<th>操作</th>
	</tr>
	<s:iterator value="persons">
		<s:url id="acm" action="approvechronicmemberinit">
			<s:param name="chronicApproveDTO.ssn">
				<s:property value="ssn" />
			</s:param>
			<s:param name="chronicApproveDTO.chronicapproveId">
			1
			</s:param>
			<s:param name="chronicApproveDTO.memberId">
				<s:property value="memberId" />
			</s:param>
			<s:param name="chronicApproveDTO.memberType">
				<s:property value="memberType" />
			</s:param>
		</s:url>
		<tr>
			<td><s:property value="familyno" /></td>
			<td><s:property value="membername" /></td>
			<td><s:property value="paperid" /></td>
			<td><s:property value="ssn" /></td>
			<td><a href="javascript:void(0)"
				onclick="handle('1','<s:property value="ssn" />','<s:property value="memberId"/>','<s:property value="memberType"/>')">填写</a></td>
		</tr>
	</s:iterator>
</table>
<p align="center">
<button type="button" onclick="history.go(-1)">返回</button>
</p>
</body>
</html>