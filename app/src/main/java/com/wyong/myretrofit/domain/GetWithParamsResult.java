package com.wyong.myretrofit.domain;

public class GetWithParamsResult {

    @Override
    public String toString() {
        return "GetWithParamsResult{" +
                "success=" + success +
                ", code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    /**
     * success : true
     * code : 10000
     * message : get带参数请求成功.
     * data : {"page":"12","keyword":"这是我的关键字keyword","order":"顺序"}
     */

    public boolean success;
    public int code;
    public String message;
    public DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {

        @Override
        public String toString() {
            return "DataBean{" +
                    "page='" + page + '\'' +
                    ", keyword='" + keyword + '\'' +
                    ", order='" + order + '\'' +
                    '}';
        }

        /**
         * page : 12
         * keyword : 这是我的关键字keyword
         * order : 顺序
         */

        public String page;
        public String keyword;
        public String order;

        public String getPage() {
            return page;
        }

        public void setPage(String page) {
            this.page = page;
        }

        public String getKeyword() {
            return keyword;
        }

        public void setKeyword(String keyword) {
            this.keyword = keyword;
        }

        public String getOrder() {
            return order;
        }

        public void setOrder(String order) {
            this.order = order;
        }
    }
}
