package com.hui.base.common.autoreport.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <b><code>ExcelData</code></b>
 * <p/>
 * Description:
 * <p/>
 * <b>Creation Time:</b> 2018/11/27 11:14.
 *
 * @author Hu weihui
 */
@Data
public class ExcelData implements Serializable {
    private static final long serialVersionUID = 5298452161158057081L;

    private List<String> title;

    private List<List<String>> values;

    public ExcelData() {
    }

    public ExcelData(List<String> title) {
        this.title = title;
    }

    public ExcelData(List<String> title, List<List<String>> values) {
        this.title = title;
        this.values = values;
    }

}
