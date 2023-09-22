package com.xmut.pet.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmut.pet.entity.Coupon;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xmut.pet.entity.Result;
import com.xmut.pet.entity.User;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Ji
 * @since 2023-09-10 02:39:51
 */
public interface CouponService extends IService<Coupon> {
    Result<Page<Coupon>> page(Integer pageNum, Integer pageSize, String account, String name);

    List<Coupon> listCoupons();

}
