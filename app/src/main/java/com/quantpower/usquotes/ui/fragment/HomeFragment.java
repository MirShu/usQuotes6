package com.quantpower.usquotes.ui.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.quantpower.usquotes.R;
import com.quantpower.usquotes.constant.URLS;
import com.quantpower.usquotes.model.MessageResult;
import com.quantpower.usquotes.model.NewsModel;
import com.quantpower.usquotes.ui.activity.MainActivity;
import com.quantpower.usquotes.utils.Constants;
import com.quantpower.usquotes.widget.extend.MarketWebView;
import com.quantpower.usquotes.widget.loding.CustomDialog;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;


/**
 * Created by linlin.1016@qq.com on 2017/04/25.
 * Description:
 */

public class HomeFragment extends Fragment implements MainActivity.FragmentBackHandler {
    private View rootView;
    private MarketWebView market_WebView;
    private NewsModel content;
    private String webviewurl;
    private CustomDialog lodingDialog;
    String menuUrls;
    private String jquery;
    private WebSettings wvSettings;
    private ImageView iv_back_icon;


    public static HomeFragment newInstance(String s) {
        HomeFragment homeFragment = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.ARGS, s);
        homeFragment.setArguments(bundle);
        return homeFragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.rootView = inflater.inflate(R.layout.fragment_home, container, false);
        this.market_WebView = (MarketWebView) rootView.findViewById(R.id.market_WebView);
        this.iv_back_icon = (ImageView) rootView.findViewById(R.id.image_back);
        this.iv_back_icon.setVisibility(View.GONE);
        Log.e("第二个页面 ",""+"创建来了");

        loadJsCod();
        showWebViewData();
        return rootView;
    }

    private void loadJsCod() {
        market_WebView.loadUrl("http://m.zhicheng.com/stock/list/us/1.html");
        jquery = "javascript: "+"$('.phone-top').remove();" +
                "$('.phone-wrapper').css('padding-top','0px');";
//        RequestParams params = new RequestParams(URLS.MARKET_GETMARKET_JS);
//        x.http().post(params, new Callback.CommonCallback<String>() {
//            @Override
//            public void onSuccess(String result) {
//                MessageResult message = MessageResult.parse(result);
//                content = NewsModel.parse(message.getData());
//                webviewurl = content.getWebview();
//
//                new Handler().postDelayed(() -> lodingDialog.dismiss(), 1500);
//            }
//
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//
//            }
//
//            @Override
//            public void onCancelled(CancelledException cex) {
//
//            }
//
//            @Override
//            public void onFinished() {
//
//            }
//        });
    }

    /**
     * 显示行情与隐藏行情上面title标题
     */
    private void showWebViewData() {
        this.market_WebView.setVisibility(View.INVISIBLE);
        lodingDialog = new CustomDialog(getActivity(), "加载中...");
        lodingDialog.show();
        wvSettings = market_WebView.getSettings();
        wvSettings.setJavaScriptEnabled(true);
        wvSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        wvSettings.setAppCacheEnabled(true);
        wvSettings.setDomStorageEnabled(true);
        wvSettings.setDatabaseEnabled(true);
        wvSettings.setAllowFileAccess(true);
        wvSettings.setBlockNetworkImage(true);
        wvSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        wvSettings.setDomStorageEnabled(true);
        market_WebView.setOnLongClickListener(view -> true);
        iv_back_icon.setOnClickListener(v -> {
            market_WebView.goBack();
            market_WebView.setVisibility(View.GONE);
            new Handler().postDelayed(() -> {
                market_WebView.setVisibility(View.VISIBLE);
            }, 1000);
        });

        market_WebView.setOnTouchScreenListener(new MarketWebView.OnTouchScreenListener() {
            @Override
            public void onTouchScreen() {

            }

            @Override
            public void onReleaseScreen() {
            }
        });

        /**
         * 定时执行
         */
        new Thread(() -> {
            int count = 0;
            while (true) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


        market_WebView.setOnScrollChangeListener(new MarketWebView.OnScrollChangeListener() {

            @Override
            public void onScrollChanged(int l, int t, int oldl, int oldt) {
            }

            @Override
            public void onPageTop(int l, int t, int oldl, int oldt) {
            }

            @Override
            public void onPageEnd(int l, int t, int oldl, int oldt) {
            }
        });
        /**
         * WebView 控件webViewClient 所有回调方法
         */
        market_WebView.setWebViewClient(new WebViewClient() {


            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                market_WebView.setVisibility(View.GONE);
                lodingDialog.show();

            }


            /**
             * 加载给定URL资源内容   该方法待使用    判断webview是否可以返回,不能返回就隐藏返回按钮
             */
            @Override
            public void onLoadResource(final WebView view, String url) {
                view.loadUrl(jquery);
                super.onLoadResource(view, url);
            }

            /**
             * 页面加载完成回调方法，获取对应url地址
             * */
            @Override
            public void onPageFinished(final WebView view, String url) {

//                if (market_WebView.canGoBack()){
//                    iv_back_icon.setVisibility(View.VISIBLE);
//                }else {
//                    iv_back_icon.setVisibility(View.GONE);
//                }
                if (market_WebView.canGoBack()){
                    iv_back_icon.setVisibility(View.VISIBLE);
                }else {
                    iv_back_icon.setVisibility(View.GONE);
                }

//
                new Handler().postDelayed(() -> {
                    market_WebView.setVisibility(View.VISIBLE);
                    lodingDialog.dismiss();
                }, 100);
                wvSettings.setBlockNetworkImage(false);
                super.onPageFinished(view, url);
            }

            /**
             * 自己定义加载错误处理效果，比如：TeachCourse定义在没有网络时候，显示一张无网络的图片
             * API 23 之后调用
             */
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                view.setVisibility(View.GONE);
                super.onReceivedError(view, request, error);

            }

            /**
             * 自己定义加载错误处理效果，比如：TeachCourse定义在没有网络时候，显示一张无网络的图片
             *API 23之前调用
             */
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                view.setVisibility(View.GONE);
                super.onReceivedError(view, errorCode, description, failingUrl);
            }
        });
    }

    @Override
    public boolean onBackPressed() {
        try {
            if (menuUrls.indexOf(market_WebView.getUrl()) == -1) {
                market_WebView.goBack();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
