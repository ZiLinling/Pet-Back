package com.xmut.pet.controller;

import com.xmut.pet.entity.Chat;
import com.xmut.pet.entity.Result;
import com.xmut.pet.service.ChatService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Ji
 * @since 2023-07-23 04:43:57
 */
@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    private ChatService chatService;


    @ApiOperation(value = "新增聊天记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "chat", dataType = "Chat", paramType = "body", value = "聊天记录", required = true),
    })
    @RequestMapping(method = RequestMethod.POST, value = "/save")
    public Result save(@RequestBody Chat chat) {
        Result result = new Result();
        result.setData(chatService.add(chat));
        result.success("聊天记录添加成功");
        System.out.println(result);
        return result;
    }


    @ApiOperation(value = "展示聊天记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "recipient", dataType = "Integer", paramType = "integer", value = "接收者的id", required = true),
    })
    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public Result list(Integer recipient) {
        Result result = new Result();
        result.setData(chatService.list(recipient));
        result.success("查询成功");
        return result;
    }

    @PostMapping("/remove")
    @ApiOperation(value = "撤回聊天记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", dataType = "Integer", paramType = "integer", value = "聊天记录id", required = true),
    })
    public Result remove(Integer id) {
        Result result = new Result();
        result.setData(chatService.remove(id));
        result.success("了解记录添加成功");
        return result;
    }
}
