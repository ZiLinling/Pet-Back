package com.xmut.pet.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.xmut.pet.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author Zi
 * @since 2023-06-06 09:27:24
 */
@Getter
@Setter
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
     * 宠物种类
     */
    private String species;

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
    private String breed;

    /**
     * 宠物描述
     */
    private String describe;
}
