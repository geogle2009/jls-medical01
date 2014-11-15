<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
			String a=request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()+"/pic/pic2";
	String f =FileHandle.getInstance().webpath2;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="com.medical.common.FileHandle"%><html>
<head>
<sj:head jqueryui="true"/>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="page/css/table-style.css" type="text/css"></link>
<title>图片</title>
<style type="text/css">
<!--
body,td,th {
	font-size: 12px;
}

body {
	margin-left: 0px;
	margin-top: 1px;
	margin-right: 0px;
	margin-bottom: 1px;
}
-->
</style>
<script language=Javascript>    
var proMaxHeight = 1200;    
var proMaxWidth = 900;
      function proDownImage(ImgD){    
        var image=new Image();    
        image.src=ImgD.src;    
        if(image.width>0 && image.height>0){    
          var rate = (proMaxWidth/image.width < proMaxHeight/image.height) ? proMaxWidth/image.width:proMaxHeight/image.height;    
          if(rate <= 1){    
            ImgD.width = image.width*rate;    
            ImgD.height =image.height*rate;    
          } else {    
            ImgD.width = image.width;    
            ImgD.height =image.height;    
          }    
        }    
      }   
    </script> 
<script type="text/javascript">
	function view(n) {
		var cimg = window.parent.frames['mainFrame'].document
				.getElementById('cimg');
		var viewpic = window.parent.frames['mainFrame'].document
				.getElementById('viewpic');
		cimg.src = '<%=a%>/'+n;
		proDownImage(cimg);
		viewpic.style.display = 'block';
		var picname = window.parent.frames['mainFrame'].document
				.getElementById('picname');
		picname.value = n;
	}
	function delfile(n){
		alert("删除图片文件:"+n);
		var arr ={"term":2,"value":n};
		$.ajax( {
			type : "post",
			url : "page/business/applysalvation/delfile.action",
			data: arr
			,
			timeout : 20000,
			error : function() {
				alert('服务器错误');
			},
			async : false,
			dataType : 'json',
			success : function(json) {
				json = eval('(' + json + ')');
				var b = document.getElementById('b'+n);
				b.style.display='none';
			}
		});
	}
</script>
</head>
<body>
<table width="95%" border="0" align="center" cellpadding="1" cellspacing="0" class="t1">
	<caption style="cursor:hand" onclick="window.location.reload();">体检表(刷新)</caption>
	<s:iterator value="healthrecordimgs">
		<tr id="b<s:property value="filename"/>">
			<td><span style="cursor: hand;"
				onclick="view('<s:property value="filename"/>')"><s:property
				value="filename" /></span>&nbsp;&nbsp;<span onclick="delfile('<s:property value="filename"/>')">删除</span></td>
		</tr>
	</s:iterator>
</table>
</body>
</html>