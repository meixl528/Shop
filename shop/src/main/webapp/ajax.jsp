<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
</head>
<body>
	姓名:<input type="text" name="name">
	<button id="button" type="button">ajax提交</button>
	
	<br><br>
	
	测试SpringMVC json:
	<button id="jsonButton" type="button">ajaxJson提交</button>
	<ul id="ul">
		
	</ul>
</body>
<script>
	$("#button").click(function(){
		$.ajax({
			url:"http://192.168.10.32:8081/system/restapi/test",
			data:{
				//name : $("input[name='name']").val()
				name :"meixl",
				pass :"meixl"
			},
			dataType:"json",
			type:"post",
			cache:false,
			success:function(data){
				alert(data)
			},
			error:function(er){
				alert("error")
			}
		})
	})
	
	$("#jsonButton").click(function(){
		$.ajax({
			url:"json.do",
			data:{
			},
			dataType:"json",
			type:"post",
			cache:false,
			success:function(data){
				$("#ul").empty();
				for(var i=0;i<data.length;i++){
					$("#ul").append("<li><span>"+(i+1)+"</span><span>"+data[i].name+"</span><span>"+data[i].pass+"</span></li>");
				}
			},
			error:function(er){
				alert("error")
			}
		})
	})
	
</script>
</html>