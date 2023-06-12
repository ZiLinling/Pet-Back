package com.xmut.pet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmut.pet.entity.Favor;
import com.xmut.pet.mapper.FavorMapper;
import com.xmut.pet.service.FavorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Zi
 * @since 2023-06-08 09:58:03
 */
@Service
public class FavorServiceImpl extends ServiceImpl<FavorMapper, Favor> implements FavorService {

    @Override
    public Page<Favor> page(Integer pageNum, Integer pageSize, Integer userId, Integer type) {
        Page<Favor> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Favor> queryWrapper = new QueryWrapper<>();
        if (userId != null) {
            queryWrapper.eq("user_id", userId);
        }
        if (type != null) {
            queryWrapper.eq("type", type);
        }
        return this.page(page, queryWrapper);
    }

    @Override
    public Long count(Integer userId, Integer type) {
        QueryWrapper<Favor> queryWrapper = new QueryWrapper<>();
        if (userId != null) {
            queryWrapper.eq("user_id", userId);
        }
        if (type != null) {
            queryWrapper.eq("type", type);
        }
        return this.count(queryWrapper);
    }
}
