package com.airparking.apms.api.monthrentrate.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.airparking.apms.api.monthrentrate.entity.Monthrentrate;
import com.airparking.core.base.mapper.AbstractMapper;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

/**
 * Created by ryan on 2016-07-28.
 */
public interface MonthrentrateMapper extends AbstractMapper<Monthrentrate, Long> {
  Monthrentrate findByMrrId(@Param("mrrId") String mrrId);

  Long findMaxVersion();

  List<Monthrentrate> findByThisYearWithParkId(@Param("parkId") Long parkId,
      @Param("startYear") Date startYear, @Param("endYear") Date endYear);

  Monthrentrate findIsExistByParkIdAndTimeAndName(@Param("parkId") Long parkId,
      @Param("startDate") Date startDate, @Param("endDate") Date endDate,
      @Param("name") String name);

	List<Monthrentrate> findByUpdatedStatus();

	void updatedStatus(@Param("id")Long id);

	@ResultType(Integer.class)
	@Select("select id from `monthrent_period` where monthrentrate_id = #{rateId} limit 1")
	Long findByMonthrentrateId(@Param("rateId") Long id);

	// Monthrentrate findIsExistByParkIdAndName(@Param("parkId") Long parkId,
  // @Param("name") String name);
}