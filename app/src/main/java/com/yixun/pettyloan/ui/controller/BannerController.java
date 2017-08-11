package com.yixun.pettyloan.ui.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yixun.pettyloan.R;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoaderInterface;

import java.util.List;

/**
 * Created by yaolei on 17-8-10.
 */

public class BannerController {
    private Context mContext;
    private Banner mBanner;

    public BannerController(Context context) {
        mContext = context;
    }

    public View initView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.widget_banner, null);
        mBanner = (Banner) view.findViewById(R.id.youth_banner);
        mBanner.setImageLoader(new GlideImageLoader());
        return view;
    }

    public void initImages(List<String> list) {
        mBanner.setImages(list).start();
    }

    private class GlideImageLoader implements ImageLoaderInterface {

        @Override
        public void displayImage(Context context, Object path, View imageView) {
            Glide.with(context.getApplicationContext())
                    .load(path)
                    .crossFade()
                    .into((ImageView) imageView);
        }

        @Override
        public View createImageView(Context context) {
            return new ImageView(context);
        }
    }
}
