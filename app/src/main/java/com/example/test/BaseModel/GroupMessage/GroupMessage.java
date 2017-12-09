package com.example.test.BaseModel.GroupMessage;

import java.util.ArrayList;

/**
 * Created by cyc20 on 2017/12/6.
 */

public class GroupMessage {
    public String groupName;
    public ArrayList<String> groupMembers;

    public GroupMessage(String groupName, ArrayList<String> groupMembers) {
        this.groupName = groupName;
        this.groupMembers = groupMembers;
    }

    public void setGroupMembers(ArrayList<String> groupMembers) {
        this.groupMembers = groupMembers;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public ArrayList<String> getGroupMembers() {
        return groupMembers;
    }

    public String getGroupName() {
        return groupName;
    }
}
