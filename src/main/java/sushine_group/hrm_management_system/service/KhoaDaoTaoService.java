package sushine_group.hrm_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sushine_group.hrm_management_system.model.KhoaDaoTao;
import sushine_group.hrm_management_system.repository.KhoaDaoTaoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class KhoaDaoTaoService {

    @Autowired
    private KhoaDaoTaoRepository khoaDaoTaoRepository;

    // Lấy danh sách tất cả khóa đào tạo
//    public List<KhoaDaoTao> getAllKhoaDaoTaos() {
//        return khoaDaoTaoRepository.findAll();
//    }
    public Page<KhoaDaoTao> getAllKhoaDaoTaos(Pageable pageable) {
        return khoaDaoTaoRepository.findAll(pageable);
    }

    // Lấy thông tin một khóa đào tạo theo ID
    public Optional<KhoaDaoTao> getKhoaDaoTaoById(int id) {
        return khoaDaoTaoRepository.findById(id);
    }

    // Thêm mới hoặc cập nhật thông tin khóa đào tạo
    public KhoaDaoTao saveKhoaDaoTao(KhoaDaoTao khoaDaoTao) {
        return khoaDaoTaoRepository.save(khoaDaoTao);
    }

    public KhoaDaoTao updateKhoaDaoTao(KhoaDaoTao khoaDaoTao) {
        KhoaDaoTao existingKhoaDaoTao = khoaDaoTaoRepository.findById(khoaDaoTao.getId()).orElse(null);
        if (existingKhoaDaoTao != null) {
            existingKhoaDaoTao.setTenKhoa(khoaDaoTao.getTenKhoa());
            existingKhoaDaoTao.setMoTa(khoaDaoTao.getMoTa());
            existingKhoaDaoTao.setDiaChi(khoaDaoTao.getDiaChi());
            existingKhoaDaoTao.setNgayBatDau(khoaDaoTao.getNgayBatDau());
            existingKhoaDaoTao.setNgayKetThuc(khoaDaoTao.getNgayKetThuc());
            existingKhoaDaoTao.setNguoiHuongDan(khoaDaoTao.getNguoiHuongDan());
            return khoaDaoTaoRepository.save(existingKhoaDaoTao);
        }
        return null;
    }

    // Xóa một khóa đào tạo theo ID
    public void deleteKhoaDaoTaoById(int id) {
        khoaDaoTaoRepository.deleteById(id);
    }

    // Kiểm tra nếu khóa đào tạo có tồn tại theo ID
    public boolean existsById(int id) {
        return khoaDaoTaoRepository.existsById(id);
    }

    // Tìm kiếm khóa đào tạo theo từ khóa
    public Page<KhoaDaoTao> searchKhoaDaoTaos(String keyword, Pageable pageable) {
        return khoaDaoTaoRepository.findByTenKhoaContainingIgnoreCaseOrMoTaContainingIgnoreCase(keyword, keyword, pageable);
    }
}