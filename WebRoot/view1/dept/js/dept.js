function loadDeptTree()
{
	var url = arguments[0];
	$.post(url,
		function (result)
		{
			var zNodes = result;
			$.fn.zTree.init($("#treeDemo1"), setting, zNodes);
		}
	);
}

function loadDeptTree(url, treeId, sett)
{
	$.post(url,
		function (result)
		{
			var zNodes = result;
			$.fn.zTree.init($("#" + treeId), sett, zNodes);
		}
	);
}


function showParentDept(val)
{
	var pdept1 = document.getElementById("pdept1");
	var pdept2 = document.getElementById("pdept2");
	if( val == "1" )
	{
		pdept1.style.display = "none";
		pdept2.style.display = "none";
		document.getElementById("wdpParentId").value = "";
		document.getElementById("wdpParentName").value = "";
		document.getElementById("tdDeptName1").innerHTML = "";
	}
	else if( val == "2" )
	{
		pdept1.style.display = "";
		pdept2.style.display = "";
		getDeptName1List("wdpParentName");
	}
}

function getDeptName1List(deptName)
{
	$.post(path + "/shop/dept/getDeptName1List",
		function (result)
		{
			$("#" + deptName).autocomplete({
	        	lookup: result,
	            onSelect: function (suggestion) {
	            	selDeptName1(suggestion);
	            }
	        });
		}
	);
}


function selDeptName1(suggestion)
{
	document.getElementById("wdpParentId").value = suggestion.data;
	document.getElementById("tdDeptName1").innerHTML = suggestion.value;
}

function addDeptSubmit()
{
	var myForm = document.forms[0];
	myForm.submit();
}