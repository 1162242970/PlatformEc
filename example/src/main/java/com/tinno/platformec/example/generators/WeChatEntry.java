package com.tinno.platformec.example.generators;

import com.example.annotations.EntryGenerator;
import com.tinno.latte.wechat.templates.WXEntryTemplate;

/**
 * Created by android on 17-12-11.
 */


@EntryGenerator(
        packageName = "com.tinno.platformec.example",
        entryTemplate = WXEntryTemplate.class
)
public interface WeChatEntry {
}
