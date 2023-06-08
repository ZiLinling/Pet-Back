package com.xmut.pet.service.impl;

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

}
