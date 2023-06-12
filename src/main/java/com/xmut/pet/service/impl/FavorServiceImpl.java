package com.xmut.pet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xmut.pet.entity.Favor;
import com.xmut.pet.mapper.FavorMapper;
import com.xmut.pet.service.FavorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Zi
 * @since 2023-06-08 09:58:03
 */
@Service
public class FavorServiceImpl extends ServiceImpl<FavorMapper, Favor> implements FavorService {

    @Override
    public List<Favor> getListByUserId(Integer userId) {
        QueryWrapper<Favor> queryWrapper= new QueryWrapper();
        queryWrapper.eq("user_id",userId);
        return this.list(queryWrapper);
    }
}
