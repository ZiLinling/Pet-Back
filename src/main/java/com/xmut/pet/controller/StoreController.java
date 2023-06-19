package com.xmut.pet.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmut.pet.entity.Result;
import com.xmut.pet.entity.Store;
import com.xmut.pet.service.StoreService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Zi
 * @since 2023-06-08 10:00:41
 */
@RestController
@RequestMapping("/store")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @GetMapping("/getById")
    @ApiOperation(value = "获取商店信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", dataType = "Integer", paramType = "query", value = "商店id", required = true),
    })
    public Result<Store> getById(Integer id) {
        Result<Store> result = new Result<>();
        result.success("商店:获取成功");
        result.setData(storeService.getById(id));
        return result;
    }


    @PostMapping("/update")
    @ApiOperation(value = "更新商店信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "store", dataType = "Store", paramType = "Body", value = "商店信息", required = true),
    })
    public Result update(@RequestBody Store store) {
        Result result = new Result();
        if (storeService.updateById(store)) {
            result.success("商店:更新成功");
        } else {
            result.fail("商店:更新失败");
        }
        return result;
    }

    //新增商店
    @PostMapping("/add")
    @ApiOperation(value = "新增商店")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "store", dataType = "Store", paramType = "Body", value = "商店信息", required = true),
    })
    public Result add(@RequestBody Store store) {
        Result result = new Result();
        if (storeService.save(store)) {
            result.success("商店:添加成功");
        } else {
            result.fail("商店:添加失败");
        }
        return result;
    }

    //删除商店
    @GetMapping("/delete")
    @ApiOperation(value = "删除商店")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", dataType = "Integer", paramType = "query", value = "商店id", required = true),
    })
    public Result delete(Integer id) {
        Result result = new Result();
        if (storeService.removeById(id)) {
            result.success("商店:删除成功");
        } else {
            result.fail("商店:删除失败");
        }
        return result;
    }

    //分页获取商店列表
    @GetMapping("/getList")
    @ApiOperation(value = "分页获取商店列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", dataType = "Integer", paramType = "query", value = "页码", required = true),
            @ApiImplicitParam(name = "pageSize", dataType = "Integer", paramType = "query", value = "页大小", required = true),
            @ApiImplicitParam(name = "key", dataType = "String", paramType = "query", value = "关键字"),
            @ApiImplicitParam(name = "status", dataType = "Integer", paramType = "query", value = "状态"),
    })
    public Result<Page<Store>> getList(Integer pageNum, Integer pageSize, String key, Integer status) {
        Result<Page<Store>> result = new Result<>();
        result.success("商品:列表请求成功");
        result.setData(storeService.page(pageNum, pageSize, key, status));
        result.put("total", storeService.count(key, status));
        return result;
    }


    @ApiOperation(value="分页查询商店宠物记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name="pageNum",required=true,paramType="query",value="当前页码"),
            @ApiImplicitParam(name="pageSize",required=true,paramType="query",value="每页显示条数"),
            @ApiImplicitParam(name="storeId",paramType="query",value="商店Id"),
            @ApiImplicitParam(name="type",paramType="query",value="判断是宠物还是周边商品，0是宠物，1是周边商品")
    })
    @RequestMapping(method = RequestMethod.POST,value = "/pageByStoreId")
    public Result pageByStoreId(@RequestBody Map map){
        Integer a = 1;
        Integer pageNum = (Integer) map.get("pageNum");
        Integer pageSize = (Integer) map.get("pageSize");
        Integer storeId = Integer.parseInt((String) map.get("storeId"));
        Integer type =(Integer) map.get("type");
        Result result = new Result();
        result.success("分页查询成功");
        result.setData(storeService.pageByStoreId(pageNum,pageSize,storeId,type));
        if(type==0)
        {
            result.put("total",storeService.countByPet(storeId,a));
        }
        else
        {
            result.put("total",storeService.countByGoods(storeId,a));
        }
        return result;
    }


}
