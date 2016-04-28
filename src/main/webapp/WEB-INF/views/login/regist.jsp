<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>登录</title>
</head>
<body>

<div class="col-md-offset-4 col-sm-3 wrapper text-center"  >
sss
	<div>
		<span class="glyphicon glyphicon-fire info" aria-hidden="true" style="font-size: 120px;color: #000;"></span>
	</div>
	<%-- <img  class="img-circle" id="headImg" src="${ctx}/resources/img/defaultHead.png"> --%>
	<br/><br/>
	<form class="form-horizontal" role="form"  action="${ctx}/login/loging" method="post">
		<div class="form-group" id="regForm">
		<div class="input-group">
			<span class="input-group-addon alert-info">
				<span class="glyphicon  glyphicon-user"></span>
			</span>
			<input type="text" required="required" class="form-control"  name="name" id="name" placeholder="账号">
		</div>
		</div>
		<div class="form-group">
		<div class="input-group">
			<span class="input-group-addon alert-info"> 
				<span class="glyphicon glyphicon-lock"></span> 
			</span>
			<input type="password"  required="required" class="form-control"   name="pass"  id="pass"  placeholder="密码">
		</div>
		</div>
		<div class="form-group">
			<button type="submit" class="btn btn-info" id="login" style="padding-left: 50px;padding-right: 50px">登录</button>
		</div>
	</form>
</div>
</body>
</html>
