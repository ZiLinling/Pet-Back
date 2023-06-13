package com.xmut.pet.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmut.pet.VO.CartVO;
import com.xmut.pet.VO.petVO;
import com.xmut.pet.entity.Pet;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Zi
 * @since 2023-06-08 10:00:05
 */
public interface PetService extends IService<Pet> {
    Page<petVO> page(Integer pageNum, Integer pageSize, String BreedName);

}
