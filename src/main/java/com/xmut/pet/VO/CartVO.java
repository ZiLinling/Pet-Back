package com.xmut.pet.VO;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class CartVO {
    private String name;
    //商店名称
    private Integer storeId;
    //商店id
    private List<goodsVO> goodsVOList;
    //商店里的商品
}
