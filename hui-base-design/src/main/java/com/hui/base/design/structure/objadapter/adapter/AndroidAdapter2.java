package com.hui.base.design.structure.objadapter.adapter;

import com.hui.base.design.structure.classadapter.adaptee.Android;
import com.hui.base.design.structure.objadapter.targetobj.Usb;

/**
 * <b><code>AndroidAdapter2</code></b>
 * <p/>
 * Description
 * <p/>
 * <b>Creation Time:</b> 2019/2/14 11:10.
 *
 * @author Hu-Weihui
 * @since hui-base-design-pattern ${PROJECT_VERSION}
 */
public class AndroidAdapter2 implements Usb {
    private Android android;

    public AndroidAdapter2(Android android){
        super();
        this.android = android;
    }

    @Override
    public void charging() {
        System.out.print("USB进行：");
        android.charging();
    }

    @Override
    public void transfrom() {
        System.out.print("USB进行：");
        android.transfrom();
    }
}
