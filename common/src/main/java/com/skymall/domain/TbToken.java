package com.skymall.domain;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户Token
 * </p>
 *
 * @author zhaogengren
 * @since 2019-03-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TbToken implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long userId;

        /**
     * token
     */
         private String token;

        /**
     * 过期时间
     */
         private LocalDateTime expireTime;

        /**
     * 更新时间
     */
         private LocalDateTime updateTime;


}
