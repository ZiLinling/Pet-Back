package com.xmut.pet.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xmut.pet.Utils.JwtUtil;
import com.xmut.pet.VO.CartVO;
import com.xmut.pet.VO.GoodsVO;
import com.xmut.pet.entity.Cart;
import com.xmut.pet.mapper.CartMapper;
import com.xmut.pet.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    public boolean delete(String ids) {
        Integer userId = JwtUtil.getUserId(request.getHeader("token"));
        List<String> idList = Arrays.asList(ids.split(","));
        for (String id : idList) {
            this.removeById(id);
        }
        return true;
    }

    @Override
    public List<CartVO> getAllCart(Integer userId) {
        List<CartVO> cartVO = new ArrayList<>();
        cartVO = this.baseMapper.getCartByUserId(userId);
        List<GoodsVO> goodsVOList = this.baseMapper.selectByUserId(userId);
        Integer num = 0;
        for (CartVO cartItem : cartVO) {
            List<GoodsVO> goodsVOItem = new ArrayList<>();
            for (GoodsVO item : goodsVOList){

                if(item.getStoreId()==cartItem.getStoreId()){
                    item.setId(num);
                    //     cartItem.setGoodsVOList(item);
                    goodsVOItem.add(item);
                    num++;
                }
            }
            cartItem.setGoodsVOList(goodsVOItem);
        }
        return cartVO;
    }

    public boolean updateNum(Integer cartId, Integer num) {
        Cart cart = this.getById(cartId);
        cart.setNum(num);
        this.updateById(cart);
        return true;
    }

    public boolean updateSelected(Integer cartId, boolean selected) {
        Cart cart = this.getById(cartId);
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
