package com.xmut.pet.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xmut.pet.VO.OrderItemVO;
import com.xmut.pet.VO.StoreVO;
import com.xmut.pet.entity.Order;
import com.xmut.pet.entity.OrderItem;

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

    List<OrderItemVO> getListByOrderId(Integer orderId);

    List<OrderItem> getOrderItemsByOrderId(Integer orderId);

    Integer generate(OrderItem orderItem);

    List<Order> listOrderItem();

    boolean toPay(List<Integer> idList);

    boolean toComment(List<Integer> idList);

    boolean cancelOrderItem(List<Integer> idList);

    boolean rejected(List<Integer> idList,String rejectReason);

    boolean getSum(StoreVO store, OrderItem item);

    Integer delivery(Integer id);

    boolean confirm(List<Integer> idList);

//    boolean directPayment(Integer orderId);

    Integer getCost(Integer userId);

    boolean identify(List<Integer> idList,String rejectReason);

    boolean reject(List<Integer> idList);

}
