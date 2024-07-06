package sushine_group.hrm_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sushine_group.hrm_management_system.model.Luong;
import sushine_group.hrm_management_system.model.LuongId;

public interface LuongRepository extends JpaRepository<Luong, LuongId> {

}