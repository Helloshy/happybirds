package com.airparking.apms.api.monthrentrate.service.impl;

import com.airparking.apms.api.monthrentrate.entity.MonthrentPeriod;
import com.airparking.apms.api.monthrentrate.entity.Monthrentrate;
import com.airparking.apms.api.monthrentrate.mapper.MonthrentPeriodMapper;
import com.airparking.apms.api.monthrentrate.mapper.MonthrentrateMapper;
import com.airparking.apms.api.monthrentrate.service.MonthrentPeriodService;
import com.airparking.apms.api.park.entity.Park;
import com.airparking.apms.api.park.service.ParkService;
import com.airparking.apms.comm.HashUtils;
import com.airparking.apms.server.ResponseCode;
import com.airparking.apms.server.ServiceResponse;
import com.airparking.core.base.service.AbstractService;
import com.airparking.core.comm.utils.DateHelper;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author
 * @create 2017-05-19-19:19
 */

@Service("monthrentPeriodService")
public class MonthrentPeriodServiceImpl extends AbstractService implements MonthrentPeriodService{

	@Autowired
	private MonthrentPeriodMapper monthrentPeriodMapper;

	@Autowired
	private MonthrentrateMapper monthrentrateMapper;

	@Autowired
	private ParkService parkService;

	private static final Integer workType = 2;
	private static final Integer dayoffType = 1;

	@Override
	@Transactional(rollbackFor={RuntimeException.class})
	public ServiceResponse createPeriod(Map<String, Object> mrr, Long parkId, String appId) throws ParseException {

		Boolean isAdd = true;
		String mrrId = (String) mrr.get("mrrid");// 月保流水号

		Monthrentrate monthrentrate = new Monthrentrate();
		if (monthrentrateMapper.findByMrrId(mrrId) != null) {
			isAdd = false;
			monthrentrate = monthrentrateMapper.findByMrrId(mrrId);
		}

		Byte status = Byte.valueOf((String) mrr.get("status"));
		String rateName = (String) mrr.get("name");
		Date startDate = DateHelper.parseDateTime((String) mrr.get("startDate"));
		Date endDate = DateHelper.parseDateTime((String) mrr.get("endDate"));

		monthrentrate.setAdminfee(
				mrr.get("adminFee") == null ? 0 : Integer.valueOf(mrr.get("adminFee")+""));
		monthrentrate.setDeleted(false);
		monthrentrate.setDisabled((byte) 0);
		monthrentrate.setEndDate(endDate);
		monthrentrate.setDeleted(false);
		monthrentrate.setMonthfee(
				mrr.get("monthFee") == null ? 0 : Integer.valueOf(mrr.get("monthFee")+""));
		monthrentrate.setMrrId(mrrId);
		monthrentrate.setName(rateName);
		monthrentrate.setParkId(parkId);
		JSONObject jo = new JSONObject();
		jo.put("mrrId", mrrId);
		jo.put("appId", appId);
		String serialMrrid = HashUtils.md5(jo.toString());
		monthrentrate.setSerialMrrid(serialMrrid);
		monthrentrate.setStartDate(startDate);
		monthrentrate.setStatus(status);
		Date now = new Date();
		Long monthrentRateId = null;
		if (isAdd) {
			Long maxVersion = monthrentrateMapper.findMaxVersion();
			if (maxVersion == null) {
				maxVersion = 0l;
			}

			monthrentrate.setVersion(maxVersion + 1);
			monthrentrate.setCreatedDate(now);
			monthrentrateMapper.add(monthrentrate);

		} else {
			monthrentRateId = monthrentrate.getId();
			monthrentrate.setUpdatedDate(now);
			monthrentrateMapper.update(monthrentrate);
		}


		List<MonthrentPeriod> periodList =  monthrentPeriodMapper.findByMonthrentrateId(monthrentRateId);

		/******************************   保存车场设置   *******************************/
		Park park = parkService.findById(parkId);
		park.setIsDayParting(status == 1 ? Park.DayParting.OPEN.getKey() : Park.DayParting.CLOSE.getKey());
		parkService.update(park);
		/******************************   分时段(保存)   *******************************/

		if(periodList.size() == 0){
			monthrentRateId = monthrentrate.getId();
			MonthrentPeriod work = getWorkData(mrr, monthrentRateId, parkId,null);
			MonthrentPeriod dayoff = getDayoffData(mrr, monthrentRateId, parkId,null);

			if(work == null && dayoff == null){
				throw new RuntimeException();
			}

			work.setCreatedDate(new Date());
			dayoff.setCreatedDate(new Date());

			monthrentPeriodMapper.add(work);
			monthrentPeriodMapper.add(dayoff);
		}else{
			/******************************   分时段(修改)   *******************************/
			MonthrentPeriod work = monthrentPeriodMapper.findByMonthrentrateIdAndTyp(monthrentRateId,workType);
			MonthrentPeriod dayoffId = monthrentPeriodMapper.findByMonthrentrateIdAndTyp(monthrentRateId,dayoffType);

			MonthrentPeriod resWork = getWorkData(mrr, monthrentRateId, parkId,work);
			MonthrentPeriod resDayoff = getDayoffData(mrr, monthrentRateId, parkId,dayoffId);

			if(resWork.getWeekDay() == 0 && resDayoff.getWeekDay() == 0){
				throw new RuntimeException();
			}

			monthrentPeriodMapper.update(resWork);
			monthrentPeriodMapper.update(resDayoff);
		}
		return new ServiceResponse(ResponseCode.SUCCESS);
	}

