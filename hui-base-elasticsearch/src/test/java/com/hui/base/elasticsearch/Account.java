package com.hui.base.elasticsearch;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;

/**
 * <b><code>Account</code></b>
 * <p/>
 * Description:
 * <p/>
 * <b>Creation Time:</b> 2019/6/29 14:56.
 *
 * @author HuWeihui
 */
@Document(indexName = "es",type = "account")
@Data
@Builder
public class Account implements Serializable {

    private static final long serialVersionUID = -6701949700224271465L;

    @Id
    private Integer id;

    @Field
    private String account;

    private String name;

    private String pwd;

}
