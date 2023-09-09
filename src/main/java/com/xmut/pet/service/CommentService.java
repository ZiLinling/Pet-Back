package com.xmut.pet.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xmut.pet.entity.Comment;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Zi
 * @since 2023-06-08 09:57:45
 */
public interface CommentService extends IService<Comment> {

    List<Comment> getListByGoodsId(Integer goodsId);

    List<Comment> getListByUserId(Integer userId);

    boolean add(Comment comment);

    boolean addAdditional(Integer commentId, String additional);
}
