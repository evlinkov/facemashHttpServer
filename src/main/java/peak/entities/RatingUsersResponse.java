package peak.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RatingUsersResponse {

    @SerializedName("rating_users")
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
