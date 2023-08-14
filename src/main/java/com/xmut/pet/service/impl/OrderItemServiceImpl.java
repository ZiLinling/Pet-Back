package com.xmut.pet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xmut.pet.Utils.JwtUtil;
import com.xmut.pet.VO.OrderItemVO;
import com.xmut.pet.VO.StoreVO;
import com.xmut.pet.entity.Order;
import com.xmut.pet.entity.OrderItem;
import com.xmut.pet.entity.Result;
import com.xmut.pet.entity.Store;
import com.xmut.pet.mapper.OrderItemMapper;
import com.xmut.pet.service.OrderItemService;
import com.xmut.pet.service.OrderService;
import com.xmut.pet.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
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
    private static final Map<Integer, String> STATUS_MAP = new HashMap<>();

    static {
        STATUS_MAP.put(1, "unpaid");
        STATUS_MAP.put(2, "back");
        STATUS_MAP.put(3, "unreceived");
        STATUS_MAP.put(4, "received");
        STATUS_MAP.put(5, "completed");
        STATUS_MAP.put(6, "refunds");
        STATUS_MAP.put(7, "cancelled");
    }

    @Autowired
    private OrderService orderService;
    @Autowired
    private StoreService storeService;

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
        if (orderItem.getType() == null) {
            orderItem.setType(1);
        }
        this.save(orderItem);
        return true;
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
    public boolean cancelOrderItem(List<Integer> idList) {
        for (Integer id : idList) {
            OrderItem orderItem = this.getById(id);
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
    public Result<List<OrderItemVO>> getListByOrderId(Integer orderId) {
        Result<List<OrderItemVO>> All = new Result();
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
        All.setData(orderItemVOList);
        return All;
    }
}
