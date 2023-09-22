package com.xmut.pet.VO;

import com.xmut.pet.base.BaseEntity;
import com.xmut.pet.entity.OrderItem;
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
public class OrderItemVO extends BaseEntity implements Serializable {

    /**
     * id
     */
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
     * 商品名称
     */

    private String name;
    /**
     * 商品图片
     */
    private String img;
    /**
     * 退货理由
     */
    private String rejectReason;


    public OrderItemVO(OrderItem orderItem) {
        this.id = orderItem.getId();
        this.itemId = orderItem.getItemId();
        this.num = orderItem.getNum();
        this.orderId = orderItem.getOrderId();
        this.price = orderItem.getPrice();
        this.type = orderItem.getType();
        this.rejectReason=orderItem.getRejectReason();

    }

}
