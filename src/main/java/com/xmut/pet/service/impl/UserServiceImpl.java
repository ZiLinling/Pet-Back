package com.xmut.pet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xmut.pet.Utils.JwtUtil;
import com.xmut.pet.entity.Result;
import com.xmut.pet.entity.User;
import com.xmut.pet.mapper.UserMapper;
import com.xmut.pet.service.OrderItemService;
import com.xmut.pet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Zi
 * @since 2023-06-08 10:00:58
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private OrderItemService orderItemService;

    @Override
    public User login(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account", user.getAccount()).eq("password", user.getPassword());
        return this.getOne(queryWrapper);
    }

    @Override
    public Boolean register(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account", user.getAccount());
        user.setRole(0);
        if (this.count(queryWrapper) != 0) {
            return false;
        } else {
            this.save(user);
            return true;
        }
    }

    @Override
    public Integer getRole() {
        Integer userId = JwtUtil.getUserId(request.getHeader("token"));
        Integer Cost = orderItemService.getCost(userId);

        Integer role = 0;
        //多一个表存等级
        if (Cost > 8000) {
            role = 6;
        } else if (Cost > 5000) {
            role = 5;
        } else if (Cost > 2000) {
            role = 4;
        } else if (Cost > 1000) {
            role = 3;
        } else if (Cost > 500) {
            role = 2;
        } else if (Cost > 0) {
            role = 1;
        }
        return role;
    }

    @Override
    public Result<Page<User>> page(Integer pageNum, Integer pageSize, String account, String name) {
        Result<Page<User>> result = new Result<>();
        Page<User> page = new Page<>(pageNum, pageSize);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
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

    @Override
    public User getByid(Integer id) {
        return this.getById(id);
    }
}
