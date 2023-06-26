package com.xmut.pet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xmut.pet.VO.petVO;
import com.xmut.pet.entity.Goods;
import com.xmut.pet.entity.Pet;
import com.xmut.pet.entity.Store;
import com.xmut.pet.mapper.StoreMapper;
import com.xmut.pet.service.GoodsService;
import com.xmut.pet.service.PetService;
import com.xmut.pet.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Zi
 * @since 2023-06-08 10:00:41
 */
@Service
public class StoreServiceImpl extends ServiceImpl<StoreMapper, Store> implements StoreService {
    @Autowired
    PetService petService;
    @Autowired
    GoodsService goodsService;

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

    @Override
    public Page<petVO> pageByStoreId(Integer pageNum, Integer pageSize, Integer storeId, Integer type) {
        if (type == 1)//type为1返回周边商品
        {
            Page<Goods> page = new Page<>(pageNum, pageSize);
            return baseMapper.pageGoodsByStoreId(page, storeId);
        }
        Page<petVO> page = new Page<>(pageNum, pageSize);
        return baseMapper.pagePetByStoreId(page, storeId);//返回按品种分类的宠物信息

    }

    @Override
    public Long countByPet(Integer storeId, Integer status) {

        QueryWrapper<Pet> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("store_id", storeId);
        if (status != null) {
            queryWrapper.eq("status", status);
        }
        return petService.count(queryWrapper);
    }

    @Override
    public Long countByGoods(Integer storeId, Integer status) {

        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("store_id", storeId);
        if (status != null) {
            queryWrapper.eq("status", status);
        }
        return goodsService.count(queryWrapper);
    }
}
