package peak.service;

import peak.entities.Vote;
import peak.entities.tables.Users;

import java.util.List;

public interface UserService {

    List<Users> getUsers(int groupId, List<Integer> userIds);

    void vote(Vote vote);

}
