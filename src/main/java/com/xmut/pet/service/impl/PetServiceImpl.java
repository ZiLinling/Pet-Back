package com.xmut.pet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmut.pet.VO.petVO;
import com.xmut.pet.entity.Pet;
import com.xmut.pet.mapper.PetMapper;
import com.xmut.pet.service.PetService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Zi
 * @since 2023-06-08 10:00:05
 */
@Service
public class PetServiceImpl extends ServiceImpl<PetMapper, Pet> implements PetService {
    @Override
    public Page<petVO> page(Integer pageNum, Integer pageSize, String BreedName) {
        Page<petVO> page=new Page<>(pageNum,pageSize);
        return baseMapper.pageByBreedName(page,BreedName);

    }
}
