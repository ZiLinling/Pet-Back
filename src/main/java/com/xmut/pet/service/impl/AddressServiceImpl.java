package com.xmut.pet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xmut.pet.entity.Address;
import com.xmut.pet.mapper.AddressMapper;
import com.xmut.pet.service.AddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Zi
 * @since 2023-06-08 09:55:47
 */
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements AddressService {

    @Override
    public List<Address> getListByUserId(int userId) {
        QueryWrapper<Address> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        return this.list(queryWrapper);
    }
}
