package com.xmut.pet.service;

import com.xmut.pet.entity.OrderItem;
import com.baomidou.mybatisplus.extension.service.IService;

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

    List<OrderItem> getListByStoreId(Integer storeId);
}
