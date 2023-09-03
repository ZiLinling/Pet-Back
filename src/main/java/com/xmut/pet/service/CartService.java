package com.xmut.pet.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xmut.pet.entity.Cart;
import com.xmut.pet.entity.Store;

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

    boolean delete(String GoodsIds);

    List<Store> getAllCart(Integer userId);

    boolean updateNum(Integer goodsId, Integer num);

    boolean updateSelected(Integer goodsId, boolean selected);

    boolean allSelected(Integer userId, boolean isAllselected);
}
