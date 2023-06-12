package com.xmut.pet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xmut.pet.entity.User;
import com.xmut.pet.mapper.UserMapper;
import com.xmut.pet.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("account",user.getAccount());
        user.setRole(0);
        if(this.count(queryWrapper)!=0) {
            return false;
        }else {
            this.save(user);
            return true;
        }
    }
}
