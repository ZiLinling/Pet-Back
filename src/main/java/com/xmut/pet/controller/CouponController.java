package com.xmut.pet.controller;

import cn.hutool.http.server.HttpServerRequest;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmut.pet.Utils.DateTool;
import com.xmut.pet.Utils.JwtUtil;
import com.xmut.pet.Utils.RedisUtil;
import com.xmut.pet.entity.Coupon;
import com.xmut.pet.entity.Favor;
import com.xmut.pet.entity.Result;
import com.xmut.pet.entity.User;
import com.xmut.pet.service.CouponService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Ji
 * @since 2023-09-10 02:39:51
 */
@RestController
@RequestMapping("/coupon")
public class CouponController {
    @Autowired
    private CouponService couponService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private RedisUtil redisUtil;



    @PostMapping("/add")
        @ApiOperation(value = "新增优惠券")
        @ApiImplicitParams({
                @ApiImplicitParam(name = "coupon", dataType = "Coupon", paramType = "query", value = "优惠券", required = true),
        })
        public Result<Coupon> add(@RequestBody Coupon coupon) {
            Result<Coupon> result = new Result<>();
            if (couponService.save(coupon)) {
                result.setData(coupon);
                result.success("优惠券:添加成功");
            } else {
                result.fail("优惠券:添加失败");
            }
            return result;
    }
    @PostMapping("/addPanicBuyCoupon")
    @ApiOperation(value = "新增优惠券")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "coupon", dataType = "Coupon", paramType = "query", value = "优惠券", required = true),
    })
    public Result<Coupon> addPanicBuyCoupon(@RequestBody Coupon coupon,Integer stock) {
        Result<Coupon> result = new Result<>();
        if (couponService.save(coupon)) {
            result.setData(coupon);
//            String RealTime=DateTool.getCurrTime();
            redisUtil.set("create_time:"+coupon.getId(), coupon.getCreateTime());
            redisUtil.set("end_time:"+coupon.getId(),coupon.getEndTime());
            redisUtil.set("stock:"+coupon.getId(), String.valueOf(stock));
            result.success("优惠券:添加成功");
        } else {
            result.fail("网络异常");
        }
        return result;
    }


    @PostMapping("/update")
    @ApiOperation(value = "修改优惠券")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "coupon", dataType = "Coupon", paramType = "query", value = "优惠券", required = true),
    })
    public Result<Coupon> update(@RequestBody Coupon coupon) {
        Result<Coupon> result = new Result<>();
        if (couponService.updateById(coupon)) {
            result.setData(coupon);
            result.success("优惠券:修改成功");
        } else {
            result.fail("优惠券:修改失败");
        }
        return result;
    }

    @GetMapping("/getList")
    @ApiOperation(value = "分页获取优惠券列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", dataType = "Integer", paramType = "query", value = "页码", required = true),
            @ApiImplicitParam(name = "pageSize", dataType = "Integer", paramType = "query", value = "页数", required = true),
            @ApiImplicitParam(name = "account", dataType = "String", paramType = "query", value = "账号", required = false),
            @ApiImplicitParam(name = "name", dataType = "String", paramType = "query", value = "用户名", required = false),
    })
    public Result<Page<Coupon>> getList(Integer pageNum, Integer pageSize, String account, String name) {
        Result<Page<Coupon>> result = couponService.page(pageNum, pageSize, account, name);
        result.success("商品:列表请求成功");
        return result;
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", dataType = "Integer", paramType = "query", value = "用户id列表", required = true),
    })
    public Result delete(String ids) {
        Result result = new Result<>();
        List<String> idList = Arrays.asList(ids.split(","));
        if (couponService.removeByIds(idList)) {
            result.success("删除成功");
        } else {
            result.fail("删除失败");
        }
        return result;
    }

    @GetMapping("/listCoupons")
    @ApiOperation(value = "分页获取优惠券列表")
    public Result<List<Coupon>> listCoupons() {
        Result<List<Coupon>> result =  new Result<>();
        result.setData(couponService.listCoupons());
        result.success("商品:列表请求成功");
        return result;
    }

}
