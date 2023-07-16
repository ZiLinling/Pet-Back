package com.xmut.pet.VO;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class StoreVO {

    /**
     * 商店名称
     */
    private String name;
    /**
     * 商店id
     */
    private Integer storeId;

    /**
     * 订单总价
     */
    private BigDecimal price;

    /**
     * item列表
     */
    private List<OrderItemVO> OrderItemVOList;

}
