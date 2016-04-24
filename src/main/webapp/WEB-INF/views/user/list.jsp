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
	<div>
		<!-- Button trigger modal -->
		<button id="add" class="btn btn-primary btn" data-toggle="modal"
			data-target="#myModal">添加用户</button>
	</div>
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
	             <th>操作</th>
				</tr>
			</thead>
			<tbody>

				<c:forEach items="${list}" var="list">
					<tr class="table-bordered">
						  	<td class="table-bordered id">${list.id}</td>
				            <td class="table-bordered nickName">${list.nickName}</td>
				             <td class="table-bordered sex">${list.sex}</td>
				            <td class="table-bordered name">${list.name}</td>
				            <td class="table-bordered pass">******</td>
				            <td class="table-bordered email">${list.email}</td>
				            <td class="table-bordered">${list.state}</td>
							<td class="table-bordered">
								<select multiple class="form-control" size="3">
										<c:forEach items="${list.roles}" var="role">
											<option class="small">${role.name}</option>
										</c:forEach>
								</select>
							</td>

						<td class="danger table-bordered">
						<a href="./del/${list.id}"> <span class="glyphicon glyphicon-trash"> </span> </a> | 
						<a href="./lock/${list.id}"><span class="glyphicon glyphicon-lock"> </span></a> | 
			             <a class="update" data-toggle="modal" data-target="#myModal"  href="#">
			             <span class="glyphicon glyphicon-pencil"></span> </a>
						</td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
	</div>

	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"  aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">添加用户</h4>
				</div>
				   <form id="addForm" role="form" action="../user/add" method="post">
				   <input class='idForUpdate' type='hidden' name='id' value="0">
				   <input type="hidden" id="roleIds" name="roleIds">
					<div class="modal-body" >
					<div class="alert alert-danger errMsg small"></div>
						<!-- 弹框内容  -->
							 <div class="form-group"  id="nickNameErr">
							 <div class="input-group">
							 <span class="input-group-addon alert-info"> 昵&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称</span>
							<input type="text" class="form-control" name="nickName" id="nickName" placeholder="请输入你的用户名">
							</div>
						</div>
						<div class="form-group">
							 <div class="input-group"  id="nameErr">
							 <span class="input-group-addon alert-info">用&nbsp;&nbsp;户&nbsp;&nbsp;名</span>
							<input type="text" class="form-control" name="name" id="name" placeholder="请输入你的用户名">
							</div>
						</div>
						<div class="form-group"  id="passErr">
							<div class=" input-group" >
							<span class="input-group-addon alert-info">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码</span>
							<input type="text"  class="form-control" name="pass" id="pass"  placeholder="请输入你的密码">
							</div>
						</div>
							<div class="form-group"  id="pass2Err">
							<div class="input-group" >
							<span class="input-group-addon alert-info">重复密码</span> 
							<input type="text"  class="form-control" name="pass2" id="pass2"  placeholder="请输入你的密码">
							</div>
						</div>
<!-- 						<div class="form-group" id="emailErr">
							<div class="input-group" >
							<span class="input-group-addon alert-info">邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱</span>
							 <input type="email" class="form-control" name="email" id="email" placeholder="请输入你的邮箱">
							 </div>
						</div> -->
						<div class="form-group">
						<div class="input-group" >
						<span class="input-group-addon alert-info">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别</span>
						<div class="btn-group col-sm-8" data-toggle="buttons">
						  <label class="btn alert-warning active ">
						    <input type="radio" name="sex" id="sex1" value="男" checked>&nbsp;&nbsp; &nbsp;&nbsp;男&nbsp;&nbsp;&nbsp;&nbsp;
						  </label>
						  <label class="btn alert-danger">
						    <input type="radio" name="sex" id="sex2" value="女"> &nbsp;&nbsp;&nbsp;&nbsp;女&nbsp;&nbsp;
						  </label>
<!-- 						  <label class="btn alert-info">
						    <input type="radio" name="sex" id="sex3" value="人妖">&nbsp;&nbsp;&nbsp;&nbsp;人妖&nbsp;&nbsp;
						  </label> -->
						</div>
						</div>
							</div>
						<div class="alert alert-warning">
							<!-- 内容 -->
							<span>角色列表</span>
							<br /><br />
								<div  class="btn-group roles" data-toggle="buttons">
								</div>
						<!-- 内容 -->
						</div>

						<!-- 弹框内容  -->
					</div>

					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<button id="enter" type="submit" class="btn btn-info pull-right disabled">添加</button>
					</div>

				</form>
			</div>
		</div>
	</div>

</body>
</html>
