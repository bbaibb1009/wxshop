function updFuwuhaoSubmit()
{
	var myForm = document.forms[0];
	myForm.submit();
}

function toReplyMsg()
{
	var id = arguments[0];
	var action = path+"/wxmsg/queryWcWeiMessage/"+id;
	var myForm = document.forms[0];
	myForm.action = action;
	myForm.submit();
	
}