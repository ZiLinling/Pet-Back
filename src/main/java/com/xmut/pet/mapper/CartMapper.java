package com.xmut.pet.mapper;


import java.math.BigDecimal;
import java.util.List;

import com.xmut.pet.VO.CartVO;
import com.xmut.pet.VO.GoodsVO;
import com.xmut.pet.entity.Cart;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Zi
 * @since 2023-06-08 03:06:15
 */
//public interface CartMapper extends BaseMapper<Cart> {
//    @Select("SELECT name,store_id,stock,price,img,status FROM goods,cart WHERE goods.id = cart.goods_id AND user_id =  #{ userId }")
//    List<goodsVO> selectByGoodsId(int userId);
//
//
//
//    @Results({
//            @Result(column = "userId", property = "userId"),
//            @Result(column = "goodsVOList", property = "goodsVOList",
//                    many = @Many(select = "com.xmut.pet.mapper.CartMapper.selectByGoodsId"))
//    })
//   @Select("SELECT DISTINCT goods.store_id,store.name FROM cart,goods,store WHERE user_id = #{ userId } and goods.id=cart.goods_id AND store.id=goods.store_id")
//    List<CartVO> getCartByUserId(Integer userId);
//
//
//}
public interface CartMapper extends BaseMapper<Cart> {


    @Select("SELECT DISTINCT store.id AS storeId,store.name FROM cart,goods,store WHERE user_id = #{ userId } and goods.id=cart.goods_id AND store.id=goods.store_id")
    List<CartVO> getCartByUserId(Integer userId);


    @Select("SELECT  num,selected,name,store_id,stock,price,img,status FROM goods,cart WHERE goods.id = cart.goods_id AND user_id= #{ userId }")
    List<GoodsVO> selectByUserId(Integer userId);


    @Select("SELECT * FROM cart WHERE goods_id=#{goodsId} AND user_id= #{ userId }")
    Cart isExist(Integer userId,Integer goodsId);

}
