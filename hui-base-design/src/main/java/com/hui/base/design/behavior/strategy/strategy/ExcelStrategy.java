package com.hui.base.design.behavior.strategy.strategy;

/**
 * <b><code>ExcelStrategy</code></b>
 * <p/>
 * Description
 * <p/>
 * <b>Creation Time:</b> 2019/2/13 10:15.
 *
 * @author Hu-Weihui
 * @since hui-base-design-pattern ${PROJECT_VERSION}
 */
public class ExcelStrategy implements FileExportStrategy{
    @Override
    public void export() {
        System.out.println("导出为Excel");
    }
}
