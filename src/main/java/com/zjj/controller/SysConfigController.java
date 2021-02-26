package com.zjj.controller;

import com.zjj.common.Result;
import com.zjj.dto.SysMenu;
import com.zjj.service.ISysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 医院信息
 */
@RestController
@RequestMapping("/system")
public class SysConfigController {

    @Autowired
    private ISysConfigService hospitalService;


    @GetMapping("/menu/list")
    public Result getMenu(){
        List<SysMenu> menu = hospitalService.selectMenuList();
        return Result.success(menu);
    }


    @GetMapping("/rule")
    public Result getRule(){
        return null;
    }
}
