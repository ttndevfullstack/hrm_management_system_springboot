package sushine_group.hrm_management_system.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sushine_group.hrm_management_system.model.Dtos.UserDto;
import sushine_group.hrm_management_system.model.User;
import sushine_group.hrm_management_system.service.RoleService;
import sushine_group.hrm_management_system.service.UserService;

import java.util.List;
import java.util.Optional;

@Controller // Đánh dấu lớp này là một Controller trong Spring MVC.
@RequestMapping("/")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/dashboard")
    public String dashboard() {
        return "/dashboard";
    }

    @GetMapping("/login")
    public String login() {
        return "users/login";
    }
    
    @GetMapping("/register")
    public String register(@NotNull Model model) {
        model.addAttribute("user", new User());
        return "users/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UserDto userDto) {
        userService.save(userDto);
        userService.setDefaultRole(userDto.getUsername());
        return "redirect:/login";
    }

    @GetMapping("/profile")
    public String profile(@NotNull Model model) {
        model.addAttribute("user", userService.getCurrentUser());
        return "users/profile";
    }
}
