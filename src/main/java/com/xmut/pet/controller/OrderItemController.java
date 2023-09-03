package com.xmut.pet.controller;

import com.xmut.pet.VO.OrderItemVO;
import com.xmut.pet.entity.Order;
import com.xmut.pet.entity.OrderItem;
import com.xmut.pet.entity.Result;
import com.xmut.pet.service.OrderItemService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 前端控制器
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
    public Result<OrderItem> generateOrderItem(@RequestBody OrderItem orderItem) {
        Result<OrderItem> result = new Result<>();
//        orderItem.setStatus(1);
//        orderItem.setCreateTime(DateTool.getCurrTime());
        if (orderItemService.generate(orderItem)) {

            result.success("订单信息生成成功");
        } else {
            result.fail("订单信息生成失败");
        }
        return result;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    @ApiOperation(value = "罗列订单")
    public Result<List<Order>> listOrder() {
        Result<List<Order>> result = new Result<>();
        result.setData(orderItemService.listOrderItem());
        return result;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/toPay")
    @ApiOperation(value = "支付，修改订单状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", dataType = "Stirng", paramType = "String", value = "订单项目id的集合", required = true),
    })
    public Result<OrderItem> toPay(String ids) {
        Result<OrderItem> result = new Result<>();

        List<Integer> idList = new ArrayList<>();

        String[] idArray = ids.split(",");
        for (String id : idArray) {
            try {
                int parsedId = Integer.parseInt(id);
                idList.add(parsedId);
            } catch (NumberFormatException e) {
                // 处理无法解析为整数的情况
                System.out.println("无法解析的整数: " + id);
            }
        }
        orderItemService.toPay(idList);
        result.success("支付成功，等待商家发货");
        return result;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/rejected")
    @ApiOperation(value = "退货，修改订单状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", dataType = "Stirng", paramType = "String", value = "订单项目id的集合", required = true),
    })
    public Result<OrderItem> rejected(String ids) {
        Result<OrderItem> result = new Result<>();

        List<Integer> idList = new ArrayList<>();

        String[] idArray = ids.split(",");
        for (String id : idArray) {
            try {
                int parsedId = Integer.parseInt(id);
                idList.add(parsedId);
            } catch (NumberFormatException e) {
                // 处理无法解析为整数的情况
                System.out.println("无法解析的整数: " + id);
            }
        }
        orderItemService.rejected(idList);
        result.success("退货申请提交，等待商家确认");
        return result;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/directPayment")
    @ApiOperation(value = "订单生成时直接付款")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", dataType = "integer", paramType = "integer", value = "订单id", required = true),
    })
    public Result<OrderItem> directPayment(Integer orderId) {
        Result<OrderItem> result = new Result<>();

        orderItemService.directPayment(orderId);
        result.success("支付成功，等待商家发货");
        return result;
    }


    @RequestMapping(method = RequestMethod.POST, value = "/cancelOrderItem")
    @ApiOperation(value = "取消订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", dataType = "Integer", paramType = "Integer", value = "订单项目id", required = true),
    })
    public Result<OrderItem> cancelOrderItem(String ids) {
        Result<OrderItem> result = new Result<>();
        List<Integer> idList = new ArrayList<>();

        String[] idArray = ids.split(",");
        for (String id : idArray) {
            try {
                int parsedId = Integer.parseInt(id);
                idList.add(parsedId);
            } catch (NumberFormatException e) {
                // 处理无法解析为整数的情况
                System.out.println("无法解析的整数: " + id);
            }
        }
        orderItemService.cancelOrderItem(idList);
        result.success("取消成功");
        return result;
    }

    @GetMapping("/getList")
//    @ApiOperation(value = "分页获取用户列表")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "pageNum", dataType = "Integer", paramType = "query", value = "页码", required = true),
//            @ApiImplicitParam(name = "pageSize", dataType = "Integer", paramType = "query", value = "页数", required = true),
//            @ApiImplicitParam(name = "account", dataType = "String", paramType = "query", value = "账号", required = false),
//            @ApiImplicitParam(name = "name", dataType = "String", paramType = "query", value = "用户名", required = false),
//    })
    public Result<List<OrderItemVO>> getList(Integer orderId) {
        Result<List<OrderItemVO>> result = new Result<>();
        result.setData(orderItemService.getListByOrderId(orderId));

        result.success("订单详情:列表请求成功");
        return result;
    }

    @PostMapping("/delivery")
//    @ApiOperation(value = "分页获取用户列表")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "pageNum", dataType = "Integer", paramType = "query", value = "页码", required = true),
//            @ApiImplicitParam(name = "pageSize", dataType = "Integer", paramType = "query", value = "页数", required = true),
//            @ApiImplicitParam(name = "account", dataType = "String", paramType = "query", value = "账号", required = false),
//            @ApiImplicitParam(name = "name", dataType = "String", paramType = "query", value = "用户名", required = false),
//    })
    public Result<OrderItem> delivery(Integer id) {
        Result<OrderItem> result = new Result<>();
        orderItemService.delivery(id);
        result.success("发货成功");
        return result;
    }

    //    @ApiOperation(value = "分页获取用户列表")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "pageNum", dataType = "Integer", paramType = "query", value = "页码", required = true),
//            @ApiImplicitParam(name = "pageSize", dataType = "Integer", paramType = "query", value = "页数", required = true),
//            @ApiImplicitParam(name = "account", dataType = "String", paramType = "query", value = "账号", required = false),
//            @ApiImplicitParam(name = "name", dataType = "String", paramType = "query", value = "用户名", required = false),
//    })
    @RequestMapping(method = RequestMethod.POST, value = "/confirm")
    public Result<OrderItem> confirm(String ids) {
        Result<OrderItem> result = new Result<>();
        List<Integer> idList = new ArrayList<>();

        String[] idArray = ids.split(",");
        for (String id : idArray) {
            try {
                int parsedId = Integer.parseInt(id);
                idList.add(parsedId);
            } catch (NumberFormatException e) {
                // 处理无法解析为整数的情况
                System.out.println("无法解析的整数: " + id);
            }
        }
        orderItemService.confirm(idList);
        result.success("发货成功");

        return result;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/toComment")
    @ApiOperation(value = "评论，修改订单状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", dataType = "Stirng", paramType = "String", value = "订单项目id的集合", required = true),
    })
    public Result<OrderItem> toComment(String ids) {
        Result<OrderItem> result = new Result<>();

        List<Integer> idList = new ArrayList<>();

        String[] idArray = ids.split(",");
        for (String id : idArray) {
            try {
                int parsedId = Integer.parseInt(id);
                idList.add(parsedId);
            } catch (NumberFormatException e) {
                // 处理无法解析为整数的情况
                System.out.println("无法解析的整数: " + id);
            }
        }
        orderItemService.toComment(idList);
        result.success("支付成功，等待商家发货");
        return result;
    }


}
