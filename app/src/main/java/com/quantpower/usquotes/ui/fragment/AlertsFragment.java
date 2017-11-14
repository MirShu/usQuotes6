package com.quantpower.usquotes.ui.fragment;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.andview.refreshview.XRefreshView;
import com.google.gson.Gson;
import com.quantpower.usquotes.R;
import com.quantpower.usquotes.adapter.recycler.RecyclerAdapter;
import com.quantpower.usquotes.adapter.recycler.RecyclerViewHolder;
import com.quantpower.usquotes.constant.URLS;
import com.quantpower.usquotes.model.LiveRoomListModel;
import com.quantpower.usquotes.model.MarketViewRollModel;
import com.quantpower.usquotes.model.MessageResult;
import com.quantpower.usquotes.model.NewsFlashResponse;
import com.quantpower.usquotes.ui.activity.CollectionActivity;
import com.quantpower.usquotes.utils.Constants;
import com.quantpower.usquotes.utils.UIHelper;
import com.quantpower.usquotes.widget.extend.GlideImageLoader;
import com.quantpower.usquotes.widget.loding.LodingDialog;
import com.quantpower.usquotes.widget.scollview.ScrollViewEx;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.transformer.FlipHorizontalTransformer;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

import static android.content.Context.MODE_PRIVATE;
import static com.quantpower.usquotes.R.id.tv_text;


/**
 * Created by linlin.1016@qq.com on 2017/04/25.
 * Description:
 */

public class AlertsFragment extends Fragment {
    private RecyclerAdapter<NewsFlashResponse.DataBean> myOrderReycAdapter;
    private List<NewsFlashResponse.DataBean> myOrderReycList;
    private View rootView;
    private View view_up;
    private View view_upto;
    private LinearLayout ll_up;
    private RecyclerView recyclerView;
    private List<NewsFlashResponse.DataBean> listNewsFlash;
    private List<NewsFlashResponse.DataBean> listNewsFlashNew;
    private RecyclerAdapter<NewsFlashResponse.DataBean> newsFlashAdapter;
    private List<String> showDayList;
    private List<String> showMonthList;
    private RelativeLayout rl_unscroll;
    private XRefreshView xrefreshview;
    private ScrollViewEx scrollViewEx;
    private BGARefreshLayout bgaRefreshLayout;
    private SpannableString spanString;
    private boolean isFirstLoad = true;
    private int readsize = 0;
    private int ordertime = 0;
    private String mday = null;
    private int firstPosition;
    private RelativeLayout rl_empty;
    private RelativeLayout rl_main_viwe;
    private LodingDialog lodingDialog;
    private LinearLayoutManager linearLayoutManager;

    private RecyclerView recycler_market;
    private RecyclerAdapter<MarketViewRollModel> lectuerAdapter;
    private RecyclerAdapter<MarketViewRollModel> marketAdapter;
    private List<MarketViewRollModel> listMarket;

    private List<LiveRoomListModel> listRoomDatas = new ArrayList<>();
    private RecyclerAdapter<LiveRoomListModel> listRoomAdapter;


    public static AlertsFragment newInstance(String s) {
        AlertsFragment homeFragment = new AlertsFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.ARGS, s);
        homeFragment.setArguments(bundle);
        return homeFragment;
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.rootView = inflater.inflate(R.layout.fragment_alerts, container, false);
        this.xrefreshview = (XRefreshView) rootView.findViewById(R.id.xrefreshview);
        this.recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        this.recycler_market = (RecyclerView) rootView.findViewById(R.id.recycler_market);
        myOrderReycList = new ArrayList<>();
        listNewsFlash = new ArrayList<>();
        listNewsFlashNew = new ArrayList<>();
        showDayList = new ArrayList<>();
        showMonthList = new ArrayList<>();
        listMarket = new ArrayList<>();

        lodingDialog = new LodingDialog(getActivity(),"加载中..");
        lodingDialog.show();
        new Handler().postDelayed(() -> lodingDialog.dismiss(),3000);

