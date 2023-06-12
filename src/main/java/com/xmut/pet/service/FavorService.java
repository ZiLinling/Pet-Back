package com.xmut.pet.service;

import com.xmut.pet.entity.Favor;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Zi
 * @since 2023-06-08 09:58:03
 */
public interface FavorService extends IService<Favor> {

    List<Favor> getListByUserId(Integer userId);
}
