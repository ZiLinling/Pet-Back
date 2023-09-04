package com.xmut.pet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xmut.pet.Utils.JwtUtil;
import com.xmut.pet.entity.Chat;
import com.xmut.pet.entity.User;
import com.xmut.pet.mapper.ChatMapper;
import com.xmut.pet.service.ChatService;
import com.xmut.pet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Ji
 * @since 2023-07-23 04:43:57
 */
@Service
public class ChatServiceImpl extends ServiceImpl<ChatMapper, Chat> implements ChatService {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private UserService userService;


    @Override
    public Chat add(Chat chat) {
        // 获取当前时间
        LocalTime now = LocalTime.now();
        // 定义输出格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        chat.setTime(now.format(formatter));

        Integer userId = JwtUtil.getUserId(request.getHeader("token"));
        User user = userService.getById(userId);
        chat.setUserId(userId);
        chat.setUsername(user.getName());
        chat.setFace(user.getImg());
        this.save(chat);
        return chat;
    }

    @Override
    public List<Chat> list(Integer recipient) {
        Integer userId = JwtUtil.getUserId(request.getHeader("token"));
        QueryWrapper<Chat> list = new QueryWrapper<>();
        list.and(wrapper -> wrapper.eq("user_id", userId).eq("recipient", recipient))
                .or(wrapper -> wrapper.eq("user_id", recipient).eq("recipient", userId))
                .orderByAsc("time"); // 按照时间从小到大排列
        return this.list(list);
    }

    @Override
    public boolean remove(Integer id) {
        // 获取当前时间
        LocalDateTime now = LocalDateTime.now();

        // 定义时间格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        Chat chat = this.getById(id);
        // 将时间字符串解析为 LocalDateTime 对象
        LocalDateTime yourTime = LocalDateTime.parse(chat.getTime(), formatter);

        // 计算两个时间之间的时间差
        Duration duration = Duration.between(now, yourTime);

        // 获取时间差的分钟数
        long diffInMinutes = duration.toMinutes();
        // 判断是否超时
        if (diffInMinutes < 2) {
            // 没有超时，可以删除
            this.removeById(id);
            return true;
        } else {
            // 已经超时，不能删除
            return false;
        }
    }
}
