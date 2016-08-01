package org.yqj.boot.utils;

import com.google.common.base.Joiner;

/**
 * Created by yaoqijun.
 * Date:2016-08-02
 * Email:yaoqijunmail@gmail.io
 * Descirbe:
 */
public class Joiners {
    public static final Joiner DOT = Joiner.on(".").skipNulls();
    public static final Joiner COMMA = Joiner.on(",").skipNulls();
    public static final Joiner COLON = Joiner.on(":").skipNulls();
}
