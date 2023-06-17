package com.xmut.pet.controller;

import com.xmut.pet.Utils.JwtUtil;
import com.xmut.pet.entity.Address;
import com.xmut.pet.entity.Result;
import com.xmut.pet.service.AddressService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
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
    @Autowired
    private HttpServletRequest request;

    @GetMapping("/list")
    @ApiOperation(value="获取用户地址列表")
    public Result<List<Address>> getListByUserId()
    {
        Integer id = JwtUtil.getUserId(request.getHeader("token"));
        Result<List<Address>> result = new Result();
        result.success("地址:列表请求成功");
        result.setData(addressService.getListByUserId(id));
        return result;
    }

    @PostMapping("/add")
    @ApiOperation(value="添加用户地址")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "address", dataType = "Address", paramType = "body",value = "地址信息", required = true),
    })
    public Result add(@RequestBody Address address)
    {
        Result result = new Result();
        Integer id = JwtUtil.getUserId(request.getHeader("token"));
        if(address.getIsDefault())
        {
            List<Address> addressList=addressService.getListByUserId(id);
            for (Address item : addressList)
            {
                item.setIsDefault(false);
                addressService.updateById(item);
            }
        }
        address.setUserId(id);
        if (addressService.save(address)) {
            result.success("地址:添加成功");
        } else {
            result.fail("地址:添加失败");
        }
        return result;
    }

    @PostMapping("/update")
    @ApiOperation(value="更新用户地址")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "address", dataType = "Address", paramType = "body",value = "地址信息", required = true),
    })
    public Result update(@RequestBody Address address)
    {
        Result result = new Result();
        if(address.getIsDefault())
        {
            Integer id = JwtUtil.getUserId(request.getHeader("token"));
            List<Address> addressList=addressService.getListByUserId(id);
            for (Address item : addressList)
            {
                item.setIsDefault(false);
                addressService.updateById(item);
            }
        }
        if (addressService.updateById(address)) {
            result.success("地址:更新成功");
        } else {
            result.fail("地址:更新失败");
        }
        return result;
    }

    @PostMapping ("/delete")
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
