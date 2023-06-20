package com.xmut.pet.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xmut.pet.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;


/**
 * <p>
 * 
 * </p>
 *
 * @author Zi
 * @since 2023-06-08 09:59:14
 */
@Data
@TableName("order_item")
@ApiModel("OrderItem")
public class OrderItem extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id	
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 物品id(宠物id/周边id)
     */
    private Integer itemId;

    /**
     * 订单数量
     */
    private Integer num;

    /**
     * 0为宠物,1为周边
     */
    private Integer type;
    /**
     * 0为宠物,1为周边
     */
    private Integer OrderId;
    /**
     * 商品单价
     */
    private Integer price;
}
