package peak.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RatingUsersRequest {

    @SerializedName("group_id")
    @JsonProperty("group_id")
    private int groupId;

    @SerializedName("users_id")
    @JsonProperty("users_id")
    private List<Integer> usersId;

    public RatingUsersRequest() {

    }

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
