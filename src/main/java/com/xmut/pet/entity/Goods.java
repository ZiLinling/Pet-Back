package com.xmut.pet.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author Zi
 * @since 2023-06-08 09:58:20
 */
@Getter
@Setter
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 周边名字
     */
    private String name;

    /**
     * 商店id
     */
    private Integer storeId;

    /**
     * 库存
     */
    private Integer stock;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 图片
     */
    private String img;

    /**
     * 商品描述
     */
    private String describe;

    /**
     * 类别
     */
    private Integer category;

    /**
     * 商品状态
     */
    private Integer status;
}
