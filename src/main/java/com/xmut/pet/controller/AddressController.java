package com.xmut.pet.controller;

import com.xmut.pet.entity.Result;
import com.xmut.pet.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Zi
 * @since 2023-06-08 09:55:47
 */
@Controller
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;


    @GetMapping("/list")
    public Result getListByUserId(Integer userId)
    {
        Result result = new Result();
        result.success("地址:列表请求成功");
        result.setData(addressService.getListByUserId(userId));
        return result;

    }



}
