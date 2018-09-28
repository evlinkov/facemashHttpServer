package peak.service;

import peak.entities.RatingUsersResponse;
import peak.entities.Vote;

import java.util.List;

public interface UserService {

    RatingUsersResponse getUsers(int groupId, List<Integer> userIds);

    void vote(Vote vote);

}
