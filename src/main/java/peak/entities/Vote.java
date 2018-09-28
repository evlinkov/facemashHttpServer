package peak.entities;

import com.google.gson.annotations.SerializedName;

public class Vote {

    @SerializedName("user_id")
    private int userId;

    @SerializedName("left_user_id")
    private int leftUserId;

    @SerializedName("right_user_id")
    private int rightUserId;

    @SerializedName("group_id")
    private int groupId;

    // 0 - победил первый, 1 - победил второй
    @SerializedName("vote")
    private boolean vote;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getLeftUserId() {
        return leftUserId;
    }

    public void setLeftUserId(int leftUserId) {
        this.leftUserId = leftUserId;
    }

    public int getRightUserId() {
        return rightUserId;
    }

    public void setRightUserId(int rightUserId) {
        this.rightUserId = rightUserId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public boolean isVote() {
        return vote;
    }

    public void setVote(boolean vote) {
        this.vote = vote;
    }
}
