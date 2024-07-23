package sushine_group.hrm_management_system.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import sushine_group.hrm_management_system.model.PhongBan;

@Repository
public interface PhongBanRepository extends JpaRepository<PhongBan, Integer>, PagingAndSortingRepository<PhongBan, Integer> {
    Page<PhongBan> findPhongBanByTenPhongBanIgnoreCaseContaining(String keyword, Pageable pageable);
    PhongBan findByTenPhongBan(String tenPhongBan);
}
