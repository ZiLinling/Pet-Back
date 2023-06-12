package com.xmut.pet.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmut.pet.entity.Store;
import com.baomidou.mybatisplus.extension.service.IService;

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
}
