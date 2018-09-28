package peak.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peak.dao.UserDao;
import peak.entities.Vote;
import peak.entities.tables.Users;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(final UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<Users> getUsers(int groupId, List<Integer> userIds) {
        return null;
    }

    @Override
    public void vote(Vote vote) {

    }
}
