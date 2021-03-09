package com.zjj.mapper;

import com.zjj.dto.SysProductBatch;
import com.zjj.dto.SysProductInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 号源信息 数据层
 */
public interface SysProductMapper {

    /**
     * 获取号源信息
     *
     * @param deptId    部门编号
     * @param beginTime 开始时间
     * @param endTime   结束时间
     * @return 号源信息
     */
    List<SysProductBatch> getProductList(@Param("deptId") String deptId, @Param("beginTime") String beginTime, @Param("endTime") String endTime);

    /**
     * 获取当日号源信息
     *
     * @param deptId    部门编号
     * @param batchDate 日期
     * @param status    时间段 0上午 1下午
     * @return 号源信息
     */
    List<SysProductInfo> getProductDetail(@Param("deptId") String deptId, @Param("batchDate") String batchDate, @Param("status") String status);

    /**
     * 获取确认号源信息
     *
     * @param deptId         部门编号
     * @param batchDate      日期
     * @param uniqProductKey 号源编号
     * @return 号源信息
     */
    SysProductInfo productConfirm(@Param("deptId") String deptId, @Param("batchDate") String batchDate, @Param("uniqProductKey") String uniqProductKey);

    /**
     * 查询号源是否存在
     *
     * @param uniqProductKey 号源id
     * @return 结果
     */
    int selectOrderNCodeById(String uniqProductKey);

    /**
     * 修改号源数量 -1
     *
     * @param uniqProductKey 号源id
     */
    int updateProductNCode(@Param("uniqProductKey") String uniqProductKey, @Param("version") int version);

    /**
     * 查询版本号
     *
     * @param uniqProductKey 号源id
     * @return 结果
     */
    int getVersionById(String uniqProductKey);

    /**
     * 修改号源数量 +1
     *
     * @param uniqProductKey 号源id
     */
    int updateProductNCodeAdd(@Param("uniqProductKey") String uniqProductKey, @Param("version") int version);

    List<SysProductInfo> getProductData(SysProductInfo sysProductInfo);

    int insertProductBatch(SysProductInfo sysProductInfo);

    SysProductInfo checkProductDate(String batchDate);

    void insertProductInfo(SysProductInfo sysProductInfo);
}
