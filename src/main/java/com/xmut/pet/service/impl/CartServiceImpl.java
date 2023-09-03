package com.xmut.pet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xmut.pet.Utils.JwtUtil;
import com.xmut.pet.entity.Cart;
import com.xmut.pet.entity.Goods;
import com.xmut.pet.entity.Store;
import com.xmut.pet.mapper.CartMapper;
import com.xmut.pet.service.CartService;
import com.xmut.pet.service.GoodsService;
import com.xmut.pet.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Zi
 * @since 2023-06-08 03:06:15
 */
@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private StoreService storeService;

    @Override
    public boolean save(Integer userId, Integer goodsId, Integer num) {
        Cart cart = new Cart();
        Cart cartExist = new Cart();
        cartExist = this.baseMapper.isExist(userId, goodsId);
        if (cartExist != null) {
            cartExist.setNum(cartExist.getNum() + num);
            this.updateById(cartExist);
        } else {
            cart.setUserId(userId);
            cart.setGoodsId(goodsId);
            cart.setNum(num);
            cart.setSelected(false);
            this.save(cart);
        }
        return true;
    }

    @Override
    public boolean delete(String goodsIds) {
        Integer userId = JwtUtil.getUserId(request.getHeader("token"));

        String[] idList = goodsIds.split(",");
        for (String goodsId : idList) {
            QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("goods_id", goodsId).eq("user_id", userId);
            Cart cart = this.getOne(queryWrapper);
            this.removeById(cart.getId());
        }
        return true;
    }

    @Override
    public List<Store> getAllCart(Integer userId) {
        //最外层搜索所有跟该userId有关的cart
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        List<Cart> carts = this.list(queryWrapper);
        //得到所有跟cart有关的商家
        List<Store> stores = this.baseMapper.getStoreByUserId(userId);

        for (Store store : stores) {
            List<Goods> goodsList = new ArrayList<>();
            for (Cart cart : carts) {
                //遍历cart将
                Store storeItem = storeService.getStoreByGoodsId(cart.getGoodsId());
                Goods goods = goodsService.getById(cart.getGoodsId());
                goods.put("num", cart.getNum());
                goods.put("selected", cart.getSelected());
                if (Objects.equals(storeItem.getId(), store.getId())) {
                    goodsList.add(goods);
                }
            }
            store.put("goodsList", goodsList);
        }

        return stores;
    }

    public boolean updateNum(Integer goodsId, Integer num) {
        Integer userId = JwtUtil.getUserId(request.getHeader("token"));
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId).eq("goods_id", goodsId);
        Cart cart = this.getOne(queryWrapper);
        Goods goods = goodsService.getById(cart.getGoodsId());
        if (num > goods.getStock()) {
            return false;
        }
        cart.setNum(num);
        this.updateById(cart);
        return true;
    }

    public boolean updateSelected(Integer goodsId, boolean selected) {
        Integer userId = JwtUtil.getUserId(request.getHeader("token"));
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId).eq("goods_id", goodsId);
        Cart cart = this.getOne(queryWrapper);
        cart.setSelected(selected);
        this.updateById(cart);
        return true;
    }

    public boolean allSelected(Integer userId, boolean isAllselected) {
        List<Cart> carts = this.baseMapper.getByUserId(userId);
        for (Cart cart : carts) {
            cart.setSelected(isAllselected);
            this.updateById(cart);
        }
        return true;
    }
}
