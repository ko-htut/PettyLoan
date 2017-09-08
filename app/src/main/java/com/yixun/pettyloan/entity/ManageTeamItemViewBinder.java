package com.yixun.pettyloan.entity;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kelly.expandablelayout.ExpandableLayout;
import com.kelly.expandablelayout.ExpandableLayoutListenerAdapter;
import com.kelly.expandablelayout.ExpandableLinearLayout;
import com.kelly.expandablelayout.Utils;
import com.yixun.pettyloan.R;
import com.yixun.pettyloan.adapter.multitype.ItemViewBinder;

/**
 * Created by zongkaili on 17-8-11.
 */
public class ManageTeamItemViewBinder extends ItemViewBinder<ManageTeam, ManageTeamItemViewBinder.ViewHolder> {
    private Context context;
    private SparseBooleanArray expandState = new SparseBooleanArray();

    public ManageTeamItemViewBinder(Context context) {
        this.context = context;
    }

    public void initeEpandState() {
        for (int i = 0; i < getAdapter().getItemCount(); i++) {
            expandState.append(i, false);
        }
    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(
            @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_management_team, parent, false);
        initeEpandState();
        return new ViewHolder(context, view, expandState);
    }


    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull ManageTeam mode) {
        holder.setData(mode);
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        private Context context;
        private
        @NonNull
        TextView tvName, tvDuty, tvIntro;
        private
        @NonNull
        ExpandableLinearLayout expandableLayout;
        private
        @NonNull
        ImageView ivSwitcher;
        SparseBooleanArray expandState;

        ViewHolder(Context context, @NonNull View itemView, SparseBooleanArray expandState) {
            super(itemView);
            this.context = context;
            this.expandState = expandState;
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvDuty = (TextView) itemView.findViewById(R.id.tv_duty);
            tvIntro = (TextView) itemView.findViewById(R.id.tv_introduction);
            ivSwitcher = (ImageView) itemView.findViewById(R.id.iv_switch);
            expandableLayout = (ExpandableLinearLayout) itemView.findViewById(R.id.expandableLayout);
        }


        void setData(@NonNull final ManageTeam mode) {
            tvName.setText(mode.name);
            tvDuty.setText(mode.duty);
            tvIntro.setText(mode.introduction);
            expandableLayout.setInRecyclerView(true);
            Log.d(" setTradeRecords() : ", " isExpanded : " + expandState.get(getAdapterPosition()));
            expandableLayout.setInterpolator(mode.interpolator);
            expandableLayout.setExpanded(expandState.get(getAdapterPosition()));
            expandableLayout.setListener(new ExpandableLayoutListenerAdapter() {
                @Override
                public void onPreOpen() {
                    createRotateAnimator(ivSwitcher, 0f, 180f).start();
                    expandState.put(getAdapterPosition(), true);
                    ivSwitcher.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_circle_minus));
                }

                @Override
                public void onPreClose() {
                    createRotateAnimator(ivSwitcher, 180f, 0f).start();
                    expandState.put(getAdapterPosition(), false);
                    ivSwitcher.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_circle_plus));
                }
            });

            ivSwitcher.setRotation(expandState.get(getAdapterPosition()) ? 180f : 0f);
            ivSwitcher.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    onClickButton(expandableLayout);
                }
            });
        }

        private void onClickButton(final ExpandableLayout expandableLayout) {
            expandableLayout.toggle();
        }

        public ObjectAnimator createRotateAnimator(final View target, final float from, final float to) {
            ObjectAnimator animator = ObjectAnimator.ofFloat(target, "rotation", from, to);
            animator.setDuration(300);
            animator.setInterpolator(Utils.createInterpolator(Utils.LINEAR_INTERPOLATOR));
            return animator;
        }

    }
}
