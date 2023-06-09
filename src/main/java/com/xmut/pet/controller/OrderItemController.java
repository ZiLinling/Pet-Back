package com.xmut.pet.controller;

import com.xmut.pet.entity.OrderItem;
import com.xmut.pet.entity.Result;
import com.xmut.pet.service.OrderItemService;
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
 * @since 2023-06-08 09:59:14
 */
@RestController
@RequestMapping("/orderItem")
public class OrderItemController {
    @Autowired
    private OrderItemService orderItemService;

    @PostMapping("/generate")
    @ApiOperation(value = "生成订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderItem", dataType = "OrderItem", paramType = "body", value = "订单物品", required = true),
    })
    public Result generateOrderItem(@RequestBody OrderItem orderItem) {
        Result result = new Result<>();
//        orderItem.setStatus(1);
//        orderItem.setCreateTime(DateTool.getCurrTime());
        if (orderItemService.generate(orderItem)) {

            result.success("订单信息生成成功");
        } else {
            result.fail("订单信息生成失败");
        }
        return result;
    }
}
