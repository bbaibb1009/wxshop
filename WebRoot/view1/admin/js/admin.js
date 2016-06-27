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
	var zTree = $.fn.zTree.getZTreeObj("treeDemo1");
	var zNodes = zTree.getCheckedNodes(true);
	var deptId = zNodes[0].id;
	$("#wsaDept").val(deptId);
	
	var zTree1 = $.fn.zTree.getZTreeObj("treeDemo");
	var zNodes1 = zTree1.getCheckedNodes(true);
	var  menuIdStr = "";
	for( var i = 0; i < zNodes1.length; i++ )
	{
		menuIdStr += "," + zNodes1[i].id;
	}
	if( menuIdStr.length > 0 )
	{
		menuIdStr = menuIdStr.substring(1, menuIdStr.length);
	}
	myForm.menuIds.value = menuIdStr;
	myForm.submit();
}


function loadMenuTreeForAdmin(adminId)
{
	var roleIds = $("#roleIds");

	var roleIdStr = $("#roleIds").val();
	if( roleIdStr == null)
	{
		roleIdStr = "-1";
	}
	loadMenuTree(path + "/shop/menu/getMenuTreeForAdmin/2/" + roleIdStr + "/" + adminId);
}

function getAdminNameList(adminName)
{
	$.post(path + "/admin/getShopAdminNameList",
		function (result)
		{
			$("#" + adminName).autocomplete({
	        	lookup: result,
	            onSelect: function (suggestion) {
	            	selAdminName(suggestion);
	            }
	        });
		}
	);
}

function selAdminName(suggestion)
{
	document.getElementById("wdpAdminId").value = suggestion.data;
}