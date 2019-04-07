package groupID.FindDev.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "Groups")
public class Group {
    @Id
    private Long groupId ;
    private String groupName;
    private int groupNumber;
    private int numProject;
    private String about;


    @OneToMany(mappedBy = "group")
    Set<UserGroup>  userGroup;

    private Timestamp createdAt;

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }

    public int getNumProject() {
        return numProject;
    }

    public void setNumProject(int numProject) {
        this.numProject = numProject;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Set<UserGroup> getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(Set<UserGroup> userGroup) {
        this.userGroup = userGroup;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