	/**
	 * 工作日
	 * @return 没有选择任何日期也创建一个对象
	 */
	public MonthrentPeriod  getWorkData(Map<String, Object> mrr,Long monthrentId,Long parkId,MonthrentPeriod workday){
		if(workday == null){
			workday = new MonthrentPeriod();
		}

		int is_holiday = Integer.valueOf(mrr.get("is_holiday") != null ? mrr.get("is_holiday")+"" : "-1");
		int weekDay_work = Integer.valueOf(mrr.get("weekDay_work") != null ? mrr.get("weekDay_work")+"" : "0");
		String workday_startTime = mrr.get("workday_startTime") != null ? mrr.get("workday_startTime")+"" : null;
		String workday_endTime = mrr.get("workday_endTime") != null ? mrr.get("workday_endTime")+"" :null;

		workday.setMonthrentrateId(monthrentId);
		workday.setParkId(parkId);
		workday.setWeekDay(weekDay_work);
		workday.setHoliday(is_holiday == 2 ? true : false);
		workday.setTyp(workType);
		workday.setStartTime(workday_startTime);
		workday.setEndTime(workday_endTime);
		workday.setDeleted(false);

		return workday;
	}

	/**
	 * 休息日
	 * @return null 用户没有勾选任何日期不需要创建
	 */
	public MonthrentPeriod getDayoffData(Map<String, Object> mrr, Long monthrentId, Long parkId, MonthrentPeriod dayoff){
		if(dayoff == null){
			dayoff = new MonthrentPeriod();
		}
		int is_holiday = Integer.valueOf(mrr.get("is_holiday") != null ? mrr.get("is_holiday")+"" : "-1");
		int weekDay_dayoff = Integer.valueOf(mrr.get("weekDay_dayoff") != null ? mrr.get("weekDay_dayoff")+"" : "0");

		String dayoff_startTime = mrr.get("dayoff_startTime") != null ? mrr.get("dayoff_startTime")+"" : null;
		String dayoff_endTime = mrr.get("dayoff_endTime") != null ? mrr.get("dayoff_endTime")+"" :null;

		dayoff.setMonthrentrateId(monthrentId);
		dayoff.setParkId(parkId);
		dayoff.setWeekDay(weekDay_dayoff);
		dayoff.setHoliday(is_holiday == 1 ? true : false);
		dayoff.setTyp(dayoffType);
		dayoff.setStartTime(dayoff_startTime);
		dayoff.setEndTime(dayoff_endTime);
		dayoff.setDeleted(false);
		return dayoff;
	}


	@Override
	@Transactional(rollbackFor={RuntimeException.class})
	public JSONArray returnPeriod(JSONArray ja) {
		//如果有修改或新增修改字段，返回给线下
		List<Monthrentrate> mrList = monthrentrateMapper.findByUpdatedStatus();

		for (Monthrentrate mr : mrList) {
			int status = 1;
			JSONObject jo = new JSONObject();
			jo.put("adminFee", mr.getAdminfee());
			jo.put("monthFee", mr.getMonthfee());
			jo.put("mrrid", mr.getMrrId());
			jo.put("name", mr.getName());
			jo.put("parkId", mr.getParkId());
			jo.put("startDate", mr.getStartDate());
			jo.put("endDate", mr.getEndDate());
			//分时段相关
			List<MonthrentPeriod> periodList = monthrentPeriodMapper.findByMonthrentrateId(mr.getId());
			for (MonthrentPeriod period : periodList) {
				if(period.getTyp() == 1){
					//休息日
					jo.put("weekDay_dayoff",period.getWeekDay());
					jo.put("dayoff_startTime",period.getStartTime());
					jo.put("dayoff_endTime",period.getEndTime());
					if(period.getHoliday()){
						jo.put("is_holiday",1);
					}
				}
				if(period.getTyp() == 2){
					//工作日
					jo.put("weekDay_work",period.getWeekDay());
					jo.put("workday_startTime",period.getStartTime());
					jo.put("workday_endTime",period.getEndTime());
					if(period.getHoliday()){
						jo.put("is_holiday",2);
					}
				}
				if(monthrentPeriodMapper.findHoliday() == 1){
					jo.put("is_holiday",3); //是否禁用
				}
				if(jo.get("is_holiday") == null){
					jo.put("is_holiday",0);
				}
			}
			if (mr.getDeleted()) {
				status = 0;
			} else {
				if (mr.getDisabled() == 1) {
					status = 0;
				} else {
					if (mr.getStatus() == 0) {
						status = 0;
					}
				}
			}
			jo.put("status", status);
			ja.add(jo);

			monthrentrateMapper.updatedStatus(mr.getId());
		}
		return ja;
	}

	@Override
	public Long findByMonthrentrateId(Long id) {
		try{
			return monthrentrateMapper.findByMonthrentrateId(id);
		}catch (Exception e){
			return null;
		}
	}
}
