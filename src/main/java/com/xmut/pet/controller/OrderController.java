package com.xmut.pet.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmut.pet.entity.Order;
import com.xmut.pet.entity.OrderItem;
import com.xmut.pet.entity.Result;
import com.xmut.pet.service.GoodsService;
import com.xmut.pet.service.OrderItemService;
import com.xmut.pet.service.OrderService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
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
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private GoodsService goodsService;

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

    @RequestMapping(method = RequestMethod.POST, value = "/getSalesChart")
    @ApiOperation(value = "返回宠物和商品销售的数据")
    public Result<Map<String, List<String>>> getSalesChart(String begin, String end) {
        Result<Map<String, List<String>>> result = new Result<>();
        result.setData(orderService.getSalesChart(begin, end));
        result.success("返回宠物和商品销售的数据");
        return result;
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", dataType = "Integer", paramType = "query", value = "订单id列表", required = true),
    })
    public Result delete(String ids) {
        Result result = new Result<>();
        List<String> idList = Arrays.asList(ids.split(","));
        for (String id : idList) {
            Order order = orderService.getById(id);
            List<OrderItem> orderItems = orderItemService.getOrderItemsByOrderId(Integer.valueOf(id));
            for (OrderItem orderItem : orderItems) {
                orderItem.setNum(0);
                goodsService.reduceStock(orderItem);
                orderItemService.removeById(orderItem);
            }
        }
        if (orderService.removeByIds(idList)) {
            result.success("删除成功");
        } else {
            result.fail("删除失败");
        }
        return result;
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", dataType = "User", paramType = "body", value = "用户信息", required = true),
    })
    public Result update(@RequestBody Order order) {
        Result result = new Result<>();
        if (orderService.updateById(order)) {
            result.success("更新成功");
        } else {
            result.fail("更新失败");
        }
        return result;
    }
}
