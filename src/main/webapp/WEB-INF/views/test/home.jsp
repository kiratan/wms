<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<!-- 最新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="http://cdn.bootcss.com/twitter-bootstrap/3.0.3/css/bootstrap.min.css">

<!-- 可选的Bootstrap主题文件（一般不用引入） -->
<link rel="stylesheet" href="http://cdn.bootcss.com/twitter-bootstrap/3.0.3/css/bootstrap-theme.min.css">

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="http://cdn.bootcss.com/jquery/1.10.2/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://cdn.bootcss.com/twitter-bootstrap/3.0.3/js/bootstrap.min.js"></script>
	<title>Home</title>
	<script type="text/javascript">
	
	  $(function(){  
		 
	        $("#enter").click(function(){  
	        	 var text=$('#code').val();
	        	 
	        	 if(text==''){
	        		 
	        		 alert('请输入一些字符');
	        	 	return false;
	        	 }
	        	 
	            $.ajax({  
	                url:"./qrcode",  
	                type:'get',  
	                data:"code="+text,  
	                dataType:'html',  
	                success:function(data,status){  
	                	
	                    if(status == "success"){  
	                        var objs = jQuery.parseJSON(data);  
	                        var str = "";  
	                        for(var i=0;i<objs.length;i++){  
	                            str = str + objs[i]+" ";  
	                           $('#img').attr('src',str);
	                           $('#download').attr('href',str);
	                        }  
	                    }  
	                },  
	                error:function(xhr,textStatus,errorThrown){  
	                }  
	            });  
	        });  
	    });  
	</script>
</head>
<body>
<div class=row>
<div class="col-xs-4">
   <label for="exampleInputEmail1">请输入一些文字：</label>
    <input type="text" class="form-control" id="code" placeholder="请输入一些文字！然后点击生成二维码。" >
    </div>
    </div>
   <!-- Button trigger modal -->
   <br />
<button id="enter" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal" >
 生成二维码
</button>
		
		
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title" id="myModalLabel">你的二维码</h4>
      </div>
      <div class="modal-body text-center" >
	      <a id="download"  href="">
	     	 <img id="img" src="" class="img-thumbnail" />
	      </a>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
</body>
</html>
