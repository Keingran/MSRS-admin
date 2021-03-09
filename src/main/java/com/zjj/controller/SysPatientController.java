package com.zjj.controller;


import com.zjj.common.Result;
import com.zjj.common.constant.HttpStatus;
import com.zjj.dto.SysPatient;
import com.zjj.dto.SysPatientCard;
import com.zjj.dto.SysUser;
import com.zjj.dto.model.LoginUser;
import com.zjj.service.ISysPatientService;
import com.zjj.utils.MessageUtils;
import com.zjj.utils.RsaUtils;
import com.zjj.utils.StringUtils;
import com.zjj.web.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 就诊人信息
 */
@RestController
@RequestMapping("/patient")
public class SysPatientController {

    private static final Logger log = LoggerFactory.getLogger(SysPatientController.class);

    @Value("${rsa.privateKey}")
    private String privateKey;  // 私钥

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ISysPatientService patientService;

    /**
     * 获取就诊人列表
     *
     * @param request 根据token获取用户信息
     * @return 就诊人信息
     */
    @GetMapping("/list")
    public Result getPatientList(HttpServletRequest request) {
        LoginUser loginUser = tokenService.getLoginUser(request);
        SysUser user = loginUser.getUser();

        // 如果用户状态为 "1" 则用户账号已停用
        if (user.getStatus().equals("1")) {
            log.info("[SysPatientController --> getPatientList] 用户已停用");
            return Result.error(HttpStatus.LOGIN_STATUS, MessageUtils.message("user.status.blocked"));
        }

        log.info("[SysPatientController --> getPatientList] 用户{}的状态：{}", user.getPhone(), user.getStatus());
        List<SysPatient> patientList = patientService.getPatientList(user.getUserId());
        for (SysPatient sysPatient : patientList) {
            Long patientId = sysPatient.getPatientId();
            List<SysPatientCard> cardList = patientService.getPatientCardList(patientId);
            sysPatient.setCardList(cardList);
        }
        return Result.success(patientList);
    }

    /**
     * 获取就诊人详情
     *
     * @param idCardNo   证件号码
     * @param idCardType 证件类型
     * @param request    userId 用户id
     * @return 存在 or null
     * @throws Exception 异常
     */
    @GetMapping("/detail")
    public Result getPatientDetail(@RequestParam("idCardNo") String idCardNo, @RequestParam("idCardType") String idCardType, HttpServletRequest request) throws Exception {

        LoginUser loginUser = tokenService.getLoginUser(request);
        SysUser user = loginUser.getUser();
        Long userId = user.getUserId();
        // 解密
        String decryptIdCard = RsaUtils.decryptByPrivateKey(privateKey, idCardNo);
        SysPatient patientDetail = patientService.getPatientDetail(decryptIdCard, idCardType, userId);
        return Result.success(patientDetail);
    }

    @GetMapping("/data/{patientId}")
    public Result getPatientData(@PathVariable Long patientId){
        return Result.success(patientService.getPatientData(patientId));
    }

    /**
     * 添加就诊人信息
     *
     * @param patient 就诊人信息
     * @param request 根据token获取用户信息
     * @return 添加成功 or 失败
     */
    @PostMapping("/add")
    public Result insertPatient(@RequestBody SysPatient patient, HttpServletRequest request) {
        LoginUser loginUser = tokenService.getLoginUser(request);
        SysUser user = loginUser.getUser();
        patient.setUserId(user.getUserId());

        log.info("[SysPatientController --> insertPatient] 添加就诊人信息：{}", patient);
        patientService.insertPatient(patient);
        return Result.success(MessageUtils.message("patient.add.success"));
    }

    /**
     * 添加就诊人信息
     *
     * @param patient 就诊人信息
     * @param request 根据token获取用户信息
     * @return 添加成功 or 失败
     */
    @PostMapping("/change")
    public Result changePatient(@RequestBody SysPatient patient, HttpServletRequest request) {
        LoginUser loginUser = tokenService.getLoginUser(request);
        SysUser user = loginUser.getUser();
        patient.setUserId(user.getUserId());

        log.info("[SysPatientController --> insertPatient] 添加就诊人信息：{}", patient);
        patientService.changePatient(patient);
        return Result.success(MessageUtils.message("patient.add.success"));
    }

    /**
     * 删除就诊人
     *
     * @param patient 就诊人
     * @param request userId 用户id
     * @return 删除成功 or null
     * @throws Exception 异常
     */
    @PostMapping("/unbindPatient")
    public Result unBindPatient(@RequestBody SysPatient patient, HttpServletRequest request) throws Exception {
        LoginUser loginUser = tokenService.getLoginUser(request);
        SysUser user = loginUser.getUser();
        patient.setUserId(user.getUserId());

        // 解密
        String decryptCardNo = RsaUtils.decryptByPrivateKey(privateKey, patient.getIdCardNo());
        patient.setIdCardNo(decryptCardNo);

        log.info("[SysPatientController --> unBindPatient] 删除就诊人：{}", patient.getPatientId());
        patientService.unBindPatient(patient);
        return Result.success(MessageUtils.message("patient.unbind.success"));
    }

    /**
     * 检查就诊人是否存在
     *
     * @param idCardNo   证件号码
     * @param idCardType 证件类型
     * @param request    userId 用户id
     * @return 存在 or null
     * @throws Exception 异常
     */
    @GetMapping("/bindCheck")
    public Result patientBindCheck(@RequestParam("idCardNo") String idCardNo, @RequestParam("idCardType") String idCardType, HttpServletRequest request) throws Exception {
        LoginUser loginUser = tokenService.getLoginUser(request);
        SysUser user = loginUser.getUser();
        Long userId = user.getUserId();
        // 解密
        String decryptIdCard = RsaUtils.decryptByPrivateKey(privateKey, idCardNo);
        SysPatient patient = patientService.bindCheck(decryptIdCard, idCardType, userId);
        if (StringUtils.isNotNull(patient)) {
            return Result.error(HttpStatus.PATIENT_ALREADY, MessageUtils.message("patient.already.exists"));
        }
        return Result.success(null, null);
    }

    /**
     * 添加就诊卡
     *
     * @param patientCard 就诊卡信息
     * @return null
     */
    @PostMapping("/bindCard")
    public Result bindPatientCard(@RequestBody SysPatientCard patientCard) throws Exception {
        // 解密
        String decryptCardNo = RsaUtils.decryptByPrivateKey(privateKey, patientCard.getCardNo());
        patientCard.setCardNo(decryptCardNo);
        patientService.bindPatientCard(patientCard);
        return Result.success(MessageUtils.message("patient.card.bind.success"));
    }

    /**
     * 解绑就诊卡
     *
     * @param patientCard 就诊卡信息
     * @return null
     */
    @PostMapping("/unbindCard")
    public Result unbindPatientCard(@RequestBody SysPatientCard patientCard) throws Exception {
        // 解密
        String decryptCardNo = RsaUtils.decryptByPrivateKey(privateKey, patientCard.getCardNo());
        patientCard.setCardNo(decryptCardNo);
        patientService.unbindPatientCard(patientCard);
        return Result.success(MessageUtils.message("patient.card.unbind.success"));
    }
}
