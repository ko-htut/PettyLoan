package com.yixun.pettyloan.ui.webview.config;

import android.view.View;

public interface IWebPageView {
    void hindProgressBar();// 隐藏进度条
    void showWebView(); // 显示webview
    void hindWebView();// 隐藏webview
    void startProgress(); // 进度条先加载到90%,然后再加载到100%
    void progressChanged(int newProgress);//进度条变化时调用
    void addImageClickListener();//添加js监听
    void fullViewAddView(View view);//播放网络视频全屏调用
    void showVideoFullView();
    void hindVideoFullView();
}
