package peak.entities;

public class User {

    private int userId;

    private int groupId;

    private double eloRating;

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
