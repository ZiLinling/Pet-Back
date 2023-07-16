package com.xmut.pet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xmut.pet.Utils.JwtUtil;
import com.xmut.pet.VO.OrderItemVO;
import com.xmut.pet.VO.OrderVO;
import com.xmut.pet.VO.StoreVO;
import com.xmut.pet.entity.Order;
import com.xmut.pet.entity.OrderItem;
import com.xmut.pet.entity.Result;
import com.xmut.pet.mapper.OrderItemMapper;
import com.xmut.pet.service.CartService;
import com.xmut.pet.service.OrderItemService;
import com.xmut.pet.service.OrderService;
import com.xmut.pet.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

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
    private CartService cartService;
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

    @Override
    public List<OrderItem> getListByStoreId(Integer storeId) {
        QueryWrapper<OrderItem> queryWrapper = new QueryWrapper();
        queryWrapper.eq("store_id", storeId);
        return this.list(queryWrapper);
    }

    @Override
    public Result listOrderItem() {
        Result result = new Result();
        Integer userId = JwtUtil.getUserId(request.getHeader("token"));
        // 根据用户id获取所有order
        List<Order> orders = orderService.getListByUserId(userId);

        // 使用Map将订单项按照订单ID存储到对应的订单对象中
        List<OrderVO> orderVOList = new ArrayList<>();
        for (Order order : orders) {

            // 构造查询条件，查询订单项
            QueryWrapper<OrderItem> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("order_id", order.getId());
            List<OrderItem> items = this.list(queryWrapper);
            OrderVO orderVO = new OrderVO();
            // 构造 StoreVO 列表
            List<StoreVO> storeVOList = new ArrayList<>();
            for (OrderItem item : items) {

                if (item.getStatus() == 1) {
                    // 根据商品 id 查询商店信息
                    StoreVO storeVO = storeService.getStoreVOByGoodsId(item.getItemId());
                    // 判断商店信息是否已经存在于列表中
                    boolean found = false;
                    for (StoreVO vo : storeVOList) {
                        if (vo.getStoreId().equals(storeVO.getStoreId())) {
                            OrderItemVO itemVO = getItemVO(item);
                            vo.getOrderItemVOList().add(itemVO);
                            break;
                        }
                    }
                    // 如果商店信息不存在于列表中，则将其加入列表
                    if (!found) {
                        OrderItemVO itemVO = getItemVO(item);
                        storeVO.setOrderItemVOList(new ArrayList<>(Collections.singletonList(itemVO)));
                        storeVOList.add(storeVO);

                    }
                } else {
                    // 根据宠物 id 查询商店信息
                    StoreVO storeVO = storeService.getStoreVOByPetId(item.getItemId());
                    // 判断商店信息是否已经存在于列表中
                    boolean found = false;
                    for (StoreVO vo : storeVOList) {
                        if (vo.getStoreId().equals(storeVO.getStoreId())) {
                            OrderItemVO itemVO = getItemVO(item);
                            vo.getOrderItemVOList().add(itemVO);
                            found = true;
                            break;
                        }
                    }
                    // 如果商店信息不存在于列表中，则将其加入列表
                    if (!found) {
                        OrderItemVO itemVO = getItemVO(item);
                        storeVO.setOrderItemVOList(new ArrayList<>(Collections.singletonList(itemVO)));
                        storeVOList.add(storeVO);
                    }
                }
                orderVO.setStoreVOList(storeVOList);
                orderVO.setId(order.getId());
            }
//            // 计算订单总价，并将其设置到 StoreVO 中
//            BigDecimal totalPrice = BigDecimal.ZERO;
//            for (StoreVO vo : storeVOList) {
//                BigDecimal storePrice = BigDecimal.ZERO;
//                for (OrderItemVO itemVO : vo.getOrderItemVOList()) {
//                    storePrice = storePrice.add(itemVO.getPrice());
//                }
//                totalPrice = totalPrice.add(storePrice);
//                vo.setPrice(storePrice);
//            }
//            // 设置订单总价
//            order.setPrice(totalPrice);

            orderVOList.add(orderVO);
        }
        result.setData(orderVOList);
        // 存放最后的订单值
        return result;
    }


    public OrderItemVO getItemVO(OrderItem item) {
        OrderItemVO orderItemVO = new OrderItemVO(item);
        //将status转换为英文的状态
        String statusString = STATUS_MAP.get(item.getStatus());
        orderItemVO.setStatus(statusString);
        Map<String, Object> others = new HashMap<>();
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
}
