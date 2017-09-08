package com.yixun.pettyloan.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.yixun.pettyloan.AppConfig;
import com.yixun.pettyloan.R;
import com.yixun.pettyloan.model.bean.CustomerBean;
import com.yixun.pettyloan.presenter.AuthPresenter;
import com.yixun.pettyloan.rx.base.contract.AuthContract;
import com.yixun.pettyloan.ui.base.MvpBaseFragment;
import com.yixun.pettyloan.utils.PreferenceUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zongkaili on 17-9-5.
 */
public class RealnameAuthFragment extends MvpBaseFragment<AuthPresenter> implements AuthContract.View {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.text_input_name)
    TextInputEditText mETRealName;
    @BindView(R.id.text_input_layout_idcard)
    TextInputLayout mTILIdcard;
    @BindView(R.id.text_input_idcard)
    TextInputEditText mTIEIdcard;
    @BindView(R.id.button)
    Button mBtn;

    public static RealnameAuthFragment newInstance() {

        Bundle args = new Bundle();

        RealnameAuthFragment fragment = new RealnameAuthFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_realname_auth;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        toolbar.setTitle(getResources().getString(R.string.setting_realname_authentication));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop();
            }
        });

        mTIEIdcard.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTILIdcard.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void authFinish(CustomerBean bean) {
        if (!bean.isSuccess()) {
            showErrorMsg("认证失败！");
            return;
        }
        Toast.makeText(context, "认证成功", Toast.LENGTH_SHORT).show();
        CustomerBean.Data data = bean.getData();
        if (data == null)
            return;

        PreferenceUtil.put(AppConfig.PREFERENCE_IS_REAL_NAME_AUTH, true);
        PreferenceUtil.put(AppConfig.PREFERENCE_CURRENT_USERNAME, bean.getData().getUsername());
        Bundle bundle = new Bundle();
        bundle.putString(CustomerBean.USERNAME, bean.getData().getUsername());
        setFragmentResult(RESULT_OK, bundle);
        pop();
    }

    @Override
    public void onSupportInvisible() {
        super.onSupportInvisible();
        hideSoftInput();
    }

    @OnClick(R.id.button)
    public void onClick() {
        String realname = mETRealName.getText().toString().trim();
        String idCard = mTIEIdcard.getText().toString().trim();
        if (TextUtils.isEmpty(idCard) || idCard.length() < 3) {
            mTILIdcard.setError("身份证号码输入有误");
            return;
        }
        auth(realname, idCard);
    }

    private void auth(String realname, String idCard) {
        Map<String, Object> map = new HashMap<>();
        map.put(CustomerBean.USERNAME, realname);
        map.put(CustomerBean.ID_CARD, idCard);
        mPresenter.auth(map);
    }
}
