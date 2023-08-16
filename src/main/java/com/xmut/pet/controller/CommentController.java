package com.xmut.pet.controller;

import com.xmut.pet.entity.Comment;
import com.xmut.pet.entity.Result;
import com.xmut.pet.service.CommentService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping("/add")
    @ApiOperation(value = "新增商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "comment", dataType = "Comment", paramType = "body", value = "评论信息", required = true),
    })
    public Result<Comment> add(@RequestBody Comment comment) {
        Result<Comment> result = new Result<>();
        if (commentService.add(comment)) {
            result.success("评论:保存成功");
        } else {
            result.fail("评论:保存失败");
        }
        return result;
    }

    //根据商品id获取评论
    @GetMapping("/getListByGoodsId")
    @ApiOperation(value = "获取商品评论列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "goodsId", dataType = "Integer", paramType = "query", value = "商品id", required = true),
    })
    public Result<List<Comment>> getListByGoodsId(Integer goodsId) {
        Result<List<Comment>> result = new Result<>();
        List<Comment> commentList = commentService.getListByGoodsId(goodsId);
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
