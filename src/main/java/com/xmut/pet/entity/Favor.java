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
 * @since 2023-06-06 09:28:58
 */
@Getter
@Setter
public class Favor extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 宠物id
     */
    private Integer petId;

    /**
     * 商品周边id
     */
    private Integer goodsId;

    /**
     * 用户id
     */
    private Integer userId;
}
