function login() 
{
	var myForm = document.forms[0];
	
	//检查一下用户名 和密码 是否输入了 
	
	
	var username 	= document.getElementById("wsaUsername").value.trim();
	var pwd 		= document.getElementById("wsaPwd").value.trim();
	if(username == null || username.length == 0)
	{
		alert("\u5fd8\u4e86\u586b\u5199\u7528\u6237\u540d\u54e6!");
		document.getElementById("wsaUsername").focus();
		return;
	}
	if(pwd ==null || pwd.length == 0)
	{
		alert("\u5fd8\u4e86\u586b\u5199\u5bc6\u7801\u54e6!");
		document.getElementById("wsaPwd").focus();
		return;
	}
	
	myForm.submit();
}



 