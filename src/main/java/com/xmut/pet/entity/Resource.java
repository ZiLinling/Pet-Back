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
 * @since 2023-06-08 10:00:22
 */

@Data
@ApiModel("Resource")
public class Resource extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 资源id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 资源链接
     */
    private String url;

    /**
     * 宠物id
     */
    private Integer petId;
}
