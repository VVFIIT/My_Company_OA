package com.thinkgem.jeesite.modules.finance.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.modules.finance.dao.ReimburseDao;
import com.thinkgem.jeesite.modules.finance.entity.BusinessTripAirTicket;
import com.thinkgem.jeesite.modules.finance.entity.ReimburseMain;
import com.thinkgem.jeesite.modules.finance.helper.ReimburseModel;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

/**
 * 报销
 * @author Grace
 * @date 2017年12月25日 下午4:04:52
 * @since 1.0.0
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class ReimburseService {

    @Autowired
    private ReimburseDao reimburseDao;

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
     * @date 2018年1月3日 下午5:00:19
     */
    public void insertReimburse(ReimburseModel reimburseModel, HttpServletRequest request, String mainId) {

        
        
        insertLongDistance(request,mainId);
        insertTaxi(request,mainId);
        insertHospitality(request,mainId);
        insertOther(request,mainId);
        //启动
        
        //申请开始
      
    }




	private void insertLongDistance(HttpServletRequest request, String mainId) {
		  SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm");
		  
		  String longDistanceEveryNum = request.getParameter("longDistanceEveryNum");
	        String itemNoLongDistance1 = request.getParameter("itemNoLongDistance1");
	        
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
			//	businessTripAirTicket.setApplicationId(applicationId);
				if(airTicketFlyDate != null && !"".equals(airTicketFlyDate)) {
//					businessTripAirTicket.setFlyDate(sdf.parse(airTicketFlyDate));
				}
				if(airTicketAmount != null && !"".equals(airTicketAmount)) {
					businessTripAirTicket.setAmount(new BigDecimal(airTicketAmount));
				}
				businessTripAirTicket.setStartLocation(airTicketStartLocation);
				businessTripAirTicket.setArrivedLocation(airTicketArrivedLocation);
				businessTripAirTicket.setRemark(airTicketRemark);
		//		businessTripDao.insertBusinessTripAirTicket(businessTripAirTicket);
			}
		
	}
	
	/**
	 * 插入其他费用
	 * 
	 * @param request
	 * @param mainId
	 * @author Grace
	 * @date 2018年1月4日 下午5:39:08
	 */
	private void insertOther(HttpServletRequest request, String mainId) {
		// TODO Auto-generated method stub
		
	}

    /**
     * 插入招待费
     * 
     * @param request
     * @param mainId
     * @author Grace
     * @date 2018年1月4日 下午5:39:14
     */
	private void insertHospitality(HttpServletRequest request, String mainId) {
		// TODO Auto-generated method stub
		
	}

    /**
     * 插入出租车费
     * 
     * @param request
     * @param mainId
     * @author Grace
     * @date 2018年1月4日 下午5:39:19
     */
	private void insertTaxi(HttpServletRequest request, String mainId) {
		// TODO Auto-generated method stub
		
	}

	

}
