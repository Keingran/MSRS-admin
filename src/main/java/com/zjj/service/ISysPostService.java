package com.zjj.service;

import com.zjj.dto.SysPosition;

import java.util.List;
import java.util.Map;

/**
 * 岗位信息 服务层
 */
public interface ISysPostService {

    /**
     * 获取岗位列表
     */
    List<SysPosition> getPostList(Map<String, String> param, int page, int size);

    /**
     * 通过岗位ID查询岗位信息
     *
     * @param postId 岗位ID
     * @return 角色对象信息
     */
    public SysPosition selectPostById(Long postId);

    /**
     * 校验岗位名称
     *
     * @param post 岗位信息
     * @return 结果
     */
    public String checkPostNameUnique(SysPosition post);

    /**
     * 校验岗位编码
     *
     * @param post 岗位信息
     * @return 结果
     */
    public String checkPostCodeUnique(SysPosition post);

    /**
     * 删除岗位信息
     *
     * @param postId 岗位ID
     * @return 结果
     */
    public int deletePostById(Long postId);

    /**
     * 新增保存岗位信息
     *
     * @param post 岗位信息
     * @return 结果
     */
    public int insertPost(SysPosition post);

    /**
     * 修改保存岗位信息
     *
     * @param post 岗位信息
     * @return 结果
     */
    public int updatePost(SysPosition post);

    /**
     * 查询所有岗位
     *
     * @return 岗位列表
     */
    List<SysPosition> selectPostAll();

    /**
     * 根据医生工号获取岗位选择框列表
     *
     * @param doctId 医生工号
     * @return 选中岗位ID列表
     */
    List<String> selectPostListByDoctId(String doctId);
}
