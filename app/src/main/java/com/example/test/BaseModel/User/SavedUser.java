package com.example.test.BaseModel.User;

/**
 * Created by cyc20 on 2017/12/3.
 * 保存在本地的登陆成功的用户信息
 */

public class SavedUser {
    private String uid;
    private String userIdImageUrl;
    private String userIdScreenName;
    private int userIdClass;
    private int userIdProvince;
    private int userIdCity;
    private String userLocation;
    private String description;
    private String cover_image_phoneUrl;
    private int followers_count;
    private int friends_count;
    private int statuses_count;
    private int favourite_count;
    private String created_at;

    private boolean selectedState;
    public SavedUser(String uid, String userIdImageUrl, String userIdScreenName,
                     int userIdClass, int userIdProvince, int userIdCity,
                     String userLocation, String description, String cover_image_phoneUrl,
                     int followers_count, int friends_count, int statuses_count, int favourite_count,
                     String created_at){
        this.userIdImageUrl=userIdImageUrl;
        this.cover_image_phoneUrl=cover_image_phoneUrl;
        this.uid=uid;
        this.description=description;
        this.statuses_count=statuses_count;
        this.userIdScreenName=userIdScreenName;
        this.userIdClass=userIdClass;
        this.userIdProvince=userIdProvince;
        this.userIdCity=userIdCity;
        this.userLocation=userLocation;
        this.followers_count=followers_count;
        this.friends_count=friends_count;
        this.favourite_count=favourite_count;
        this.created_at=created_at;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }

    public void setUserIdImage(String userIdImageUrl) {
        this.userIdImageUrl = userIdImageUrl;
    }


    public String getUserIdImageUrl() {
        return userIdImageUrl;
    }

    public  void setUserIdScreenName(String userIdScreenName) {
        this.userIdScreenName = userIdScreenName;
    }

    public  String getUserIdScreenName() {
        return userIdScreenName;
    }

    public void setSelectedState(boolean selectedState) {
        this.selectedState = selectedState;
    }

    public boolean isSelectedState() {
        return selectedState;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public void setCover_image_phoneUrl(String cover_image_phoneUrl) {
        this.cover_image_phoneUrl = cover_image_phoneUrl;
    }

    public void setFavourite_count(int favourite_count) {
        this.favourite_count = favourite_count;
    }

    public void setFollowers_count(int followers_count) {
        this.followers_count = followers_count;
    }

    public void setFriends_count(int friends_count) {
        this.friends_count = friends_count;
    }

    public void setUserIdCity(int userIdCity) {
        this.userIdCity = userIdCity;
    }

    public void setUserIdClass(int userIdClass) {
        this.userIdClass = userIdClass;
    }

    public void setUserIdImageUrl(String userIdImageUrl) {
        this.userIdImageUrl = userIdImageUrl;
    }

    public void setUserIdProvince(int userIdProvince) {
        this.userIdProvince = userIdProvince;
    }

    public void setUserLocation(String userLocation) {
        this.userLocation = userLocation;
    }

    public String getCreated_at() {
        return created_at;
    }

    public int getFavourite_count() {
        return favourite_count;
    }

    public int getFollowers_count() {
        return followers_count;
    }

    public int getFriends_count() {
        return friends_count;
    }

    public int getUserIdCity() {
        return userIdCity;
    }

    public int getUserIdClass() {
        return userIdClass;
    }

    public int getUserIdProvince() {
        return userIdProvince;
    }

    public String getCover_image_phoneUrl() {
        return cover_image_phoneUrl;
    }

    public String getUserLocation() {
        return userLocation;
    }
}
