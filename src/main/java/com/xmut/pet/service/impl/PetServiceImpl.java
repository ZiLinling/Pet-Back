package com.xmut.pet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xmut.pet.VO.petVO;
import com.xmut.pet.entity.Pet;
import com.xmut.pet.mapper.PetMapper;
import com.xmut.pet.service.PetService;
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
    public Page<petVO> page(Integer pageNum, Integer pageSize, String BreedName, Integer specie) {
        Page<petVO> page=new Page<>(pageNum,pageSize);
        if (specie == 1 || specie == 2 || specie == 3)//判断宠物类别，1是猫，2是狗，3是其他
        {
            return baseMapper.pageBySpecie(page, specie);//返回宠物物种分类信息
        } else if (BreedName == null || BreedName.equals(" ")) {
            return baseMapper.pageByAllBreedName(page);//返回所有宠物分页信息
        }
        return baseMapper.pageByBreedName(page, BreedName);//返回按品种分类的宠物信息

    }

    @Override
    public Page<petVO> pageByPetName(Integer pageNum, Integer pageSize, String petName) {
        Page<petVO> page = new Page<>(pageNum, pageSize);
        return baseMapper.pageByName(page, petName);
    }

    @Override
    public int getCount(Integer id) {
        QueryWrapper<Pet> queryWrapper = new QueryWrapper<>();
        if (id == -1) {
            return this.list().size();
        }
        queryWrapper.eq("breed_id", id);
        return this.list(queryWrapper).size();
    }

    @Override
    public int getCountBySpecie(Integer specie) {
        return baseMapper.listBySpecie(specie).size();
    }

    @Override
    public int getCountByPetName(String petName) {
        return baseMapper.listByName(petName).size();
    }
}
