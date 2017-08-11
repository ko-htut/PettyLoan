package com.yixun.pettyloan.ui.fragment;

import android.widget.TextView;

import com.yixun.pettyloan.R;
import com.yixun.pettyloan.ui.base.BaseSupportFragment;

import butterknife.BindView;

public class EntityFragment extends BaseSupportFragment {
    private String mTitle;
    @BindView(R.id.card_title_tv)
    TextView card_title_tv;

    public static EntityFragment getInstance(String title) {
        EntityFragment sf = new EntityFragment();
        sf.mTitle = title;
        return sf;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fr_simple_card;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        card_title_tv.setText(mTitle);
    }
}