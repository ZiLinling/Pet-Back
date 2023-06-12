package com.xmut.pet.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.math.BigDecimal;

import com.xmut.pet.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author Zi
 * @since 2023-06-08 09:58:20
 */
@Data
@ApiModel("Goods")
public class Goods extends BaseEntity implements Serializable {

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
     * 商品图片
     */
    private String img;

    /**
     * 商品描述
     */
    private String description;

    /**
     * 类别
     */
    private Integer category;

    /**
     * 商品状态
     */
    private Integer status;
}
