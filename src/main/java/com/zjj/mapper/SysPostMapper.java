package com.zjj.mapper;

import com.zjj.dto.SysPosition;

import java.util.List;
import java.util.Map;

public interface SysPostMapper {

    /**
     * 查询岗位数据集合
     *
     * @param param 岗位信息
     * @return 岗位数据集合
     */
    List<SysPosition> getPostList(Map<String, String> param);

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
     * @param postName 岗位名称
     * @return 结果
     */
    public SysPosition checkPostNameUnique(String postName);

    /**
     * 校验岗位编码
     *
     * @param postCode 岗位编码
     * @return 结果
     */
    public SysPosition checkPostCodeUnique(String postCode);

    /**
     * 删除岗位信息
     *
     * @param postId 岗位ID
     * @return 结果
     */
    public int deletePostById(Long postId);

    /**
     * 修改岗位信息
     *
     * @param post 岗位信息
     * @return 结果
     */
    public int updatePost(SysPosition post);

    /**
     * 新增岗位信息
     *
     * @param post 岗位信息
     * @return 结果
     */
    public int insertPost(SysPosition post);

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
