package com.xmut.pet.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xmut.pet.VO.OrderVO;
import com.xmut.pet.entity.Order;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Zi
 * @since 2023-06-08 09:58:59
 */
public interface OrderMapper extends BaseMapper<Order> {
    @Select("SELECT DISTINCT store.id AS storeId,store.name FROM order,goods,store WHERE user_id = #{ userId } and goods.id=order_item.item_id AND store.id=goods.store_id")
    List<OrderVO> getOrderVOByUserId(Integer userId);
}
