package groupID.FindDev.repository;

import groupID.FindDev.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findByUsername(String username);
    User findUserByUsername(String username);
}
