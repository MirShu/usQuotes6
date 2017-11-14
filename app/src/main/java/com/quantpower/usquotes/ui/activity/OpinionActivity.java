package com.quantpower.usquotes.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.quantpower.usquotes.R;
import com.quantpower.usquotes.utils.UIHelper;

/**
 * Created by ShuLin on 2017/5/17.
 * Email linlin.1016@qq.com
 * Company Shanghai Quantpower Information Technology Co.,Ltd.
 */

public class OpinionActivity extends Activity {
    ImageView imageBack;
    TextView suBmit, etName,etAddress;
    EditText edDetailed;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opinionactivity);
        imageBack = (ImageView) findViewById(R.id.image_back);
        suBmit = (TextView) findViewById(R.id.submit);
        etName = (TextView) findViewById(R.id.ed_name);
        etAddress = (TextView) findViewById(R.id.ed_address);
        imageBack.setOnClickListener(v -> finish());
        edDetailed = (EditText) findViewById(R.id.ed_detailed);
        String content = edDetailed.getText().toString().trim();
        String mName = etName.getText().toString().trim();
        String mAddress = etAddress.getText().toString().trim();
        suBmit.setOnClickListener(v -> {
            if (TextUtils.isEmpty(content)||TextUtils.isEmpty(mName)||TextUtils.isEmpty(mAddress)) {
                UIHelper.toastMessage(OpinionActivity.this, "请填写完整资料");
                return;
            } else if (!TextUtils.isEmpty(content)) {
                UIHelper.toastMessage(OpinionActivity.this, "意见提交成功");
            }
        });

    }
}
