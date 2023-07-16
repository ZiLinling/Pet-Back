package com.xmut.pet.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xmut.pet.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * <p>
 * 
 * </p>
 *
 * @author Zi
 * @since 2023-06-08 09:58:59
 */
@Data
@TableName("`order`")
@ApiModel("Order")
public class Order extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 客户ID
     */
    private Integer userId;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 订单总价
     */
    private BigDecimal price;

    /**
     * 收货人名字
     */
    private String name;

    /**
     * 收货人电话电话
     */
    private String telephone;

    /**
     * 收货人地址
     */
    private String address;

    /**
     * 订单备注
     */
    private String postscript;

    /**
     * 是否评论，0是未评论，1是已评论
     */
    private Boolean isComment;
}
