package com.xmut.pet.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class CategoryChart {

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
    private Integer orderId;
    /**
     * 商品单价
     */
    private Integer price;
    /**
     * 订单状态
     */
    private Integer status;

    private Integer category;
}
