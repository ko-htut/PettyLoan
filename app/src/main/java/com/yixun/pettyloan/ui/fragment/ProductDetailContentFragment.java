package com.yixun.pettyloan.ui.fragment;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.TextView;

import com.yixun.pettyloan.AppConfig;
import com.yixun.pettyloan.R;
import com.yixun.pettyloan.event.StartBrotherEvent;
import com.yixun.pettyloan.model.bean.ProductDetailBean;
import com.yixun.pettyloan.presenter.ProductDetailPresenter;
import com.yixun.pettyloan.rx.base.contract.ProductDetailContract;
import com.yixun.pettyloan.ui.base.MvpBaseFragment;
import com.yixun.pettyloan.utils.LogUtils;
import com.yixun.pettyloan.utils.PreferenceUtil;
import com.yixun.pettyloan.utils.TimeUtils;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

public class ProductDetailContentFragment extends MvpBaseFragment<ProductDetailPresenter> implements ProductDetailContract.View {
    private static final String TAG = ProductDetailContentFragment.class.getSimpleName();
    public static final int REQUEST_CODE = 0;
    private ProductDetailBean.Data mode;
    @BindView(R.id.srl_refresh)
    SwipeRefreshLayout mRefresh;
    @BindView(R.id.tv_earning_title)
    TextView mTvCalculator;
    @BindView(R.id.tv_total_amount)
    TextView mTvTotalAmount;

    public static ProductDetailContentFragment getInstance(ProductDetailBean.Data mode) {
        ProductDetailContentFragment sf = new ProductDetailContentFragment();
        sf.mode = mode;
        return sf;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_product_detail_content;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        configRefresh();
    }

    @Override
    protected void initData() {
//        mPresenter.getProductDetail(mode.getProductId());
        mTvTotalAmount.setText(String.valueOf(mode.getTotal_money()));
    }

    private void configRefresh() {
        mRefresh.setColorSchemeColors(ContextCompat.getColor(context, R.color.blue_dark));
        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                updateRefreshStatus();
            }
        });
    }

    public void updateRefreshStatus() {
        mPresenter.getProductDetail(mode.getProduct_id());
    }

    @OnClick({R.id.tv_earning_title, R.id.tv_see_detail, R.id.tv_common_problems, R.id.tv_invest, R.id.tv_bottom_invest})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_earning_title:
                startForResult(ProductDetailCalculatorFragment.getInstance(), REQUEST_CODE);
                break;
            case R.id.tv_see_detail:
                EventBus.getDefault().post(new StartBrotherEvent(ProductIntroduceFragment.getInstance(mode.getPro_name())));
                break;
            case R.id.tv_common_problems:
                EventBus.getDefault().post(new StartBrotherEvent(CommonProblemsFragment.getInstance(mode.getPro_name())));
                break;
            case R.id.tv_invest:
            case R.id.tv_bottom_invest:
                preInvest();
                break;
            default:
                break;
        }
    }

    private void preInvest() {
        long currentTime = System.currentTimeMillis();
        LogUtils.logd(TAG, " currentTime : " + currentTime);
        if (PreferenceUtil.getBoolean(AppConfig.PREFERENCE_IS_LOGGED, false)) {
            if (currentTime < TimeUtils.str2date(mode.getTrade_start_time())) {
                showErrorMsg("交易还未开始");
                return;
            } else if (currentTime > TimeUtils.str2date(mode.getTrade_end_time())) {
                showErrorMsg("交易已经结束");
                return;
            }
            EventBus.getDefault().post(new StartBrotherEvent(PaymentFragment.getInstance(mode)));
        } else {
            EventBus.getDefault().post(new StartBrotherEvent(LoginFragment.newInstance()));
        }
    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {

        }
    }

    @Override
    public boolean onBackPressedSupport() {
        return super.onBackPressedSupport();
    }

    @Override
    public void showContent(ProductDetailBean bean) {
        if (mRefresh.isRefreshing())
            mRefresh.setRefreshing(false);
        if (!bean.isSuccess()) {
            showErrorMsg(bean.getMsg());
            return;
        }
        //TODO
    }

}