package com.xmut.pet.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xmut.pet.entity.Address;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Zi
 * @since 2023-06-08 09:55:47
 */
public interface AddressService extends IService<Address> {

    List<Address> getListByUserId(int userId);

    Address getAddressByDefault(int userId);

}
