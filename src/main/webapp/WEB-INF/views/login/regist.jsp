<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>登录</title>

<script src="${ctx}/resources/ui/js/MD5.js"></script>

<script type="text/javascript">

	$(function() {
		
		var tipsStart="<div class=\"alert alert-danger valide small fade in\">";
		var tipsEnd="</div>";
		$("#regSubmit").click(function() {
			var nickName = $('#nickNameReg').val();
			var name = $('#nameReg').val();
			var pass = $('#passReg').val();
			var pass2 = $('#pass2Reg').val();
			var email = $('#emailReg').val();
			var sex=$('input:radio:checked').val();
			
          $.ajax({  
            url:"./checkRegAjax",  
            type:'post',  
            data:{"nickName":nickName,"name":name,"pass":hex_md5(pass),"pass2":hex_md5(pass2),"email":email,"sex":sex},
            dataType:'json',  
            success:function(map,status){  
            	
            	 $(".alert.alert-danger.valide").remove();
            	for(var msg in map){
            		
	             	$( '#' + msg ).append( tipsStart + map[msg] + tipsEnd ); 
            		
            		if ( msg == "regForm" ) {
                    	$('#myModal').modal('hide');
                    	$('#name').val(name);
                    	$('#pass').val(pass);
            		}  
            		
            		return;
            	}
             },  
            error:function(xhr,textStatus,errorThrown){  
            	
            	alert(erronThrown);
            }  
        });   

		});
		
		$("#login").click(function(){

			$('#pass').val(hex_md5($('#passInput').val()));
			
		 });
		
	});
</script>
</head>
<body>

	<div class="col-md-offset-4 col-sm-3 wrapper text-center"  >

	<img  class="img-circle" id="headImg" src="${ctx}/resources/img/defaultHead.png">
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
				<input type="password"  required="required" class="form-control"   name="passInput"  id="passInput"  placeholder="密码">
				<input type="hidden"  required="required" class="form-control"   name="pass"  id="pass"  placeholder="密码">
		</div>
		</div>
<%-- 		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<div class="checkbox" onclick="">
					<label> <input type="checkbox">记住我 ${msg}</label>
				</div>
			</div>
		</div> --%>
		<div class="form-group">
				<button type="submit" class="btn btn-info" id="login" style="padding-left: 50px;padding-right: 50px">登录</button>
<!-- 				<button id="enter" class="btn btn-info"
					data-toggle="modal" data-target="#myModal">注册</button> -->
		</div>
	</form>
	</div>


	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content alert-info">
				<div class="modal-header alert-info">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title alert-info" id="myModalLabel">注 册</h4>
				</div>
				<div class="modal-body text-center">
					<!-- <form class="form-horizontal" role="form"  action="#" method="post">../login/regist 
					 -->	
					 <div class="form-horizontal" >
					 <div class="form-group"  id="nickNameErr">
							 <div class="input-group">
							 <span class="input-group-addon alert-info"> 昵&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称</span>
							<input type="text" class="form-control" name="nickName" id="nickNameReg" placeholder="请输入你的用户名">
							</div>
						</div>
						<div class="form-group" id="nameErr">
							 <div class="input-group" >
							 <span class="input-group-addon alert-info">用&nbsp;&nbsp;户&nbsp;&nbsp;名</span>
							<input type="text" class="form-control" name="name" id="nameReg" placeholder="请输入你的用户名">
							</div>
						</div>
						<div class="form-group"  id="passErr">
							<div class=" input-group" >
							<span class="input-group-addon alert-info">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码</span>
							<input type="password"  class="form-control" name="pass" id="passReg"  placeholder="请输入你的密码">
							</div>
						</div>
							<div class="form-group"  id="pass2Err">
							<div class="input-group" >
							<span class="input-group-addon alert-info">重复密码</span> 
							<input type="password"  class="form-control" name="pass2" id="pass2Reg"  placeholder="请输入你的密码">
							</div>
						</div>
						<div class="form-group" id="emailErr">
							<div class="input-group" >
							<span class="input-group-addon alert-info">邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱</span>
							 <input type="email" class="form-control" name="email" id="emailReg" placeholder="请输入你的邮箱">
							 </div>
						</div>
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
						  <label class="btn alert-info">
						    <input type="radio" name="sex" id="sex3" value="人妖">&nbsp;&nbsp;&nbsp;&nbsp;人妖&nbsp;&nbsp;
						  </label>
						</div>
						</div>
							</div>
				<div class="modal-footer">
					<button type="submit"  id="regSubmit" class="btn btn-info">注册</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
				</div>
<!-- 	</form> -->
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	</div>
</body>
</html>
