<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>

<!DOCTYPE html>
<html>
  <head>
   <title>自定义菜单修改</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" >
	<link rel="stylesheet" href="${pageContext.request.contextPath}/view1/css/bootstrap.css" >
	<link rel="stylesheet" href="${pageContext.request.contextPath}/view1/css/matrix-style.css" >
	<link rel="stylesheet" href="${pageContext.request.contextPath}/view1/css/matrix-media.css" >
	<link rel="stylesheet" href="${pageContext.request.contextPath}/view1/css/uniform.css" >
	<link rel="stylesheet" href="${pageContext.request.contextPath}/view1/font-awesome/css/font-awesome.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/view1/css/jquery.gritter.css" >

	<script type="text/javascript" src="${pageContext.request.contextPath}/view1/js/jquery.min.js"></script> 
	<script type="text/javascript" src="${pageContext.request.contextPath}/view1/js/jquery-browser.js"></script> 		
	<script type="text/javascript" src="${pageContext.request.contextPath}/view1/js/basic.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view1/js/tabList.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view1/js/jquery.ui.custom.js"></script> 
	<script type="text/javascript" src="${pageContext.request.contextPath}/view1/js/bootstrap.js"></script> 
	<script type="text/javascript" src="${pageContext.request.contextPath}/view1/js/bootstrap-typeahead.js"></script> <!-- 自动补全 -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/view1/js/jquery.flot.min.js"></script> 
	<script type="text/javascript" src="${pageContext.request.contextPath}/view1/js/jquery.flot.resize.min.js"></script> 
	<script type="text/javascript" src="${pageContext.request.contextPath}/view1/js/jquery.peity.min.js"></script> 
	<script type="text/javascript" src="${pageContext.request.contextPath}/view1/js/jquery.gritter.min.js"></script> 
	<script type="text/javascript" src="${pageContext.request.contextPath}/view1/js/jquery.wizard.js"></script> 
	<script type="text/javascript" src="${pageContext.request.contextPath}/view1/js/jquery.uniform.js"></script><!-- 美化checkBox的js -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/view1/js/jquery.dataTables.1.10.9.min.js"></script> 
	<script type="text/javascript" src="${pageContext.request.contextPath}/view1/weixinmenu/js/menu.js" ></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view1/fuwuhao/js/fuwuhao.js" ></script>
	
	<script type="text/javascript">
	var path = "${pageContext.request.contextPath}";
	var alertMsg = "${alertMsg}";
	$(document).ready(function(){
		if( alertMsg.length>0)
		{
			alert(alertMsg);
		}
		$(".trSubBtn").hide();
		$("select[name^='selType']").bind("change",function(){
			showSubBtns($(this));
		});
		//要根据菜单项json还原一下菜单的明细
		initSubButton(${command.wmuJson});
	});
	</script>
  </head>
  <body>
  	<jsp:include page="/view1/login/menuFrame.jsp"></jsp:include>
   
    <div id="content">
