<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>二维码</title>

<script type="text/javascript">
	$(function() {
		var tipsStart="<div class=\"alert alert-danger valide small fade in\">";
		var tipsEnd="</div>";
		$('#phone').popover({
			animation : true,
			trigger : 'manual',
			placement : 'top',
			content : '请输入手机号'
		});
		$('#times').popover({
			animation : true,
			trigger : 'manual',
			placement : 'top',
			content : '输入攻击次数'
		});
		$("#enter").click(function() {
			$('#times').popover('hide');
			$('#phone').popover('hide');
			var phone = $('#phone').val();
			var times = $('#times').val();
			var time = new Date().getTime();
			if (phone == '') {
				$('#phone').popover('show');
				return false;
			} else if (times == "") {
				$('#times').popover('show');
				return false;
			}
			$.ajax({
				url : "./phoneBombAjax",
				type : 'post',
				data : "phone=" + phone + "&times=" + times + "&time=" + time,
				dataType : 'html',
				success : function(data, status) {
					$('#warn').remove();
					if (status == "success") {

						var list = jQuery.parseJSON(data);
						for(var i=0;i<list.length;i++){
							$('#alert').append("<div>"+list[i]+"</div>");
						}
					}
				},
				error : function(xhr, textStatus, errorThrown) {
				}
			});
		});
	});
</script>
</head>
<body>
<div class="panel  panel-info col-md-offset-3 col-sm-6">
  <div class="panel-heading">
    <h3 class="panel-title">电话炸弹</h3>
  </div>
  <div class="panel-body">
		<div class="row ">
			<div class="alert alert-warning alert-dismissable" id="alert" >
			  <strong>警告：</strong>
			  <span id="warn">
			  本功能只做技术交流使用，所有责任由使用者承担。
			  </span>
			  <div id="warn">
			  </div>
		</div>
		</div>
		<div class="input-group">
			<span class="input-group-addon alert-info">手机</span>
			<input type="number" class="form-control " maxlength="11" id="phone" placeholder="请输入手机号">
			<span class="input-group-addon alert-info"> 10分钟内只能轰炸一次最多10个</span>
			</div>
			<br />
			<div class="input-group">
		<span class="input-group-addon alert-info">次数</span>
		<input type="number" maxlength="2" class="form-control" id="times" placeholder="次数" value="10">
		<span class="input-group-addon alert-info">10分钟内只能轰炸一次最多10个</span>
		</div>
		<br/>
		<div class="row">
			<button id="enter" class="btn btn-info pull-right">进击吧 少年！</button>
		</div>	
 </div>
</div>

</body>
</html>
