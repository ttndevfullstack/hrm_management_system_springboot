package sushine_group.hrm_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sushine_group.hrm_management_system.model.NhanVien;

import java.util.Optional;

public interface NhanVienRepository extends JpaRepository<NhanVien, Integer> {
    Optional<NhanVien> findById(String integer);
}
