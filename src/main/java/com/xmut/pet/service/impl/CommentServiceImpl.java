package com.xmut.pet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xmut.pet.Utils.DateTool;
import com.xmut.pet.Utils.JwtUtil;
import com.xmut.pet.entity.Comment;
import com.xmut.pet.entity.User;
import com.xmut.pet.mapper.CommentMapper;
import com.xmut.pet.service.CommentService;
import com.xmut.pet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Zi
 * @since 2023-06-08 09:57:45
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private UserService userService;

    @Override
    public List<Comment> getListByGoodsId(Integer goodsId) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("goods_id", goodsId);
        List<Comment> comments = this.list(queryWrapper);
        for (Comment comment : comments) {
            User user = userService.getById(comment.getUserId());
            comment.put("user", user);
        }
        return comments;
    }

    @Override
    public List<Comment> getListByUserId(Integer userId) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        return list(queryWrapper);
    }

    @Override
    public boolean add(Comment comment) {
        Integer userId = JwtUtil.getUserId(request.getHeader("token"));
        comment.setUserId(userId);
        comment.setCreateTime(DateTool.getCurrTime());
        this.save(comment);

        return true;
    }
}
