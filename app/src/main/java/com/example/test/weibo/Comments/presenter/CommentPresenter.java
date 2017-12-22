package com.example.test.weibo.Comments.presenter;

import com.example.test.base.presenter.BasePresenter;
import com.example.test.base.rxjava.ApiCallback;
import com.example.test.base.rxjava.SubscriberCallback;
import com.example.test.weibo.Comments.Bean.CommentData;
import com.example.test.weibo.Comments.View.CommentsView;
import com.example.test.weibo.Comments.retrofit.CommentService;
import com.example.test.weibo.Comments.retrofit.CommentServiceFactory;

/**
 * Created by cyc20 on 2017/12/21.
 */

public class CommentPresenter extends BasePresenter<CommentsView> {
    private CommentsView view;
    public CommentPresenter(CommentsView commentsView){
        this.view=commentsView;
    }
    @SuppressWarnings("unchecked")
    public void data(String url){
        addSubscription(CommentServiceFactory.getInstance().createService(CommentService.class).loadCommentData(url),
                new SubscriberCallback(new ApiCallback<CommentData>() {
            @Override
            public void onSuccess(CommentData model) {
                view.success(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                view.error(code,msg);
            }

            @Override
            public void onComplete() {

            }
        }));
    }
}
