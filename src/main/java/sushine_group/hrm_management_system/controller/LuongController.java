package sushine_group.hrm_management_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sushine_group.hrm_management_system.model.Luong;
import sushine_group.hrm_management_system.service.LuongService;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/luong")
public class LuongController {

    @Autowired
    private LuongService luongService;

    @PostMapping("/capnhat")
    public void tinhLuongToanBoNhanVien() {
        luongService.tinhLuongToanBoNhanVien();
    }

    @GetMapping("/danhsach")
    public String hienThiDanhSachLuong(Model model) {
        List<Luong> danhSachLuong = luongService.layDanhSachLuong();
        model.addAttribute("danhSachLuong", danhSachLuong);
        return "hr/luong";
    }
}