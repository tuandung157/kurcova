package groupID.FindDev.repository;

import groupID.FindDev.model.Group;
import groupID.FindDev.model.Mess;
import groupID.FindDev.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MessRepository extends JpaRepository<Mess,Long> {
    List<Mess> findByUserFromAndUserTo(User userFrom, User userTo);
    List<Mess> findByUserFromAndGroupTo(User userFrom, Group groupTo);
}


