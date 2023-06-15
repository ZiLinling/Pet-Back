package com.xmut.pet.VO;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class GoodsVO {
    private Integer id;

    private Integer goodsId;

    private String name;
    /**
     * 商店id
     */
    private Integer storeId;
    /**
     * 库存
     */
    private Integer stock;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 商品图片
     */
    private String img;
    /*
     * 商品状态
     */
    private Integer status;
    /*
     * 是否选中
     */
    private boolean selected;
    /*
     * 数量
     */
    private Integer num;
}
