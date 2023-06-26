package com.xmut.pet.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmut.pet.entity.Breed;
import com.xmut.pet.entity.Result;
import com.xmut.pet.service.BreedService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Zi
 * @since 2023-06-08 09:56:07
 */
@RestController
@RequestMapping("/breed")
public class BreedController {

    @Autowired
    private BreedService breedService;

    @GetMapping("/getBreed")
    @ApiOperation(value = "查询宠物类别")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "specie", dataType = "Integer", paramType = "query", value = "宠物类别", required = true),
    })
    public Result getBreed(Integer specie) {
        Result result = new Result();
        result.setData(breedService.getBreed(specie));
        result.success("查询成功");
        return result;
    }

    @GetMapping("/list")
    @ApiOperation(value = "查询所有品种")
    public Result list() {
        Result result = new Result();
        result.setData(breedService.getList());
        result.success("查询成功");
        return result;
    }

    @GetMapping("/getList")
    @ApiOperation(value = "分页获取宠物列表")

    public Result<Page<Breed>> getList(Integer pageNum, Integer pageSize, Integer specie, Integer status) {
        Result<Page<Breed>> result = breedService.page(pageNum, pageSize, specie, status);
        result.success("商品:列表请求成功");
        return result;
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除品种")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", dataType = "String", paramType = "query", value = "品种id", required = true),
    })
    public Result delete(String ids) {
        Result result = new Result();
        List<String> idList = Arrays.asList(ids.split(","));
        if (breedService.removeByIds(idList)) {
            result.success("删除成功");
        } else {
            result.fail("删除失败");
        }
        return result;
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增品种")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "breed", dataType = "Breed", paramType = "body", value = "品种信息", required = true),
    })
    public Result add(@RequestBody Breed breed) {
        Result result = new Result();
        if (breedService.save(breed)) {
            result.success("品种:添加成功");
        } else {
            result.fail("品种:添加失败");
        }
        return result;
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新品种")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "breed", dataType = "Breed", paramType = "body", value = "品种信息", required = true),
    })
    public Result update(@RequestBody Breed breed) {
        Result result = new Result();
        if (breedService.updateById(breed)) {
            result.success("品种:更新成功");
        } else {
            result.fail("品种:更新失败");
        }
        return result;
    }

}
