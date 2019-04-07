package groupID.FindDev.repository;

import groupID.FindDev.model.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserGroupRepository extends JpaRepository<UserGroup,Long> {
}
