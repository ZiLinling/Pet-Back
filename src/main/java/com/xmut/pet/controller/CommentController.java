package com.xmut.pet.controller;

import com.xmut.pet.entity.Comment;
import com.xmut.pet.entity.Result;
import com.xmut.pet.service.CommentService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Zi
 * @since 2023-06-08 09:57:45
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    //根据商品id获取评论
    @GetMapping("/getListByGoodsId")
    @ApiOperation(value="获取商品评论列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "goodsId", dataType = "Integer", paramType = "query",value = "商品id", required = true),
    })
    public Result<List<Comment>> getListByGoodsId(Integer goodsId)
    {
        Result<List<Comment>> result = new Result<>();
        List<Comment> commentList=commentService.getListByGoodsId(goodsId);
        result.success("评论:列表请求成功");
        result.setData(commentList);
        return result;
    }


    //根据用户id获取评论
    @GetMapping("/getListByUserId")
    @ApiOperation(value="获取用户评论列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", dataType = "Integer", paramType = "query",value = "用户id", required = true),
    })
    public Result<List<Comment>> getListByUserId(Integer userId)
    {
        Result<List<Comment>> result = new Result<>();
        List<Comment> commentList=commentService.getListByUserId(userId);
        result.success("评论:列表请求成功");
        result.setData(commentList);
        return result;
    }
}
