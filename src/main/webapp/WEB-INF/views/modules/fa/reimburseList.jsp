<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>费用报销列表</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {

        });

        function page(n, s) {
            if (n) $("#pageNo").val(n);
            if (s) $("#pageSize").val(s);
            $("#searchForm").attr("action", "${ctx}/fa/reimburse/list");
            $("#searchForm").submit();
            return false;
        }

        <%--function noInsertMonth() {--%>
            <%--var mode = "${MODE}";--%>
            <%--if(mode == "noInsertMonth"){--%>
                <%--alert("您没有可以添加的月份！");--%>
            <%--}--%>
        <%--}--%>
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li class="active"><a href="${ctx}/fa/reimburse/list">费用报销列表</a></li>
</ul>
<form:form id="searchForm" action="${ctx}/fa/reimburse/list" class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
</form:form>
<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed" style="table-layout:fixed;">
    <thead>
    <tr>
        <th>申请日期</th>
        <th>部门</th>
        <c:if test="${reimburseMain.officeId ==2 || reimburseMain.officeId ==8}"><th style="display:none;">申报人</th></c:if>
        <c:if test="${reimburseMain.officeId !=2 || reimburseMain.officeId !=8}"><th>申报人</th></c:if>
        <th>申报起始日</th>
        <th>申报结束日</th>
        <th>总金额</th>
        <th>状态</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="reimburseMain">
        <tr>
            <td>${fns:abbr(reimburseMain.applyDate,30)}</td>
            <td></td>
            <td></td>
            <%--<c:forEach items="${pages.list}" var="user">--%>
                <%--<td>${fns:abbr(user.office,30)}</td>--%>
                <%--<td>${fns:abbr(user.name,30)}</td>--%>
            <%--</c:forEach>--%>
            <td>${fns:abbr(reimburseMain.beginDate,30)}</td>
            <td>${fns:abbr(reimburseMain.endDate,30)}</td>
            <td>${fns:abbr(reimburseMain.totalAmount,30)}</td>
            <td>${fns:getDictLabel(reimburseMain.status,'fa_reimburseMain_status','')}</td>
            <td><a href="${ctx}/oa/attendance/searchAttendanceInformation?id=${reimburseMain.id}">查看</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>
