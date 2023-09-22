package com.xmut.pet.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2023-09-11 11:27:24
 */
@Getter
@Setter
@TableName("coupons_by_user")
public class CouponsByUser  extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 优惠券id
     */
    private Integer couponId;

    /**
     * 获取时间
     */
    private String getTime;

    /**
     * 到期时间
     */
    private String expireTime;
}
