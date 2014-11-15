<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html
	style="filter:progid:DXImageTransform.microsoft.gradient(gradienttype=0,startColorStr=white,endColorStr=#387fd1);">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>XML生成树状菜单</title>
<!--<script language="JavaScript" type="text/javascript"
	src="js/menu-tree.js"></script>
-->
<!--<script type="text/javascript" language="javascript" defer="defer">
	//将处理过的XML数据，插入到页面的相应位置
	var d = document.getElementById("TreeMenu");
	d.innerHTML = BuilderTree(rootNode);
	document.getElementById("info").value = d.innerHTML;
</script>
-->
<link rel="StyleSheet" href="css/dtree.css" type="text/css" />
<script type="text/javascript" src="js/dtree.js"></script>
<script type="text/javascript"> 
	var xmlDoc = null;
	var d = new dTree('d');
	d.add(0, -1, '医疗救助系统');
	function initMenu() {
		if (window.ActiveXObject) {//IE浏览器
			xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
		} else if (document.implementation
				&& document.implementation.createDocument) { //其它浏览器
			xmlDoc = document.implementation.createDocument("", "", null);
		}
		xmlDoc.async = "false";
		xmlDoc.load("js/menu.xml");
		rootNode = xmlDoc.lastChild;
		buildermenu(rootNode, 0, 0);
		//document.write(d);
		var TreeMenu = document.getElementById("TreeMenu");
		TreeMenu.innerHTML = d;
		//document.getElementById("info").value = d.toString();
	}

	var seq = 0;
	function buildermenu(parentNode, parent) {
		var nodes = parentNode.childNodes;
		for ( var i = 0; i < nodes.length; i++) {
			seq++;
			//当该节点没下级节点时
			var nodename = nodes[i].getAttribute("name") != null ? nodes[i]
					.getAttribute("name") : '';
			var url = nodes[i].getAttribute("url") != null ? nodes[i]
					.getAttribute("url") : '';
			var target = nodes[i].getAttribute("target") != null ? nodes[i]
					.getAttribute("target") : '';
			d.add(seq, parent, nodename, url, '', target);
			if (nodes.length > 0) {
				buildermenu(nodes[i], seq);
			}
		}
		return d;
	}
</script>
<link href="css/menu-style.css" rel="stylesheet" type="text/css" />
</head>
<body onload="initMenu()" style='OVERFLOW-Y:auto;OVERFLOW-X:hidden'>
<div id="TreeMenu" style="width:100%"></div>
<!--
<textarea id="info"></textarea>
<input type="button" name="test" onclick="initMenu()" />
-->
</body>
</html>
