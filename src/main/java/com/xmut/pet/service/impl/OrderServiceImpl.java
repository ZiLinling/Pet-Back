package com.xmut.pet.service.impl;

import com.xmut.pet.entity.Order;
import com.xmut.pet.mapper.OrderMapper;
import com.xmut.pet.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Zi
 * @since 2023-06-08 09:58:59
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

}
