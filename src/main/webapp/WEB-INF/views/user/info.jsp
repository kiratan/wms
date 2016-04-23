<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
<META HTTP-EQUIV="expires" CONTENT="Wed, 26 Feb 1997 08:21:57 GMT">
 <script src="${ctx}/resources/web/userJs.js"></script> 

<title>主页</title>

<script type="text/javascript">
	
</script>
</head>
<body>

<div class="row">
	  <div class="col-md-4"><img class="img-thumbnail" src="${ctx}/resources/img/400${sessionScope.LOGINED.headImg}"></div>
	  <div class="col-md-6 col-md-offset-1">
	  		<div class="row"><h3 class="text-warning">用户名：${sessionScope.LOGINED.name}</h3></div>
			<div class="row"><h3 class="text-warning">昵称：${sessionScope.LOGINED.nickName}</h3></div>
			<div class="row"><h3 class="text-warning">性别：${sessionScope.LOGINED.sex}</h3></div>
			<div class="row"><h3 class="text-warning">邮箱：${sessionScope.LOGINED.email}</h3></div>
	 </div>
</div>

</body>
</html>
