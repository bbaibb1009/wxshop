function getMemberNameList(memName)
{
	$.post(path + "/member/getMemNameList",
		function (result)
		{
			$("#" + memName).autocomplete({
	        	lookup: result,
	            onSelect: function (suggestion) {
	            	selMemName(suggestion);
	            }
	        });
		}
	);
}