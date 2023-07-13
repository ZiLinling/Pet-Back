package com.xmut.pet.controller;

import com.xmut.pet.Utils.JwtUtil;
import com.xmut.pet.entity.Favor;
import com.xmut.pet.entity.Result;
import com.xmut.pet.service.FavorService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * <p>
 * 前端控制器
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

    @Autowired
    private HttpServletRequest request;

    //根据用户id获取收藏列表
    @GetMapping("/list")
    @ApiOperation(value = "分页获取用户收藏列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", dataType = "Integer", paramType = "query", value = "页码", required = true),
            @ApiImplicitParam(name = "pageSize", dataType = "Integer", paramType = "query", value = "页大小", required = true),
            @ApiImplicitParam(name = "type", dataType = "Integer", paramType = "query", value = "收藏类型", required = true),
            //type:1-商品 2-宠物 3-商店
    })
    public Result<List> getList(Integer pageNum, Integer pageSize, Integer type) {
        Integer userId = JwtUtil.getUserId(request.getHeader("token"));
        Result<List> result = favorService.page(pageNum, pageSize, userId, type);
        result.success("商品:列表请求成功");
        return result;
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增收藏")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "item", dataType = "Integer", paramType = "query", value = "收藏id", required = true),
            @ApiImplicitParam(name = "type", dataType = "Integer", paramType = "query", value = "收藏类型", required = true),
            //type:1-商品 2-宠物 3-商店
    })
    public Result add(Integer itemId, Integer type) {
        Result result = new Result<>();
        Integer userId = JwtUtil.getUserId(request.getHeader("token"));
        Favor favor = new Favor();
        favor.setUserId(userId);
        favor.setItemId(itemId);
        favor.setType(type);
        if (favorService.save(favor)) {
            result.setData(favor);
            result.success("收藏:添加成功");
        } else {
            result.fail("收藏:添加失败");
        }
        return result;
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除收藏")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", dataType = "Integer", paramType = "query", value = "收藏id", required = true),
    })
    public Result delete(Integer id) {
        Result result = new Result<>();
        if (favorService.removeById(id)) {
            result.success("收藏:删除成功");
        } else {
            result.fail("收藏:删除失败");
        }
        return result;
    }

    @GetMapping("/check")
    @ApiOperation(value = "删除收藏")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", dataType = "Integer", paramType = "query", value = "收藏id", required = true),
    })
    public Result check(Integer itemId, Integer type) {
        Result result = new Result<>();
        Integer userId = JwtUtil.getUserId(request.getHeader("token"));
        Favor favor = favorService.check(userId, itemId, type);
        if (favor != null) {
            result.setData(favor);
            result.success("收藏:已收藏");
        } else {
            result.fail("收藏:未收藏");
        }
        return result;
    }
}
