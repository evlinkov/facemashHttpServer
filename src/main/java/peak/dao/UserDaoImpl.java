package peak.dao;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import peak.entities.User;

import java.util.List;

import static peak.entities.tables.Users.USERS;

@Repository
public class UserDaoImpl implements UserDao {

    private final DSLContext dsl;

    @Autowired
    public UserDaoImpl(final DSLContext dsl) {
        this.dsl = dsl;
    }

    @Override
    public List<User> getAllUsers() {
        return dsl.selectFrom(USERS)
                .fetchInto(User.class);
    }
}
