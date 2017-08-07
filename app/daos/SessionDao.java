package daos;

import com.avaje.ebean.Finder;
import com.avaje.ebean.QueryIterator;
import models.Session;

public class SessionDao {
    private static SessionDao sessionDaoInstance;

    public static SessionDao getInstance() {
        if (sessionDaoInstance == null) {
            sessionDaoInstance = new SessionDao();
        }
        return sessionDaoInstance;
    }

    private Finder<String, Session> sessionFinder;

    private SessionDao() {
        sessionFinder = new Finder<>(Session.class);
    }

    public Session find(String userId, String accessToken) {
        QueryIterator<Session> queryIterator = sessionFinder.query()
                .where()
                .eq("access_token", accessToken)
                .eq("user_user_id", userId)
                .findIterate();
        if (queryIterator.hasNext()) {
            return queryIterator.next();
        }
        return null;
    }
}
