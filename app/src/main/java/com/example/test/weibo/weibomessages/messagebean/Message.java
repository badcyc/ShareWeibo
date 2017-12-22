package com.example.test.weibo.weibomessages.messagebean;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.test.navigation.accountmanagement.bean.User;

import java.util.ArrayList;

/**
 * Created by cyc20 on 2017/11/28.
 */

public class Message implements Parcelable {
    private String created_at;
    private String id;
    private String mid;
    private boolean can_edit;
    private String idstr;
    private String contentText;
    private int source_type;
    private String source_url;
    private boolean favorited;
    private boolean truncated;
    private String in_reply_to_status_id;
    private String in_reply_to_user_id;
    private String in_reply_to_screen_name;
    private String geo;
    private boolean is_paid;
    private int mblog_vip_type;
    private ArrayList<String> pic_urls;//图片链接
    private int reposts_count;
    private int comments_count;
    private int attitudes_count;
    private User user;
    private Message retweeted_status = null;

    public Message(String created_at, String id, String contentText, String source_url, User user,
                   String reposts_count, String comments_count, String attitudes_count, Message retweeted_status) {
        super();
        this.contentText = contentText;
        this.id = id;
        this.created_at = created_at;
        this.source_url = source_url;
        this.user = user;
        this.reposts_count = Integer.valueOf(reposts_count);
        this.comments_count = Integer.valueOf(comments_count);
        this.attitudes_count = Integer.valueOf(attitudes_count);
        this.retweeted_status = retweeted_status;
    }

    public void setRetweeted_status(Message retweeted_status) {
        this.retweeted_status = retweeted_status;
    }

    public Message getRetweeted_status() {
        return retweeted_status;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public int getSource_type() {
        return source_type;
    }

    public int getMblog_vip_type() {
        return mblog_vip_type;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getId() {
        return id;
    }

    public String getGeo() {
        return geo;
    }

    public String getIdstr() {
        return idstr;
    }

    public String getIn_reply_to_screen_name() {
        return in_reply_to_screen_name;
    }

    public String getIn_reply_to_status_id() {
        return in_reply_to_status_id;
    }

    public String getIn_reply_to_user_id() {
        return in_reply_to_user_id;
    }

    public String getMid() {
        return mid;
    }

    public ArrayList<String> getPic_urls() {
        return pic_urls;
    }

    public String getSource_url() {
        return source_url;
    }

    public String getText() {
        return contentText;
    }

    public void setCan_edit(boolean can_edit) {
        this.can_edit = can_edit;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public void setFavorited(boolean favorited) {
        this.favorited = favorited;
    }

    public void setGeo(String geo) {
        this.geo = geo;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setIdstr(String idstr) {
        this.idstr = idstr;
    }

    public void setIn_reply_to_screen_name(String in_reply_to_screen_name) {
        this.in_reply_to_screen_name = in_reply_to_screen_name;
    }

    public void setIn_reply_to_status_id(String in_reply_to_status_id) {
        this.in_reply_to_status_id = in_reply_to_status_id;
    }

    public void setIn_reply_to_user_id(String in_reply_to_user_id) {
        this.in_reply_to_user_id = in_reply_to_user_id;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public void setIs_paid(boolean is_paid) {
        this.is_paid = is_paid;
    }

    public void setPic_urls(ArrayList<String> pic_urls) {
        this.pic_urls = pic_urls;
    }

    public void setSource_type(int source_type) {
        this.source_type = source_type;
    }

    public void setMblog_vip_type(int mblog_vip_type) {
        this.mblog_vip_type = mblog_vip_type;
    }

    public void setSource_url(String source_url) {
        this.source_url = source_url;
    }

    public void setContentText(String contentText) {
        this.contentText = contentText;
    }

    public void setTruncated(boolean truncated) {
        this.truncated = truncated;
    }

    public void setAttitudes_count(int attitudes_count) {
        this.attitudes_count = attitudes_count;
    }

    public void setComments_count(int comments_count) {
        this.comments_count = comments_count;
    }

    public void setReposts_count(int reposts_count) {
        this.reposts_count = reposts_count;
    }

    public int getAttitudes_count() {
        return attitudes_count;
    }

    public int getComments_count() {
        return comments_count;
    }

    public int getReposts_count() {
        return reposts_count;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.created_at);
        dest.writeString(this.id);
        dest.writeString(this.mid);
        dest.writeByte(this.can_edit ? (byte) 1 : (byte) 0);
        dest.writeString(this.idstr);
        dest.writeString(this.contentText);
        dest.writeInt(this.source_type);
        dest.writeString(this.source_url);
        dest.writeByte(this.favorited ? (byte) 1 : (byte) 0);
        dest.writeByte(this.truncated ? (byte) 1 : (byte) 0);
        dest.writeString(this.in_reply_to_status_id);
        dest.writeString(this.in_reply_to_user_id);
        dest.writeString(this.in_reply_to_screen_name);
        dest.writeString(this.geo);
        dest.writeByte(this.is_paid ? (byte) 1 : (byte) 0);
        dest.writeInt(this.mblog_vip_type);
        dest.writeStringList(this.pic_urls);
        dest.writeInt(this.reposts_count);
        dest.writeInt(this.comments_count);
        dest.writeInt(this.attitudes_count);
        dest.writeParcelable(this.user,flags);
        dest.writeParcelable(this.retweeted_status, flags);
    }

    protected Message(Parcel in) {
        this.created_at = in.readString();
        this.id = in.readString();
        this.mid = in.readString();
        this.can_edit = in.readByte() != 0;
        this.idstr = in.readString();
        this.contentText = in.readString();
        this.source_type = in.readInt();
        this.source_url = in.readString();
        this.favorited = in.readByte() != 0;
        this.truncated = in.readByte() != 0;
        this.in_reply_to_status_id = in.readString();
        this.in_reply_to_user_id = in.readString();
        this.in_reply_to_screen_name = in.readString();
        this.geo = in.readString();
        this.is_paid = in.readByte() != 0;
        this.mblog_vip_type = in.readInt();
        this.pic_urls = in.createStringArrayList();
        this.reposts_count = in.readInt();
        this.comments_count = in.readInt();
        this.attitudes_count = in.readInt();
        this.user =  in.readParcelable(User.class.getClassLoader());
        this.retweeted_status = in.readParcelable(Message.class.getClassLoader());
    }

    public static final Parcelable.Creator<Message> CREATOR = new Parcelable.Creator<Message>() {
        @Override
        public Message createFromParcel(Parcel source) {
            return new Message(source);
        }

        @Override
        public Message[] newArray(int size) {
            return new Message[size];
        }
    };
}
