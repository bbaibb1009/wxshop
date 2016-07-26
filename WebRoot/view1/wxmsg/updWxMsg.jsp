<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<html>
<head>
<title>微商城管理后台</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/view1/css/bootstrap.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/view1/css/fullcalendar.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/view1/css/matrix-style.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/view1/css/matrix-media.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/view1/css/select2.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/view1/font-awesome/css/font-awesome.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/view1/css/jquery.gritter.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/view1/css/zTree/zTreeStyle.css"/>
<link rel='stylesheet' href="${pageContext.request.contextPath}/view1/css/select2.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/view1/css/icheck/all.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/view1/css/bootstrap-wysihtml5.css" />
<link rel='stylesheet' href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800'  >
<script src="${pageContext.request.contextPath}/view1/js/jquery.min.js"></script> 


</head>
<body>
<jsp:include page="/view1/login/menuFrame.jsp"></jsp:include>
<!--main-container-part-->
<div id="content">
<!--breadcrumbs-->
  <div id="content-header">
    <div id="breadcrumb"><a  href="${pageContext.request.contextPath}/admin/adminLoginSuccess" title="Go to Home" class="tip-bottom"><i class="icon-home"></i>首页</a><a href="#" class="current">回复消息修改</a></div>
  </div>
  <div class="container-fluid">
  <hr>
  	<div class="row-fluid">
    <!--这是我自己的 -->
     <div class="span6">
      <div class="widget-box">
      	  <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
             <h5>修改回复消息</h5>
          </div>
          <div class="widget-content ">
          	<f:form action="${pageContext.request.contextPath}/wxmsg/updWxMsg" cssClass="form-horizontal" onsubmit="return false;">
          	<f:hidden path="wmgId"/>
          	<f:hidden path="wmgAppId"/>
          	<f:hidden path="wmgRegistor"/>
          	<f:hidden path="wmgRegistdate"/>
          	
 			<f:hidden path="currentPage"/>
  			<f:hidden path="pageSize"/>
			<f:hidden path="wmgReplyType_Q"/>  
			<f:hidden path="wmgMsgType_Q"/>  
			<f:hidden path="wmgAesType_Q"/> 
			<f:hidden path="wmgAppId_Q"/>
            <div class="form-group">
            	<label for="wmgContent" class="col-lg-1 control-label">回复内容</label>
            	<div class="col-lg-10">
               	 	<f:textarea path="wmgContent" cssClass="textarea_editor form-control input-ms" rows="6" placeholder="Enter text ..."></f:textarea> 
                </div>
            </div>
            <div class="form-group">
            	<label for="wmgContentXml" class="col-lg-1 control-label">回复内容XML</label>
            	<div class="col-lg-3">
               	 	<f:input path="wmgContentXml" cssClass="form-control input-ms"/>
                </div>
            </div>
            <div class="form-group">
            	<label for="wmgReplyType" class="col-lg-1 control-label">回复类型</label>
            	<div class="col-lg-3">
               	 	<label><f:radiobutton path="wmgReplyType" value="1"/>关键字回复</label>
	            		<label><f:radiobutton path="wmgReplyType" value="2"/>默认回复</label>
	            		<label><f:radiobutton path="wmgReplyType" value="3"/>关注回复</label>
                </div>
            </div>
            <div class="form-group ">
            	<label for="wmgMsgType" class="col-lg-1 control-label">消息类型</label> 
            	<div class="col-lg-3">
                	<label><f:radiobutton path="wmgMsgType" value="1"/>图文信息</label>
	            		<label><f:radiobutton path="wmgMsgType" value="2"/>文字信息</label>
	            		<label><f:radiobutton path="wmgMsgType" value="3"/>图片信息</label>
	            		<label><f:radiobutton path="wmgMsgType" value="4"/>语音信息</label>
	            		<label><f:radiobutton path="wmgMsgType" value="5"/>视频信息</label>
	            		<label><f:radiobutton path="wmgMsgType" value="6"/>音乐信息</label>
            	</div>
            </div>
            <div class="form-group">
            	<label for="wmgAesType" class="col-lg-1 control-label">加密类型</label>
            	<div class="col-lg-3">
                	<label><f:radiobutton path="wmgAesType" value="1"/>加密</label>
	            		<label><f:radiobutton path="wmgAesType" value="2"/>明文</label>
	            		<label><f:radiobutton path="wmgAesType" value="3"/>兼容</label>
            	</div>
            </div>
            <f:hidden path=""/>
             <div class="form-group">
            	<label for="wmgStatus" class="col-lg-1 control-label">状态</label>
            	<div class="col-lg-3">
                	<label><f:radiobutton path="wmgStatus" value="1000"/>启用</label>
	            	<label><f:radiobutton path="wmgStatus" value="2000"/>禁用</label>
            	</div>
            </div>
            <div class="form-group">
            	<label for="wmgDesc" class="col-lg-1 control-label">备注</label>
            	<div class="col-lg-3">
            	<f:textarea path="wmgDesc" cssClass="form-control input-ms"></f:textarea>            	
            	</div>
            </div>
			<div class="form-group  ">
				<div class="col-lg-1"></div>
				<div class="col-lg-3">
					<button class="btn btn-info" onclick="updWxMsgSubmit();" >保存</button>
				</div>          	
            </div>
            </f:form>
          </div>
       </div>
     </div>
  	</div>
  </div>
</div>
<!--end-main-container-part-->
<!--Footer-part-->
<jsp:include page="/view1/common/footer.jsp"></jsp:include>
<!--end-Footer-part-->

<script src="${pageContext.request.contextPath}/view1/js/jquery-browser.js"></script> 		
<script src="${pageContext.request.contextPath}/view1/js/basic.js"></script>
<script src="${pageContext.request.contextPath}/view1/js/tabList.js"></script>
<script src="${pageContext.request.contextPath}/view1/js/jquery.ui.custom.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/bootstrap.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/bootstrap-colorpicker.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/bootstrap-datepicker.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/masked.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/jquery.flot.min.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/jquery.flot.resize.min.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/jquery.peity.min.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/fullcalendar.min.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/matrix.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/jquery.gritter.min.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/matrix.interface.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/matrix.chat.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/jquery.validate.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/matrix.form_common.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/matrix.tables.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/matrix.form_validation.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/jquery.wizard.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/jquery.uniform.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/select2.min.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/jquery.dataTables.1.10.9.min.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/zTree/jquery.ztree.core-3.5.min.js?v=${applicationScope.sysStartUpTime}"></script>
<script src="${pageContext.request.contextPath}/view1/js/zTree/jquery.ztree.excheck-3.5.min.js?v=${applicationScope.sysStartUpTime}"></script>
<script src="${pageContext.request.contextPath}/view1/js/icheck/icheck.js" ></script>
<script src="${pageContext.request.contextPath}/view1/wxmsg/js/wxmsg.js" ></script>
<script src="${pageContext.request.contextPath}/view1/js/wysihtml5-0.3.0.js"></script> 
<script src="${pageContext.request.contextPath}/view1/js/bootstrap-wysihtml5.js"></script> 
<script>
	var path = "${pageContext.request.contextPath}";
	$(document).ready(function(){
		 $('input[type=radio]').iCheck({
             radioClass: 	'iradio_minimal',
             increaseArea: 	'10%'
         });
		 
		 $('.textarea_editor').wysihtml5();
	});
</script>
</body>
</html>
