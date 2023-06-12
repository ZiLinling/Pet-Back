package com.xmut.pet.controller;

import com.xmut.pet.entity.Pet;
import com.xmut.pet.entity.Result;
import com.xmut.pet.service.PetService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Zi
 * @since 2023-06-08 10:00:05
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private PetService petService;


    //查询宠物信息
    @GetMapping("/getById")
    @ApiOperation(value = "获取宠物信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", dataType = "Integer", paramType = "query",value = "宠物id", required = true),
    })
    public Result<Pet> getById(Integer id) {
        Result<Pet> result = new Result<>();
        result.success("宠物:获取成功");
        result.setData(petService.getById(id));
        return result;
    }

    //更新宠物信息
    @PostMapping("/update")
    @ApiOperation(value = "更新宠物信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pet", dataType = "Pet", paramType = "Body",value = "宠物信息", required = true),
    })
    public Result update(@RequestBody Pet pet) {
        Result result = new Result();
        if (petService.updateById(pet)) {
            result.success("宠物:更新成功");
        } else {
            result.fail("宠物:更新失败");
        }
        return result;
    }

    //新增宠物
    @PostMapping("/add")
    @ApiOperation(value = "新增宠物")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pet", dataType = "Pet", paramType = "Body",value = "宠物信息", required = true),
    })
    public Result add(@RequestBody Pet pet) {
        Result result = new Result();
        if (petService.save(pet)) {
            result.success("宠物:添加成功");
        } else {
            result.fail("宠物:添加失败");
        }
        return result;
    }

    //删除宠物
    @PostMapping("/delete")
    @ApiOperation(value = "删除宠物")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", dataType = "Integer", paramType = "query",value = "宠物id", required = true),
    })
    public Result delete(Integer id) {
        Result result = new Result();
        if (petService.removeById(id)) {
            result.success("宠物:删除成功");
        } else {
            result.fail("宠物:删除失败");
        }
        return result;
    }



}
