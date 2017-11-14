package com.quantpower.usquotes.ui.fragment;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.quantpower.usquotes.R;
import com.quantpower.usquotes.adapter.recycler.RecyclerAdapter;
import com.quantpower.usquotes.adapter.recycler.RecyclerViewHolder;
import com.quantpower.usquotes.ui.activity.CollectionActivity;
import com.quantpower.usquotes.utils.Constants;
import com.quantpower.usquotes.utils.UIHelper;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.quantpower.usquotes.R.styleable.CircleImageView;


/**
 * Created by linlin.1016@qq.com on 2017/04/25.
 * Description:
 */

public class FoundFragment extends Fragment {
    RecyclerView recyclerView;
    private RecyclerAdapter myOrderReycAdapter;
    private List<String> myOrderReycList;
    XRefreshView xrefreshview;
    private View rootView;

    public static FoundFragment newInstance(String s) {
        FoundFragment homeFragment = new FoundFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.ARGS, s);
        homeFragment.setArguments(bundle);
        return homeFragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.rootView = inflater.inflate(R.layout.fragment_found, container, false);
        this.xrefreshview = (XRefreshView) rootView.findViewById(R.id.xrefreshview);
        this.recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        this.bindRecycleView();
        myOrderReycAdapter.notifyDataSetChanged();
        this.xRefreshView();
        return rootView;
    }

    private void bindRecycleView() {
        this.getData();
        this.myOrderReycAdapter = new RecyclerAdapter<String>(getActivity(), myOrderReycList,
                R.layout.item_found) {
            @Override
            public void convert(RecyclerViewHolder helper, String item, int position) {

                Log.e("--------position--",""+position);
                TextView tvState = helper.getView(R.id.tv_name);
//                RelativeLayout rlItemBg = helper.getView(R.id.rl_item_bg);
                TextView tvProductLabel = helper.getView(R.id.tv_product_label);
                TextView tvCenter = helper.getView(R.id.tv_center);
                ImageView imItem = helper.getView(R.id.im_item_bg);
                TextView tvStar = helper.getView(R.id.tv_star);
                de.hdodenhof.circleimageview.CircleImageView imageHead = helper.getView(R.id.iv_head);

                if (position == 0) {
                    tvState.setText("张老师");
                    tvProductLabel.setText("金融泰斗,业界神话");
//                    tvCenter.setText("智能量化指标模型自动操盘");
                    imItem.setImageResource(R.mipmap.bg_crude_index);
                    Resources resources = getContext().getResources();
                    imageHead.setImageResource(R.mipmap.head_one);
                    Drawable btnDrawable = resources.getDrawable(R.mipmap.bg_hang_seng_index);
//                    rlItemBg.setBackgroundDrawable(btnDrawable);
                } else if (position == 1) {
                    tvState.setText("西门老师");
                    tvProductLabel.setText("专职私募操盘手");
//                    tvCenter.setText("非农狙击手，短线暴利建仓");
                    imItem.setImageResource(R.mipmap.bg_gold_index);

                    Resources resources = getContext().getResources();
                    imageHead.setImageResource(R.mipmap.head_two);

                    Drawable btnDrawable = resources.getDrawable(R.mipmap.bg_crude_index);
//                    rlItemBg.setBackgroundDrawable(btnDrawable);
                } else if (position == 2) {
                    tvState.setText("李老师");
                    tvProductLabel.setText("资深讲师,专业讲解");
//                    tvCenter.setText("长线交易，心态致胜");
                    imItem.setImageResource(R.mipmap.bg_hang_seng_index);

                    Resources resources = getContext().getResources();
                    imageHead.setImageResource(R.mipmap.head_three);

                    Drawable btnDrawable = resources.getDrawable(R.mipmap.bg_gold_index);
//                    rlItemBg.setBackgroundDrawable(btnDrawable);
                } else if (position == 3) {
                    tvState.setText("上官老师");
                    tvProductLabel.setText("分析精准,独具慧眼");
                    tvCenter.setText("短线暴利建仓");
                    imItem.setImageResource(R.mipmap.bg_silver_index);
                    Resources resources = getContext().getResources();
                    imageHead.setImageResource(R.mipmap.head_one);

                    Drawable btnDrawable = resources.getDrawable(R.mipmap.bg_silver_index);
//                    rlItemBg.setBackgroundDrawable(btnDrawable);
                }


                tvStar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (tvStar.getText().equals("+关注")){
                            UIHelper.toastMessage(getActivity(),"已关注");
                            tvStar.getBackground().setAlpha(100);
                            tvStar.setText("已关注");
                        }else {
                            UIHelper.toastMessage(getActivity(),"已取消关注");
                            tvStar.getBackground().setAlpha(255);
                            tvStar.setText("+关注");
                        }
                    }
                });

            }
        };

        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1,
                LinearLayoutManager.VERTICAL, false));
        this.recyclerView.setAdapter(this.myOrderReycAdapter);
        this.myOrderReycAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onClick(View parent, int position) {
                UIHelper.startActivity(getActivity(), CollectionActivity.class);
            }
        });

    }

    private void getData() {
        myOrderReycList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            myOrderReycList.add("" + i);
        }
//        Log.e("--------list--",""+myOrderReycList);


//        myOrderReycAdapter.notifyDataSetChanged();

    }

    /**
     * 刷新机制
     */
    private void xRefreshView() {
        this.xrefreshview.setAutoRefresh(true);
        this.xrefreshview.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(() -> xrefreshview.stopRefresh(), 1000);
            }

            @Override
            public void onLoadMore(boolean isSlience) {

            }
        });
    }

}
