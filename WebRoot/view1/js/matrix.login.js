var tt;
$(document).ready(function(){

	var login = $('#loginform');
	var recover = $('#recoverform');
	var speed = 400;

	$('#to-recover').click(function(){
		$("#loginform").slideUp();
		$("#recoverform").fadeIn();

		t=window.setInterval("showSceneImg()",1000*60*5); 
		
	});
	$('#to-login').click(function(){
		
		$("#recoverform").hide();
		$("#loginform").fadeIn();
		
		window.clearInterval(t); 
	});
	

    
	
    
    if($.browser.msie == true && $.browser.version.slice(0,3) < 10) {
        $('input[placeholder]').each(function(){ 
       
        var input = $(this);       
       
        $(input).val(input.attr('placeholder'));
               
        $(input).focus(function(){
             if (input.val() == input.attr('placeholder')) {
                 input.val('');
             }
        });
       
        $(input).blur(function(){
            if (input.val() == '' || input.val() == input.attr('placeholder')) {
                input.val(input.attr('placeholder'));
            }
        });
    });

        
        
    }
});

function showSceneImg()
{
   $.ajax({
	  url:path+"/qrCode/getLoginWeiQrcode",
	  data:{},
	  dataType:"json",
	  type:"post",
	  success:function(data){
		  //��ȡurl
		  //�ҵ�div ��� Ȼ�� append img
		  var url = $(data)["url"];
		  var sessionId = $(data)["sessionId"];
		  var img = $("<img/>");
		  $(img).attr("src",url);
		  $("#sceneImg").html("").append(img);
		  //�ɹ��� ��ѵ��̨��sessionId 
		  
	  }
	   
   });
	

}

document.onkeydown=function(event){
     var e = event || window.event || arguments.callee.caller.arguments[0];
     if(e && e.keyCode==27){ // �� Esc 
          //Ҫ��������
     }
     if(e && e.keyCode==113){ // �� F2 
          //Ҫ��������
     }            
     if(e && e.keyCode==13){ // enter ��
          //Ҫ��������
    	 login();
    	 
     }
}; 