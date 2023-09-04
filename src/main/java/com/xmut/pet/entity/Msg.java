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
 * @since 2023-07-30 12:33:55
 */
@Getter
@Setter
public class Msg implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 接收者的id分类，0为商店，1为用户
     */
    private Byte type;

    /**
     * 接收者的id
     */
    private Integer recipient;

    /**
     * 未读消息数量
     */
    private Integer tisNum;
}
