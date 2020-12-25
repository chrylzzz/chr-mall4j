package com.chryl.bean.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Chr.yl on 2020/12/21.
 *
 * @author Chr.yl
 */

@Data
@TableName("chryl_user")
public class ChrylUser implements Serializable {

    private static final long serialVersionUID = -1028512085483647080L;

    @TableId(type = IdType.INPUT)//input是 自行输入方式
    private String id;

    @NotBlank(message = "用户名不能为空")
    @Size(min = 2, max = 20, message = "用户名长度要在2-20之间")
    private String userName;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)//可写,为可操作,但是不返回给前端
    private String userPassword;

    //注解优先级高于配置
//    注解@JsonFormat主要是后台到前台的时间格式的转换
//    注解@DataFormAT主要是前后到后台的时间格式的转换
    @DateTimeFormat(pattern = "yyyy-MM-dd")//前->后
//    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")//后->前,会覆盖jackson.date-format
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd")//后->前,会覆盖jackson.date-format
    private Date userDate;
//    private LocalDate userDate;
}
