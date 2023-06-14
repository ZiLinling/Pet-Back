package com.xmut.pet.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.xmut.pet.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * <p>
 * 
 * </p>
 *
 * @author Zi
 * @since 2023-06-08 10:00:05
 */
@Data
@ApiModel("Pet")
public class Pet extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 宠物ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 宠物名称
     */
    private String name;

    /**
     * 宠物年龄
     */
    private Integer age;

    /**
     * 宠物性别
     */
    private String gender;

    /**
     * 宠物价格
     */
    private BigDecimal price;

    /**
     * 宠物品种
     */
    private Integer breedId;

    /**
     * 宠物描述
     */
    private String description;

    /**
     * 宠物状态
     */
    private Integer status;

    /**
     * 宠物图片
     */
    private String img;
}
