package com.example.test.weibo.Comments.retrofit;

import com.example.test.weibo.Comments.Bean.CommentData;



import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by cyc20 on 2017/12/21.
 */

public interface CommentService {
    String BASE_URL="";
    @GET("")
    Observable<CommentData> loadCommentData(@Path("url")String url);
}
