package com.xmut.pet.entity;

import java.io.Serializable;

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
 * @since 2023-06-06 09:27:15
 */
@Getter
@Setter
public class Store extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 商店名称
     */
    private String name;

    /**
     * 商店电话
     */
    private String telephone;

    /**
     * 商店描述
     */
    private String describe;

    /**
     * 商店地址
     */
    private String address;

    /**
     * 商店状态
     */
    private Integer status;
}
