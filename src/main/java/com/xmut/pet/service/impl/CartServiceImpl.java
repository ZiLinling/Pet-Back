package com.xmut.pet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmut.pet.VO.CartVO;
import com.xmut.pet.VO.goodsVO;
import com.xmut.pet.entity.Cart;
import com.xmut.pet.entity.Store;
import com.xmut.pet.mapper.CartMapper;
import com.xmut.pet.service.CartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Zi
 * @since 2023-06-08 03:06:15
 */
@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {
    @Override
    public boolean save(Integer userId,Integer goodsId){
        Cart cart=new Cart();
        cart.setUserId(userId);
        cart.setGoodsId(goodsId);
        cart.setNum(1);
        this.save(cart);
        return true;
    }

    @Override
    public boolean delete(Integer Id){
        this.removeById(Id);
        return true;
    }
    @Override
    public List<CartVO> getAllCart(Cart cart){
        List<CartVO> cartVO = new ArrayList<>();
        cartVO=this.baseMapper.getCartByUserId(cart.getUserId());
        List<goodsVO> goodsVOList= this.baseMapper.selectByUserId(cart.getUserId());

        for (CartVO cartItem : cartVO) {
            List<goodsVO> goodsVOItem=new ArrayList<>();
            for (goodsVO item : goodsVOList){
                if(item.getStoreId()==cartItem.getStoreId()){
               //     cartItem.setGoodsVOList(item);
                    goodsVOItem.add(item);
                }
            }
            cartItem.setGoodsVOList(goodsVOItem);
        }
        return cartVO;
    }
}
