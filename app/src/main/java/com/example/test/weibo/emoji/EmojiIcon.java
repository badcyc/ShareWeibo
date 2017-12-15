package com.example.test.weibo.emoji;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by cyc20 on 2017/12/15.
 */

public class EmojiIcon implements Parcelable {
    private String phrase;
    private String type;
    private String url;
    private boolean hot;
    private boolean common;
    private String picid;
    private String icon;
    private String category;
    private String value;


    public EmojiIcon(String phrase,String type,String url,boolean hot,boolean common,
                     String picid,String icon,String category,String value){
        this.category=category;
        this.url=url;
        this.type=type;
        this.phrase=phrase;
        this.hot=hot;
        this.common=common;
        this.picid=picid;
        this.icon=icon;
        this.value=value;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public void setCommon(boolean common) {
        this.common = common;
    }
    public void setHot(boolean hot) {
        this.hot = hot;
    }

  //  public void setOrderNumber(Integer orderNumber) {
     ///   this.orderNumber = orderNumber;
   // }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setPicid(String picid) {
        this.picid = picid;
    }

    public void setValue(String value) {
        this.value = value;
    }
    //public Integer getOrderNumber() {
     //   return orderNumber;
  //  }

    public String getCategory() {
        return category;
    }

    public String getPhrase() {
        return phrase;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public String getIcon() {
        return icon;
    }

    public String getPicid() {
        return picid;
    }

    public String getValue() {
        return value;
    }

    public boolean isCommon() {
        return common;
    }

    public boolean isHot() {
        return hot;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.phrase);
        dest.writeString(this.type);
        dest.writeString(this.url);
        dest.writeByte(this.hot ? (byte) 1 : (byte) 0);
        dest.writeByte(this.common ? (byte) 1 : (byte) 0);
        dest.writeString(this.picid);
        dest.writeString(this.icon);
        dest.writeString(this.category);
        dest.writeString(this.value);
    }



    protected EmojiIcon(Parcel in) {
        this.phrase = in.readString();
        this.type = in.readString();
        this.url = in.readString();
        this.hot = in.readByte() != 0;
        this.common = in.readByte() != 0;
        this.picid = in.readString();
        this.icon = in.readString();
        this.category = in.readString();
        this.value = in.readString();
    }

    public static final Creator<EmojiIcon> CREATOR = new Creator<EmojiIcon>() {
        @Override
        public EmojiIcon createFromParcel(Parcel source) {
            return new EmojiIcon(source);
        }

        @Override
        public EmojiIcon[] newArray(int size) {
            return new EmojiIcon[size];
        }
    };
}
