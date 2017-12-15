package com.example.test.weibo.Comments.Bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by cyc20 on 2017/12/8.
 */

public class CommentData implements Parcelable {
    private String created_at;
    private int floor_number;
    private String content;
    private String source_url;
    private CommentUser user;

    public CommentData(String created_at, String floor_name, String content, String source_url, CommentUser user) {
        this.content = content;
        this.created_at = created_at;
        this.floor_number = Integer.valueOf(floor_name);
        this.source_url = source_url;
        this.user = user;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public void setUser(CommentUser user) {
        this.user = user;
    }

    public void setSource_url(String source_url) {
        this.source_url = source_url;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setFloor_number(String floor_number) {
        this.floor_number = Integer.valueOf(floor_number);
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getSource_url() {
        return source_url;
    }

    public CommentUser getUser() {
        return user;
    }

    public int getFloor_number() {
        return floor_number;
    }

    public String getContent() {
        return content;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.created_at);
        dest.writeInt(this.floor_number);
        dest.writeString(this.content);
        dest.writeString(this.source_url);
        dest.writeParcelable(this.user,flags);
    }

    protected CommentData(Parcel in) {
        this.created_at = in.readString();
        this.floor_number = in.readInt();
        this.content = in.readString();
        this.source_url = in.readString();
        this.user = in.readParcelable(CommentUser.class.getClassLoader());
    }

    public static final Parcelable.Creator<CommentData> CREATOR = new Parcelable.Creator<CommentData>() {
        @Override
        public CommentData createFromParcel(Parcel source) {
            return new CommentData(source);
        }

        @Override
        public CommentData[] newArray(int size) {
            return new CommentData[size];
        }
    };
}
