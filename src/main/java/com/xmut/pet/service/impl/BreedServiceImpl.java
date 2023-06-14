package com.xmut.pet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xmut.pet.entity.Breed;
import com.xmut.pet.mapper.BreedMapper;
import com.xmut.pet.service.BreedService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Zi
 * @since 2023-06-08 09:56:07
 */
@Service
public class BreedServiceImpl extends ServiceImpl<BreedMapper, Breed> implements BreedService {
    @Override
    public List<Breed> getBreed(Integer specie) {
        QueryWrapper<Breed> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("specie",specie);
        return this.list(queryWrapper);
    }

}
