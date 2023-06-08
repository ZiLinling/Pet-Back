package com.xmut.pet.service.impl;

import com.xmut.pet.entity.Comment;
import com.xmut.pet.mapper.CommentMapper;
import com.xmut.pet.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Zi
 * @since 2023-06-08 09:57:45
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
