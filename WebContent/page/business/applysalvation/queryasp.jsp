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
<script type="text/javascript">
function handle(id,type,q){
	var url="approveaspinit?aspApproveDTO.aspapproveId="+id+"&aspApproveDTO.memberId="+type+"&aspApproveDTO.memberType="+q;
	var f="dialogWidth=700px;dialogHeight=500px;status=no;help=no;scroll=auto";		
	window.showModalDialog(url,window,f);
	window.location.reload();
}
</script>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="page/css/table-style.css" type="text/css"></link>
<title>查询</title>
</head>
<body>
<s:form action="queryasp" method="post" theme="simple">
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
		<th>审批状态</th>
		<th>审批时间</th>
		<th>操作</th>
	</tr>
	<s:iterator value="aas">
	<s:url id="view" action="viewasp">
			<s:param name="aspApproveDTO.aspapproveId">
				<s:property value="aspapproveId" />
			</s:param>
		</s:url>
		<tr>
			<td><s:property value="familyId"></s:property></td>
			<td>
			<s:a href="%{view}" target="_blank">
			<s:property value="name"></s:property>
			</s:a>
			</td>
			<td><s:property value="ssn"></s:property></td>
			<td><s:if test="aprresult1==1">
			街道同意 
			</s:if> <s:if test="aprresult1==2">
			街道不同意 
			</s:if> <s:if test="aprresult2==1">
			>>区县同意>> 
			</s:if> <s:if test="aprresult2==2">
			>>区县不同意>> 
			</s:if> <s:if test="aprresult3==1">
			市级同意 
			</s:if> <s:if test="aprresult3==2">
			市级不同意 
			</s:if>
			 <s:if test="aprresult3==3">
			体检中
			</s:if></td>
			<td>
			<s:date name="aprtime1" format="yyyy-MM-dd"/>至<s:date name="aprtime2" format="yyyy-MM-dd"/>
			</td>
			<td><a href="javascript:void(0)" onclick="handle('<s:property value="aspapproveId" />','q','')">填写</a></td>
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