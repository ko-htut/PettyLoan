package com.yixun.pettyloan.ui.fragment;

import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.kelin.translucentbar.library.TranslucentBarManager;
import com.yixun.pettyloan.R;
import com.yixun.pettyloan.model.bean.ProductDetailBean;
import com.yixun.pettyloan.presenter.ProductDetailPresenter;
import com.yixun.pettyloan.rx.base.contract.ProductDetailContract;
import com.yixun.pettyloan.ui.base.MvpBaseFragment;
import com.yixun.pettyloan.utils.NumberUtils;

import butterknife.BindView;

public class ProductDetailFragment extends MvpBaseFragment<ProductDetailPresenter> implements ProductDetailContract.View {
    private ProductDetailBean.Data mode;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tv_mine_title_tip_top_start)
    TextView mTvRateStart;
    @BindView(R.id.tv_mine_title_tip_top_end)
    TextView mTvRateEnd;

    public static ProductDetailFragment getInstance(ProductDetailBean.Data mode) {
        ProductDetailFragment sf = new ProductDetailFragment();
        sf.mode = mode;
        return sf;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_product_detail;
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

    @Override
    protected void initData() {
        mPresenter.getProductDetail(mode.getProduct_id());
    }

    private void initToolbar() {
        mToolbar.setTitle(mode.getPro_name());
        mToolbar.inflateMenu(R.menu.tool_bar_menu_product_detail);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop();
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

    @Override
    public void showContent(ProductDetailBean bean) {
        if (!bean.isSuccess()) {
            showErrorMsg(bean.getMsg());
            return;
        }
        if (bean.getData() == null)
            return;
        mTvRateStart.setText(String.valueOf(NumberUtils.keepDecimalPlaces(bean.getData().getRate(), 2) - 2));
        mTvRateEnd.setText(String.valueOf(NumberUtils.keepDecimalPlaces(bean.getData().getRate(), 2) + 2));
        loadRootFragment(R.id.fl_container, ProductDetailContentFragment.getInstance(bean.getData()));
    }
}