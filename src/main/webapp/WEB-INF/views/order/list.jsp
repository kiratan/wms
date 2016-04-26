<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
<META HTTP-EQUIV="expires" CONTENT="Wed, 26 Feb 1997 08:21:57 GMT">

<title>主页</title>

<script type="text/javascript">
	
</script>
</head>
<body>
	<div>
		<!-- Button trigger modal -->
		<button id="add" class="btn btn-primary btn" data-toggle="modal"
			data-target="#myModal">添加</button>
	</div>
	<div class="table-responsive col-md-offset-1 col-sm-10 ">
		<table class="table table-hover success center">
			<thead>
				<tr>
				<th>#</th>
	            <th>名称</th>
	            <th>操作</th>
				</tr>
			</thead>
			<tbody>

				<c:forEach items="${list}" var="list">
					<tr class="table-bordered">
						  	<td class="table-bordered id">${list.id}</td>
				            <td class="table-bordered name">${list.name}</td>
							<td class="danger table-bordered">
							<a href="./del/${list.id}"> <span class="glyphicon glyphicon-trash"> </span> </a> | 
							<a href="./lock/${list.id}"><span class="glyphicon glyphicon-lock"> </span></a> | 
				            <a class="update" data-toggle="modal" data-target="#myModal"  href="#">
				             	<span class="glyphicon glyphicon-pencil"></span> 
				            </a>
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
					<h4 class="modal-title" id="myModalLabel">添加</h4>
				</div>
				   <form id="addForm" role="form" action="${ctx}/order/addIngredientType" method="post">
					<div class="modal-body" >
						<!-- 弹框内容  -->
					 	<div class="input-group">
						 <span class="input-group-addon alert-info form-control"> 名称</span>
						<input type="text" class="form-control" name="name" placeholder="请输入">
						</div>
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