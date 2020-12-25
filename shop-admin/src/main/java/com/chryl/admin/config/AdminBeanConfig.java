/*
 * Copyright (c) 2018-2999 广州亚米信息科技有限公司 All rights reserved.
 *
 * https://www.gz-yami.com/
 *
 * 未经允许，不可做商业用途！
 *
 * 版权所有，侵权必究！
 */

package com.chryl.admin.config;

import cn.hutool.core.lang.Snowflake;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 雪花id , 配置机房编号和主机编号
 */
@Configuration
@AllArgsConstructor
public class AdminBeanConfig {

    private final AdminConfig adminConfig;

    @Bean
    public Snowflake snowflake() {
        return new Snowflake(adminConfig.getWorkerId(), adminConfig.getDatacenterId());
    }
}
