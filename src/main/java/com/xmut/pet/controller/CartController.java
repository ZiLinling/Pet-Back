package com.xmut.pet.controller;

import com.xmut.pet.Utils.JwtUtil;
import com.xmut.pet.entity.User;
import com.xmut.pet.service.CartService;
import com.xmut.pet.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.catalina.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.xmut.pet.entity.Cart;
import com.xmut.pet.entity.Result;
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
    @ApiOperation(value = "新增购物车")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", dataType = "Integer", paramType = "query",value = "用户id", required = true),
            @ApiImplicitParam(name = "goodsId", dataType = "Integer", paramType = "query",value = "商品id", required = true),
    })

    @RequestMapping(method = RequestMethod.POST,value = "/save")
    public Result save( Integer userId ,Integer goodsId){
        Result result = new Result();


        if(userId==null||goodsId==null){
            result.addError("某个id为空");
        }
        else{

            if(cartService.save(userId,goodsId)){
                result.success("新增成功");
            }
            else {
                result.fail("新增失败");
            }
        }
        return result;
    }
    @ApiOperation(value = "移除购物车")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cartId", dataType = "Integer", paramType = "query",value = "购物车id", required = true),
    })
    @RequestMapping(method = RequestMethod.POST,value = "/delete")
    public Result delete( Integer goodsId) {
        Result result = new Result();
            Integer userId=1;


                if (cartService.delete(goodsId,userId)) {
                    result.success("删除成功");
                }

        return result;
    }
    @ApiOperation(value = "获得购物车列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", dataType = "Integer", paramType = "query",value = "用户id", required = true),
    })
    @RequestMapping(method = RequestMethod.GET, value = "/getcart")
    public Result pageCart(Integer userId) {
        Result result = new Result();
//        try {
//            String token = RequestUtil.getCurrentToken();
//            String userId = JwtUtil.validateToken(token);
//            User exitUser = userService.getById(userId);
//            if (token == null || token.equals("")) {
//                result.againLogin("token不能为空，请登录！");
//            } else if (userId == null || userId.equals("") || exitUser == null) {
//                result.againLogin("身份信息失效，请重新登录！");
//            } else {
                result.setData(cartService.getAllCart(userId));
                result.success("查询成功！");

//            }
//        } catch (Exception e) {
//            result.addError(e.toString());
//        }
        return result;
    }
}

