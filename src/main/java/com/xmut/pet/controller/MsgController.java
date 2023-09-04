package com.xmut.pet.controller;

import com.xmut.pet.entity.Msg;
import com.xmut.pet.entity.Result;
import com.xmut.pet.service.MsgService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Ji
 * @since 2023-07-30 12:33:55
 */
@RestController
@RequestMapping("/msg")
public class MsgController {
    @Autowired
    private MsgService msgService;


    @ApiOperation(value = "展示聊天列表")
    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public Result list() {
        Result result = new Result();

        result.setData(msgService.listMsg());
        result.success("查询成功");
        return result;
    }

    @ApiOperation(value = "增加聊天列表")
    @RequestMapping(method = RequestMethod.POST, value = "/save")
    public Result save(@RequestBody Msg msg) {
        Result result = new Result();

        if (msgService.add(msg)) {
            result.success("增加成功");
        } else {
            result.fail("已经有了，无需重复添加");
        }

        return result;
    }
}
