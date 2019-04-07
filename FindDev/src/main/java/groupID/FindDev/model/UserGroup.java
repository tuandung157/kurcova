package groupID.FindDev.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
public class UserGroup implements Serializable {

    @Id @GeneratedValue
    Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @JoinColumn(name = "group_id")
    User group;

    Timestamp createdAt;
    Long Grade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getGroup() {
        return group;
    }

    public void setGroup(User group) {
        this.group = group;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Long getGrade() {
        return Grade;
    }

    public void setGrade(Long grade) {
        Grade = grade;
    }
}
