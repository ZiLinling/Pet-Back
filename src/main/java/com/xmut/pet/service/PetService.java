package com.xmut.pet.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xmut.pet.VO.petVO;
import com.xmut.pet.entity.Pet;
import com.xmut.pet.entity.Result;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Zi
 * @since 2023-06-08 10:00:05
 */
public interface PetService extends IService<Pet> {
    Page<petVO> page(Integer pageNum, Integer pageSize, String BreedName, Integer specie);

    Page<petVO> pageByPetName(Integer pageNum, Integer pageSize, String petName);

    int getCount(Integer id);

    int getCountBySpecie(Integer specie);

    int getCountByPetName(String petName);

    Result<Page<Pet>> page(Integer pageNum, Integer pageSize, Integer storeId, Integer breedId, String name);
}
