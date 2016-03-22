<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>

<script>
	$('#myModal').on('hidden.bs.modal', function (e) {
  		// do something...
	});
	$('#myModal').on('show.bs.modal', function (e) {
  		
	});
	
</script>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"   aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		    	<h4 class="modal-title" id="myModalLabel">提示</h4>
			</div>
		    <div class="modal-body">
		    </div>
		    <div class="modal-footer">
		        <button type="button" class="btn btn-info" data-dismiss="modal">我知道了</button>
		    </div>
	    </div>
	</div>
</div>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="myModalLabel">提示</h4>
		      </div>
		      <div class="modal-body">
		        ...
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		      </div>
	    </div>
	  </div>
	</div>

