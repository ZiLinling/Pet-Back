package com.xmut.pet.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xmut.pet.VO.CartVO;
import com.xmut.pet.entity.Cart;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Zi
 * @since 2023-06-08 03:06:15
 */
public interface CartService extends IService<Cart> {
    boolean save(Integer userId, Integer goodsId, Integer num);

    boolean delete(String ids);

    List<CartVO> getAllCart(Integer userId);

    boolean updateNum(Integer cartId, Integer num);

    boolean updateSelected(Integer cartId, boolean selected);

    boolean allSelected(Integer userId, boolean isAllselected);
}
