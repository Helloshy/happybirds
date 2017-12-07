/**
 * 计算气差费用和压车费用
 */
$(function(){
	try{
		
		/**计算公式实际气差 = 装车净重 - 卸车净重
		合同气差 = ±200
		结算气差 = IF(ABS(实际气差)<=200,0,
				 IF(实际气差>0, 实际气差 - 200, 实际气差+ 200))
		采购数量 = 订单采购数量
		实际结算重量 = 采购数量 + 结算气差
		气差费用 = 结算气差/1000 * 成交价
		*/
		var gasValue = loading-unload;
		var gas = 0;
		if(Math.abs(gasValue)<=200){
			gas = 0;
		}else if(gasValue>0){
			gas = gasValue-200;
		}else{
			gas = gasValue+200;
		}
		var gasCost =  toDecimal(gas/1000 * qty * unit_price);
		/**计算公式
		合同压车 = 6 小时
		超出合同压车时间 = 装车信息:离厂时间 - 装车信息:到厂时间 
		压车费用 = IF(超出合同压车时间 - 合同压车 > 0 ,
		(超出合同压车时间 - 合同压车)*压车处理费用(栏位:car_process), 0  )
		*/
		var hour = toDecimal((new Date(date_leave).getTime() - new Date(date_arrive).getTime())/1000/3600);
		var carCost =  (hour-6) > 0 ? toDecimal((hour-6)* car_process) : '0.00';
		var totalCost = toDecimal(parseFloat(qty*unit_price+parseFloat(delivery_fee)+parseFloat(gasCost)+parseFloat(carCost)));
	    $("#gasCost").html(''+gasCost);
	    $("#carCost").html(''+carCost);
	    $("#totalCost").html(''+totalCost);
	}catch(e){
		console.log(e);
	}
})