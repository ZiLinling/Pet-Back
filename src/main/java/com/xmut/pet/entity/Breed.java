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
 * @since 2023-06-08 09:56:07
 */
@Getter
@Setter
public class Breed extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 品种名称
     */
    private String name;

    /**
     * 宠物物种(1是猫,2是狗,3是其他)
     */
    private String specie;

    /**
     * 状态
     */
    private String status;

    /**
     * 物种图片
     */
    private String img;
}
