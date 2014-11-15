<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base target="_self">
<s:head theme="xhtml" />
<sx:head extraLocales="en-us,nl-nl,de-de" />
<link rel="stylesheet" href="../css/table-style.css" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>审核信息</title>
<script type="text/javascript">
	var newbizcheckid=<s:property value="bizCheckDTO.bizcheckId"/>;
	//alert("保存成功页"+newbizcheckid);
	var args=window.dialogArguments;
	if(newbizcheckid){
 		args.bizcheckid=newbizcheckid;
 	} 
</script>
</head>
<body>
<table width="100%">
	<tr>
		<th height="27" bgcolor="#FFA147">审核信息</th>
	</tr>
	<tr>
		<td>
		<table width="100%" class="t2">
			<tr>
				<th>姓名</th>
				<td><s:property value="bizCheckDTO.membername" /></td>
				<th>家庭编号</th>
				<td><s:property value="bizCheckDTO.familyno" /></td>
				<th>社会保险号</th>
				<td><s:property value="bizCheckDTO.ssn" /></td>
			</tr>
			<tr>
				<th>就诊医院</th>
				<td><s:property value="bizCheckDTO.hospital" /></td>
				<th>入院时间</th>
				<td><s:date name="bizCheckDTO.begintime" format="yyyy-MM-dd" /></td>
				<th>入院科室</th>
				<td><s:property value="bizCheckDTO.inDeptName" /></td>
			</tr>
			<tr>
				<th>诊断</th>
				<td colspan="3"><s:property value="bizCheckDTO.sickname" /></td>
				<th>预计费用</th>
				<td>&nbsp;</td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td>
		<table width="100%" class="t2">
			<caption>社区审核情况</caption>
			<tr>
				<th>审核意见</th>
				<td><s:if test="bizCheckDTO.checked1==0"> 不同意 </s:if> <s:if
					test="bizCheckDTO.checked1==1"> 已审核 </s:if> <s:else> 未审核 </s:else></td>
				<th>审核时间</th>
				<td><s:date name="bizCheckDTO.checktime1" format="yyyy-MM-dd" /></td>
				<th>审核人</th>
				<td><s:property value="bizCheckDTO.operator1" default="无" /></td>
			</tr>
			<tr>
				<td colspan="6">
				<div align="left"><strong>审核说明:</strong><br>
				<s:property value="bizCheckDTO.detail1" default="无" /></div>
				</td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td>
		<table width="100%" class="t2">
			<caption>街道审核情况</caption>
			<tr>
				<th>审核意见</th>
				<td><s:if test="bizCheckDTO.checked2==0"> 不同意 </s:if> <s:if
					test="bizCheckDTO.checked2==1"> 已审核 </s:if> <s:else> 未审核 </s:else></td>
				<th>审核时间</th>
				<td><s:date name="bizCheckDTO.checktime2" format="yyyy-MM-dd" /></td>
				<th>审核人</th>
				<td><s:property value="bizCheckDTO.operator2" default="无" /></td>
			</tr>
			<tr>
				<td colspan="6">
				<div align="left"><strong>审核说明:</strong><br>
				<s:property value="bizCheckDTO.detail2" default="无" /></div>
				</td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td>
		<table width="100%" class="t2">
			<caption>区县审核情况</caption>
			<tr>
				<th>审核意见</th>
				<td><s:if test="bizCheckDTO.checked3==0"> 不同意 </s:if> <s:if
					test="bizCheckDTO.checked3==1"> 已审核 </s:if> <s:else> 未审核 </s:else>
				</td>
				<th>审核时间</th>
				<td><s:date name="bizCheckDTO.checktime3" format="yyyy-MM-dd" /></td>
				<th>审核人</th>
				<td><s:property value="bizCheckDTO.operator3" default="无" /></td>
			</tr>
			<tr>
				<td colspan="6">
				<div align="left"><strong>审核说明:</strong><br>
				<s:property value="bizCheckDTO.detail3" default="无" /></div>
				</td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td>
		<table width="100%" class="t2">
			<caption>市低保中心审核情况</caption>
			<tr>
				<th>审核意见</th>
				<td><s:if test="bizCheckDTO.checked4==0"> 不同意 </s:if> <s:if
					test="bizCheckDTO.checked4==1"> 已审核 </s:if> <s:else> 未审核 </s:else></td>
				<th>审核时间</th>
				<td><s:date name="bizCheckDTO.checktime4" format="yyyy-MM-dd" />
				</td>
				<th>审核人</th>
				<td><s:property value="bizCheckDTO.operator4" default="无" /></td>
			</tr>
			<tr>
				<td colspan="6">
				<div align="left"><strong>审核说明:</strong><br>
				<s:property value="bizCheckDTO.detail4" default="无" /></div>
				</td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<th height="27" bgcolor="#FFA147">审核意见填写</th>
	</tr>
	<tr>
		<td><input type="button" value="关闭窗口" onClick="window.close()" />
		</td>
	</tr>
</table>
</body>
</html>
