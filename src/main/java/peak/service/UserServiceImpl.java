package peak.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peak.dao.UserDao;
import peak.entities.Client;
import peak.entities.User;
import peak.entities.Vote;
import peak.entities.tables.Users;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final Map<Client,Double> clients = new ConcurrentHashMap<>();

    @Autowired
    public UserServiceImpl(final UserDao userDao) {
        this.userDao = userDao;
    }

    @PostConstruct
    public void init() {
        List<User> users = userDao.getAllUsers();
        for (User user : users) {
            Client client = new Client(user.getUserId(), user.getGroupId());
            clients.put(client, user.getEloRating());
        }
    }

    @Override
    public List<Users> getUsers(int groupId, List<Integer> userIds) {
        return null;
    }

    @Override
    public void vote(Vote vote) {

    }
}
