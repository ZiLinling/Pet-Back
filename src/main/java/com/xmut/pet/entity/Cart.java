package com.xmut.pet.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.xmut.pet.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;


/**
 * <p>
 * 
 * </p>
 *
 * @author Zi
 * @since 2023-06-08 03:06:15
 */
@Data
@ApiModel("Cart")
public class Cart extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 商品id
     */
    private Integer goodsId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 数量
     */
    private Integer num;

    /**
     * 是否选中，0未选中，1选中
     */
    private Boolean check;
}
