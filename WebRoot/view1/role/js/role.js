function addRoleSubmit()
{
	var myform = document.forms[0];
	if( myform.wsrRoleName.value.trim().length == 0 )
	{
		alert("角色名称不能为空");
		myform.roleName.focus();
		return;
	}
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	var zNodes = zTree.getCheckedNodes(true);
	if( zNodes.length == 0 )
	{
		alert("请选择相关菜单");
		return;
	}
	if( myform.wsrRoleDesc.value.trim().length > 200 )
	{
		alert("角色备注不能超过200字!");
		myform.roleDesc.focus();
		return;
	}
	
	var menuIdStr = "";
	for( var i = 0; i < zNodes.length; i++ )
	{
		menuIdStr += "," + zNodes[i].id;
	}
	//var zTree2 = $.fn.zTree.getZTreeObj("treeDemo2");
	//var zNodes2 = zTree2.getCheckedNodes(true);
	var adminIdStr = "";
	//for( var i = 0; i < zNodes2.length; i++ )
	//{
	//	adminIdStr += "," + zNodes2[i].id * -1;
	//}
	//if( adminIdStr.length > 0 )
	//{
	//	adminIdStr = adminIdStr.substring(1, adminIdStr.length);
	//}
	
	myform.wsrRoleName.value = myform.wsrRoleName.value.trim();
	myform.wsrRoleDesc.value = myform.wsrRoleDesc.value.trim();
	myform.menuIds.value = menuIdStr.substring(1, menuIdStr.length);
	myform.adminIds.value = adminIdStr;
	
	myform.submit();
}