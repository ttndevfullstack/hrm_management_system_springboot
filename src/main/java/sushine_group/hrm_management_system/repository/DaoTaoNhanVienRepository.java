package sushine_group.hrm_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sushine_group.hrm_management_system.model.DaoTaoNhanVien;

import java.util.List;

@Repository
public interface DaoTaoNhanVienRepository extends JpaRepository<DaoTaoNhanVien, Integer> {
    List<DaoTaoNhanVien> findByNhanVienId(String nhanVienId);
    void deleteByNhanVienIdAndKhoaDaoTaoId(String nhanVienId, int khoaDaoTaoId);
    boolean existsByNhanVienIdAndKhoaDaoTaoId(String nhanVienId, int khoaDaoTaoId);
}
