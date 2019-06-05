package com.hui.base.design.behavior.strategy.context;

import com.hui.base.design.behavior.strategy.strategy.FileExportStrategy;

/**
 * <b><code>Context</code></b>
 * <p/>
 * Description
 * <p/>
 * <b>Creation Time:</b> 2019/2/13 10:21.
 *
 * @author Hu-Weihui
 * @since hui-base-design-pattern ${PROJECT_VERSION}
 */
public class Context {

    private FileExportStrategy fileExportStrategy;

    public Context(FileExportStrategy fileExportStrategy){
        this.fileExportStrategy = fileExportStrategy;
    }

    public void execute(){
        this.fileExportStrategy.export();
    }
}
