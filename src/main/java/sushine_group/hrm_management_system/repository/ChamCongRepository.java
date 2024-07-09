package sushine_group.hrm_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sushine_group.hrm_management_system.model.Category;
import sushine_group.hrm_management_system.model.ChamCong;
import sushine_group.hrm_management_system.model.ChamCongId;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ChamCongRepository extends JpaRepository<ChamCong, ChamCongId> {
    @Query("SELECT c FROM ChamCong c WHERE YEAR(c.thangNam) = :year AND MONTH(c.thangNam) = :month")
    List<ChamCong> findByMonth(@Param("year") int year, @Param("month") int month);
}
