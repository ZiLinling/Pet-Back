package com.xmut.pet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xmut.pet.Utils.DateTool;
import com.xmut.pet.Utils.JwtUtil;
import com.xmut.pet.Utils.RedisUtil;
import com.xmut.pet.entity.Coupon;
import com.xmut.pet.entity.CouponsByUser;
import com.xmut.pet.entity.Result;
import com.xmut.pet.mapper.CouponsByUserMapper;
import com.xmut.pet.service.CouponsByUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.io.Console;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Ji
 * @since 2023-09-11 11:27:24
 */
@Service
public class CouponsByUserServiceImpl extends ServiceImpl<CouponsByUserMapper, CouponsByUser> implements CouponsByUserService {

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private HttpServletRequest request;
//    @Autowired
//    private final Integer userId;

    @Override
    public List<CouponsByUser> getCouponByUserId(Integer userId){
        QueryWrapper<CouponsByUser> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        return  this.list(queryWrapper);

    }

    @Override
    public List<String> getTime(){
        List<String> time=new ArrayList<>();
        String realTime = DateTool.getCurrTime();
//        Console.class
//        redisUtil
        time.add(realTime.substring(0, 4));  // 年
        time.add(realTime.substring(5, 7));  // 月
        time.add(realTime.substring(8, 10)); // 日
        time.add(realTime.substring(11, 13)); // 时
        time.add(realTime.substring(14, 16)); // 分
        time.add(realTime.substring(17, 19)); // 秒
        //从0开始分别是年，月，日，时，分，秒
        return time;
    }

    @Override
    public boolean panicBuy(Integer couponId){
        boolean lock=redisUtil.setIfAbsent("panicBuyLock:", String.valueOf(couponId));
        if(lock){
            System.out.println("成功获取锁");
            CouponsByUser couponsByUser=new CouponsByUser();
            Integer userId= JwtUtil.getUserId(request.getHeader("token"));

            couponsByUser.setCouponId(couponId);
            couponsByUser.setUserId(userId);
            couponsByUser.setGetTime(DateTool.getCurrTime());
            couponsByUser.setExpireTime(DateTool.getCurrTime()+1);
            this.save(couponsByUser);
            Integer stock= Integer.valueOf(redisUtil.get("stock:"+couponId))-1;
            redisUtil.set("stock:"+couponId, String.valueOf(stock));
            redisUtil.delete("panicBuyLock:");
        }else {
            System.out.println("网络异常");
            return false;
        }

        return true;
    }
}
