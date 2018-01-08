package com.tinno.latte.ec.main.sort.adapter;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.tinno.latte.delegates.LatteDelegate;
import com.tinno.latte.ec.main.sort.content.ContentDelegate;
import com.tinno.latte.ec.main.sort.list.SortDelegate;
import com.tinno.latte.ui.recycler.ItemType;
import com.tinno.latte.ui.recycler.MultipleFields;
import com.tinno.latte.ui.recycler.MultipleItemEntity;
import com.tinno.latte.ui.recycler.adapter.MultipleRecyclerAdapter;
import com.tinno.latte.ui.recycler.adapter.MultipleViewHolder;
import com.tinno.latteec.ec.R;

import java.util.List;

import me.yokeyword.fragmentation.SupportHelper;

/**
 * Created by android on 17-12-18.
 */

public class SortRecyclerAdapter extends MultipleRecyclerAdapter {

    private final SortDelegate DELEGATE;
    private int mPrePosition = 0;

    public SortRecyclerAdapter(List<MultipleItemEntity> data, SortDelegate delegate) {
        super(data);
        this.DELEGATE = delegate;
        //添加垂直菜单布局
        addItemType(ItemType.VERTICAL_MENU_LIST, R.layout.item_vertical_menu_list);
    }

    /**
     * 完成分类左侧List的数据设置
     */
    @Override
    protected void convert(final MultipleViewHolder holder, final MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()) {
            case ItemType.VERTICAL_MENU_LIST:
                final String text = entity.getField(MultipleFields.TEXT);
                final boolean isClicked = entity.getField(MultipleFields.TAG);
                final AppCompatTextView name = holder.getView(R.id.tv_vertical_item_name);
                final View line = holder.getView(R.id.view_line);
                final View itemView = holder.itemView;
                //设置整个item的监听
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final int currentPosition = holder.getAdapterPosition();
                        //还原上一个
                        getData().get(mPrePosition).setField(MultipleFields.TAG, false);
                        notifyItemChanged(mPrePosition);

                        //更新选中的item
                        entity.setField(MultipleFields.TAG, true);
                        notifyItemChanged(currentPosition);
                        mPrePosition = currentPosition;

                        final int contentId = getData().get(currentPosition).getField(MultipleFields.ID);
                        //显示右侧的content布局
                        showContent(contentId);

                    }
                });
                if (!isClicked) {
                    line.setVisibility(View.INVISIBLE);
                    name.setTextColor(ContextCompat.getColor(mContext, R.color.weChat_black));
                    itemView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.item_background));
                } else {
                    //设置被点击时item的边框和颜色
                    line.setVisibility(View.VISIBLE);
                    name.setTextColor(ContextCompat.getColor(mContext, R.color.app_main));
                    itemView.setBackgroundColor(Color.WHITE);
                }
                holder.setText(R.id.tv_vertical_item_name, text);
                break;
            default:
                break;
        }
    }

    /**
     * 展示content
     */

    private void showContent(int contentId) {
        final ContentDelegate delegate = ContentDelegate.newInstance(contentId);
        switchContent(delegate);
    }

    /**
     * 替换content
     */
    private void switchContent(ContentDelegate delegate) {
        final LatteDelegate contentDelegate =
                SupportHelper.findFragment(DELEGATE.getChildFragmentManager(), ContentDelegate.class);
        if (contentDelegate != null) {
            contentDelegate.getSupportDelegate().replaceFragment(delegate, false);
        }
    }
}
