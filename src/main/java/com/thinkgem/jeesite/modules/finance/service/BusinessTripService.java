package com.thinkgem.jeesite.modules.finance.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.mail.MessagingException;
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
import com.thinkgem.jeesite.modules.oa.helper.EmailUtil;
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
		String enddate = request.getParameter("endDate");
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
		if(enddate != null && !"".equals(enddate)) {
			businessTripApplication.setEndDate(sdf.parse(enddate));
		}
		businessTripApplication.setId(applicationId);
		User user = UserUtils.getUser();
		businessTripApplication.setOffice(user.getOffice());
		businessTripApplication.setApplicant(user);
		businessTripApplication.setInsertFlag("no");
		businessTripDao.insertBusinessTripApplication(businessTripApplication);
	}
	
	/**
	 * 修改出差信息
	 * @author Meng
	 */
	@Transactional(readOnly = false)
	public void updateBusinessTripApplication(HttpServletRequest request, String businessTripApplicationId) throws ParseException {
		BusinessTripApplication businessTripApplication = businessTripDao.getBusinessTripApplicationInfo(businessTripApplicationId);
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		String togetherId = request.getParameter("togetherId");
		String phone = request.getParameter("phone");
		String IDNo = request.getParameter("IDNo");
		String projectName = request.getParameter("projectId");
		String type = request.getParameter("type");
		String remark = request.getParameter("remark");
		String begindate = request.getParameter(("beginDate"));
		String enddate = request.getParameter("endDate");
		if(togetherId!=null && !togetherId.equals(businessTripApplication.getTogether().getName())) {
			User together = new User();
			together.setId(togetherId);
			businessTripApplication.setTogether(together);
		} else if(togetherId==null) {
			businessTripApplication.setTogether(new User());
		}
		businessTripApplication.setPhone(phone);
		businessTripApplication.setIDNo(IDNo);
		businessTripApplication.setProject(businessTripDao.getProjectByName(projectName));
		businessTripApplication.setType(type);
		businessTripApplication.setRemark(remark);
		if(begindate != null && !"".equals(begindate)) {
			businessTripApplication.setBeginDate(sdf.parse(begindate));
		}
		if(enddate != null && !"".equals(enddate)) {
			businessTripApplication.setEndDate(sdf.parse(enddate));
		}
		businessTripDao.updateBusinessTripApplication(businessTripApplication);
	}

	/**
	 * 将订房信息插入db
	 * @author Meng
	 */
	@Transactional(readOnly = false)
	public void insertBusinessTripReservation(HttpServletRequest request, String applicationId) throws ParseException {
		String reservationEveryNum = request.getParameter("reservationEveryNum");
		if(!"".equals(reservationEveryNum) && !"0".equals(reservationEveryNum)) {
			SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
			String insertFlag = request.getParameter("insertFlag");
			String[] reservationNum = reservationEveryNum.split("");
			for (int i = 0; i < reservationNum.length; i++) {
				BusinessTripReservation businessTripReservation = new BusinessTripReservation();
				String reservationCity = request.getParameter(("reservationCity"+reservationNum[i]));
				String reservationWorkPlace = request.getParameter(("reservationWorkPlace"+reservationNum[i]));
				String reservationBeginDate = request.getParameter(("reservationBeginDate"+reservationNum[i]));
				String reservationEndDate = request.getParameter(("reservationEndDate"+reservationNum[i]));
				String reservationDays = request.getParameter(("reservationDays"+reservationNum[i]));
				String reservationRemark = request.getParameter(("reservationRemark"+reservationNum[i]));
				businessTripReservation.setId(UUID.randomUUID().toString());
				businessTripReservation.setApplicationId(applicationId);
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
				businessTripReservation.setRemark(reservationRemark);
				if(!"".equals(insertFlag) && insertFlag != null) {
					businessTripReservation.setInsertFlag(insertFlag);
				} else {
					businessTripReservation.setInsertFlag("no");
				}
				businessTripDao.insertBusinessTripReservation(businessTripReservation);
			}
		}
	}
	
	/**
	 * 修改订房信息
	 * @author Meng
	 */
	@Transactional(readOnly = false)
	public void updateBusinessTripReservation(HttpServletRequest request, String businessTripApplicationId) throws ParseException {
		String insertFlag = businessTripDao.getBusinessTripApplicationInfo(businessTripApplicationId).getInsertFlag();
		if ("yes".equals(insertFlag)) {
			deleteBusinessTripInsertReservation(businessTripApplicationId);
			insertBusinessTripReservation(request, businessTripApplicationId);
		} else {
			deleteBusinessTripReservation(businessTripApplicationId);
			insertBusinessTripReservation(request, businessTripApplicationId);
		}
	}
	
	/**
	 * 删除出差信息(新建申请)
	 * @author Meng
	 */
	@Transactional(readOnly = false)
	public void deleteBusinessTripApplication(String id) {
		businessTripDao.deleteBusinessTripApplication(id);
	}
	
	/**
	 * 删除订房信息(新建申请)
	 * @author Meng
	 */
	@Transactional(readOnly = false)
	public void deleteBusinessTripReservation(String businessTripApplicationId) {
		businessTripDao.deleteBusinessTripReservation(businessTripApplicationId);
	}
	
	/**
	 * 删除订房信息(追加申请)
	 * @author Meng
	 */
	@Transactional(readOnly = false)
	public void deleteBusinessTripInsertReservation(String businessTripApplicationId) {
		businessTripDao.deleteBusinessTripInsertReservation(businessTripApplicationId);
	}

	/**
	 * 将机票信息插入db
	 * @author Meng
	 */
	@Transactional(readOnly = false)
	public void insertBusinessTripAirTicket(HttpServletRequest request, String applicationId) throws ParseException {
		String airTicketEveryNum = request.getParameter("airTicketEveryNum");
		if(!"".equals(airTicketEveryNum) && !"0".equals(airTicketEveryNum)) {
			SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String insertFlag = request.getParameter("insertFlag");
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
				if(!"".equals(insertFlag) && insertFlag != null) {
					businessTripAirTicket.setInsertFlag(insertFlag);
				} else {
					businessTripAirTicket.setInsertFlag("no");
				}
				businessTripDao.insertBusinessTripAirTicket(businessTripAirTicket);
			}
		}
	}
	
	/**
	 * 修改机票信息
	 * @author Meng
	 */
	@Transactional(readOnly = false)
	public void updateBusinessTripAirTicket(HttpServletRequest request, String businessTripApplicationId) throws ParseException {
		String insertFlag = businessTripDao.getBusinessTripApplicationInfo(businessTripApplicationId).getInsertFlag();
		if ("yes".equals(insertFlag)) {
			deleteBusinessInsertTripAirTicket(businessTripApplicationId);
			insertBusinessTripAirTicket(request, businessTripApplicationId);
		} else {
			deleteBusinessTripAirTicket(businessTripApplicationId);
			insertBusinessTripAirTicket(request, businessTripApplicationId);
		}
	}
	
	/**
	 * 删除机票信息(新建申请)
	 * @author Meng
	 */
	@Transactional(readOnly = false)
	public void deleteBusinessTripAirTicket(String businessTripApplicationId) {
		businessTripDao.deleteBusinessTripAirTicket(businessTripApplicationId);
	}
	
	/**
	 * 删除机票信息(追加申请)
	 * @author Meng
	 */
	@Transactional(readOnly = false)
	public void deleteBusinessInsertTripAirTicket(String businessTripApplicationId) {
		businessTripDao.deleteBusinessInsertTripAirTicket(businessTripApplicationId);
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
		String title = "出差审批";
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
		String insertFlag = businessTripDao.getBusinessTripApplicationInfo(applicationId).getInsertFlag();
		// 流程标题
		String title = null;
		if("yes".equals(insertFlag)) {
			title = "追加审批-经理";
		}else {
			title = "出差审批-经理";
		}
		// 通过ProcInsId获取TaskId
		String taskId = businessTripDao.findTaskIdByProcInsId(procInsId, "BusinessTripApply");
		// 创建流程变量
		Map<String, Object> vars = Maps.newHashMap();
		// 添加流程变量-标题
		vars.put("title", title);
		// 完成流程节点
		actTaskService.complete(taskId, procInsId, "", vars);
		//发送邮件
		try {
			String name = businessTripDao.getBusinessTripApplicationInfo(applicationId).getApplicant().getName();
			String emailTitle = name+"出差申请";
			String emailContent = "你好，"+name+"向您发起了出差申请！请审批。";
			EmailUtil.sendTextEmail("office@hongshenol.com", "zhenming.yang@hongshenol.com", emailTitle, emailContent);
		} catch (IOException | MessagingException e) {
			e.printStackTrace();
		}
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
	 * 追加修改页面订房信息默认值
	 * @author Meng
	 */
	public List<BusinessTripReservation> getBusinessTripReservationInsertList(String id) {
		List<BusinessTripReservation> businessTripReservationInsertList = businessTripDao.getBusinessTripReservationInsertList(id);
		return businessTripReservationInsertList;
	}
	
	/**
	 * 除了追加以外的修改页面订房信息默认值
	 * @author Meng
	 */
	public List<BusinessTripReservation> getBusinessTripReservationNoInsertList(String id) {
		List<BusinessTripReservation> businessTripReservationNoInsertList = businessTripDao.getBusinessTripReservationNoInsertList(id);
		return businessTripReservationNoInsertList;
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
	 * 追加修改页面机票信息默认值
	 * @author Meng
	 */
	public List<BusinessTripAirTicket> getBusinessTripAirTicketInsertList(String id) {
		List<BusinessTripAirTicket> businessTripAirTicketInsertList = businessTripDao.getBusinessTripAirTicketInsertList(id);
		return businessTripAirTicketInsertList;
	}
	
	/**
	 * 除了追加以外的修改页面机票信息默认值
	 * @author Meng
	 */
	public List<BusinessTripAirTicket> getBusinessTripAirTicketNoInsertList(String id) {
		List<BusinessTripAirTicket> businessTripAirTicketNoInsertList = businessTripDao.getBusinessTripAirTicketNoInsertList(id);
		return businessTripAirTicketNoInsertList;
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
	public void completeManagerProcess(String managerFlag, String applicationId) {
		String insertFlag = businessTripDao.getBusinessTripApplicationInfo(applicationId).getInsertFlag();
		String procInsId = businessTripDao.getBusinessTripApplicationInfo(applicationId).getProcInstId();
		// 通过ProcInsId获取TaskId
		String taskId = businessTripDao.findTaskIdByProcInsId(procInsId, "BusinessTripManagerApproval");
		// 创建流程变量
		Map<String, Object> vars = Maps.newHashMap();
		if ("yes".equals(managerFlag)) {
			// 流程标题
			String title = null;
			if ("yes".equals(insertFlag)) {
				title = "出差追加审批-财务";
			} else {
				title = "出差审批-财务";
			}
			// 添加流程变量-标题
			vars.put("title", title);
			// 添加流程变量-审批同意标识
			vars.put("pass", "1");
		}else {
			// 流程标题
			String title = null;
			if ("yes".equals(insertFlag)) {
				title = "出差追加审批-经理驳回";
			} else {
				title = "出差审批-经理驳回";
			}
			// 添加流程变量-标题
			vars.put("title", title);
			// 添加流程变量-审批同意标识
			vars.put("pass", "0");
		}
		// 完成流程节点
		actTaskService.complete(taskId, procInsId, "", vars);
		String name = businessTripDao.getBusinessTripApplicationInfo(applicationId).getApplicant().getName();
		String email = businessTripDao.getBusinessTripApplicationInfo(applicationId).getApplicant().getEmail();
		if ("0".equals(vars.get("pass"))) {
			//发送邮件
			try {
				String emailTitle = "出差申请驳回";
				String emailContent = "您好，经理驳回了您发起的出差申请！请查看。";
				EmailUtil.sendTextEmail("office@hongshenol.com", email, emailTitle, emailContent);
			} catch (IOException | MessagingException e) {
				e.printStackTrace();
			}
		} else {
			//发送邮件
			try {
				String emailTitle = name+"出差申请";
				String emailContent = "您好，"+name+"向您发起了出差申请！请审批。";
				EmailUtil.sendTextEmail("office@hongshenol.com", "zhe.jiang@hongshenol.com", emailTitle, emailContent);
			} catch (IOException | MessagingException e) {
				e.printStackTrace();
			}
		}
		
		// 更新流程状态
		if ("yes".equals(managerFlag)) {
			businessTripDao.updateStatus("20", applicationId);
		} else {
			businessTripDao.updateStatus("30", applicationId);
		}
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
		if ("yes".equals(FAFlag)) {
			BusinessTripReservation businessTripReservation = businessTripHotelHelper.getBusinessTripReservation();
			if (businessTripReservation!=null) {
				BusinessTripHotel businessTripHotel = businessTripHotelHelper.getBusinessTripHotel();
				String[] idArray = businessTripReservation.getId().split(",");
				for (int i = 0; i < idArray.length; i++) {
					BusinessTripHotel hotel = new BusinessTripHotel();
					hotel.setId(UUID.randomUUID().toString());
					hotel.setReservationId(idArray[i]);
					String insertFlag = businessTripDao.getBusinessTripReservation(idArray[i]).getInsertFlag();
					if (businessTripHotel.getType().split(",").length>i) {
						hotel.setType(businessTripHotel.getType().split(",")[i]);
					}
					if (businessTripHotel.getHotel().split(",").length>i) {
						hotel.setHotel(businessTripHotel.getHotel().split(",")[i]);
					}
					if (businessTripHotel.getAddress().split(",").length>i) {
						hotel.setAddress(businessTripHotel.getAddress().split(",")[i]);
					}
					if (businessTripHotel.getContact().split(",").length>i) {
						hotel.setContact(businessTripHotel.getContact().split(",")[i]);
					}
					if (businessTripHotel.getContactPhone().split(",").length>i) {
						hotel.setContactPhone(businessTripHotel.getContactPhone().split(",")[i]);
					}
					if ("yes".equals(insertFlag)) {
						hotel.setInsertFlag("yes");
					} else {
						hotel.setInsertFlag("no");
					}
					if(hotel.getHotel()!=null || hotel.getAddress()!=null) {
						businessTripDao.insertBusinessTripHotel(hotel);
					}
				}
			}
			
		}
	}

	/**
	 * complete出差财务审批工作流
	 * @author Meng
	 */
	@Transactional(readOnly = false)
	public void completeFAProcess(String FAFlag, String applicationId) {
		String procInsId = businessTripDao.getBusinessTripApplicationInfo(applicationId).getProcInstId();
		String insertFlag = businessTripDao.getBusinessTripApplicationInfo(applicationId).getInsertFlag();
		// 通过ProcInsId获取TaskId
		String taskId = businessTripDao.findTaskIdByProcInsId(procInsId, "BusinessTripFinancialApproval");
		// 创建流程变量
		Map<String, Object> vars = Maps.newHashMap();
		if ("yes".equals(FAFlag)) {
			// 添加流程变量-审批标识
			vars.put("pass", "1");
		} else {
			// 流程标题
			String title = null;
			if ("yes".equals(insertFlag)) {
				title = "出差追加审批-财务驳回";
			} else {
				title = "出差审批-财务驳回";
			}
			// 添加流程变量-标题
			vars.put("title", title);
			// 添加流程变量-标题
			vars.put("pass", "0");
		}
		// 完成流程节点
		actTaskService.complete(taskId, procInsId, "", vars);
		String email = businessTripDao.getBusinessTripApplicationInfo(applicationId).getApplicant().getEmail();
		if ("0".equals(vars.get("pass"))) {
			//发送邮件
			try {
				String emailTitle = "出差申请驳回";
				String emailContent = "您好，财务驳回了您发起的出差申请！请查看。";
				EmailUtil.sendTextEmail("office@hongshenol.com", email, emailTitle, emailContent);
			} catch (IOException | MessagingException e) {
				e.printStackTrace();
			}
		} else {
			//发送邮件
			try {
				String emailTitle = "出差申请通过";
				String emailContent = "您好，您的出差申请已通过！";
				EmailUtil.sendTextEmail("office@hongshenol.com", email, emailTitle, emailContent);
			} catch (IOException | MessagingException e) {
				e.printStackTrace();
			}
		}
		// 更新流程状态
		if ("yes".equals(FAFlag)) {
			businessTripDao.updateStatus("50", applicationId);
		} else {
			businessTripDao.updateStatus("40", applicationId);
		}
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
		if (!"zhenming.yang".equals(loginUser.getLoginName()) && !"zhe.jiang".equals(loginUser.getLoginName())) {
			businessTripApplication.setApplicant(loginUser);
		}
		List<BusinessTripApplication> businessTripInfoList = businessTripDao.findList(businessTripApplication);
		return businessTripInfoList;
	}

	/**
	 * 通过applicationId查找procInsId
	 * @author Meng
	 */
	public String findProcInsIdByApplicationId(String businessTripApplicationId) {
		String procInsId = businessTripDao.getBusinessTripApplicationInfo(businessTripApplicationId).getProcInstId();
		return procInsId;
	}
	
	/**
	 * 出差申请追加时清空主表的审批意见
	 * @author Meng
	 */
	@Transactional(readOnly = false)
	public void resetComment(HttpServletRequest request, String applicationId) {
		BusinessTripApplication businessTripApplication = getBusinessTripApplicationInfo(applicationId);
		String enddate = request.getParameter("endDate");
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		if(enddate != null && !"".equals(enddate)) {
			try {
				businessTripApplication.setEndDate(sdf.parse(enddate));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		businessTripApplication.setManagerFlag(null);
		businessTripApplication.setManagerComment(null);
		businessTripApplication.setFAFlag(null);
		businessTripApplication.setFAComment(null);
		businessTripApplication.setInsertFlag("yes");
		businessTripDao.updateBusinessTripApplication(businessTripApplication);
	}

	/**
	 * 修改出差追加信息
	 * @author Meng
	 */
	@Transactional(readOnly = false)
	public void updateBusinessTripInsertApplication(HttpServletRequest request, String businessTripApplicationId) throws ParseException {
		BusinessTripApplication businessTripApplication = businessTripDao.getBusinessTripApplicationInfo(businessTripApplicationId);
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		String enddate = request.getParameter("endDate");
		if(enddate != null && !"".equals(enddate)) {
			businessTripApplication.setEndDate(sdf.parse(enddate));
		}
		businessTripDao.updateBusinessTripApplication(businessTripApplication);
	}

	/**
	 * 如果新追加申请，就将旧追加的insertFlag变为no
	 * @author Meng
	 */
	@Transactional(readOnly = false)
	public void resetInsertFlag(String applicationId) {
		List<BusinessTripReservation> businessTripReservationList = getBusinessTripReservationList(applicationId);
		for (BusinessTripReservation businessTripReservation : businessTripReservationList) {
			if ("yes".equals(businessTripReservation.getInsertFlag())) {
				businessTripDao.updateReservationInsertFlag(businessTripReservation.getId());
				String hotelId = getBusinessTripHotel(businessTripReservation.getId()).getId();
				businessTripDao.updateHotelInsertFlag(hotelId);
			}
		}
		List<BusinessTripAirTicket> businessTripAirTicketList = getBusinessTripAirTicketList(applicationId);
		for (BusinessTripAirTicket businessTripAirTicket : businessTripAirTicketList) {
			if ("yes".equals(businessTripAirTicket.getInsertFlag())) {
				businessTripDao.updateAirTicketInsertFlag(businessTripAirTicket.getId());
			}
			
		}
	}

	
}
