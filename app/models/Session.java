package models;

import com.avaje.ebean.Model;
import com.eclipsesource.json.JsonObject;
import configs.ParamConfig;
import utils.AppUtil;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "sessions")
public class Session extends Model {
    @ManyToOne(optional = false)
    private User user;

    @Id
    private String sessionId;
    private String accessToken;
    private String refreshToken;
    private Date sessionBeginTime;
    private Date sessionEndTime;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Date getSessionBeginTime() {
        return sessionBeginTime;
    }

    public void setSessionBeginTime(Date sessionBeginTime) {
        this.sessionBeginTime = sessionBeginTime;
    }

    public Date getSessionEndTime() {
        return sessionEndTime;
    }

    public void setSessionEndTime(Date sessionEndTime) {
        this.sessionEndTime = sessionEndTime;
    }

    public boolean isExpired() {
        return AppUtil.getCurrentDate().after(getSessionEndTime());
    }

    public JsonObject toJson() {
        JsonObject session = new JsonObject();
        session.add(ParamConfig.PARAM_USER_HASH_KEY, getUser().getUserHash());
        session.add(ParamConfig.PARAM_ACCESS_TOKEN_KEY, getAccessToken());
        session.add(ParamConfig.PARAM_REFRESH_TOKEN_KEY, getRefreshToken());
        session.add(ParamConfig.PARAM_SESSION_BEGIN_TIME_KEY, getSessionBeginTime().getTime());
        session.add(ParamConfig.PARAM_SESSION_END_TIME_KEY, getSessionEndTime().getTime());
        return session;
    }
}
