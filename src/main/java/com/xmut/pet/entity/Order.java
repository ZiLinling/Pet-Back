package com.xmut.pet.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.xmut.pet.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author Zi
 * @since 2023-06-06 09:27:37
 */
@Getter
@Setter
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
     * 订房总价
     */
    private BigDecimal price;

    /**
     * 订单状态
     */
    private String status;

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
}
