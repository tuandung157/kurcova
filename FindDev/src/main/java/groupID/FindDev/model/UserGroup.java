package groupID.FindDev.model;

import org.springframework.web.bind.annotation.RestController;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class UserGroup extends AuditModel{

    @Id @GeneratedValue
    Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @JoinColumn(name = "group_id")
    User group;

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


    public Long getGrade() {
        return Grade;
    }

    public void setGrade(Long grade) {
        Grade = grade;
    }
}
