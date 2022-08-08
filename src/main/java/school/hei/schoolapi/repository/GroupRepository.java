package school.hei.schoolapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.hei.schoolapi.model.Groups;

public interface GroupRepository extends JpaRepository<Groups, Long> {
}
