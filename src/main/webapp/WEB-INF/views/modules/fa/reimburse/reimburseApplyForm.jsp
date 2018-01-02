<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>报销申请</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		
		var row_count = 0; 
		function addNew() 
		{ 
			
		row_count++; 
		
		var table1 = $('#longDistanceTable'); 
		
		var firstTr = table1.find('tbody>tr:first'); 
		
		
		var row = $("<tr></tr>"); 
		var td = $("<td>"+row_count+"</td>"); 
		
		
		
		row.append($("<td><input type='text' name='count' style='width:100px' ></td>")); 
		row.append($("<td><input type='text' name='count' style='width:100px'></td>")); 
		row.append($("<td><input type='text' name='count' style='width:100px'></td>")); 
		row.append($("<td><input type='text' name='remark' style='width:100px'></td>")); 
		row.append($("<td><input type='text' name='amount' style='width:100px'></td>")); 
		
		row.append($("<input type='button' value='删除' onclick='delRow(this)'>")); 
			 
		table1.append(row); 
		} 
		
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs" id="updateTitle">
	    <li class="active"><a href="${ctx}/fa/reimbursement/toApplyForm">报销申请</a></li>
	</ul>
	<form:form id="reimburseApplyForm" modelAttribute="reimburseModel" target="mainFrame" action="${ctx}/fa/reimburse/commitApplyForm" method="post" class="breadcrumb form-search">
		<div >
			<label style="font-weight:bold">部门：${reimburseModel.officeName}</label>
			<label style="font-weight:bold">申请人：${reimburseModel.userName}</label>
		</div>
		<div ><label style="font-weight:bold">基本信息</label></div>
		<div>
			<table class="table table-striped table-bsordered table-condensed">
				<tr>
					<td><label style="font-weight:bold">日期</label></td>
					<td><form:input path="reimbursementMain.applyDate" pattern="yyyy-MM-dd HH:mm:ss" htmlEscape="false" maxlength="50" class="required"/></td>
					<td><label style="font-weight:bold">申报日期</label></td>
					<td><form:input path="reimbursementMain.beginDate" htmlEscape="false" maxlength="50" class="required"  /></td>
					<td><label style="font-weight:bold">申报期间</label></td>
					<td><form:input path="reimbursementMain.endDate" htmlEscape="false" maxlength="50" class="required"/></td>
					<td><form:input path="reimbursementMain.endDate" htmlEscape="false" maxlength="50" class="required"/></td>
				</tr>
		
				
			</table>
		</div>
	 	<div><label style="font-weight:bold">长途交通车费</label></div>
		<div>
			<table id="longDistanceTable" class="table table-striped table-bsordered table-condensed">
				<thead><tr><th>日期</th><th>项目编号</th><th>项目名称</th><th>摘要（内容、事由）</th><th>金额</th><th>删除</th></thead>
				<tbody>
				
				
					<tr>
							<%-- <td><form:input path="longDistanceList[${status.index}].createDate" htmlEscape="false" maxlength="50" class="required"/></td>
							<td><form:input path="longDistanceList[${status.index}].city" htmlEscape="false" maxlength="50" class="required"/></td>
							<td><form:input path="longDistanceList[${status.index}].projectId" htmlEscape="false" maxlength="50" class="required"/></td>
							<td><form:input path="longDistanceList[${status.index}].city" htmlEscape="false" maxlength="50" class="required"/></td>
							<td><form:input path="longDistanceList[${status.index}].projectId" htmlEscape="false" maxlength="50" class="required"/></td>
							<td><form:input path="longDistanceList[${status.index}].remark" htmlEscape="false" maxlength="50" class="required"/></td>
							<td><form:input path="longDistanceList[${status.index}].amount" htmlEscape="false" maxlength="50" class="required"/></td> --%>
						
				
						
						<td>
						<input type="button" value="添加" onclick="addNew();"> 
						</td>
						
					</tr>
						
					<%-- <c:forEach items="${businessTripModel.longDistanceList}" var="businessTripReservation" varStatus="status">
						
					</c:forEach> --%>
				</tbody>
			</table>
		</div>
		
		<div>
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="提交" style="margin-left:50px"/>
		</div>
	</form:form>
</body>
</html>