package com.xmut.pet.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmut.pet.VO.petVO;
import com.xmut.pet.entity.Goods;
import com.xmut.pet.entity.Store;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Zi
 * @since 2023-06-08 10:00:41
 */
public interface StoreMapper extends BaseMapper<Store> {
    @Select("SELECT DISTINCT pet.*,breed.name as breedName,breed.specie FROM pet,breed,store WHERE pet.breed_id = breed.id AND pet.store_id = '${storeId}'")
    Page<petVO> pagePetByStoreId(@Param("page") Page<petVO> page , @Param("storeId") Integer storeId);

    @Select("SELECT DISTINCT goods.* FROM goods,store WHERE goods.store_id = '${storeId}'")
    Page<petVO> pageGoodsByStoreId(@Param("page") Page<Goods> page , @Param("storeId") Integer storeId);

}
