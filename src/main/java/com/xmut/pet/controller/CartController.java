package com.xmut.pet.controller;

import com.xmut.pet.Utils.JwtUtil;
import com.xmut.pet.entity.Result;
import com.xmut.pet.service.CartService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Zi
 * @since 2023-06-08 03:06:15
 */
@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService cartService;

    @Autowired
    private HttpServletRequest request;

    @ApiOperation(value = "新增购物车")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", dataType = "Integer", paramType = "query", value = "用户id", required = true),
            @ApiImplicitParam(name = "goodsId", dataType = "Integer", paramType = "query", value = "商品id", required = true),
    })

    @RequestMapping(method = RequestMethod.POST, value = "/save")
    public Result save(Integer goodsId, Integer num) {
        Result result = new Result();
        Integer userId = JwtUtil.getUserId(request.getHeader("token"));
        if (userId == null) {
            result.fail("新增失败");
        } else {

            if (cartService.save(userId, goodsId, num)) {
                result.success("新增成功");
            } else {
                result.fail("新增失败");
            }
        }
        return result;
    }

    @ApiOperation(value = "移除购物车")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cartId", dataType = "Integer", paramType = "query", value = "购物车id", required = true),
    })
    @RequestMapping(method = RequestMethod.POST, value = "/delete")
    public Result delete(String ids) {
        Result result = new Result();
        Integer userId = JwtUtil.getUserId(request.getHeader("token"));
        List<String> List = Arrays.asList(ids.split(","));
        System.out.println(List);
        if (cartService.delete(List)) {
            result.success("删除成功");
        }
        return result;
    }

    @ApiOperation(value = "获得购物车列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", dataType = "Integer", paramType = "query",value = "用户id", required = true),
    })
    @RequestMapping(method = RequestMethod.GET, value = "/getcart")
    public Result pageCart() {
        Result result = new Result();
        if (request.getHeader("token") != null) {
            Integer userId = JwtUtil.getUserId(request.getHeader("token"));
            result.setData(cartService.getAllCart(userId));
            result.success("查询成功！");
        } else {
            result.fail("请先登录");
        }
        return result;
    }

    @ApiOperation(value = "购物车数量修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "goodsId", dataType = "Integer", paramType = "query", value = "商品id", required = true),
            @ApiImplicitParam(name = "num", dataType = "Integer", paramType = "query", value = "商品数量", required = true),
    })
    @RequestMapping(method = RequestMethod.POST, value = "/updateNum")
    public Result updateNum(Integer cartId, Integer num) {
        Result result = new Result();
        if (cartService.updateNum(cartId, num)) {
            result.success("修改成功");
        } else {
            result.setData("修改失败");
        }
        return result;
    }

    @ApiOperation(value = "购物车数量修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "goodsId", dataType = "Integer", paramType = "query", value = "商品id", required = true),
            @ApiImplicitParam(name = "selected", dataType = "Integer", paramType = "query", value = "商品是否选中", required = true),
    })
    @RequestMapping(method = RequestMethod.POST, value = "/updateSelected")
    public Result updateSelected(Integer cartId, boolean selected) {
        Result result = new Result();
        if (cartService.updateSelected(cartId, selected)) {
            result.success("修改成功");
        } else {
            result.setData("修改失败");
        }
        return result;
    }

    @ApiOperation(value = "全选")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "isAllSelected", dataType = "Integer", paramType = "query", value = "商品是否全部选中", required = true),
    })
    @RequestMapping(method = RequestMethod.POST, value = "/allSelected")
    public Result allSelected(boolean isAllSelected) {
        Result result = new Result();
        Integer userId = JwtUtil.getUserId(request.getHeader("token"));
        if (cartService.allSelected(userId, isAllSelected)) {
            result.success("修改成功");
        } else {
            result.setData("修改失败");
        }
        return result;
    }
}

