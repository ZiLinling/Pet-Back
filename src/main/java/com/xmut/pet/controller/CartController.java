package com.xmut.pet.controller;

import com.xmut.pet.service.CartService;
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

    @RequestMapping(method = RequestMethod.POST,value = "/delete")
    public Result delete( Integer Id) {
        Result result = new Result();

        if (cartService.delete(Id)) {
            result.success("删除成功");
        }
        return result;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getcart")
    public Result pageCart(@RequestBody Cart cart) {
        Result result = new Result();
        Integer userId=1;
//        try {
//            String token = RequestUtil.getCurrentToken();
//            String userId = JwtUtil.validateToken(token);
//            User exitUser = userService.getById(userId);
//            if (token == null || token.equals("")) {
//                result.againLogin("token不能为空，请登录！");
//            } else if (userId == null || userId.equals("") || exitUser == null) {
//                result.againLogin("身份信息失效，请重新登录！");
//            } else {
        System.out.println(cartService.getAllCart(cart));
                result.setData(cartService.getAllCart(cart));
                result.success("查询成功！");

//            }
//        } catch (Exception e) {
//            result.addError(e.toString());
//        }
        return result;
    }
}

