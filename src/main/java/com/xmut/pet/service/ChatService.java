package com.xmut.pet.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xmut.pet.entity.Chat;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Ji
 * @since 2023-07-23 04:43:57
 */
public interface ChatService extends IService<Chat> {
    Chat add(Chat chat);

    List<Chat> list(Integer recipient);

    boolean remove(Integer recipient);
}
