package sushine_group.hrm_management_system.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import sushine_group.hrm_management_system.model.Dtos.KhoaDaoTaoDto;
import sushine_group.hrm_management_system.model.KhoaDaoTao;
import sushine_group.hrm_management_system.model.NhanVien;
import sushine_group.hrm_management_system.model.User;
import sushine_group.hrm_management_system.service.DaoTaoNhanVienService;
import sushine_group.hrm_management_system.service.KhoaDaoTaoService;

import jakarta.servlet.http.HttpSession;
import sushine_group.hrm_management_system.service.UserService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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
                                  @RequestParam(value = "keyword", required = false) String keyword,
                                  HttpSession session,
                                  Authentication authentication) {
        int pageSize = 5; // Số lượng bản ghi trên mỗi trang
        PageRequest pageable = PageRequest.of(page - 1, pageSize);
        Page<KhoaDaoTao> khoaDaoTaoPage = khoaDaoTaoService.getAllKhoaDaoTaos(pageable);

        if (keyword != null && !keyword.isEmpty()) {
            khoaDaoTaoPage = khoaDaoTaoService.searchKhoaDaoTaos(keyword, pageable);
            model.addAttribute("keyword", keyword);
        } else {
            khoaDaoTaoPage = khoaDaoTaoService.getAllKhoaDaoTaos(pageable);
        }
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

    @GetMapping("/add")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String addKhoaDaoTao(Model model) {
        model.addAttribute("khoaDaoTao", new KhoaDaoTaoDto());
        return "khoadaotao/khoaDaoTao-add";
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String addKhoaDaoTao(@Valid @ModelAttribute("khoaDaoTao") KhoaDaoTaoDto khoaDaoTao,
                                BindingResult bindingResult, Model model) throws ParseException {
        if (bindingResult.hasErrors()) {
            System.out.print(bindingResult);
            return "khoadaotao/khoaDaoTao-add"; // Nếu có lỗi, trả về form thêm mới lại
        }
        khoaDaoTaoService.saveKhoaDaoTao(khoaDaoTao); // Lưu thông tin khoaDaoTao mới vào cơ sở dữ liệu
        return "redirect:/khoaDaoTaos"; // Sau khi thêm mới thành công, chuyển hướng về danh sách khoaDaoTaos
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        Optional<KhoaDaoTao> optionalKhoaDaoTao = khoaDaoTaoService.getKhoaDaoTaoById(id);
        if (optionalKhoaDaoTao.isPresent()) {
            model.addAttribute("khoaDaoTao", optionalKhoaDaoTao.get());
            return "khoaDaoTao/khoaDaoTao-edit";
        } else {
            return "redirect:/error";
        }
    }

    @PostMapping("/edit/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String editKhoaDaoTao(@PathVariable("id") int id,
                                 @Valid @ModelAttribute("khoaDaoTao") KhoaDaoTaoDto khoaDaoTao,
                                 BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "khoaDaoTao/khoaDaoTao-edit";
        }
        khoaDaoTao.setId(id);
        khoaDaoTaoService.saveKhoaDaoTao(khoaDaoTao);
        return "redirect:/khoaDaoTaos";
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String deleteKhoaDaoTao(@PathVariable("id") int id) {
        khoaDaoTaoService.deleteKhoaDaoTaoById(id);
        return "redirect:/khoaDaoTaos";
    }


}
