package com.xmut.pet.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.xmut.pet.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author Ji
 * @since 2023-09-10 02:39:51
 */
@Getter
@Setter
public class Coupon extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 优惠券名称
     */
    private String title;

    /**
     * 使用规则
     */
    private String rule;

    /**
     * 实付金额
     */
    private Integer payValue;

    /**
     * 可抵扣金额
     */
    private Integer actualValue;

    /**
     * 0是普通商家券，1是秒杀券
     */
    private Integer type;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 结束时间
     */
    private String endTime;
}
