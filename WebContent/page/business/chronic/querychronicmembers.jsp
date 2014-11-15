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
function handle(id,type,mid,mtype){
	var url="page/business/approvechronicmemberinit?chronicApproveDTO.chronicapproveId="+id+"&chronicApproveDTO.ssn="+type+"&chronicApproveDTO.memberId="
	+ mid
	+ "&chronicApproveDTO.memberType=" + mtype;
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
<table align="center" width="90%" class="t1" border="0" cellpadding="0"
	cellspacing="0"><tr><td>
<s:form action="querychronicmembers" method="post" theme="simple">
查询条件：<s:select name="term" list="#{'':'全部','name':'姓名','familyno':'家庭编号','ssn':'社会保障号'}"
		listKey="key" listValue="value">
	</s:select>&nbsp;&nbsp;
	查询值：<s:textfield name="value"></s:textfield>&nbsp;&nbsp;
	审批来源：<s:select name="apds" list="#{'':'全部','民政审批':'民政审批','医保接口核对':'医保来源'}"
		listKey="key" listValue="value">
	</s:select>&nbsp;&nbsp;
	来源：<s:select name="ds" list="#{'':'全部','1':'城市','2':'农村'}"
		listKey="key" listValue="value">
	</s:select>&nbsp;&nbsp;
	<s:submit value="查询"></s:submit>
</s:form></td></tr></table>
<table align="center" width="90%" class="t1" border="0" cellpadding="0"
	cellspacing="0">
	<tr>
		<th>家庭编号</th>
		<th>姓名</th>
		<th>社会保险号</th>
		<th>审批状态</th>
		<th>审批时间</th>
		<th>数据来源</th>
		<th>操作</th>
	</tr>
	<s:iterator value="cas">
	<s:url id="view" action="viewapprovelist">
			<s:param name="chronicApproveDTO.chronicapproveId">
				<s:property value="chronicapproveId" />
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
			>>区县同意
			</s:if> <s:if test="aprresult2==2">
			>>区县不同意 
			</s:if> <s:if test="aprresult3==1">
			>>市级同意 
			</s:if> <s:if test="aprresult3==2">
			>>市级不同意 
			</s:if>
			 <s:if test="aprresult3==3">
			体检中
			</s:if></td>
			<td>
			<s:date name="aprtime1" format="yyyy-MM-dd"/>至<s:date name="aprtime3" format="yyyy-MM-dd"/>
			</td>
			<td>
				<s:if test="memberType==1">
				城市
				</s:if>
				<s:if test="memberType==2">
				农村
				</s:if>
				</td>
			<td><s:url id="acm" action="approvechronicmemberinit">
				<s:param name="chronicApproveDTO.chronicapproveId">
					<s:property value="chronicapproveId" />
				</s:param>
				<s:param name="chronicApproveDTO.ssn">
				q
			</s:param>
			<s:param name="chronicApproveDTO.memberId">
				<s:property value="memberId" />
			</s:param>
			<s:param name="chronicApproveDTO.memberType">
				<s:property value="memberType" />
			</s:param>
			</s:url> <a href="javascript:void(0)" onclick="handle('<s:property value="chronicapproveId" />','q','<s:property value="memberId" />','<s:property value="memberType" />')">填写</a></td>
		</tr>
	</s:iterator>
	<tr>
		<td colspan="7">
		<div align="center"><s:property value="toolsmenu" escape="false" /></div>
		</td>
	</tr>
</table>
</body>
</html>