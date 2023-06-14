package com.xmut.pet.controller;

import com.xmut.pet.Utils.DateTool;
import com.xmut.pet.Utils.JwtUtil;
import com.xmut.pet.entity.Result;
import com.xmut.pet.entity.User;
import com.xmut.pet.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
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
    @ApiOperation(value="用户注册")
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
    @ApiOperation(value="更新用户信息")
    public Result update(@RequestBody User user) {
        Result result = new Result<>();
        if (userService.updateById(user)) {
            result.success("更新成功");
        } else {
            result.fail("更新失败");
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
        result.setData(userService.getById(id));
        return result;
    }

}
