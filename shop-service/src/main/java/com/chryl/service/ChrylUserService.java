package com.chryl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chryl.bean.dto.ChrylUser;
import com.chryl.bean.param.ChrylUserParam;
import com.chryl.common.util.PageParam;

/**
 * Created by Chr.yl on 2020/12/21.
 *
 * @author Chr.yl
 */
public interface ChrylUserService extends IService<ChrylUser> {
    IPage<ChrylUser> pageChrylUserByChrylUserParam(PageParam<ChrylUser> page, ChrylUserParam chrylUserParam);


    /**
     * 根据用户id 批量删除用户
     *
     * @param userIds
     */
    void deleteChrylUserBatch(String[] userIds);

}
