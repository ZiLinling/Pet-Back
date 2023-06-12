package com.xmut.pet.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.math.BigDecimal;

import com.xmut.pet.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author Zi
 * @since 2023-06-08 10:00:05
 */
@Getter
@Setter
@ApiModel("宠物实体类")
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
    private String breedId;

    /**
     * 宠物描述
     */
    private String describe;

    /**
     * 宠物状态
     */
    private String status;

    /**
     * 宠物图片
     */
    private String img;
}
