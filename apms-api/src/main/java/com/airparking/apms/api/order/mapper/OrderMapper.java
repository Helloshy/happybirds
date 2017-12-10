package com.airparking.apms.api.order.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.airparking.apms.api.order.entity.Order;
import com.airparking.core.base.mapper.AbstractMapper;

/**
 * Created by ryan on 2016-07-26.
 */
public interface OrderMapper extends AbstractMapper<Order, Long> {
    int updateByTradeNo(Order order);
    
    @ResultType(Integer.class)
    @Update("UPDATE park_order SET total_amount = #{totalAmount}, pay_amount = #{payAmount}, "
        + "order_status = #{orderStatus} WHERE trade_no = #{tradeNo} AND order_status = #{oldOrderStatus} ")
    int updateByTradeNoAndOrderStatus(@Param("tradeNo") String tradeNo, 
        @Param("oldOrderStatus") Integer oldOrderStatus, @Param("orderStatus") Integer orderStatus,
        @Param("totalAmount") Integer totalAmount, @Param("payAmount") Integer payAmount);
    
    @ResultType(Integer.class)
    @Update("UPDATE park_order SET total_amount = #{totalAmount}, pay_amount = #{payAmount}, "
        + "order_status = #{orderStatus} , end_time = #{endTime},  " +
			"updated_date = #{updatedDate}, total_time = #{totalTime} WHERE trade_no = #{tradeNo} ")
    int updateByTradeNoToEnd(@Param("tradeNo") String tradeNo, @Param("orderStatus") Integer orderStatus,
        			@Param("totalAmount") Integer totalAmount, @Param("payAmount") Integer payAmount,
					@Param("endTime") Date endTime,@Param("updatedDate") Date updatedDate, @Param("totalTime") Long totalTime);

	@ResultType(Integer.class)
	@Update("UPDATE park_order SET order_status = #{orderStatus} WHERE "
			+ "trade_no = #{tradeNo} AND (order_status = 1 OR order_status = 2)")
	int updateByTradeNoStatusToPaid(@Param("tradeNo") String tradeNo, @Param("orderStatus") Integer orderStatus);
    
    @ResultType(Integer.class)
    @Update("UPDATE park_order SET is_send = 1 WHERE trade_no = #{tradeNo} ")
    int updateOrderIsSend(@Param("tradeNo") String tradeNo);

    @ResultType(Integer.class)
    @Select("select count(1) from `park_order` where trade_no = #{treadNo}")
    int countByTradeNo(String tradeNo);

    @ResultType(Order.class)
    @Select("select o.trade_no as tradeNo, p.updated_date from `order` o join payment p where o.trade_no = p.out_trade_no and p.park_id = #{parkId} and o.park_id = #{parkId} and p.payment_way = 2 and o.order_status = 2 and p.updated_date > date_add(current_timestamp(), interval #{intervals} second)")
    List<Order> findLatestOnlinePaid(@Param("parkId") Long parkId, @Param("intervals") int intervals);

	List<Order> findByTradeNo(@Param("tradeNo") String tradeNo);
    
    @ResultType(Order.class)
    @Select("SELECT "+
                        "po.trade_no as tradeNo,po.pay_amount as payAmount,po.rate_id rateId,po.total_amount totalAmount,po.updated_date updatedDate, po.id "+
                "FROM "+
                  "park_order po "+
                "WHERE "+
                  "po.order_status = 2 "+
                "AND po.is_deleted = 0 "+
                "AND po.updated_date <= #{end} "+
                "AND po.updated_date >= #{start} "+
                "AND po.park_id = #{parkId} ")
    List<Order> findOrderToday(@Param("start") String start, @Param("end") String end, @Param("parkId") Long parkId);
    
    List<Order> findByIsSend();
    
    Order getByTradeNo(@Param("tradeNo") String tradeNo);
    
    Order getByCardNo(@Param("cardNo") String cardNo);

	@ResultType(Order.class)
	@Select("select "+
			"trade_no as tradeNo,pay_amount as payAmount,rate_id as rateId,total_amount as totalAmount,updated_date as updatedDate, order_status as orderStatus,id "+
			"from park_order where trade_no = #{tradeNo} and type = 0 and is_deleted = 0")
	Order findByTradeNoAndType(String tradeNo);

//	@ResultType(Integer.class)
//	@Update("UPDATE park_order SET order_status = #{orderStatus} , end_time = #{endTime} ,updated_date = #{updatedDate} " +
//			",total_amount = #{totalAmount}, pay_amount = #{payAmount} WHERE trade_no = #{tradeNo} and type = #{type}")
//	int updateByMonthrentToEnd(@Param("tradeNo") String tradeNo, @Param("orderStatus") Integer orderStatus
//			,@Param("endTime") Date endTime,@Param("updatedDate")Date updatedDate
//			,@Param("totalAmount") Integer totalAmount, @Param("payAmount") Integer payAmount,@Param("type") Byte type);

}