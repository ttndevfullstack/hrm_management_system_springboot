package sushine_group.hrm_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sushine_group.hrm_management_system.model.ChucVu;
import sushine_group.hrm_management_system.model.NhanVien;
import sushine_group.hrm_management_system.repository.NhanVienRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LuongService {

    @Autowired
    private NhanVienRepository nhanVienRepository;

    public List<NhanVien> getAllNhanVienWithLuong() {
        List<NhanVien> nhanViens = nhanVienRepository.findAll();
        return nhanViens.stream().map(this::calculateLuong).collect(Collectors.toList());
    }

    private NhanVien calculateLuong(NhanVien nhanVien) {
        ChucVu chucVu = nhanVien.getChucVu();
        BigDecimal luongCoBan = chucVu.getLuongCoBan();
        BigDecimal heSoLuong = chucVu.getHeSoLuong();
        BigDecimal phuCapChucVu = chucVu.getPhuCapChucVu();

        // Thực hiện tính toán lương
        BigDecimal salary = luongCoBan.multiply(heSoLuong).add(phuCapChucVu);

        BigDecimal thueTNCN = tinhThueTNCN(salary);

        // Lương sau khi trừ thuế
        BigDecimal luongSauThue = salary.subtract(thueTNCN);

        nhanVien.setLuongSauThue(luongSauThue);
        return nhanVien;
    }

    private BigDecimal tinhThueTNCN(BigDecimal luong) {
        BigDecimal thueTNCN;

        if (luong.compareTo(BigDecimal.valueOf(5000000)) <= 0) {
            thueTNCN = BigDecimal.ZERO;
        } else if (luong.compareTo(BigDecimal.valueOf(10000000)) <= 0) {
            thueTNCN = luong.multiply(BigDecimal.valueOf(0.05));
        } else if (luong.compareTo(BigDecimal.valueOf(18000000)) <= 0) {
            thueTNCN = luong.multiply(BigDecimal.valueOf(0.1)).subtract(BigDecimal.valueOf(250000));
        } else if (luong.compareTo(BigDecimal.valueOf(32000000)) <= 0) {
            thueTNCN = luong.multiply(BigDecimal.valueOf(0.15)).subtract(BigDecimal.valueOf(750000));
        } else if (luong.compareTo(BigDecimal.valueOf(52000000)) <= 0) {
            thueTNCN = luong.multiply(BigDecimal.valueOf(0.2)).subtract(BigDecimal.valueOf(1650000));
        } else if (luong.compareTo(BigDecimal.valueOf(80000000)) <= 0) {
            thueTNCN = luong.multiply(BigDecimal.valueOf(0.25)).subtract(BigDecimal.valueOf(3250000));
        } else {
            thueTNCN = luong.multiply(BigDecimal.valueOf(0.3)).subtract(BigDecimal.valueOf(5850000));
        }
        // Đảm bảo thuế không âm và không vượt quá lương
        thueTNCN = thueTNCN.max(BigDecimal.ZERO);
        thueTNCN = thueTNCN.min(luong);
        return thueTNCN;
    }
}
