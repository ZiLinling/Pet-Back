package com.xmut.pet.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.xmut.pet.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author Zi
 * @since 2023-06-08 09:55:47
 */
@Getter
@Setter
public class Address extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 地址id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 电话号码
     */
    private String telephone;

    /**
     * 地区
     */
    private String region;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 是否为默认地址(1为默认)
     */
    private Byte isDefault;

    /**
     * 用户id
     */
    private Integer userId;
}
