package sushine_group.hrm_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sushine_group.hrm_management_system.model.ChucVu;

@Repository
public interface ChucVuRepository extends JpaRepository<ChucVu, Integer> {
}
