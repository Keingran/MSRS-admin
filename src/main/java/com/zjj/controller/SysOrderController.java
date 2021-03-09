package com.zjj.controller;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.zjj.common.Result;
import com.zjj.common.constant.Constants;
import com.zjj.common.constant.HttpStatus;
import com.zjj.dto.SysOrder;
import com.zjj.dto.SysPosition;
import com.zjj.dto.model.OrderBody;
import com.zjj.exception.SmsException;
import com.zjj.exception.SmsExpireException;
import com.zjj.redis.RedisCache;
import com.zjj.service.ISysOrderService;
import com.zjj.service.ISysProductService;
import com.zjj.utils.*;
import com.zjj.utils.UUID.IdUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class SysOrderController {
    private static final Logger log = LoggerFactory.getLogger(SysOrderController.class);

    @Value("${rsa.privateKey}")
    private String privateKey;  // 私钥

    @Autowired
    private ISysOrderService orderService;

    @Autowired
    private ISysProductService productService;

    @Autowired
    private RedisCache redisCache;

    private static final String orderStatus = "2"; // 校验取消订单码

    /**
     * 预约挂单
     *
     * @param orderBody 信息
     * @return 结果
     * @throws Exception 异常
     */
    @PostMapping("/save")
    public Result saveOrder(@RequestBody OrderBody orderBody) throws Exception {
        // 1、先判断验证码是否正确
        String phone = orderBody.getPhone();
        String code = orderBody.getOrderSmsCode();
        String smsCodeKey = Constants.SMS_ORDER_CODE_KEY + phone;
        // 查询redis中是否有这个key
        String smsCode = redisCache.getCacheObject(smsCodeKey);
        // 如果没有这个key, 则验证码已过期
        if (smsCode == null) {
            throw new SmsExpireException();
        }
        // 如果输入的验证码不正确
        if (!code.equalsIgnoreCase(smsCode)) {
            throw new SmsException();
        }

        // 2、先判断日期是否有效,日期非法
        Date date = DateUtils.dateTime("yyyy-MM-dd", orderBody.getBatchDate());
        Date nowDate = DateUtils.getNowDate();
        if (date.before(nowDate)) {
            return Result.error(HttpStatus.ORDER_ERROR, MessageUtils.message("order.date.error"));
        }

        // 3、先判断数据库是否有号源,相关号源已挂完
        String uniqProductKey = RsaUtils.decryptByPrivateKey(privateKey, orderBody.getUniqProductKey());
        String cardNo = RsaUtils.decryptByPrivateKey(privateKey, orderBody.getCardNo());
        orderBody.setUniqProductKey(uniqProductKey);
        orderBody.setCardNo(cardNo);
        int nCode = productService.selectOrderNCodeById(uniqProductKey);
        if (nCode > 0) {
            // 加锁
            synchronized (this) {
                nCode = productService.selectOrderNCodeById(uniqProductKey);
                // 如果有号源
                if (nCode > 0) {
                    // 生成随机的预约订单号
                    String uuid = IdUtils.simpleUUID();
                    orderBody.setOrderId(uuid);
                    int row = orderService.saveOrder(orderBody);
                    // 预约成功，删减号源,删除redis中的短信缓存
                    if (row == 1) {
                        productService.updateProductNCode(uniqProductKey);
                        redisCache.deleteObject(Constants.SMS_ORDER_CODE_KEY + orderBody.getPhone());
                        return Result.success(MessageUtils.message("order.success"));
                    }
                }
            }

        }
        return Result.error(HttpStatus.ORDER_ERROR, MessageUtils.message("order.count.error"));
    }

    /**
     * 订单列表
     *
     * @param dto 信息
     * @return 结果
     */
    @PostMapping("/list")
    public Result getOrderList(@RequestBody SysOrder order) {

        int page = StringUtils.isNotNull(order.getPage()) ? order.getPage() : 1;
        int size = StringUtils.isNotNull(order.getSize()) ? order.getSize() : 10;

        List<SysOrder> list = orderService.getOrderList(order, page, size);
        // 分页处理
        JSONObject pageResult = PageUtils.getPageResult(list, page, size);
        return Result.success(pageResult);
    }



    /**
     * 修改订单状态
     *
     * @param sysOrder 信息
     * @return 结果
     */
    @PostMapping("/updateStatus")
    public Result updateOrderStatus(@RequestBody SysOrder sysOrder) {
        int row = orderService.updateOrderStatus(sysOrder);
        // 如果修改的状态是 取消，则需要释放库存
        if (orderStatus.equals(sysOrder.getOrderStatus())) {
            if (row > 0) {
                // 库存+1
                productService.updateProductNCodeAdd(sysOrder.getUniqProductKey());
                return PageUtils.toResult(row, "order.update.status.success");
            }
        }
        return PageUtils.toResult(row, "order.update.status.success");
    }
}
