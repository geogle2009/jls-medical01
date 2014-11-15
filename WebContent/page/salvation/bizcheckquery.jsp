<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link rel="stylesheet" href="../css/table-style.css" type="text/css"></link>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>审核查询</title>
<script type="text/javascript">
	function openBizCheck(bizid, bizcheckid) {
		var url = "initBizcheck.action?bizid="
				+ bizid + "&bizcheckid=" + bizcheckid;
	  	var args =new Object();
	  	args.bizid=bizid;
	  	args.bizcheckid=bizcheckid;
 		var state =window.showModalDialog(url,args,"dialogHeight: 650px; dialogWidth: 800px; center: Yes; resizable: Yes; status: No;");
 		if(args.bizcheckid){
 	 		var html="<a href=\"#\" onclick=\"openBizCheck(" + args.bizid  + "," + args.bizcheckid +  ")\">操作</a>";
 			//alert(html);
 			document.getElementById("cz_" + args.bizid).innerHTML=html;
 		}
	}
</script>
</head>
<body>
<s:form theme="simple" action="initQueryPage" method="post"
	cssStyle="font-size:12px">
	查询条件：
	<s:select value="term" name="term"
		list="#{'':'全部','SSN':'社会保险号','FAMILYNO':'家庭编号','MEMBERNAME':'姓名','PAPERID':'身份证号'}"
		label="查询条件：" listKey="key" listValue="value">
	</s:select>
	操作符：
	<s:select value="operational" name="operational"
		list="#{'=':'等于','like':'相似于'}" label="操作符：" listKey="key"
		listValue="value">
	</s:select>
	查询值：
	<s:textfield name="value"></s:textfield>
	审核状态：
	<s:select listKey="key" listValue="value" value="state" name="state"
		list="#{'unchecked':'未审核','agree':'审核同意','reject':'审核不同意','all':'全部'}">
	</s:select>
	<s:submit value="查询"></s:submit>
	<%String type =request.getParameter("type");
	if(null==type){
		type=(String)request.getAttribute("type");
	}
	%>
	<input value="<%=type %>" type="hidden" name="type"/>
</s:form>
<table align="center" width="90%" class="t1" border="0" cellpadding="0"
	cellspacing="0">
	<tr>
		<th>家庭编号</th>
		<th>姓名</th>
		<th>社会保险号</th>
		<th>医院名称</th>
		<th>入院科室</th>
		<th>入院时间</th>
		<th>疾病诊断</th>
		<th>预计费用</th>
		<th>应审核机构</th>
		<th>审核状态</th>
		<th>操作</th>
	</tr>
	<s:iterator value="checkedlist">
		<tr>
			<td><s:property value="familyno" /></td>
			<td><s:property value="membername" /></td>
			<td><s:property value="ssn" /></td>
			<td><s:property value="hospital" /></td>
			<td><s:property value="inDeptName" /></td>
			<td><s:date name="begintime" format="yyyy-MM-dd"/></td>
			<td><s:property value="sickname" /></td>
			<td><s:property value="estimate"/></td>
			<td><s:property value="checkorg" />
			</td>
			<td><s:if test="checkState==-1">
			未审核
			</s:if> <s:if test="checkState==0">
			不同意救助
			</s:if> <s:if test="checkState==1">
			同意救助
			</s:if></td>
			<td id="cz_<s:property value='bizId'  default='\'\''/>"><a
				href="#"
				onclick="openBizCheck(<s:property value='bizId'  default='\'\''/>, <s:property value='bizcheckId' default='\'\''/>)">
			操作 </a></td>
		</tr>
	</s:iterator>
</table>
<div align="center" style="font-size: 12px"><s:property
	value="toolsmenu" escape="false" /></div>
</body>
</html>