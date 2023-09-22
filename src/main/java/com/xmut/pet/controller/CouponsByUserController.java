package com.xmut.pet.controller;

import com.xmut.pet.Utils.DateTool;
import com.xmut.pet.Utils.JwtUtil;
import com.xmut.pet.Utils.RedisUtil;
import com.xmut.pet.entity.Cart;
import com.xmut.pet.entity.CouponsByUser;
import com.xmut.pet.entity.Result;
import com.xmut.pet.entity.Store;
import com.xmut.pet.service.CouponsByUserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Ji
 * @since 2023-09-11 11:27:24
 */
@RestController
@RequestMapping("/couponsByUser")
public class CouponsByUserController {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private CouponsByUserService couponsByUserService;

    @Autowired
    private RedisUtil redisUtil;

    @PostMapping("/add")
    @ApiOperation(value = "用户获取优惠券")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "coupon", dataType = "Coupon", paramType = "query", value = "优惠券", required = true),
    })
    public Result<CouponsByUser> add(Integer couponId) {
        Result<CouponsByUser> result = new Result<>();
        Integer userId = JwtUtil.getUserId(request.getHeader("token"));
        CouponsByUser couponsByUser=new CouponsByUser();
        couponsByUser.setCouponId(couponId);
        couponsByUser.setUserId(userId);
        couponsByUser.setGetTime(DateTool.getCurrTime());
        couponsByUser.setExpireTime(DateTool.getCurrTime()+1);
        if (couponsByUserService.save(couponsByUser)) {
            result.setData(couponsByUser);
            result.success("优惠券:属于你了");
        } else {
            result.fail("优惠券:没拿到哦");
        }
        return result;
    }

    @PostMapping("/getPanicBuyCoupon")
    @ApiOperation(value = "用户获取优惠券")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "coupon", dataType = "Coupon", paramType = "query", value = "优惠券", required = true),
    })
    public Result<CouponsByUser> getPanicBuyCoupon(Integer couponId) {
        Result<CouponsByUser> result = new Result<>();
        if (couponsByUserService.panicBuy(couponId)) {
            result.success("优惠券:属于你了");
        } else {
            result.fail("网络异常");
        }
        return result;
    }

    @ApiOperation(value = "获得优惠券列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", dataType = "Integer", paramType = "query", value = "用户id", required = true),
    })
    @RequestMapping(method = RequestMethod.GET, value = "/getCoupons")
    public Result<List<CouponsByUser>> getCoupons() {
        Result<List<CouponsByUser>> result = new Result<>();
        if (request.getHeader("token") != null) {
            Integer userId = JwtUtil.getUserId(request.getHeader("token"));
            result.setData(couponsByUserService.getCouponByUserId(userId));
            result.success("查询成功！");
        } else {
            result.fail("请先登录");
        }
        return result;
    }

    @ApiOperation(value = "获得时间")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", dataType = "Integer", paramType = "query", value = "用户id", required = true),
    })
    @RequestMapping(method = RequestMethod.GET, value = "/getTime")
    public Result<List<String>> getTime() {
        Result<List<String>> result = new Result<>();
            result.setData(couponsByUserService.getTime());
            result.success("查询成功！");

        return result;
    }

}
