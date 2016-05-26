/**
	修改管理员权限
*/
function toUpdRole()
{
	//
}

function addAdminSubmit()
{
	var myForm = document.forms[0];
	myForm.submit();
}


function loadMenuTreeForAdmin(adminId)
{
	var roleIds = document.getElementById("roleIds");
	var roleIdStr = "";
	for( var i = 0; i < roleIds.length; i++ )
	{
		roleIdStr += "," + roleIds[i].value;
	}
	if( roleIdStr.length == 0 )
	{
		roleIdStr = "-1";
	}
	else
	{
		roleIdStr = roleIdStr.substring(1, roleIdStr.length);
	}
	
	loadMenuTree(path + "/shop/menu/getMenuTreeForAdmin/2/" + roleIdStr + "/" + adminId);
}