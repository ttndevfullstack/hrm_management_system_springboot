package sushine_group.hrm_management_system.model.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sushine_group.hrm_management_system.model.Role;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String username;
    private String email;
    private String phone;
    private String password;
    private Set<Role> roles = new HashSet<>();
}

