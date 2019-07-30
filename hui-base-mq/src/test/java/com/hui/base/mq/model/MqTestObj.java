package com.hui.base.mq.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <b><code>MqTestObj</code></b>
 * <p/>
 * Description
 * <p/>
 * <b>Creation Time:</b> 2019/7/29 14:42.
 *
 * @author Hu-Weihui
 * @since hui-base-java ${PROJECT_VERSION}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MqTestObj implements Serializable {
    private String name;
}
