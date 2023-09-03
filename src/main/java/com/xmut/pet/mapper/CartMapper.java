package com.xmut.pet.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xmut.pet.VO.GoodsVO;
import com.xmut.pet.entity.Cart;
import com.xmut.pet.entity.Store;
import org.apache.ibatis.annotations.Select;

import java.util.List;

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

    //根据userid去查跟该userid有关的购物车列表，然后根据购物车中的商品查询商店
    @Select("SELECT DISTINCT  store.* FROM cart,goods,store WHERE user_id = #{ userId } and goods.id=cart.goods_id AND store.id=goods.store_id")
    List<Store> getStoreByUserId(Integer userId);


    @Select("SELECT cart.id AS cartId ,goods.id AS goodsId,num,selected,name,store_id,stock,price,img,status FROM goods,cart WHERE goods.id = cart.goods_id AND user_id= #{ userId }")
    List<GoodsVO> selectByUserId(Integer userId);


    @Select("SELECT id FROM cart WHERE goods_id = #{ goodsId } AND user_id= #{ userId }")
    Integer getId(Integer goodsId, Integer userId);

    @Select("SELECT * FROM cart WHERE goods_id=#{goodsId} AND user_id= #{ userId }")
    Cart isExist(Integer userId, Integer goodsId);

    @Select("SELECT * FROM cart WHERE user_id= #{userId}")
    List<Cart> getByUserId(Integer userId);
}
