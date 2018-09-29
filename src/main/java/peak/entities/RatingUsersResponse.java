package peak.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RatingUsersResponse {

    @SerializedName("rating_users")
    @JsonProperty("rating_users")
    private List<User> users;

    public RatingUsersResponse() {

    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
