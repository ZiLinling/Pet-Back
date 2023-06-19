package com.xmut.pet.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmut.pet.entity.Goods;
import com.xmut.pet.entity.Result;
import com.xmut.pet.service.GoodsService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Zi
 * @since 2023-06-08 09:58:20
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    //添加商品
    @PostMapping("/add")
    @ApiOperation(value = "新增商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "good", dataType = "Goods", paramType = "body",value = "商品信息", required = true),
    })
    public Result add(@RequestBody Goods good) {
        Result result = new Result<>();
        if (goodsService.save(good)) {
            result.success("商品:添加成功");
        } else {
            result.fail("商品:添加失败");
        }
        return result;
    }

    //删除商品
    @PostMapping("/delete")
    @ApiOperation(value = "删除商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", dataType = "Integer", paramType = "query",value = "商品id", required = true),
    })
    public Result delete(Integer id) {
        Result result = new Result<>();
        if (goodsService.removeById(id)) {
            result.success("商品:删除成功");
        } else {
            result.fail("商品:删除失败");
        }
        return result;
    }

    //更新商品
    @PostMapping("/update")
    @ApiOperation(value = "更新商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "good", dataType = "Goods", paramType = "body",value = "商品信息", required = true),
    })
    public Result update(@RequestBody Goods good) {
        Result result = new Result<>();
        if (goodsService.updateById(good)) {
            result.success("商品:更新成功");
        } else {
            result.fail("商品:更新失败");
        }
        return result;
    }

    //获取商品信息
    @GetMapping("/getById")
    @ApiOperation(value = "获取商品信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", dataType = "Integer", paramType = "query", value = "商品id", required = true),
    })
    public Result<Goods> getById(Integer id) {
        Result<Goods> result = new Result<>();
        result.success("商品:获取成功");
        result.setData(goodsService.getById(id));
        return result;
    }

    //分页获取商品列表
    @GetMapping("/getList")
    @ApiOperation(value = "分页获取商品列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", dataType = "Integer", paramType = "query", value = "页码", required = true),
            @ApiImplicitParam(name = "pageSize", dataType = "Integer", paramType = "query", value = "页大小", required = true),
            @ApiImplicitParam(name = "key", dataType = "String", paramType = "query", value = "关键字"),
            @ApiImplicitParam(name = "category", dataType = "Integer", paramType = "query", value = "分类"),
            @ApiImplicitParam(name = "status", dataType = "Integer", paramType = "query", value = "状态"),
    })
    public Result<Page<Goods>> getList(Integer pageNum, Integer pageSize, String key, Integer category, Integer status) {
        Result<Page<Goods>> result = goodsService.page(pageNum, pageSize, key, category, status);
        result.success("商品:列表请求成功");
        return result;
    }

    @GetMapping("/searchList")
    @ApiOperation(value = "分页获取商品列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", dataType = "Integer", paramType = "query", value = "页码", required = true),
            @ApiImplicitParam(name = "pageSize", dataType = "Integer", paramType = "query", value = "页大小", required = true),
            @ApiImplicitParam(name = "name", dataType = "String", paramType = "query", value = "搜索周边商品名称"),
    })
    public Result<Page<Goods>> searchList(Integer pageNum, Integer pageSize, String name) {
        Result<Page<Goods>> result = goodsService.pageByGoodsName(pageNum, pageSize, name);
        result.success("商品:列表请求成功");
        return result;
    }
}
