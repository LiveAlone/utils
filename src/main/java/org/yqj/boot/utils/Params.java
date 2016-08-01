package org.yqj.boot.utils;

import com.google.common.base.Predicate;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created by yaoqijun.
 * Date:2016-08-02
 * Email:yaoqijunmail@gmail.io
 * Descirbe:
 */
public class Params {
    /**
     * 过滤Map中的NULL或""值
     */
    public static Map<String, Object> filterNullOrEmpty(Map<String, Object> criteria) {
        return Maps.filterEntries(criteria, new Predicate<Map.Entry<String, Object>>() {
            public boolean apply(Map.Entry<String, Object> input) {
                Object v = input.getValue();
                if (v instanceof String) {
                    return !Strings.isNullOrEmpty((String) v);
                }
                return v != null;
            }
        });
    }

    public static String trimToNull(String str) {
        return str != null ? Strings.emptyToNull(str.replace('\u00A0',' ').trim()) : null;
    }

    public static String trimToNull(Object obj) {
        return obj != null ? trimToNull(obj.toString()) : null;
    }
}
