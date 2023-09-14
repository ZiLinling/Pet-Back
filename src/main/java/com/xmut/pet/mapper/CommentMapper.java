package com.xmut.pet.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xmut.pet.entity.Comment;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Zi
 * @since 2023-06-08 09:57:45
 */
public interface CommentMapper extends BaseMapper<Comment> {

    @Select("SELECT * FROM `comment` WHERE goods_id=#{ goodsId } ORDER BY level DESC\n" + "LIMIT 1;")
    Comment getBestCommentByGoodsId(Integer goodsId);

}
