package com.yixun.pettyloan.ui.fragment;

import android.view.View;
import com.yixun.pettyloan.R;
import com.yixun.pettyloan.ui.base.BaseSupportFragment;

import butterknife.OnClick;

public class ProductDetailCalculatorFragment extends BaseSupportFragment {
    public static ProductDetailCalculatorFragment getInstance() {
        ProductDetailCalculatorFragment sf = new ProductDetailCalculatorFragment();
        return sf;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_product_detail_caculator;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initData() {
    }

    @OnClick(R.id.iv_close)
    public void onClick(View view){
        switch (view.getId()){
            case R.id.iv_close:
                pop();
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onBackPressedSupport() {
        pop();
        return true;
    }
}