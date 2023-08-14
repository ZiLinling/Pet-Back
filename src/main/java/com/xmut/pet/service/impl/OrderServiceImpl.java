package com.xmut.pet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xmut.pet.Utils.DateTool;
import com.xmut.pet.Utils.JwtUtil;
import com.xmut.pet.entity.Order;
import com.xmut.pet.entity.Result;
import com.xmut.pet.mapper.OrderMapper;
import com.xmut.pet.service.OrderService;
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
 * @since 2023-06-08 09:58:59
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
    @Autowired
    private HttpServletRequest request;

    @Override
    public List<Order> getListByUserId(Integer userId) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id", userId);
        return this.list(queryWrapper);
    }

    public Integer generate(Order order) {
        order.setUserId(JwtUtil.getUserId(request.getHeader("token")));
        order.setCreateTime(DateTool.getCurrTime());
        order.setIsComment(false);
        this.save(order);
        return order.getId();
    }

    @Override
    public Result<Page<Order>> page(Integer pageNum, Integer pageSize, String account, String name) {
        Result<Page<Order>> result = new Result<>();
        Page<Order> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        if (account != null && !account.equals("")) {
            queryWrapper.like("account", account);
        }
        if (name != null && !name.equals("")) {
            queryWrapper.like("name", name);
        }
        result.setData(this.page(page, queryWrapper));
        result.put("total", this.count(queryWrapper));
        return result;
    }


}
