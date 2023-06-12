package com.xmut.pet.controller;

import com.xmut.pet.entity.Favor;
import com.xmut.pet.entity.Result;
import com.xmut.pet.service.FavorService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Zi
 * @since 2023-06-08 09:58:03
 */
@RestController
@RequestMapping("/favor")
public class FavorController {
    @Autowired
    private FavorService favorService;

    //根据用户id获取收藏列表
    @GetMapping("/getListByUserId")
    @ApiOperation(value = "获取用户收藏列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", dataType = "Integer", paramType = "query",value = "用户id", required = true),
    })
    public Result<List<Favor>> getListByUserId(Integer userId) {
        Result<List<Favor>> result = new Result<>();
        List<Favor> favorList = favorService.getListByUserId(userId);
        result.success("收藏:列表请求成功");
        result.setData(favorList);
        return result;
    }
}
