package com.xmut.pet.component;

import com.xmut.pet.Utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Component
//@EnableScheduling
public class CountdownListener {

    // ...
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private RedisTemplate redisTemplate;



//    @Scheduled(cron = "* * * * * *")
    public void executeCountdownTask(Integer couponId) {
        // 在每个整点的五分钟时刻执行倒计时任务
        System.out.println("监听器触发了");
        // 获取当前时间和目标时间，假设目标时间为 2023-09-22 10:00:00
        LocalDateTime now = LocalDateTime.now();
        String create_time=redisUtil.get("create_time:"+couponId);
        String end_time=redisUtil.get("end_time:"+couponId);
        create_time=create_time.replace(" ", "T");
        end_time=end_time.replace(" ", "T");
        LocalDateTime createTime= LocalDateTime.parse(create_time);
        LocalDateTime endTime= LocalDateTime.parse(end_time);
        // 设置倒计时值到Redis中，假设使用键 "countdown" 存储
        if(now.compareTo(createTime)<0 ){
            long seconds = Duration.between(now, createTime).getSeconds();
            redisUtil.set("countdown:"+couponId, "距离开始还有"+String.valueOf(seconds)+"秒");
            redisUtil.set("status:"+couponId, String.valueOf(1));
        }else if(now.compareTo(endTime)<0){
            long seconds = Duration.between(now, endTime).getSeconds();
            redisUtil.set("countdown:"+couponId, "距离活动还有"+String.valueOf(seconds)+"秒");
            redisUtil.set("status:"+couponId, String.valueOf(2));
        }else{
            redisUtil.set("countdown:"+couponId, "活动已结束");
            redisUtil.set("status:"+couponId, String.valueOf(3));
        }


    }
}