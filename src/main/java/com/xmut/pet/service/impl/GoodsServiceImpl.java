package com.xmut.pet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xmut.pet.entity.*;
import com.xmut.pet.mapper.GoodsMapper;
import com.xmut.pet.service.GoodsService;
import com.xmut.pet.service.OrderItemService;
import com.xmut.pet.service.PetService;
import com.xmut.pet.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private PetService petService;

    @Override
    public Result<Page<Goods>> page(Integer pageNum, Integer pageSize, Integer storeId, String name, Integer category, Integer status) {
        Result<Page<Goods>> result = new Result<>();
        Page<Goods> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        if (name != null && !"".equals(name)) {
            queryWrapper.like("name", name);
        }
        if (category != null) {
            queryWrapper.eq("category", category);
        }
        if (status != null) {
            queryWrapper.eq("status", status);
        }
        if (storeId != null) {
            queryWrapper.eq("store_id", storeId);
        }
        //根据库存排序
        queryWrapper.orderByDesc("stock");
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

    @Override
    public List<String> getCategorySalesChart() {
        List<String> Sums = new ArrayList<>();
        List<CategoryChart> categoryCharts = this.baseMapper.getCategorySalesChart();
        Integer toySum = 0, HealthSum = 0, grainSum = 0;
        for (CategoryChart categoryChart : categoryCharts) {
            //1-玩具 2-保健  3-主粮
            if (categoryChart.getCategory() == 1) {
                toySum += categoryChart.getPrice() * categoryChart.getNum();
            } else if (categoryChart.getCategory() == 2) {
                HealthSum += categoryChart.getPrice() * categoryChart.getNum();
            } else if (categoryChart.getCategory() == 3) {
                grainSum += categoryChart.getPrice() * categoryChart.getNum();
            }
        }
        Sums.add(String.valueOf(toySum));
        Sums.add(String.valueOf(HealthSum));
        Sums.add(String.valueOf(grainSum));
        return Sums;
    }

    @Override
    public boolean reduceStock(OrderItem orderItem) {
        //根据订单详情列减少库存
        OrderItem item = orderItemService.getById(orderItem.getId());
        Integer difference = 0;
        if (item == null) {
            difference = orderItem.getNum();
        } else if (orderItem.getNum() != item.getNum()) {
            //作差，如果新的比旧的大，后续库存减去更多的。反之，负负得正就会增加
            difference = orderItem.getNum() - item.getNum();
        }
        Goods goods = this.getById(orderItem.getItemId());
        goods.setStock(goods.getStock() - difference);
        if (goods.getStock() == 0) {
            goods.setStatus(0);
        } else {
            goods.setStatus(1);
        }
        this.updateById(goods);
        return true;
    }

    @Override
    public boolean returnStock(OrderItem orderItem) {
        if (orderItem.getType() == 0) {
            Pet pet = petService.getById(orderItem.getItemId());
            pet.setStatus(1);
            petService.updateById(pet);
            //如果是宠物就不用增加库存,直接给他状态设置为1，继续售卖
            return true;
        }
        //取消订单就把库存加回来
        Goods goods = this.getById(orderItem.getItemId());
        goods.setStock(goods.getStock() + orderItem.getNum());
        if (goods.getStatus() == 0) {
            goods.setStatus(1);
        }
        this.updateById(goods);
        return true;
    }

    ;
}
