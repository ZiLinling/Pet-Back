package com.xmut.pet.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xmut.pet.VO.petVO;
import com.xmut.pet.entity.Store;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Zi
 * @since 2023-06-08 10:00:41
 */
public interface StoreService extends IService<Store> {

    Page<Store> page(Integer pageNum, Integer pageSize, String key, Integer status);

    Long count(String key, Integer status);

    Page<petVO> pageByStoreId(Integer pageNum, Integer pageSize, Integer storeId, Integer type);

    Long countByPet(Integer storeId, Integer status);

    Long countByGoods(Integer storeId, Integer status);
}
