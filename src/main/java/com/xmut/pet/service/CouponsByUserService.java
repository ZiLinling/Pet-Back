package com.xmut.pet.service;

import com.xmut.pet.entity.CouponsByUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Ji
 * @since 2023-09-11 11:27:24
 */
public interface CouponsByUserService extends IService<CouponsByUser> {
   List<CouponsByUser> getCouponByUserId(Integer userId);

   List<String> getTime();

   boolean panicBuy(Integer couponId);
}
