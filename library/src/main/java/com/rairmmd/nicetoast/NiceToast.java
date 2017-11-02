package com.rairmmd.nicetoast;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.rarimmd.nicetoast.R;

/**
 * @author Rair
 * @date 2017/11/2
 * <p>
 * desc:
 */

public class NiceToast {

    private static NiceToast niceToast;
    private final NiceToastView ntvNiceToast;
    private final Toast toast;
    private int duration = Toast.LENGTH_LONG;
    private int gravity = Gravity.BOTTOM;
    private final View view;

    public static NiceToast newNiceToast(Context context) {
        if (niceToast == null) {
            niceToast = new NiceToast(context);
        }
        return niceToast;
    }

    private NiceToast(Context context) {
        view = LayoutInflater.from(context).inflate(R.layout.layout_nice_toast, new LinearLayout(context));
        ntvNiceToast = view.findViewById(R.id.ntv_nice_toast);
        toast = new Toast(context);
    }

    public NiceToast setText(String text) {
        ntvNiceToast.setMessage(text);
        return this;
    }

    public NiceToast setIcon(int icon) {
        ntvNiceToast.setIcon(icon);
        return this;
    }

    public NiceToast setBgColor(int bgColor) {
        ntvNiceToast.setColor(bgColor);
        return this;
    }

    public NiceToast setTextColor(int color) {
        ntvNiceToast.setTextColor(color);
        return this;
    }

    public NiceToast setWidth(int width) {
        ntvNiceToast.setWidth(width);
        return this;
    }

    public NiceToast isRound(boolean round) {
        if (!round) {
            ntvNiceToast.setBorderRetangle();
        }
        return this;
    }

    public NiceToast alignTop(boolean top) {
        if (top) {
            gravity = Gravity.TOP;
        }
        return this;
    }

    public NiceToast setDuration(int duration) {
        this.duration = duration;
        return this;
    }

    public NiceToast setGravity(int gravity) {
        this.gravity = gravity;
        return this;
    }

    public void show() {
        ntvNiceToast.show();
        toast.setDuration(duration);
        toast.setGravity(gravity, 0, 0);
        toast.setView(view);
        toast.show();
    }


}
