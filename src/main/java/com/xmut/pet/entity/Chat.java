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
 * @since 2023-07-23 04:43:57
 */
@Getter
@Setter
public class Chat implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer userId;

    private Integer recipient;

    private String username;

    private String face;

    private String time;

    private String type;

    private String msg;
}
