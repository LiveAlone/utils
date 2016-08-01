package org.yqj.boot.utils;

import com.google.common.base.Objects;
import com.google.common.base.Strings;

import java.util.Collection;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Created by yaoqijun.
 * Date:2016-08-02
 * Email:yaoqijunmail@gmail.io
 * Descirbe:
 */
public class Arguments {

    public static <T extends Collection> boolean isNullOrEmpty(T t) {return isNull(t)||isEmpty(t);}

    public static boolean isNull(Object o) {return o==null;}

    public static boolean notNull(Object o) {
        return o != null;
    }

    public static boolean isEmpty(String s) {
        return Strings.isNullOrEmpty(s);
    }

    public static  <T extends Collection> boolean isEmpty(T t) {return t.isEmpty();}

    public static boolean notEmpty(String s) {
        return !isEmpty(s);
    }

    public static <T extends Collection> boolean notEmpty(T l) {
        return notNull(l) && !l.isEmpty();
    }

    public static boolean positive(Number n) {
        return n.doubleValue() > 0;
    }

    public static boolean isPositive(Number n) {
        return n != null && n.doubleValue() > 0;
    }

    public static boolean negative(Number n) {
        return n.doubleValue() < 0;
    }

    public static boolean isNegative(Number n) {
        return n!=null && n.doubleValue()<0;
    }

    public static <T> boolean equalWith(T source, T target) {
        return Objects.equal(source, target);
    }

    public static boolean not(Boolean t) {
        checkArgument(notNull(t));
        return !t;
    }

    /**
     * 高效地检查是否为整数
     * @param str    待检验字符串
     */
    public static boolean isDecimal(String str) {
        for (char c : str.toCharArray()) {
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    /**
     * 高效地检查是否为数字，整数或小数
     * @param str    待检验字符串
     */
    public static boolean isNumberic(String str) {
        int dot = 0;
        for (char c : str.toCharArray()) {
            if (c == '.' && dot == 0) {
                dot = 1;
            } else if (c == '.') {
                return false;
            } else if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }
}
