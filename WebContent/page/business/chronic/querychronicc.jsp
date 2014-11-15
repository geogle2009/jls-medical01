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
	var url="page/business/approvechronicmemberinit?chronicApproveDTO.chronicapproveId="+id+"&chronicApproveDTO.ssn="+type+"&chronicApproveDTO.memberId="
	+ mid
	+ "&chronicApproveDTO.memberType=" + mtype;
	var f="dialogWidth=700px;dialogHeight=500px;status=no;help=no;scroll=auto";		
	window.showModalDialog(url,window,f);
	window.location.reload();
}
function printhr(id){
	window.open("<%=basePath%>page/business/printhr.action?chronicApproveDTO.chronicapproveId="+id,null,
	 "height=600,width=800,status=yes,toolbar=no,menubar=no,location=no,scrollbars=yes");
		}
</script>
<body>
<s:form action="querychronicc" method="post" theme="simple">
	<table align="center" width="99%" class="t1" border="0" cellpadding="0"
		cellspacing="0">
		<tr>
			<td>选择地区：<s:select name="orgid" list="orgs" listKey="orgid"
				listValue="orgname"></s:select>&nbsp; 查询条件：<s:select name="term"
				list="#{'':'全部','name':'姓名','familyno':'家庭编号','ssn':'社会保障号','paperid':'身份证号'}"
				listKey="key" listValue="value">
			</s:select>查询值：<s:textfield name="value"></s:textfield> 患病名称：<s:select name="icdid" list="chronics" listKey="key"
				listValue="value" headerKey='' headerValue="全部"></s:select>来源：<s:select name="ds" list="#{'':'全部','1':'城市','2':'农村'}"
				listKey="key" listValue="value"></s:select>审批来源：<s:select name="apds" list="#{'':'全部','民政审批':'民政审批','医保接口核对':'医保来源'}"
		listKey="key" listValue="value">
	</s:select> <s:submit value="查询"></s:submit>&nbsp;<button onclick="window.open('../downloadExcel.action?type=2')">导出excel</button>
			</td>
		</tr>
	</table>
</s:form>
<table align="center" width="99%" class="t1" border="0" cellpadding="0"
	cellspacing="0">
	<tr>
		<th>家庭编号</th>
		<th>姓名</th>
		<th>身份证号</th>
		<th>患病名称</th>
		<th>审批情况</th>
		<th>审批时间</th>
		<th>数据来源</th>
		 
		<!--  <th>操作</th>-->
	</tr>
	<s:iterator value="ccs">
		<s:url id="view" action="viewapprovelist">
			<s:param name="chronicApproveDTO.chronicapproveId">
				<s:property value="capid" />
			</s:param>
		</s:url>
		<tr>
			<td><s:property value="familyId"></s:property></td>
			<td><s:a href="%{view}" target="_blank">
				<s:property value="name"></s:property>
			</s:a></td>
			<td><s:property value="paperid"></s:property></td>
			<td><s:property value="entityval"></s:property></td>
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
			</s:if> <s:if test="aprresult3==3">
			>>体检中
			</s:if></td>
			<td>
				<s:date name="aprtime2" format="yyyy-MM-dd"/>
			</td>
			<td><s:if test="memberType==1">
				城市
				</s:if> <s:if test="memberType==2">
				农村
				</s:if>
				<s:property value="ywval" />
				</td>
			  
			<!-- <td><s:if test="(#session.user.organizationId.length()==6)||(#session.user.organizationId.length()==4)">
				<s:url id="cancel" action="approvechronicmemberinit">
					<s:param name="chronicApproveDTO.chronicapproveId">
						<s:property value="capid" />
					</s:param>
					<s:param name="chronicApproveDTO.ssn">
				d
			</s:param>
				</s:url>
				<s:a  href="%{cancel}">作废重审</s:a>
			</s:if> <s:if test="flag==1">
				<a href="javascript:void(0)"
					onclick="handle('<s:property value="capid" />','m','<s:property value="memberId" />','<s:property value="memberType" />')">修改</a>
			</s:if> <s:if test="aprresult3==3&&#session.user.organizationId.length()==4">
				<a href="javascript:void(0)"
					onclick="printhr(<s:property value="capid" />)">打印</a>
			</s:if></td> -->
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