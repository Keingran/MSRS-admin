package com.zjj.utils;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zjj.common.Result;

import java.util.List;

/**
 * 分页工具类
 */
public class PageUtils {

    /**
     * 将分页信息封装成json数据
     *
     * @param list        数据
     * @param currentPage 分页页码
     * @param pageSize    分页大小
     * @return 分页结果
     */
    public static JSONObject getPageResult(List<?> list, int currentPage, int pageSize) {
        PageInfo<?> pageInfo = new PageInfo<>(list);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("total", pageInfo.getTotal());
        jsonObject.put("data", pageInfo.getList());
        jsonObject.put("page", pageInfo.getPageNum());
        jsonObject.put("size", pageInfo.getPageSize());
        return jsonObject;
    }


    /**
     * 响应返回结果
     *
     * @param rows 影响行数
     * @return 操作结果
     */
    public static Result toResult(int rows, String msg) {
        return rows > 0 ? Result.success(MessageUtils.message(msg)) : Result.error();
    }
}
