package peak.entities;

public class Client {

    private int userId;

    private int groupId;

    public Client(int userId, int groupId) {
        this.userId = userId;
        this.groupId = groupId;
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

    @Override
    public int hashCode() {
        return (this.userId * this.groupId) * (this.userId * this.groupId + 1) / 2 + this.groupId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Client that = (Client) o;
        return (this.userId == that.userId && this.groupId == that.groupId);
    }

    @Override
    public String toString() {
        return "Client{" +
                "userId=" + userId +
                ", groupId=" + groupId +
                '}';
    }
}
