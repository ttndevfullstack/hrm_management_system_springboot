package sushine_group.hrm_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sushine_group.hrm_management_system.model.Role;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {
    Role findRoleById(Long id);
    Role findRoleByName(String name);
}
