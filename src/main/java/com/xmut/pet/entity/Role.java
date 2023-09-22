package com.xmut.pet.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author Ji
 * @since 2023-09-22 08:22:52
 */
@Getter
@Setter
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String level;

    private String discount;

    private BigDecimal cost;
}
