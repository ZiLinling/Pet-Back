package com.xmut.pet.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.xmut.pet.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;


/**
 * <p>
 * 
 * </p>
 *
 * @author Zi
 * @since 2023-06-08 10:00:41
 */
@Data
@ApiModel("Store")
public class Store extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 商店名称
     */
    private String name;

    /**
     * 商店电话
     */
    private String telephone;

    /**
     * 商店描述
     */
    private String describe;

    /**
     * 商店地址
     */
    private String address;

    /**
     * 商店状态
     */
    private Integer status;

    /**
     * 商店图片
     */
    private String img;
}
