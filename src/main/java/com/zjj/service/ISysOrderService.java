package com.zjj.service;

import com.zjj.dto.SysOrder;
import com.zjj.dto.SysPosition;
import com.zjj.dto.model.OrderBody;

import java.util.List;
import java.util.Map;

public interface ISysOrderService {

    /**
     * 预约挂号下单
     * @param orderBody 预约信息
     * @return 结果
     */
    int saveOrder(OrderBody orderBody);

    /**
     * 查询订单信息
     * @param param 参数
     * @param page 分页
     * @param size 大小
     * @return 结果
     */
    List<SysOrder> getOrderList(SysOrder param, int page, int size);

    /**
     * 修改订单状态
     * @param sysOrder 参数
     * @return 结果
     */
    int updateOrderStatus(SysOrder sysOrder);
}
