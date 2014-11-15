<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link rel="stylesheet" href="../css/table-style.css" type="text/css"></link>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>临时救助审批表</title>
</head>
<body>
<s:form theme="simple" action="saveEmecheckapproves" method="post"
	cssStyle="font-size:12px">
	<s:hidden name="emeCheckDTO.emecheckId"></s:hidden>
	<s:hidden name="pagetype"></s:hidden>
	<table class="t1" width="101%" border="1" cellpadding="0"
		cellspacing="0">
		<caption>临时救助审批表</caption>
		<tr>
			<td width="99" height="24">家庭编号</td>
			<td width="132"><s:property value="emeCheckDTO.familyno" /></td>
			<td colspan="2">救助人姓名</td>
			<td colspan="2"><s:property value="emeCheckDTO.membername" /></td>
			<td width="141" rowspan="4">&nbsp;</td>
		</tr>
		<tr>
			<td width="99" height="24">救助人身份证号码</td>
			<td><s:property value="emeCheckDTO.paperid" /></td>
			<td width="99">性别</td>
			<td width="132"><s:property value="emeCheckDTO.sex" /></td>
			<td width="99">出生日期</td>
			<td width="132"><s:date name="emeCheckDTO.birthday"
				format="yyyy-MM-dd" /></td>
		</tr>
		<tr>
			<td width="99" height="24">户主姓名</td>
			<td><s:property value="emeCheckDTO.masterName" /></td>
			<td width="99">与户主关系</td>
			<td><s:property value="emeCheckDTO.relmaster" /></td>
			<td colspan="2">&nbsp;</td>
		</tr>
		<tr>
			<td width="99" height="24">联系方式</td>
			<td><s:property value="emeCheckDTO.linkmode" /></td>
			<td width="99">救助金额</td>
			<td><s:if test="pagetype==1">
				<s:property value="emeCheckDTO.salmoney" />
			</s:if> <s:if test="pagetype==2">
				<s:textfield name="emeCheckDTO.salmoney" />元
			</s:if></td>
			<td colspan="2">&nbsp;</td>
		</tr>
		<tr>
			<td width="99" height="24">家庭地址</td>
			<td colspan="6"><s:property value="emeCheckDTO.address" /></td>
		</tr>
		<tr>
			<td width="99" height="24">救助对象类别</td>
			<td colspan="6"><s:if test="emeCheckDTO.assistType==10">
			城市低保
			</s:if> <s:if test="emeCheckDTO.assistType==11">
			分类施保
			</s:if> <s:if test="emeCheckDTO.assistType==00">
			不参与保障
			</s:if></td>
		</tr>
		<tr>
			<td width="99" height="24">救助方式</td>
			<td colspan="6">临时应急医疗救助</td>
		</tr>
		<tr>
			<td width="99" rowspan="2">应急救助原因</td>
			<td height="50" colspan="6"><s:property
				value="emeCheckDTO.interview" /></td>
		</tr>
		<tr>
			<td height="50" colspan="5">本人签字：</td>
			<td height="50"><s:date name="emeCheckDTO.interviewtime"
				format="yyyy-MM-dd" /></td>
		</tr>
		<tr>
			<td width="99" rowspan="3">街道初审意见</td><td colspan="6">
			<s:if test="pagetype==1">
			审批意见：
				<s:select theme="simple" value="emeCheckDTO.resultofstreet"
					name="emeCheckDTO.resultofstreet"
					list="#{'0':'未填写','1':'同意','2':'不同意'}" label="审批结果" listKey="key"
					listValue="value">
				</s:select>
			</s:if> <s:if test="pagetype==2">
				<s:if test="emeCheckDTO.resultofstreet==0">
			未审批
			</s:if>
				<s:if test="emeCheckDTO.resultofstreet==1">
			同意
			</s:if>
				<s:if test="emeCheckDTO.resultofstreet==2">
			不同意
			</s:if>
			</s:if>&nbsp;&nbsp;&nbsp;&nbsp;
			</td></tr>
			<tr>
			<td height="50" colspan="6"><s:if test="pagetype==1">
				<s:textarea theme="simple" name="emeCheckDTO.comofstreet" rows="5"
					cssStyle="width:100%" />
			</s:if> <s:if test="pagetype==2">
				<s:property value="emeCheckDTO.comofstreet" />
			</s:if></td>
		</tr>
		<tr>
			<td height="24" colspan="5"> 低保所长：<s:property
				value="emeCheckDTO.auditorofstreet" /></td>
			<td height="24"><s:date name="emeCheckDTO.streetapptime"
				format="yyyy-MM-dd" /></td>
		</tr>
		<tr>
			<td width="99" rowspan="3">区民政部门审批意见</td>
			<td colspan="6">
			<s:if test="pagetype==2">
				<s:select theme="simple" value="emeCheckDTO.resultofareg"
					name="emeCheckDTO.resultofareg"
					list="#{'0':'未填写','1':'同意','2':'不同意'}" label="审批结果" listKey="key"
					listValue="value">
				</s:select>
			</s:if> <s:if test="pagetype==1">
				<s:if test="emeCheckDTO.resultofareg==0">
			未审批
			</s:if>
				<s:if test="emeCheckDTO.resultofareg==1">
			同意
			</s:if>
				<s:if test="emeCheckDTO.resultofareg==2">
			不同意
			</s:if>
			</s:if>&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
			</tr>
			<tr>
			<td height="50" colspan="6"><s:if test="pagetype==2">
				<s:textarea theme="simple" name="emeCheckDTO.comofareg" rows="5"
					cssStyle="width:100%" />
			</s:if> <s:if test="pagetype==1">
				<s:property value="emeCheckDTO.comofareg" />
			</s:if></td>
		</tr>
		<tr>
			<td height="24" colspan="5">低保中心主任：<s:property
				value="emeCheckDTO.auditorofareg" />
				主管领导：
				<s:if test="pagetype==2">
				<s:textfield name="emeCheckDTO.aregmaster"></s:textfield>
				</s:if>
				<s:if test="pagetype==1">
				<s:property value="emeCheckDTO.aregmaster"/>
				</s:if>
				</td>
			<td height="24"><s:date name="emeCheckDTO.aregapptime"
				format="yyyy-MM-dd" /></td>
		</tr>
	</table>
	<p align="center">
		<s:submit value="保存"></s:submit>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<button onclick="window.close()">关闭</button>
	</p>
</s:form>
</body>
</html>