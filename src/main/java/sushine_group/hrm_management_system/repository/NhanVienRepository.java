package sushine_group.hrm_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sushine_group.hrm_management_system.model.ChamCong;
import sushine_group.hrm_management_system.model.NhanVien;

public interface NhanVienRepository extends JpaRepository<NhanVien, String> {
}
