package com.xmut.pet.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author Ji
 * @since 2023-07-12 05:00:47
 */
@Getter
@Setter
@TableName("bank_card")
public class BankCard implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 银行卡id
     */
    private Integer id;

    /**
     * 银行id
     */
    private Integer bankId;

    /**
     * 0是储蓄卡，1是信用卡
     */
    private Byte type;

    /**
     * 银行卡号
     */
    private String number;

    /**
     * 是否冻结
     */
    private Byte isFrezed;
}
