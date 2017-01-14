<%@ page language="java" contentType="text/html; charset=utf-8"    pageEncoding="utf-8"%>


<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
    <title>发起新活动</title>
    <link href="${pageContext.request.contextPath}/view1/my/fonts/iconfont.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/view1/my/css/bass.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/view1/my/css/index.css" rel="stylesheet"/>
</head>
<body>
    <div>
	    <header>
		    <div class="header">
			    <h1>新的活动</h1>
			    <a href="supply.html" class="return"><i class="icon-16"></i></a>
		    </div>
	    </header>
	    <div style="height: 2.5rem;"></div>
    </div>
    
    <div class="purchase">
		<form class="input-group">
		    <div class="input-row">
			    <label><i class="icon-113"></i>&nbsp;标题</label>
			    <input type="text" placeholder="字数不超过20个字" required>
			    <div class="form_hint"></div>
		    </div>
            <div class="input-row">
			    <label><i class="icon-uniE95A "></i>&nbsp;类型</label>
		        <div class="group-T">
			        <input type="radio" class="radio-la" name="purchase-type" id="check-1" hidden>
			        <label for="check-1" class="group-T-l icon-uniE940">免费</label>
			        <input type="radio" class="radio-la" name="purchase-type" id="check-2" hidden>
			        <label for="check-2" class="group-T-l icon-uniE940">收费</label>
				</div>
		    </div>
		    <div class="input-row">
			    <label><i class="icon-uniE952 "></i>&nbsp;金额</label>
			    <input type="text" placeholder="请输入金额" required/>
			    <div class="form_hint">收费活动，金额不能空着</div>
		    </div>
			<div class="input-row">
			    <label><i class="icon-uniE96B"></i>&nbsp;人数</label>
			    <input type="text" placeholder="空着就是不限人数" required/>
			    <div class="form_hint">请填写数字</div>
			</div>
			<div class="input-row">
			    <label><i class="icon-uniE96C"></i>&nbsp;时间</label>
			    <input type="text" placeholder="请填写" required/>
			    <div class="form_hint">请填写具体时间</div>
			</div>
			<div class="input-row">
			    <label><i class="icon-uniE902"></i>&nbsp;地点</label>
			    <input type="text" placeholder="地点" required/>
			    <div class="form_hint">请填写</div>
			</div>			                 
        	<div class="input-row">
                <label><i class="icon-uniE951"></i>&nbsp;活动简介</label> 
                <div class="pur-content">
                	<textarea ></textarea>  
                </div>                    
        	</div>
            <button type="button" onclick="return false;">发布</button>

        </form>
	</div>


</body>
</html>