package com.yixun.pettyloan.ui.fragment;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.yixun.pettyloan.AppConfig;
import com.yixun.pettyloan.R;
import com.yixun.pettyloan.model.bean.RechargeBean;
import com.yixun.pettyloan.presenter.RechargePresenter;
import com.yixun.pettyloan.rx.base.contract.RechargeContract;
import com.yixun.pettyloan.ui.base.MvpBaseFragment;
import com.yixun.pettyloan.utils.NumberUtils;
import com.yixun.pettyloan.utils.PreferenceUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class WithdrawFragment extends MvpBaseFragment<RechargePresenter> implements RechargeContract.View {
    private String mTitle;
    private float mAvailableAssets;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tv_amount)
    TextView mTvAmount;
    @BindView(R.id.tv_current_amount)
    TextView mTvCurrentAmount;
    @BindView(R.id.text_input_layout)
    TextInputLayout mTILInput;
    @BindView(R.id.text_input)
    TextInputEditText mETWithdrawAmount;
    @BindView(R.id.tv_sure_pay)
    TextView mTvBtn;

    public static WithdrawFragment getInstance(String title, float assets) {
        WithdrawFragment sf = new WithdrawFragment();
        sf.mTitle = title;
        sf.mAvailableAssets = assets;
        return sf;
    }


    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_withdraw;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        initToolbar();
        setListener();
    }

    @Override
    protected void initData() {
        mTvCurrentAmount.setText(NumberUtils.formatNumber(mAvailableAssets, 2) + "元");
    }

    private void initToolbar() {
        mToolbar.setTitle(mTitle);
        mToolbar.inflateMenu(R.menu.tool_bar_menu_recharge);
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
        mTvAmount.setText(getResources().getString(R.string.withdraw_amount));
        mTILInput.setHint(getResources().getString(R.string.withdraw_amount_input_hint));
        mTvBtn.setText(getResources().getString(R.string.withdraw_sure));
    }

    private void setListener() {
        mETWithdrawAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(s)) {
                    mTvBtn.setBackground(getResources().getDrawable(R.drawable.bg_btn_selecter_gray_solid, null));
                    mTvBtn.setClickable(false);
                } else {
                    mTvBtn.setBackground(getResources().getDrawable(R.drawable.bg_btn_selecter_brown_solid, null));
                    mTvBtn.setClickable(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @OnClick({R.id.tv_sure_pay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_sure_pay:
                withdraw();
                break;
            default:
                break;
        }
    }

    private void withdraw() {
        String amount = mETWithdrawAmount.getText().toString().trim();
        if (TextUtils.isEmpty(amount)) {
            showErrorMsg("提现金额不能为空");
            return;
        }

        if (Integer.valueOf(amount) > mAvailableAssets) {
            showErrorMsg("提现金额不能大于可用余额");
            return;
        }
        Map<String, Object> map = new HashMap<>();
        map.put(RechargeBean.CUSTOMER_ID, PreferenceUtil.getInt(AppConfig.PREFERENCE_CUSTOMER_ID, 0));
        map.put(RechargeBean.ORDER_TYPE, 0);
        map.put(RechargeBean.PRICE, Float.valueOf(amount));
        mPresenter.rechangeOrWithdraw(map);
    }

    @Override
    public void rechangeOrWithdrawFinish(RechargeBean bean) {
        if (!bean.isSuccess()) {
            showErrorMsg("提现失败！");
            return;
        }
        showErrorMsg("提现成功！");
        PreferenceUtil.put(AppConfig.PREFERENCE_AVAILABLE_ASSETS, mAvailableAssets - Float.valueOf(mETWithdrawAmount.getText().toString().trim()));
        mTvCurrentAmount.setText(NumberUtils.formatNumber(PreferenceUtil.getFloat(AppConfig.PREFERENCE_AVAILABLE_ASSETS, 0), 2) + "元");
        mETWithdrawAmount.setText("");
        pop();
    }
}