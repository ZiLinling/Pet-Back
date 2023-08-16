package com.xmut.pet.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xmut.pet.VO.OrderItemVO;
import com.xmut.pet.VO.StoreVO;
import com.xmut.pet.entity.Order;
import com.xmut.pet.entity.OrderItem;
import com.xmut.pet.entity.Result;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Zi
 * @since 2023-06-08 09:59:14
 */
public interface OrderItemService extends IService<OrderItem> {

    Result<List<OrderItemVO>> getListByOrderId(Integer orderId);

    boolean generate(OrderItem orderItem);

    List<Order> listOrderItem();

    boolean toPay(List<Integer> idList);

    boolean toComment(List<Integer> idList);

    boolean cancelOrderItem(List<Integer> idList);

    boolean getSum(StoreVO store, OrderItem item);

    Integer delivery(Integer id);

    boolean confirm(List<Integer> idList);
}
