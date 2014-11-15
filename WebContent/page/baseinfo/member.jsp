<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> 
<html>
<head>
<link rel="stylesheet" href="../css/table-style.css" type="text/css"></link>
<!--
author:Administrator
create time:2009-9-1
-->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>成员信息</title>
</head>
<body>
<form action="family.jsp" method="post" name="inputform">
<table align="left" width="99%" class="t1" border="0" cellpadding="0" cellspacing="0">
  <tr><th colspan="7">查询救助对象</th></tr>
  <tr>
    <td width="80">成员姓名</td>
    <td width="120"><input type="text" name="textfield22"></td>
    <td width="80">与户主关系</td>
    <td width="120"><select name="select3">
      <option value="1">夫妻</option>
      <option value="0">父子</option>
      <option value="0">祖孙</option>
    </select></td>
    <td width="80">社会保险号</td>
    <td width="120"><input type="text" name="textfield62"></td>
    <td rowspan="8">&nbsp;</td>
  </tr>
  <tr>
    <td width="80">证件类别</td>
    <td width="120"><select name="select">
      <option value="1">身份证</option>
      <option value="0">军人证</option>
      <option value="0">其他</option>
    </select></td>
    <td width="80">证件号码</td>
    <td width="120"><input type="text" name="textfield6"></td>
    <td width="80">民族</td>
    <td width="120"><select name="select4">
      <option value="1">汉族</option>
    </select></td>
    </tr>
  <tr>
    <td width="80">出生日期</td>
    <td width="120"><input type="text" name="textfield3"></td>
    <td width="80">性别</td>
    <td width="120"><select name="select2">
      <option value="1">男</option>
      <option value="0">女</option>
    </select></td>
    <td width="80">婚姻状况</td>
    <td width="120"><select name="select5">
      <option value="1">未婚</option>
      <option value="0">已婚</option>
    </select></td>
    </tr>
  <tr>
    <td width="80">户口类型</td>
    <td width="120"><select name="select7">
      <option value="1">人户分离</option>
    </select></td>
    <td width="80">户口性质</td>
    <td width="120"><select name="select8">
      <option value="1">非农户口</option>
    </select></td>
    <td width="80">参保情况</td>
    <td width="120"><select name="select6">
      <option value="1">社会保险</option>
      <option value="0">失业保险</option>
      <option value="0">职工医疗</option>
      <option value="0">居民医疗保险</option>
      <option value="0">新型农村合作医疗</option>
    </select></td>
    </tr>
  <tr>
    <td width="80">劳动能力</td>
    <td width="120"><select name="select12">
      <option value="1">完全劳动能力</option>
      <option value="0">部分劳动能力</option>
      <option value="0">丧失劳动能力</option>
    </select></td>
    <td width="80">健康状况</td>
    <td width="120"><select name="select11">
      <option value="1">健康</option>
      <option value="0">患病</option>
    </select></td>
    <td width="80">患病类型</td>
    <td width="120"><select name="select13">
      <option value="1">尿毒症</option>
      <option value="0">白血病</option>
      <option value="0">肝炎</option>
    </select></td>
    </tr>
  <tr>
    <td>残疾情况</td>
    <td><select name="select9">
      <option value="1">肢体残疾</option>
      <option value="0">智力残疾</option>
    </select></td>
    <td>残疾等级</td>
    <td><select name="select10">
      <option value="1">一级</option>
      <option value="0">二级</option>
      <option value="0">三级</option>
    </select></td>
    <td>患病名称</td>
    <td><input type="text" name="textfield722"></td>
  </tr>
  <tr>
    <td>工作情况</td>
    <td><input type="text" name="textfield723"></td>
    <td>工作单位</td>
    <td><input type="text" name="textfield724"></td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td width="80">&nbsp;</td>
    <td width="120">&nbsp;</td>
    <td width="80">&nbsp;</td>
    <td width="120">&nbsp;</td>
    <td width="80">&nbsp;</td>
    <td width="120">&nbsp;</td>
    </tr>
  <tr>
    <td width="80">户口所在地</td>
    <td colspan="5"><input type="text" name="textfield7"></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td width="80">联系方式</td>
    <td colspan="5"><input type="text" name="textfield72"></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td colspan="7">
    <button>保存</button>
   	&nbsp;&nbsp;&nbsp;&nbsp;<button onclick="history.go(-1)">返回</button>
    </td>
  </tr>
</table>
</form>
</body>
</html>