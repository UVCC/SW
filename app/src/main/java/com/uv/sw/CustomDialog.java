package com.uv.sw;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by chinauv on 2017/10/30.
 */

public class CustomDialog extends Dialog {

    public CustomDialog(Context context) {
        super(context);
    }

    public CustomDialog(Context context, int theme) {
        super(context, theme);
    }

    public static class Builder {
        private Context context;

        public Builder(Context context) {
            this.context = context;
        }

        public CustomDialog create() {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // instantiate the dialog with the custom Theme
            final CustomDialog dialog = new CustomDialog(context, R.style.Dialog);
            View layout = inflater.inflate(R.layout.dialog_normal_layout, null);
//            dialog.setContentView(layout);

//
//            dialog.addContentView(layout, new LayoutParams(
//                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));


            dialog.setContentView(layout);
            return dialog;
        }
    }

}
