/**
 * 	matrix_tables 的设置
 *	引用的是jquery的table控件 
 
 */
$(document).ready(function(){
	
	//表格控件的初始化
	//演示功能数据未启用服务器端的分页
	$('.data-table').dataTable({
		"jQueryUI": true,
		"pageing":true,
		"pagingType": "full_numbers",
		"dom": '<""l>t<"F"fp>'
	});
	
	$('input[type=checkbox],input[type=radio],input[type=file]').uniform();
	
	$('select').select2();
	
	$("span.icon input:checkbox, th input:checkbox").click(function() {
		var checkedStatus = this.checked;
		var checkbox = $(this).parents('.widget-box').find('tr td:first-child input:checkbox');		
		checkbox.each(function() {
			this.checked = checkedStatus;
			if (checkedStatus == this.checked) {
				$(this).closest('.checker > span').removeClass('checked');
			}
			if (this.checked) {
				$(this).closest('.checker > span').addClass('checked');
			}
		});
	});	
});
