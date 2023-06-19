package com.xmut.pet.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xmut.pet.entity.Goods;
import com.xmut.pet.entity.Result;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Zi
 * @since 2023-06-08 09:58:20
 */
public interface GoodsService extends IService<Goods> {

    Result<Page<Goods>> page(Integer pageNum, Integer pageSize, String key, Integer category, Integer status);

    Result<Page<Goods>> pageByGoodsName(Integer pageNum, Integer pageSize, String name);
}
