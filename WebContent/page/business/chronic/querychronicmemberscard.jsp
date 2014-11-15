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
<title></title>
</head>
<script type="text/javascript">
function handle(id,type,mid,mtype){
	var url="viewcard.action?chronicApproveDTO.chronicapproveId="+id+"&chronicApproveDTO.ssn="+type+"&chronicApproveDTO.memberId="
	+ mid
	+ "&chronicApproveDTO.memberType=" + mtype;
	var f="dialogWidth=520px;dialogHeight=330px;status=no;help=no;scroll=auto";		
	window.showModalDialog(url,window,f);
}
function printhr(id){
	window.open("<%=basePath%>page/business/printhr.action?chronicApproveDTO.chronicapproveId="+id,null,
	 "height=600,width=800,status=yes,toolbar=no,menubar=no,location=no,scrollbars=yes");
		}
</script>
<body>
<s:form action="querychronicmemberscard" method="post" theme="simple">
查询条件：<s:select name="term" list="#{'':'全部','name':'姓名','familyno':'家庭编号','ssn':'社会保障号'}"
		listKey="key" listValue="value">
	</s:select>&nbsp;&nbsp;
	查询值：<s:textfield name="value"></s:textfield>&nbsp;&nbsp;
	<s:hidden name="app1" value="1"></s:hidden>
	<s:hidden name="app2" value="2"></s:hidden>	
	来源：<s:select name="ds" list="#{'':'全部','1':'城市','2':'农村'}" listKey="key" listValue="value"></s:select>
	<s:submit value="查询"></s:submit>&nbsp;&nbsp;<button
		onclick="window.open('../downloadExcel.action?type=2')">导出excel</button>
</s:form>
<table align="center" width="90%" class="t1" border="0" cellpadding="0"
	cellspacing="0">
	<tr>
		<th>家庭编号</th>
		<th>姓名</th>
		<th>社会保险号</th>
		<th>救助病种</th>
		<th>数据来源</th>
		<th>有效标识</th>
		<th>发卡标识</th>
		<th>照片</th>
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
			<td>
			<s:property value="entityval"/>
			</td>
			
				<td>
				<s:if test="memberType==1">
				城市
				</s:if>
				<s:if test="memberType==2">
				农村
				</s:if>
				</td>
				<td><s:property value="sts"/></td>
				<td><s:property value="remark"/></td>
				<td>
				 <s:property value="anFilename"/>
				</td>
			<td> 
				<a href="javascript:void(0)" onclick="handle('<s:property value="chronicapproveId" />','m','<s:property value="memberId" />','<s:property value="memberType" />')">预览</a>
			</td>
		</tr>
	</s:iterator>
	<tr>
		<td colspan="9">
		<div align="center"><s:property value="toolsmenu" escape="false" /></div>
		</td>
	</tr>
</table>
</body>
</html>