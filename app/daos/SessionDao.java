package daos;

public class SessionDao {
    private static SessionDao sessionDaoInstance;

    public static SessionDao getInstance() {
        if (sessionDaoInstance == null) {
            sessionDaoInstance = new SessionDao();
        }
        return sessionDaoInstance;
    }

    private SessionDao() {
    }
}
