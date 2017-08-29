package com.yixun.pettyloan.ui.fragment;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.yixun.pettyloan.R;
import com.yixun.pettyloan.entity.ELChild;
import com.yixun.pettyloan.entity.ELParent;
import com.yixun.pettyloan.ui.base.BaseSupportFragment;
import com.yixun.pettyloan.ui.widge.expandable.ExpandCollapseListener;
import com.yixun.pettyloan.ui.widge.expandable.ExpandableLayout;
import com.yixun.pettyloan.ui.widge.expandable.Section;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoaderInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CompanyProfileFragment extends BaseSupportFragment {
    private String mTitle;
    @BindView(R.id.banner_top)
    Banner mTopBanner;
    @BindView(R.id.el)
    ExpandableLayout sectionLinearLayout;

    public static CompanyProfileFragment getInstance(String title) {
        CompanyProfileFragment sf = new CompanyProfileFragment();
        sf.mTitle = title;
        return sf;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_company_profile;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        initToolbar();
        initExpandableLayout();
    }

    @Override
    protected void initData() {
        bindBanner();
    }

    private void initToolbar() {
    }

    private void initExpandableLayout() {
        sectionLinearLayout.setParentRenderer(new ExpandableLayout.Renderer<ELParent>() {
            @Override
            public void render(View view, ELParent model, boolean isExpanded, int position) {
                ((TextView) view.findViewById(R.id.tv_parent)).setText(model.name);
                view.findViewById(R.id.iv_flag).setBackgroundResource(isExpanded ? R.drawable.icon_circle_minus : R.drawable.icon_circle_plus);
            }
        });

        sectionLinearLayout.setChildRenderer(new ExpandableLayout.Renderer<ELChild>() {
            @Override
            public void render(View view, ELChild model, boolean isExpanded, int position) {
                ((TextView) view.findViewById(R.id.tv_child)).setText(model.name);
            }
        });

        sectionLinearLayout.addSection(getSection());
        sectionLinearLayout.addSection(getSection1());

        sectionLinearLayout.setExpandListener(new ExpandCollapseListener.ExpandListener<ELParent>() {
            @Override
            public void onExpanded(int parentIndex, ELParent parent, View view) {

            }
        });

        sectionLinearLayout.setCollapseListener(new ExpandCollapseListener.CollapseListener<ELParent>() {
            @Override
            public void onCollapsed(int parentIndex, ELParent parent, View view) {

            }
        });
    }

    private void bindBanner() {
        final List<Object> urlList = new ArrayList<>();
        urlList.add(R.drawable.pic_banner_company);
        mTopBanner.setImageLoader(new ImageLoaderInterface() {
            @Override
            public void displayImage(Context context, Object path, View imageView) {
                Glide.with(context.getApplicationContext())
                        .load(path)
                        .into((ImageView) imageView);
            }

            @Override
            public View createImageView(Context context) {
                ImageView imageView = new ImageView(context);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    imageView.setTransitionName(getString(R.string.transition_banner));
                }
                return imageView;
            }
        });
        mTopBanner.setImages(urlList).start();
        mTopBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Toast.makeText(context,"Why did you click me? " + position,Toast.LENGTH_SHORT).show();
            }
        });
    }

    public Section<ELParent, ELChild> getSection() {
        Section<ELParent, ELChild> section = new Section<>();
        String[] elChilds = getResources().getStringArray(R.array.test_company_chronicle_of_events);
        section.parent = new ELParent(getResources().getString(R.string.about_chronicle_of_events));
        for (int i = 0; i < elChilds.length; i++) {
            section.children.add(new ELChild(elChilds[i]));
        }
        return section;
    }
    public Section<ELParent, ELChild> getSection1() {
        Section<ELParent, ELChild> section = new Section<>();
        String[] elChilds = getResources().getStringArray(R.array.test_company_chronicle_of_events);
        section.parent = new ELParent(getResources().getString(R.string.about_company_registration_information));
        for (int i = 0; i < elChilds.length; i++) {
            section.children.add(new ELChild(elChilds[i]));
        }
        return section;
    }

//    @OnClick({R.id.iv_back,R.id.iv_avatar,R.id.tv_about})
//    public void onClick(View view){
//        switch (view.getId()){
//            case R.id.iv_back:
//                pop();
//                break;
//            case R.id.iv_avatar:
//                Toast.makeText(context,"功能开发中...",Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.tv_about:
//                Toast.makeText(context,"功能开发中...",Toast.LENGTH_SHORT).show();
//                break;
//            default:
//                break;
//        }
//    }
}