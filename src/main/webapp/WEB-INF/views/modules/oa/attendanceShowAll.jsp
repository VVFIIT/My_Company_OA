<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>考勤列表</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {

        });

        function attendanceShow() {
            window.location.href = "${ctx}/oa/attendance/show";
        }
        function updateAttendance() {
            loading('正在跳转，请稍等...');
            $("#listForm").attr("action", "${ctx}/cms/category/updateSort");
            $("#listForm").submit();
        }
        
		function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/oa/attendance/showAllExact");
			$("#searchForm").submit();
	    	return false;
	    }
        
        function showExport(id) {
            top.$.jBox.confirm("确认要导出员工考勤数据吗？","系统提示",function(v,h,f){
				if(v=="ok"){	
					var href = "${ctx}/oa/attendance/showAllExport?id="+id;				
					$("#searchForm").attr("action",href); 					
					$("#searchForm").submit();
				}
			},{buttonsFocus:1});
			top.$('.jbox-body .jbox-icon').css('top','55px');
        }
        
    </script>
</head>
<body>
	<ul class="nav nav-tabs">
	    <li class="active"><a href="${ctx}/oa/attendance/showAll">考勤列表</a></li>
	</ul>
	
	<form:form id="searchForm" modelAttribute="attendanceShowAll" action="${ctx}/oa/attendance/showAllExact" method="post" class="breadcrumb form-search">
			 <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
			 <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
			 <sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
			<ul class="ul-form">
				<li><label>请选择年份：</label>
					<form:select path="year" class="input-medium">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('oa_year_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</li>
				<li><label>请选择月份：</label>
					<form:select path="month" class="input-medium">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('oa_month_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</li>
				<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" /></li>
			</ul>
	</form:form>
	<sys:message content="${message}"/>

	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr>                                                                               
			<th class="sort-column name">姓名</th>
			<th>考勤状态</th>
			<th>操作</th>
		</tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="attendance">
			<tr>
				<td>
				 	<input id="attendanceIdShow" name="id" type="hidden" value="${attendance.id}"/>  	 
					${attendance.name}
				<td>
					<font color=red><c:if test="${empty attendance.processStatus}">无</c:if></font>
					<font color=red><c:if test="${attendance.processStatus ==1}"> 未提交</c:if></font>
					<c:if test="${attendance.processStatus ==2}"> 提交</c:if>
					<c:if test="${attendance.processStatus ==3}"> 确认</c:if>	
				</td>
<<<<<<< HEAD
				<td>
					<%-- <c:if test="${empty attendance.processStatus}"><a href="${ctx}/oa/attendance/showAllExact?year=${attendance.year}&month=${attendance.month}" onclick="return confirmx('该用户未创建', this.href)">查看</a></c:if> --%>					
					<c:if test="${attendance.processStatus ==2}"> <a href="${ctx}/oa/attendance/show?id=${attendance.id}">查看</a></c:if>
					<%-- <c:if test="${attendance.processStatus ==1}"> <a href="${ctx}/oa/attendance/showAllExact?year=${attendance.year}&month=${attendance.month}" onclick="return confirmx('该用户未提交', this.href)">查看</a></c:if> --%>
					<c:if test="${attendance.processStatus ==3}"> <a href="${ctx}/oa/attendance/show?id=${attendance.id}">查看</a></c:if>
				
				 	<a id="btnAttExport1" href="#" style="${fns:getCheckStatusShow(attendance.processStatus)}" onclick="showExport('${attendance.id}')"  >导出</a> 
				 	<c:if test="${attendance.processStatus ==2}"> 
				 		<a id="sendBack" href="${ctx}/oa/attendance/sendBack?id=${attendance.id}&year=${attendance.year}&month=${attendance.month}" onclick="return confirmx('该用户未提交', this.href)">退回</a>
				 	</c:if>
=======
				<td>					
					<a id="showAttendance" style="${fns:getCheckStatusShow(attendance.processStatus)}" href="${ctx}/oa/attendance/show?id=${attendance.id}">查看</a>
				 	<a id="btnAttExport1" style="${fns:getCheckStatusShow(attendance.processStatus)}" onclick="showExport('${attendance.id}')"  >导出</a> 
				 	<a id="sendBack" style="${fns:getCheckStatusShow(attendance.processStatus)}" href="${ctx}/oa/attendance/sendBack?id=${attendance.id}&year=${attendance.year}&month=${attendance.month}" onclick="return confirmx('是否退回考勤？', this.href)">退回</a>				 	
>>>>>>> update attendanceShowAll.jsp
				</td>

			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
