package com.yixun.pettyloan.ui.fragment;

import android.content.DialogInterface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.yixun.pettyloan.AppConfig;
import com.yixun.pettyloan.R;
import com.yixun.pettyloan.model.bean.PayBean;
import com.yixun.pettyloan.model.bean.ProductDetailBean;
import com.yixun.pettyloan.presenter.PayPresenter;
import com.yixun.pettyloan.rx.base.contract.PayContract;
import com.yixun.pettyloan.ui.base.MvpBaseFragment;
import com.yixun.pettyloan.utils.LogUtils;
import com.yixun.pettyloan.utils.NumberUtils;
import com.yixun.pettyloan.utils.PreferenceUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class PaymentFragment extends MvpBaseFragment<PayPresenter> implements PayContract.View {
    private ProductDetailBean.Data mode;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_select_way)
    TextView mTvSelectWay;
    @BindView(R.id.iv_add_bank_card_select)
    ImageView mABCSelectIv;
    @BindView(R.id.iv_wechat_pay_select)
    ImageView mWPSelectIv;
    @BindView(R.id.tv_total_amount)
    TextView mTvTotalAmount;
    @BindView(R.id.tv_current_amount)
    TextView mTvCurrentAmount;
    @BindView(R.id.tv_amount)
    TextView mTvAmount;
    @BindView(R.id.text_input_layout)
    TextInputLayout mTILInput;
    @BindView(R.id.text_input)
    TextInputEditText mETInvestAmount;
    @BindView(R.id.checkBox)
    CheckBox mCheckbox;
    @BindView(R.id.tv_sure_pay)
    TextView mTvBtn;

    private double currentAmount;
    private String payWay = "";

    public static PaymentFragment getInstance(ProductDetailBean.Data mode) {
        PaymentFragment sf = new PaymentFragment();
        sf.mode = mode;
        return sf;
    }


    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_pay;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        initToolbar();
        setListener();
        mTvSelectWay.setText(getString(R.string.pay_select_way));
        mTvAmount.setText(getResources().getString(R.string.detail_title_caculator_invest_acounts));
    }

    @Override
    protected void initData() {
        LogUtils.logd(" initData totalmoney: " + mode.getTotal_money() + " saleroom : " + mode.getSaleroom());
        currentAmount = mode.getTotal_money() - mode.getSaleroom();
        mTvTotalAmount.setText(NumberUtils.formatNumber(mode.getTotal_money(), 2) + "元");
        mTvCurrentAmount.setText(NumberUtils.formatNumber(currentAmount, 2) + "元");
        mTILInput.setHint(String.valueOf(NumberUtils.formatNumber(mode.getMin_money(), 2) + "元起投"));
    }

    private void initToolbar() {
        mTvTitle.setText(mode.getPro_name());
    }

    private void setListener() {
        mETInvestAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(s)) {
                    mTvBtn.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_btn_selecter_gray_solid));
                    mTvBtn.setClickable(false);
                } else {
                    mTvBtn.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_btn_selecter_brown_solid));
                    mTvBtn.setClickable(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @OnClick({R.id.iv_back, R.id.ll_add_bank_card_root, R.id.ll_wechat_pay_root, R.id.tv_sure_pay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                pop();
                break;
            case R.id.ll_add_bank_card_root:
                mABCSelectIv.setVisibility(View.VISIBLE);
                mWPSelectIv.setVisibility(View.INVISIBLE);
                payWay = "bank";
                break;
            case R.id.ll_wechat_pay_root:
                mWPSelectIv.setVisibility(View.VISIBLE);
                mABCSelectIv.setVisibility(View.INVISIBLE);
                payWay = "wechat";
                break;
            case R.id.tv_sure_pay:
                doPay();
                break;
            default:
                payWay = "";
                break;
        }
    }

    private void doPay() {
        String amount = mETInvestAmount.getText().toString().trim();
        if (TextUtils.isEmpty(amount) || Integer.valueOf(amount) < mode.getMin_money()) {
            showErrorMsg("投资金额应大于等于" + mode.getMin_money() + "元！");
            return;
        }
        if (Integer.valueOf(amount) > currentAmount) {
            showErrorMsg("当前额度余额不足");
            return;
        }
        if (TextUtils.isEmpty(payWay)) {
            showErrorMsg("请选择支付方式");
            return;
        }
        if (!mCheckbox.isChecked()) {
            showErrorMsg("请同意安全说明");
            return;
        }

        Map<String, Object> map = new HashMap<>();
        map.put(PayBean.CUSTOMER_ID, PreferenceUtil.getInt(AppConfig.PREFERENCE_CUSTOMER_ID, 0));
        map.put(PayBean.ORDER_TYPE, 2);
        map.put(PayBean.PRICE, Integer.valueOf(amount));
        map.put(PayBean.PRODUCT_ID, mode.getProduct_id());
        mPresenter.buy(map);
    }

    @Override
    public void buyFinish(PayBean payBean) {
        if (!payBean.isSuccess()) {
            showErrorMsg("生成订单失败！");
            return;
        }
        showSurePayDialog(payBean.getData().getOrderId());
    }

    private void showSurePayDialog(final String orderId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("确认支付？");
        builder.setPositiveButton("支付", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Map<String, Object> map = new HashMap();
                map.put(PayBean.ORDER_STATUS, 1);
                mPresenter.pay(orderId, map);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    @Override
    public void payFinish(PayBean payBean) {
        if (!payBean.isSuccess()) {
            showErrorMsg("支付失败！");
            return;
        }
        showErrorMsg("支付成功！");
        pop();
    }
}