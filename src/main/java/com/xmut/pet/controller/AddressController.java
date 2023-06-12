package com.xmut.pet.controller;

import com.xmut.pet.entity.Address;
import com.xmut.pet.entity.Result;
import com.xmut.pet.service.AddressService;
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
    public Result<List<Address>> getListByUserId(Integer userId)
    {
        Result<List<Address>> result = new Result();
        List<Address> addressList=new ArrayList<>();
        result.success("地址:列表请求成功");
        result.setData(addressList);
        System.out.println(result.getStatusCode());
        return result;
    }



}
