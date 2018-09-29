package peak.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import peak.dao.UserDao;
import peak.entities.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserServiceImpl implements UserService {

    private final static double DEFAULT_ELO_RATING = 1500;
    private final static int COEF = 16;
    private final static int SECOND = 1000;
    private final static int MINUTE = 60 * SECOND;

    private final UserDao userDao;
    private final Map<Client,Double> clients = new ConcurrentHashMap<>();

    @Value("${log.path}")
    private String logPath;

    @Value("${log.file.name}")
    private String logFileName;

    private List<String> logs = Collections.synchronizedList(new ArrayList<>());

    @Autowired
    public UserServiceImpl(final UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional(readOnly = true)
    @PostConstruct
    public void init() {
        List<User> users = userDao.getAllUsers();
        for (User user : users) {
            Client client = new Client(user.getUserId(), user.getGroupId());
            clients.put(client, user.getEloRating());
        }
    }

    @Override
    public RatingUsersResponse getUsers(RatingUsersRequest ratingUsersRequest) {
        List<User> users = new ArrayList<>();
        for (Integer userId : ratingUsersRequest.getUsersId()) {
            Client client = new Client(userId, ratingUsersRequest.getGroupId());
            if (!clients.containsKey(client)) {
                addUser(client);
            }
            users.add(new User(userId, ratingUsersRequest.getGroupId(), clients.get(client)));
        }
        RatingUsersResponse ratingUsersResponse = new RatingUsersResponse();
        ratingUsersResponse.setUsers(users);
        return ratingUsersResponse;
    }

    @Override
    public synchronized void vote(Vote vote) {
        Client leftClient = new Client(vote.getLeftUserId(), vote.getGroupId());
        Client rightClient = new Client(vote.getRightUserId(), vote.getGroupId());
        if (!clients.containsKey(leftClient)) {
            addUser(leftClient);
        }
        if (!clients.containsKey(rightClient)) {
            addUser(rightClient);
        }

        double leftEloRating = clients.get(leftClient);
        double rightEloRating = clients.get(rightClient);

        clients.put(leftClient, getUpdatedEloRate(leftEloRating, rightEloRating,
                vote.isVote()));
        clients.put(rightClient, getUpdatedEloRate(rightEloRating, leftEloRating,
                !vote.isVote()));
    }

    @Override
    @Scheduled(initialDelay = 5 * MINUTE, fixedDelay = 5 * MINUTE)
    public void printLog() throws Exception {
        String timestamp = String.valueOf(new Timestamp(System.currentTimeMillis()).getTime());
        PrintWriter writer = new PrintWriter(logPath + logFileName + timestamp + ".txt", "UTF-8");

        int n = logs.size();
        for (int i = 0; i < n; ++i) {
            writer.println(logs.get(i));
        }
        logs.subList(0, n).clear();

        writer.close();
    }

    @PreDestroy
    public void preDestroy() throws Exception {
        printLog();
    }

    private synchronized void addUser(Client client) {
        if (clients.containsKey(client)) {
            return;
        }
        clients.put(client, DEFAULT_ELO_RATING);
    }

    private double calculateEloRate(double left, double right) {
        return 1.0 / (1.0 + Math.pow(10, (right - left) / 400));
    }

    private double getUpdatedEloRate(double left, double right, boolean vote) {
        double sa;
        if (vote) {
            sa = 0;
        } else {
            sa = 1;
        }
        return (sa - calculateEloRate(left, right)) * COEF;
    }
}
