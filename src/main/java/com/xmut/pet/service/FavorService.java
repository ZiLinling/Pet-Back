package com.xmut.pet.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmut.pet.entity.Favor;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Zi
 * @since 2023-06-08 09:58:03
 */
public interface FavorService extends IService<Favor> {

    Page<Favor> page(Integer pageNum, Integer pageSize, Integer userId, Integer type);

    Long count(Integer userId, Integer type);

}
