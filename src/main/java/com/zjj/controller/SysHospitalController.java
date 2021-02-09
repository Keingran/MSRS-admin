package com.zjj.controller;

import com.zjj.common.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hospital")
public class SysHospitalController {


    @GetMapping("/rule")
    public Result getRule(){
        return null;
    }
}
