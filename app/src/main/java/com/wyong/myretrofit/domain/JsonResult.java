package com.wyong.myretrofit.domain;

import java.util.List;

/**
 * http://127.0.0.1:9102/get/text
 */
public class JsonResult {

    /**
     * success : true
     * code : 10000
     * message : 获取成功
     * data : [{"id":"1343478032825016320","title":"Android加载大图片，解决OOM问题","viewCount":242,"commentCount":11,"publishTime":"2020-12-28T08:44:58.298+0000","userName":"程序员拉大锯","cover":"/imgs/12.png"},{"id":"1343478032829210624","title":"Volley/Xutils对大图片处理算法源码分析","viewCount":216,"commentCount":56,"publishTime":"2020-12-28T08:44:58.298+0000","userName":"程序员拉大锯","cover":"/imgs/4.png"},{"id":"1343478032829210625","title":"Android开发网络安全配置","viewCount":245,"commentCount":77,"publishTime":"2020-12-28T08:44:58.298+0000","userName":"程序员拉大锯","cover":"/imgs/16.png"},{"id":"1343478032829210626","title":"Android开发网络编程，请求图片","viewCount":228,"commentCount":96,"publishTime":"2020-12-28T08:44:58.298+0000","userName":"程序员拉大锯","cover":"/imgs/8.png"},{"id":"1343478032829210627","title":"Intent页面跳转工具类分享","viewCount":269,"commentCount":104,"publishTime":"2020-12-28T08:44:58.298+0000","userName":"程序员拉大锯","cover":"/imgs/4.png"},{"id":"1343478032829210628","title":"阳光沙滩商城的API文档","viewCount":159,"commentCount":102,"publishTime":"2020-12-28T08:44:58.298+0000","userName":"程序员拉大锯","cover":"/imgs/10.png"},{"id":"1343478032829210629","title":"Android课程视频打包下载","viewCount":179,"commentCount":12,"publishTime":"2020-12-28T08:44:58.298+0000","userName":"程序员拉大锯","cover":"/imgs/10.png"},{"id":"1343478032829210630","title":"非常轻量级的gif录制软件","viewCount":251,"commentCount":89,"publishTime":"2020-12-28T08:44:58.298+0000","userName":"程序员拉大锯","cover":"/imgs/5.png"},{"id":"1343478032829210631","title":"Fiddler抓包工具，墙裂推荐，功能很强大很全的一个工具","viewCount":91,"commentCount":41,"publishTime":"2020-12-28T08:44:58.298+0000","userName":"程序员拉大锯","cover":"/imgs/10.png"},{"id":"1343478032829210632","title":"AndroidStudio奇淫技巧-代码管理","viewCount":54,"commentCount":28,"publishTime":"2020-12-28T08:44:58.298+0000","userName":"程序员拉大锯","cover":"/imgs/8.png"},{"id":"1343478032829210633","title":"OC和Swift混编","viewCount":270,"commentCount":22,"publishTime":"2020-12-28T08:44:58.298+0000","userName":"程序员拉大锯","cover":"/imgs/5.png"},{"id":"1343478032829210634","title":"最新的Android studio是不是没有Android Device Monitor","viewCount":166,"commentCount":90,"publishTime":"2020-12-28T08:44:58.298+0000","userName":"程序员拉大锯","cover":"/imgs/13.png"}]
     */

    public boolean success;
    public int code;
    public String message;
    public List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1343478032825016320
         * title : Android加载大图片，解决OOM问题
         * viewCount : 242
         * commentCount : 11
         * publishTime : 2020-12-28T08:44:58.298+0000
         * userName : 程序员拉大锯
         * cover : /imgs/12.png
         */

        public String id;
        public String title;
        public int viewCount;
        public int commentCount;
        public String publishTime;
        public String userName;
        public String cover;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getViewCount() {
            return viewCount;
        }

        public void setViewCount(int viewCount) {
            this.viewCount = viewCount;
        }

        public int getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(int commentCount) {
            this.commentCount = commentCount;
        }

        public String getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(String publishTime) {
            this.publishTime = publishTime;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }
    }
}
