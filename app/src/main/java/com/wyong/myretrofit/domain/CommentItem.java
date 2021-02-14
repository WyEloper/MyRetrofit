package com.wyong.myretrofit.domain;

/**
 * <pre>
 *     author : WYong
 *     e-mail : 923275116@qq.com
 *     time   : 2021/02/14
 *     desc   : 表单提交数据的入参
 * </pre>
 */
public class CommentItem {

    public CommentItem(String articleId, String commentContent) {
        this.articleId = articleId;
        this.commentContent = commentContent;
    }

    /**
     * articleId : 234123
     * commentContent : 这是评论内容
     */

    public String articleId;
    public String commentContent;

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }
}
