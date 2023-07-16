package com.xmut.pet.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author Ji
 * @since 2023-07-12 05:00:15
 */
@Getter
@Setter
public class Bank implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 银行id
     */
    private Integer id;

    /**
     * 银行名称
     */
    private String name;

    /**
     * 银行卡背景
     */
    private String img;
}
