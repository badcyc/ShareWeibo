package com.example.test.BaseModel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by cyc20 on 2017/11/29.
 */

public class Utils {
    public static String access_token = "2.00Sx1GJGtmFCAD65dc4a9aa7rOmSdC";
    public static final String getContentUrl = "https://api.weibo.com/2/statuses/home_timeline.json";
    public static final String getUidUrl = "https://api.weibo.com/2/account/get_uid.json";
    public static final String getUserMessageUrl = "https://api.weibo.com/2/users/show.json";
    public static final String getCommentsDataUrl = "https://api.weibo.com/2/comments/show.json";
    public static String directory;


    public static final int AllWeiboFragmentArgs = 1;
    public static String getPicUrl = null;
    public static String getDescription = null;
    public static String uid = null;
    public static String userName = null;

    public static String getSource(final String source_url) {
        String source = null;
        try {
            Pattern pattern = Pattern.compile("\\>([\\d\\D]*)\\<");
            Matcher matcher = pattern.matcher(source_url);
            matcher.find();
            source = matcher.group(1);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        return source;
    }

    public static String parseTime(String time) {
        String[] realTime = new String[1];
        String[] alltime = time.split(" ");
        switch (alltime[1]) {
            case "Nov":
                realTime[0] = "11";
                break;
            case "Jan":
                realTime[0] = "1";
                break;
            case "Feb":
                realTime[0] = "2";
                break;
            case "Mar":
                realTime[0] = "3";
                break;
            case "Apr":
                realTime[0] = "4";
                break;
            case "May":
                realTime[0] = "5";
                break;
            case "Jun":
                realTime[0] = "6";
                break;
            case "Jul":
                realTime[0] = "7";
                break;
            case "Aug":
                realTime[0] = "8";
                break;
            case "Sep":
                realTime[0] = "9";
                break;
            case "Oct":
                realTime[0] = "10";
                break;
            case "Dec":
                realTime[0] = "12";
                break;
            default:
                break;
        }
        return realTime[0] + "月" + alltime[2] + "日 " + alltime[3];
    }

    public static String parseMessage(Message message) {
        int repostsCounts = message.getReposts_count();
        return String.valueOf(repostsCounts);
    }
}
