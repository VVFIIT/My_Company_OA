package com.thinkgem.jeesite.modules.finance.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.TaskService;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.modules.act.entity.Act;
import com.thinkgem.jeesite.modules.act.service.ActTaskService;
import com.thinkgem.jeesite.modules.act.utils.ActUtils;
import com.thinkgem.jeesite.modules.finance.dao.BusinessTripDao;
import com.thinkgem.jeesite.modules.finance.entity.BusinessTripAirTicket;
import com.thinkgem.jeesite.modules.finance.entity.BusinessTripApplication;
import com.thinkgem.jeesite.modules.finance.entity.BusinessTripHotel;
import com.thinkgem.jeesite.modules.finance.entity.BusinessTripHotelHelper;
import com.thinkgem.jeesite.modules.finance.entity.BusinessTripReservation;
import com.thinkgem.jeesite.modules.finance.entity.Project;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;



/**
 * 出差
 * 
 * @author Grace
 * @date 2017年12月25日 下午4:04:37
 * @since 1.0.0
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class BusinessTripService {
	
	@Autowired
	private BusinessTripDao businessTripDao;
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private ActTaskService actTaskService;
	
	/**
	 * 将出差信息插入db
	 * @author Meng
	 */
	@Transactional(readOnly = false)
	public void insertBusinessTripApplication(HttpServletRequest request, String applicationId) throws ParseException {
		BusinessTripApplication businessTripApplication = new BusinessTripApplication();
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		String togetherId = request.getParameter("togetherId");
		String phone = request.getParameter("phone");
		String IDNo = request.getParameter("IDNo");
		String projectName = request.getParameter("projectId");
		String type = request.getParameter("type");
		String remark = request.getParameter("remark");
		String begindate = request.getParameter(("beginDate"));
		String managerName = request.getParameter("managerId");
		User together = new User();
		together.setId(togetherId);
		businessTripApplication.setTogether(together);
		businessTripApplication.setPhone(phone);
		businessTripApplication.setIDNo(IDNo);
		businessTripApplication.setProject(businessTripDao.getProjectByName(projectName));
		businessTripApplication.setType(type);
		businessTripApplication.setRemark(remark);
		if(begindate != null && !"".equals(begindate)) {
			businessTripApplication.setBeginDate(sdf.parse(begindate));
		}
		businessTripApplication.setManager(businessTripDao.getUserByName(managerName));
		businessTripApplication.setId(applicationId);
		User user = UserUtils.getUser();
		businessTripApplication.setOffice(user.getOffice());
		businessTripApplication.setApplicant(user);
		businessTripDao.insertBusinessTripApplication(businessTripApplication);
	}

	/**
	 * 将订房信息插入db
	 * @author Meng
	 */
	@Transactional(readOnly = false)
	public void insertBusinessTripReservation(HttpServletRequest request, String applicationId) throws ParseException {
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		String reservationEveryNum = request.getParameter("reservationEveryNum");
		String[] reservationNum = reservationEveryNum.split("");
		for (int i = 0; i < reservationNum.length; i++) {
			BusinessTripReservation businessTripReservation = new BusinessTripReservation();
			String reservationType = request.getParameter(("reservationType"+reservationNum[i]));
			String reservationCity = request.getParameter(("reservationCity"+reservationNum[i]));
			String reservationWorkPlace = request.getParameter(("reservationWorkPlace"+reservationNum[i]));
			String reservationBeginDate = request.getParameter(("reservationBeginDate"+reservationNum[i]));
			String reservationEndDate = request.getParameter(("reservationEndDate"+reservationNum[i]));
			String reservationDays = request.getParameter(("reservationDays"+reservationNum[i]));
			businessTripReservation.setId(UUID.randomUUID().toString());
			businessTripReservation.setApplicationId(applicationId);
			businessTripReservation.setType(reservationType);
			businessTripReservation.setCity(reservationCity);
			businessTripReservation.setWorkPlace(reservationWorkPlace);
			if(reservationBeginDate != null && !"".equals(reservationBeginDate)) {
				businessTripReservation.setBeginDate(sdf.parse(reservationBeginDate));
			}
			if(reservationEndDate != null && !"".equals(reservationEndDate)) {
				businessTripReservation.setEndDate(sdf.parse(reservationEndDate));
			}
			if(reservationDays != null && !"".equals(reservationDays)) {
				businessTripReservation.setDays(Integer.valueOf(reservationDays));
			}
			businessTripDao.insertBusinessTripReservation(businessTripReservation);
		}
	}

	/**
	 * 将机票信息插入db
	 * @author Meng
	 */
	@Transactional(readOnly = false)
	public void insertBusinessTripAirTicket(HttpServletRequest request, String applicationId) throws ParseException {
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String airTicketEveryNum = request.getParameter("airTicketEveryNum");
		String[] airTicketNum = airTicketEveryNum.split("");
		for (int i = 0; i < airTicketNum.length; i++) {
			BusinessTripAirTicket businessTripAirTicket = new BusinessTripAirTicket();
			String airTicketFlyDate = request.getParameter(("airTicketFlyDate"+airTicketNum[i]));
			String airTicketAmount = request.getParameter(("airTicketAmount"+airTicketNum[i]));
			String airTicketStartLocation = request.getParameter(("airTicketStartLocation"+airTicketNum[i]));
			String airTicketArrivedLocation = request.getParameter(("airTicketArrivedLocation"+airTicketNum[i]));
			String airTicketRemark = request.getParameter(("airTicketRemark"+airTicketNum[i]));
			businessTripAirTicket.setId(UUID.randomUUID().toString());
			businessTripAirTicket.setApplicationId(applicationId);
			if(airTicketFlyDate != null && !"".equals(airTicketFlyDate)) {
				businessTripAirTicket.setFlyDate(sdf.parse(airTicketFlyDate));
			}
			if(airTicketAmount != null && !"".equals(airTicketAmount)) {
				businessTripAirTicket.setAmount(new BigDecimal(airTicketAmount));
			}
			businessTripAirTicket.setStartLocation(airTicketStartLocation);
			businessTripAirTicket.setArrivedLocation(airTicketArrivedLocation);
			businessTripAirTicket.setRemark(airTicketRemark);
			businessTripDao.insertBusinessTripAirTicket(businessTripAirTicket);
		}
	}
	
	/**
	 * 获取出差任务列表
	 * @author Meng
	 */
	public List<BusinessTripApplication> businessTripTaskList(BusinessTripApplication application){
		List<BusinessTripApplication> resultList = new ArrayList<BusinessTripApplication>();
		// 获取当前用户
		String userId = UserUtils.getUser().getLoginName();
		TaskQuery todoTaskQuery = taskService.createTaskQuery().taskAssignee(userId).active().includeProcessVariables()
				.orderByTaskCreateTime().asc();
		// 获取当前用户的任务列表
		List<Task> todoList = todoTaskQuery.list();
		// 循环任务列表，将处理好的vehicleProcess对象放到resultList中
		for (Task task : todoList) {
			// 获取任务的procInsId
			String procInstId = ((TaskEntity) task).getProcessInstanceId();
			BusinessTripApplication businessTripApplication = new BusinessTripApplication();
			businessTripApplication.setProcInstId(procInstId);
			if(application.getProject()!=null) {
				businessTripApplication.setProject(application.getProject());
			}
			if(application.getApplicant()!=null) {
				businessTripApplication.setApplicant(application.getApplicant());
			}
			// 通过procInsId获取相应对象
			List<BusinessTripApplication> businessTripApplicationList = businessTripDao.findList(businessTripApplication);
			if(businessTripApplicationList!=null&&businessTripApplicationList.size()>0){
				// 正常情况下vehicleProcessList中只有一个vehicleProcess对象
				businessTripApplication = businessTripApplicationList.get(0);
				// 将task和流程变量都赋给这个vehicleProcess对象
				Act act = new Act();
				act.setTask(task);
				act.setVars(task.getProcessVariables());
				businessTripApplication.setAct(act);
				// 将该对象放到resultList中
				resultList.add(businessTripApplication);
			}
		}
		return resultList;
	}

	/**
	 * 翻页
	 * @author Meng
	 */
	public Page<BusinessTripApplication> findPage(Page<BusinessTripApplication> page,
			List<BusinessTripApplication> list) {
		// 将数据个数赋给page
		page.setCount(list.size());
		// 将数据list赋给page
		page.setList(list);
		return page;
	}

	/**
	 * 启动出差工作流
	 * @author Meng
	 */
	@Transactional(readOnly = false)
	public String startBusinessTripProcess(String applicationId) {
		// 流程标题
		String title = "出差审批-经理";
		Map<String, Object> startVars = Maps.newHashMap();
		startVars.put("businessTripApplicant", UserUtils.getUser().getLoginName());
		// 启动流程 获取procInsId
		String procInsId = actTaskService.startProcess(ActUtils.PD_BUSINESSTRIP[0], ActUtils.PD_BUSINESSTRIP[1],
				applicationId, title, startVars);
		// 更新主表的procInsId
		businessTripDao.updateProcInsIdByApplicationId(procInsId, applicationId);
		return procInsId;
	}

	/**
	 * complete出差申请工作流
	 * @author Meng
	 */
	@Transactional(readOnly = false)
	public void completeApplicantProcess(String procInsId, String applicationId) {
		// 流程标题
		String title = "出差审批-经理";
		// 通过ProcInsId获取TaskId
		String taskId = businessTripDao.findTaskIdByProcInsId(procInsId, "BusinessTripApply");
		// 创建流程变量
		Map<String, Object> vars = Maps.newHashMap();
		// 添加流程变量-标题
		vars.put("title", title);
		// 完成流程节点
		actTaskService.complete(taskId, procInsId, "", vars);
		// 更新流程状态
		businessTripDao.updateStatus("10", applicationId);
	}

	/**
	 * 修改页面出差信息默认值
	 * @author Meng
	 */
	public BusinessTripApplication getBusinessTripApplicationInfo(String id) {
		BusinessTripApplication businessTripApplication = businessTripDao.getBusinessTripApplicationInfo(id);
		return businessTripApplication;
	}

	/**
	 * 修改页面订房信息默认值
	 * @author Meng
	 */
	public List<BusinessTripReservation> getBusinessTripReservationList(String id) {
		List<BusinessTripReservation> businessTripReservationList = businessTripDao.getBusinessTripReservationList(id);
		return businessTripReservationList;
	}
	
	/**
	 * 修改页面机票信息默认值
	 * @author Meng
	 */
	public List<BusinessTripAirTicket> getBusinessTripAirTicketList(String id) {
		List<BusinessTripAirTicket> businessTripAirTicketList = businessTripDao.getBusinessTripAirTicketList(id);
		return businessTripAirTicketList;
	}
	
	/**
	 * 修改页面酒店信息默认值
	 * @author Meng
	 */
	public BusinessTripHotel getBusinessTripHotel(String id) {
		BusinessTripHotel businessTripHotel = businessTripDao.getBusinessTripHotel(id);
		return businessTripHotel;
	}
	
	/**
	 * 获取所有项目名称
	 * @author Meng
	 */
	public List<String> getBusinessTripProjectNameList() {
		List<String> projectNameList = businessTripDao.getBusinessTripProjectNameList();
		return projectNameList;
	}

	/**
	 * 出差审批--经理
	 * @author Meng
	 */
	@Transactional(readOnly = false)
	public void managerApprove(BusinessTripApplication businessTripApplication) {
		String applicationId = businessTripApplication.getId();
		String managerFlag = businessTripApplication.getManagerFlag();
		String ManagerComment = businessTripApplication.getManagerComment();
		businessTripDao.updateManagerApproveInfo(managerFlag, ManagerComment, applicationId);
		
	}

	/**
	 * complete出差经理审批工作流
	 * @author Meng
	 */
	@Transactional(readOnly = false)
	public void completeManagerProcess(String applicationId) {
		// 流程标题
		String title = "出差审批-财务";
		String procInsId = businessTripDao.getBusinessTripApplicationInfo(applicationId).getProcInstId();
		// 通过ProcInsId获取TaskId
		String taskId = businessTripDao.findTaskIdByProcInsId(procInsId, "BusinessTripManagerApproval");
		// 创建流程变量
		Map<String, Object> vars = Maps.newHashMap();
		// 添加流程变量-标题
		vars.put("title", title);
		vars.put("pass", "1");
		// 完成流程节点
		actTaskService.complete(taskId, procInsId, "", vars);
		// 更新流程状态
		businessTripDao.updateStatus("20", applicationId);
	}
	
	/**
	 * 出差审批--财务
	 * @author Meng
	 */
	@Transactional(readOnly = false)
	public void FAApprove(BusinessTripHotelHelper businessTripHotelHelper) {
		BusinessTripApplication businessTripApplication = businessTripHotelHelper.getBusinessTripApplication();
		String applicationId = businessTripApplication.getId();
		String FAFlag = businessTripApplication.getFAFlag();
		String FAComment = businessTripApplication.getFAComment();
		businessTripDao.updateFAApproveInfo(FAFlag, FAComment, applicationId);
		BusinessTripHotel businessTripHotel = businessTripHotelHelper.getBusinessTripHotel();
		businessTripHotel.setId(UUID.randomUUID().toString());
		businessTripHotel.setApplicationId(applicationId);
		businessTripDao.insertBusinessTripHotel(businessTripHotel);
	}

	/**
	 * complete出差财务审批工作流
	 * @author Meng
	 */
	@Transactional(readOnly = false)
	public void completeFAProcess(String applicationId) {
		String procInsId = businessTripDao.getBusinessTripApplicationInfo(applicationId).getProcInstId();
		// 通过ProcInsId获取TaskId
		String taskId = businessTripDao.findTaskIdByProcInsId(procInsId, "BusinessTripFinancialApproval");
		// 创建流程变量
		Map<String, Object> vars = Maps.newHashMap();
		// 添加流程变量-标题
		vars.put("pass", "1");
		// 完成流程节点
		actTaskService.complete(taskId, procInsId, "", vars);
		// 更新流程状态
		businessTripDao.updateStatus("50", applicationId);
	}
	
	/**
	 * 查找db中所有的出差信息
	 * @author Meng
	 */
	public List<BusinessTripApplication> businessTripInfoList(User loginUser, String projectName) {
		BusinessTripApplication businessTripApplication = new BusinessTripApplication();
		Project project = new Project();
		project.setName(projectName);
		businessTripApplication.setProject(project);
		if (!"yzm".equals(loginUser.getLoginName()) && !"zhe.jiang".equals(loginUser.getLoginName())) {
			businessTripApplication.setApplicant(loginUser);
		}
		List<BusinessTripApplication> businessTripInfoList = businessTripDao.findList(businessTripApplication);
		return businessTripInfoList;
	}

	

	

	
	
	
}
