<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<html>
<head>
	<title>${applicationScope.globalTitle}</title>
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
	<link rel='stylesheet' href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800'  >
	<script src="${pageContext.request.contextPath}/view1/js/jquery.min.js"></script> 
</head>
<body>
<jsp:include page="/view1/login/menuFrame.jsp"></jsp:include>
<!--main-container-part-->
<div id="content">
<!--breadcrumbs-->
  <div id="content-header">
    <div id="breadcrumb"><a  href="${pageContext.request.contextPath}/admin/adminLoginSuccess" title="Go to Home" class="tip-bottom"><i class="icon-home"></i>首页</a><a href="#" class="current">公众号修改</a></div>
  </div>
  <div class="container-fluid">
  <hr>
  	<div class="row-fluid">
    <!--这是我自己的 -->
     <div class="span6">
      <div class="widget-box">
      	  <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
             <h5>修改公众号</h5>
          </div>
          <div class="widget-content ">
          	<f:form action="${pageContext.request.contextPath}/fuwuhao/updFuwuhao" cssClass="form-horizontal" onsubmit="return false;">
          	<f:hidden path="fwhId"/>
          	<f:hidden path="fwhRegistor"/>
          	<f:hidden path="fwhRegistdate"/>
           	<f:hidden path="fwhStatus"/>
 			<f:hidden path="currentPage"/>
  			<f:hidden path="pageSize"/>
            <div class="form-group">
            	<label for="fwhAppName" class="col-lg-1 control-label">应用名称</label>
            	<div class="col-lg-3">
               	 	<f:input path="fwhAppName" cssClass="form-control input-ms"/>
                </div>
            </div>
            <div class="form-group">
            	<label for="fwhAppId" class="col-lg-1 control-label">AppId</label>
            	<div class="col-lg-3">
               	 	<f:input path="fwhAppId" cssClass="form-control input-ms"/>
                </div>
            </div>
            <div class="form-group">
            	<label for="fwhAppSecret" class="col-lg-1 control-label">AppSecret</label>
            	<div class="col-lg-3">
               	 	<f:password path="fwhAppSecret" cssClass="form-control input-ms"/>
                </div>
            </div>
            <div class="form-group ">
            	<label for="fwhRederectUrl" class="col-lg-1 control-label">RederectUrl</label> 
            	<div class="col-lg-3">
                	<f:input path="fwhRederectUrl" cssClass="form-control input-ms"/>
            	</div>
            </div>
            <div class="form-group">
            	<label for="fwhToken" class="col-lg-1 control-label">Token</label>
            	<div class="col-lg-3">
                	<f:input path="fwhToken" cssClass="form-control input-ms"/>
            	</div>
            </div>
            
            <div class="form-group">
            	<label for="fwhEncodingAesKey" class="col-lg-1 control-label">EncodingAesKey</label>
            	<div class="col-lg-3">
            		<f:input path="fwhEncodingAesKey"  cssClass="form-control input-ms"/>
            	</div>
            </div>
                  
			<div class="form-group ">
            	<label for="fwhAppType" class="col-lg-1 control-label">应用类型</label> 
            	<div class="col-lg-3">
                   	<label><f:radiobutton path="fwhAppType" value="1"/> 微信</label>
            		<label><f:radiobutton path="fwhAppType" value="2"/> 微博</label>
            	</div>
            </div>
            <div class="form-group ">
            	<label for="fwhAesType" class="col-lg-1 control-label">消息加解密方式</label> 
            	<div class="col-lg-3">
                	<label><f:radiobutton path="fwhAesType" value="0"/> 明文</label>
            		<label><f:radiobutton path="fwhAesType" value="1"/> 加密</label>
            		<label><f:radiobutton path="fwhAesType" value="2"/> 兼容</label>
            	</div>
            </div>
			<div class="form-group ">
            	<label for="fwhCusType" class="col-lg-1 control-label">客户类型</label> 
            	<div class="col-lg-3">
                	<label><f:radiobutton path="fwhCusType" value="1"/> 自用</label>
            		<label><f:radiobutton path="fwhCusType" value="2"/> 商用</label>
            	</div>
            </div>
            
            <div class="form-group ">
            	<label for="fwhAccountType" class="col-lg-1 control-label">账户类型</label> 
            	<div class="col-lg-3">
                	<label><f:radiobutton path="fwhAccountType" value="1"/> 订阅号</label>
                	<label><f:radiobutton path="fwhAccountType" value="2"/> 服务号</label>
            		<label><f:radiobutton path="fwhAccountType" value="3"/> 企业号</label>
            	</div>
            </div>
            <div class="form-group ">
            	<label for="fwhEnterId" class="col-lg-1 control-label">商家ID:仅适用于商用</label> 
            	<div class="col-lg-3">
                	<f:input path="fwhEnterId" cssClass="form-control input-ms"/>
            	</div>
            </div>
            <div class="form-group ">
            	<label for="fwhSubscribeMsg" class="col-lg-1 control-label">默认关注消息(限定100个字)</label> 
            	<div class="col-lg-3">
            		<input type="text" id="subscribeMsg" name="subscribeMsg" class="form-control input-ms" placeholder="文，图，语音，视频信息，均可回复哦!" />	
                	<f:hidden path="fwhSubscribeMsg"/>
                </div>
                <div class="col-lg-1">
            		<input type="button" onclick="javascript:toAddMsg('3');" class="btn btn-primary" value="添加多媒体关注信息"/>
            	</div>
            </div>
            <div class="form-group ">
            	<label for="fwhDefaultMsg" class="col-lg-1 control-label">默认回复消息(限定60个字)</label> 
            	<div class="col-lg-3">
            		<input type="text" id="defaultMsg" name="defaultMsg" class="form-control input-ms" placeholder="非关键字的默认回复"/>	
                	<f:hidden path="fwhDefaultMsg"/> 
            	</div>
            	<div class="col-lg-1">
            		<input type="button" onclick="javascript:toAddMsg('2');" class=" btn btn-primary" value="添加多媒体回复信息"/>
            	</div>
            </div>
            <div class="form-group ">
            	<label for="fwhDefaultMsg" class="col-lg-1 control-label">关键字回复</label> 
            	<div class="col-lg-3">
					
            	</div>
            </div>
            
            <div class="form-group">
            	<label for="fwhDesc" class="col-lg-1 control-label">备注</label> 
            	<div class="col-lg-3">
                	<f:textarea path="fwhDesc" cssClass="form-control input-ms"></f:textarea>
            	</div>
            </div>
			<div class="form-group">
				<div class="col-lg-1"></div>
				<div class="col-lg-3">
					<button class="btn btn-info" onclick="updFuwuhaoSubmit();" >提交</button>
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
<script>
	var path = "${pageContext.request.contextPath}";
	
	
	$(document).ready(function(){
		 $('input[type=radio]').iCheck({
             radioClass: 	'iradio_minimal',
             increaseArea: 	'10%'
         });
	});
	
</script>
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
<script src="${pageContext.request.contextPath}/view1/fuwuhao/js/fuwuhao.js" ></script>
<script src="${pageContext.request.contextPath}/view1/js/zTree/jquery.ztree.core-3.5.min.js?v=${applicationScope.sysStartUpTime}"></script>
<script src="${pageContext.request.contextPath}/view1/js/zTree/jquery.ztree.excheck-3.5.min.js?v=${applicationScope.sysStartUpTime}"></script>
<script src="${pageContext.request.contextPath}/view1/js/icheck/icheck.js" ></script>
	

</body>
</html>
