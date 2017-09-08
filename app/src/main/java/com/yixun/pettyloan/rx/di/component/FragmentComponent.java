package com.yixun.pettyloan.rx.di.component;

import android.app.Activity;

import com.yixun.pettyloan.rx.di.module.FragmentModule;
import com.yixun.pettyloan.rx.di.scope.FragmentScope;
import com.yixun.pettyloan.ui.fragment.EntityChildOneFragment;
import com.yixun.pettyloan.ui.fragment.EntityChildTwoFragment;
import com.yixun.pettyloan.ui.fragment.EntityFragment;
import com.yixun.pettyloan.ui.fragment.HomeFragment;
import com.yixun.pettyloan.ui.fragment.InvestFragment;
import com.yixun.pettyloan.ui.fragment.LoginFragment;
import com.yixun.pettyloan.ui.fragment.MediaReportFragment;
import com.yixun.pettyloan.ui.fragment.MineFragment;
import com.yixun.pettyloan.ui.fragment.MyMessageFragment;
import com.yixun.pettyloan.ui.fragment.MySettingFragment;
import com.yixun.pettyloan.ui.fragment.PaymentFragment;
import com.yixun.pettyloan.ui.fragment.ProductDetailContentFragment;
import com.yixun.pettyloan.ui.fragment.ProductDetailFragment;
import com.yixun.pettyloan.ui.fragment.RealnameAuthFragment;
import com.yixun.pettyloan.ui.fragment.RechargeFragment;
import com.yixun.pettyloan.ui.fragment.RegisterFragment;
import com.yixun.pettyloan.ui.fragment.TradingRecordChildFragment;
import com.yixun.pettyloan.ui.fragment.WithdrawFragment;

import dagger.Component;

/**
 * Created by zongkaili on 2017/8/28.
 */
@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    Activity getActivity();

    void inject(LoginFragment loginFragment);

    void inject(MySettingFragment mySettingFragment);

    void inject(RegisterFragment registerFragment);

    void inject(HomeFragment homeFragment);

    void inject(InvestFragment investFragment);

    void inject(EntityFragment entityFragment);

    void inject(MineFragment mineFragment);

    void inject(EntityChildOneFragment entityFragment);

    void inject(EntityChildTwoFragment entityFragment);

    void inject(PaymentFragment paymentFragment);

    void inject(TradingRecordChildFragment tradingRecordChildFragment);

    void inject(RechargeFragment rechargeFragment);

    void inject(WithdrawFragment withdrawFragment);

    void inject(ProductDetailFragment productDetailFragment);

    void inject(ProductDetailContentFragment productDetailContentFragment);

    void inject(MyMessageFragment myMessageFragment);

    void inject(RealnameAuthFragment realnameAuthFragment);

    void inject(MediaReportFragment mediaReportFragment);

}
