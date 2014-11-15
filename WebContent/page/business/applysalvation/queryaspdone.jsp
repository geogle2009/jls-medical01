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
function printhr(id,a1,a2){
	 var bbbb ="<%=basePath%>";
		var url = bbbb
				+ "page/business/printasp.action?aspApproveDTO.aspapproveId="
				+ id + "&aspApproveDTO.memberId=" + a1
				+ "&aspApproveDTO.memberType=" + a2 + "";
		window.open(url,null,"height=600,width=800,status=yes,toolbar=no,menubar=no,location=no,scrollbars=yes");}
	function handle(id, type) {
		var url = "approveaspinit?aspApproveDTO.aspapproveId=" + id
				+ "&aspApproveDTO.memberId=" + type;
		var f = "dialogWidth=700px;dialogHeight=500px;status=no;help=no;scroll=auto";
		window.showModalDialog(url, window, f);
		window.location.reload();
	}
</script>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="page/css/table-style.css" type="text/css"></link>
<title>查询</title>
</head>
<body>
<s:form action="queryaspdone" method="post" theme="simple">
选择机构：<select name="orgid" id="querychronicmembersdone_orgid">
		<option value="2202">吉林市</option>
		<option value="220201">昌邑区</option>
		<option value="220202">船营区</option>
		<option value="220203">龙潭区</option>
		<option value="220204">丰满区</option>
		<option value="220205">高新区</option>
		<option value="220206">经济区</option>
	</select>
查询条件：<s:select name="term" list="#{'':'全部','name':'姓名','ssn':'社会保障号'}"
		listKey="key" listValue="value">
	</s:select>
	查询值：<s:textfield name="value"></s:textfield>
	街道审批：<select name="app1" id="querychronicmembersdone_app1">
		<option value="">未审批</option>
		<option value="1" selected="selected">同意</option>
		<option value="2">不同意</option>
	</select>
 &nbsp; 区县审批：<select name="app2" id="querychronicmembersdone_app2">
		<option value="">未审批</option>
		<option value="1" selected="selected">同意</option>
		<option value="2">不同意</option>
	</select>
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
		<th>打印次数</th>
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
			<td><s:a href="%{view}" target="_blank">
				<s:property value="name"></s:property>
			</s:a></td>
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
			</s:if> <s:if test="aprresult3==3">
			体检中
			</s:if></td>
			<td><s:date name="aprtime1" format="yyyy-MM-dd" />至<s:date
				name="aprtime2" format="yyyy-MM-dd" /></td>
			<td><s:property value="printcount" /></td>
			<td><s:if test="#session.user.organizationId.length()==4">
				<s:url id="cancel" action="approveaspinit">
					<s:param name="aspApproveDTO.aspapproveId">
						<s:property value="aspapproveId" />
					</s:param>
					<s:param name="aspApproveDTO.memberId">
				d
			</s:param>
				</s:url>
				<s:a href="%{cancel}">作废</s:a>
			</s:if> <s:if test="#session.user.organizationId.length()-2==aprlevel*2">
				<s:url id="modify" action="approveaspinit">
					<s:param name="aspApproveDTO.aspapproveId">
						<s:property value="aspapproveId" />
					</s:param>
					<s:param name="aspApproveDTO.memberId">
				m
			</s:param>
				</s:url>
				<a href="javascript:void(0)"
					onclick="handle('<s:property value="aspapproveId" />','m')">修改</a>
			</s:if> <s:if test="status==1&&#session.user.organizationId.length()==4">
				<a href="javascript:void(0)"
					onclick="printhr('<s:property value="aspapproveId" />','<s:property value="memberId"/>','<s:property value="memberType"/>')">打印</a>
			</s:if></td>
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