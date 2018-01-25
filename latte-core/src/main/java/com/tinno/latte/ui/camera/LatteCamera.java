package com.tinno.latte.ui.camera;

import android.net.Uri;

import com.tinno.latte.delegates.PermissionCheckerDelegate;
import com.tinno.latte.util.file.FileUtil;

/**
 * Created by android on 18-1-10.
 * 照相机调用类
 */

public class LatteCamera {

    public static Uri creareCropFile() {
        return Uri.parse
                (FileUtil.createFile("crop_image",
                        FileUtil.getFileNameByTime(
                                "IMG", "jpg")).getPath());
    }

    public static void start(PermissionCheckerDelegate delegate) {
        new CameraHandler(delegate).beginCameraDialog();
    }
}
