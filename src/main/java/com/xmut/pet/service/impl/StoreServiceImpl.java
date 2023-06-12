package com.xmut.pet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmut.pet.entity.Store;
import com.xmut.pet.mapper.StoreMapper;
import com.xmut.pet.service.StoreService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Zi
 * @since 2023-06-08 10:00:41
 */
@Service
public class StoreServiceImpl extends ServiceImpl<StoreMapper, Store> implements StoreService {

    @Override
    public Page<Store> page(Integer pageNum, Integer pageSize, String key, Integer status) {
        Page<Store> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Store> queryWrapper = new QueryWrapper<>();
        if (key != null && !"".equals(key)) {
            queryWrapper.like("name", key);
        }
        if (status != null) {
            queryWrapper.eq("status", status);
        }
        return this.page(page, queryWrapper);
    }

    @Override
    public Long count(String key, Integer status) {
        QueryWrapper<Store> queryWrapper = new QueryWrapper<>();
        if (key != null && !"".equals(key)) {
            queryWrapper.like("name", key);
        }
        if (status != null) {
            queryWrapper.eq("status", status);
        }
        return this.count(queryWrapper);
    }
}
