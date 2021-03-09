package com.zjj.service.impl;

import com.github.pagehelper.PageHelper;
import com.zjj.dto.SysProductBatch;
import com.zjj.dto.SysProductInfo;
import com.zjj.mapper.SysProductMapper;
import com.zjj.service.ISysProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 号源信息 服务层处理
 */
@Service
public class SysProductServiceImpl implements ISysProductService {

    @Autowired
    private SysProductMapper productMapper;

    /**
     * 获取号源信息
     *
     * @param deptId      部门编号
     * @param beginTime   开始时间
     * @param endTime     结束时间
     * @param currentPage 分页编码
     * @return 号源信息
     */
    @Override
    public List<SysProductBatch> getProductList(String deptId, String beginTime, String endTime, int currentPage) {
        PageHelper.startPage(currentPage, 7);
        return productMapper.getProductList(deptId, beginTime, endTime);
    }

    /**
     * 获取当日号源信息
     *
     * @param deptId    部门编号
     * @param batchDate 日期
     * @param status    时间段 0上午 1下午
     * @return 号源信息
     */
    @Override
    public List<SysProductInfo> getProductDetail(String deptId, String batchDate, String status) {
        return productMapper.getProductDetail(deptId, batchDate, status);
    }


    /**
     * 获取确认号源信息
     *
     * @param deptId         部门编号
     * @param batchDate      日期
     * @param uniqProductKey 号源编号
     * @return 号源信息
     */
    @Override
    public SysProductInfo productConfirm(String deptId, String batchDate, String uniqProductKey) {
        return productMapper.productConfirm(deptId, batchDate, uniqProductKey);
    }


    /**
     * 修改号源数量 -1
     * 参考CAS的无锁算法来处理更新的并发问题（利用死循环+版本号）
     *
     * @param uniqProductKey 号源id
     */
    @Override
    public void updateProductNCode(String uniqProductKey) {
        // 参考cas
        for (; ; ) {
            Integer version = productMapper.getVersionById(uniqProductKey);
            Integer updateCount = productMapper.updateProductNCode(uniqProductKey, version);
            if (updateCount != null && updateCount.equals(1)) {
                break;
            }
        }
    }

    /**
     * 查询号源是否存在
     *
     * @param uniqProductKey 号源id
     * @return 结果
     */
    @Override
    public int selectOrderNCodeById(String uniqProductKey) {
        return productMapper.selectOrderNCodeById(uniqProductKey);
    }

    /**
     * 修改号源数量 +1
     * 参考CAS的无锁算法来处理更新的并发问题（利用死循环+版本号）
     *
     * @param uniqProductKey 号源id
     */
    @Override
    public void updateProductNCodeAdd(String uniqProductKey) {
        // 参考cas
        for (; ; ) {
            Integer version = productMapper.getVersionById(uniqProductKey);
            Integer updateCount = productMapper.updateProductNCodeAdd(uniqProductKey, version);
            if (updateCount != null && updateCount.equals(1)) {
                break;
            }
        }
    }

    @Override
    public List<SysProductInfo> getProductData(SysProductInfo sysProductInfo) {
        return productMapper.getProductData(sysProductInfo);
    }

    @Override
    public int insertProductBatch(SysProductInfo sysProductInfo) {
        return productMapper.insertProductBatch(sysProductInfo);
    }

    @Override
    public SysProductInfo checkProductDate(String batchDate) {
        return productMapper.checkProductDate(batchDate);
    }

    @Override
    public void insertProductInfo(SysProductInfo sysProductInfo) {
        productMapper.insertProductInfo(sysProductInfo);
    }
}