<!--breadcrumbs-->
  <div id="content-header">
    <div id="breadcrumb"><a  href="${pageContext.request.contextPath}/admin/adminLoginSuccess" title="Go to Home" class="tip-bottom"><i class="icon-home"></i>首页</a><a href="#" class="current">自定义菜单修改</a></div>
  </div>
  <div class="container-fluid">
  <hr>
  	<div class="row-fluid">
    <!--这是我自己的 -->
     <div class="span6">
      <div class="widget-box">
      	  <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
             <h5>修改自定义菜单</h5>
          </div>
          <div class="widget-content ">
          	<f:form action="${pageContext.request.contextPath}/weixinmenu/addMenuByEnter" cssClass="form-horizontal" onsubmit="return false;">
			<f:hidden path="wmuId"/>
  			<f:hidden path="wmuWecId"/>

			
			 <div class="form-group  trNotSubBtn">
            	<label for="fwhAppName" class="col-lg-1 control-label">应用名称</label>
            	<div class="col-lg-3">
               	 	${weiEnter.fwhAppName}
                </div>
            </div>
			
	    	
	    	
			 <div class="form-group  trNotSubBtn">
            	<label for="fwhAppName" class="col-lg-1 control-label">应用APPID</label>
            	<div class="col-lg-3">
               	 	<f:input path="wmuAppId" /><f:hidden path="wmuAppSecret"/>
                </div>
            </div>
	    	

	    	 <div class="form-group  trNotSubBtn">
            	<label for="fwhAppName" class="col-lg-1 control-label">JSON串</label>
            	<div class="col-lg-3">
               	 	<f:input cssStyle="width:100%;" path="wmuJson" />
                </div>
            </div>

	    	 <div class="form-group  trNotSubBtn">
            	<label for="fwhAppName" class="col-lg-1 control-label">备注</label>
            	<div class="col-lg-3">
               	 	<f:textarea path="wmuDesc" />
                </div>
            </div>
	    	
	    	
	    	 <div class="form-group  trNotSubBtn">
            	<label for="fwhAppName" class="col-lg-1 control-label"><input type="checkbox" name="checkBtn_1" id="checkBtn_1" value="1" onclick="javascript:needBtn(this,1);"/>按钮1_左</label>
            	<div class="col-lg-3">
               	 	
                </div>
            </div>
            
	    	
	    	<div class="form-group  trNotSubBtn">
            	<label for="fwhAppName" class="col-lg-1 control-label">名称</label>
            	<div class="col-lg-3">
               	 	<input type="text" name="iptName_1" value=""/>
                </div>
            </div>
            
	    	
	    	
	    	<div class="form-group  trNotSubBtn">
            	<label for="fwhAppName" class="col-lg-1 control-label">类型</label>
            	<div class="col-lg-3">
               	 	<select name="selType_1" >
	    			<option value="view">跳转URL</option>
	    			<option value="click">点击推事件</option>
	    			<option value="">含子节点</option>
	    		</select>
                </div>
            </div>
	    	
	    	
	    	<div class="form-group  trNotSubBtn">
            	<label for="fwhAppName" class="col-lg-1 control-label">key</label>
            	<div class="col-lg-3">
               	 <input type="text" name="iptKey_1" id="iptKey_1" value=""/>
                </div>
            </div>
	    	
	    	<div class="form-group trNotSubBtn">
            	<label for="fwhAppName" class="col-lg-1 control-label">url</label>
            	<div class="col-lg-3">
               	 <input type="text" name="iptUrl_1" id="iptUrl_1" value=""/>
                </div>
            </div>
	    	
	    	<div class="form-group trSubBtn">
            	<label for="fwhAppName" class="col-lg-1 control-label">小按钮1</label>
            	<div class="col-lg-3">
               	 <input type="checkbox" name="checkSubChk_1" id="checkSubChk_1_1" onclick="javascript:needSubbtn(this,1);" />
	    			名称:<input type="text" name="iptSubName_1"  id="iptSubName_1_1"  value="" disabled="disabled"/> 
	    			类型:
	    			<select name="selSubType_1" id="selSubType_1_1" disabled="disabled">
	    				<option value="">-请选择-</option>
	    				<option value="view">跳转URL</option>
	    				<option value="click">点击推事件</option>
	    			</select>
	    			key:<input type="text" name="subIptKey_1" id="subIptKey_1_1" value=""  disabled="disabled"/>
	    			url:<input type="text" name="subIptUrl_1" id="subIptUrl_1_1" value=""  disabled="disabled"/>
                </div>
            </div>
            
	    	
	    	
	    	<div class="form-group trSubBtn">
            	<label for="fwhAppName" class="col-lg-1 control-label">小按钮2</label>
            	<div class="col-lg-3">
               	 <input type="checkbox"  name="checkSubChk_1"  id="checkSubChk_1_2" onclick="javascript:needSubbtn(this,2);"/>
	    			名称:
	    			<input type="text" name="iptSubName_1" id="iptSubName_1_2" value="" disabled="disabled"/> 
	    			类型:
	    			<select name="selSubType_1" id="selSubType_1_2" disabled="disabled">
	    				<option value="">-请选择-</option>
	    				<option value="view">跳转URL</option>
	    				<option value="click">点击推事件</option>
	    			</select>
	    			key:<input type="text" name="subIptKey_1" id="subIptKey_1_2" value=""  disabled="disabled"/>
	    			url:<input type="text" name="subIptUrl_1" id="subIptUrl_1_2" value=""  disabled="disabled"/>
                </div>
            </div>
            
	    	
	    	
	    	<div class="form-group trSubBtn">
            	<label for="fwhAppName" class="col-lg-1 control-label">小按钮3</label>
            	<div class="col-lg-3">
               	 <input type="checkbox" name="checkSubChk_1" id="checkSubChk_1_3" onclick="javascript:needSubbtn(this,3);"/>
	    			名称:<input type="text" name="iptSubName_1" id="iptSubName_1_3" value="" disabled="disabled"/> 
	    			类型:
	    			<select name="selSubType_1" id="selSubType_1_3" disabled="disabled">
	    				<option value="">-请选择-</option>
	    				<option value="view">跳转URL</option>
	    				<option value="click">点击推事件</option>
	    			</select>
	    			key:<input type="text" name="subIptKey_1" id="subIptKey_1_3" value=""  disabled="disabled"/>
	    			url:<input type="text" name="subIptUrl_1" id="subIptUrl_1_3" value=""  disabled="disabled"/>
                </div>
            </div>
	    	
	    	
	    	
	    	<div class="form-group trSubBtn">
            	<label for="fwhAppName" class="col-lg-1 control-label">小按钮4</label>
            	<div class="col-lg-3">
               	 <input type="checkbox" name="checkSubbtn_1" id="checkSubbtn_1_4" onclick="javascript:needSubbtn(this,4);"/>
	    			名称:<input type="text" name="iptSubName_1" id="iptSubName_1_4" value="" disabled="disabled"/> 
	    			类型:
	    			<select name="selSubType_1" id="selSubType_1_4" disabled="disabled">
	    				<option value="">-请选择-</option>
	    				<option value="view">跳转URL</option>
	    				<option value="click">点击推事件</option>
	    			</select>
	    			key:<input type="text" name="subIptKey_1" id="subIptKey_1_4" value="" disabled="disabled"/>
	    			url:<input type="text" name="subIptUrl_1" id="subIptUrl_1_4" value="" disabled="disabled"/>
                </div>
            </div>

	    	<div class="form-group trSubBtn">
            	<label for="fwhAppName" class="col-lg-1 control-label">小按钮5</label>
            	<div class="col-lg-3">
	    			<input type="checkbox" name="checkSubbtn_1" id="checkSubbtn_1_5" onclick="javascript:needSubbtn(this,5);"/>
	    			名称:<input type="text" name="iptSubName_1" id="iptSubName_1_5" value="" disabled="disabled"/> 
	    			类型:
	    			<select name="selSubType_1" id="selSubType_1_5" disabled="disabled">
	    				<option value="">-请选择-</option>
	    				<option value="view">跳转URL</option>
	    				<option value="click">点击推事件</option>
	    			</select>
	    			key:<input type="text" name="subIptKey_1" id="subIptKey_1_5" value=""  disabled="disabled"/>
	    			url:<input type="text" name="subIptUrl_1" id="subIptUrl_1_5" value=""  disabled="disabled"/>
                </div>
            </div>
	    	
	    	
	    	
	    	<div class="form-group trNotSubBtn">
            	<label for="fwhAppName" class="col-lg-1 control-label"><input type="checkbox" name="checkBtn_2" id="checkBtn_2" value="2"  onclick="javascript:needBtn(this,2);"/>按钮2_中</label>
            	<div class="col-lg-3">
	    			
                </div>
            </div>
	    	
	    	
	    	<div class="form-group trNotSubBtn">
            	<label for="fwhAppName" class="col-lg-1 control-label">名称</label>
            	<div class="col-lg-3">
	    			<input type="text" name="iptName_2"  value="" />
                </div>
            </div>
            
            <div class="form-group trNotSubBtn">
            	<label for="fwhAppName" class="col-lg-1 control-label">类型</label>
            	<div class="col-lg-3">
	    			<select name="selType_2" >
	    			<option value="view">跳转URL</option>
	    			<option value="click">点击推事件</option>
	    			<option value="">含子节点</option>
	    		</select>
                </div>
            </div>
	    	
	    	
	    	
	    	 <div class="form-group trNotSubBtn">
            	<label for="fwhAppName" class="col-lg-1 control-label">key</label>
            	<div class="col-lg-3">
	    			<input type="text" name="iptKey_2"  value="" />
                </div>
            </div>
	    	
	    	
	    	
	    	 <div class="form-group trNotSubBtn">
            	<label for="fwhAppName" class="col-lg-1 control-label">url</label>
            	<div class="col-lg-3">
	    			<input type="text" name="iptUrl_2"  value="" />
                </div>
            </div>
	    	
	    	
	    	 <div class="form-group trSubBtn">
            	<label for="fwhAppName" class="col-lg-1 control-label">小按钮1</label>
            	<div class="col-lg-3">
	    			<input type="checkbox" name="checkSubChk_2" id="checkSubChk_2_1" onclick="javascript:needSubbtn(this,1);" />
	    			名称:<input type="text" name="iptSubName_2"  id="iptSubName_2_1" value="" disabled="disabled"/> 
	    			类型:
	    			<select name="selSubType_2" id="selSubType_2_1" disabled="disabled">
	    				<option value="">-请选择-</option>
	    				<option value="view">跳转URL</option>
	    				<option value="click">点击推事件</option>
	    			</select>
	    			key:<input type="text" name="subIptKey_2" id="subIptKey_2_1" value=""  disabled="disabled"/>
	    			url:<input type="text" name="subIptUrl_2" id="subIptUrl_2_1" value=""  disabled="disabled"/>
                </div>
            </div>
	    	
	    	
	    	 <div class="form-group trSubBtn">
            	<label for="fwhAppName" class="col-lg-1 control-label">小按钮2</label>
            	<div class="col-lg-3">
	    			<input type="checkbox"  name="checkSubChk_2"  id="checkSubChk_2_2" onclick="javascript:needSubbtn(this,2);"/>
	    			名称:
	    			<input type="text" name="iptSubName_2" id="iptSubName_2_2" value="" disabled="disabled"/> 
	    			类型:
	    			<select name="selSubType_2" id="selSubType_2_2" disabled="disabled">
	    				<option value="">-请选择-</option>
	    				<option value="view">跳转URL</option>
	    				<option value="click">点击推事件</option>
	    			</select>
	    			key:<input type="text" name="subIptKey_2" id="subIptKey_2_2" value=""  disabled="disabled"/>
	    			url:<input type="text" name="subIptUrl_2" id="subIptUrl_2_2" value=""  disabled="disabled"/>
                </div>
            </div>
	    	
	    	
	    	
	    	
	    	 <div class="form-group trSubBtn">
            	<label for="fwhAppName" class="col-lg-1 control-label">小按钮3</label>
            	<div class="col-lg-3">
	    			<input type="checkbox" name="checkSubChk_2" id="checkSubChk_2_3" onclick="javascript:needSubbtn(this,3);"/>
	    			名称:<input type="text" name="iptSubName_2" id="iptSubName_2_3" value="" disabled="disabled"/> 
	    			类型:
	    			<select name="selSubType_2" id="selSubType_2_3" disabled="disabled">
	    				<option value="">-请选择-</option>
	    				<option value="view">跳转URL</option>
	    				<option value="click">点击推事件</option>
	    			</select>
	    			key:<input type="text" name="subIptKey_2" id="subIptKey_2_3" value=""  disabled="disabled"/>
	    			url:<input type="text" name="subIptUrl_2" id="subIptUrl_2_3" value=""  disabled="disabled"/>
                </div>
            </div>
	    	
	    	
	    	
	    	 <div class="form-group trSubBtn">
            	<label for="fwhAppName" class="col-lg-1 control-label">小按钮4</label>
            	<div class="col-lg-3">
	    			<input type="checkbox" name="checkSubbtn_2" id="checkSubbtn_2_4" onclick="javascript:needSubbtn(this,4);"/>
	    			名称:<input type="text" name="iptSubName_2" id="iptSubName_2_4" value="" disabled="disabled"/> 
	    			类型:
	    			<select name="selSubType_2" id="selSubType_2_4" disabled="disabled">
	    				<option value="">-请选择-</option>
	    				<option value="view">跳转URL</option>
	    				<option value="click">点击推事件</option>
	    			</select>
	    			key:<input type="text" name="subIptKey_2" id="subIptKey_2_4" value="" disabled="disabled"/>
	    			url:<input type="text" name="subIptUrl_2" id="subIptUrl_2_4" value="" disabled="disabled"/>
                </div>
            </div>
	    	
	    
	    	
	    	 <div class="form-group trSubBtn">
            	<label for="fwhAppName" class="col-lg-1 control-label">小按钮5</label>
            	<div class="col-lg-3">
	    			<input type="checkbox" name="checkSubbtn_2" id="checkSubbtn_2_5" onclick="javascript:needSubbtn(this,5);"/>
	    			名称:<input type="text" name="iptSubName_2" id="iptSubName_2_5" value="" disabled="disabled"/> 
	    			类型:
	    			<select name="selSubType_2" id="selSubType_2_5" disabled="disabled">
	    				<option value="">-请选择-</option>
	    				<option value="view">跳转URL</option>
	    				<option value="click">点击推事件</option>
	    			</select>
	    			key:<input type="text" name="subIptKey_2" id="subIptKey_2_5" value=""  disabled="disabled"/>
	    			url:<input type="text" name="subIptUrl_2" id="subIptUrl_2_5" value=""  disabled="disabled"/>
                </div>
            </div>
	    	
	    	 <div class="form-group trNotSubBtn">
            	<label for="fwhAppName" class="col-lg-1 control-label"><input type="checkbox" name="checkBtn_3" id="checkBtn_3" value="3"  onclick="javascript:needBtn(this,3);"/>按钮3_右</label>
            	<div class="col-lg-3">
	    			
                </div>
            </div>
	    	
	    	
	    	 <div class="form-group trNotSubBtn">
            	<label for="fwhAppName" class="col-lg-1 control-label">名称</label>
            	<div class="col-lg-3">
	    			<input type="text" name="iptName_3"  value="" />
                </div>
            </div>
	    	
	    	 <div class="form-group trNotSubBtn">
            	<label for="fwhAppName" class="col-lg-1 control-label">类型</label>
            	<div class="col-lg-3">
	    			<select name="selType_3" >
	    			<option value="view">跳转URL</option>
	    			<option value="click">点击推事件</option>
	    			<option value="">含子节点</option>
	    		</select>
                </div>
            </div>
	    	
	    	
	    	<div class="form-group trNotSubBtn">
            	<label for="fwhAppName" class="col-lg-1 control-label">key</label>
            	<div class="col-lg-3">
	    			<input type="text" name="iptKey_3"  value="" />
                </div>
            </div>
            
	    	
	    	<div class="form-group trNotSubBtn">
            	<label for="fwhAppName" class="col-lg-1 control-label">url</label>
            	<div class="col-lg-3">
	    			<input type="text" name="iptUrl_3"  value="" />
                </div>
            </div>
	    	
	    	
	    	<div class="form-group trSubBtn">
            	<label for="fwhAppName" class="col-lg-1 control-label">小按钮1</label>
            	<div class="col-lg-3">
	    			<input type="checkbox" name="checkSubChk_3" id="checkSubChk_3_1" onclick="javascript:needSubbtn(this,1);" />
	    			名称:<input type="text" name="iptSubName_3"  id="iptSubName_3_1" value="" disabled="disabled"/> 
	    			类型:
	    			<select name="selSubType_3" id="selSubType_3_1" disabled="disabled">
	    				<option value="">-请选择-</option>
	    				<option value="view">跳转URL</option>
	    				<option value="click">点击推事件</option>
	    			</select>
	    			key:<input type="text" name="subIptKey_3" id="subIptKey_3_1" value=""  disabled="disabled"/>
	    			url:<input type="text" name="subIptUrl_3" id="subIptUrl_3_1" value=""  disabled="disabled"/>
                </div>
            </div>
	    	
	    	
	    	<div class="form-group trSubBtn">
            	<label for="fwhAppName" class="col-lg-1 control-label">小按钮2</label>
            	<div class="col-lg-3">
	    			<input type="checkbox"  name="checkSubChk_3"  id="checkSubChk_3_2" onclick="javascript:needSubbtn(this,2);"/>
	    			名称:
	    			<input type="text" name="iptSubName_3" id="iptSubName_3_2" value="" disabled="disabled"/> 
	    			类型:
	    			<select name="selSubType_3" id="selSubType_3_2" disabled="disabled">
	    				<option value="">-请选择-</option>
	    				<option value="view">跳转URL</option>
	    				<option value="click">点击推事件</option>
	    			</select>
	    			key:<input type="text" name="subIptKey_3" id="subIptKey_3_2" value=""  disabled="disabled"/>
	    			url:<input type="text" name="subIptUrl_3" id="subIptUrl_3_2" value=""  disabled="disabled"/>
                </div>
            </div>
	    	
	    	<div class="form-group trSubBtn">
            	<label for="fwhAppName" class="col-lg-1 control-label">小按钮3</label>
            	<div class="col-lg-3">
	    			<input type="checkbox" name="checkSubChk_3" id="checkSubChk_3_3" onclick="javascript:needSubbtn(this,3);"/>
	    			名称:<input type="text" name="iptSubName_3" id="iptSubName_3_3" value="" disabled="disabled"/> 
	    			类型:
	    			<select name="selSubType_3" id="selSubType_3_3" disabled="disabled">
	    				<option value="">-请选择-</option>
	    				<option value="view">跳转URL</option>
	    				<option value="click">点击推事件</option>
	    			</select>
	    			key:<input type="text" name="subIptKey_3" id="subIptKey_3_3" value=""  disabled="disabled"/>
	    			url:<input type="text" name="subIptUrl_3" id="subIptUrl_3_3" value=""  disabled="disabled"/>
                </div>
            </div>
	    	
	    	<div class="form-group trSubBtn">
            	<label for="fwhAppName" class="col-lg-1 control-label">小按钮4</label>
            	<div class="col-lg-3">
	    			<input type="checkbox" name="checkSubbtn_3" id="checkSubbtn_3_4" onclick="javascript:needSubbtn(this,4);"/>
	    			名称:<input type="text" name="iptSubName_3" id="iptSubName_3_4" value="" disabled="disabled"/> 
	    			类型:
	    			<select name="selSubType_3" id="selSubType_3_4" disabled="disabled">
	    				<option value="">-请选择-</option>
	    				<option value="view">跳转URL</option>
	    				<option value="click">点击推事件</option>
	    			</select>
	    			key:<input type="text" name="subIptKey_3" id="subIptKey_3_4" value="" disabled="disabled"/>
	    			url:<input type="text" name="subIptUrl_3" id="subIptUrl_3_4" value="" disabled="disabled"/>
                </div>
            </div>
	    	
	    	<div class="form-group trSubBtn">
            	<label for="fwhAppName" class="col-lg-1 control-label">小按钮5</label>
            	<div class="col-lg-3">
	    			<input type="checkbox" name="checkSubbtn_3" id="checkSubbtn_3_5" onclick="javascript:needSubbtn(this,5);"/>
	    			名称:<input type="text" name="iptSubName_3" id="iptSubName_3_5" value="" disabled="disabled"/> 
	    			类型:
	    			<select name="selSubType_3" id="selSubType_3_5" disabled="disabled">
	    				<option value="">-请选择-</option>
	    				<option value="view">跳转URL</option>
	    				<option value="click">点击推事件</option>
	    			</select>
	    			key:<input type="text" name="subIptKey_3" id="subIptKey_3_5" value=""  disabled="disabled"/>
	    			url:<input type="text" name="subIptUrl_3" id="subIptUrl_3_5" value=""  disabled="disabled"/>
                </div>
            </div>
	    	<div class="form-group">
            	<label for="fwhAppName" class="col-lg-1 control-label"></label>
            	<div class="col-lg-3">
	    			<input type="button" value="保存" onclick="javascript:addMenu()"/>
                </div>
            </div>

            </f:form>
          </div>
       </div>
     </div>
  	</div>
  </div>
