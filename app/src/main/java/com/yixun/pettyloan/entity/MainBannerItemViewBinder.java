package com.yixun.pettyloan.entity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yixun.pettyloan.R;
import com.yixun.pettyloan.adapter.multitype.ItemViewBinder;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoaderInterface;

/**
 * Created by zongkaili on 17-8-25.
 */
public class MainBannerItemViewBinder extends ItemViewBinder<BannerFeed, MainBannerItemViewBinder.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(
            @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_main_feed_banner, parent, false);
        return new ViewHolder(view);
    }


    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull BannerFeed data) {
        holder.setData(data);
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        private @NonNull
        final Banner banner;


        ViewHolder(@NonNull View itemView) {
            super(itemView);
            banner = (Banner) itemView.findViewById(R.id.banner_top);
        }

        private void setData(BannerFeed data){
            banner.setImageLoader(new ImageLoaderInterface() {
                @Override
                public void displayImage(Context context, Object path, View imageView) {
                    Glide.with(context.getApplicationContext())
                            .load(path)
                            .into((ImageView) imageView);
                }

                @Override
                public View createImageView(Context context) {
                    return new ImageView(context);
                }
            });
            banner.setImages(data.urlList).start();
        }
    }
}