        readsize = 0;
        ordertime = 0;
        mday = null;
        linearLayoutManager = new LinearLayoutManager(getActivity());
        Log.e("第一个页面 ", "" + "创建来了");
        this.bindRecycleView();
//        bindRecycleViewto();
        this.xRefreshView();

//        banner.setImages(BaseApplication.images)
//                .setImageLoader(new GlideImageLoader())
//                .setBannerTitles(BaseApplication.titles)
//                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
//                .setOnBannerListener(this)
//                .setBannerAnimation(ZoomOutSlideTransformer.class)
//                .start();
        return rootView;
    }


    private void bindRecycleView() {
        /*******************
         * 绑定头条新闻数据
         *******************/
        this.initData();
        markeData();


        this.newsFlashAdapter = new RecyclerAdapter<NewsFlashResponse.DataBean>(this.getActivity(), listNewsFlash,
                R.layout.item_news) {
            @Override
            public void convert(RecyclerViewHolder helper, NewsFlashResponse.DataBean item, int position) {
                TextView tv_time = helper.getView(R.id.tv_time);
                TextView tv_day = helper.getView(R.id.tv_day);
                RelativeLayout rl_day = helper.getView(R.id.rl_day);
                TextView tv_line_up = helper.getView(R.id.tv_line_up);
                TextView tv_line_up_day = helper.getView(R.id.tv_line_up_day);
//                try {

                //判断显示日期的圆圈是否隐藏
                if (showMonthList.get(position) == "0") {
                    rl_day.setVisibility(View.GONE);//隐藏日期显示
                    tv_time.setVisibility(View.VISIBLE);
                    //是否隐藏小时上面的蓝线
                    if (position == 0) {
                        tv_line_up.setVisibility(View.VISIBLE);
                    } else {
                        tv_line_up.setVisibility(View.GONE);
                    }

                } else {
                    rl_day.setVisibility(View.GONE);//显示日期
                    tv_line_up.setVisibility(View.GONE);
                    //是否隐藏day视图上面的蓝线
                    if (position == 0) {
                        tv_line_up_day.setVisibility(View.VISIBLE);
                    } else {
                        tv_line_up_day.setVisibility(View.GONE);
                    }
                }

//                    if (position == 0) {
//                        tv_line_up.setVisibility(View.VISIBLE);
//                    } else {
//                        tv_line_up.setVisibility(View.GONE);
//                    }
                helper.setText(R.id.tv_time, item.getTime());
                String itemContent = item.getContent().replace("*=*", "\n");
                spanString = new SpannableString(itemContent);
                int colorOnStart = itemContent.indexOf(item.getFont_color());
                int bOnStart = itemContent.indexOf(item.getFont_b());
                if (item.getColor_start().equals(false)) {
                    if (item.getB_start().equals(false)) {
                        helper.setText(tv_text, itemContent);
                    } else {
                        spanString.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), bOnStart, bOnStart + item.getFont_b().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }

                } else {
                    if (item.getB_start().equals(false)) {
                        spanString.setSpan(new ForegroundColorSpan(Color.RED), colorOnStart, colorOnStart + item.getFont_color().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    } else {
                        spanString.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), bOnStart, bOnStart + item.getFont_b().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        spanString.setSpan(new ForegroundColorSpan(Color.RED), colorOnStart, colorOnStart + item.getFont_color().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }
                }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
                tv_day.setText(item.getMonth() + "\n" + item.getDay());
                helper.setText(tv_text, spanString);
            }

        };



        this.marketAdapter = new RecyclerAdapter<MarketViewRollModel>(this.getActivity(), listMarket,
                R.layout.item_market) {
            @Override
            public void convert(RecyclerViewHolder helper, MarketViewRollModel item, int position) {
                TextView tv_name = helper.getView(R.id.tv_market_name);
                TextView tv_price = helper.getView(R.id.tv_market_price);
                TextView tv_float = helper.getView(R.id.tv_market_float);

                helper.setText(R.id.tv_market_name, item.getMarket_name());
                helper.setText(R.id.tv_market_price, item.getMarket_price());
                helper.setText(R.id.tv_market_float, item.getMarket_float());
                int size = listMarket.size();

                if (String.valueOf(listMarket.get(position % size).getMarket_float().charAt(0)).equals("-")) {
                    tv_float.setTextColor(Color.parseColor("#019f53"));
                    tv_price.setTextColor(Color.parseColor("#019f53"));
                } else {
                    tv_float.setTextColor(Color.parseColor("#fd040a"));
                    tv_price.setTextColor(Color.parseColor("#fd040a"));
                }

            }

        };


        this.recycler_market.setHasFixedSize(true);
        this.recycler_market.setLayoutManager(new GridLayoutManager(getActivity(), 4, GridLayoutManager.VERTICAL, false));
        this.recycler_market.setAdapter(this.marketAdapter);

        /***************
         * 实时行情数据
         ***************/
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1, linearLayoutManager.VERTICAL, false));
        this.recyclerView.setAdapter(this.newsFlashAdapter);

    }











    //获取行情数据
    private void markeData() {
        RequestParams mparams = new RequestParams(URLS.MARKET_NOWMARKET);
        x.http().get(mparams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                listMarket.clear();
                MessageResult message = MessageResult.parse(result);
                List<MarketViewRollModel> marketList = JSON.parseArray(message.getData(),
                        MarketViewRollModel.class);

                listMarket.addAll(marketList);
                Log.e("---------marketlist--",marketList+"");
//                recycler_market.setLayoutManager(new GridLayoutManager(getActivity(), 1, LinearLayoutManager.HORIZONTAL, false));
//                marketAdapter = new MarqueeView.InnerAdapter(listMarket, getActivity());
//                rcMarketView_up.setAdapter(marketAdapter);
                marketAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {


            }

            @Override
            public void onFinished() {


            }
        });

    }


    /**
     * 刷新机制
     */
    private void xRefreshView() {
        this.xrefreshview.setPullLoadEnable(true);
        this.xrefreshview.setMoveForHorizontal(true);
        this.xrefreshview.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh() {
                listNewsFlash.clear();
                ordertime = 0;
                readsize = 0;
                mday = null;
                initData();
                new Handler().postDelayed(() -> xrefreshview.stopRefresh(), 1000);
            }

            @Override
            public void onLoadMore(boolean isSlience) {
                new Handler().postDelayed(() -> {
                    initData();
                }, 600);
                new Handler().postDelayed(() -> xrefreshview.stopLoadMore(), 1500);
            }

        });
    }


    /*****************
     * 获取头条新闻列表数据
     ****************/
    private void initData() {
        RequestParams params = new RequestParams(URLS.NEWS_LIST);
        params.addBodyParameter("read", String.valueOf(readsize));
        params.addBodyParameter("ordertime", String.valueOf(ordertime));
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                lodingDialog.dismiss();
                Gson gson = new Gson();
                NewsFlashResponse response = gson.fromJson(result, NewsFlashResponse.class);
                if (response.getCode().equals("0")) {
                    List<NewsFlashResponse.DataBean> newsList = response.getData();
                    listNewsFlash.addAll(newsList);
                    ordertime = listNewsFlash.get(0).getOrdertime();
                    readsize = listNewsFlash.size();

                    SimpleDateFormat formatter = new SimpleDateFormat("dd");
                    Date curDate = new Date(System.currentTimeMillis());
                    mday = formatter.format(curDate);
                    //用集合记录需要显示当前天日期的条目
                    showDayList.clear();
                    showMonthList.clear();
                    for (int j = 0; j < listNewsFlash.size(); j++) {
                        showDayList.add("0");
                        showMonthList.add("0");
                    }

                    for (int j = 0; j < listNewsFlash.size(); j++) {
                        if (!listNewsFlash.get(j).getDay().equals(mday)) {
                            showDayList.add(j, listNewsFlash.get(j).getDay());
                            showMonthList.add(j, listNewsFlash.get(j).getMonth());
                            break;
                        }
                    }
                    recyclerView.setLayoutManager(linearLayoutManager);
                    firstPosition = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
                    newsFlashAdapter.notifyDataSetChanged();
                    rl_main_viwe.setVisibility(View.VISIBLE);
                    rl_empty.setVisibility(View.GONE);
                    lodingDialog.dismiss();
                    bgaRefreshLayout.endRefreshing();
                    bgaRefreshLayout.endLoadingMore();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {


            }

            @Override
            public void onFinished() {

            }
        });
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
