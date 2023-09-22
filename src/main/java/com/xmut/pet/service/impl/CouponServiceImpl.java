package com.xmut.pet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmut.pet.Utils.RedisUtil;
import com.xmut.pet.component.CountdownListener;
import com.xmut.pet.entity.Coupon;
import com.xmut.pet.entity.Result;
import com.xmut.pet.entity.Coupon;
import com.xmut.pet.mapper.CouponMapper;
import com.xmut.pet.service.CouponService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Ji
 * @since 2023-09-10 02:39:51
 */
@Service
public class CouponServiceImpl extends ServiceImpl<CouponMapper, Coupon> implements CouponService {

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private CountdownListener countdownListener;

    @Override
    public Result<Page<Coupon>> page(Integer pageNum, Integer pageSize, String account, String name) {
        Result<Page<Coupon>> result = new Result<>();
        Page<Coupon> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Coupon> queryWrapper = new QueryWrapper<>();
        if (account != null && !account.equals("")) {
            queryWrapper.like("account", account);
        }
        if (name != null && !name.equals("")) {
            queryWrapper.like("name", name);
        }
        result.setData(this.page(page, queryWrapper));
        result.put("total", this.count(queryWrapper));
        return result;
    }

    @Override
    public List<Coupon> listCoupons(){
        List<Coupon> coupons = this.list();
        for(Coupon coupon : coupons){
            if(coupon.getType()==1){
                countdownListener.executeCountdownTask(coupon.getId());
                coupon.put("countdown",redisUtil.get("countdown:"+coupon.getId()));
                coupon.put("status",redisUtil.get("status:"+coupon.getId()));
            }
        }
        return coupons;
    }


}
