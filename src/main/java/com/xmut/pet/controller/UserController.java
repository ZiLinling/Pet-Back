package com.xmut.pet.controller;

import com.xmut.pet.Utils.DateTool;
import com.xmut.pet.Utils.JwtUtil;
import com.xmut.pet.entity.Result;
import com.xmut.pet.entity.User;
import com.xmut.pet.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

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
    @ApiOperation(value="用户登录")
    public Result login(@RequestBody User user) {
        Result result = new Result<>();
        user = userService.login(user);
        if (user == null) {
            result.fail("账号或密码错误,请重新登录");
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("userid", user.getId());
            map.put("token", JwtUtil.generateToken(user));
            result.success("登录成功");
            result.setData(map);
        }
        return result;
    }

    @PostMapping("/register")
    @ApiOperation(value="用户注册")
    public Result register(@RequestBody User user) {
        Result result = new Result<>();
        user.setCreateTime(DateTool.getCurrTime());
        if (userService.register(user)) {
            Map<String, Object> map = new HashMap<>();
            map.put("userid", user.getId());
            map.put("token", JwtUtil.generateToken(user));
            result.success("注册成功,自动登录");
            result.setData(map);
        } else {
            result.fail("注册失败,用户名重复");
        }
        return result;
    }

}
