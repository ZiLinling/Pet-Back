package com.xmut.pet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xmut.pet.entity.Breed;
import com.xmut.pet.entity.Result;
import com.xmut.pet.mapper.BreedMapper;
import com.xmut.pet.service.BreedService;
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
        QueryWrapper<Breed> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("specie", specie);
        return this.list(queryWrapper);
    }

    @Override
    public List<Breed> getList() {
        QueryWrapper<Breed> queryWrapper = new QueryWrapper<>();
        queryWrapper.ne("status", 0);
        return this.list(queryWrapper);
    }

    @Override
    public Result<Page<Breed>> page(Integer pageNum, Integer pageSize, Integer specie, Integer status) {
        Result<Page<Breed>> result = new Result<>();
        Page<Breed> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Breed> queryWrapper = new QueryWrapper<>();
        if (specie != null) {
            queryWrapper.eq("specie", specie);
        }
        if (status != null) {
            queryWrapper.eq("status", status);
        }
        result.setData(this.page(page, queryWrapper));
        result.put("total", this.count(queryWrapper));
        return result;
    }
}
