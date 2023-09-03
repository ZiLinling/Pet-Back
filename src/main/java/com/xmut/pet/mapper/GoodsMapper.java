package com.xmut.pet.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xmut.pet.entity.CategoryChart;
import com.xmut.pet.entity.Goods;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Zi
 * @since 2023-06-08 09:58:20
 */
public interface GoodsMapper extends BaseMapper<Goods> {

    @Select("select i.*,g.category from (`order_item` `i` join `goods` `g` on((`g`.`id` = `i`.`item_id`AND i.type=1)))")
    List<CategoryChart> getCategorySalesChart();

}
