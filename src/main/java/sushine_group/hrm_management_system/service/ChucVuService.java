package sushine_group.hrm_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import sushine_group.hrm_management_system.model.ChucVu;
import sushine_group.hrm_management_system.repository.ChucVuRepository;

import java.util.List;

@Service
public class ChucVuService {
    @Autowired
    private ChucVuRepository chucVuRepository;

    public List<ChucVu> findAll() {
        return chucVuRepository.findAll();
    }

    public ChucVu findById(int idChucVu) {
        return chucVuRepository.findById(idChucVu).orElse(null);
    }
}
