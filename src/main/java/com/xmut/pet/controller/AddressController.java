package com.xmut.pet.controller;

import com.xmut.pet.entity.Address;
import com.xmut.pet.entity.Result;
import com.xmut.pet.service.AddressService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Zi
 * @since 2023-06-08 09:55:47
 */
@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;


    @GetMapping("/list")
    @ApiOperation(value="获取用户地址列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", dataType = "Integer", paramType = "query",value = "用户id", required = true),
    })
    public Result<List<Address>> getListByUserId(Integer userId)
    {
        Result<List<Address>> result = new Result();
        List<Address> addressList=new ArrayList<>();
        result.success("地址:列表请求成功");
        result.setData(addressList);
        System.out.println(result.getStatusCode());
        return result;
    }

    @GetMapping("/add")
    @ApiOperation(value="添加用户地址")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "address", dataType = "Address", paramType = "body",value = "地址信息", required = true),
    })
    public Result add(Address address)
    {
        Result result = new Result();
        if (addressService.save(address)) {
            result.success("地址:添加成功");
        } else {
            result.fail("地址:添加失败");
        }
        return result;
    }

    @GetMapping("/update")
    @ApiOperation(value="更新用户地址")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "address", dataType = "Address", paramType = "body",value = "地址信息", required = true),
    })
    public Result update(Address address)
    {
        Result result = new Result();
        if (addressService.updateById(address)) {
            result.success("地址:更新成功");
        } else {
            result.fail("地址:更新失败");
        }
        return result;
    }

    @GetMapping("/delete")
    @ApiOperation(value="删除用户地址")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", dataType = "Integer", paramType = "query",value = "地址id", required = true),
    })
    public Result delete(Integer id)
    {
        Result result = new Result();
        if (addressService.removeById(id)) {
            result.success("地址:删除成功");
        } else {
            result.fail("地址:删除失败");
        }
        return result;
    }

}
