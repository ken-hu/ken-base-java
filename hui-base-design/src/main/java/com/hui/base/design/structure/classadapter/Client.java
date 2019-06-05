package com.hui.base.design.structure.classadapter;

import com.hui.base.design.structure.classadapter.adapter.AndroidAdapter;
import com.hui.base.design.structure.objadapter.targetobj.Usb;

/**
 * <b><code>Client</code></b>
 * <p/>
 * Description
 * <p/>
 * <b>Creation Time:</b> 2019/2/14 10:25.
 *
 * @author Hu-Weihui
 * @since hui-base-design-pattern ${PROJECT_VERSION}
 */
public class Client {
    public static void main(String[] args) {
        Usb usb = new AndroidAdapter();
        usb.charging();
        usb.transfrom();
    }
}
