package com.xmut.pet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xmut.pet.entity.OrderItem;
import com.xmut.pet.mapper.OrderItemMapper;
import com.xmut.pet.service.OrderItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Zi
 * @since 2023-06-08 09:59:14
 */
@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements OrderItemService {

    @Override
    public List<OrderItem> getListByStoreId(Integer storeId) {
        QueryWrapper<OrderItem> queryWrapper=new QueryWrapper();
        queryWrapper.eq("store_id",storeId);
        return this.list(queryWrapper);
    }
}
