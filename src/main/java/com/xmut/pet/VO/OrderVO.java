package com.xmut.pet.VO;

import lombok.Data;

import java.util.List;

@Data
public class OrderVO {

    /**
     * 订单id
     */
    private Integer id;
    /**
     * 订单下的商店列表
     */
    private List<StoreVO> storeVOList;


}
