package com.example.mybatisplusgenerator.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author Kurt
 * @since 2023-07-28 04:58:59
 */
@Getter
@Setter
@TableName("goods")
@ApiModel(value = "Goods对象", description = "")
public class Goods {

    @ApiModelProperty("id")
    @TableField("id")
    private Long id;

    @ApiModelProperty("商品编码")
    @TableField("goods_sn")
    private String goodsSn;

    @ApiModelProperty("商品名称")
    @TableField("name")
    private String name;

    @ApiModelProperty("标题")
    @TableField("title")
    private String title;

    @ApiModelProperty("售价")
    @TableField("price")
    private BigDecimal price;

    @ApiModelProperty("商品状态")
    @TableField("status")
    private Integer status;

    @ApiModelProperty("销量")
    @TableField("sale_count")
    private Integer saleCount;

    @ApiModelProperty("创建时间")
    @TableField("create_date")
    private Date createDate;

    @ApiModelProperty("修改时间")
    @TableField("modify_date")
    private Date modifyDate;


}
