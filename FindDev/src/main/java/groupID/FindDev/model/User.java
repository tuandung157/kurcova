package groupID.FindDev.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "users")
public class User extends AuditModel {
    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long userId;

    private String username;
    private String password;
    private String email;
    private String telephone;
    private String text;
    private Timestamp lastLog;

    @OneToMany(mappedBy = "user")
    Set<UserGroup>  userGroup;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getLastLog() {
        return lastLog;
    }

    public void setLastLog(Timestamp lastLog) {
        this.lastLog = lastLog;
    }

    public Set<UserGroup> getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(Set<UserGroup> userGroup) {
        this.userGroup = userGroup;
    }
}
