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
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache, must-revalidate">
<meta http-equiv="expires" content="0">
<title>审核信息保存</title>
<link type="text/css" href="../js/themes/base/jquery.ui.all.css"
	rel="stylesheet" />
<script type="text/javascript" src="../js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="../js/ui/jquery.ui.core.js"></script>
<script type="text/javascript" src="../js/ui/jquery.ui.widget.js"></script>
<script type="text/javascript" src="../js/ui/jquery.ui.datepicker.js"></script>
<script type="text/javascript"
	src="../js/ui/i18n/jquery.ui.datepicker-zh-CN.js"></script>
<script type="text/javascript">
	$(function() {
		$("#checktime").datepicker(); 
	});
	</script>

<style type="text/css">
.tacss {
	width: 100%;
}
</style>
<script type="text/javascript"> 
//var args=window.dialogArguments;
//alert("传来的参数：" + window.dialogArguments);
//alert("接到的参数:"+args.bizid);
//window.returnValue="返回的结果";
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
		<td><s:form action="saveBizcheck.action" method="post"
			theme="simple">
			<s:hidden name="bizCheckDTO.bizId"></s:hidden>
			<s:hidden name="bizCheckDTO.bizcheckId"></s:hidden>
			<s:hidden name="bizCheckDTO.level"></s:hidden>
			<s:hidden name="bizCheckDTO.smsState"></s:hidden>
			<s:hidden name="bizCheckDTO.estimate"></s:hidden>
			<table width="100%" class="t1">
				<tr>
					<th>审核意见</th>
					<td><s:select list="#{1:'同意救助',0:'不同意救助'}"
						name="bizCheckDTO.checked" /></td>
					<th>审核时间</th>
					<td><input type="text" readonly="readonly" id="checktime"
						name="bizCheckDTO.checktime"
						value="<s:date name="bizCheckDTO.checktime" format="yyyy-MM-dd"/>" />
					</td>
					<th>审核人</th>
					<td><s:textfield name="bizCheckDTO.operator"></s:textfield></td>
				</tr>
				<tr>
					<td colspan="6"><s:textarea name="bizCheckDTO.detail"
						cssClass="tacss"></s:textarea></td>
				</tr>
				<tr>
					<td colspan="6"><s:submit value="保存审核意见"></s:submit> <input
						type="button" value="关闭窗口" onClick="window.close()" /> <!--  <input
						type="button" value="查看源代码"
						onClick="javascript:clipboardData.setData('text',document.documentElement.innerHTML);" />-->
					</td>
				</tr>
			</table>

		</s:form></td>
	</tr>
</table>
</body>
</html>
