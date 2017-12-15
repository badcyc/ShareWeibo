package com.example.test.BaseModel.User;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by cyc20 on 2017/11/29.
 * 发微博里的用户信息
 */

public class User implements Parcelable {
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
    private int ptype;
    private boolean allow_all_comment;
    private String avatar_large;
    private String avatar_hd;  //头像
    private String verified_reason;
    private String verified_trade;
    private String verified_reason_url;
    private String verified_source;
    private String verified_source_url;
    private boolean follow_me;
    private boolean like;
    private boolean like_me;
    private String online_status;
    private String bi_followers_count;
    private String lang;
    private int star;
    private int mbtype;
    private int mbrank;
    private int block_word;
    private int block_app;
    private int credit_score;
    private int user_ability;
    private int urank;
    private int story_read_state;
    private int vclub_member;

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.idstr);
        dest.writeInt(this.classes);
        dest.writeString(this.screen_name);
        dest.writeString(this.name);
        dest.writeInt(this.provice);
        dest.writeInt(this.city);
        dest.writeString(this.location);
        dest.writeString(this.description);
        dest.writeString(this.url);
        dest.writeString(this.profile_image_url);
        dest.writeString(this.cover_image_phone);
        dest.writeString(this.profile_url);
        dest.writeString(this.domain);
        dest.writeString(this.weihao);
        dest.writeString(this.gender);
        dest.writeInt(this.followers_count);
        dest.writeInt(this.friends_count);
        dest.writeInt(this.pagefriends_count);
        dest.writeInt(this.statuses_count);
        dest.writeInt(this.favourites_count);
        dest.writeString(this.created_at);
        dest.writeByte(this.following ? (byte) 1 : (byte) 0);
        dest.writeByte(this.allow_all_act_msg ? (byte) 1 : (byte) 0);
        dest.writeByte(this.geo_enabled ? (byte) 1 : (byte) 0);
        dest.writeByte(this.verified ? (byte) 1 : (byte) 0);
        dest.writeInt(this.verified_type);
        dest.writeString(this.remark);
        dest.writeInt(this.ptype);
        dest.writeByte(this.allow_all_comment ? (byte) 1 : (byte) 0);
        dest.writeString(this.avatar_large);
        dest.writeString(this.avatar_hd);
        dest.writeString(this.verified_reason);
        dest.writeString(this.verified_trade);
        dest.writeString(this.verified_reason_url);
        dest.writeString(this.verified_source);
        dest.writeString(this.verified_source_url);
        dest.writeByte(this.follow_me ? (byte) 1 : (byte) 0);
        dest.writeByte(this.like ? (byte) 1 : (byte) 0);
        dest.writeByte(this.like_me ? (byte) 1 : (byte) 0);
        dest.writeString(this.online_status);
        dest.writeString(this.bi_followers_count);
        dest.writeString(this.lang);
        dest.writeInt(this.star);
        dest.writeInt(this.mbtype);
        dest.writeInt(this.mbrank);
        dest.writeInt(this.block_word);
        dest.writeInt(this.block_app);
        dest.writeInt(this.credit_score);
        dest.writeInt(this.user_ability);
        dest.writeInt(this.urank);
        dest.writeInt(this.story_read_state);
        dest.writeInt(this.vclub_member);
    }

    public User() {
    }

    protected User(Parcel in) {
        this.id = in.readString();
        this.idstr = in.readString();
        this.classes = in.readInt();
        this.screen_name = in.readString();
        this.name = in.readString();
        this.provice = in.readInt();
        this.city = in.readInt();
        this.location = in.readString();
        this.description = in.readString();
        this.url = in.readString();
        this.profile_image_url = in.readString();
        this.cover_image_phone = in.readString();
        this.profile_url = in.readString();
        this.domain = in.readString();
        this.weihao = in.readString();
        this.gender = in.readString();
        this.followers_count = in.readInt();
        this.friends_count = in.readInt();
        this.pagefriends_count = in.readInt();
        this.statuses_count = in.readInt();
        this.favourites_count = in.readInt();
        this.created_at = in.readString();
        this.following = in.readByte() != 0;
        this.allow_all_act_msg = in.readByte() != 0;
        this.geo_enabled = in.readByte() != 0;
        this.verified = in.readByte() != 0;
        this.verified_type = in.readInt();
        this.remark = in.readString();
        this.ptype = in.readInt();
        this.allow_all_comment = in.readByte() != 0;
        this.avatar_large = in.readString();
        this.avatar_hd = in.readString();
        this.verified_reason = in.readString();
        this.verified_trade = in.readString();
        this.verified_reason_url = in.readString();
        this.verified_source = in.readString();
        this.verified_source_url = in.readString();
        this.follow_me = in.readByte() != 0;
        this.like = in.readByte() != 0;
        this.like_me = in.readByte() != 0;
        this.online_status = in.readString();
        this.bi_followers_count = in.readString();
        this.lang = in.readString();
        this.star = in.readInt();
        this.mbtype = in.readInt();
        this.mbrank = in.readInt();
        this.block_word = in.readInt();
        this.block_app = in.readInt();
        this.credit_score = in.readInt();
        this.user_ability = in.readInt();
        this.urank = in.readInt();
        this.story_read_state = in.readInt();
        this.vclub_member = in.readInt();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
