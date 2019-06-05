package com.hui.base.design.structure.classadapter.adapter;

import com.hui.base.design.structure.classadapter.adaptee.Android;
import com.hui.base.design.structure.objadapter.targetobj.Usb;

/**
 * <b><code>AndroidAdapter</code></b>
 * <p/>
 * Description
 * <p/>
 * <b>Creation Time:</b> 2019/2/14 10:24.
 *
 * @author Hu-Weihui
 * @since hui-base-design-pattern ${PROJECT_VERSION}
 */
public class AndroidAdapter extends Android implements Usb {

    @Override
    public void charging() {
        System.out.print("USB进行：");
        super.charging();
    }

    @Override
    public void transfrom() {
        System.out.print("USB进行：");
        super.transfrom();
    }
}
