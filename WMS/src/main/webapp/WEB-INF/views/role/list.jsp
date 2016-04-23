<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<script src="${ctx}/resources/web/roleJs.js"></script>

<title>主页</title>

<script type="text/javascript">
	
</script>
</head>
<body>
	<div>
		<!-- Button trigger modal -->
		<button id="add" class="btn btn-primary btn" data-toggle="modal"
			data-target="#myModal">添加角色</button>
	</div>
	<div class="table-responsive col-md-offset-1 col-sm-10 ">
		<table class="table table-hover success center">
			<thead>
				<tr>
					<th>#</th>
					<th>角色名称</th>
					<th>权限</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>

				<c:forEach items="${list}" var="role">
					<tr class="table-bordered">
						<td class="table-bordered id">${role.id}</td>
						<td class="table-bordered name">${role.name}</td>
						<td class="table-bordered">
							<select multiple class="form-control" size="3">
									<c:forEach items="${role.permissions}" var="permission">
										<option class="small">${permission.name}</option>
									</c:forEach>
							</select>
						</td>
						<td class="table-bordered">
							${role.state}
						</td>

						<td class="danger table-bordered">
						<a href="./del/${role.id}"> <span class="glyphicon glyphicon-trash"> </span> </a> | 
						<a href="./lock/${role.id}"><span class="glyphicon glyphicon-lock"> </span></a> | 
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
					<h4 class="modal-title" id="myModalLabel">添加角色</h4>
				</div>
				<form id="addForm" role="form" action="${ctx}/role/add" method="post">
					<div class="modal-body">
						<!-- 弹框内容  -->
						<!--id -->
						<input class="idForUpdate" type="hidden" name="id">
						<!--权限id -->
						<input type="hidden" id=perIds name="perIds">
						<div class="input-group">
							<span class="input-group-addon alert-info">角色名称</span>
							 <input type="text" class="form-control " id="name" name="name" placeholder="角色名称"> 
								 <span class="input-group-addon alert-warning"></span>						
						</div>
						<br />
						<div class="input-group">
							<div class="panel-group" id="accordion" >
									<div id="perPanel" class="panel panel-info"  hidden>
										<div class="perInfo" data-toggle="collapse" data-parent="#accordion" href="">
											<div class="panel-heading">
												<h3 class="panel-title ">
													<!-- 标题 -->
													权限类型:
													<!-- 标题 -->
												</h3>
											</div>
										</div>
										<div id="" class="panel-collapse collapse">
											<div class="panel-body alert-warning">
												<!-- 内容 -->
												<div class="btn-group " data-toggle="buttons">
												<%-- 	<c:forEach items="${map.value}" var="per"> --%>
														<label class="btn alert-info perlable" id=""> </label>
													<%-- </c:forEach> --%>
												</div>
												<!-- 内容 -->
											</div>
										</div>
									</div>
									
									<div id="autoDiv">  </div>
							</div>
						</div>
						<br />


						<!-- 弹框内容  -->
					</div>

					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<button id="enter" type="submit" class="btn btn-info pull-right">添加</button>
					</div>

				</form>
			</div>
		</div>
	</div>

</body>
</html>
