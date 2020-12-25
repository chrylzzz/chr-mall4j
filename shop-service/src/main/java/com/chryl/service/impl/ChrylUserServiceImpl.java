package com.chryl.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chryl.bean.dto.ChrylUser;
import com.chryl.bean.param.ChrylUserParam;
import com.chryl.common.util.PageAdapter;
import com.chryl.common.util.PageParam;
import com.chryl.dao.ChrylUserMapper;
import com.chryl.service.ChrylUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Chr.yl on 2020/12/21.
 *
 * @author Chr.yl
 */
@Service
public class ChrylUserServiceImpl extends ServiceImpl<ChrylUserMapper, ChrylUser> implements ChrylUserService {

    @Autowired
    private ChrylUserMapper chrylUserMapper;

    @Override
    public IPage<ChrylUser> pageChrylUserByChrylUserParam(PageParam<ChrylUser> page, ChrylUserParam chrylUserParam) {
        List<ChrylUser> chrylUserList = chrylUserMapper.listChrylUserByChrylUserParam(new PageAdapter(page), chrylUserParam);
        page.setRecords(chrylUserList);
        Long countChrylUser = chrylUserMapper.countChrylUser(chrylUserParam);
        page.setTotal(countChrylUser);
        return page;
    }

    @Override
    public void deleteChrylUserBatch(String[] userIds) {
        chrylUserMapper.deleteChrylUserBatch(userIds);
    }


}
