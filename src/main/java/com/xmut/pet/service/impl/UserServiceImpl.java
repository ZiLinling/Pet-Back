package com.xmut.pet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xmut.pet.entity.Result;
import com.xmut.pet.entity.User;
import com.xmut.pet.mapper.UserMapper;
import com.xmut.pet.service.UserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Zi
 * @since 2023-06-08 10:00:58
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public User login(User user) {
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("account",user.getAccount()).eq("password",user.getPassword());
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
}
