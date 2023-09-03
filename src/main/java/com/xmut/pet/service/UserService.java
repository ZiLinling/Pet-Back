package com.xmut.pet.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xmut.pet.entity.Result;
import com.xmut.pet.entity.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Zi
 * @since 2023-06-08 10:00:58
 */
public interface UserService extends IService<User> {

    User login(User user);

    Boolean register(User user);

    Result<Page<User>> page(Integer pageNum, Integer pageSize, String account, String name);

    User getByid(Integer id);

    Integer getRole();

}
