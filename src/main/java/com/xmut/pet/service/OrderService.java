package com.xmut.pet.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xmut.pet.entity.Order;
import com.xmut.pet.entity.Result;

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

    Result<Page<Order>> page(Integer pageNum, Integer pageSize, String account, String name);

    Integer generate(Order order);

}
