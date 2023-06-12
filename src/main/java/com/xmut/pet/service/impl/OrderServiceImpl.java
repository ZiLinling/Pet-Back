package com.xmut.pet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xmut.pet.entity.Order;
import com.xmut.pet.mapper.OrderMapper;
import com.xmut.pet.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<Order> getListByUserId(Integer userId) {
        QueryWrapper<Order> queryWrapper=new QueryWrapper();
        queryWrapper.eq("user_id",userId);
        return this.list(queryWrapper);
    }
}
