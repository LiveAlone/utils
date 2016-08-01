package org.yqj.boot.model;

import com.google.common.base.MoreObjects;

import java.io.Serializable;

/**
 * Created by yaoqijun.
 * Date:2016-08-02
 * Email:yaoqijunmail@gmail.io
 * Descirbe: 对应的Service 对应的返回的数据信息方式
 */
public class Response<T> implements Serializable{

    private static final long serialVersionUID = 2200310478259203420L;

    private boolean success; //调用是否成功

    private T result;       // 如果success = true,则通过result可以获得调用结果

    private String error;   //如果success = false,则通过error可以查看错误信息

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.success = true;
        this.result = result;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.success = false;
        this.error = error;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("success", success)
                .add("result", result)
                .add("error", error)
                .omitNullValues()
                .toString();
    }

    public static <T> Response<T> ok(T data) {
        Response<T> resp = new Response();
        resp.setResult(data);
        return resp;
    }

    public static <T> Response<T> ok() {
        return Response.ok(null);
    }

    public static <T> Response<T> fail(String error) {
        Response<T> resp = new Response();
        resp.setError(error);
        return resp;
    }
}
