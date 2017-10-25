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
        
        function showExport(id) {
            top.$.jBox.confirm("确认要导出员工考勤数据吗？","系统提示",function(v,h,f){
				if(v=="ok"){	
					var href = "${ctx}/oa/attendance/showAllExport?id="+id;
					
					$("#attSearchListForm").attr("action",href); 					
					$("#attSearchListForm").submit();
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
	
	<form:form id="attSearchListForm" modelAttribute="attendanceShowAll" action="${ctx}/oa/attendance/showAll" method="post" class="breadcrumb form-search">
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
				<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			</ul>
	</form:form>
	<sys:message content="${message}"/>

	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr>
			<th>姓名</th>
			<th>考勤提交状态</th>
			<th>操作</th>
		</tr></thead>
		<tbody>
		<c:forEach items="${list}" var="attendance">
			<tr>
				<td>
				 <input id="attendanceIdShow" name="id" type="hidden" value="${attendance.id}"/>  	 
				${attendance.name}
				</td>
				<td>
					<c:if test="${empty attendance.processStatus}"></c:if>
					<c:if test="${attendance.processStatus ==1}"> 提交</c:if>
					<font color=red><c:if test="${attendance.processStatus ==2}"> 未提交</c:if></font>
					<c:if test="${attendance.processStatus ==3}"> 确认</c:if>
  					
				</td>
				<td>
				<c:if test="${empty attendance.processStatus}"><a href="" onclick="return confirmx('用户未创建!', this.href)">查看</a></c:if>
				
				<c:if test="${attendance.processStatus ==1}"> <a href="${ctx}/oa/attendance/show?id=${attendance.id}">查看</a></c:if>
				<c:if test="${attendance.processStatus ==2}"> <a href="" onclick="return confirmx('用户未提交!', this.href)">查看</a></c:if>
				<c:if test="${attendance.processStatus ==3}"> <a href="${ctx}/oa/attendance/show?id=${attendance.id}">查看</a></c:if>
				
				
				 <a id="btnAttExport1" onclick="showExport('${attendance.id}')"  >导出</a> 
				</td>

			</tr>
		</c:forEach>
		</tbody>
	</table>
</body>
</html>
