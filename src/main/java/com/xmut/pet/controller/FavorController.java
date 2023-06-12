package com.xmut.pet.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
    @ApiOperation(value = "分页获取用户收藏列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", dataType = "Integer", paramType = "query", value = "页码", required = true),
            @ApiImplicitParam(name = "pageSize", dataType = "Integer", paramType = "query", value = "页大小", required = true),
            @ApiImplicitParam(name = "userId", dataType = "Integer", paramType = "query", value = "用户id", required = true),
            @ApiImplicitParam(name = "type", dataType = "Integer", paramType = "query", value = "收藏类型", required = true),
            //type:1-商品 2-宠物 3-商店
    })
    public Result<Page<Favor>> getListByUserId(Integer pageNum, Integer pageSize, Integer userId, Integer type) {
        Result<Page<Favor>> result = new Result<>();
        result.success("商品:列表请求成功");
        result.setData(favorService.page(pageNum, pageSize, userId, type));
        result.put("total", favorService.count(userId, type));
        return result;
    }
}
