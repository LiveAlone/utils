/*
 * Copyright (c) 2015 杭州端点网络科技有限公司
 */

package org.yqj.boot.utils;

import com.google.common.collect.Iterables;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author Effet
 */
public class Iters {

    public static <I> List<I> nullToEmpty(List<I> list) {
        if (list == null) {
            return Collections.emptyList();
        }
        return list;
    }

    public static <K, V> Map<K, V> nullToEmpty(Map<K, V> map) {
        if (map == null) {
            return Collections.emptyMap();
        }
        return map;
    }

    public static <I> Iterable<I> emptyToNull(Iterable<I> iter) {
        return iter != null && iter.iterator().hasNext() ? iter : null;
    }

    public static <T> List<T> emptyToNull(List<T> list) {
        return list != null && !list.isEmpty() ? list : null;
    }

    public static <T> T[] emptyToNull(T[] array) {
        return array != null && array.length > 0 ? array : null;
    }

    public static long[] emptyToNull(long[] array) {
        return array != null && array.length > 0 ? array : null;
    }
}
