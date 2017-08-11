package com.yixun.pettyloan.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.yixun.pettyloan.R;
import com.yixun.pettyloan.ui.base.BaseSupportFragment;

import butterknife.BindView;

/**
 * Created by zongkaili on 17-8-9.
 */
public class RegisterSuccessFragment extends BaseSupportFragment {
    @BindView(R.id.button)
    Button mFinishBtn;

    private OnLoginSuccessListener mOnLoginSuccessListener;

    public static RegisterSuccessFragment newInstance() {

        Bundle args = new Bundle();

        RegisterSuccessFragment fragment = new RegisterSuccessFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnLoginSuccessListener) {
//            mOnLoginSuccessListener = (OnLoginSuccessListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnLoginSuccessListener");
//        }
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_register_success;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        mFinishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop();
            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mOnLoginSuccessListener = null;
    }

    public interface OnLoginSuccessListener {
        void onLoginSuccess(String account);
    }

    @Override
    public void onSupportInvisible() {
        super.onSupportInvisible();
        hideSoftInput();
    }
}
