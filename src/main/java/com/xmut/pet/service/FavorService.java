package com.xmut.pet.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xmut.pet.entity.Favor;
import com.xmut.pet.entity.Result;

import java.util.List;


/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Zi
 * @since 2023-06-08 09:58:03
 */
public interface FavorService extends IService<Favor> {

    Result<List> page(Integer pageNum, Integer pageSize, Integer userId, Integer type);

    List getItem(Page<Favor> page, Integer type);

    Favor check(Integer userId, Integer itemId, Integer type);
}
