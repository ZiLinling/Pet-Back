package com.xmut.pet.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmut.pet.entity.Goods;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Zi
 * @since 2023-06-08 09:58:20
 */
public interface GoodsService extends IService<Goods> {

    Page<Goods> page(Integer pageNum, Integer pageSize, String key, Integer category, Integer status);

    Long count(String key, Integer category, Integer status);

}
