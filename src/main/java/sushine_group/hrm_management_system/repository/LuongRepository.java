package sushine_group.hrm_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sushine_group.hrm_management_system.model.Luong;
import sushine_group.hrm_management_system.model.LuongId;

import java.util.List;

@Repository
public interface LuongRepository extends JpaRepository<Luong, LuongId> {

    @Query("SELECT l FROM Luong l WHERE YEAR(l.thangNam) = :year AND MONTH(l.thangNam) = :month")
    List<Luong> findByMonth(int year, int month);
}
