package com.example.test.Model;

import java.io.Serializable;

/**
 * Created by cyc20 on 2017/11/28.
 */

public class Message implements Serializable{
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
    private String pic_urls;
    private String geo;
    private boolean is_paid;
    private int mblog_vip_type;
    private User user;
    public Message(String created_at,String contentText,String source_url,String pic_urls,User user){
        this.contentText=contentText;
        this.created_at=created_at;
        this.source_url=source_url;
        this.pic_urls=pic_urls;
        this.user=user;
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

    public String getPic_urls() {
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

    public void setPic_urls(String pic_urls) {
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
}
