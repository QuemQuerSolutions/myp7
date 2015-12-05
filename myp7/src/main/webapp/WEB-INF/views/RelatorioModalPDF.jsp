<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<script type="text/javascript">
$(document).ready(function(){
	$('#relatorioModalPDF').on('show.bs.modal', function () {
		$(".modal-title").text($("#titulo-modal").val())
		$("#frame-report").attr("src", $("#link-modal").val());
	});

	$("#relatorioModalPDF").on('hidden.bs.modal', function (e) {
		$("#frame-report").attr("src", "");
	});
});

</script>

<input type="hidden" id="titulo-modal" value="" />
<input type="hidden" id="link-modal" value="" />
<div class="modal fade bs-example-modal-lg" id="relatorioModalPDF" >
	<div class="modal-dialog">
		<div class="modal-content modal-report-landscape">
			<div class="modal-header ${theme}">
				<button type="button" class="close" data-dismiss="modal" id="fechar" >&times;</button>
				<h4 class="modal-title"></h4>
			</div>
			<div class="modal-report">
				<iframe src="" id="frame-report"></iframe>
			</div>

			<div class="modal-footer"></div>
		</div>
	</div>
</div>
