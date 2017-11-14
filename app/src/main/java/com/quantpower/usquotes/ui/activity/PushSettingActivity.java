package com.quantpower.usquotes.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.quantpower.usquotes.R;

/**
 * Created by ShuLin on 2017/5/17.
 * Email linlin.1016@qq.com
 * Company Shanghai Quantpower Information Technology Co.,Ltd.
 */

public class PushSettingActivity extends Activity {
    ImageView imageBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pushsettingactivity);
        imageBack = (ImageView) findViewById(R.id.image_back);
        imageBack.setOnClickListener(v -> finish());
    }
}
