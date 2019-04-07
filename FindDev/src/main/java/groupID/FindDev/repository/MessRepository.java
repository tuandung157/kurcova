package groupID.FindDev.repository;

import groupID.FindDev.model.Mess;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessRepository extends JpaRepository<Mess,Long> {
}
