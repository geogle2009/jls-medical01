<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
			String a=request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()+"/pic/pic1";
	String f =FileHandle.getInstance().webpath1;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="com.medical.common.FileHandle"%><html>
<head>

<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="page/css/table-style.css" type="text/css"></link>
<title>核对健康档案</title>
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
      var proMaxHeight = 594;    
      var proMaxWidth = 420;
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
</script>

</head>
<body>
<table width="95%" border="0" align="center" cellpadding="1" cellspacing="0" class="t1">
	<caption style="cursor:hand" onclick="window.location.reload();">未核对健康档案</caption>
	<s:iterator value="healthrecordimgs">
		<tr id="b<s:property value="filename"/>">
			<td><span style="cursor: hand;"
				onclick="view('<s:property value="filename"/>')"><s:property
				value="filename" /></span></td>
		</tr>
	</s:iterator>
</table>
</body>
</html>