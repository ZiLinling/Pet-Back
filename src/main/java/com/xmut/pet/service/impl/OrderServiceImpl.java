package com.xmut.pet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xmut.pet.Utils.DateTool;
import com.xmut.pet.Utils.JwtUtil;
import com.xmut.pet.entity.Order;
import com.xmut.pet.entity.Result;
import com.xmut.pet.entity.Sales_chart;
import com.xmut.pet.mapper.OrderMapper;
import com.xmut.pet.service.OrderItemService;
import com.xmut.pet.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Zi
 * @since 2023-06-08 09:58:59
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private OrderItemService orderItemService;



    @Override
    public List<Order> getListByUserId(Integer userId) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        return this.list(queryWrapper);
    }

    public Integer generate(Order order) {
        if (order.getUserId() == null) {
            order.setUserId(JwtUtil.getUserId(request.getHeader("token")));
        }
        order.setCreateTime(DateTool.getCurrTime());
        order.setIsComment(false);
        this.save(order);
        return order.getId();
    }

    @Override
    public Result<Page<Order>> page(Integer pageNum, Integer pageSize, String account, String name) {
        Result<Page<Order>> result = new Result<>();
        Page<Order> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        if (account != null && !account.equals("")) {
            queryWrapper.like("account", account);
        }
        if (name != null && !name.equals("")) {
            queryWrapper.like("name", name);
        }
        result.setData(this.page(page, queryWrapper));
        result.put("total", this.count(queryWrapper));
        return result;
    }

    @Override
    public Map<String, List<String>> getSalesChart(String begin, String end) {
        Map<String, List<String>> Chart = new HashMap<>();
        List<String> petSalesChart = new ArrayList<>();
        List<String> petTimeSalesChart = new ArrayList<>();

        List<String> goodsSalesChart = new ArrayList<>();
        List<String> goodsTimeSalesChart = new ArrayList<>();
        List<Sales_chart> salesCharts = this.baseMapper.getChartData();

        String oldTime = null;
        Integer cnt = 0, petSum = 0, goodsSum = 0;
        for (Sales_chart salesChart : salesCharts) {
            String initial_time = salesChart.getTime();
            LocalDateTime parsedDateTime = LocalDateTime.parse(initial_time, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            String time = parsedDateTime.format(DateTimeFormatter.ofPattern("M.d"));
            String total_time = parsedDateTime.format(DateTimeFormatter.ofPattern("y.M.d"));
            //将时间格式修改为几月几日
            if (total_time.compareTo(end) > 0 || total_time.compareTo(begin) < 0) {
                System.out.println(total_time.compareTo(end) > 0);
                System.out.println(total_time.compareTo(begin) < 0);
                continue;
            }
            if (oldTime == null) {
                oldTime = time;
            } else if (!Objects.equals(time, oldTime)) {

                //卖的是商品
                goodsSalesChart.add(String.valueOf(goodsSum));
                goodsTimeSalesChart.add(oldTime);
                goodsSum = 0;


                //卖的是宠物
                petSalesChart.add(String.valueOf(petSum));
                petTimeSalesChart.add(oldTime);
                petSum = 0;

                cnt++;

                oldTime = time;
            }
            if (salesChart.getType() == 1) {
                if (salesChart.getStatus() < 7 && salesChart.getStatus() > 1) {
                    goodsSum += salesChart.getPrice() * salesChart.getNum();
                }
            } else {
                if (salesChart.getStatus() < 7 && salesChart.getStatus() > 1) {
                    petSum += salesChart.getPrice();
                }
            }
        }

        goodsSalesChart.add(String.valueOf(goodsSum));
        goodsTimeSalesChart.add(oldTime);
        petSalesChart.add(String.valueOf(petSum));
        petTimeSalesChart.add(oldTime);

        Chart.put("goods", goodsSalesChart);
        Chart.put("goodsTime", goodsTimeSalesChart);
        Chart.put("pet", petSalesChart);
        Chart.put("petTime", petTimeSalesChart);
        return Chart;
    }

}
