package com.xmut.pet.controller;

import com.xmut.pet.entity.Result;
import com.xmut.pet.service.BreedService;
import com.xmut.pet.service.PetService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

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
            @ApiImplicitParam(name = "specie", dataType = "Integer", paramType = "query",value = "宠物类别", required = true),
    })
    public Result getBreed(Integer specie) {
        Result result = new Result();
        result.setData(breedService.getBreed(specie));
        result.success("查询成功");
        return result;
    }

}
