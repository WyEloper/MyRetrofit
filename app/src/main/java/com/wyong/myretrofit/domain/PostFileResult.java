package com.wyong.myretrofit.domain;

/**
 * <pre>
 *     author : WYong
 *     e-mail : 923275116@qq.com
 *     time   : 2021/02/14
 *     desc   : 单文件上传的返回参 实体 (使用@Part注解)
 * </pre>
 */
public class PostFileResult {

    @Override
    public String toString() {
        return "PostFileResult{" +
                "success=" + success +
                ", code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    /**
     * success : true
     * code : 10000
     * message : 上传成功.文件路径为：E:\codes\Idear\SobNetworkCourseServer\target\classes\sobUpload\0.png
     * data : null
     */

    public boolean success;
    public int code;
    public String message;
    public Object data;

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
