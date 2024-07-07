package sushine_group.hrm_management_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sushine_group.hrm_management_system.model.KhoaDaoTao;
import sushine_group.hrm_management_system.model.NhanVien;
import sushine_group.hrm_management_system.model.User;
import sushine_group.hrm_management_system.service.DaoTaoNhanVienService;
import sushine_group.hrm_management_system.service.KhoaDaoTaoService;

import jakarta.servlet.http.HttpSession;
import sushine_group.hrm_management_system.service.UserService;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/khoaDaoTaos")
public class KhoaDaoTaoController {

    @Autowired
    private KhoaDaoTaoService khoaDaoTaoService;

    @Autowired
    private DaoTaoNhanVienService daoTaoNhanVienService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String listKhoaDaoTaos(Model model,
                                  @RequestParam(defaultValue = "1") int page,
                                  HttpSession session,
                                  Authentication authentication) {
        int pageSize = 5; // Số lượng bản ghi trên mỗi trang
        PageRequest pageable = PageRequest.of(page - 1, pageSize);
        Page<KhoaDaoTao> khoaDaoTaoPage = khoaDaoTaoService.getAllKhoaDaoTaos(pageable);

        // Lấy thông tin nhân viên từ session
        User currentUser = userService.getCurrentUser(); // Lấy người dùng hiện tại
        if (currentUser == null) {
            model.addAttribute("errors", List.of("Người dùng chưa đăng nhập"));
            return "error/errorPage";
        }

        NhanVien nhanVien = currentUser.getNhanVien(); // Lấy NhanVien từ User
        // Lấy danh sách các khóa đào tạo mà nhân viên đã theo dõi
        Set<Integer> followedKhoaDaoTaoIds = daoTaoNhanVienService.getFollowedKhoaDaoTaoIds(nhanVien.getId());

        model.addAttribute("khoaDaoTaos", khoaDaoTaoPage.getContent());
        model.addAttribute("totalPages", khoaDaoTaoPage.getTotalPages());
        model.addAttribute("pageNumber", page);
        model.addAttribute("followedKhoaDaoTaoIds", followedKhoaDaoTaoIds);
        model.addAttribute("nhanVienId", nhanVien.getId());

        return "khoadaotao/khoaDaoTao-list"; // tên của file HTML template
    }
}
