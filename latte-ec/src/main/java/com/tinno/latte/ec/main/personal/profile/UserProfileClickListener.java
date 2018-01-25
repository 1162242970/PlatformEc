package com.tinno.latte.ec.main.personal.profile;

import android.content.DialogInterface;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.tinno.latte.delegates.LatteDelegate;
import com.tinno.latte.ec.main.personal.list.ListBean;
import com.tinno.latte.net.RestClient;
import com.tinno.latte.net.callback.ISuccess;
import com.tinno.latte.ui.data.DateDialogUtil;
import com.tinno.latte.util.callback.CallbackManager;
import com.tinno.latte.util.callback.CallbackType;
import com.tinno.latte.util.callback.IGlobalCallback;
import com.tinno.latteec.ec.R;

/**
 * Created by android on 18-1-9.
 * <p>
 * RecyclerView的Item点击事件
 */

public class UserProfileClickListener extends SimpleClickListener {

    private final LatteDelegate DELEGATE;

    private String[] mGenders = new String[]{"男", "女", "保密"};

    public static final String API_HOST = "你的服务器域名";
    public static final String UPLOAD_IMG = API_HOST + "你的上传地址";

    public UserProfileClickListener(LatteDelegate delegate) {
        DELEGATE = delegate;
    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, final View view, int position) {
        final ListBean bean = (ListBean) baseQuickAdapter.getData().get(position);
        final int id = bean.getId();
        switch (id) {
            case 1:
                //设置回调
                CallbackManager.getInstance()
                        .addCallback(CallbackType.ON_CROP, new IGlobalCallback<Uri>() {
                            @Override
                            public void executeCallback(@Nullable Uri args) {
                                //为ImageView设置头像
                                final ImageView avatar = view.findViewById(R.id.img_arrow_avatar);
                                Glide.with(DELEGATE)
                                        .load(args)
                                        .into(avatar);

                                //上传服务器
                                if (args != null) {
                                    RestClient.builder()
                                            .url(UPLOAD_IMG)
                                            .loader(DELEGATE.getContext())
                                            .file(args.getPath())
                                            .success(new ISuccess() {
                                                @Override
                                                public void onSuccess(String response) {
                                                    //返回服务器图片的url,即上传的图片
                                                    final String path = JSON.parseObject(response).getJSONObject("result")
                                                            .getString("path");

                                                    //通知服务器更新信息
                                                    RestClient.builder()
                                                            .url("user_profile.php")
                                                            .params("avatar", path)
                                                            .loader(DELEGATE.getContext())
                                                            .success(new ISuccess() {
                                                                @Override
                                                                public void onSuccess(String response) {
                                                                    //获取更新后的用户信息，然后更新本地数据库
                                                                    //或者没有本地数据的APP，每次打开APP都请求API，获取信息
                                                                }
                                                            })
                                                            .build()
                                                            .post();


                                                }
                                            })
                                            .build()
                                            .upload();
                                }
                            }
                        });
                //开启照相机或选择图片
                DELEGATE.startCameraWithCheck();
                break;
            case 2:
                //进入更改姓名界面
                final LatteDelegate nameDelegate = bean.getDelegate();
                DELEGATE.getSupportDelegate().start(nameDelegate);
                break;
            case 3:
                //更改性别界面
                getGenderDialog(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final AppCompatTextView textView = view.findViewById(R.id.tv_arrow_value);
                        textView.setText(mGenders[which]);
                        dialog.cancel();
                    }
                });
                break;
            case 4:
                final DateDialogUtil dateDialogUtil = new DateDialogUtil();
                dateDialogUtil.setDateListener(new DateDialogUtil.IDateListener() {
                    @Override
                    public void onDateChange(String date) {
                        final TextView textView = view.findViewById(R.id.tv_arrow_value);
                        textView.setText(date);
                    }
                });
                dateDialogUtil.showDialog(DELEGATE.getContext());
                break;
            default:
                break;

        }
    }

    private void getGenderDialog(DialogInterface.OnClickListener listener) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(DELEGATE.getContext());
        builder.setSingleChoiceItems(mGenders, 0, listener);
        builder.show();
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
