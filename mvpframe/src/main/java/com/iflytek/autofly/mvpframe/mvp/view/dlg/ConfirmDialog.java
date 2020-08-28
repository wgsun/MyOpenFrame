package com.iflytek.autofly.mvpframe.mvp.view.dlg;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import com.iflytek.autofly.mvpframe.R;
import com.iflytek.autofly.mvpframe.mvp.model.listener.NoDoubleClickListener;

/**
 * @author wgsun
 * @descrbe
 * @since 2020/2/2 11:34
 */

public class ConfirmDialog extends BaseDialog {

    private String title;
    private String positive;
    private String negative;

    private TextView mTvTitle;

    public ConfirmDialog(Context context, String title, String positive, String negative) {
        super(context);
        this.title = title;
        this.positive = positive;
        this.negative = negative;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window = getWindow();
        setContentView(R.layout.float_dialog_confirm);
        //window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.CENTER;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        //params.windowAnimations = R.style.bottom_enter_and_exist;
        // animation
        getWindow().setAttributes(params);
        setCancelable(true);
        setCanceledOnTouchOutside(false);
        init();
    }

    public void init() {
        Button btnNegative = (Button) findViewById(R.id.btn_negative);
        setTextViewText(btnNegative, negative);

        btnNegative.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                dismiss();
                if (null != clickListener) {
                    clickListener.onCancel(ConfirmDialog.this);
                }
            }
        });

        Button btnPositive = (Button) findViewById(R.id.btn_positive);
        btnPositive.setSelected(true);
        setTextViewText(btnPositive, positive);

        btnPositive.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                dismiss();
                if (null != clickListener) {
                    clickListener.onConfim(ConfirmDialog.this, view);
                }
            }
        });

        mTvTitle = (TextView) findViewById(R.id.tv_title);
        setTextViewText(mTvTitle, title);
    }

}
