package daos;

import com.avaje.ebean.Finder;
import com.avaje.ebean.QueryIterator;
import models.User;

public class UserDao {
    private static UserDao userDaoInstance;

    public static UserDao getInstance() {
        if (userDaoInstance == null) {
            userDaoInstance = new UserDao();
        }
        return userDaoInstance;
    }

    private Finder<String, User> userFinder;

    private UserDao() {
        userFinder = new Finder<>(User.class);
    }

    public boolean isEmailExists(String userEmail) {
        return userFinder.query()
                .where()
                .eq("user_email", userEmail)
                .findRowCount() > 0;
    }

    public User find(String userEmail, String userPassword) {
        QueryIterator<User> queryIterator = userFinder.query()
                .where()
                .eq("user_email", userEmail)
                .eq("user_password", userPassword)
                .findIterate();
        if (queryIterator.hasNext()) {
            return queryIterator.next();
        }
        return null;
    }

    public User find(String userHash) {
        QueryIterator<User> queryIterator = userFinder.query()
                .where()
                .eq("user_hash", userHash)
                .findIterate();
        if (queryIterator.hasNext()) {
            return queryIterator.next();
        }
        return null;
    }
}
