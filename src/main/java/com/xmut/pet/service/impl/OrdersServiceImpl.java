package com.xmut.pet.service.impl;

import com.xmut.pet.entity.Orders;
import com.xmut.pet.mapper.OrdersMapper;
import com.xmut.pet.service.OrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Zi
 * @since 2023-06-06 09:27:37
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {

}
