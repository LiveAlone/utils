package org.yqj.boot.model;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Maps;

import java.util.Map;

import static com.google.common.base.Strings.isNullOrEmpty;

/**
 * Created by yaoqijun.
 * Date:2016-08-02
 * Email:yaoqijunmail@gmail.io
 * Descirbe:
 */
public class PageInfo {
    public static final String LIMIT = "limit";
    public static final String OFFSET = "offset";

    private  Integer offset;
    private Integer limit;

    public PageInfo() {
    }

    public static PageInfo of(Integer pageNo, Integer size) {
        return new PageInfo(pageNo, size);
    }

    public PageInfo(Integer pageNo, Integer size) {
        pageNo = MoreObjects.firstNonNull(pageNo, 1);
        size = MoreObjects.firstNonNull(size, 20);
        limit = size > 0 ? size : 20;
        offset = (pageNo - 1) * size;
        offset = offset > 0 ? offset : 0;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Map<String, Object> toMap() {
        return toMap(null, null);
    }

    public Map<String, Object> toMap(String key1, String key2) {
        Map param = Maps.newHashMapWithExpectedSize(2);
        param.put(isNullOrEmpty(key1)?OFFSET:key1, offset);
        param.put(isNullOrEmpty(key2)?LIMIT:key2, limit);

        return param;
    }
}
