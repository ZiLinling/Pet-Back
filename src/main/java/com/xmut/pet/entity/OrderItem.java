package com.xmut.pet.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2023-06-06 09:28:33
 */
@Getter
@Setter
@TableName("order_item")
public class OrderItem extends BaseEntity implements Serializable {

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
     * 订单数量
     */
    private Integer num;

    /**
     * 是否评论，0是未评论，1是已评论
     */
    private String isComment;
}
