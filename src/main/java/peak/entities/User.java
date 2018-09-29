package peak.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("user_id")
    @JsonProperty("user_id")
    private int userId;

    @SerializedName("group_id")
    @JsonProperty("group_id")
    private int groupId;

    @SerializedName("elo_rating")
    @JsonProperty("elo_rating")
    private double eloRating;

    public User() {

    }

    public User(int userId, int groupId, double eloRating) {
        this.userId = userId;
        this.groupId = groupId;
        this.eloRating = eloRating;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public double getEloRating() {
        return eloRating;
    }

    public void setEloRating(double eloRating) {
        this.eloRating = eloRating;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", groupId=" + groupId +
                ", eloRating=" + eloRating +
                '}';
    }
}
