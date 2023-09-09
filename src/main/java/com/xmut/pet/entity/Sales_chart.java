package com.xmut.pet.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sales_chart") // 视图的名称
@Data
public class Sales_chart {
    @Id
    /**
     * 物品id(宠物id/周边id)
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
    /**
     * 创建时间
     */
    private String time;
    /**
     * 数量
     */
    private Integer num;

}
