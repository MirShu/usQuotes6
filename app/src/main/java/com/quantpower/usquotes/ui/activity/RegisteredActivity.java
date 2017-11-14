package com.quantpower.usquotes.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.quantpower.usquotes.R;
import com.quantpower.usquotes.utils.UIHelper;

/**
 * Created by ShuLin on 2017/5/18.
 * Email linlin.1016@qq.com
 * Company Shanghai Quantpower Information Technology Co.,Ltd.
 */

public class RegisteredActivity extends Activity {
    Button btLogin;
    Button btIdent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registered_activity);
        btIdent = (Button) findViewById(R.id.bt_ident);
        btLogin = (Button) findViewById(R.id.bt_login);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIHelper.startActivity(RegisteredActivity.this, MainActivity.class);
            }
        });

        btIdent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UIHelper.toastMessage(RegisteredActivity.this, "稍等。。。");
            }
        });
    }
}
