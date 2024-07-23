package sushine_group.hrm_management_system.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import sushine_group.hrm_management_system.model.KhoaDaoTao;

import java.util.List;

public interface KhoaDaoTaoRepository extends JpaRepository<KhoaDaoTao, Integer>, PagingAndSortingRepository<KhoaDaoTao, Integer> {
    void deleteById(int id);
    Page<KhoaDaoTao> findByTenKhoaContaining(String keyword, Pageable pageable);
    Page<KhoaDaoTao> findByTenKhoaContainingIgnoreCaseOrMoTaContainingIgnoreCase(String tenKhoa, String moTa, Pageable pageable);
}
