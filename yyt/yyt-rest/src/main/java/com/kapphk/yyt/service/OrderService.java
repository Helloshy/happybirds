package com.kapphk.yyt.service;

import com.kapphk.base.service.BaseService;
import com.kapphk.web.utils.Result;
import com.kapphk.yyt.bean.UserOrder;

public interface OrderService extends BaseService<UserOrder, Long> {



	Result saveOrder(UserOrder order, Result result);


}
