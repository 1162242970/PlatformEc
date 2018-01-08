package com.tinno.platformec.example.generators;

import com.example.annotations.AppRegisterGenerator;
import com.tinno.latte.wechat.templates.AppregisterTemplate;


/**
 * Created by android on 17-12-11.
 */
@AppRegisterGenerator(
        packageName = "com.tinno.platformec.example",
        appRegisterTemplate = AppregisterTemplate.class
)
public interface AppRegister {
}
