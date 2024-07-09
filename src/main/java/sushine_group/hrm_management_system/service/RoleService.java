package sushine_group.hrm_management_system.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sushine_group.hrm_management_system.model.Role;
import sushine_group.hrm_management_system.repository.IRoleRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final IRoleRepository roleRepository;

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public Role findRoleByName(String name) {
        return roleRepository.findRoleByName(name);
    }
}
