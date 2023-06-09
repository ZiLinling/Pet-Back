package com.xmut.pet.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.xmut.pet.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;


/**
 * <p>
 * 
 * </p>
 *
 * @author Zi
 * @since 2023-06-08 09:57:45
 */
@Data
@ApiModel("Comment")
public class Comment extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 商品周边id
     */
    private Integer goodsId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 评价等级分五个等级，0，1，2，3，4
     */
    private Double level;

    /**
     * 评论内容
     */
    private String comment;

    /**
     * 追加评论
     */
    private String additional;

    /**
     * 创建时间
     */
    private String createTime;
}
