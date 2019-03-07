package com.skymall.domain;

import java.math.BigDecimal;
import java.io.Serializable;
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
public class QrtzSimpropTriggers implements Serializable {

    private static final long serialVersionUID = 1L;

    private String schedName;

    private String triggerName;

    private String triggerGroup;

    private String strProp1;

    private String strProp2;

    private String strProp3;

    private Integer intProp1;

    private Integer intProp2;

    private Long longProp1;

    private Long longProp2;

    private BigDecimal decProp1;

    private BigDecimal decProp2;

    private String boolProp1;

    private String boolProp2;


}
