package com.quantpower.usquotes.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.quantpower.usquotes.R;
import com.quantpower.usquotes.constant.URLS;
import com.quantpower.usquotes.model.MessageResult;
import com.quantpower.usquotes.utils.PhoneLegal;
import com.quantpower.usquotes.utils.UIHelper;
import com.rey.material.widget.RelativeLayout;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;

/**
 * Created by ShuLin on 2017/5/18.
 * Email linlin.1016@qq.com
 * Company Shanghai Quantpower Information Technology Co.,Ltd.
 */

public class GuideActivity extends Activity {
    private ImageView point;
    private ViewPager mViewPager;
    private LinearLayout llContainer;
    private ImageView ivRedPoint;
    private TextView btStart;
    private ArrayList<ImageView> mImageViewList;
    private int[] mImageIds = new int[]{R.mipmap.guide_one, R.mipmap.guide_two, R.mipmap.guide_three
    };
    private int mPointDis;
    private LinearLayout llPopuLogin;
    TimeCount time;
    private com.rey.material.widget.TextView tvLoginIdent;
    private EditText etLoginCode;
    private EditText etLoginPhone;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        mViewPager = (ViewPager) findViewById(R.id.vp_guide);
        llContainer = (LinearLayout) findViewById(R.id.ll_container);
        ivRedPoint = (ImageView) findViewById(R.id.iv_red_point);
        llPopuLogin = (LinearLayout) findViewById(R.id.ll_popu_login);
        btStart = (TextView) findViewById(R.id.bt_start);
        tvLoginIdent = (com.rey.material.widget.TextView) findViewById(R.id.tv_ident);
        etLoginCode = (EditText) findViewById(R.id.et_code);
        etLoginPhone = (EditText) findViewById(R.id.et_phone);
        initData();
    }

    public void initData() {
        initRecord();
        mViewPager.setAdapter(new GuideAdapter());
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                int leftMargin = (int) (mPointDis * positionOffset) + position
                        * mPointDis;
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) ivRedPoint
                        .getLayoutParams();
                params.leftMargin = leftMargin;
                ivRedPoint.setLayoutParams(params);
            }

            @Override
            public void onPageSelected(int position) {
                if (position == mImageViewList.size() - 1) {
                    btStart.setVisibility(View.VISIBLE);
                    llPopuLogin.setVisibility(View.VISIBLE);
                    ivRedPoint.setVisibility(View.GONE);
                    llContainer.setVisibility(View.GONE);
                } else {
                    btStart.setVisibility(View.GONE);
                    llPopuLogin.setVisibility(View.GONE);
                    ivRedPoint.setVisibility(View.VISIBLE);
                    llContainer.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        ivRedPoint.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {

                    @Override
                    public void onGlobalLayout() {
                        ivRedPoint.getViewTreeObserver()
                                .removeGlobalOnLayoutListener(this);
                        mPointDis = llContainer.getChildAt(1).getLeft()
                                - llContainer.getChildAt(0).getLeft();
                    }
                });


        tvLoginIdent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                identView();
            }
        });

        btStart.setOnClickListener(v -> {
//            PrefUtils.setBoolean(getApplicationContext(), "is_first_enter", false);
//            startActivity(new Intent(getApplicationContext(), MainActivity.class));
//            finish();
            UIHelper.startActivity(GuideActivity.this,MainActivity.class);
            loginView();
        });

    }


    public void initRecord() {
        mImageViewList = new ArrayList<>();
        for (int i = 0; i < mImageIds.length; i++) {
            ImageView view = new ImageView(this);
            view.setBackgroundResource(mImageIds[i]);
            mImageViewList.add(view);
            point = new ImageView(this);
            point.setImageResource(R.drawable.shape_point_gray);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);

            if (i > 0) {
                params.leftMargin = 25;
            }
            point.setLayoutParams(params);
            llContainer.addView(point);
        }
    }



    /**
     * 获取验证码
     */
    private void identView() {
        etLoginCode.setNextFocusDownId(R.id.et_code);
        String mPhone = etLoginPhone.getText().toString().trim();
        boolean result = PhoneLegal.isPhone(mPhone);
        if (TextUtils.isEmpty(mPhone)) {
            UIHelper.toastMessage(GuideActivity.this, getResources().getString(R.string.tv_input_phone));
            return;
        }
        if (result == false) {
            UIHelper.toastMessage(GuideActivity.this, getResources().getString(R.string.tv_input_phone_erro));
            return;
        } else {
            etLoginCode.requestFocus();
            etLoginCode.getImeOptions();
            RequestParams params = new RequestParams(URLS.USER_SENDCODE);
            params.addBodyParameter("phone", mPhone);
            x.http().post(params, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    time.start();
                    MessageResult message = MessageResult.parse(result);
                    if (message.getCode() == 0) {
                        UIHelper.toastMessage(GuideActivity.this, getResources().getString(R.string.tv_code_success));
                    }else {
                        UIHelper.toastMessage(GuideActivity.this, getResources().getString(R.string.tv_get_code_error));

                    }
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    UIHelper.toastMessage(GuideActivity.this, getResources().getString(R.string.tv_get_code_error));
                }

                @Override
                public void onCancelled(CancelledException cex) {

                }

                @Override
                public void onFinished() {

                }
            });
        }
    }



    /**
     * 登录
     */
    private void loginView() {
        String mPhone = etLoginPhone.getText().toString().trim();
        String mCode = etLoginCode.getText().toString().trim();
        if (mPhone.isEmpty() || mCode.isEmpty()) {
            UIHelper.toastMessage(GuideActivity.this, getResources().getString(R.string.tv_correct_phone_number));
        } else {
            RequestParams params = new RequestParams(URLS.USER_USER_LOGIN);
            params.addBodyParameter("phone", mPhone);
            params.addBodyParameter("code", mCode);
            params.addBodyParameter("source", getString(R.string.user_from));
            params.addBodyParameter("app_num", getString(R.string.appNumber));
            x.http().post(params, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    MessageResult message = MessageResult.parse(result);
                    if (message.getCode() == 0) {
                        UIHelper.toastMessage(GuideActivity.this, getResources().getString(R.string.tv_login_success));
                        UIHelper.startActivity(GuideActivity.this, MainActivity.class);



                    } else {
                        UIHelper.toastMessage(GuideActivity.this, getResources().getString(R.string.tv_code_error));
                    }
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    UIHelper.toastMessage(GuideActivity.this, getResources().getString(R.string.tv_code_error));
                }

                @Override
                public void onCancelled(CancelledException cex) {

                }

                @Override
                public void onFinished() {

                }
            });
        }

    }


    class GuideAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return mImageViewList.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView view = mImageViewList.get(position);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    private class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            tvLoginIdent.setClickable(false);
            tvLoginIdent.setText(millisUntilFinished / 1000 + "秒");
        }

        @Override
        public void onFinish() {
            tvLoginIdent.setText("重发");
            tvLoginIdent.setClickable(true);
        }
    }

}
