package com.xmut.pet.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmut.pet.entity.Order;
import com.xmut.pet.entity.Result;
import com.xmut.pet.service.OrderService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Zi
 * @since 2023-06-06 09:27:37
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/generate")
    @ApiOperation(value = "生成订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "order", dataType = "Order", paramType = "body", value = "订单信息", required = true),
    })
    public Result<Integer> generateOrder(@RequestBody Order order) {
        Result<Integer> result = new Result<>();
        Integer orderId = orderService.generate(order);
        if (orderId != null) {
            result.setData(orderId);
            result.success("订单生成成功");
        } else {
            result.fail("订单生成失败");
        }
        return result;
    }

    @GetMapping("/getList")
    @ApiOperation(value = "分页获取订单列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", dataType = "Integer", paramType = "query", value = "页码", required = true),
            @ApiImplicitParam(name = "pageSize", dataType = "Integer", paramType = "query", value = "页数", required = true),
            @ApiImplicitParam(name = "account", dataType = "String", paramType = "query", value = "账号", required = false),
            @ApiImplicitParam(name = "name", dataType = "String", paramType = "query", value = "用户名", required = false),
    })
    public Result<Page<Order>> getList(Integer pageNum, Integer pageSize, String account, String name) {
        Result<Page<Order>> result = orderService.page(pageNum, pageSize, account, name);
        result.success("商品:列表请求成功");
        return result;
    }
}
