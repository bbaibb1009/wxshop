function addGoodsFenlei() {
	var myForm = document.forms[0];
	myForm.submit();
}
function addGoods() {
	var myForm = document.forms[0]; 
	if ($("#wgsName").val() == null || $("#wgsName").val().length == 0) {
		alert("商品名称不能为空!");
		return;
	}
	if ($("#wgsBzPrice").val() == null || $("#wgsBzPrice").val().length == 0) {
		alert("标准价格不能为空!");
		return;
	}
	if ($("#wgsLsPrice").val() == null || $("#wgsLsPrice").val().length == 0) {
		alert("零售价格不能为空!");
		return;
	}
	myForm.submit();
}

function addInGoods() {
	var myForm = document.forms[0];
	if ($("#wgiInTime").val() == null || $("#wgiInTime").val().length == 0) {
		alert("���ʱ�䲻��Ϊ��!");
		return;
	}
	if ($("#wgiInNum").val() == null || !$("#wgiInNum").val().isFloat()
			|| parseInt($("#wgiInNum").val()) <= 0) {
		alert("�����������С��0!");
		return;
	}
	if ($("#wgiInPrice").val() == null || !$("#wgiInPrice").val().isFloat()) {
		alert("�����д����!");
		return;
	}
	myForm.submit();
}


function addOutGoods() {
	var myForm = document.forms[0];
	if ($("#wgoWgsId").val() == null || $("#wgoWgsId").val().length == 0) {
		alert("商家编号为空!");
		return;
	}
	if ($("#wgoOutTime").val() == null || $("#wgoOutTime").val().length == 0) {
		alert("请选择出库时间!");
		return;
	}
	if ($("#wgoOutNum").val() == null || !$("#wgoOutNum").val().isFloat()
			|| parseInt($("#wgoOutNum").val()) <= 0) {
		alert("销售数量不能为0!");
		return;
	}
	if ($("#wgoOutPrice").val() == null || !$("#wgoOutPrice").val().isFloat()) {
		alert("价格格式不正确!");
		return;
	}
	if($("#wgoWmbId").val()==null || $("#wgoWmbId").val().length==0)
	{
		alert("会员未选择!");
		return;
	}
	else
	{
		if($("#wgoType").val()=="3" )
		{
			if(parseInt($("#wgoWmbId").val())==0)
			{
				alert("会员未选择!");
				return;
			}
		}
	}
	if(parseFloat($("#wgoOutNum").val())>parseFloat($("#wgsKucun").html()))
	{
		alert("出库量不能大于库存量!");
		return;
	}
	myForm.submit();
}

function selMember()
{
	var wcsId = arguments[0];
	var action = path+"/member/selMember/"+wcsId;
	var retVal = showMyDialog(action,window,680,400);
	if(retVal)
	{
		var val = retVal.split("^")[1];
		
		var wmeId = val.split("_")[0];
		var wmeName = val.split("_")[1];
		$("#wgoWmbId").val(wmeId);
		$("#wgoWmbName_Q").val(wmeName);
	}
}

function addMemForSale()
{
	var chkboxAry = $("input[name='memIds']:checked");
	var strCheck ="";
	if( chkboxAry.length > 0 )
	{
		var selCount = 0;
		for( var i = 0; i < chkboxAry.length; i++ )
		{
			if( chkboxAry[i].checked )
			{
				selCount++;
				strCheck = strCheck +"^" +chkboxAry[i].value;
			}
		}
		if( selCount == 0 )
		{
			alert("��ѡ��һ����Ա");
			return;
		}
		else
		{
			retValMyDialog(strCheck);
		}
	}
}

function getGoodsKucun()
{
	var goodsId = $("#wgoWgsId").val();
	if(goodsId.length>0&&goodsId.isNumber())
	{
		$.ajax({
			url:path+"/goods/getKucunByGoods/"+goodsId,
			type:"post",
			dataType:"json",
			data:{},
			success : function(data){
				var obj = eval(data);
				var kucun = obj.kucun;
				var wgsBiaozhunjia = obj.wgsBiaozhunjia;
				if(parseFloat(kucun)<=0)
				{
					alert("该商品库存为零，请补库!");
					$("#wgoWgsId").val("");
					$("#wgsBiaozhunjia").val("");
					
				}
				else
				{
					$("#wgsKucun").html(kucun);
					$("#wgsBiaozhunjia").html(wgsBiaozhunjia);
				}
				
			},
			error:function()
			{
			alert("出错了!");
			}
		});
	}
	
	
}
/**
计算折扣
*/
function computeZhekou()
{
	var bzPrice = $("#wgsBiaozhunjia").html().trim();
	var price = $("#wgoOutPrice").val().trim();
	if(!bzPrice.isFloat())
	{
		return;
	}
	if(!price.isFloat())
	{
		$("#wgoOutPrice").val("");
		return;
	}
	var zhekou = ((parseFloat(price)/parseFloat(bzPrice))*10).toFixed(1);
	$("#wgoZhekou").val(zhekou);
	
}

/**
计算价格
*/
function computePrice()
{
	var bzPrice = $("#wgsBiaozhunjia").html().trim();
	var zhekou = $("#wgoZhekou").val().trim();
	if(!bzPrice.isFloat())
	{
		return;
	}
	if(!zhekou.isFloat())
	{
		$("#wgoZhekou").val("");
		return;
	}
	var price = (parseFloat(bzPrice)*(parseFloat(zhekou)/10)).toFixed(2);
	$("#wgoOutPrice").val(price);
	
}

function setGoods(id,$this)
{
	var select = document.getElementById(id);
	var fenlei = $($this).val();
	if(fenlei!=null&&fenlei.length>0&&fenlei.isNumber())
	{
		$.ajax({
			url:path+'/goods/getGoodsByFenlei/'+fenlei,
			data:{},
			dataType:'json',
			type:'post',
			success:function(data){
				var obj = eval(data);
				$(select).html('');
				var optionNull = $('<option value="">请选择</option>');
				$(select).append(optionNull);
				$(obj).each(
					function(index){
						var id = obj[index].WGS_ID;
						var name = obj[index].WGS_NAME;
						var option = $("<option></option>").val(id).html(name);
						$(select).append(option);
					}
				);
			}
		});
	}
	
	
}

function queryBysel(id,$this)
{
	setGoods(id,$this);
	querySubmit();	
}



