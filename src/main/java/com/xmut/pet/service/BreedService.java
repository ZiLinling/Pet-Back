package com.xmut.pet.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xmut.pet.entity.Breed;
import com.xmut.pet.entity.Result;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Zi
 * @since 2023-06-08 09:56:07
 */
public interface BreedService extends IService<Breed> {

    List<Breed> getBreed(Integer specie);

    List<Breed> getList();

    Result<Page<Breed>> page(Integer pageNum, Integer pageSize, Integer specie, Integer status);
}
