package sushine_group.hrm_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sushine_group.hrm_management_system.model.DaoTaoNhanVien;
import sushine_group.hrm_management_system.model.KhoaDaoTao;
import sushine_group.hrm_management_system.model.NhanVien;
import sushine_group.hrm_management_system.repository.DaoTaoNhanVienRepository;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DaoTaoNhanVienService {
    @Autowired
    private DaoTaoNhanVienRepository daoTaoNhanVienRepository;

    public void followKhoaDaoTao(NhanVien nhanVien, KhoaDaoTao khoaDaoTao) {
        if (!isFollowing(nhanVien.getId(), khoaDaoTao.getId())) {
            DaoTaoNhanVien daoTaoNhanVien = new DaoTaoNhanVien();
            daoTaoNhanVien.setNhanVien(nhanVien);
            daoTaoNhanVien.setKhoaDaoTao(khoaDaoTao);
            daoTaoNhanVien.setNgayHoanThanh(khoaDaoTao.getNgayKetThuc());
            daoTaoNhanVienRepository.save(daoTaoNhanVien);
        }
    }

    @Transactional
    public void unfollowKhoaDaoTao(String nhanVienId, int khoaDaoTaoId) {
        daoTaoNhanVienRepository.deleteByNhanVienIdAndKhoaDaoTaoId(nhanVienId, khoaDaoTaoId);
    }

    public boolean isFollowing(String nhanVienId, int khoaDaoTaoId) {
        return daoTaoNhanVienRepository.existsByNhanVienIdAndKhoaDaoTaoId(nhanVienId, khoaDaoTaoId);
    }

    public Set<Integer> getFollowedKhoaDaoTaoIds(String nhanVienId) {
        return daoTaoNhanVienRepository.findByNhanVienId(nhanVienId).stream()
                .map(daoTaoNhanVien -> daoTaoNhanVien.getKhoaDaoTao().getId())
                .collect(Collectors.toSet());
    }
}