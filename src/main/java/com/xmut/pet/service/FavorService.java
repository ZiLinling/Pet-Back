package com.xmut.pet.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xmut.pet.entity.Favor;
import com.xmut.pet.entity.Result;


/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Zi
 * @since 2023-06-08 09:58:03
 */
public interface FavorService extends IService<Favor> {

    Result<Page<Favor>> page(Integer pageNum, Integer pageSize, Integer userId, Integer type);

}
