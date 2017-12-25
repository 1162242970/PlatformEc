package com.tinno.latte.ec.main.sort.adapter;

import android.support.v7.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tinno.latte.ec.main.sort.content.SectionBean;
import com.tinno.latte.ec.main.sort.content.SectionContentItemEntity;
import com.tinno.latteec.ec.R;

import java.util.List;

/**
 * Created by android on 17-12-20.
 */

public class SectionAdapter extends BaseSectionQuickAdapter<SectionBean, BaseViewHolder>{

    private static final RequestOptions OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .dontAnimate();

    public SectionAdapter(int layoutResId, int sectionHeadResId, List<SectionBean> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    /**
     * 对于头数据的转换
     */
    @Override
    protected void convertHead(BaseViewHolder holder, SectionBean item) {
        holder.setText(R.id.header, item.header);
        //true时显示,false时不显示
        holder.setVisible(R.id.more, item.isMore());
        holder.addOnClickListener(R.id.more);
    }

    @Override
    protected void convert(BaseViewHolder holder, SectionBean item) {
        //item.t返回SectionBean类型
        final String thumb = item.t.getGoodsThumb();
        final String name = item.t.getGoodsName();
        final int goodsId = item.t.getGoodsId();
        final SectionContentItemEntity entity = item.t;

        //设置content的图片和文字
        holder.setText(R.id.tv, name);
        final AppCompatImageView goodsImageView = holder.getView(R.id.iv);
        Glide.with(mContext)
                .load(thumb)
                .apply(OPTIONS)
                .into(goodsImageView);

    }

}
