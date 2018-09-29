package peak;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import peak.entities.RatingUsersRequest;
import peak.entities.RatingUsersResponse;
import peak.entities.Vote;
import peak.service.UserService;

/**
 * Created by evgenylinkov on 29.09.18.
 */
@RestController
@RequestMapping("/facemash")
public class Controller {

    private final UserService userService;

    @Autowired
    public Controller(final UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/get/users", method = RequestMethod.POST,
        produces = "application/json", consumes = "application/json")
    public RatingUsersResponse getUsers(@RequestBody RatingUsersRequest ratingUsersRequest) {
        return userService.getUsers(ratingUsersRequest);
    }

    @RequestMapping(value = "/vote", method = RequestMethod.POST,
        consumes = "application/json")
    public void vote(@RequestBody Vote vote) {
        userService.vote(vote);
    }
}
