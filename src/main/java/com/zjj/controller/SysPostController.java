package com.zjj.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.zjj.common.Result;
import com.zjj.dto.SysPosition;
import com.zjj.service.ISysPostService;
import com.zjj.utils.PageUtils;
import com.zjj.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 岗位信息
 */
@RestController
@RequestMapping("/post")
public class SysPostController {

    @Autowired
    private ISysPostService postService;

    private static final String NOT_UNIQUE = "1"; // 校验返回结果码

    /**
     * 获取岗位列表
     */
    @PostMapping("/list")
    public Result getPostList(@RequestBody JSONObject dto) {
        Map<String, String> param = JSONObject.parseObject(dto.toJSONString(), new TypeReference<Map<String, String>>() {
        });
        int page = StringUtils.isNotEmpty(param.get("page")) ? Integer.parseInt(param.get("page")) : 1;
        int size = StringUtils.isNotEmpty(param.get("size")) ? Integer.parseInt(param.get("size")) : 10;

        List<SysPosition> list = postService.getPostList(param, page, size);
        // 分页处理
        JSONObject pageResult = PageUtils.getPageResult(list, page, size);
        return Result.success(pageResult);
    }

    /**
     * 根据岗位编号获取详细信息
     */
    @GetMapping("/detail/{postId}")
    public Result getInfo(@PathVariable Long postId) {
        return Result.success(postService.selectPostById(postId));
    }

    /**
     * 新增岗位
     */
    @PostMapping("/add")
    public Result add(@Validated @RequestBody SysPosition post) {
        if (NOT_UNIQUE.equals(postService.checkPostNameUnique(post))) {
            return Result.error("新增岗位'" + post.getPostName() + "'失败，岗位名称已存在");
        } else if (NOT_UNIQUE.equals(postService.checkPostCodeUnique(post))) {
            return Result.error("新增岗位'" + post.getPostName() + "'失败，岗位编码已存在");
        }
        return PageUtils.toResult(postService.insertPost(post), "post.add.success");
    }

    /**
     * 修改岗位
     */
    @PutMapping("/update")
    public Result edit(@Validated @RequestBody SysPosition post) {
        if (NOT_UNIQUE.equals(postService.checkPostNameUnique(post))) {
            return Result.error("修改岗位'" + post.getPostName() + "'失败，岗位名称已存在");
        } else if (NOT_UNIQUE.equals(postService.checkPostCodeUnique(post))) {
            return Result.error("修改岗位'" + post.getPostName() + "'失败，岗位编码已存在");
        }
        return PageUtils.toResult(postService.updatePost(post), "post.update.success");
    }

    /**
     * 删除岗位
     */
    @DeleteMapping("/delete/{postId}")
    public Result remove(@PathVariable Long postId) {
        return PageUtils.toResult(postService.deletePostById(postId), "post.delete.success");
    }

}
