package models;

import com.avaje.ebean.Model;
import com.eclipsesource.json.JsonObject;
import configs.ParamConfig;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "users")
public class User extends Model {
    @Constraints.Required
    @Constraints.Email
    private String userEmail;

    @Constraints.Required
    @Constraints.MinLength(8)
    @Constraints.MaxLength(30)
    private String userPassword;

    @Constraints.Required
    @Constraints.MinLength(3)
    @Constraints.MaxLength(30)
    private String userName;

    @Id
    private String userId;

    private String userHash;

    @OneToMany(mappedBy = "user")
    private List<Session> sessions;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserHash() {
        return userHash;
    }

    public void setUserHash(String userHash) {
        this.userHash = userHash;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    public JsonObject toJson() {
        JsonObject userAsJson = new JsonObject();
        userAsJson.add(ParamConfig.PARAM_USER_NAME_KEY, getUserName());
        userAsJson.add(ParamConfig.PARAM_USER_EMAIL_KEY, getUserEmail());
        userAsJson.add(ParamConfig.PARAM_USER_HASH_KEY, getUserHash());
        return userAsJson;
    }
}
