<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>费用报销任务列表</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {

        });

        function page(n, s) {
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").submit();
            return false;
        }
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li class="active"><a href="${ctx}/fa/reimburse/list${reimburseMain.id}">费用报销任务列表</a></li>
</ul>
<form:form id="searchForm"
           modelAttribute="reimburseMain" action="${ctx}/fa/reimburse/taskList" class="breadcrumb form-search">
   
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <ul class="ul-form">
        
        <li><label>状态：</label>
            <form:select path="status" class="input-medium">
				<form:option value="" label="请选择"/>
				<form:options items="${fns:getDictList('fa_reimburseMain_status')}" itemLabel="label" itemValue="value"/>
			</form:select>
			
        </li> 
        <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
        <li class="clearfix"></li>
    </ul>
</form:form>
<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
    	<th>任务标题</th>
        <th>申请日期</th>
        <th>部门</th>
        <th>申报人</th>
        <th>申报起始日</th>
        <th>申报结束日</th>
       <!--  <th>总金额</th> -->
        <th>状态</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="reimburseMain">
        <tr>
      		<td>${reimburseMain.act.vars.map.title}</td>
            <td><fmt:formatDate value="${reimburseMain.applyDate}" type="both" pattern="yyyy-MM-dd"/></td>
       
           <td>${reimburseMain.office.name}</td>
           <td>${reimburseMain.applicant.name}</td>
            <td><fmt:formatDate value="${reimburseMain.beginDate}" type="both" pattern="yyyy-MM-dd"/></td>
            <td><fmt:formatDate value="${reimburseMain.endDate}" type="both" pattern="yyyy-MM-dd"/></td>
          <%--   <td>${fns:abbr(reimburseMain.totalAmount,30)}</td> --%>
            <td>${fns:getDictLabel(reimburseMain.status,'fa_reimburseMain_status','')}</td>
            <td><a href="${ctx}/fa/reimburse/show?mainId=${reimburseMain.id}">查看 </a>
          
           <c:if test="${reimburseMain.status=='40'}">
           		 <a href="${ctx}/fa/reimburse/update?mainId=${reimburseMain.id}">修改 </a>
           		 <a href="${ctx}/fa/reimburse/delete?mainId=${reimburseMain.id}" onclick="return confirmx('确认要删除该报销订单吗？', this.href)">删除 </a>
            </c:if>
            <c:if test="${role=='FA' && reimburseMain.status=='20'}">
            	<a href="${ctx}/fa/reimburse/approve?id=${reimburseMain.id}&procInstId=${reimburseMain.procInstId}&status=${reimburseMain.status}">审批</a>
            </c:if>
             <c:if test="${role=='PM' && reimburseMain.status=='30'}">
            	<a href="${ctx}/fa/reimburse/approve?id=${reimburseMain.id}&procInstId=${reimburseMain.procInstId}&status=${reimburseMain.status}">审批</a>
            </c:if>
            </td> 
           
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>
