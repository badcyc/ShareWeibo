package com.example.test.BaseModel.User;

import java.io.Serializable;

/**
 * Created by cyc20 on 2017/11/29.
 * 发微博里的用户信息
 */

public class User implements Serializable{
    private String id;
    private String idstr;
    private int classes;
    private String screen_name;
    private String name;
    private int provice;
    private int city;
    private String location;
    private String description;
    private String url;
    private String profile_image_url;
    private String cover_image_phone;
    private String profile_url;
    private String domain;
    private String weihao;
    private String gender;
    private int followers_count;
    private int friends_count;
    private int pagefriends_count;
    private int statuses_count;
    private int favourites_count;
    private String created_at;
    private boolean following;
    private boolean allow_all_act_msg;
    private boolean geo_enabled;
    private boolean verified;
    private int verified_type;
    private String remark;
    private int  ptype;
    private boolean  allow_all_comment;
    private String  avatar_large;
    private String  avatar_hd;  //头像
    private String  verified_reason;
    private String  verified_trade;
    private String  verified_reason_url;
    private String  verified_source;
    private String  verified_source_url;
    private boolean  follow_me;
    private boolean  like;
    private boolean  like_me;
    private String  online_status;
    private String  bi_followers_count;
    private String  lang;
    private int  star;
    private int  mbtype;
    private int  mbrank;
    private int  block_word;
    private int  block_app;
    private int  credit_score;
    private int  user_ability;
    private int  urank;
    private int  story_read_state;
    private int  vclub_member;

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAvatar_hd(String avatar_hd) {
        this.avatar_hd = avatar_hd;
    }

    public String getAvatar_hd() {
        return avatar_hd;
    }
}
