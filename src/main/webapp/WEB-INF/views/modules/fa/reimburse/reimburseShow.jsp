<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>报销费用综合申报表查看</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {

        });
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="${ctx}/fa/reimburse/list">费用报销列表</a></li>
    <li class="active"><a href="${ctx}/fa/reimburse/formList">报销费用综合申报表查看</a></li>
</ul>


<div style="background:#40abe9"><label style="font-weight:bold">基本信息</label></div>
	<div>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
	<!-- 主表-->
	    <thead>
	    <tr>
	        <th>部门</th>
	        <th>申报人</th>
	        <th>申报日期</th>
	        <th>申报开始时间</th>
	        <th>申报结束时间</th>
	    </tr>
	    </thead>
	    <tbody>
	        <tr>
	            <td>${reimburseMain.office.name}</td>
	            <td>${reimburseMain.applicant.name}</td>
	            <td><fmt:formatDate value="${reimburseMain.applyDate}" type="both" pattern="yyyy-MM-dd"/></td>
	            <td><fmt:formatDate value="${reimburseMain.beginDate}" type="both" pattern="yyyy-MM-dd"/></td>
	            <td><fmt:formatDate value="${reimburseMain.endDate}" type="both" pattern="yyyy-MM-dd"/></td>	
	        </tr>
	    </tbody>
	</table>
</div>

<div style="background:#40abe9"><label style="font-weight:bold">长途汽车费信息</label></div>
	<div>
		<table class="table table-striped table-bsordered table-condensed">
		   <thead>
			    <tr>
			        <th>日期</th>
			        <th>项目编号</th>
			        <th>项目名称</th>
			        <th>摘要（内容、事由）</th>
			        <th>金额</th>
			    </tr>
		    </thead>
			<tbody>
				<c:forEach items="${longDistanceList}" var="longDistance">
					<tr>
						<td><fmt:formatDate value="${longDistance.createDate}" pattern="yyyy-MM-dd"/></td>
						<td>${longDistance.project.itemNo}</td>
						<td>${longDistance.project.name}</td>
						<td>${longDistance.remark}</td>
						<td>${longDistance.amount}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
<div style="background:#40abe9"><label style="font-weight:bold">出租车费信息</label></div>
	<div>	
		<table id="contentTable1" class="table table-striped table-bordered table-condensed">
		    <thead>
		    <tr>
		        <th>日期</th>
		        <th>项目编号</th>
		        <th>项目名称</th>
		        <th>摘要（内容、事由）</th>
		        <th>时间</th>
		        <th>出发地点</th>
		        <th>到达地点</th>
		        <th>金额</th>
		    </tr>
		    </thead>
		    <tbody>
		   <c:forEach items="${taxiList}" var="taxi">
		        <tr>
		            <td><fmt:formatDate value="${taxi.createDate}" type="both" pattern="yyyy-MM-dd"/></td>
		            <td>${taxi.project.itemNo}</td>
					<td>${taxi.project.name}</td>
					<td>${taxi.remark}</td>
				    <td>${taxi.time}</td>
				    <td>${taxi.departureLocation}</td>
				    <td>${taxi.arrivedLocation}</td>
				    <td>${taxi.amount}</td>
		        </tr>
		    </c:forEach>
		    </tbody>
		</table>
	</div>
	
	<div style="background:#40abe9"><label style="font-weight:bold">招待费信息</label></div>
	<div>	
		<table id="contentTable1" class="table table-striped table-bordered table-condensed">
		    <thead>
		    <tr>
		        <th>日期</th>
		        <th>项目编号</th>
		        <th>项目名称</th>
		        <th>摘要（内容、事由）</th>
		        <th>受邀人员姓名</th>
		        <th>受邀人员职务</th>
		        <th>人数</th>
		        <th>金额</th>
		    </tr>
		    </thead>
		    <tbody>
		    <c:forEach items="${hospitalityList}" var="reimburseHospitality">
		        <tr>
		            <td><fmt:formatDate value="${reimburseHospitality.createDate}" type="both" pattern="yyyy-MM-dd"/></td>
		            <td>${reimburseHospitality.project.itemNo}</td>
		            <td>${reimburseHospitality.project.name}</td>
		            <td></td>
		            <td>${reimburseHospitality.inviteesName}</td>
		            <td>${reimburseHospitality.invitedPosition}</td>
		            <td>${reimburseHospitality.number}</td>
		            <td>${reimburseHospitality.amount}</td>
		        </tr>
		    </c:forEach>
		    </tbody>
		</table>
</div>

<div style="background:#40abe9"><label style="font-weight:bold">其他费信息</label></div>
	<div>
		<table class="table table-striped table-bsordered table-condensed">
		   <thead>
			    <tr>
			        <th>日期</th>
			        <th>项目编号</th>
			        <th>项目名称</th>
			        <th>摘要（内容、事由）</th>
			        <th>金额</th>
			    </tr>
		    </thead>
			<tbody>
				<c:forEach items="${otherList}" var="other">
					<tr>
						<td><fmt:formatDate value="${other.createDate}" pattern="yyyy-MM-dd"/></td>
						<td>${other.project.itemNo}</td>
						<td>${other.project.name}</td>
						<td>${other.remark}</td>
						<td>${other.amount}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
<div class="form-actions">
    <input id="btnSubmit" class="btn btn-primary" type="button" value="返 回" onclick="history.go(-1)"/>
</div>
</body>
</html>
