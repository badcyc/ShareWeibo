package com.example.test.weibo.Comments.Bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by cyc20 on 2017/12/8.
 */

public class CommentUser implements Parcelable {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.avatar_hd);
    }

    protected CommentUser(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.avatar_hd = in.readString();
    }

    public static final Parcelable.Creator<CommentUser> CREATOR = new Parcelable.Creator<CommentUser>() {
        @Override
        public CommentUser createFromParcel(Parcel source) {
            return new CommentUser(source);
        }

        @Override
        public CommentUser[] newArray(int size) {
            return new CommentUser[size];
        }
    };
}
