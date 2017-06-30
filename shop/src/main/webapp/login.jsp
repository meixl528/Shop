<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="js/jquery.cookie.js"></script>
<title>Insert title here</title>
</head>
<body>
	<form id="form" action="login.do" method="post">
		姓名:<input type="text" name="name" value="admin">
		密码:<input type="text" name="pass" value="admin">
		<button type="submit">登录</button>
	</form>
</body>
<script>
	$(function(){
		console.log("cookies = "+$.cookie("userName"));
		if($.cookie("userName")){
			$("input[name='name']").val($.cookie("userName"));
			// $("#form").submit();
		}
	})
</script>

<br>

<form id="formJob" action="create.do" method="post">
		新建一个job:<input type="text" name="jobClassName" value="com.hand.hap.job.examples.HelloJob">
		<button type="submit">登录</button>
</form>

<!-- jobCreateDto = JobCreateDto [jobClassName=com.hand.hap.job.examples.HelloJob, cronExpression=null, 
start=Mon Mar 20 00:00:00 CST 2017, 
end=Thu Mar 30 00:00:00 CST 2017, 
repeatCount=5, 
repeatInterval=300, 
jobDatas=[com.hand.hap.job.dto.JobData@23740600[name=job_internal_notification,value=false,requestId=-1,programId=-1], 
com.hand.hap.job.dto.JobData@6157852b[name=job_internal_emailAddress,value=,requestId=-1,programId=-1]]] -->
</html>