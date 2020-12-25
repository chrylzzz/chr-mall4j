/*
 * Copyright (c) 2018-2999 广州亚米信息科技有限公司 All rights reserved.
 *
 * https://www.gz-yami.com/
 *
 * 未经允许，不可做商业用途！
 *
 * 版权所有，侵权必究！
 */

package com.chryl.sys.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chryl.common.util.PageParam;
import com.chryl.sys.model.SysLog;
import com.chryl.sys.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 系统日志
 *
 * @author lgh
 */
@RestController
@RequestMapping("/sys/log")
public class SysLogController {
    @Autowired
    private SysLogService sysLogService;

    /**
     * 列表
     */
    @GetMapping("/page")
    @PreAuthorize("@pms.hasPermission('sys:log:page')")
    public ResponseEntity<IPage<SysLog>> page(SysLog sysLog, PageParam<SysLog> page) {
        IPage<SysLog> sysLogs = sysLogService.page(page,
                new LambdaQueryWrapper<SysLog>()
                        .like(StrUtil.isNotBlank(sysLog.getUsername()), SysLog::getUsername, sysLog.getUsername())
                        .like(StrUtil.isNotBlank(sysLog.getOperation()), SysLog::getOperation, sysLog.getOperation()));
        return ResponseEntity.ok(sysLogs);
    }

}
