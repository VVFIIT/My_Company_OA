<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>出差任务列表</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#RejectFAApproveForm").validate({
				submitHandler: function(form){
					loading('正在提交审批，请稍等...');
					form.submit();
				},
			});
			$("#AgreeFAApproveForm").validate({
				submitHandler: function(form){
					loading('正在提交审批，请稍等...');
					form.submit();
				},
			});
		});
		
		function setAgreeFAFlag(){
			$('#agreeFAFlag').val('yes');
			var agreeFAComment = $('#rejectFAComment').val();
			$('#agreeFAComment').val(agreeFAComment);
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/fa/businessTrip/toBusinessTripTaskList">出差任务列表</a></li>
		<li class="active"><a href="">出差财务审批</a></li>
	</ul>
	<form:form id="RejectFAApproveForm" modelAttribute="businessTripHotelHelper" action="${ctx}/fa/businessTrip/approveBusinessTripInfo_FA_Reject" method="post" class="breadcrumb form-search">
		<form:hidden id="FAFlag" path="businessTripApplication.FAFlag"/>
		<form:hidden id="id" path="businessTripApplication.id"/>
		<div style="background:#40abe9; margin-bottom:10px"><label style="font-weight:bold">您的意见</label></div>
		<div>
			<fieldset>
				<form:textarea id="rejectFAComment" path="businessTripApplication.FAComment" class="required"  maxlength="200" cssStyle="width:50%"/>
			</fieldset>
		</div>
		<div style="margin-top:10px; margin-left:20px">
			<input id="btnSubmit" class="btn btn-inverse" type="submit" value="驳 回" onclick="$('#FAFlag').val('no')"/>
		</div>
	</form:form>
		
	<form:form id="AgreeFAApproveForm" modelAttribute="businessTripHotelHelper" action="${ctx}/fa/businessTrip/approveBusinessTripInfo_FA_Agree" method="post" class="breadcrumb form-search">
		<form:hidden id="agreeFAFlag" path="businessTripApplication.FAFlag"/>
		<form:hidden id="id" path="businessTripApplication.id"/>
		<form:hidden id="agreeFAComment" path="businessTripApplication.FAComment"/>
		<c:if test="${businessTripHotelHelper.businessTripApplication.insertFlag == 'no'}">
			<c:forEach items="${businessTripReservationList}" var="businessTripReservation">
				<form:hidden path="businessTripReservation.id" value="${businessTripReservation.id}"/>
				<div style="background:#40abe9"><label style="font-weight:bold">${businessTripReservation.city}&nbsp;<fmt:formatDate value="${businessTripReservation.beginDate}" pattern="yyyy-MM-dd"/>--<fmt:formatDate value="${businessTripReservation.endDate}" pattern="yyyy-MM-dd"/>&nbsp;酒店信息</label></div>
				<table class="table table-striped table-bsordered table-condensed">
					<tr>
						<td><label style="font-weight:bold">订房类型</label></td>
						<td>
							<form:select path="businessTripHotel.type" class="input-xlarge required" style="width:220px">
								<form:option value="" label=""/>
								<form:option value="公司订房" label="公司订房"/>
								<form:option value="公司租房" label="公司租房"/>
							</form:select>
						</td>
					</tr>
					<tr>
						<td><label style="font-weight:bold">住宿酒店/小区名称</label></td>
						<td><form:input path="businessTripHotel.hotel" maxlength="50" class="required"/></td>
					</tr>
					<tr>
						<td><label style="font-weight:bold">具体地址</label></td>
						<td><form:input path="businessTripHotel.address" maxlength="500" class="required"/></td>
					</tr>
					<tr>
						<td><label style="font-weight:bold">联系人</label></td>
						<td><form:input path="businessTripHotel.contact" maxlength="50"/></td>
					</tr>
					<tr>
						<td><label style="font-weight:bold">联系电话</label></td>
						<td><form:input path="businessTripHotel.contactPhone" maxlength="50"/></td>
					</tr>
				</table>
			</c:forEach>
		</c:if>
		<c:if test="${businessTripHotelHelper.businessTripApplication.insertFlag == 'yes'}">
			<c:forEach items="${businessTripReservationInsertList}" var="businessTripReservationInsert">
				<form:hidden path="businessTripReservation.id" value="${businessTripReservationInsert.id}"/>
				<div style="background:#40abe9"><label style="font-weight:bold">追加：${businessTripReservationInsert.city}&nbsp;<fmt:formatDate value="${businessTripReservationInsert.beginDate}" pattern="yyyy-MM-dd"/>--<fmt:formatDate value="${businessTripReservationInsert.endDate}" pattern="yyyy-MM-dd"/>&nbsp;酒店信息</label></div>
				<table class="table table-striped table-bsordered table-condensed">
					<tr>
						<td><label style="font-weight:bold">订房类型</label></td>
						<td>
							<form:select path="businessTripHotel.type" class="input-xlarge required" style="width:220px">
								<form:option value="" label=""/>
								<form:option value="公司订房" label="公司订房"/>
								<form:option value="公司租房" label="公司租房"/>
							</form:select>
						</td>
					</tr>
					<tr>
						<td><label style="font-weight:bold">住宿酒店/小区名称</label></td>
						<td><form:input path="businessTripHotel.hotel" maxlength="50" class="required"/></td>
					</tr>
					<tr>
						<td><label style="font-weight:bold">具体地址</label></td>
						<td><form:input path="businessTripHotel.address" maxlength="500" class="required"/></td>
					</tr>
					<tr>
						<td><label style="font-weight:bold">联系人</label></td>
						<td><form:input path="businessTripHotel.contact" maxlength="50"/></td>
					</tr>
					<tr>
						<td><label style="font-weight:bold">联系电话</label></td>
						<td><form:input path="businessTripHotel.contactPhone" maxlength="50"/></td>
					</tr>
				</table>
			</c:forEach>
		</c:if>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="同 意" onclick="setAgreeFAFlag()"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>	
	</form:form>
</body>
</html>