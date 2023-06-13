package com.xmut.pet.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmut.pet.VO.CartVO;
import com.xmut.pet.entity.Cart;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Zi
 * @since 2023-06-08 03:06:15
 */
public interface CartService extends IService<Cart> {
    boolean save(Integer userId,Integer goodsId);
    boolean delete(Integer goodsId,Integer userId);
    List<CartVO> getAllCart(Integer userId);

}
