package com.example.test.CommentsActivity.Model;

import java.io.Serializable;

/**
 * Created by cyc20 on 2017/12/8.
 */

public class CommentUser implements Serializable {
    private String id;
    private String name;
    private String avatar_hd;

    public CommentUser(String id, String name, String avatar_hd) {
        this.avatar_hd = avatar_hd;
        this.id = id;
        this.name = name;
    }

    public void setAvatar_hd(String avatar_hd) {
        this.avatar_hd = avatar_hd;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAvatar_hd() {
        return avatar_hd;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
