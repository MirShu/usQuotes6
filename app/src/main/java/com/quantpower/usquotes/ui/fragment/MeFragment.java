package com.quantpower.usquotes.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.quantpower.usquotes.R;
import com.quantpower.usquotes.ui.activity.AboutActivity;
import com.quantpower.usquotes.ui.activity.OpinionActivity;
import com.quantpower.usquotes.ui.activity.ServiceActivity;
import com.quantpower.usquotes.utils.Constants;
import com.quantpower.usquotes.utils.UIHelper;
import com.quantpower.usquotes.widget.loding.LodingDialog;


/**
 * Created by linlin.1016@qq.com on 2017/04/25.
 * Description:
 */

public class MeFragment extends Fragment {
    public static MeFragment newInstance(String s) {
        MeFragment homeFragment = new MeFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.ARGS, s);
        homeFragment.setArguments(bundle);
        return homeFragment;
    }

    private TextView tvMeCollection;
    private TextView tvMeReading;
    private TextView tvMeOpinion;
    private TextView tvMeSetting;
    private TextView tv_huancun;
    private LodingDialog lodingDialog;
    private TextView tvMeGuide;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_person, container, false);
//        tvMeCollection = (TextView) view.findViewById(R.id.tv_me_collection);
        tvMeReading = (TextView) view.findViewById(R.id.tv_me_reading);
        tvMeOpinion = (TextView) view.findViewById(R.id.tv_me_opinion);
        tvMeSetting = (TextView) view.findViewById(R.id.tv_me_setting);
        tv_huancun = (TextView) view.findViewById(R.id.tv_huancun);
        tvMeGuide = (TextView) view.findViewById(R.id.tv_me_guide);
//        tvMeCollection.setOnClickListener(v -> UIHelper.startActivity(getActivity(), CollectionActivity.class));//直播室
        tvMeOpinion.setOnClickListener(v -> UIHelper.startActivity(getActivity(), AboutActivity.class));//关于我们界面
//        tvMeSetting.setOnClickListener(v -> UIHelper.startActivity(getActivity(), SettingActivity.class));
        tvMeGuide.setOnClickListener(view1 -> UIHelper.startActivity(getActivity(), ServiceActivity.class));


        //清除缓存
        tvMeReading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lodingDialog = new LodingDialog(getActivity(), "清除中...");
                lodingDialog.show();
                new Handler().postDelayed(() -> {
                    lodingDialog.dismiss();
                    UIHelper.toastMessage(getActivity(), "暂无缓存");
                    tv_huancun.setText("0.0KB");

                }, 1000);
            }
        });


        //意见反馈
        tvMeSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UIHelper.startActivity(getActivity(), OpinionActivity.class);
            }
        });

        return view;
    }
}
