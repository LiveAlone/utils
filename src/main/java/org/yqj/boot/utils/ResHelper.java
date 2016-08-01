package org.yqj.boot.utils;

import com.google.common.base.Optional;
import org.yqj.boot.exception.JsonResponseException;
import org.yqj.boot.exception.ServiceException;
import org.yqj.boot.model.Response;

import java.util.Collections;

/**
 * Created by yaoqijun.
 * Date:2016-08-02
 * Email:yaoqijunmail@gmail.io
 * Descirbe:
 */
public class ResHelper {
    public static <T> T or(Response<T> resp, T failValue) {
        return resp.isSuccess() ? resp.getResult() : failValue;
    }

    public static Boolean orFalse(Response<Boolean> resp) {
        return or(resp, Boolean.FALSE);
    }

    public static <T> T or500(Response<T> resp) {
        if (resp.isSuccess()) {
            return resp.getResult();
        }
        throw new JsonResponseException(500, resp.getError());
    }

    public static <T> T orServEx(Response<T> resp) {
        if (resp.isSuccess()) {
            return resp.getResult();
        }
        throw new ServiceException(resp.getError());
    }

    /**
     * need not to cast
     */
    public static <T, D extends T> Response<T> ok(D data) {
        Response<T> resp = new Response();
        resp.setResult(data);
        return resp;
    }

    /**
     * Guava {@code Optional} Helpers
     */
    public static final class Opt {
        public static <T> Response<T> unwrap(Response<Optional<T>> resp, String error) {
            if (resp.isSuccess()) {
                if (resp.getResult().isPresent()) {
                    return Response.ok(resp.getResult().get());
                }
                return Response.fail(error);
            }
            return Response.fail(resp.getError());
        }

        public static <T, D extends T> Response<Optional<T>> of(D data) {
            return Response.ok(Optional.<T>of(data));
        }

        public static <T> Response<Optional<T>> absent() {
            return Response.ok(Optional.<T>absent());
        }

        public static <T, D extends T> Response<Optional<T>> fromNullable(D data) {
            return Response.ok(Optional.<T>fromNullable(data));
        }
    }

    /**
     * Vanilla java {@code Map} Helpers
     */
    public static final class Map {
        public static <K, V> Response<java.util.Map<K, V>> empty() {
            return Response.ok(Collections.<K, V>emptyMap());
        }
    }
}
