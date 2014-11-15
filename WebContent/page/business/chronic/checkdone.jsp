<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<sj:head jqueryui="true" />
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="page/css/table-style.css" type="text/css"></link>
<title>核对页面</title>
</head>
<script type="text/javascript">
	function check(){
		if(''!=$('#memberid')[0].value&&''!=$('#ssn')[0].value){
			var arr ={"personDTO.ssn":$('#ssn')[0].value,"personDTO.memberId":$('#memberid')[0].value,"personDTO.masterName":$('#picname')[0].value,"personDTO.rprkind" : $('#n')[0].value};
			$.ajax( {
				type : "post",
				url : "page/business/chronic/checkhealthrecord.action",
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
					
					var filename=json['filename'];
					var flag=json['flag'];

					//var a = window.parent.frames['leftFrame'].document.getElementById('a'+filename);
					var b = window.parent.frames['leftFrame'].document.getElementById('b'+filename);

					if('1'==flag){
					//	a.style.display='none';
						b.style.display='none';
						$('#cimg')[0].src="";
						$('#viewpic')[0].style.display='none';
					}
					
					alert(json['str']);
				}
			});
		};
	}
	function checkssn() {
		var ssn = $('#ssn')[0].value;
		if ('' == ssn) {
			alert('请输入社会保险号!');
		} else {
			var arr ={"personDTO.ssn":ssn};
			$.ajax( {
				type : "post",
				url : "page/business/chronic/querypersonbyssn.action",
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
					if('undefined'== typeof(json['memberid'])){
						var memberinfo = $('#memberinfo')[0];
						memberinfo.innerHTML='';
						$('#memberid')[0].value='';
						$('#ssn')[0].value='';
						$('#check')[0].style.display='none';
						alert('此居民不存在!');
					}else{
						var memberinfo = $('#memberinfo')[0];
						memberinfo.innerHTML='姓名：'+json['membername']+'&nbsp;&nbsp;&nbsp;&nbsp;身份证号：'+json['paperid']+'&nbsp;&nbsp;&nbsp;&nbsp;社会保险号:'+json['ssn']+'';
						$('#memberid')[0].value=json['memberid'];
						$('#ssn')[0].value=json['ssn'];
						$('#check')[0].style.display='block';
					}
				}
			});
		}
	}
</script>
<script language=Javascript>    
      var proMaxHeight = 594;    
      var proMaxWidth = 420;
      function proDownImage(ImgD){    
        var image=new Image();    
        image.src=ImgD.src;    
        if(image.width>0 && image.height>0){    
          var rate = (proMaxWidth/image.width < proMaxHeight/image.height) ? proMaxWidth/image.width:proMaxHeight/image.height;    
          if(rate <= 1){    
        	  ImgD.width = image.width;   
        	  ImgD.height =image.height;  
          } else {    
            ImgD.width = image.width;    
            ImgD.height =image.height;    
          }    
        }    
      }   
    </script> 
<body>
<br />
<div>社会保险号： <s:textfield id="ssn" name="ssn"></s:textfield>
&nbsp;&nbsp;&nbsp;&nbsp;
<button type="button" onclick="checkssn()">查询</button>&nbsp;&nbsp;&nbsp;&nbsp;
</div>
<br />
<div id="memberinfo">
</div>
<br/>
<button id="check" style="display:none" type="button" onclick="check()">核对并且保存</button>
<s:hidden id="memberid" name="personDTO.memberId"></s:hidden>
<s:hidden id="ssn" name="personDTO.ssn"></s:hidden>
<s:hidden id="picname" name="personDTO.masterName"></s:hidden>
<br/>
<div id="viewpic" style="display:none">当前选择健康卡片:<br/><br/>
填写图片名称:<input type="text" id="n" value=""></input>
<img id="cimg" src="" onclick="proDownImage(this)"/></div>
</body>
</html>