package daos;

public class UserDao {
    private static UserDao userDaoInstance;

    public static UserDao getInstance() {
        if (userDaoInstance == null) {
            userDaoInstance = new UserDao();
        }
        return userDaoInstance;
    }

    private UserDao() {
    }
}
