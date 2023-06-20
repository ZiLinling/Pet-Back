package com.xmut.pet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xmut.pet.entity.*;
import com.xmut.pet.mapper.FavorMapper;
import com.xmut.pet.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


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

    @Autowired
    private GoodsService GoodsService;
    @Autowired
    private StoreService storeService;
    @Autowired
    private PetService petService;
    @Autowired
    private BreedService breedService;

    @Override
    public Result<List> page(Integer pageNum, Integer pageSize, Integer userId, Integer type) {
        Result<List> result = new Result<>();
        QueryWrapper<Favor> queryWrapper= new QueryWrapper<>();
        Page<Favor> page = new Page<>(pageNum, pageSize);
        queryWrapper.eq("user_id",userId).eq("type",type);
        result.setData(getItem(this.page(page,queryWrapper),type));
        result.put("total", this.count(queryWrapper));
        return result;
    }

    @Override
    public List<Favor> getItem(Page<Favor> page, Integer type) {
        List<Favor> favorList=page.getRecords();
        for(Favor favor:favorList){
            if(type==1) {
                Pet pet=petService.getById(favor.getItemId());
                pet.put("breed",breedService.getById(pet.getBreedId()).getName());
                favor.put("item",pet);
            } else if(type==2){
                Goods goods = GoodsService.getById(favor.getItemId());
                goods.put("store",storeService.getById(goods.getStoreId()).getName());
                favor.put("item",goods);
            } else if(type==3){
                Store store=storeService.getById(favor.getItemId());
                favor.put("item",store);
            }
        }
        return favorList;
    }

    @Override
    public Favor check(Integer userId, Integer itemId, Integer type){
        QueryWrapper<Favor> queryWrapper= new QueryWrapper<>();
        queryWrapper.eq("user_id",userId).eq("item_id",itemId).eq("type",type);
        return this.getOne(queryWrapper);
    }
}
