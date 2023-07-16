package com.xmut.pet.controller;

import com.xmut.pet.Utils.JwtUtil;
import com.xmut.pet.entity.Result;
import com.xmut.pet.service.BankCardService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Ji
 * @since 2023-07-12 05:00:47
 */
@Controller
@RequestMapping("/bankCard")
public class BankCardController {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private BankCardService bankCardService;

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

//            if (bankCardService.save(userId, goodsId, num)) {
//                result.success("新增成功");
//            } else {
//                result.fail("新增失败");
//            }
        }
        return result;
    }

}
