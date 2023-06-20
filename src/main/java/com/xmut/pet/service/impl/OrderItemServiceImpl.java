package com.xmut.pet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xmut.pet.entity.OrderItem;
import com.xmut.pet.mapper.OrderItemMapper;
import com.xmut.pet.service.CartService;
import com.xmut.pet.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Zi
 * @since 2023-06-08 09:59:14
 */
@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements OrderItemService {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private CartService cartService;

    @Override
    public List<OrderItem> getListByStoreId(Integer storeId) {
        QueryWrapper<OrderItem> queryWrapper = new QueryWrapper();
        queryWrapper.eq("store_id", storeId);
        return this.list(queryWrapper);
    }

    @Override
    public boolean generate(OrderItem orderItem) {
        this.save(orderItem);
        return true;
    }
}
