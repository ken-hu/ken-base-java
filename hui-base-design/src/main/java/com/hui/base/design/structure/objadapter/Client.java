package com.hui.base.design.structure.objadapter;

import com.hui.base.design.structure.classadapter.adaptee.Android;
import com.hui.base.design.structure.objadapter.adapter.AndroidAdapter2;
import com.hui.base.design.structure.objadapter.targetobj.Usb;

/**
 * <b><code>Client</code></b>
 * <p/>
 * Description
 * <p/>
 * <b>Creation Time:</b> 2019/2/14 11:08.
 *
 * @author Hu-Weihui
 * @since hui-base-design-pattern ${PROJECT_VERSION}
 */
public class Client {
    public static void main(String[] args) {
        Usb usb = new AndroidAdapter2(new Android());
        usb.charging();
        usb.transfrom();
    }
}
