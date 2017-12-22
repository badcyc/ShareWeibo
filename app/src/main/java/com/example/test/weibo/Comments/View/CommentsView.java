package com.example.test.weibo.Comments.View;

import com.example.test.weibo.Comments.Bean.CommentData;

/**
 * Created by cyc20 on 2017/12/21.
 */

public interface CommentsView {
    void success(CommentData model);
    void error(int code, String msg);
}
