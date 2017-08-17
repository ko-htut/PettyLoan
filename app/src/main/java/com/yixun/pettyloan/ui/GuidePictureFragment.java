package com.yixun.pettyloan.ui;

import android.animation.AnimatorSet;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.yixun.pettyloan.R;

/**
 * Created by zongkaili on 2017/3/21.
 */
public class GuidePictureFragment extends Fragment implements OnClickListener {
    private TextView mTitleTv, mContentTv;
    private ImageView mBackIv;
    private ImageView mStartIv;

    public interface InitAnimation {
        public AnimatorSet doInit(View bg, View title, View content);
    }

    public static GuidePictureFragment newInstance(int fragId) {
        GuidePictureFragment frag = new GuidePictureFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("fragId", fragId);
        frag.setArguments(bundle);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int fragId = getArguments().getInt("fragId");
        int layoutId = getFragLayout(fragId);
        View rootView = inflater.inflate(layoutId, null);
        initFragLayout(rootView, fragId);
        return rootView;
    }

    private int getFragLayout(int fragId) {
        return R.layout.frag_guide_picture;
    }

    private void initFragLayout(View rootView, int fragId) {
        mBackIv = (ImageView) rootView.findViewById(R.id.iv_guide_picture);
        mStartIv = (ImageView) rootView.findViewById(R.id.iv_guide_btn);
        switch (fragId) {
            case 0:
                mBackIv.setBackgroundResource(R.drawable.pic_guide1);
                break;
            case 1:
                mBackIv.setBackgroundResource(R.drawable.pic_guide2);
                break;
            case 2:
                mBackIv.setBackgroundResource(R.drawable.pic_guide3);
                break;
            case 3:
                mBackIv.setBackgroundResource(R.drawable.pic_guide4);
                mStartIv.setVisibility(View.VISIBLE);
                mStartIv.setOnClickListener(this);
                break;
            default:
                break;
        }
    }

    public void setBackIvVisible() {
        mBackIv.setVisibility(View.VISIBLE);
    }

    public void startAnimationSet(InitAnimation initAnimationCallBack) {
        AnimatorSet animation = initAnimationCallBack.doInit(mBackIv, mTitleTv, mContentTv);
        animation.setInterpolator(new LinearInterpolator());
        animation.start();
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(getActivity(), MainActivity.class));
        getActivity().finish();
    }
}
