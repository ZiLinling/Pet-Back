package com.xmut.pet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmut.pet.entity.Goods;
import com.xmut.pet.mapper.GoodsMapper;
import com.xmut.pet.service.GoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Zi
 * @since 2023-06-08 09:58:20
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    @Override
    public Page<Goods> page(Integer pageNum, Integer pageSize, String key, Integer category, Integer status) {
        Page<Goods> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        if (key != null && !"".equals(key)) {
            queryWrapper.like("name", key);
        }
        if (category != null) {
            queryWrapper.eq("category", category);
        }
        if (status != null) {
            queryWrapper.eq("status", status);
        }
        return this.page(page, queryWrapper);
    }

    @Override
    public Long count(String key, Integer category, Integer status) {
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        if (key != null && !"".equals(key)) {
            queryWrapper.like("name", key);
        }
        if (category != null) {
            queryWrapper.eq("category", category);
        }
        if (status != null) {
            queryWrapper.eq("status", status);
        }
        return this.count(queryWrapper);
    }
}
