package com.zjj.service;


import com.zjj.dto.SysProductBatch;
import com.zjj.dto.SysProductInfo;

import java.util.List;

/**
 * 号源 业务层
 */
public interface ISysProductService {

    /**
     * 获取号源信息
     *
     * @param deptId      部门编号
     * @param beginTime   开始时间
     * @param endTime     结束时间
     * @param currentPage 分页编码
     * @return 号源信息
     */
    List<SysProductBatch> getProductList(String deptId, String beginTime, String endTime, int currentPage);


    /**
     * 获取当日号源信息
     *
     * @param deptId    部门编号
     * @param batchDate 日期
     * @param status    时间段 0上午 1下午
     * @return 号源信息
     */
    List<SysProductInfo> getProductDetail(String deptId, String batchDate, String status);

    /**
     * 获取确认号源信息
     *
     * @param deptId         部门编号
     * @param batchDate      日期
     * @param uniqProductKey 号源编号
     * @return 号源信息
     */
    SysProductInfo productConfirm(String deptId, String batchDate, String uniqProductKey);

    /**
     * 修改号源数量 -1
     * @param uniqProductKey 号源id
     */
    void updateProductNCode(String uniqProductKey);

    /**
     * 查询号源是否存在
     * @param uniqProductKey 号源id
     * @return 结果
     */
    int selectOrderNCodeById(String uniqProductKey);

    /**
     * 修改号源数量 +1
     * @param uniqProductKey 号源id
     */
    void updateProductNCodeAdd(String uniqProductKey);

    List<SysProductInfo> getProductData(SysProductInfo sysProductInfo);

    int insertProductBatch(SysProductInfo sysProductInfo);

    SysProductInfo checkProductDate(String batchDate);

    void insertProductInfo(SysProductInfo sysProductInfo);
}
