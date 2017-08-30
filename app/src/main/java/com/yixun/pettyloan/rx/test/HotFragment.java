//package com.yixun.pettyloan.rx.test;
//
//import android.app.ActivityOptions;
//import android.content.Intent;
//import android.support.v4.widget.SwipeRefreshLayout;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.view.View;
//
//import com.yixun.pettyloan.rx.base.contract.HotContract;
//import com.yixun.pettyloan.R;
//import com.yixun.pettyloan.model.bean.HotListBean;
//import com.yixun.pettyloan.presenter.HotPresenter;
//import com.yixun.pettyloan.ui.base.MvpBaseFragment;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import butterknife.BindView;
//
///**
// * Created by codeest on 2016/8/11.
// */
//public class HotFragment extends MvpBaseFragment<HotPresenter> implements HotContract.View {
//
//    @BindView(R.id.view_main)
//    RecyclerView rvHotContent;
//    @BindView(R.id.swipe_refresh)
//    SwipeRefreshLayout swipeRefresh;
//
//    List<HotListBean.RecentBean> mList;
//    HotAdapter mAdapter;
//
//    @Override
//    protected void initInject() {
//        getFragmentComponent().inject(this);
//    }
//
//    @Override
//    protected int getLayoutId() {
//        return R.layout.view_common_list;
//    }
//
//    @Override
//    protected void initEventAndData() {
//        super.initEventAndData();
//        mList = new ArrayList<>();
//        stateLoading();
//        mAdapter = new HotAdapter(mContext,mList);
//        rvHotContent.setVisibility(View.INVISIBLE);
//        rvHotContent.setLayoutManager(new LinearLayoutManager(mContext));
//        rvHotContent.setAdapter(mAdapter);
//        mPresenter.getHotData();
//        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                mPresenter.getHotData();
//            }
//        });
//        mAdapter.setOnItemClickListener(new HotAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(int position, View shareView) {
//                mPresenter.insertReadToDB(mList.get(position).getNews_id());
//                mAdapter.setReadState(position,true);
//                mAdapter.notifyItemChanged(position);
//                Intent intent = new Intent();
//                intent.setClass(mContext, ZhihuDetailActivity.class);
//                intent.putExtra(Constants.IT_ZHIHU_DETAIL_ID, mList.get(position).getNews_id());
//                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(mActivity, shareView, "shareView");
//                mContext.startActivity(intent,options.toBundle());
//            }
//        });
//    }
//
//    @Override
//    public void stateError() {
//        super.stateError();
//        if(swipeRefresh.isRefreshing()) {
//            swipeRefresh.setRefreshing(false);
//        }
//    }
//
//    @Override
//    public void showContent(HotListBean hotListBean) {
//        if (swipeRefresh.isRefreshing()) {
//            swipeRefresh.setRefreshing(false);
//        }
//        stateMain();
//        rvHotContent.setVisibility(View.VISIBLE);
//        mList.clear();
//        mList.addAll(hotListBean.getRecent());
//        mAdapter.notifyDataSetChanged();
//    }
//
//    @Override
//    protected int getLayoutResource() {
//        return 0;
//    }
//
//    @Override
//    public void initPresenter() {
//
//    }
//
//    @Override
//    protected void initView() {
//
//    }
//
//    @Override
//    protected void initData() {
//
//    }
//}
