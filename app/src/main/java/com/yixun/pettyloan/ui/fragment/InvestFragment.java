package com.yixun.pettyloan.ui.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.yixun.pettyloan.R;
import com.yixun.pettyloan.ui.base.BaseSupportFragment;
import com.yixun.pettyloan.ui.controller.BannerController;
import com.yixun.pettyloan.ui.controller.TabGoodsController;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class InvestFragment extends BaseSupportFragment {
    private BannerController mBannerController;
    private TabGoodsController mGoodsController;
    @BindView(R.id.ll_invest_parent)
    LinearLayout mParentLl;

    public static InvestFragment getInstance(String title) {
        InvestFragment sf = new InvestFragment();
        return sf;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_invest;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        mBannerController = new BannerController(getActivity());
        View bannerView = mBannerController.initView();
        mParentLl.addView(bannerView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                (int) getResources().getDimension(R.dimen.banner_height)));
        List<String> urlList = new ArrayList<>();
        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502364345401&di=c973f24fda4b0e74d9d233efd0a34dd3&imgtype=0&src=http%3A%2F%2Fimg3.duitang.com%2Fuploads%2Fitem%2F201608%2F10%2F20160810204650_8sQuF.jpeg");
        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502364364886&di=48c237b56ad5126d60aac44b6d35c6ac&imgtype=jpg&src=http%3A%2F%2Fimg0.imgtn.bdimg.com%2Fit%2Fu%3D1038163141%2C2794573076%26fm%3D214%26gp%3D0.jpg");
        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502364345401&di=a137348f727c523a65936c60d6a6b6cb&imgtype=0&src=http%3A%2F%2Fimg3.duitang.com%2Fuploads%2Fitem%2F201605%2F26%2F20160526175959_xRWTH.thumb.700_0.jpeg");
        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502364345401&di=f101d8d3cc8abfed11a2ad6584a30df4&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20160907%2FImg467862638.jpg");
        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502364345400&di=9812520307a97a517568e690257da493&imgtype=0&src=http%3A%2F%2Fimg.ladywu.net%2F2017%2F0704%2F20170704021437678.jpg");
        mBannerController.initImages(urlList);

        mGoodsController = new TabGoodsController(getActivity());
        View goodsView = mGoodsController.initView();
        mParentLl.addView(goodsView);
        List<String> typeList = new ArrayList<>();
        typeList.add("投资理财");
        mGoodsController.initTabs(getActivity().getSupportFragmentManager(), typeList);
    }

    @Override
    protected void initData() {

    }
}