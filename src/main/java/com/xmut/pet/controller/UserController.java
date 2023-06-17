package com.xmut.pet.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmut.pet.Utils.DateTool;
import com.xmut.pet.Utils.JwtUtil;
import com.xmut.pet.entity.Result;
import com.xmut.pet.entity.User;
import com.xmut.pet.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Zi
 * @since 2023-06-08 10:00:58
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest request;

    @PostMapping("/login")
    @ApiOperation(value = "用户登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", dataType = "User", paramType = "body", value = "用户信息", required = true),
    })
    public Result<String> login(@RequestBody User user) {
        Result<String> result = new Result<>();
        user = userService.login(user);
        if (user == null) {
            result.fail("账号或密码错误,请重新登录");
        } else {
            result.setData(JwtUtil.generateToken(user));
            result.success("登录成功");
        }
        return result;
    }

    @PostMapping("/register")
    @ApiOperation(value = "用户注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", dataType = "User", paramType = "body", value = "用户信息", required = true),
    })
    public Result register(@RequestBody User user) {
        Result result = new Result<>();
        user.setStatus(1);
        user.setCreateTime(DateTool.getCurrTime());
        if (userService.register(user)) {
            result.setData(JwtUtil.generateToken(user));
            result.success("注册成功,自动登录");
        } else {
            result.fail("注册失败,用户名重复");
        }
        return result;
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", dataType = "User", paramType = "body", value = "用户信息", required = true),
    })
    public Result update(@RequestBody User user) {
        Result result = new Result<>();
        if (userService.updateById(user)) {
            result.success("更新成功");
        } else {
            result.fail("更新失败");
        }
        return result;
    }

    @PostMapping("/add")
    @ApiOperation(value = "添加用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", dataType = "User", paramType = "body", value = "用户信息", required = true),
    })
    public Result add(@RequestBody User user) {
        Result result = new Result<>();
        user.setCreateTime(DateTool.getCurrTime());
        if (userService.register(user)) {
            result.success("添加成功");
        } else {
            result.fail("添加失败");
        }
        return result;
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", dataType = "Integer", paramType = "query", value = "用户id列表", required = true),
    })
    public Result delete(String ids) {
        Result result = new Result<>();
        List<String> idList = Arrays.asList(ids.split(","));
        if (userService.removeByIds(idList)) {
            result.success("删除成功");
        } else {
            result.fail("删除失败");
        }
        return result;
    }

    @GetMapping("/getUser")
    @ApiOperation(value = "获取用户信息")
    public Result<User> getUser() {
        Integer id = JwtUtil.getUserId(request.getHeader("token"));
        Result<User> result = new Result<>();
        User user = userService.getById(id);
        user.setPassword(null);
        result.success("获取成功");
        result.setData(user);
        return result;
    }

    @GetMapping("/getList")
    @ApiOperation(value = "分页获取用户列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", dataType = "Integer", paramType = "query", value = "页码", required = true),
            @ApiImplicitParam(name = "pageSize", dataType = "Integer", paramType = "query", value = "页数", required = true),
            @ApiImplicitParam(name = "account", dataType = "String", paramType = "query", value = "账号", required = false),
            @ApiImplicitParam(name = "name", dataType = "String", paramType = "query", value = "用户名", required = false),
    })
    public Result<Page<User>> getList(Integer pageNum, Integer pageSize, String account, String name) {
        Result<Page<User>> result = userService.page(pageNum, pageSize, account, name);
        result.success("商品:列表请求成功");
        return result;
    }
}
