package com.yixun.pettyloan.ui.fragment;

import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.kelin.translucentbar.library.TranslucentBarManager;
import com.yixun.pettyloan.R;
import com.yixun.pettyloan.ui.base.BaseSupportFragment;

import butterknife.BindView;

public class MineFragment extends BaseSupportFragment {
    private String mTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    public static MineFragment getInstance(String title) {
        MineFragment sf = new MineFragment();
        sf.mTitle = title;
        return sf;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_about;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        TranslucentBarManager translucentBarManager = new TranslucentBarManager(this);
        translucentBarManager.translucent(this, rootView, R.color.text_blue);

        initToolbar();
    }

    private void initToolbar() {
        mToolbar.inflateMenu(R.menu.tool_bar_menu_mine);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "功能开发中...", Toast.LENGTH_SHORT).show();
            }
        });
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.sign:
                        Toast.makeText(getContext(), "功能开发中...", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });

    }
}