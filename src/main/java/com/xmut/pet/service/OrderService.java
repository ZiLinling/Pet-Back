package com.xmut.pet.service;

import com.xmut.pet.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Zi
 * @since 2023-06-08 09:58:59
 */
public interface OrderService extends IService<Order> {

    List<Order> getListByUserId(Integer userId);
}
