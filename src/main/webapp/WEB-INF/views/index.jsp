<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %> 
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<title>主页</title>
</head>
<body id="context" data-toggle="context" data-target="#context-menu">
 
 <div class="table-responsive col-md-offset-1 col-sm-10 ">
		<table class="table table-hover success center">
			<thead>
				<tr>
				<th>#</th>
	            <th>昵称</th>
	            <th>性别</th>
	            <th>用户名</th>
	            <th>密码</th>
	            <th>邮箱</th>
	             <th>状态</th>
	             <th>角色列表</th>
				</tr>
			</thead>
			<tbody>

				<c:forEach items="${list}" var="list">
					<tr class="table-bordered">
						  	<td class="table-bordered id">${list.id}</td>
				            <td class="table-bordered nickName">${list.nickName}</td>
				             <td class="table-bordered sex">${list.sex}</td>
				            <td class="table-bordered name">${list.name}</td>
				            <td class="table-bordered pass">${list.pass}</td>
				            <td class="table-bordered email">${list.email}</td>
				            <td class="table-bordered">${list.state}</td>
							<td class="table-bordered">
								<select multiple class="form-control" size="3">
										<c:forEach items="${list.roles}" var="role">
											<option class="small">${role.name}</option>
										</c:forEach>
								</select>
							</td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
	</div>
 
 
</body>
</html>
