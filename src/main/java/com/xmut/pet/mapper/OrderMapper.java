package com.xmut.pet.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xmut.pet.VO.OrderVO;
import com.xmut.pet.entity.Order;
import com.xmut.pet.entity.Sales_chart;
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

    @Select("select distinct `i`.`id` AS `id`,`i`.`num` AS `num`,`i`.`type` AS `type`,`i`.`price` AS `price`,`i`.`order_id` AS `order_id`,`i`.`status` AS `status`,`o`.`create_time` AS `time` from (`order_item` `i` join `order` `o` on((`o`.`id` = `i`.`order_id`)))")
    List<Sales_chart> getChartData();
}
