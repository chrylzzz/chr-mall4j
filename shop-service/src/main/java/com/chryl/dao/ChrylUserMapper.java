package com.chryl.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chryl.bean.dto.ChrylUser;
import com.chryl.bean.param.ChrylUserParam;
import com.chryl.common.util.PageAdapter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Chr.yl on 2020/12/21.
 *
 * @author Chr.yl
 */
public interface ChrylUserMapper extends BaseMapper<ChrylUser> {

    List<ChrylUser> listChrylUserByChrylUserParam(@Param("adapter") PageAdapter pageAdapter,
                                                  @Param("chrylUserParam") ChrylUserParam chrylUserParam);

    Long countChrylUser(@Param("chrylUserParam") ChrylUserParam chrylUserParam);

    void deleteChrylUserBatch(@Param("userIds") String[] userIds);

}
