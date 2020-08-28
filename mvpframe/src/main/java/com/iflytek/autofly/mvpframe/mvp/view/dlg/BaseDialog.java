package com.iflytek.autofly.mvpframe.mvp.view.dlg;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.widget.TextView;

import com.iflytek.autofly.mvpframe.R;

/**
 * @author wgsun
 * @descrbe
 * @since 2020/2/2 11:31
 */
public class BaseDialog extends Dialog {

    public interface OnClickListener{
        void onConfim(Dialog dialog, Object sender);

        void onCancel(Dialog dialog);
    }

    protected OnClickListener clickListener;

    public void setClickListener(OnClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public BaseDialog(Context context) {
        super(context, R.style.dialogTheme);
    }

    protected void setTextViewText(TextView btn, String text) {
        if (btn != null) {
            if(!TextUtils.isEmpty(text)){
                btn.setText(text);
            }
        }
    }
}
