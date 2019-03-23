package com.skymall.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhaogengren
 * @since 2019-03-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("nideshop_attribute")
public class Attribute implements Serializable {

    private static final long serialVersionUID = 1L;

        /**
     * 主键
     */
    @ApiModelProperty(value="id" ,required=false)
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

        /**
     * 商品类型
     */
    @ApiModelProperty(value="参数类型id" ,required=false)
    private Integer attributeCategoryId;

        /**
     * 属性名称
     */
     @ApiModelProperty(value="名称" ,required=false)
     private String name;

        /**
     * 当添加商品时,该属性的添加类别; 0为手功输入;1为选择输入;2为多行文本输入
     */
     @ApiModelProperty(value ="毕设项目暂时置空，当添加商品时,该属性的添加类别; 0为手功输入;1为选择输入;2为多行文本输入", required = false)
     private Boolean inputType;

        /**
     * 即选择输入,则attr_name对应的值的取值就是该这字段值
     */
     @ApiModelProperty(value ="毕设项目暂时置空，他主要是关联goods_attribute的value的，用来方便传值", required = false)
     private String value;

    @ApiModelProperty(value ="毕设项目暂时置空，他主要是关联goods_attribute的value的，用来方便传值", required = false)
    private Integer sortOrder;


}
