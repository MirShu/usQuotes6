package com.quantpower.usquotes.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.quantpower.usquotes.R;
import com.quantpower.usquotes.utils.UIHelper;
import com.quantpower.usquotes.widget.loding.LodingDialog;

/**
 * Created by ShuLin on 2017/5/17.
 * Email linlin.1016@qq.com
 * Company Shanghai Quantpower Information Technology Co.,Ltd.
 */

public class SettingActivity extends Activity {
    private TextView tvPush;
    private TextView tvClean;
    private TextView tvAbout;
    private ImageView imageBack;
    private LodingDialog lodingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settingactivity);
        tvPush = (TextView) findViewById(R.id.tv_push);
        tvClean = (TextView) findViewById(R.id.tv_clean);
        tvAbout = (TextView) findViewById(R.id.tv_about);
        imageBack = (ImageView) findViewById(R.id.image_back);
        imageBack.setOnClickListener(v -> finish());
        tvPush.setOnClickListener(v -> UIHelper.startActivity(SettingActivity.this, PushSettingActivity.class));
        tvAbout.setOnClickListener(v -> UIHelper.startActivity(SettingActivity.this, AboutActivity.class));
        tvClean.setOnClickListener(v -> {
            lodingDialog = new LodingDialog(SettingActivity.this, "加载中...");
            lodingDialog.show();
            new Handler().postDelayed(() -> {
                lodingDialog.dismiss();
                UIHelper.toastMessage(SettingActivity.this, "暂无缓存");
            }, 1000);
        });
    }
}
