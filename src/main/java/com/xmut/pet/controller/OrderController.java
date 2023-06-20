package com.xmut.pet.controller;

import com.xmut.pet.entity.Order;
import com.xmut.pet.entity.Result;
import com.xmut.pet.service.OrderService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Result generateOrder(@RequestBody Order order) {
            Result result = new Result<>();
        Integer orderId = orderService.generate(order);
        if (orderId != null) {
            result.setData(orderId);
            result.success("订单生成成功");
        } else {
            result.fail("订单生成失败");
        }
        return result;
    }
}
