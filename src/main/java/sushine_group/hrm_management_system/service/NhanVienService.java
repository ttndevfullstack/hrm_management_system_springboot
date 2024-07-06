package sushine_group.hrm_management_system.service;

import sushine_group.hrm_management_system.model.Category;
import sushine_group.hrm_management_system.model.NhanVien;
import sushine_group.hrm_management_system.repository.NhanVienRepository;

import java.util.Optional;

public class NhanVienService {
    NhanVienRepository nhanVienRepository;
    public Optional<NhanVien> getNhanVienById(String id) {
        return nhanVienRepository.findById(id);
    }
}
