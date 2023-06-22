package com.xmut.pet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xmut.pet.entity.Goods;
import com.xmut.pet.entity.Result;
import com.xmut.pet.entity.Store;
import com.xmut.pet.mapper.GoodsMapper;
import com.xmut.pet.service.GoodsService;
import com.xmut.pet.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Zi
 * @since 2023-06-08 09:58:20
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    @Autowired
    private StoreService storeService;

    @Override
    public Result<Page<Goods>> page(Integer pageNum, Integer pageSize, String key, Integer category, Integer status) {
        Result<Page<Goods>> result = new Result<>();
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
        page = this.page(page, queryWrapper);
        List<Goods> goodsList = page.getRecords();
        for (Goods goods : goodsList) {
            Store store = storeService.getById(goods.getStoreId());
            if (store != null) {
                goods.put("storeName", store.getName());
            }
        }
        page.setRecords(goodsList);
        result.setData(page);
        result.put("total", this.count(queryWrapper));
        return result;
    }

    @Override
    public Result<Page<Goods>> pageByGoodsName(Integer pageNum, Integer pageSize, String name) {
        Result<Page<Goods>> result = new Result<>();
        Page<Goods> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", name);
        page = this.page(page, queryWrapper);
        List<Goods> goodsList = page.getRecords();
        for (Goods goods : goodsList) {
            Store store = storeService.getById(goods.getStoreId());
            if (store != null) {
                goods.put("storeName", store.getName());
            }
        }
        page.setRecords(goodsList);
        result.setData(page);
        result.put("total", this.count(queryWrapper));
        return result;
    }


}
