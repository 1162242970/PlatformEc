package com.tinno.platformec.example.generators;


import com.example.annotations.PayEntryGenerator;
import com.tinno.latte.wechat.templates.WXPayEntryTemplate;

/**
 * Created by android on 17-12-11.
 */

@PayEntryGenerator(
        packageName = "com.tinno.platformec.example",
        payEntryTemplate = WXPayEntryTemplate.class
)
public interface WeChatPayEntry {
}
