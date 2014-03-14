<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>Ajax</title>    
    <script type="text/javascript">
    	/* 创建xmlHttp对象 */
    	function ajaxFunction(){
    		var xmlHttp;
    		try{//FireFox, Opera 8.0+ Safari
    			xmlHttp = new XMLHttpRequest();
    		}catch (e) {
				try{//IE
					xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
				}catch (e) {
					try{
						xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
					}catch(e){}
				}
			}
			return xmlHttp;
    	}
    	
    	window.onload = function(){
    		document.getElementById("ok").onclick = function(){
    		//alert("Good");
    		//1.获取xmlHttpRequest对象
    		var xmlReq = ajaxFunction();
    		
    		//4.接收服务器端的响应
    		//XMLHttpRequest 对象中有个readyState属性
    		//readyState属性表示Ajax请求的当前状态，它的值用数字代表
    		//0代表未初始化：还没有调用open方法
    		//1代表正在加载：open已调用，但send还没被调用
    		//2代表已加载完毕：sned被调用，请求已经开始
    		//3代表交互中：服务器正在发送响应
    		//4代表完成：响应发送完毕
    		//每次readyState值得改变，都会触发onreadystatechange事件
    		/* 
    		xmlReq.onreadystatechange = function(){
    			alert(xmlReq.readyState);
    		};
    		 */
    		xmlReq.onreadystatechange = function(){
    			if(xmlReq.readyState == 4){
    				//200---处理成功 或 304---文件没有被修改
    				if(xmlReq.status==200 || xmlReq.status==304){
    					//alert("xxx");
    					alert(xmlReq.responseText);
    				}
    			}
    		};
    		
    		
    		//2.打开与服务器的连接
    		//open(method, url, asynch)
    		xmlReq.open("get", "${pageContext.request.contextPath}/servlet/TestAjax?timeStamp="+ new Date().getTime(), true);
    		
    		//3.发送数据
    		xmlReq.send(null);
		
    		};
    		
    	};
    </script>
  </head>
  
  <body>
    	<input type="button" name="ok" id="ok" value="testAjax">
  </body>
</html>
