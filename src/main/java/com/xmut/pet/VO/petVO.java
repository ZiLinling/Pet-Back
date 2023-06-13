package com.xmut.pet.VO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class petVO {
    private Integer id;
    /**
     * 宠物id
     */
    private String BreedName;
    /**
     * 宠物品种名称
     */
    private String specie;
    /**
     * 宠物类别 1是猫，2是狗，3是其他
     */
    private String name;
    /**
     * 宠物名称
     */
    private Integer age;
    /**
     * 宠物年龄
     */
    private BigDecimal price;
    /**
     * 商宠物价格
     */
    private String img;
    /**
     * 宠物图片
     */
    private String describe;
    /**
     * 宠物描述
     */
}
