package com.thinkgem.jeesite.modules.finance.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.modules.finance.dao.ReimburseDao;
import com.thinkgem.jeesite.modules.finance.dao.ReimburseHospitalityDao;
import com.thinkgem.jeesite.modules.finance.dao.ReimburseLongDistanceDao;
import com.thinkgem.jeesite.modules.finance.dao.ReimburseMainDao;
import com.thinkgem.jeesite.modules.finance.dao.ReimburseOtherDao;
import com.thinkgem.jeesite.modules.finance.dao.ReimburseTaxiDao;
import com.thinkgem.jeesite.modules.finance.entity.BusinessTripAirTicket;
import com.thinkgem.jeesite.modules.finance.entity.ReimburseHospitality;
import com.thinkgem.jeesite.modules.finance.entity.ReimburseLongDistance;
import com.thinkgem.jeesite.modules.finance.entity.ReimburseMain;
import com.thinkgem.jeesite.modules.finance.entity.ReimburseOther;
import com.thinkgem.jeesite.modules.finance.entity.ReimburseTaxi;
import com.thinkgem.jeesite.modules.finance.helper.ReimburseModel;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
	private ReimburseDao reimburseDao;

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

	/**
	 * 报销列表数据
	 */
	public Page<ReimburseMain> reimburseMainList(Page<ReimburseMain> page, ReimburseMain reimburseMain) {
		reimburseMain.setPage(page);
		User user = UserUtils.getUser();
		reimburseMain.setApplicantId(user.getId());
		reimburseMain.setOfficeId(user.getOffice().getId());
		if ("1".equals(reimburseMain.getOfficeId()) || "8".equals(reimburseMain.getOfficeId())) {
			List<ReimburseMain> reimburseMainLists = reimburseDao.findAllNameList(reimburseMain);
			page.setList(reimburseMainLists);
		} else {
			List<ReimburseMain> reimburseMainLists = reimburseDao.findOnlyNameList(reimburseMain);
			page.setList(reimburseMainLists);
		}
		return page;
	}

	/**
	 * 报销信息详情
	 */
	public ReimburseMain reimburseInformation(String id) {
		ReimburseMain reimburseMain = reimburseDao.findOnly(id);
		return reimburseMain;
	}

	/**
	 * 提交报销申请
	 *
	 * @param request
	 * @param mainId
	 * @author Grace
	 * @param reimburseModel
	 * @throws ParseException
	 * @date 2018年1月3日 下午5:00:19
	 */
	@Transactional(readOnly = false)
	public void insertReimburse(ReimburseModel reimburseModel, HttpServletRequest request, String mainId)
			throws ParseException {

		insertMain(reimburseModel,request, mainId);
		insertLongDistance(request, mainId);
		insertTaxi(request, mainId);
		insertHospitality(request, mainId);
		insertOther(request, mainId);
		// 启动

		// 申请开始

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
	private void insertMain(ReimburseModel reimburseModel,HttpServletRequest request, String mainId) throws ParseException {
	
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		  User user=UserUtils.getUser();
		  ReimburseMain reimburseMain=new ReimburseMain();
		  reimburseMain.setId(mainId);
		  reimburseMain.setOfficeId(user.getOffice().getId());
		  reimburseMain.setApplicantId(user.getId());
		  //已创建
		  reimburseMain.setStatus("10");
		  
		  reimburseMain.setApplyDate(sdf.parse(request.getParameter("applyDate")));
		  reimburseMain.setBeginDate(sdf.parse(request.getParameter("beginDate")));
		  reimburseMain.setEndDate(sdf.parse(request.getParameter("endDate")));
		//  reimburseMain.setRemark(remark);
		//  reimburseMain.setTotalAmount(totalAmount);
		  reimburseMain.setUpdateDate(new Date());
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

			ReimburseLongDistance reimburseLongDistance = new ReimburseLongDistance();

			String createDate = request.getParameter(("createDateLongDistance" + longDistanceNum[i]));
			String itemNo = request.getParameter(("itemNoLongDistance" + longDistanceNum[i]));
			String projectName = request.getParameter(("projectNameLongDistance" + longDistanceNum[i]));
			String remark = request.getParameter(("remarkLongDistance" + longDistanceNum[i]));
			String amount = request.getParameter(("amountLongDistance" + longDistanceNum[i]));

			reimburseLongDistance.setId(UUID.randomUUID().toString());
			reimburseLongDistance.setCreateDate(sdf.parse(createDate));
			reimburseLongDistance.setMainId(mainId);
			// reimburseLongDistance.setProjectId(projectId);
			 reimburseLongDistance.setProjectId("e43f5f0c-f413-11e7-8177-2c337a19e798");
			reimburseLongDistance.setRemark(remark);
			reimburseLongDistance.setUpdateDate(new Date());
			reimburseLongDistance.setAmount(new BigDecimal(amount));

			reimburseLongDistanceDao.insert(reimburseLongDistance);
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

			ReimburseOther reimburseOther = new ReimburseOther();

			String createDate = request.getParameter(("createDateOther" + otherNum[i]));
			String itemNo = request.getParameter(("itemNoOther" + otherNum[i]));
			String projectName = request.getParameter(("projectNameOther" + otherNum[i]));
			String remark = request.getParameter(("remarkOther" + otherNum[i]));
			String amount = request.getParameter(("amountOther" + otherNum[i]));

			reimburseOther.setId(UUID.randomUUID().toString());
			reimburseOther.setCreateDate(sdf.parse(createDate));
			reimburseOther.setMainId(mainId);
			// reimburseOther.setProjectId(projectId);
			reimburseOther.setProjectId("e43f5f0c-f413-11e7-8177-2c337a19e798");
			reimburseOther.setRemark(remark);
			reimburseOther.setUpdateDate(new Date());
			reimburseOther.setAmount(new BigDecimal(amount));

			reimburseOtherDao.insert(reimburseOther);
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

			ReimburseHospitality reimburseHospitality = new ReimburseHospitality();

			
			String createDate = request.getParameter(("createDateHospitality" + hospitalityNum[i]));
			String itemNo = request.getParameter(("itemNoHospitality" + hospitalityNum[i]));
			String projectName = request.getParameter(("projectNameHospitalitye" + hospitalityNum[i]));
			String clientName = request.getParameter(("clientNameHospitality" + hospitalityNum[i]));
			String inviteesName = request.getParameter(("inviteesNameHospitality" + hospitalityNum[i]));
			String invitedPosition = request.getParameter(("invitedPositionHospitality" + hospitalityNum[i]));
			String number = request.getParameter(("numberHospitality" + hospitalityNum[i]));
			String amount = request.getParameter(("amountHospitality" + hospitalityNum[i]));

			reimburseHospitality.setId(UUID.randomUUID().toString());
			reimburseHospitality.setCreateDate(sdf.parse(createDate));
			reimburseHospitality.setMainId(mainId);
			// reimburseHospitality.setProjectId(projectId);
			reimburseHospitality.setProjectId("e43f5f0c-f413-11e7-8177-2c337a19e798");
			reimburseHospitality.setClientName(clientName);
			reimburseHospitality.setInviteesName(inviteesName);
			reimburseHospitality.setInvitedPosition(invitedPosition);
			reimburseHospitality.setNumber(Integer.parseInt(number));
			reimburseHospitality.setUpdateDate(new Date());
			reimburseHospitality.setAmount(new BigDecimal(amount));

			reimburseHospitalityDao.insert(reimburseHospitality);
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

			ReimburseTaxi reimburseTaxi = new ReimburseTaxi();

			String createDate = request.getParameter(("createDateTaxi" + taxiNum[i]));
			String itemNo = request.getParameter(("itemNoTaxi" + taxiNum[i]));
			String projectName = request.getParameter(("projectNameTaxi" + taxiNum[i]));
			String remark = request.getParameter(("remarkTaxi" + taxiNum[i]));
			String time = request.getParameter(("timeTaxi" + taxiNum[i]));
			String departureLocation = request.getParameter(("departureLocationTaxi" + taxiNum[i]));
			String arrivedLocation = request.getParameter(("arrivedLocationTaxi" + taxiNum[i]));
			String amount = request.getParameter(("amountTaxi" + taxiNum[i]));

			reimburseTaxi.setId(UUID.randomUUID().toString());
			reimburseTaxi.setCreateDate(sdf.parse(createDate));
			reimburseTaxi.setMainId(mainId);
			// reimburseTaxi.setProjectId(projectId);
			reimburseTaxi.setProjectId("e43f5f0c-f413-11e7-8177-2c337a19e798");
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
