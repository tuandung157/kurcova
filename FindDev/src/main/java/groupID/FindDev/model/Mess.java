package groupID.FindDev.model;

import org.springframework.web.bind.annotation.RestController;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "Mess")
public class Mess extends AuditModel {
    @Id
    private int messId;
    private String text;
    @ManyToOne
    @JoinColumn(name = "user_from",referencedColumnName = "user_id")
    private User userFrom;

    @ManyToOne
    @JoinColumn(name = "user_to",referencedColumnName = "user_id")
    private User userTo;

    @ManyToOne
    @JoinColumn(name = "group_to")
    private Group groupTo;

    public int getMessId() {
        return messId;
    }

    public void setMessId(int messId) {
        this.messId = messId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(User userFrom) {
        this.userFrom = userFrom;
    }

    public User getUserTo() {
        return userTo;
    }

    public void setUserTo(User userTo) {
        this.userTo = userTo;
    }

    public Group getGroupTo() {
        return groupTo;
    }

    public void setGroupTo(Group groupTo) {
        this.groupTo = groupTo;
    }
}
