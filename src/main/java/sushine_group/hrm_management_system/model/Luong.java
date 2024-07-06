package sushine_group.hrm_management_system.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "LUONG")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Luong {
    @EmbeddedId
    private LuongId id;
    @Column(name = "ThanhTien", precision = 18, scale = 2)
    private BigDecimal thanhTien;
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idNV")
    @JoinColumn(name = "IDNV")
    private NhanVien nhanVien;
    /*@Override
    public String toString() {
        return "Luong{" +
                "id=" + id +
                ", thanhTien=" + thanhTien +
                '}';
    }*/
}
