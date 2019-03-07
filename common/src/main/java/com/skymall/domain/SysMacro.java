package com.skymall.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 通用字典表
 * </p>
 *
 * @author zhaogengren
 * @since 2019-03-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysMacro implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

        /**
     * 父级id
     */
         private Long parentId;

        /**
     * 名称
     */
         private String name;

        /**
     * 值
     */
         private String value;

        /**
     * 状态，0：隐藏   1：显示
     */
         private Integer status;

        /**
     * 类型,0:目录，1:参数配置
     */
         private Integer type;

        /**
     * 排序
     */
         private Integer orderNum;

        /**
     * 备注
     */
         private String remark;

        /**
     * 创建时间
     */
         private LocalDate gmtCreate;

        /**
     * 修改时间
     */
         private LocalDate gmtModified;


}
