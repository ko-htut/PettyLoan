package com.yixun.pettyloan.ui.fragment;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.kelin.translucentbar.library.TranslucentBarManager;
import com.yixun.pettyloan.AppConfig;
import com.yixun.pettyloan.R;
import com.yixun.pettyloan.event.StartBrotherEvent;
import com.yixun.pettyloan.model.bean.CustomerBean;
import com.yixun.pettyloan.presenter.CustomerPresenter;
import com.yixun.pettyloan.rx.base.contract.CustomerContract;
import com.yixun.pettyloan.ui.base.MvpBaseFragment;
import com.yixun.pettyloan.utils.LogUtils;
import com.yixun.pettyloan.utils.NumberUtils;
import com.yixun.pettyloan.utils.PreferenceUtil;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

public class MineFragment extends MvpBaseFragment<CustomerPresenter> implements CustomerContract.View {
    private static final String TAG = MineFragment.class.getSimpleName();
    private String mTitle;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout mToolbarLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.srl_refresh)
    SwipeRefreshLayout mRefresh;
    @BindView(R.id.tv_total_assets)
    TextView mTvTotalAssets;
    @BindView(R.id.tv_available_assets)
    TextView mTvAvailableAssets;

    private CustomerBean.Data mCustomerInfo;

    public static MineFragment getInstance(String title) {
        MineFragment sf = new MineFragment();
        sf.mTitle = title;
        return sf;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        TranslucentBarManager translucentBarManager = new TranslucentBarManager(this);
        translucentBarManager.translucent(this, rootView, R.color.blue_medium);

        initToolbar();

        configRefresh();
    }

    @Override
    protected void initData() {
        mTvTotalAssets.setText(NumberUtils.formatNumber(PreferenceUtil.getFloat(AppConfig.PREFERENCE_TOTAL_ASSETS, 0f), 2));
        mTvAvailableAssets.setText(NumberUtils.formatNumber(PreferenceUtil.getFloat(AppConfig.PREFERENCE_AVAILABLE_ASSETS, 0f), 2));
    }

    private void initToolbar() {
        mToolbar.setTitle(PreferenceUtil.getString(AppConfig.PREFERENCE_CURRENT_USERNAME, getString(R.string.not_login)));
        mToolbar.inflateMenu(R.menu.tool_bar_menu_mine);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!verifyLogin()) return;
                EventBus.getDefault().post(new StartBrotherEvent(MySettingFragment.getInstance("我的设置")));
            }
        });
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.sign:
                        EventBus.getDefault().post(new StartBrotherEvent(MyMessageFragment.getInstance("我的消息")));
                        break;
                }
                return false;
            }
        });

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
        if (PreferenceUtil.getBoolean(AppConfig.PREFERENCE_IS_LOGGED, false)) {
            mPresenter.getCustomerInfo();
        } else {
            mRefresh.setRefreshing(false);
            showErrorMsg("请登录后，再刷新");
            mToolbarLayout.setTitle(PreferenceUtil.getString(AppConfig.PREFERENCE_CURRENT_USERNAME, getString(R.string.not_login)));
            mTvTotalAssets.setText(NumberUtils.formatNumber(PreferenceUtil.getFloat(AppConfig.PREFERENCE_TOTAL_ASSETS, 0f), 2));
            mTvAvailableAssets.setText(NumberUtils.formatNumber(PreferenceUtil.getFloat(AppConfig.PREFERENCE_AVAILABLE_ASSETS, 0f), 2));
        }
    }

    @OnClick({R.id.rl_member_center, R.id.ll_total_assets, R.id.tv_withdraw, R.id.tv_recharge, R.id.ll_account_bill, R.id.tv_record, R.id.tv_my_invitation, R.id.tv_my_credits,
            R.id.ll_coupon})
    public void onClick(View view) {
        switch (view.getId()) {
//            case R.id.rl_member_center:
//                EventBus.getDefault().post(new StartBrotherEvent(LoginFragment.newInstance()));
//                break;
            case R.id.ll_total_assets:
                if (!verifyLogin()) break;
                EventBus.getDefault().post(new StartBrotherEvent(TotalAssetsFragment.getInstance("总资产")));
                break;
            case R.id.tv_withdraw:
                if (!verifyLogin()) break;
                EventBus.getDefault().post(new StartBrotherEvent(WithdrawFragment.getInstance("提现", PreferenceUtil.getFloat(AppConfig.PREFERENCE_AVAILABLE_ASSETS, 0))));
                break;
            case R.id.tv_recharge:
                if (!verifyLogin()) break;
                EventBus.getDefault().post(new StartBrotherEvent(RechargeFragment.getInstance("充值", PreferenceUtil.getFloat(AppConfig.PREFERENCE_AVAILABLE_ASSETS, 0))));
                break;
            case R.id.ll_account_bill:
                if (!verifyLogin()) break;
                EventBus.getDefault().post(new StartBrotherEvent(TotalAssetsFragment.getInstance("我的账单")));
                break;
            case R.id.tv_record:
                if (!verifyLogin()) break;
                EventBus.getDefault().post(new StartBrotherEvent(TradingRecordFragment.getInstance("交易记录")));
                break;
            case R.id.tv_my_invitation:
                if (!verifyLogin()) break;
                EventBus.getDefault().post(new StartBrotherEvent(MyInvitationFragment.getInstance("我的邀请")));
                break;
            case R.id.tv_my_credits:
                if (!verifyLogin()) break;
                EventBus.getDefault().post(new StartBrotherEvent(MyCreditsFragment.getInstance("我的积分")));
                break;
            case R.id.ll_coupon:
                if (!verifyLogin()) break;
                EventBus.getDefault().post(new StartBrotherEvent(CouponsPackageFragment.getInstance("券包")));
                break;
            default:
                Toast.makeText(context, "功能开发中...", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private boolean verifyLogin() {
        if (!PreferenceUtil.getBoolean(AppConfig.PREFERENCE_IS_LOGGED, false)) {
            EventBus.getDefault().post(new StartBrotherEvent(LoginFragment.newInstance()));
            return false;
        }
        return true;
    }

    @Override
    public void stateError(Throwable e) {
        super.stateError(e);
        if (mRefresh.isRefreshing())
            mRefresh.setRefreshing(false);
    }

    @Override
    public void showContent(CustomerBean customerBean) {
        if (mRefresh.isRefreshing())
            mRefresh.setRefreshing(false);
        if (!customerBean.isSuccess()) {
            showErrorMsg("获取用户信息失败！");
            return;
        }
        mCustomerInfo = customerBean.getData();
        if (mCustomerInfo == null)
            return;
        mToolbarLayout.setTitle(mCustomerInfo.getUsername());
        mTvTotalAssets.setText(NumberUtils.formatNumber(mCustomerInfo.getTotal_assets(), 2));
        mTvAvailableAssets.setText(NumberUtils.formatNumber(mCustomerInfo.getAvailable_assets(), 2));
        PreferenceUtil.put(AppConfig.PREFERENCE_CURRENT_USERNAME, mCustomerInfo.getUsername());
        PreferenceUtil.put(AppConfig.PREFERENCE_TOTAL_ASSETS, (float) mCustomerInfo.getTotal_assets());
        PreferenceUtil.put(AppConfig.PREFERENCE_AVAILABLE_ASSETS, (float) mCustomerInfo.getAvailable_assets());
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        LogUtils.logd(TAG, " onSupportVisible : ");
        if (PreferenceUtil.getBoolean(AppConfig.PREFERENCE_IS_LOGGED, false)) {
            mPresenter.getCustomerInfo();
        } else {
            mToolbarLayout.setTitle(PreferenceUtil.getString(AppConfig.PREFERENCE_CURRENT_USERNAME, getString(R.string.not_login)));
            mTvTotalAssets.setText(NumberUtils.formatNumber(PreferenceUtil.getFloat(AppConfig.PREFERENCE_TOTAL_ASSETS, 0f), 2));
            mTvAvailableAssets.setText(NumberUtils.formatNumber(PreferenceUtil.getFloat(AppConfig.PREFERENCE_AVAILABLE_ASSETS, 0f), 2));
        }
    }

    @Override
    public void onSupportInvisible() {
        super.onSupportInvisible();
        LogUtils.logd(TAG, " onSupportInvisible : ");
        if (mRefresh.isRefreshing())
            mRefresh.setRefreshing(false);
    }
}