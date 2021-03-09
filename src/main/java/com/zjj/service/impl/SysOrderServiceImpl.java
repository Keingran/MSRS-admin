package com.zjj.service.impl;

import com.github.pagehelper.PageHelper;
import com.zjj.dto.SysOrder;
import com.zjj.dto.model.OrderBody;
import com.zjj.mapper.SysOrderMapper;
import com.zjj.service.ISysOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SysOrderServiceImpl implements ISysOrderService {

    @Autowired
    private SysOrderMapper orderMapper;

    /**
     * 预约挂号下单
     *
     * @param orderBody 预约信息
     * @return 结果
     */
    @Override
    public int saveOrder(OrderBody orderBody) {
        return orderMapper.saveOrder(orderBody);
    }

    /**
     * 查询订单信息
     *
     * @param param 参数
     * @param page  分页
     * @param size  大小
     * @return 结果
     */
    @Override
    public List<SysOrder> getOrderList(SysOrder param, int page, int size) {
        PageHelper.startPage(page, size);
        return orderMapper.getOrderList(param);
    }

    /**
     * 修改订单状态
     *
     * @param sysOrder 参数
     * @return 结果
     */
    @Override
    public int updateOrderStatus(SysOrder sysOrder) {
        return orderMapper.updateOrderStatus(sysOrder);
    }
}
