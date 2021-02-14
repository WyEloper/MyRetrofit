package com.wyong.myretrofit.domain;

/**
 * <pre>
 *     author : WYong
 *     e-mail : 923275116@qq.com
 *     time   : 2021/02/14
 *     desc   :
 * </pre>
 */
public class LoginResult {

    @Override
    public String toString() {
        return "LoginResult{" +
                "success=" + success +
                ", code=" + code +
                ", message='" + message + '\'' +
                ", data='" + data + '\'' +
                '}';
    }

    /**
     * success : true
     * code : 10000
     * message : 这是你提交上来的数据：wyong - 123456
     * data : d3914aa3-268a-4454-912d-7b20656a883b
     */

    public boolean success;
    public int code;
    public String message;
    public String data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
