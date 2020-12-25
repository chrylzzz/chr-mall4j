/*
 * Copyright (c) 2018-2999 广州亚米信息科技有限公司 All rights reserved.
 *
 * https://www.gz-yami.com/
 *
 * 未经允许，不可做商业用途！
 *
 * 版权所有，侵权必究！
 */

package com.chryl.security.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.chryl.security.enums.App;
import com.chryl.security.model.AppConnect;

/**
 * @author lgh on 2018/09/07.
 */
public interface AppConnectService extends IService<AppConnect> {

    AppConnect getByBizUserId(String bizUserId, App app);
}
