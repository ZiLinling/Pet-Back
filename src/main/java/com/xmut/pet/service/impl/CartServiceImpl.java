package com.xmut.pet.service.impl;

import com.xmut.pet.VO.CartVO;
import com.xmut.pet.VO.GoodsVO;
import com.xmut.pet.entity.Cart;
import com.xmut.pet.mapper.CartMapper;
import com.xmut.pet.service.CartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        Cart cartExist=new Cart();
        cartExist=this.baseMapper.isExist(userId,goodsId);
        if(cartExist!=null){
            cartExist.setNum(cartExist.getNum()+1);
            this.updateById(cartExist);
        }else{
            cart.setUserId(userId);
            cart.setGoodsId(goodsId);
            cart.setNum(1);
            cart.setCheck(false);
            this.save(cart);
        }
        return true;
    }

    @Override
    public boolean delete(Integer Id){
        this.removeById(Id);
        return true;
    }
    @Override
    public List<CartVO> getAllCart(Integer userId){
        List<CartVO> cartVO = new ArrayList<>();
        cartVO=this.baseMapper.getCartByUserId(userId);
        List<GoodsVO> goodsVOList= this.baseMapper.selectByUserId(userId);

        for (CartVO cartItem : cartVO) {
            List<GoodsVO> goodsVOItem=new ArrayList<>();
            for (GoodsVO item : goodsVOList){
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
