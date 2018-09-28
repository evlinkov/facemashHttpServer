package peak.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RatingUsersRequest {

    @SerializedName("group_id")
    private int groupId;

    @SerializedName("users_id")
    private List<Integer> usersId;

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public List<Integer> getUsersId() {
        return usersId;
    }

    public void setUsersId(List<Integer> usersId) {
        this.usersId = usersId;
    }
}
