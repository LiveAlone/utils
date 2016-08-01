package org.yqj.boot.utils;

/**
 * Created by yaoqijun.
 * Date:2016-08-02
 * Email:yaoqijunmail@gmail.io
 * Descirbe: 不同的 Thread 创建对应的 Object
 */
public final class ThreadObjectUtil {

    private static ThreadLocal<Object> threadLocal = new ThreadLocal();

    public static void put(Object o) {
        threadLocal.set(o);
    }

    public static Object getCurrentObject() {
        return threadLocal.get();
    }

    public static void clearCurrentVar() {
        threadLocal.remove();
    }
}