</div>
    
    <jsp:include page="/view1/common/footer.jsp"></jsp:include>
    
    <jsp:include page="/view1/common/modal-dialog.jsp"></jsp:include>
  	<script src="${pageContext.request.contextPath}/view1/js/jquery-browser.js"></script> 		
	<script src="${pageContext.request.contextPath}/view1/js/basic.js"></script>
	<script src="${pageContext.request.contextPath}/view1/js/tabList.js"></script>
	<script src="${pageContext.request.contextPath}/view1/js/jquery.ui.custom.js"></script> 
	<script src="${pageContext.request.contextPath}/view1/js/bootstrap.js"></script> 
	<script src="${pageContext.request.contextPath}/view1/js/bootstrap-typeahead.js"></script> <!-- 自动补全 -->
	<script src="${pageContext.request.contextPath}/view1/js/jquery.flot.min.js"></script> 
	<script src="${pageContext.request.contextPath}/view1/js/jquery.flot.resize.min.js"></script> 
	<script src="${pageContext.request.contextPath}/view1/js/jquery.peity.min.js"></script> 
	<script src="${pageContext.request.contextPath}/view1/js/matrix.js"></script> 
	<script src="${pageContext.request.contextPath}/view1/js/jquery.gritter.min.js"></script> 
	<script src="${pageContext.request.contextPath}/view1/js/jquery.wizard.js"></script> 
	<script src="${pageContext.request.contextPath}/view1/js/jquery.uniform.js"></script><!-- 美化checkBox的js -->
	<script src="${pageContext.request.contextPath}/view1/js/select2.min.js"></script> 
	<script src="${pageContext.request.contextPath}/view1/js/jquery.dataTables.1.10.9.min.js"></script> 
	<script src="${pageContext.request.contextPath}/view1/js/matrix.tables.js"></script> 
	<script src="${pageContext.request.contextPath}/view1/weixinmenu/js/menu.js" ></script>
	<script src="${pageContext.request.contextPath}/view1/fuwuhao/js/fuwuhao.js" ></script>
	
  </body>
  
</html>
