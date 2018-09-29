package peak.service;

import peak.entities.RatingUsersRequest;
import peak.entities.RatingUsersResponse;
import peak.entities.Vote;

public interface UserService {

    RatingUsersResponse getUsers(RatingUsersRequest ratingUsersRequest);

    void vote(Vote vote);

    void printLog() throws Exception;

}
