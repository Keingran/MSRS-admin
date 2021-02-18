package com.zjj.controller;

import com.zjj.common.Result;
import com.zjj.dto.SysMenu;
import com.zjj.service.ISysHospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 医院信息
 */
@RestController
@RequestMapping("/hospital")
public class SysHospitalController {

    @Autowired
    private ISysHospitalService hospitalService;


    @GetMapping("/menu")
    public Result getMenu(){
        List<SysMenu> menu = hospitalService.selectMenuList();
        return Result.success(menu);
    }


    @GetMapping("/rule")
    public Result getRule(){
        return null;
    }
}
