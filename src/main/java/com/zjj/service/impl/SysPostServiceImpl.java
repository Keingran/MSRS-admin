package com.zjj.service.impl;

import com.github.pagehelper.PageHelper;
import com.zjj.dto.SysPosition;
import com.zjj.mapper.SysPostMapper;
import com.zjj.service.ISysPostService;
import com.zjj.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 岗位信息 服务层处理
 */
@Service
public class SysPostServiceImpl implements ISysPostService {

    @Autowired
    private SysPostMapper postMapper;

    /**
     * 获取岗位列表
     */
    @Override
    public List<SysPosition> getPostList(Map<String, String> param, int page, int size) {
        PageHelper.startPage(page, size);
        return postMapper.getPostList(param);
    }

    /**
     * 通过岗位ID查询岗位信息
     *
     * @param postId 岗位ID
     * @return 角色对象信息
     */
    @Override
    public SysPosition selectPostById(Long postId) {
        return postMapper.selectPostById(postId);
    }

    /**
     * 校验岗位名称是否唯一
     *
     * @param post 岗位信息
     * @return 结果
     */
    @Override
    public String checkPostNameUnique(SysPosition post) {
        Long postId = StringUtils.isNull(post.getPostId()) ? -1L : post.getPostId();
        SysPosition info = postMapper.checkPostNameUnique(post.getPostName());
        if (StringUtils.isNotNull(info) && info.getPostId().longValue() != postId.longValue()) {
            return "1";
        }
        return "0";
    }

    /**
     * 校验岗位编码是否唯一
     *
     * @param post 岗位信息
     * @return 结果
     */
    @Override
    public String checkPostCodeUnique(SysPosition post) {
        Long postId = StringUtils.isNull(post.getPostId()) ? -1L : post.getPostId();
        SysPosition info = postMapper.checkPostCodeUnique(post.getPostCode());
        if (StringUtils.isNotNull(info) && info.getPostId().longValue() != postId.longValue()) {
            return "1";
        }
        return "0";
    }

    /**
     * 删除岗位信息
     *
     * @param postId 岗位ID
     * @return 结果
     */
    @Override
    public int deletePostById(Long postId) {
        return postMapper.deletePostById(postId);
    }

    /**
     * 新增保存岗位信息
     *
     * @param post 岗位信息
     * @return 结果
     */
    @Override
    public int insertPost(SysPosition post) {
        return postMapper.insertPost(post);
    }

    /**
     * 修改保存岗位信息
     *
     * @param post 岗位信息
     * @return 结果
     */
    @Override
    public int updatePost(SysPosition post) {
        return postMapper.updatePost(post);
    }

    /**
     * 查询所有岗位
     *
     * @return 岗位列表
     */
    @Override
    public List<SysPosition> selectPostAll() {
        return postMapper.selectPostAll();
    }

    /**
     * 根据医生工号获取岗位选择框列表
     *
     * @param doctId 医生工号
     * @return 选中岗位ID列表
     */
    @Override
    public List<String> selectPostListByDoctId(String doctId) {
        return postMapper.selectPostListByDoctId(doctId);
    }
}
