package com.chryl.admin.controller;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.emoji.EmojiUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chryl.bean.dto.ChrylUser;
import com.chryl.bean.param.ChrylUserParam;
import com.chryl.common.annotation.SysLog;
import com.chryl.common.util.PageParam;
import com.chryl.security.util.SecurityUtils;
import com.chryl.service.ChrylUserService;
import com.chryl.sys.constant.Constant;
import com.chryl.sys.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * Created by Chr.yl on 2020/12/21.
 *
 * @author Chr.yl
 */
@Controller
@RequestMapping("/chryluser/chryluser")
public class ChrUserController {

    @Autowired
    private ChrylUserService chrylUserService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 分页获取
     */
    @GetMapping("/page")
    @PreAuthorize("@pms.hasPermission('chryluser:chryluser:page')")
    public ResponseEntity<IPage<ChrylUser>> page(ChrylUserParam chrylUserParam, PageParam<ChrylUser> page) {
//        Long shopId = SecurityUtils.getSysUser().getShopId();
//        orderParam.setShopId(shopId);
        IPage<ChrylUser> orderIPage = chrylUserService.pageChrylUserByChrylUserParam(page, chrylUserParam);
        return ResponseEntity.ok(orderIPage);
    }


    /**
     * 获取信息
     */
    @GetMapping("/info/{id}")
    @PreAuthorize("@pms.hasPermission('chryluser:chryluser:info')")
    public ResponseEntity<ChrylUser> info(@PathVariable("id") String id) {
        ChrylUser user = chrylUserService.getById(id);
        user.setUserName(EmojiUtil.toUnicode(user.getUserName() == null ? "" : user.getUserName()));
        return ResponseEntity.ok(user);
    }

    /**
     * 修改
     */
    @PutMapping
    @PreAuthorize("@pms.hasPermission('chryluser:chryluser:update')")
    public ResponseEntity<Void> update(@RequestBody ChrylUser user) {
//        user.setUserDate(new Date());
        user.setUserName(EmojiUtil.toAlias(user.getUserName() == null ? "" : user.getUserName()));
//        if (!Objects.equals(user.getShopId(), SecurityUtils.getSysUser().getShopId())) {
//            throw new YamiShopBindException("没有权限获取该用户信息");
//        }
        String password = user.getUserPassword();
        if (StrUtil.isBlank(password)) {//密码为空时不修改密码
            user.setUserPassword(null);
        } else {
            user.setUserPassword(passwordEncoder.encode(password));
        }
        chrylUserService.updateById(user);//只更新赋值的
        return ResponseEntity.ok().build();
    }


    /**
     * 删除用户
     */
    @DeleteMapping
    @PreAuthorize("@pms.hasPermission('chryluser:chryluser:delete')")
    public ResponseEntity<String> delete(@RequestBody String[] userIds) {
        if (userIds.length == 0) {
            return ResponseEntity.badRequest().body("请选择需要删除的用户");
        }
        if (ArrayUtil.contains(userIds, Constant.SUPER_ADMIN_ID)) {
            return ResponseEntity.badRequest().body("系统管理员不能删除");
        }
        if (ArrayUtil.contains(userIds, SecurityUtils.getSysUser().getUserId())) {
            return ResponseEntity.badRequest().body("当前用户不能删除");
        }
        chrylUserService.deleteChrylUserBatch(userIds);
        return ResponseEntity.ok().build();
    }

    /**
     * 保存用户
     */
    @PostMapping
    @PreAuthorize("@pms.hasPermission('chryluser:chryluser:save')")
    public ResponseEntity<String> save(@Valid @RequestBody ChrylUser chrylUser) {
        String username = chrylUser.getUserName();
        ChrylUser dbUser = chrylUserService.getOne(new LambdaQueryWrapper<ChrylUser>()
                .eq(ChrylUser::getUserName, username));

        if (dbUser != null) {
            return ResponseEntity.badRequest().body("该用户已存在");
        }
        chrylUser.setUserPassword(passwordEncoder.encode(chrylUser.getUserPassword()));
        chrylUser.setId(UUID.randomUUID().toString().replaceAll("-", "").substring(2, 15));
        chrylUserService.save(chrylUser);

        return ResponseEntity.ok().build();
    }
}
