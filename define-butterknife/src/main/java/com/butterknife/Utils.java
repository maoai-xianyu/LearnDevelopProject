package com.butterknife;

import android.app.Activity;
import android.view.View;

/**
 * @author zhangkun
 * @time 2020-03-23 19:25
 */
public class Utils {

    public static <T extends View> T findViewById(Activity activity, int viewId) {
        return activity.findViewById(viewId);
    }
}
