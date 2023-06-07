package com.xmut.pet.service.impl;

import com.xmut.pet.entity.Pet;
import com.xmut.pet.mapper.PetsMapper;
import com.xmut.pet.service.PetService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Zi
 * @since 2023-06-06 09:27:24
 */
@Service
public class PetServiceImpl extends ServiceImpl<PetsMapper, Pet> implements PetService {

}
