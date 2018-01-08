package com.example.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * Target(ElementType.TYPE):表示修饰类,接口或者枚举定义
 * Retention(RetentionPolicy.SOURCE): Annotation只保留在源代码中,编译器直接丢弃这种Annotation
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface EntryGenerator {

    String packageName();

    Class<?> entryTemplate();
}
