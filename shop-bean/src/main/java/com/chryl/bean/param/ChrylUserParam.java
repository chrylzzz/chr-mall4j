package com.chryl.bean.param;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * param类只作为综合的参数类,仅仅用作传递多样的参数
 * Created by Chr.yl on 2020/12/21.
 *
 * @author Chr.yl
 */
@Data
public class ChrylUserParam {

    /**
     * 用户名
     */
    private String userName;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date userDate;
}
