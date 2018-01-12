package com.thinkgem.jeesite.modules.finance.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.act.dao.ActHiTaskInstDao;
import com.thinkgem.jeesite.modules.act.entity.Act;
import com.thinkgem.jeesite.modules.act.service.ActTaskService;
import com.thinkgem.jeesite.modules.act.utils.ActUtils;
import com.thinkgem.jeesite.modules.finance.dao.ProjectDao;
import com.thinkgem.jeesite.modules.finance.dao.ReimburseHospitalityDao;
import com.thinkgem.jeesite.modules.finance.dao.ReimburseLongDistanceDao;
import com.thinkgem.jeesite.modules.finance.dao.ReimburseMainDao;
import com.thinkgem.jeesite.modules.finance.dao.ReimburseOtherDao;
import com.thinkgem.jeesite.modules.finance.dao.ReimburseTaxiDao;
import com.thinkgem.jeesite.modules.finance.entity.Project;
import com.thinkgem.jeesite.modules.finance.entity.ReimburseHospitality;
import com.thinkgem.jeesite.modules.finance.entity.ReimburseLongDistance;
import com.thinkgem.jeesite.modules.finance.entity.ReimburseMain;
import com.thinkgem.jeesite.modules.finance.entity.ReimburseOther;
import com.thinkgem.jeesite.modules.finance.entity.ReimburseTaxi;
import com.thinkgem.jeesite.modules.oa.helper.EmailUtil;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 报销
 * 
 * @author Grace
 * @date 2017年12月25日 下午4:04:52
 * @since 1.0.0
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class ReimburseService {

	@Autowired
	private ReimburseMainDao reimburseMainDao;

	@Autowired
	private ReimburseOtherDao reimburseOtherDao;

	@Autowired
	private ReimburseLongDistanceDao reimburseLongDistanceDao;

	@Autowired
	private ReimburseHospitalityDao reimburseHospitalityDao;

	@Autowired
	private ReimburseTaxiDao reimburseTaxiDao;

	@Autowired
	private TaskService taskService;

	@Autowired
	private ActHiTaskInstDao actHiTaskInstDao;

	@Autowired
	private ActTaskService actTaskService;

	@Autowired
	private ProjectDao projectDao;

	/**
	 * 提交报销申请
	 *
	 * @param request
	 * @param mainId
	 * @author Grace
	 * @param reimburseModel
	 * @throws ParseException
	 * @throws MessagingException
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @date 2018年1月3日 下午5:00:19
	 */
	@Transactional(readOnly = false)
	public void insertReimburse(ReimburseMain reimburseMain, HttpServletRequest request, String mainId)
			throws ParseException, FileNotFoundException, IOException, MessagingException {

		User user = UserUtils.getUser();

		// 启动Activity
		String title = user.getName() + " 报销申请";

		Map<String, Object> startVars = Maps.newHashMap();
		startVars.put("reimburseApplicant", user.getLoginName());

		String procInstId = actTaskService.startProcess(ActUtils.PD_Reimburse[0], ActUtils.PD_Reimburse[1], mainId,
				title, startVars);

		// 触发Acitiviti个人申请流程
		Map<String, Object> vars = Maps.newHashMap();
		vars.put("pass", "1");
		// 根据procinstId查taskId
		Act act = actHiTaskInstDao.findIdByProcInsId(procInstId);
		String taskId = act.getTaskId();
		complete(taskId, procInstId, "ReimburseApply", title, vars);

		String emailContent = "你好，"+user.getName()+"向您发起了报销申请，请审批.";
		EmailUtil.sendTextEmail(user.getEmail(), "1606528102@qq.com", title, emailContent);

		reimburseMain.setProcInstId(procInstId);

		insertMain(reimburseMain, request, mainId);
		insertLongDistance(request, mainId);
		insertTaxi(request, mainId);
		insertHospitality(request, mainId);
		insertOther(request, mainId);
	}

	/**
	 * 调用Activiti
	 * 
	 * @param taskId
	 * @param procInsId
	 * @param comment
	 * @param title
	 * @param vars
	 * @author Grace
	 * @date 2018年1月9日 上午11:37:30
	 */
	@Transactional(readOnly = false)
	public void complete(String taskId, String procInsId, String comment, String title, Map<String, Object> vars) {
		// 添加意见
		if (StringUtils.isNotBlank(procInsId) && StringUtils.isNotBlank(comment)) {
			// 封装
			taskService.addComment(taskId, procInsId, comment);
		}

		// 设置流程变量
		if (vars == null) {
			vars = Maps.newHashMap();
		}

		// 设置流程标题
		if (StringUtils.isNotBlank(title)) {
			vars.put("title", title);
		}

		// 提交任务（封装）
		taskService.complete(taskId, vars);
	}

	/**
	 * 插入报销主表
	 * 
	 * @param request
	 * @param mainId
	 * @author Grace
	 * @throws ParseException
	 * @date 2018年1月5日 下午3:46:39
	 */
	@Transactional(readOnly = false)
	private void insertMain(ReimburseMain reimburseMain, HttpServletRequest request, String mainId)
			throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		User user = UserUtils.getUser();

		reimburseMain.setId(mainId);
		reimburseMain.setProcInstId(reimburseMain.getProcInstId());

		Office office = new Office();
		office.setId(user.getOffice().getId());
		reimburseMain.setOffice(office);

		User applicant = new User();
		applicant.setId(user.getId());
		reimburseMain.setApplicant(applicant);
		reimburseMain.setApplyDate(sdf.parse(request.getParameter("applyDate")));
		reimburseMain.setBeginDate(sdf.parse(request.getParameter("beginDate")));
		reimburseMain.setEndDate(sdf.parse(request.getParameter("endDate")));
		// reimburseMain.setRemark(remark);
		// reimburseMain.setTotalAmount(totalAmount);
		reimburseMain.setUpdateDate(new Date());

		// 已提交
		reimburseMain.setStatus("20");
		reimburseMainDao.insert(reimburseMain);
	}

	/**
	 * 插入长途汽车费
	 * 
	 * @param request
	 * @param mainId
	 * @throws ParseException
	 * @author Grace
	 * @date 2018年1月5日 下午2:44:59
	 */
	@Transactional(readOnly = false)
	private void insertLongDistance(HttpServletRequest request, String mainId) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		String longDistanceEveryNum = request.getParameter("longDistanceEveryNum");

		String[] longDistanceNum = longDistanceEveryNum.split("");
		for (int i = 0; i < longDistanceNum.length; i++) {

			String amount = request.getParameter(("amountLongDistance" + longDistanceNum[i]));
			if (StringUtils.isNoneBlank(amount)) {
				ReimburseLongDistance reimburseLongDistance = new ReimburseLongDistance();
				String createDate = request.getParameter(("createDateLongDistance" + longDistanceNum[i]));
				String remark = request.getParameter(("remarkLongDistance" + longDistanceNum[i]));
				String projectId = request.getParameter(("projectLongDistance" + longDistanceNum[i]));
				reimburseLongDistance.setId(UUID.randomUUID().toString());
				reimburseLongDistance.setCreateDate(sdf.parse(createDate));
				reimburseLongDistance.setMainId(mainId);
				Project project = new Project();
				project.setId(projectId);
				reimburseLongDistance.setProject(project);
				reimburseLongDistance.setRemark(remark);
				reimburseLongDistance.setUpdateDate(new Date());
				reimburseLongDistance.setAmount(new BigDecimal(amount));

				reimburseLongDistanceDao.insert(reimburseLongDistance);
			}

		}

	}

	/**
	 * 插入其他费用
	 * 
	 * @param request
	 * @param mainId
	 * @author Grace
	 * @throws ParseException
	 * @date 2018年1月4日 下午5:39:08
	 */
	@Transactional(readOnly = false)
	private void insertOther(HttpServletRequest request, String mainId) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String otherEveryNum = request.getParameter("otherEveryNum");
		String[] otherNum = otherEveryNum.split("");

		for (int i = 0; i < otherNum.length; i++) {
			String amount = request.getParameter(("amountOther" + otherNum[i]));

			if (StringUtils.isNoneBlank(amount)) {
				ReimburseOther reimburseOther = new ReimburseOther();
				String createDate = request.getParameter(("createDateOther" + otherNum[i]));
				String remark = request.getParameter(("remarkOther" + otherNum[i]));
				String projectId = request.getParameter(("projectOther" + otherNum[i]));
				reimburseOther.setId(UUID.randomUUID().toString());
				reimburseOther.setCreateDate(sdf.parse(createDate));
				reimburseOther.setMainId(mainId);
				Project project = new Project();
				project.setId(projectId);
				reimburseOther.setProject(project);
				reimburseOther.setRemark(remark);
				reimburseOther.setUpdateDate(new Date());
				reimburseOther.setAmount(new BigDecimal(amount));

				reimburseOtherDao.insert(reimburseOther);
			}

		}

	}

	/**
	 * 插入招待费
	 * 
	 * @param request
	 * @param mainId
	 * @author Grace
	 * @throws ParseException
	 * @date 2018年1月4日 下午5:39:14
	 */
	@Transactional(readOnly = false)
	private void insertHospitality(HttpServletRequest request, String mainId) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String hospitalityEveryNum = request.getParameter("hospitalityEveryNum");
		String[] hospitalityNum = hospitalityEveryNum.split("");

		for (int i = 0; i < hospitalityNum.length; i++) {

			String amount = request.getParameter(("amountHospitality" + hospitalityNum[i]));
			if (StringUtils.isNoneBlank(amount)) {
				ReimburseHospitality reimburseHospitality = new ReimburseHospitality();

				String createDate = request.getParameter(("createDateHospitality" + hospitalityNum[i]));
				String projectId = request.getParameter(("projectHospitality" + hospitalityNum[i]));
				String clientName = request.getParameter(("clientNameHospitality" + hospitalityNum[i]));
				String inviteesName = request.getParameter(("inviteesNameHospitality" + hospitalityNum[i]));
				String invitedPosition = request.getParameter(("invitedPositionHospitality" + hospitalityNum[i]));
				String number = request.getParameter(("numberHospitality" + hospitalityNum[i]));

				reimburseHospitality.setId(UUID.randomUUID().toString());
				reimburseHospitality.setCreateDate(sdf.parse(createDate));
				reimburseHospitality.setMainId(mainId);

				Project project = new Project();
				project.setId(projectId);
				reimburseHospitality.setProject(project);

				reimburseHospitality.setClientName(clientName);
				reimburseHospitality.setInviteesName(inviteesName);
				reimburseHospitality.setInvitedPosition(invitedPosition);
				reimburseHospitality.setNumber(Integer.parseInt(number));
				reimburseHospitality.setUpdateDate(new Date());
				reimburseHospitality.setAmount(new BigDecimal(amount));

				reimburseHospitalityDao.insert(reimburseHospitality);
			}

		}

	}

	/**
	 * 插入出租车费
	 * 
	 * @param request
	 * @param mainId
	 * @author Grace
	 * @throws ParseException
	 * @date 2018年1月4日 下午5:39:19
	 */
	@Transactional(readOnly = false)
	private void insertTaxi(HttpServletRequest request, String mainId) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String taxiEveryNum = request.getParameter("taxiEveryNum");
		String[] taxiNum = taxiEveryNum.split("");

		for (int i = 0; i < taxiNum.length; i++) {
			String amount = request.getParameter(("amountTaxi" + taxiNum[i]));
			if (StringUtils.isNoneBlank(amount)) {
				ReimburseTaxi reimburseTaxi = new ReimburseTaxi();

				String createDate = request.getParameter(("createDateTaxi" + taxiNum[i]));
				String remark = request.getParameter(("remarkTaxi" + taxiNum[i]));
				String time = request.getParameter(("timeTaxi" + taxiNum[i]));
				String departureLocation = request.getParameter(("departureLocationTaxi" + taxiNum[i]));
				String arrivedLocation = request.getParameter(("arrivedLocationTaxi" + taxiNum[i]));
				String projectId = request.getParameter(("projectTaxi" + taxiNum[i]));
				reimburseTaxi.setId(UUID.randomUUID().toString());
				reimburseTaxi.setCreateDate(sdf.parse(createDate));
				reimburseTaxi.setMainId(mainId);
				Project project = new Project();
				project.setId(projectId);
				reimburseTaxi.setProject(project);
				reimburseTaxi.setRemark(remark);
				reimburseTaxi.setUpdateDate(new Date());
				reimburseTaxi.setTime(time);
				reimburseTaxi.setDepartureLocation(departureLocation);
				reimburseTaxi.setArrivedLocation(arrivedLocation);
				reimburseTaxi.setAmount(new BigDecimal(amount));

				reimburseTaxiDao.insert(reimburseTaxi);

			}

		}

	}

	/**
	 * 报销查看列表数据
	 * 
	 * @param page
	 * @param reimburseMain
	 * @return
	 * @author Grace
	 * @date 2018年1月8日 上午10:06:57
	 */
	@Transactional(readOnly = false)
	public Page<ReimburseMain> reimburseMainList(Page<ReimburseMain> page, ReimburseMain reimburseMain) {
		reimburseMain.setPage(page);
		List<ReimburseMain> reimburseMainList = reimburseMainDao.findList(reimburseMain);
		page.setList(reimburseMainList);
		return page;
	}

	/**
	 * 我的任务列表
	 * 
	 * @param page
	 * @param reimburseModel
	 * @return
	 * @author Grace
	 * @date 2018年1月8日 下午5:01:15
	 */
	@Transactional(readOnly = false)
	public Page<ReimburseMain> reimburseTaskListList(Page<ReimburseMain> page, ReimburseMain reimburseMain) {
		List<ReimburseMain> resultList = new ArrayList<ReimburseMain>();

		// 获取当前用户
		String userId = UserUtils.getUser().getLoginName();
		TaskQuery todoTaskQuery = taskService.createTaskQuery().taskAssignee(userId).active().includeProcessVariables()
				.orderByTaskCreateTime().asc();

		// 获取当前用户的任务列表
		List<Task> todoList = todoTaskQuery.list();
		// 循环任务列表，将处理好的vehicleProcess对象放到resultList中
		for (Task task : todoList) {
			// 获取任务的procInsId
			String procInsId = ((TaskEntity) task).getProcessInstanceId();

			// 通过procInsId获取相应对象
			reimburseMain.setProcInstId(procInsId);
			List<ReimburseMain> reimburseMainList = reimburseMainDao.findList(reimburseMain);

			if (reimburseMainList != null && reimburseMainList.size() > 0) {

				reimburseMain = reimburseMainList.get(0);
				// 将task和流程变量都赋给这个reimburseModel对象
				Act act = new Act();
				act.setTask(task);
				act.setVars(task.getProcessVariables());
				reimburseMain.setAct(act);
				// 将该对象放到resultList中
				resultList.add(reimburseMain);
			}
		}

		reimburseMain.setPage(page);
		page.setList(resultList);
		return page;

	}

	/**
	 * 保存审批
	 * 
	 * @param reimburseModel
	 * @param flag
	 * @author Grace
	 * @throws MessagingException 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @date 2018年1月8日 下午6:03:18
	 */
	@Transactional(readOnly = false)
	public void saveApprove(ReimburseMain reimburseMain) throws FileNotFoundException, IOException, MessagingException {

		User user = UserUtils.getUser();
		// 获取taskId
		Act act = new Act();
		act.setProcInsId(reimburseMain.getProcInstId());
		if ("20".equals(reimburseMain.getStatus())) {
			act.setTaskDefKey("FinancialApproval");
			act = actHiTaskInstDao.findIdByProcInsIdAndActId(act).get(0);
		} else if ("30".equals(reimburseMain.getStatus())) {
			act.setTaskDefKey("ManagerApproval");
			act = actHiTaskInstDao.findIdByProcInsIdAndActId(act).get(0);
		}
		
		ReimburseMain reimburseMainReturn = reimburseMainDao.getMainById(reimburseMain.getId());
		String title = reimburseMainReturn.getApplicant().getName() + " 报销申请";
		String emailBackTitle="报销申请被驳回";
		
		String applyEmailAddress="346535377@qq.com";
		//	String applyEmailAddress=reimburseMainReturn.getApplicant().getEmail();
		
		// 设置意见
		reimburseMain.getAct().setComment(("yes".equals(reimburseMain.getAct().getFlag()) ? "[同意] " : "[驳回] ")
				+ reimburseMain.getAct().getComment());

		// 审核环节 根据状态判断，如果是20 则是该财务审批了 ；如果是30则是领导该审批
		if ("10".equals(reimburseMain.getStatus())) {

		} else if ("20".equals(reimburseMain.getStatus())) {
			// 如果PM或者人事驳回 都转到个人让重新提交
			if ("no".equals(reimburseMain.getAct().getFlag())) {
				// 驳回
				reimburseMain.setStatus("40");
		
				String emailContent = "您好，财务驳回了您的报销申请，请修改后重新申请。";
				EmailUtil.sendTextEmail(user.getEmail(),applyEmailAddress , emailBackTitle, emailContent);
			} else {
				//财务同意
				reimburseMain.setStatus("30");
				
				String emailContent = "您好，"+reimburseMainReturn.getApplicant().getName()+"向您发起了报销申请，请审批.";
				//目标邮箱第二个改成杨哥的邮箱
				EmailUtil.sendTextEmail(user.getEmail(), "1606528102@qq.com", title, emailContent);
			}
			reimburseMain.setFAComment(reimburseMain.getAct().getComment());

			reimburseMainDao.update(reimburseMain);

			// 经理
		} else if ("30".equals(reimburseMain.getStatus())) {
			if ("no".equals(reimburseMain.getAct().getFlag())) {
				// 驳回
				reimburseMain.setStatus("40");
				
				String emailContent = "您好，经理驳回了您的报销申请，请修改后重新申请。";
				EmailUtil.sendTextEmail(user.getEmail(),applyEmailAddress , emailBackTitle, emailContent);
			} else {
				reimburseMain.setStatus("50");
				
				String emailContent = "您好，恭喜， 报销申请已通过！";
				EmailUtil.sendTextEmail(user.getEmail(),applyEmailAddress , emailBackTitle, emailContent);
			}
			reimburseMain.setManagerComment(reimburseMain.getAct().getComment());

			reimburseMainDao.update(reimburseMain);
		} else if ("50".equals(reimburseMain.getStatus())) {

		}
		// 未知环节，直接返回
		else {
			return;
		}

		// 提交流程任务
		Map<String, Object> vars = Maps.newHashMap();
		vars.put("pass", "yes".equals(reimburseMain.getAct().getFlag()) ? "1" : "0");

		
		

		complete(act.getTaskId(), reimburseMain.getProcInstId(), reimburseMain.getAct().getComment(), title, vars);
	}

	/**
	 * 根据主键查实体
	 * 
	 * @param mainId
	 * @return
	 * @author Grace
	 * @date 2018年1月9日 下午4:26:10
	 */
	public ReimburseMain getMainById(String mainId) {
		return reimburseMainDao.getMainById(mainId);
	}

	/**
	 * 根据mainId查出租车费List
	 * 
	 * @param mainId
	 * @return
	 * @author Grace
	 * @date 2018年1月9日 下午4:26:05
	 */
	public List<ReimburseTaxi> getTaxiListByMainId(String mainId) {
		ReimburseTaxi taxi = new ReimburseTaxi();
		taxi.setMainId(mainId);
		return reimburseTaxiDao.findList(taxi);
	}

	/**
	 * 根据mainId查询其他车费List
	 * 
	 * @param mainId
	 * @return
	 * @author Grace
	 * @date 2018年1月9日 下午4:27:45
	 */
	public List<ReimburseOther> getOtherListByMainId(String mainId) {
		ReimburseOther reimburseOther = new ReimburseOther();
		reimburseOther.setMainId(mainId);
		return reimburseOtherDao.findList(reimburseOther);
	}

	/**
	 * 根据mainId查询招待费List
	 * 
	 * @param mainId
	 * @return
	 * @author Grace
	 * @date 2018年1月9日 下午4:28:16
	 */
	public List<ReimburseHospitality> getHospitalityListByMainId(String mainId) {
		ReimburseHospitality hospitality = new ReimburseHospitality();
		hospitality.setMainId(mainId);
		return reimburseHospitalityDao.findList(hospitality);
	}

	/**
	 * 根据mainId获取长途汽车费
	 * 
	 * @param mainId
	 * @return
	 * @author Grace
	 * @date 2018年1月9日 下午5:11:28
	 */
	public List<ReimburseLongDistance> getLongDistanceListByMainId(String mainId) {
		ReimburseLongDistance reimburseLongDistance = new ReimburseLongDistance();
		reimburseLongDistance.setMainId(mainId);
		return reimburseLongDistanceDao.findList(reimburseLongDistance);
	}

	/**
	 * 获取projectList
	 * 
	 * @return
	 * @author Grace
	 * @date 2018年1月10日 下午4:22:40
	 */
	public List<Project> getProjectList() {
		Project project = new Project();
		return projectDao.findList(project);
	}

	/**
	 * 保存修改
	 * 
	 * @param reimburseMain
	 * @author Grace
	 * @throws ParseException
	 * @date 2018年1月11日 下午2:51:29
	 */
	@Transactional(readOnly = false)
	public void saveUpdate(ReimburseMain reimburseMain, HttpServletRequest request) throws ParseException {
		String mainId = reimburseMain.getId();
		// 触发Acitiviti流程
		Map<String, Object> vars = Maps.newHashMap();
		vars.put("pass", "1");
		String procInstId = reimburseMain.getProcInstId();

		// 根据procinstId查taskId
		Act act = new Act();
		act.setProcInsId(procInstId);
		act.setTaskDefKey("ReimburseApply");
		act = actHiTaskInstDao.findIdByProcInsIdAndActId(act).get(0);
		String taskId = act.getTaskId();

		// 启动Activity
		User user = UserUtils.getUser();
		String title = user.getName() + " 报销申请";
		complete(taskId, procInstId, "ReimburseApply", title, vars);

		// 删除报销业务所有相关表
		deleteReimburse(mainId);

		// 重新插入
		String newMainId = UUID.randomUUID().toString();
		insertMain(reimburseMain, request, newMainId);
		insertLongDistance(request, newMainId);
		insertTaxi(request, newMainId);
		insertHospitality(request, newMainId);
		insertOther(request, newMainId);
	}

	/**
	 * 删除报销业务所有相关表
	 * 
	 * @param mainId
	 * @author Grace
	 * @date 2018年1月12日 上午11:08:26
	 */
	@Transactional(readOnly = false)
	public void deleteReimburse(String mainId) {
		// 物理删除
		delete("fa_reimburse_longdistance", mainId);
		delete("fa_reimburse_hospitality", mainId);
		delete("fa_reimburse_other", mainId);
		delete("fa_reimburse_other", mainId);

		// 逻辑删除（正常数据默认1，删除标记0）
		reimburseMainDao.deleteByFlag(mainId);

	}

	/**
	 * 删除共用
	 * 
	 * @param tableName
	 * @param mainId
	 * @author Grace
	 * @date 2018年1月11日 下午8:01:36
	 */
	@Transactional(readOnly = false)
	private void delete(String tableName, String mainId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("tableName", tableName);
		map.put("mainId", mainId);

		reimburseMainDao.deleteByMainId(map);

	}
}
