package groupID.FindDev.repository;

import groupID.FindDev.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Long> {
}