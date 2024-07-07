package sushine_group.hrm_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import sushine_group.hrm_management_system.model.KhoaDaoTao;

public interface KhoaDaoTaoRepository extends JpaRepository<KhoaDaoTao, Integer>, PagingAndSortingRepository<KhoaDaoTao, Integer> {
    void deleteById(int id);
}
