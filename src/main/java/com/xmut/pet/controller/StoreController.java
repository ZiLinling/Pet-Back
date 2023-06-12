package com.xmut.pet.controller;

import com.xmut.pet.entity.Result;
import com.xmut.pet.entity.Store;
import com.xmut.pet.service.StoreService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Result<Store> getById(Integer id) {
        Result<Store> result = new Result<>();
        result.success("商店:获取成功");
        result.setData(storeService.getById(id));
        return result;
    }


    @PostMapping("/update")
    @ApiOperation(value = "更新商店信息")
    public Result getById(@RequestBody Store store) {
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
    public Result delete(Integer id) {
        Result result = new Result();
        if (storeService.removeById(id)) {
            result.success("商店:删除成功");
        } else {
            result.fail("商店:删除失败");
        }
        return result;
    }



}
