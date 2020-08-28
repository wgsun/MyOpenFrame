package com.iflytek.autofly.mvpframe.mvp.model.response;

/**
 * @author wgsun
 * @descrbe 接口响应基类
 * @since 2019/12/17 16:26
 */
public class BaseResponse<T> {

    private static final int SUCCESS_CODE = 0;

    /**
     * 接口响应码
     */
    public int code;

    /**
     * 接口响应信息
     */
    public String msg;

    public T data;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    public boolean isSuccess() {
        return SUCCESS_CODE == code;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
