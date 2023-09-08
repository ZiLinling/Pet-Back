package com.xmut.pet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xmut.pet.Utils.JwtUtil;
import com.xmut.pet.VO.OrderItemVO;
import com.xmut.pet.VO.StoreVO;
import com.xmut.pet.entity.*;
import com.xmut.pet.mapper.OrderItemMapper;
import com.xmut.pet.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Zi
 * @since 2023-06-08 09:59:14
 */
@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements OrderItemService {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private OrderService orderService;
    @Autowired
    private StoreService storeService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private UserService userService;
    @Autowired
    private PetService petService;

//    @Override
//    public List<OrderItem> getListByStoreId(Integer storeId) {
//        QueryWrapper<OrderItem> queryWrapper = new QueryWrapper();
//        queryWrapper.eq("store_id", storeId);
//        return this.list(queryWrapper);
//    }

    @Override
    public List<Order> listOrderItem() {
        Integer userId = JwtUtil.getUserId(request.getHeader("token"));
        List<Order> orders = orderService.getListByUserId(userId);
        for (Order order : orders) {
            Store old_store = new Store();
            QueryWrapper<OrderItem> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("order_id", order.getId());
            List<OrderItem> orderItems = this.list(queryWrapper);
            List<OrderItem> itemsInStore = new ArrayList<>();
            List<Store> stores = new ArrayList<>();
            for (OrderItem orderItem : orderItems) {
                Store new_store;
                Map<String, Object> others;
                orderItem.put("status", orderItem.getStatus());

                if (orderItem.getType() == 1) {
                    others = this.baseMapper.getOthersByGoodsId(orderItem.getItemId());
                    orderItem.put("storeName", storeService.getStoreByGoodsId(orderItem.getItemId()).getName());
                    orderItem.put("name", others.get("name"));
                    orderItem.put("img", others.get("img"));

                    new_store = storeService.getStoreByGoodsId(orderItem.getItemId());
                    new_store.put("order_status", orderItem.getStatus());
                } else {
                    others = this.baseMapper.getOthersByPetId(orderItem.getItemId());
                    orderItem.put("storeName", storeService.getStoreByPetId(orderItem.getItemId()).getName());
                    orderItem.put("name", others.get("name"));
                    orderItem.put("img", others.get("img"));

                    new_store = storeService.getStoreByPetId(orderItem.getItemId());
                    new_store.put("order_status", orderItem.getStatus());
                }
                //如果长度为1就直接插入即可
                if (orderItems.size() == 1) {
                    new_store.put("orderItems", orderItems);
                    new_store.put("num", 1);
                    new_store.put("price", orderItem.getPrice());
                    new_store.put("time", order.getCreateTime());
                    stores.add(new_store);
                    break;
                }
                if (new_store.getId() != old_store.getId()) {
                    // 将订单项存储到旧商店对象中
                    if (old_store.getId() != null) {
                        // 将订单项存储到旧商店对象中
                        Integer price = 0;
                        for (OrderItem item : itemsInStore) {
                            price += item.getPrice();
                        }
                        old_store.put("orderItems", itemsInStore);
                        old_store.put("num", itemsInStore.size());
                        old_store.put("price", price);
                        old_store.put("time", order.getCreateTime());
                        stores.add(old_store);
                    }
                    // 创建新的商店对象并将旧商店对象存储到订单对象中
                    old_store = new_store;
                    itemsInStore = new ArrayList<>();
                }
                itemsInStore.add(orderItem);
            }
            // 将最后一个商店对象添加到商店列表中
            if (old_store.getId() != null) {
                Integer price = 0;
                for (OrderItem item : itemsInStore) {
                    price += item.getPrice();
                }
                old_store.put("orderItems", itemsInStore);
                old_store.put("num", itemsInStore.size());
                old_store.put("price", price);
                old_store.put("time", order.getCreateTime());
                stores.add(old_store);
            }
            order.put("stores", stores);
        }
        return orders;
    }


    public OrderItemVO getItemVO(OrderItem item) {
        OrderItemVO orderItemVO = new OrderItemVO(item);
//        //将status转换为英文的状态
//        String statusString = STATUS_MAP.get(item.getStatus());
//        orderItemVO.setStatus(statusString);
        Map<String, Object> others;
        if (item.getType() == 1) {
            others = this.baseMapper.getOthersByGoodsId(orderItemVO.getItemId());
        } else {
            others = this.baseMapper.getOthersByPetId(orderItemVO.getItemId());
        }
        orderItemVO.setName((String) others.get("name"));
        orderItemVO.setImg((String) others.get("img"));
        return orderItemVO;
    }

    @Override
    public boolean generate(OrderItem orderItem) {
        if (orderItem.getStatus() == null) {
            orderItem.setStatus(1);
        }
        if (orderItem.getType() == 0) {
            Pet pet = petService.getById(orderItem.getItemId());
            pet.setStatus(0);
            petService.updateById(pet);
            //如果是宠物就不用删减库存,直接给他状态设置为0，为0不显示
            return true;
        }
        //订单生成之后减少库存
        goodsService.reduceStock(orderItem);
        this.save(orderItem);
        return true;
    }

    @Override
    public Integer getCost(Integer userId) {
        Integer cost = 0;
        List<Order> orders = orderService.getListByUserId(userId);
        for (Order order : orders) {
            QueryWrapper<OrderItem> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("order_id", order.getId());
            List<OrderItem> orderItems = this.list(queryWrapper);
            for (OrderItem orderItem : orderItems) {
                if (orderItem.getType() == 1 && orderItem.getStatus() < 7 && orderItem.getStatus() >= 2) {
                    cost += orderItem.getPrice() * orderItem.getNum();
                }
            }
        }
        System.out.println("你总共消费了：" + cost + "元");
        return cost;
    }

    @Override
    public Integer delivery(Integer id) {
        OrderItem item = this.getById(id);
        if (item.getStatus() == 2) {
            item.setStatus(3);
            this.updateById(item);
        }
        return item.getStatus();
    }

    @Override
    public boolean toComment(List<Integer> idList) {
        for (Integer id : idList) {
            OrderItem orderItem = this.getById(id);
            orderItem.setStatus(6);
            this.updateById(orderItem);
        }
        return true;
    }

    @Override
    public boolean confirm(List<Integer> idList) {
        for (Integer id : idList) {
            OrderItem orderItem = this.getById(id);
            orderItem.setStatus(4);
            this.updateById(orderItem);
        }
        return true;
    }

    @Override
    public boolean toPay(List<Integer> idList) {
        for (Integer id : idList) {
            OrderItem orderItem = this.getById(id);
            orderItem.setStatus(2);
            this.updateById(orderItem);
        }
        return true;
    }

    @Override
    public boolean directPayment(Integer orderId) {
        QueryWrapper<OrderItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_id", orderId);
        List<OrderItem> orderItems = this.list(queryWrapper);
        for (OrderItem orderItem : orderItems) {
            orderItem.setStatus(2);
            this.updateById(orderItem);
        }
        return true;
    }

    @Override
    public boolean rejected(List<Integer> idList) {
        for (Integer id : idList) {
            OrderItem orderItem = this.getById(id);
            orderItem.setStatus(5);
            this.updateById(orderItem);
        }

        return true;
    }

    @Override
    public boolean cancelOrderItem(List<Integer> idList) {
        for (Integer id : idList) {
            OrderItem orderItem = this.getById(id);
            if (orderItem.getType() == 0) {
                Pet pet = petService.getById(orderItem.getItemId());
                pet.setStatus(1);
                petService.updateById(pet);
                //如果是宠物就不用增加库存,直接给他状态设置为1，继续售卖
                continue;
            }
            //取消订单就把库存加回来
            Goods goods = goodsService.getById(orderItem.getItemId());
            goods.setStock(goods.getStock() + orderItem.getNum());
            if (goods.getStatus() == 0) {
                goods.setStatus(1);
            }
            goodsService.updateById(goods);
            //item被取消之后，去order中总价扣除一下
            Order order = orderService.getById(orderItem.getOrderId());
            BigDecimal unit_price = BigDecimal.valueOf(orderItem.getPrice());
            BigDecimal result = order.getPrice().subtract(unit_price);

            order.setPrice(result);
            orderService.updateById(order);

            orderItem.setStatus(7);
            this.updateById(orderItem);
        }
        return true;
    }

    @Override
    public boolean getSum(StoreVO store, OrderItem item) {
        // 获取当前商店的价格
        BigDecimal storePrice = BigDecimal.ZERO;
        if (store.getPrice() != null) {
            storePrice = store.getPrice();
        }

        // 获取商品的价格和数量
        BigDecimal itemPrice = BigDecimal.valueOf(item.getPrice());
        BigDecimal itemNum = BigDecimal.valueOf(item.getNum());

        // 计算总价格
        BigDecimal totalPrice = storePrice.add(itemPrice.multiply(itemNum));

        // 将总价格设置为商店的价格
        store.setPrice(totalPrice);
        return true;
    }

    @Override
    public List<OrderItemVO> getListByOrderId(Integer orderId) {
        QueryWrapper<OrderItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_id", orderId);
        List<OrderItem> orderItems = this.list(queryWrapper);
        List<OrderItemVO> orderItemVOList = new ArrayList<>();
        for (OrderItem orderItem : orderItems) {
            OrderItemVO orderItemVO = new OrderItemVO(orderItem);
            Map<String, Object> others;
            if (orderItem.getType() == 1) {
                others = this.baseMapper.getOthersByGoodsId(orderItemVO.getItemId());
                orderItemVO.put("storeName", storeService.getStoreByGoodsId(orderItem.getItemId()).getName());
            } else {
                others = this.baseMapper.getOthersByPetId(orderItemVO.getItemId());
                orderItemVO.put("storeName", storeService.getStoreByPetId(orderItem.getItemId()).getName());
            }
            orderItemVO.put("status", orderItem.getStatus());
            orderItemVO.setName((String) others.get("name"));
            orderItemVO.setImg((String) others.get("img"));
            orderItemVOList.add(orderItemVO);
        }

        return orderItemVOList;
    }

    @Override
    public List<OrderItem> getOrderItemsByOrderId(Integer orderId) {
        QueryWrapper<OrderItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_id", orderId);
        return this.list(queryWrapper);
    }

}
