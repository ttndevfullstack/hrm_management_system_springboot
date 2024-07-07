/*
package sushine_group.hrm_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sushine_group.hrm_management_system.model.ChucVu;
import sushine_group.hrm_management_system.model.Luong;
import sushine_group.hrm_management_system.model.NhanVien;
import sushine_group.hrm_management_system.repository.ChamCongRepository;
import sushine_group.hrm_management_system.repository.LuongRepository;
import sushine_group.hrm_management_system.repository.NhanVienRepository;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LuongService {

    private final LuongRepository luongRepository;
    private final ChamCongRepository chamCongRepository;

    @Autowired
    public LuongService(LuongRepository luongRepository, ChamCongRepository chamCongRepository) {
        this.luongRepository = luongRepository;
        this.chamCongRepository = chamCongRepository;
    }

    @Transactional(readOnly = true)
    public List<Luong> getLuongByMonth(YearMonth selectedMonth) {
        int year = selectedMonth.getYear();
        int month = selectedMonth.getMonthValue();
        return luongRepository.findByMonth(year, month);
    }
    @Transactional(readOnly = true)
    public List<Luong> getAllLuong() {
        return luongRepository.findAll();
    }
}*/
package sushine_group.hrm_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sushine_group.hrm_management_system.model.Luong;
import sushine_group.hrm_management_system.repository.LuongRepository;

import java.time.YearMonth;
import java.util.List;

@Service
public class LuongService {

    private final LuongRepository luongRepository;

    @Autowired
    public LuongService(LuongRepository luongRepository) {
        this.luongRepository = luongRepository;
    }

    @Transactional(readOnly = true)
    public List<Luong> getLuongByMonth(YearMonth selectedMonth) {
        int year = selectedMonth.getYear();
        int month = selectedMonth.getMonthValue();
        return luongRepository.findByMonth(year, month);
    }

    @Transactional(readOnly = true)
    public List<Luong> getAllLuong() {
        return luongRepository.findAll();
    }
}

