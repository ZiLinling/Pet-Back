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

    @RequestMapping(method = RequestMethod.POST,value = "/save")
    @ApiOperation(value = "新增购物车")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "goodsId", dataType = "Integer", paramType = "query",value = "商品id", required = true),
    })
    public Result save(Integer goodsId){
        Result result = new Result();
        Integer userId = JwtUtil.getUserId(request.getHeader("token"));
        if (userId == null) {
            result.fail("新增失败");
        } else {

            if (cartService.save(userId, goodsId)) {
                result.success("新增成功");
            } else {
                result.fail("新增失败");
            }
        }
        return result;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/delete")
    @ApiOperation(value = "移除购物车")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "goodsId", dataType = "Integer", paramType = "query",value = "商品id", required = true),
    })
    public Result delete(Integer goodsId) {
        Result result = new Result();
        Integer userId = JwtUtil.getUserId(request.getHeader("token"));


                if (cartService.delete(goodsId,userId)) {
                    result.success("删除成功");
                }

        return result;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getcart")
    @ApiOperation(value = "获得购物车列表")
    public Result pageCart() {
        Result result = new Result();
        if(request.getHeader("token")!=null){
            Integer userId = JwtUtil.getUserId(request.getHeader("token"));
            result.setData(cartService.getAllCart(userId));
            result.success("查询成功！");
        }
        else {
            result.fail("请先登录");
        }
        return result;
    }
}

