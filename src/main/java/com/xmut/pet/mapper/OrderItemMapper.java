package com.xmut.pet.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xmut.pet.entity.OrderItem;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Zi
 * @since 2023-06-08 09:59:14
 */
public interface OrderItemMapper extends BaseMapper<OrderItem> {
    @Select("SELECT name,img FROM `goods` where id = #{ goodsId }")
    Map<String, Object> getOthersByGoodsId(Integer goodsId);

    @Select("SELECT name,img FROM `pet` where id = #{ petId }")
    Map<String, Object> getOthersByPetId(Integer petId);


}
