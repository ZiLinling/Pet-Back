package com.xmut.pet.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.xmut.pet.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;


/**
 * <p>
 * 
 * </p>
 *
 * @author Zi
 * @since 2023-06-08 10:00:58
 */
@Data
@ApiModel("User")
public class User extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 名字
     */
    private String name;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 电话
     */
    private String phone;

    /**
     * 密码
     */
    private String password;

    /**
     * 账号
     */
    private String account;

    /**
     * 0是普通用户，1是管理员
     */
    private Integer role;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 头像地址
     */
    private String img;

    /**
     * 宠物状态
     */
    private Integer status;
}
